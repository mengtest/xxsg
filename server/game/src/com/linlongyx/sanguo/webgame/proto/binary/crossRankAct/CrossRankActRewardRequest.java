/*    */ package com.linlongyx.sanguo.webgame.proto.binary.crossRankAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class CrossRankActRewardRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int rankId;
/*    */   public int itemId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.rankId);
/* 30 */     ProtocolUtil.writeInt(out, this.itemId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.rankId = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossRankActRewardRequest clone() throws CloneNotSupportedException {
/* 41 */     CrossRankActRewardRequest clone = (CrossRankActRewardRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.rankId = 0;
/* 48 */     this.itemId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"rankId\":" + this.rankId + ",\"itemId\":" + this.itemId + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\crossRankAct\CrossRankActRewardRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */