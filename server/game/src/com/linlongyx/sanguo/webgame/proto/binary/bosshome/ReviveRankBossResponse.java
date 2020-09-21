/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bosshome;
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
/*    */ public class ReviveRankBossResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int bossId;
/*    */   
/*    */   public ReviveRankBossResponse() {
/* 21 */     this.eventResponseId = 20314;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ReviveRankBossResponse(short retCode) {
/* 26 */     this.eventResponseId = 20314;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.bossId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.bossId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ReviveRankBossResponse clone() throws CloneNotSupportedException {
/* 43 */     ReviveRankBossResponse clone = (ReviveRankBossResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.bossId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"bossId\":" + this.bossId + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bosshome\ReviveRankBossResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */