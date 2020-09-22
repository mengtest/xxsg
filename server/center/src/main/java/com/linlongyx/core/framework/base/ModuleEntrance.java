/*    */ package com.linlongyx.core.framework.base;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModuleEntrance
/*    */ {
/* 14 */   private Entrance moduleEntrance = new Entrance();
/*    */ 
/*    */ 
/*    */   
/* 18 */   private Map<Integer, Entrance> entrances = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   private Map<Integer, Class> requests = (Map)new HashMap<>();
/*    */   
/*    */   public void init(int requestId, Class request) {
/* 26 */     Entrance entrance = new Entrance();
/* 27 */     this.entrances.put(Integer.valueOf(requestId), entrance);
/* 28 */     this.requests.put(Integer.valueOf(requestId), request);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entrance getEntrance(int requestId) {
/* 33 */     return this.entrances.get(Integer.valueOf(requestId));
/*    */   }
/*    */   
/*    */   public void setEntrance(int requestId, boolean flag) {
/* 37 */     Entrance entrance = getEntrance(requestId);
/* 38 */     if (null == entrance)
/*    */       return; 
/* 40 */     entrance.setOpenFlag(flag);
/*    */   }
/*    */   
/*    */   public Entrance getModuleEntrance() {
/* 44 */     return this.moduleEntrance;
/*    */   }
/*    */   
/*    */   public void setModuleEntrance(boolean flag) {
/* 48 */     this.moduleEntrance.setOpenFlag(flag);
/*    */   }
/*    */   
/*    */   public RequestBase getRequest(int msgId) {
/* 52 */     Class<RequestBase> clazz = this.requests.get(Integer.valueOf(msgId));
/* 53 */     if (null != clazz) {
/*    */       try {
/* 55 */         return clazz.newInstance();
/* 56 */       } catch (InstantiationException|IllegalAccessException e) {
/* 57 */         e.printStackTrace();
/*    */       } 
/*    */     }
/* 60 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\base\ModuleEntrance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */