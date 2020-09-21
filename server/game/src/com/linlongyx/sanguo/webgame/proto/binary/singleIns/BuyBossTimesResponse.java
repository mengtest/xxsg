/*    */ package com.linlongyx.sanguo.webgame.proto.binary.singleIns;
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
/*    */ public class BuyBossTimesResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int bossId;
/*    */   public int times;
/*    */   
/*    */   public BuyBossTimesResponse() {
/* 22 */     this.eventResponseId = 23005;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public BuyBossTimesResponse(short retCode) {
/* 27 */     this.eventResponseId = 23005;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.bossId);
/* 35 */     ProtocolUtil.writeInt(out, this.times);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.bossId = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.times = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BuyBossTimesResponse clone() throws CloneNotSupportedException {
/* 46 */     BuyBossTimesResponse clone = (BuyBossTimesResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.bossId = 0;
/* 53 */     this.times = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"bossId\":" + this.bossId + ",\"times\":" + this.times + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\singleIns\BuyBossTimesResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */