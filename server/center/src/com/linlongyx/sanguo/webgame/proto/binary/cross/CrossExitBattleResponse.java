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
/*    */ public class CrossExitBattleResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long playerId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 27 */     ProtocolUtil.writeLong(out, this.playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 32 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossExitBattleResponse clone() throws CloneNotSupportedException {
/* 37 */     CrossExitBattleResponse clone = (CrossExitBattleResponse)super.clone();
/* 38 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 43 */     this.playerId = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     String retVal = "{\"playerId\":" + this.playerId + "}";
/* 49 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossExitBattleResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */