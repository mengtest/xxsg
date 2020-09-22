/*    */ package linlongyx.core.framework.protocol;
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
/* 13 */   private transient IEvent.EVENT_TYPE dragonballEventType = IEvent.EVENT_TYPE.EVENT_C2S;
/*    */   public short eventRequestId;
/*    */   
/*    */   public IEvent.EVENT_TYPE getType() {
/* 17 */     return this.dragonballEventType;
/*    */   }
/*    */   public byte codeType;
/*    */   public void setType(IEvent.EVENT_TYPE type) {
/* 21 */     this.dragonballEventType = type;
/*    */   }
/*    */   
/*    */   public short getEventId() {
/* 25 */     return this.eventRequestId;
/*    */   }
/*    */   
/*    */   public void setEventId(short id) {
/* 29 */     this.eventRequestId = id;
/*    */   }
/*    */   
/*    */   public byte getCodeType() {
/* 33 */     return this.codeType;
/*    */   }
/*    */   
/*    */   public void setCodeType(byte codeType) {
/* 37 */     this.codeType = codeType;
/*    */   }
/*    */   
/*    */   public abstract void reset();
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\protocol\RequestBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */