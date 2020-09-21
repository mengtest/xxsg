/*    */ package com.linlongyx.sanguo.webgame.proto.binary.group;
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
/*    */ public class GroupApplyDealRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int type;
/*    */   public long playerId;
/*    */   public int deal;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.type);
/* 31 */     ProtocolUtil.writeLong(out, this.playerId);
/* 32 */     ProtocolUtil.writeInt(out, this.deal);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 39 */     this.deal = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupApplyDealRequest clone() throws CloneNotSupportedException {
/* 44 */     GroupApplyDealRequest clone = (GroupApplyDealRequest)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.type = 0;
/* 51 */     this.playerId = 0L;
/* 52 */     this.deal = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"type\":" + this.type + ",\"playerId\":" + this.playerId + ",\"deal\":" + this.deal + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupApplyDealRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */