/*    */ package com.linlongyx.sanguo.webgame.proto.binary.warpet;
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
/*    */ public class WarPetUpgradeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int warPet;
/*    */   public int level;
/*    */   public int exp;
/*    */   
/*    */   public WarPetUpgradeResponse() {
/* 23 */     this.eventResponseId = 26404;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WarPetUpgradeResponse(short retCode) {
/* 28 */     this.eventResponseId = 26404;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.warPet);
/* 36 */     ProtocolUtil.writeInt(out, this.level);
/* 37 */     ProtocolUtil.writeInt(out, this.exp);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.warPet = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.exp = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WarPetUpgradeResponse clone() throws CloneNotSupportedException {
/* 49 */     WarPetUpgradeResponse clone = (WarPetUpgradeResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.warPet = 0;
/* 56 */     this.level = 0;
/* 57 */     this.exp = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"warPet\":" + this.warPet + ",\"level\":" + this.level + ",\"exp\":" + this.exp + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\warpet\WarPetUpgradeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */