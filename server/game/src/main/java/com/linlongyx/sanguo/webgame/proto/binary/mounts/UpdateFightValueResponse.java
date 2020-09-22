/*    */ package com.linlongyx.sanguo.webgame.proto.binary.mounts;
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
/*    */ public class UpdateFightValueResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int id;
/*    */   public long fightValue;
/*    */   
/*    */   public UpdateFightValueResponse() {
/* 23 */     this.eventResponseId = 29007;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public UpdateFightValueResponse(short retCode) {
/* 28 */     this.eventResponseId = 29007;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.type);
/* 36 */     ProtocolUtil.writeInt(out, this.id);
/* 37 */     ProtocolUtil.writeLong(out, this.fightValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public UpdateFightValueResponse clone() throws CloneNotSupportedException {
/* 49 */     UpdateFightValueResponse clone = (UpdateFightValueResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.type = 0;
/* 56 */     this.id = 0;
/* 57 */     this.fightValue = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"type\":" + this.type + ",\"id\":" + this.id + ",\"fightValue\":" + this.fightValue + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\mounts\UpdateFightValueResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */