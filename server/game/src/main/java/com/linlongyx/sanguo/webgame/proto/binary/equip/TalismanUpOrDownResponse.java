/*    */ package com.linlongyx.sanguo.webgame.proto.binary.equip;
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
/*    */ public class TalismanUpOrDownResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public long pid;
/*    */   public long equipId;
/*    */   
/*    */   public TalismanUpOrDownResponse() {
/* 23 */     this.eventResponseId = 20818;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TalismanUpOrDownResponse(short retCode) {
/* 28 */     this.eventResponseId = 20818;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.type);
/* 36 */     ProtocolUtil.writeLong(out, this.pid);
/* 37 */     ProtocolUtil.writeLong(out, this.equipId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 44 */     this.equipId = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TalismanUpOrDownResponse clone() throws CloneNotSupportedException {
/* 49 */     TalismanUpOrDownResponse clone = (TalismanUpOrDownResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.type = 0;
/* 56 */     this.pid = 0L;
/* 57 */     this.equipId = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"type\":" + this.type + ",\"pid\":" + this.pid + ",\"equipId\":" + this.equipId + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\equip\TalismanUpOrDownResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */