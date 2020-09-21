/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rankAct;
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
/*    */ public class RankActListRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int rankId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 28 */     ProtocolUtil.writeInt(out, this.rankId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.rankId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RankActListRequest clone() throws CloneNotSupportedException {
/* 38 */     RankActListRequest clone = (RankActListRequest)super.clone();
/* 39 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 44 */     this.rankId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "{\"rankId\":" + this.rankId + "}";
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rankAct\RankActListRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */