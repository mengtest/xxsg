/*    */ package com.linlongyx.sanguo.webgame.proto.binary.warlineup;
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
/*    */ public class WarLineuprUpgradeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int warlineup;
/*    */   public int level;
/*    */   public int exp;
/*    */   
/*    */   public WarLineuprUpgradeResponse() {
/* 23 */     this.eventResponseId = 26505;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WarLineuprUpgradeResponse(short retCode) {
/* 28 */     this.eventResponseId = 26505;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.warlineup);
/* 36 */     ProtocolUtil.writeInt(out, this.level);
/* 37 */     ProtocolUtil.writeInt(out, this.exp);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.warlineup = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.exp = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WarLineuprUpgradeResponse clone() throws CloneNotSupportedException {
/* 49 */     WarLineuprUpgradeResponse clone = (WarLineuprUpgradeResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.warlineup = 0;
/* 56 */     this.level = 0;
/* 57 */     this.exp = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"warlineup\":" + this.warlineup + ",\"level\":" + this.level + ",\"exp\":" + this.exp + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\warlineup\WarLineuprUpgradeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */