/*    */ package com.linlongyx.sanguo.webgame.proto.binary.sign;
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
/*    */ public class BuyLevelPackageResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int level;
/*    */   public int buyTimes;
/*    */   
/*    */   public BuyLevelPackageResponse() {
/* 22 */     this.eventResponseId = 25207;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public BuyLevelPackageResponse(short retCode) {
/* 27 */     this.eventResponseId = 25207;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.level);
/* 35 */     ProtocolUtil.writeInt(out, this.buyTimes);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.buyTimes = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BuyLevelPackageResponse clone() throws CloneNotSupportedException {
/* 46 */     BuyLevelPackageResponse clone = (BuyLevelPackageResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.level = 0;
/* 53 */     this.buyTimes = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"level\":" + this.level + ",\"buyTimes\":" + this.buyTimes + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\sign\BuyLevelPackageResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */