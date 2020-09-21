/*    */ package com.linlongyx.sanguo.webgame.app.eightGraphic;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaguaComponent
/*    */   extends AbstractComponent<BaguaEntity>
/*    */ {
/*    */   public BaguaComponent(long playerId) {
/* 19 */     super(BaguaEntity.class, playerId);
/* 20 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 25 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 30 */     setPlayerId(playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean reset(int time) {
/* 35 */     if (time == 0) {
/* 36 */       setInsIdMap(new HashMap<>());
/* 37 */       setAsistTime(0);
/* 38 */       setSweep((byte)0);
/*    */     } 
/* 40 */     return true;
/*    */   }
/*    */   
/*    */   public Map<Integer, Set<Integer>> getChapterMap() {
/* 44 */     return ((BaguaEntity)getEntity()).getChapterMap();
/*    */   }
/*    */   
/*    */   public void setChapterMap(Map<Integer, Set<Integer>> chapterMap) {
/* 48 */     ((BaguaEntity)getEntity()).setChapterMap(chapterMap);
/*    */   }
/*    */   
/*    */   public int getCurInsId() {
/* 52 */     return ((BaguaEntity)getEntity()).getCurInsId();
/*    */   }
/*    */   
/*    */   public void setCurInsId(int curInsId) {
/* 56 */     ((BaguaEntity)getEntity()).setCurInsId(curInsId);
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getInsIdMap() {
/* 60 */     return ((BaguaEntity)getEntity()).getInsIdMap();
/*    */   }
/*    */   
/*    */   public void setInsIdMap(Map<Integer, Integer> insIdMap) {
/* 64 */     ((BaguaEntity)getEntity()).setInsIdMap(insIdMap);
/*    */   }
/*    */   
/*    */   public int getAsistTime() {
/* 68 */     return ((BaguaEntity)getEntity()).getAsistTime();
/*    */   }
/*    */   
/*    */   public void setAsistTime(int asistTime) {
/* 72 */     ((BaguaEntity)getEntity()).setAsistTime(asistTime);
/*    */   }
/*    */   
/*    */   public byte getSweep() {
/* 76 */     return ((BaguaEntity)getEntity()).getSweep();
/*    */   }
/*    */   
/*    */   public void setSweep(byte sweep) {
/* 80 */     ((BaguaEntity)getEntity()).setSweep(sweep);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\eightGraphic\BaguaComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */