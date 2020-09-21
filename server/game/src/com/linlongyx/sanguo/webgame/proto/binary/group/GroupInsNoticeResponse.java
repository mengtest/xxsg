/*    */ package com.linlongyx.sanguo.webgame.proto.binary.group;
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
/*    */ public class GroupInsNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int insId;
/*    */   public long curHp;
/*    */   public long maxHp;
/*    */   public long damage;
/*    */   public String playerName;
/*    */   
/*    */   public GroupInsNoticeResponse() {
/* 26 */     this.eventResponseId = 21121;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GroupInsNoticeResponse(short retCode) {
/* 31 */     this.eventResponseId = 21121;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeInt(out, this.insId);
/* 39 */     ProtocolUtil.writeLong(out, this.curHp);
/* 40 */     ProtocolUtil.writeLong(out, this.maxHp);
/* 41 */     ProtocolUtil.writeLong(out, this.damage);
/* 42 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 47 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 48 */     this.curHp = ProtocolUtil.readUTFBinLong(in);
/* 49 */     this.maxHp = ProtocolUtil.readUTFBinLong(in);
/* 50 */     this.damage = ProtocolUtil.readUTFBinLong(in);
/* 51 */     this.playerName = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupInsNoticeResponse clone() throws CloneNotSupportedException {
/* 56 */     GroupInsNoticeResponse clone = (GroupInsNoticeResponse)super.clone();
/* 57 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 62 */     this.insId = 0;
/* 63 */     this.curHp = 0L;
/* 64 */     this.maxHp = 0L;
/* 65 */     this.damage = 0L;
/* 66 */     this.playerName = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 71 */     String retVal = "{\"insId\":" + this.insId + ",\"curHp\":" + this.curHp + ",\"maxHp\":" + this.maxHp + ",\"damage\":" + this.damage + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + "}";
/* 72 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupInsNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */