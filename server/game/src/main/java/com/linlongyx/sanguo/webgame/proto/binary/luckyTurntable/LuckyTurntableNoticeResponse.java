/*    */ package com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LuckyTurntableRecord;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class LuckyTurntableNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public LuckyTurntableRecord record = new LuckyTurntableRecord();
/*    */   
/*    */   public LuckyTurntableNoticeResponse() {
/* 22 */     this.eventResponseId = 23503;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 28 */     this.record.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.record = new LuckyTurntableRecord();
/* 34 */     this.record.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LuckyTurntableNoticeResponse clone() throws CloneNotSupportedException {
/* 39 */     LuckyTurntableNoticeResponse clone = (LuckyTurntableNoticeResponse)super.clone();
/* 40 */     clone.record = this.record.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 46 */     this.record.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "{\"record\":" + this.record.toString() + "}";
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\luckyTurntable\LuckyTurntableNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */