/*    */ package com.linlongyx.sanguo.webgame.processors.rebate;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.rebate.RechargeRebateComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rebate.RechargeRebateEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RechargeTurntableBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ChargeRebateParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.ChargeRebateTurnRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.ChargeRebateTurnResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChargeRebateTurnProcessor
/*    */   extends ProcessorBase<ChargeRebateTurnRequest, ChargeRebateTurnResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 32 */     this.response = (ResponseBase)new ChargeRebateTurnResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ChargeRebateTurnRequest request) {
/* 37 */     ArrayList<Reward> rewardList = new ArrayList<>();
/* 38 */     int times = request.times;
/* 39 */     ArrayList<KeyValue> timeArray = new ArrayList<>();
/* 40 */     RechargeRebateComponent rechargeRebateComponent = (RechargeRebateComponent)playerSession.getPlayer().createIfNotExist(RechargeRebateComponent.class);
/* 41 */     ChargeRebateParameter chargeRebateParameter = (ChargeRebateParameter)ParameterConstant.getParameter(49);
/* 42 */     if (!chargeRebateParameter.isActOpen(request.actId)) {
/* 43 */       return 12702;
/*    */     }
/*    */     
/* 46 */     if (times > chargeRebateParameter.getOneTimes()) {
/* 47 */       return 18004;
/*    */     }
/* 49 */     if (times <= 0 || times * 1L * chargeRebateParameter.getOneCost() > 2147483647L) {
/* 50 */       return 10090;
/*    */     }
/* 52 */     RechargeRebateEntity rechargeRebateEntity = rechargeRebateComponent.getEntity(request.actId);
/* 53 */     if ((request.times * chargeRebateParameter.getOneCost()) > rechargeRebateEntity.getRefChare()) {
/* 54 */       return 18003;
/*    */     }
/* 56 */     RechargeTurntableBean rechargeTurntableBean = (RechargeTurntableBean)JsonTableService.getJsonData(request.actId, RechargeTurntableBean.class);
/* 57 */     for (RechargeTurntableBean.RareTimesBean rareTimesBean : rechargeTurntableBean.getRareTimes()) {
/* 58 */       KeyValue keyValue = new KeyValue();
/* 59 */       keyValue.key = rareTimesBean.getMin();
/* 60 */       keyValue.value = rareTimesBean.getMax();
/* 61 */       keyValue.valueStr = rareTimesBean.getId() + "";
/* 62 */       timeArray.add(keyValue);
/*    */     } 
/* 64 */     int addScore = request.times * chargeRebateParameter.getAddScore();
/* 65 */     ArrayList<KeyValue> rewards = new ArrayList<>();
/* 66 */     ChargeRebateUtil.turnTime(playerSession.getPlayer(), chargeRebateParameter, rechargeRebateEntity, timeArray, rewardList, rewards, rechargeRebateComponent, times, rechargeTurntableBean.getRaffle());
/*    */     
/* 68 */     Map<Long, Long> mapRewards = new HashMap<>();
/* 69 */     FinanceUtil.overlyingMap(mapRewards, rewardList);
/* 70 */     ArrayList<Reward> reward3 = FinanceUtil.overlyingReward(mapRewards);
/* 71 */     FinanceUtil.reward(reward3, playerSession.getPlayer(), ResourceEvent.ChargeRebateReward, false);
/* 72 */     rechargeRebateComponent.setReward(rewardList);
/*    */     
/* 74 */     rechargeRebateEntity.setRefChare(rechargeRebateEntity.getRefChare() - (times * chargeRebateParameter.getOneCost()));
/* 75 */     rechargeRebateComponent.updateRefChareDB(rechargeRebateEntity.getId());
/* 76 */     rechargeRebateEntity.setScore(rechargeRebateEntity.getScore() + addScore);
/* 77 */     rechargeRebateComponent.updateScoreDB(rechargeRebateEntity.getId());
/* 78 */     ((ChargeRebateTurnResponse)this.response).actId = request.actId;
/* 79 */     ((ChargeRebateTurnResponse)this.response).times = (int)(rechargeRebateEntity.getRefChare() / chargeRebateParameter.getOneCost());
/* 80 */     ((ChargeRebateTurnResponse)this.response).sorce = rechargeRebateEntity.getScore();
/* 81 */     ((ChargeRebateTurnResponse)this.response).reward = new ArrayList<>(rewards);
/* 82 */     LogUtils.errorLog(new Object[] { "ChargeRebateTurnProcessor", Integer.valueOf(request.actId), Integer.valueOf(request.times), Integer.valueOf(rechargeRebateEntity.getScore()) });
/* 83 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void process(IPlayerSession playerSession, ChargeRebateTurnRequest request) throws Exception {
/* 88 */     short retCode = handleRequest(playerSession, request);
/* 89 */     ((ChargeRebateTurnResponse)this.response).setRetCode(retCode);
/* 90 */     playerSession.sendMessage(this.response);
/* 91 */     if (0 != retCode)
/* 92 */       return;  LookUpService.pushServiceNotice();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rebate\ChargeRebateTurnProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */