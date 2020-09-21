/*    */ package com.linlongyx.sanguo.webgame.proto.binary.fashion;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
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
/*    */ public class FashionStrengthenResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public IntIntValue fashion = new IntIntValue();
/*    */   
/*    */   public FashionStrengthenResponse() {
/* 22 */     this.eventResponseId = 23905;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 28 */     this.fashion.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.fashion = new IntIntValue();
/* 34 */     this.fashion.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FashionStrengthenResponse clone() throws CloneNotSupportedException {
/* 39 */     FashionStrengthenResponse clone = (FashionStrengthenResponse)super.clone();
/* 40 */     clone.fashion = this.fashion.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 46 */     this.fashion.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "{\"fashion\":" + this.fashion.toString() + "}";
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\fashion\FashionStrengthenResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */