/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bosshome;
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
/*    */ public class BossBuyTimeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int buyTime;
/*    */   
/*    */   public BossBuyTimeResponse() {
/* 22 */     this.eventResponseId = 20310;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public BossBuyTimeResponse(short retCode) {
/* 27 */     this.eventResponseId = 20310;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.type);
/* 35 */     ProtocolUtil.writeInt(out, this.buyTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.buyTime = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BossBuyTimeResponse clone() throws CloneNotSupportedException {
/* 46 */     BossBuyTimeResponse clone = (BossBuyTimeResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.type = 0;
/* 53 */     this.buyTime = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"type\":" + this.type + ",\"buyTime\":" + this.buyTime + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bosshome\BossBuyTimeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */