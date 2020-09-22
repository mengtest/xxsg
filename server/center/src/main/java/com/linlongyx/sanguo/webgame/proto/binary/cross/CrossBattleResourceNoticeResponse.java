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
/*    */ public class CrossBattleResourceNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int resourceId;
/*    */   public int state;
/*    */   public long playerId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.resourceId);
/* 30 */     ProtocolUtil.writeInt(out, this.state);
/* 31 */     ProtocolUtil.writeLong(out, this.playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.resourceId = ProtocolUtil.readUTFBinInt(in);
/* 37 */     this.state = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossBattleResourceNoticeResponse clone() throws CloneNotSupportedException {
/* 43 */     CrossBattleResourceNoticeResponse clone = (CrossBattleResourceNoticeResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.resourceId = 0;
/* 50 */     this.state = 0;
/* 51 */     this.playerId = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     String retVal = "{\"resourceId\":" + this.resourceId + ",\"state\":" + this.state + ",\"playerId\":" + this.playerId + "}";
/* 57 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossBattleResourceNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */