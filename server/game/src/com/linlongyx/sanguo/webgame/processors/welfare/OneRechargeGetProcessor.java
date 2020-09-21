/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.OneyuanBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.OneyuanRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FundParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.charge.ChargeUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.OneRechargeGetRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.OneRechargeGetResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class OneRechargeGetProcessor
/*    */   extends ProcessorBase<OneRechargeGetRequest, OneRechargeGetResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 29 */     this.response = (ResponseBase)new OneRechargeGetResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, OneRechargeGetRequest request) {
/* 34 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 6404)) {
/* 35 */       return 10061;
/*    */     }
/* 37 */     IPlayer iPlayer = playerSession.getPlayer();
/* 38 */     WelfareComponent welfareComponent = (WelfareComponent)iPlayer.createIfNotExist(WelfareComponent.class);
/* 39 */     if (welfareComponent.getOneState() == 2) {
/* 40 */       return 15002;
/*    */     }
/* 42 */     if (welfareComponent.getOneState() == 0) {
/* 43 */       return 15003;
/*    */     }
/* 45 */     int now = TimeUtil.currentTime();
/* 46 */     FundParameter fundParameter = (FundParameter)ParameterConstant.getParameter(25);
/* 47 */     int id = ChargeUtil.getOneyuan(now, fundParameter);
/*    */     
/* 49 */     OneyuanBean oneyuanBean = (OneyuanBean)JsonTableService.getJsonData(id, OneyuanBean.class);
/* 50 */     if (null == oneyuanBean) {
/* 51 */       return 10006;
/*    */     }
/* 53 */     int rewardId = ((Integer)oneyuanBean.getRewardId().get(welfareComponent.getTimes())).intValue();
/* 54 */     OneyuanRewardBean oneyuanRewardBean = (OneyuanRewardBean)JsonTableService.getJsonData(rewardId, OneyuanRewardBean.class);
/* 55 */     if (null == oneyuanRewardBean) {
/* 56 */       return 10006;
/*    */     }
/* 58 */     welfareComponent.setOneState(2);
/* 59 */     welfareComponent.setId(id);
/*    */     
/* 61 */     ArrayList<Reward> rewards = FinanceUtil.rewardBeanGet(oneyuanRewardBean.getReward(), playerSession.getPlayer(), ResourceEvent.OneyuanReward, true);
/* 62 */     if (welfareComponent.getTimes() + 1 < oneyuanBean.getRewardId().size()) {
/* 63 */       welfareComponent.setTimes(welfareComponent.getTimes() + 1);
/* 64 */       welfareComponent.setOneState(0);
/*    */     } 
/* 66 */     LogUtil.errorLog(new Object[] { "OneRechargeGetProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(welfareComponent.getId()) });
/* 67 */     ((OneRechargeGetResponse)this.response).id = welfareComponent.getId();
/* 68 */     ((OneRechargeGetResponse)this.response).list = new ArrayList<>(rewards);
/* 69 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\OneRechargeGetProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */