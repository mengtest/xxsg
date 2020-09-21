/*    */ package com.linlongyx.sanguo.webgame.proto.binary.crossRankAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
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
/*    */ public class CrossRankActRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int rankId;
/* 20 */   public IntIntValue data = new IntIntValue();
/*    */   
/*    */   public CrossRankActRewardResponse() {
/* 23 */     this.eventResponseId = 24104;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.rankId);
/* 30 */     this.data.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.rankId = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.data = new IntIntValue();
/* 37 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossRankActRewardResponse clone() throws CloneNotSupportedException {
/* 42 */     CrossRankActRewardResponse clone = (CrossRankActRewardResponse)super.clone();
/* 43 */     clone.data = this.data.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.rankId = 0;
/* 50 */     this.data.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     String retVal = "{\"rankId\":" + this.rankId + ",\"data\":" + this.data.toString() + "}";
/* 56 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\crossRankAct\CrossRankActRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */