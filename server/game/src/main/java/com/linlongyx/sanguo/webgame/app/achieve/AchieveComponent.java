/*    */ package com.linlongyx.sanguo.webgame.app.achieve;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FameBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AchieveComponent
/*    */   extends AbstractComponent<AchieveEntity>
/*    */ {
/*    */   public AchieveComponent(long playerId) {
/* 17 */     super(AchieveEntity.class, playerId);
/* 18 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 23 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 28 */     setPlayerId(playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean reset(int time) {
/* 33 */     if (time == 0) {
/* 34 */       Map<Integer, Object> map = JsonTableService.getJsonTable(FameBean.class);
/* 35 */       Map<Integer, Long> values = getValue();
/* 36 */       Set<Integer> fameDone = getFameDone();
/* 37 */       Set<Integer> fameReward = getFameReward();
/* 38 */       boolean isDirty = false;
/* 39 */       for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 40 */         FameBean fameBean = (FameBean)entry.getValue();
/* 41 */         if (0 == fameBean.getFameType()) {
/* 42 */           fameDone.remove(Integer.valueOf(fameBean.getFameId()));
/* 43 */           fameReward.remove(Integer.valueOf(fameBean.getFameId()));
/* 44 */           values.put(Integer.valueOf(fameBean.getFameId()), Long.valueOf(0L));
/* 45 */           isDirty = true;
/*    */         } 
/*    */       } 
/* 48 */       if (isDirty) {
/* 49 */         setValue(values);
/* 50 */         setFameDone(fameDone);
/* 51 */         setFameReward(fameReward);
/*    */       } 
/*    */     } 
/* 54 */     return true;
/*    */   }
/*    */   
/*    */   public Map<Integer, Long> getValue() {
/* 58 */     return ((AchieveEntity)getEntity()).getValue();
/*    */   }
/*    */   
/*    */   public void setValue(Map<Integer, Long> value) {
/* 62 */     ((AchieveEntity)getEntity()).setValue(value);
/*    */   }
/*    */   
/*    */   public Set<Integer> getFameReward() {
/* 66 */     return ((AchieveEntity)getEntity()).getFameReward();
/*    */   }
/*    */   
/*    */   public void setFameReward(Set<Integer> fameReward) {
/* 70 */     ((AchieveEntity)getEntity()).setFameReward(fameReward);
/*    */   }
/*    */   
/*    */   public Set<Integer> getProcessReward() {
/* 74 */     return ((AchieveEntity)getEntity()).getProcessReward();
/*    */   }
/*    */   
/*    */   public void setProcessReward(Set<Integer> processReward) {
/* 78 */     ((AchieveEntity)getEntity()).setProcessReward(processReward);
/*    */   }
/*    */   
/*    */   public int getPoint() {
/* 82 */     return ((AchieveEntity)getEntity()).getPoint();
/*    */   }
/*    */   
/*    */   public void setPoint(int point) {
/* 86 */     ((AchieveEntity)getEntity()).setPoint(point);
/*    */   }
/*    */   
/*    */   public Set<Integer> getFameDone() {
/* 90 */     return ((AchieveEntity)getEntity()).getFameDone();
/*    */   }
/*    */   
/*    */   public void setFameDone(Set<Integer> fameDone) {
/* 94 */     ((AchieveEntity)getEntity()).setFameDone(fameDone);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\achieve\AchieveComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */