/*    */ package com.linlongyx.core.framework.protocol.mobile;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.IEvent;
/*    */ import com.linlongyx.core.framework.protocol.ISerialize;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class RequestBase
/*    */   implements ISerialize, IEvent
/*    */ {
/*    */   private transient IEvent.EVENT_TYPE u3dEventType;
/* 24 */   protected int checkCode = -1073265851;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int eventRequestId;
/*    */ 
/*    */ 
/*    */   
/*    */   protected short retCode;
/*    */ 
/*    */ 
/*    */   
/*    */   protected int corCode;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEventId() {
/* 43 */     return this.eventRequestId;
/*    */   }
/*    */   
/*    */   public void setEventId(short id) {
/* 47 */     this.eventRequestId = id;
/*    */   }
/*    */   
/*    */   public int getCheckCode() {
/* 51 */     return this.checkCode;
/*    */   }
/*    */   
/*    */   public void setCheckCode(int checkCode) {
/* 55 */     this.checkCode = checkCode;
/*    */   }
/*    */   
/*    */   public int getRetCode() {
/* 59 */     return this.retCode;
/*    */   }
/*    */   
/*    */   public void setRetCode(short retCode) {
/* 63 */     this.retCode = retCode;
/*    */   }
/*    */   
/*    */   public abstract void reset();
/*    */   
/*    */   public int getEventRequestId() {
/* 69 */     return this.eventRequestId;
/*    */   }
/*    */   
/*    */   public void setEventRequestId(int eventRequestId) {
/* 73 */     this.eventRequestId = eventRequestId;
/*    */   }
/*    */   
/*    */   public int getCorCode() {
/* 77 */     return this.corCode;
/*    */   }
/*    */   
/*    */   public void setCorCode(int corCode) {
/* 81 */     this.corCode = corCode;
/*    */   }
/*    */   
/*    */   public IEvent.EVENT_TYPE getType() {
/* 85 */     return this.u3dEventType;
/*    */   }
/*    */   
/*    */   public void setType(IEvent.EVENT_TYPE type) {
/* 89 */     this.u3dEventType = type;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\protocol\mobile\RequestBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */