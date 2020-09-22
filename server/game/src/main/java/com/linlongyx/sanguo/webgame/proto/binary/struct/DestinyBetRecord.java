/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyBetRecord
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long leftPlayerId;
/*    */   public String leftPlayerName;
/*    */   public String leftHead;
/*    */   public long leftFightValue;
/*    */   public long rightPlayerId;
/*    */   public String rightPlayerName;
/*    */   public String rightHead;
/*    */   public long rightFightValue;
/*    */   public long winner;
/*    */   public String recordId;
/*    */   public long num;
/*    */   public int createtime;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeLong(out, this.leftPlayerId);
/* 30 */     ProtocolUtil.writeUTFBinary(out, this.leftPlayerName);
/* 31 */     ProtocolUtil.writeUTFBinary(out, this.leftHead);
/* 32 */     ProtocolUtil.writeLong(out, this.leftFightValue);
/* 33 */     ProtocolUtil.writeLong(out, this.rightPlayerId);
/* 34 */     ProtocolUtil.writeUTFBinary(out, this.rightPlayerName);
/* 35 */     ProtocolUtil.writeUTFBinary(out, this.rightHead);
/* 36 */     ProtocolUtil.writeLong(out, this.rightFightValue);
/* 37 */     ProtocolUtil.writeLong(out, this.winner);
/* 38 */     ProtocolUtil.writeUTFBinary(out, this.recordId);
/* 39 */     ProtocolUtil.writeLong(out, this.num);
/* 40 */     ProtocolUtil.writeInt(out, this.createtime);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 45 */     this.leftPlayerId = ProtocolUtil.readUTFBinLong(in);
/* 46 */     this.leftPlayerName = ProtocolUtil.readUTFStr(in);
/* 47 */     this.leftHead = ProtocolUtil.readUTFStr(in);
/* 48 */     this.leftFightValue = ProtocolUtil.readUTFBinLong(in);
/* 49 */     this.rightPlayerId = ProtocolUtil.readUTFBinLong(in);
/* 50 */     this.rightPlayerName = ProtocolUtil.readUTFStr(in);
/* 51 */     this.rightHead = ProtocolUtil.readUTFStr(in);
/* 52 */     this.rightFightValue = ProtocolUtil.readUTFBinLong(in);
/* 53 */     this.winner = ProtocolUtil.readUTFBinLong(in);
/* 54 */     this.recordId = ProtocolUtil.readUTFStr(in);
/* 55 */     this.num = ProtocolUtil.readUTFBinLong(in);
/* 56 */     this.createtime = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyBetRecord clone() throws CloneNotSupportedException {
/* 61 */     DestinyBetRecord clone = (DestinyBetRecord)super.clone();
/* 62 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 67 */     this.leftPlayerId = 0L;
/* 68 */     this.leftPlayerName = null;
/* 69 */     this.leftHead = null;
/* 70 */     this.leftFightValue = 0L;
/* 71 */     this.rightPlayerId = 0L;
/* 72 */     this.rightPlayerName = null;
/* 73 */     this.rightHead = null;
/* 74 */     this.rightFightValue = 0L;
/* 75 */     this.winner = 0L;
/* 76 */     this.recordId = null;
/* 77 */     this.num = 0L;
/* 78 */     this.createtime = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 83 */     String retVal = "{\"leftPlayerId\":" + this.leftPlayerId + ",\"leftPlayerName\":" + StringUtil.str2Str(this.leftPlayerName) + ",\"leftHead\":" + StringUtil.str2Str(this.leftHead) + ",\"leftFightValue\":" + this.leftFightValue + ",\"rightPlayerId\":" + this.rightPlayerId + ",\"rightPlayerName\":" + StringUtil.str2Str(this.rightPlayerName) + ",\"rightHead\":" + StringUtil.str2Str(this.rightHead) + ",\"rightFightValue\":" + this.rightFightValue + ",\"winner\":" + this.winner + ",\"recordId\":" + StringUtil.str2Str(this.recordId) + ",\"num\":" + this.num + ",\"createtime\":" + this.createtime + "}";
/* 84 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\DestinyBetRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */