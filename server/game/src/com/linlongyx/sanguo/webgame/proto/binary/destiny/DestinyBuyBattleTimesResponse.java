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
/*    */ public class DestinyBuyBattleTimesResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int robTimes;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 27 */     ProtocolUtil.writeInt(out, this.robTimes);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 32 */     this.robTimes = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyBuyBattleTimesResponse clone() throws CloneNotSupportedException {
/* 37 */     DestinyBuyBattleTimesResponse clone = (DestinyBuyBattleTimesResponse)super.clone();
/* 38 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 43 */     this.robTimes = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     String retVal = "{\"robTimes\":" + this.robTimes + "}";
/* 49 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\destiny\DestinyBuyBattleTimesResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */