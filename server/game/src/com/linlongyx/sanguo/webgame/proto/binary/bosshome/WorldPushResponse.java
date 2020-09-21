/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
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
/*    */ @Message
/*    */ public class WorldPushResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int insId;
/*    */   public long curHp;
/*    */   public long maxHp;
/*    */   public String name;
/*    */   public long hurt;
/*    */   
/*    */   public WorldPushResponse() {
/* 26 */     this.eventResponseId = 20306;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WorldPushResponse(short retCode) {
/* 31 */     this.eventResponseId = 20306;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeInt(out, this.insId);
/* 39 */     ProtocolUtil.writeLong(out, this.curHp);
/* 40 */     ProtocolUtil.writeLong(out, this.maxHp);
/* 41 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 42 */     ProtocolUtil.writeLong(out, this.hurt);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 47 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 48 */     this.curHp = ProtocolUtil.readUTFBinLong(in);
/* 49 */     this.maxHp = ProtocolUtil.readUTFBinLong(in);
/* 50 */     this.name = ProtocolUtil.readUTFStr(in);
/* 51 */     this.hurt = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldPushResponse clone() throws CloneNotSupportedException {
/* 56 */     WorldPushResponse clone = (WorldPushResponse)super.clone();
/* 57 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 62 */     this.insId = 0;
/* 63 */     this.curHp = 0L;
/* 64 */     this.maxHp = 0L;
/* 65 */     this.name = null;
/* 66 */     this.hurt = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 71 */     String retVal = "{\"insId\":" + this.insId + ",\"curHp\":" + this.curHp + ",\"maxHp\":" + this.maxHp + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"hurt\":" + this.hurt + "}";
/* 72 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bosshome\WorldPushResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */