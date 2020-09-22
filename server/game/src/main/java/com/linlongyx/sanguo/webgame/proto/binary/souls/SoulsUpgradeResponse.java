/*    */ package com.linlongyx.sanguo.webgame.proto.binary.souls;
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
/*    */ public class SoulsUpgradeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int soulsId;
/*    */   public int currLevel;
/*    */   public int exp;
/*    */   
/*    */   public SoulsUpgradeResponse() {
/* 23 */     this.eventResponseId = 21210;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public SoulsUpgradeResponse(short retCode) {
/* 28 */     this.eventResponseId = 21210;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.soulsId);
/* 36 */     ProtocolUtil.writeInt(out, this.currLevel);
/* 37 */     ProtocolUtil.writeInt(out, this.exp);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.soulsId = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.currLevel = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.exp = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SoulsUpgradeResponse clone() throws CloneNotSupportedException {
/* 49 */     SoulsUpgradeResponse clone = (SoulsUpgradeResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.soulsId = 0;
/* 56 */     this.currLevel = 0;
/* 57 */     this.exp = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"soulsId\":" + this.soulsId + ",\"currLevel\":" + this.currLevel + ",\"exp\":" + this.exp + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\souls\SoulsUpgradeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */