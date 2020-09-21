/*    */ package com.linlongyx.sanguo.webgame.proto.binary.souls;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SoulsData;
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
/*    */ public class SoulsRecastResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public SoulsData data = new SoulsData();
/*    */   
/*    */   public SoulsRecastResponse() {
/* 22 */     this.eventResponseId = 21211;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public SoulsRecastResponse(short retCode) {
/* 27 */     this.eventResponseId = 21211;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     this.data.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.data = new SoulsData();
/* 40 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SoulsRecastResponse clone() throws CloneNotSupportedException {
/* 45 */     SoulsRecastResponse clone = (SoulsRecastResponse)super.clone();
/* 46 */     clone.data = this.data.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.data.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"data\":" + this.data.toString() + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\souls\SoulsRecastResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */