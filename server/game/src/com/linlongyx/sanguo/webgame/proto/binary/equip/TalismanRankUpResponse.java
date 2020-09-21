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
/*    */ public class TalismanRankUpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long pid;
/*    */   public int talismanRank;
/*    */   public long equipId;
/*    */   
/*    */   public TalismanRankUpResponse() {
/* 23 */     this.eventResponseId = 20817;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TalismanRankUpResponse(short retCode) {
/* 28 */     this.eventResponseId = 20817;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeLong(out, this.pid);
/* 36 */     ProtocolUtil.writeInt(out, this.talismanRank);
/* 37 */     ProtocolUtil.writeLong(out, this.equipId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 43 */     this.talismanRank = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.equipId = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TalismanRankUpResponse clone() throws CloneNotSupportedException {
/* 49 */     TalismanRankUpResponse clone = (TalismanRankUpResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.pid = 0L;
/* 56 */     this.talismanRank = 0;
/* 57 */     this.equipId = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"pid\":" + this.pid + ",\"talismanRank\":" + this.talismanRank + ",\"equipId\":" + this.equipId + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\equip\TalismanRankUpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */