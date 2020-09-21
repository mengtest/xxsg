/*    */ package com.linlongyx.sanguo.webgame.proto.binary.wander;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class WanderBuyResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int id;
/*    */   public int num;
/*    */   
/*    */   public WanderBuyResponse() {
/* 22 */     this.eventResponseId = 25302;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WanderBuyResponse(short retCode) {
/* 27 */     this.eventResponseId = 25302;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.id);
/* 35 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WanderBuyResponse clone() throws CloneNotSupportedException {
/* 46 */     WanderBuyResponse clone = (WanderBuyResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.id = 0;
/* 53 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"id\":" + this.id + ",\"num\":" + this.num + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\wander\WanderBuyResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */