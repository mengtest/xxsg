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
/*    */ public class LeaveTeamRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long teamId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 28 */     ProtocolUtil.writeLong(out, this.teamId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.teamId = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LeaveTeamRequest clone() throws CloneNotSupportedException {
/* 38 */     LeaveTeamRequest clone = (LeaveTeamRequest)super.clone();
/* 39 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 44 */     this.teamId = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "{\"teamId\":" + this.teamId + "}";
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\team\LeaveTeamRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */