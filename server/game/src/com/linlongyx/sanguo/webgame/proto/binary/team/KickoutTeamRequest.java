/*    */ package com.linlongyx.sanguo.webgame.proto.binary.team;
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
/*    */ public class KickoutTeamRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long teamId;
/*    */   public long playerId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeLong(out, this.teamId);
/* 30 */     ProtocolUtil.writeLong(out, this.playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.teamId = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public KickoutTeamRequest clone() throws CloneNotSupportedException {
/* 41 */     KickoutTeamRequest clone = (KickoutTeamRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.teamId = 0L;
/* 48 */     this.playerId = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"teamId\":" + this.teamId + ",\"playerId\":" + this.playerId + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\team\KickoutTeamRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */