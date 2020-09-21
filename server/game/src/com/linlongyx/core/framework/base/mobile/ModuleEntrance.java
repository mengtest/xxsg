/*    */ package com.linlongyx.core.framework.base.mobile;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.Entrance;
/*    */ import com.linlongyx.core.framework.protocol.mobile.RequestBase;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModuleEntrance
/*    */ {
/* 16 */   private Entrance moduleEntrance = new Entrance();
/*    */ 
/*    */ 
/*    */   
/* 20 */   private Map<Integer, Entrance> entrances = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   private Map<Integer, Class<? extends RequestBase>> requests = new HashMap<>();
/*    */   
/*    */   public void init(int requestId, Class<? extends RequestBase> request) {
/* 28 */     Entrance entrance = new Entrance();
/* 29 */     this.entrances.put(Integer.valueOf(requestId), entrance);
/* 30 */     this.requests.put(Integer.valueOf(requestId), request);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entrance getEntrance(int requestId) {
/* 35 */     return this.entrances.get(Integer.valueOf(requestId));
/*    */   }
/*    */   
/*    */   public void setEntrance(int requestId, boolean flag) {
/* 39 */     Entrance entrance = getEntrance(requestId);
/* 40 */     if (null == entrance)
/*    */       return; 
/* 42 */     entrance.setOpenFlag(flag);
/*    */   }
/*    */   
/*    */   public Entrance getModuleEntrance() {
/* 46 */     return this.moduleEntrance;
/*    */   }
/*    */   
/*    */   public void setModuleEntrance(boolean flag) {
/* 50 */     this.moduleEntrance.setOpenFlag(flag);
/*    */   }
/*    */   
/*    */   public RequestBase getRequest(int msgId) {
/* 54 */     Class<? extends RequestBase> clazz = this.requests.get(Integer.valueOf(msgId));
/* 55 */     if (null != clazz) {
/*    */       try {
/* 57 */         return clazz.newInstance();
/* 58 */       } catch (InstantiationException|IllegalAccessException e) {
/* 59 */         e.printStackTrace();
/*    */       } 
/*    */     }
/* 62 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\base\mobile\ModuleEntrance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */