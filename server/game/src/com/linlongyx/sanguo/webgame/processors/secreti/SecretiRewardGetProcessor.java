/*    */ package com.linlongyx.sanguo.webgame.processors.secreti;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.secreti.SecretiComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SecretiRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.secreti.SecretiRewardGetRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.secreti.SecretiRewardGetResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SecretiRewardGetProcessor
/*    */   extends ProcessorBase<SecretiRewardGetRequest, SecretiRewardGetResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new SecretiRewardGetResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SecretiRewardGetRequest request) {
/* 35 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 44))
/* 36 */       return 10061; 
/* 37 */     SecretiComponent secretiComponent = (SecretiComponent)playerSession.getPlayer().createIfNotExist(SecretiComponent.class);
/* 38 */     ArrayList<RewardBean> rewardList = new ArrayList<>();
/* 39 */     if (request.rewardId == -1) {
/* 40 */       for (Map.Entry<Integer, Integer> kv : (Iterable<Map.Entry<Integer, Integer>>)secretiComponent.getRewards().entrySet()) {
/* 41 */         if (((Integer)kv.getValue()).intValue() == 1) {
/* 42 */           SecretiRewardBean secretiRewardBean = (SecretiRewardBean)JsonTableService.getJsonData(((Integer)kv.getKey()).intValue(), SecretiRewardBean.class);
/* 43 */           if (secretiRewardBean != null) {
/* 44 */             rewardList.addAll(secretiRewardBean.getReward());
/* 45 */             secretiComponent.getRewards().put(kv.getKey(), Integer.valueOf(2)); continue;
/*    */           } 
/* 47 */           LogUtil.errorLog(new Object[] { "逐鹿秘境奖励配置错误" });
/*    */         } 
/*    */       } 
/*    */     } else {
/*    */       
/* 52 */       if (!secretiComponent.getRewards().containsKey(Integer.valueOf(request.rewardId))) {
/* 53 */         return 10095;
/*    */       }
/* 55 */       if (((Integer)secretiComponent.getRewards().get(Integer.valueOf(request.rewardId))).intValue() == 2) {
/* 56 */         return 10091;
/*    */       }
/* 58 */       SecretiRewardBean secretiRewardBean = (SecretiRewardBean)JsonTableService.getJsonData(request.rewardId, SecretiRewardBean.class);
/* 59 */       if (secretiRewardBean == null) {
/* 60 */         return 10095;
/*    */       }
/* 62 */       rewardList.addAll(secretiRewardBean.getReward());
/* 63 */       secretiComponent.getRewards().put(Integer.valueOf(request.rewardId), Integer.valueOf(2));
/*    */     } 
/* 65 */     FinanceUtil.reward(FinanceUtil.transform(rewardList), playerSession.getPlayer(), ResourceEvent.SecretiReward, true);
/* 66 */     secretiComponent.setRewards(secretiComponent.getRewards());
/*    */     
/* 68 */     for (Map.Entry<Integer, Integer> kv : (Iterable<Map.Entry<Integer, Integer>>)secretiComponent.getRewards().entrySet()) {
/* 69 */       IntIntValue intIntValue = new IntIntValue();
/* 70 */       intIntValue.key = ((Integer)kv.getKey()).intValue();
/* 71 */       intIntValue.value = ((Integer)kv.getValue()).intValue();
/* 72 */       ((SecretiRewardGetResponse)this.response).rewardList.add(intIntValue);
/*    */     } 
/* 74 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\secreti\SecretiRewardGetProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */