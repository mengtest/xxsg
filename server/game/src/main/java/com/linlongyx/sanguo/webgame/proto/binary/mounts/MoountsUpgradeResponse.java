/*    */ package com.linlongyx.sanguo.webgame.proto.binary.mounts;
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
/*    */ public class MoountsUpgradeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int mounts;
/*    */   public int level;
/*    */   public int exp;
/*    */   
/*    */   public MoountsUpgradeResponse() {
/* 23 */     this.eventResponseId = 29004;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public MoountsUpgradeResponse(short retCode) {
/* 28 */     this.eventResponseId = 29004;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.mounts);
/* 36 */     ProtocolUtil.writeInt(out, this.level);
/* 37 */     ProtocolUtil.writeInt(out, this.exp);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.mounts = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.exp = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MoountsUpgradeResponse clone() throws CloneNotSupportedException {
/* 49 */     MoountsUpgradeResponse clone = (MoountsUpgradeResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.mounts = 0;
/* 56 */     this.level = 0;
/* 57 */     this.exp = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"mounts\":" + this.mounts + ",\"level\":" + this.level + ",\"exp\":" + this.exp + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\mounts\MoountsUpgradeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */