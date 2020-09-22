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
/*    */ public class ConvenientInviteRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int insType;
/*    */   public long teamId;
/*    */   public int type;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.insType);
/* 31 */     ProtocolUtil.writeLong(out, this.teamId);
/* 32 */     ProtocolUtil.writeInt(out, this.type);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.insType = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.teamId = ProtocolUtil.readUTFBinLong(in);
/* 39 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ConvenientInviteRequest clone() throws CloneNotSupportedException {
/* 44 */     ConvenientInviteRequest clone = (ConvenientInviteRequest)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.insType = 0;
/* 51 */     this.teamId = 0L;
/* 52 */     this.type = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"insType\":" + this.insType + ",\"teamId\":" + this.teamId + ",\"type\":" + this.type + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\team\ConvenientInviteRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */