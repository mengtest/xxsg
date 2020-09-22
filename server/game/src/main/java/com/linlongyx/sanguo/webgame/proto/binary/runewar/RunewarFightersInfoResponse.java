/*    */ package com.linlongyx.sanguo.webgame.proto.binary.runewar;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarFightersData;
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
/*    */ public class RunewarFightersInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public RunewarFightersData data = new RunewarFightersData();
/*    */   
/*    */   public RunewarFightersInfoResponse() {
/* 22 */     this.eventResponseId = 24502;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 28 */     this.data.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.data = new RunewarFightersData();
/* 34 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RunewarFightersInfoResponse clone() throws CloneNotSupportedException {
/* 39 */     RunewarFightersInfoResponse clone = (RunewarFightersInfoResponse)super.clone();
/* 40 */     clone.data = this.data.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 46 */     this.data.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "{\"data\":" + this.data.toString() + "}";
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\runewar\RunewarFightersInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */