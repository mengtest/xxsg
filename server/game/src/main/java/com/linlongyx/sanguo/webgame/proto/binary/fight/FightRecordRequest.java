/*    */ package com.linlongyx.sanguo.webgame.proto.binary.fight;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
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
/*    */ public class FightRecordRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public byte type;
/*    */   public String id;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeByte(out, this.type);
/* 31 */     ProtocolUtil.writeUTFBinary(out, this.id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 37 */     this.id = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FightRecordRequest clone() throws CloneNotSupportedException {
/* 42 */     FightRecordRequest clone = (FightRecordRequest)super.clone();
/* 43 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 48 */     this.type = 0;
/* 49 */     this.id = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"type\":" + this.type + ",\"id\":" + StringUtil.str2Str(this.id) + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\fight\FightRecordRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */