/*    */ package com.linlongyx.sanguo.webgame.proto.binary.destiny;
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
/*    */ public class DestinyBetResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long playerId;
/*    */   public int num;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 28 */     ProtocolUtil.writeLong(out, this.playerId);
/* 29 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 35 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyBetResponse clone() throws CloneNotSupportedException {
/* 40 */     DestinyBetResponse clone = (DestinyBetResponse)super.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 46 */     this.playerId = 0L;
/* 47 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     String retVal = "{\"playerId\":" + this.playerId + ",\"num\":" + this.num + "}";
/* 53 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\destiny\DestinyBetResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */