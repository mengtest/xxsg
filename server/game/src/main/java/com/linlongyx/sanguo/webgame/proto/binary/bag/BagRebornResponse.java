/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bag;
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
/*    */ public class BagRebornResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public long pid;
/*    */   
/*    */   public BagRebornResponse() {
/* 22 */     this.eventResponseId = 20707;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public BagRebornResponse(short retCode) {
/* 27 */     this.eventResponseId = 20707;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.type);
/* 35 */     ProtocolUtil.writeLong(out, this.pid);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BagRebornResponse clone() throws CloneNotSupportedException {
/* 46 */     BagRebornResponse clone = (BagRebornResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.type = 0;
/* 53 */     this.pid = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"type\":" + this.type + ",\"pid\":" + this.pid + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bag\BagRebornResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */