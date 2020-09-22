/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.VipGiftBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.WelfareVipGiftRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.WelfareVipGiftResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WelfareVipGiftProcessor
/*    */   extends ProcessorBase<WelfareVipGiftRequest, WelfareVipGiftResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new WelfareVipGiftResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WelfareVipGiftRequest request) {
/* 31 */     int packageType = request.packageType;
/* 32 */     VipGiftBean vipGiftBean = (VipGiftBean)JsonTableService.getJsonData(packageType, VipGiftBean.class);
/* 33 */     if (null == vipGiftBean) {
/* 34 */       return 11902;
/*    */     }
/* 36 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 37 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 38 */     Map<Integer, Integer> vipGiftGoods = welfareComponent.getVipGiftGoods();
/* 39 */     int num = ((Integer)vipGiftGoods.getOrDefault(Integer.valueOf(packageType), Integer.valueOf(0))).intValue();
/* 40 */     if (vipGiftBean.getVipLevel() > playerComponent.getVip()) {
/* 41 */       return 10088;
/*    */     }
/* 43 */     if (vipGiftBean.getLimitType() != 0 && num >= vipGiftBean.getSelltimes()) {
/* 44 */       return 11903;
/*    */     }
/* 46 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, vipGiftBean.getPrice())) {
/* 47 */       return 10052;
/*    */     }
/* 49 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, vipGiftBean.getPrice(), ResourceEvent.WelfareVipWeek, true);
/* 50 */     num++;
/* 51 */     vipGiftGoods.put(Integer.valueOf(packageType), Integer.valueOf(num));
/* 52 */     welfareComponent.setVipGiftGoods(vipGiftGoods);
/* 53 */     FinanceUtil.reward(FinanceUtil.transform(vipGiftBean.getReward()), playerSession.getPlayer(), ResourceEvent.WelfareVipWeek, true);
/*    */     
/* 55 */     ((WelfareVipGiftResponse)this.response).packageType = packageType;
/* 56 */     ((WelfareVipGiftResponse)this.response).num = num;
/*    */     
/* 58 */     LogUtils.errorLog(new Object[] { "WelfareVipGift", Long.valueOf(playerSession.getPlayer().getPlayerId()), ((WelfareVipGiftResponse)this.response).toString() });
/*    */     
/* 60 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\WelfareVipGiftProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */