/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cross;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class CrossSyncPosResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long playerId;
/*    */   public int x;
/*    */   public int y;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeLong(out, this.playerId);
/* 30 */     ProtocolUtil.writeInt(out, this.x);
/* 31 */     ProtocolUtil.writeInt(out, this.y);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 37 */     this.x = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.y = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossSyncPosResponse clone() throws CloneNotSupportedException {
/* 43 */     CrossSyncPosResponse clone = (CrossSyncPosResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.playerId = 0L;
/* 50 */     this.x = 0;
/* 51 */     this.y = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     String retVal = "{\"playerId\":" + this.playerId + ",\"x\":" + this.x + ",\"y\":" + this.y + "}";
/* 57 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossSyncPosResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */