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
/*    */ public class NormalInsInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int leftCount;
/*    */   public int maxCount;
/*    */   public int checkPoint;
/*    */   public int leftSweep;
/*    */   
/*    */   public NormalInsInfoResponse() {
/* 25 */     this.eventResponseId = 23001;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public NormalInsInfoResponse(short retCode) {
/* 30 */     this.eventResponseId = 23001;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.type);
/* 38 */     ProtocolUtil.writeInt(out, this.leftCount);
/* 39 */     ProtocolUtil.writeInt(out, this.maxCount);
/* 40 */     ProtocolUtil.writeInt(out, this.checkPoint);
/* 41 */     ProtocolUtil.writeInt(out, this.leftSweep);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 46 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 47 */     this.leftCount = ProtocolUtil.readUTFBinInt(in);
/* 48 */     this.maxCount = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.checkPoint = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.leftSweep = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public NormalInsInfoResponse clone() throws CloneNotSupportedException {
/* 55 */     NormalInsInfoResponse clone = (NormalInsInfoResponse)super.clone();
/* 56 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 61 */     this.type = 0;
/* 62 */     this.leftCount = 0;
/* 63 */     this.maxCount = 0;
/* 64 */     this.checkPoint = 0;
/* 65 */     this.leftSweep = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "{\"type\":" + this.type + ",\"leftCount\":" + this.leftCount + ",\"maxCount\":" + this.maxCount + ",\"checkPoint\":" + this.checkPoint + ",\"leftSweep\":" + this.leftSweep + "}";
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\singleIns\NormalInsInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */