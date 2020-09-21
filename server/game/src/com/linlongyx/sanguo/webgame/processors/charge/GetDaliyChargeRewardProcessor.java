/*    */ package com.linlongyx.sanguo.webgame.processors.charge;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.EverydayChargeBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.charge.GetDaliyChargeRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.charge.GetDaliyChargeRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetDaliyChargeRewardProcessor
/*    */   extends ProcessorBase<GetDaliyChargeRewardRequest, GetDaliyChargeRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new GetDaliyChargeRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetDaliyChargeRewardRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 6403))
/* 31 */       return 10061; 
/* 32 */     EverydayChargeBean everydayChargeBean = (EverydayChargeBean)JsonTableService.getJsonData(request.id, EverydayChargeBean.class);
/* 33 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 34 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 35 */     if (null == everydayChargeBean) {
/* 36 */       return 10006;
/*    */     }
/* 38 */     if (everydayChargeBean.getPlayerLevel() != playerComponent.getTodayLevel()) {
/* 39 */       return 11808;
/*    */     }
/* 41 */     if (everydayChargeBean.getRmb() > extendComponent.getTodayRecharge()) {
/* 42 */       return 15003;
/*    */     }
/* 44 */     if (extendComponent.getTodayFirstReward().containsKey(Integer.valueOf(request.id))) {
/* 45 */       return 15002;
/*    */     }
/* 47 */     extendComponent.getTodayFirstReward().put(Integer.valueOf(request.id), Integer.valueOf(TimeUtil.currentTime()));
/* 48 */     extendComponent.setTodayFirstReward(extendComponent.getTodayFirstReward());
/* 49 */     FinanceUtil.reward(FinanceUtil.transform(everydayChargeBean.getReward()), playerSession.getPlayer(), ResourceEvent.EveryDayCharge, true);
/* 50 */     ((GetDaliyChargeRewardResponse)this.response).id = request.id;
/* 51 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\charge\GetDaliyChargeRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */