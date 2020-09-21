/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.OneyuanBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FundParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.charge.ChargeUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.OneRechargeInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.OneRechargeInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OneRechargeInfoProcessor
/*    */   extends ProcessorBase<OneRechargeInfoRequest, OneRechargeInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new OneRechargeInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, OneRechargeInfoRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 6404))
/* 32 */       return 10061; 
/* 33 */     IPlayer iPlayer = playerSession.getPlayer();
/* 34 */     WelfareComponent welfareComponent = (WelfareComponent)iPlayer.createIfNotExist(WelfareComponent.class);
/* 35 */     FundParameter fundParameter = (FundParameter)ParameterConstant.getParameter(25);
/*    */     
/* 37 */     int now = TimeUtil.currentTime();
/*    */     
/* 39 */     int id = ChargeUtil.getOneyuan(now, fundParameter);
/* 40 */     OneyuanBean oneyuanBean = (OneyuanBean)JsonTableService.getJsonData(id, OneyuanBean.class);
/* 41 */     if (null == oneyuanBean) {
/* 42 */       return 0;
/*    */     }
/*    */     
/* 45 */     ((OneRechargeInfoResponse)this.response).id = id;
/* 46 */     ((OneRechargeInfoResponse)this.response).chargeId = ((Integer)oneyuanBean.getChargeId().get(welfareComponent.getTimes())).intValue();
/* 47 */     ((OneRechargeInfoResponse)this.response).rewardId = ((Integer)oneyuanBean.getRewardId().get(welfareComponent.getTimes())).intValue();
/* 48 */     ((OneRechargeInfoResponse)this.response).state = welfareComponent.getOneState();
/* 49 */     ((OneRechargeInfoResponse)this.response).time = TimeUtil.leftNextZeroTime();
/* 50 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\OneRechargeInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */