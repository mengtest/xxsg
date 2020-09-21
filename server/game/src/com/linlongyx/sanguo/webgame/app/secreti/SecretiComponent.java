/*    */ package com.linlongyx.sanguo.webgame.app.secreti;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SecretiBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.SecretiParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SecretiComponent
/*    */   extends AbstractComponent<SecretiEntity>
/*    */ {
/*    */   public SecretiComponent(long playerId) {
/* 22 */     super(SecretiEntity.class, playerId);
/* 23 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 28 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 33 */     setPlayerId(playerId);
/* 34 */     Set<Integer> set = JsonTableService.getJsonTableKey(SecretiBean.class);
/* 35 */     int first = ((Integer)set.iterator().next()).intValue();
/* 36 */     getInsMap().put(Integer.valueOf(first), new TreeSet<>());
/*    */     
/* 38 */     SecretiParameter secretiParameter = (SecretiParameter)ParameterConstant.getParameter(44);
/* 39 */     setRewardTimes(secretiParameter.getRewardTimes());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean reset(int time) {
/* 44 */     if (time == 0) {
/*    */       
/* 46 */       SecretiParameter secretiParameter = (SecretiParameter)ParameterConstant.getParameter(44);
/* 47 */       setRewardTimes(secretiParameter.getRewardTimes());
/*    */     } 
/* 49 */     return true;
/*    */   }
/*    */   
/*    */   public Map<Integer, Set<Integer>> getInsMap() {
/* 53 */     return ((SecretiEntity)getEntity()).getInsMap();
/*    */   }
/*    */   
/*    */   public void setInsMap(Map<Integer, Set<Integer>> insMap) {
/* 57 */     ((SecretiEntity)getEntity()).setInsMap(insMap);
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getRewards() {
/* 61 */     return ((SecretiEntity)getEntity()).getRewards();
/*    */   }
/*    */   
/*    */   public void setRewards(Map<Integer, Integer> rewards) {
/* 65 */     ((SecretiEntity)getEntity()).setRewards(rewards);
/*    */   }
/*    */   
/*    */   public int getRewardTimes() {
/* 69 */     return ((SecretiEntity)getEntity()).getRewardTimes();
/*    */   }
/*    */   
/*    */   public void setRewardTimes(int rewardTimes) {
/* 73 */     ((SecretiEntity)getEntity()).setRewardTimes(rewardTimes);
/*    */   }
/*    */   
/*    */   public int getTotal() {
/* 77 */     return ((SecretiEntity)getEntity()).getTotal();
/*    */   }
/*    */   
/*    */   public void setTotal(int total) {
/* 81 */     ((SecretiEntity)getEntity()).setTotal(total);
/*    */   }
/*    */   
/*    */   public int getMaxLayer() {
/* 85 */     return ((Integer)getInsMap().keySet().stream().max(Integer::compareTo).orElse(Integer.valueOf(1))).intValue();
/*    */   }
/*    */   
/*    */   public int getTotalTimes() {
/* 89 */     return ((SecretiEntity)getEntity()).getTotalTimes();
/*    */   }
/*    */   
/*    */   public void setTotalTimes(int totalTimes) {
/* 93 */     ((SecretiEntity)getEntity()).setTotalTimes(totalTimes);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\secreti\SecretiComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */