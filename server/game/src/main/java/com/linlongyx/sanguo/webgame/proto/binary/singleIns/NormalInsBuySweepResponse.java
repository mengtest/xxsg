/*    */ package com.linlongyx.sanguo.webgame.proto.binary.singleIns;
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
/*    */ public class NormalInsBuySweepResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int insId;
/*    */   public int leftCount;
/*    */   public int leftSweep;
/*    */   
/*    */   public NormalInsBuySweepResponse() {
/* 23 */     this.eventResponseId = 23007;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public NormalInsBuySweepResponse(short retCode) {
/* 28 */     this.eventResponseId = 23007;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.insId);
/* 36 */     ProtocolUtil.writeInt(out, this.leftCount);
/* 37 */     ProtocolUtil.writeInt(out, this.leftSweep);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.leftCount = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.leftSweep = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public NormalInsBuySweepResponse clone() throws CloneNotSupportedException {
/* 49 */     NormalInsBuySweepResponse clone = (NormalInsBuySweepResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.insId = 0;
/* 56 */     this.leftCount = 0;
/* 57 */     this.leftSweep = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"insId\":" + this.insId + ",\"leftCount\":" + this.leftCount + ",\"leftSweep\":" + this.leftSweep + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\singleIns\NormalInsBuySweepResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */