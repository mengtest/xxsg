/*    */ package com.linlongyx.sanguo.webgame.processors.sign;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.sign.SignComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.LevelPackageBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sign.BuyLevelPackageRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sign.BuyLevelPackageResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BuyLevelPackageProcessor
/*    */   extends ProcessorBase<BuyLevelPackageRequest, BuyLevelPackageResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new BuyLevelPackageResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BuyLevelPackageRequest request) {
/* 29 */     SignComponent signComponent = (SignComponent)playerSession.getPlayer().createIfNotExist(SignComponent.class);
/* 30 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 31 */     LevelPackageBean levelPackageBean = (LevelPackageBean)JsonTableService.getJsonData(request.level, LevelPackageBean.class);
/* 32 */     if (null == levelPackageBean) {
/* 33 */       return 15015;
/*    */     }
/* 35 */     int cost = levelPackageBean.getPrice();
/* 36 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost)) {
/* 37 */       return 10052;
/*    */     }
/* 39 */     if (playerComponent.getLevel() < request.level) {
/* 40 */       return 10084;
/*    */     }
/* 42 */     if (!signComponent.getLevelMap().containsKey(Integer.valueOf(request.level))) {
/* 43 */       return 15015;
/*    */     }
/* 45 */     if (((Integer)signComponent.getLevelMap().get(Integer.valueOf(request.level))).intValue() < TimeUtil.currentTime()) {
/* 46 */       return 15016;
/*    */     }
/* 48 */     if (((Integer)signComponent.getTimeMap().getOrDefault(Integer.valueOf(request.level), (V)Integer.valueOf(0))).intValue() >= levelPackageBean.getSelltimes()) {
/* 49 */       return 15017;
/*    */     }
/* 51 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost, ResourceEvent.LevelPackage);
/* 52 */     FinanceUtil.rewardBean(levelPackageBean.getReward(), playerSession.getPlayer(), ResourceEvent.LevelPackage, true);
/* 53 */     signComponent.getTimeMap().put(Integer.valueOf(request.level), Integer.valueOf(((Integer)signComponent.getTimeMap().getOrDefault(Integer.valueOf(request.level), Integer.valueOf(0))).intValue() + 1));
/* 54 */     signComponent.setTimeMap(signComponent.getTimeMap());
/* 55 */     ((BuyLevelPackageResponse)this.response).level = request.level;
/* 56 */     ((BuyLevelPackageResponse)this.response).buyTimes = ((Integer)signComponent.getTimeMap().get(Integer.valueOf(request.level))).intValue();
/* 57 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\sign\BuyLevelPackageProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */