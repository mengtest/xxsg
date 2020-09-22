/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cross;
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
/*    */ public class CrossSyncPosRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long playerId;
/*    */   public int x;
/*    */   public int y;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeLong(out, this.playerId);
/* 31 */     ProtocolUtil.writeInt(out, this.x);
/* 32 */     ProtocolUtil.writeInt(out, this.y);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 38 */     this.x = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.y = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossSyncPosRequest clone() throws CloneNotSupportedException {
/* 44 */     CrossSyncPosRequest clone = (CrossSyncPosRequest)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.playerId = 0L;
/* 51 */     this.x = 0;
/* 52 */     this.y = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"playerId\":" + this.playerId + ",\"x\":" + this.x + ",\"y\":" + this.y + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossSyncPosRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */