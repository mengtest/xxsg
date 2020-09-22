/*    */ package com.linlongyx.sanguo.webgame.proto.binary.partner;
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
/*    */ public class PartnerUpgradeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long pid;
/*    */   public int level;
/*    */   public long exp;
/*    */   
/*    */   public PartnerUpgradeResponse() {
/* 23 */     this.eventResponseId = 23304;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public PartnerUpgradeResponse(short retCode) {
/* 28 */     this.eventResponseId = 23304;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeLong(out, this.pid);
/* 36 */     ProtocolUtil.writeInt(out, this.level);
/* 37 */     ProtocolUtil.writeLong(out, this.exp);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 43 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.exp = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PartnerUpgradeResponse clone() throws CloneNotSupportedException {
/* 49 */     PartnerUpgradeResponse clone = (PartnerUpgradeResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.pid = 0L;
/* 56 */     this.level = 0;
/* 57 */     this.exp = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"pid\":" + this.pid + ",\"level\":" + this.level + ",\"exp\":" + this.exp + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\partner\PartnerUpgradeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */