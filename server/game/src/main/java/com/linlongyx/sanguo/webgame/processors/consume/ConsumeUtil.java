/*    */ package com.linlongyx.sanguo.webgame.processors.consume;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.app.consume.ConsumeRebateComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.consume.ConsumeRebateEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ChargeRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ConsumeRebateParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConsumeUtil
/*    */ {
/*    */   public static void updateConsumeRebate(long playerId, long value) {
/* 25 */     ConsumeRebateComponent consumeRebateComponent = (ConsumeRebateComponent)LookUpService.getComponent(playerId, ConsumeRebateComponent.class);
/* 26 */     ConsumeRebateParameter consumeRebateParameter = (ConsumeRebateParameter)ParameterConstant.getParameter(52);
/* 27 */     List<Integer> list = consumeRebateParameter.getActId(true);
/* 28 */     for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int actId = ((Integer)iterator.next()).intValue();
/* 29 */       ConsumeRebateEntity consumeRebateEntity = consumeRebateComponent.getEntity(actId);
/* 30 */       ChargeRewardBean chargeRewardBean = (ChargeRewardBean)JsonTableService.getJsonData(actId, ChargeRewardBean.class);
/* 31 */       if (null != consumeRebateEntity && null != chargeRewardBean) {
/* 32 */         if (chargeRewardBean.getActType() == 1) {
/* 33 */           consumeRebateEntity.setTakeConsume(consumeRebateEntity.getTakeConsume() + value);
/* 34 */           consumeRebateComponent.updateTakeConsumeDB(actId); continue;
/* 35 */         }  if (chargeRewardBean.getActType() == 2) {
/* 36 */           consumeRebateEntity.setPostureConsume(consumeRebateEntity.getPostureConsume() + value);
/* 37 */           consumeRebateComponent.updatePostureConsumeDB(actId); continue;
/* 38 */         }  if (chargeRewardBean.getActType() == 3) {
/* 39 */           consumeRebateEntity.setZhenFaConsume(consumeRebateEntity.getZhenFaConsume() + value);
/* 40 */           consumeRebateComponent.updatezhenFaConsumeDB(actId);
/*    */         } 
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\consume\ConsumeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */