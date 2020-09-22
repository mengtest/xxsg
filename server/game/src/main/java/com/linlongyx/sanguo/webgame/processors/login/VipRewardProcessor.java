/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.VipAccessBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.VipRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.VipRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VipRewardProcessor
/*    */   extends ProcessorBase<VipRewardRequest, VipRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new VipRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, VipRewardRequest request) {
/* 32 */     int id = request.id;
/* 33 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 34 */     List<Integer> vipReward = playerComponent.getVipReward();
/* 35 */     if (vipReward.contains(Integer.valueOf(id))) {
/* 36 */       return 10093;
/*    */     }
/* 38 */     VipAccessBean vipAccessBean = (VipAccessBean)JsonTableService.getJsonData(id, VipAccessBean.class);
/* 39 */     if (null == vipAccessBean) {
/* 40 */       return 10092;
/*    */     }
/* 42 */     if (playerComponent.getVip() < vipAccessBean.getVipLevel()) {
/* 43 */       return 10088;
/*    */     }
/* 45 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, vipAccessBean.getPrice())) {
/* 46 */       return 10052;
/*    */     }
/* 48 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, vipAccessBean.getPrice(), ResourceEvent.VipReward, true);
/* 49 */     vipReward.add(Integer.valueOf(id));
/* 50 */     playerComponent.setVipReward(vipReward);
/*    */     
/* 52 */     ArrayList<Reward> rewards = FinanceUtil.transform(vipAccessBean.getVipReward());
/* 53 */     FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.VipReward, true);
/* 54 */     ((VipRewardResponse)this.response).id = id;
/* 55 */     LogUtils.errorLog(new Object[] { "VipReward", Long.valueOf(playerSession.getPlayer().getPlayerId()), ((VipRewardResponse)this.response).toString() });
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\VipRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */