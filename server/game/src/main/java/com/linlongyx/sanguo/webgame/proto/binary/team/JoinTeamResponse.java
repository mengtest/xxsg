/*    */ package com.linlongyx.sanguo.webgame.proto.binary.team;
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
/*    */ public class JoinTeamResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int insId;
/*    */   public long teamId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.type);
/* 30 */     ProtocolUtil.writeInt(out, this.insId);
/* 31 */     ProtocolUtil.writeLong(out, this.teamId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 37 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.teamId = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public JoinTeamResponse clone() throws CloneNotSupportedException {
/* 43 */     JoinTeamResponse clone = (JoinTeamResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.type = 0;
/* 50 */     this.insId = 0;
/* 51 */     this.teamId = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     String retVal = "{\"type\":" + this.type + ",\"insId\":" + this.insId + ",\"teamId\":" + this.teamId + "}";
/* 57 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\team\JoinTeamResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */