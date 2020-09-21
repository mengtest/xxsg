/*    */ package com.linlongyx.sanguo.webgame.proto.binary.kungfu;
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
/*    */ public class KungFuUpgradeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int kungFu;
/*    */   public int level;
/*    */   public int exp;
/*    */   
/*    */   public KungFuUpgradeResponse() {
/* 23 */     this.eventResponseId = 25604;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public KungFuUpgradeResponse(short retCode) {
/* 28 */     this.eventResponseId = 25604;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.kungFu);
/* 36 */     ProtocolUtil.writeInt(out, this.level);
/* 37 */     ProtocolUtil.writeInt(out, this.exp);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.kungFu = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.exp = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public KungFuUpgradeResponse clone() throws CloneNotSupportedException {
/* 49 */     KungFuUpgradeResponse clone = (KungFuUpgradeResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.kungFu = 0;
/* 56 */     this.level = 0;
/* 57 */     this.exp = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"kungFu\":" + this.kungFu + ",\"level\":" + this.level + ",\"exp\":" + this.exp + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\kungfu\KungFuUpgradeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */