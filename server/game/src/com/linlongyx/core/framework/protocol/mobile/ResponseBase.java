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
/*    */ public abstract class ResponseBase
/*    */   implements ISerialize, IEvent
/*    */ {
/*    */   private transient IEvent.EVENT_TYPE u3dEventType;
/* 24 */   protected int checkCode = -1073265851;
/*    */ 
/*    */ 
/*    */   
/*    */   protected int eventResponseId;
/*    */ 
/*    */ 
/*    */   
/*    */   protected int retCode;
/*    */ 
/*    */   
/*    */   protected int corCode;
/*    */ 
/*    */ 
/*    */   
/*    */   public ResponseBase() {
/* 40 */     this.u3dEventType = IEvent.EVENT_TYPE.EVENT_S2C;
/*    */   }
/*    */   
/*    */   public IEvent.EVENT_TYPE getType() {
/* 44 */     return this.u3dEventType;
/*    */   }
/*    */   
/*    */   public void setType(IEvent.EVENT_TYPE type) {
/* 48 */     this.u3dEventType = type;
/*    */   }
/*    */   
/*    */   public int getCheckCode() {
/* 52 */     return this.checkCode;
/*    */   }
/*    */   
/*    */   public void setCheckCode(int checkCode) {
/* 56 */     this.checkCode = checkCode;
/*    */   }
/*    */   
/*    */   public int getEventId() {
/* 60 */     return this.eventResponseId;
/*    */   }
/*    */   
/*    */   public void setEventId(int id) {
/* 64 */     this.eventResponseId = id;
/*    */   }
/*    */   
/*    */   public int getRetCode() {
/* 68 */     return this.retCode;
/*    */   }
/*    */   
/*    */   public void setRetCode(short retCode) {
/* 72 */     this.retCode = retCode;
/*    */   }
/*    */   
/*    */   public int getCorCode() {
/* 76 */     return this.corCode;
/*    */   }
/*    */   
/*    */   public void setCorCode(int corCode) {
/* 80 */     this.corCode = corCode;
/*    */   }
/*    */   
/*    */   public abstract void reset();
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\protocol\mobile\ResponseBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */