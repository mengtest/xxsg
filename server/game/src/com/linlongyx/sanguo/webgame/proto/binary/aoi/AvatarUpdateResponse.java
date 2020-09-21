/*    */ package com.linlongyx.sanguo.webgame.proto.binary.aoi;
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
/*    */ @Message
/*    */ public class AvatarUpdateResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long playerId;
/*    */   public long hp;
/*    */   public long addVal;
/*    */   
/*    */   public AvatarUpdateResponse() {
/* 23 */     this.eventResponseId = 20113;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public AvatarUpdateResponse(short retCode) {
/* 28 */     this.eventResponseId = 20113;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeLong(out, this.playerId);
/* 36 */     ProtocolUtil.writeLong(out, this.hp);
/* 37 */     ProtocolUtil.writeLong(out, this.addVal);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 43 */     this.hp = ProtocolUtil.readUTFBinLong(in);
/* 44 */     this.addVal = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public AvatarUpdateResponse clone() throws CloneNotSupportedException {
/* 49 */     AvatarUpdateResponse clone = (AvatarUpdateResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.playerId = 0L;
/* 56 */     this.hp = 0L;
/* 57 */     this.addVal = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"playerId\":" + this.playerId + ",\"hp\":" + this.hp + ",\"addVal\":" + this.addVal + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\AvatarUpdateResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */