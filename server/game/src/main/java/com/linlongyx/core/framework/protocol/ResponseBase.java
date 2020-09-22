/*    */ package com.linlongyx.core.framework.protocol;
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
/* 15 */   private transient IEvent.EVENT_TYPE dragonballEventType = IEvent.EVENT_TYPE.EVENT_S2C;
/*    */   public short eventResponseId;
/*    */   
/*    */   public IEvent.EVENT_TYPE getType() {
/* 19 */     return this.dragonballEventType;
/*    */   }
/*    */   public short retCode; public byte codeType;
/*    */   public void setType(IEvent.EVENT_TYPE type) {
/* 23 */     this.dragonballEventType = type;
/*    */   }
/*    */   
/*    */   public short getEventId() {
/* 27 */     return this.eventResponseId;
/*    */   }
/*    */   
/*    */   public void setEventId(short id) {
/* 31 */     this.eventResponseId = id;
/*    */   }
/*    */   
/*    */   public short getRetCode() {
/* 35 */     return this.retCode;
/*    */   }
/*    */   
/*    */   public void setRetCode(short retCode) {
/* 39 */     this.retCode = retCode;
/*    */   }
/*    */   
/*    */   public byte getCodeType() {
/* 43 */     return this.codeType;
/*    */   }
/*    */   
/*    */   public void setCodeType(byte codeType) {
/* 47 */     this.codeType = codeType;
/*    */   }
/*    */   
/*    */   public abstract void reset();
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\protocol\ResponseBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */