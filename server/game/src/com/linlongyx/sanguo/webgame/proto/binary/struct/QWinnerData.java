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
/*    */ public class QWinnerData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int serverId;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public String head;
/*    */   public long amount;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 22 */     ProtocolUtil.writeInt(out, this.serverId);
/* 23 */     ProtocolUtil.writeLong(out, this.playerId);
/* 24 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 25 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 26 */     ProtocolUtil.writeLong(out, this.amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 31 */     this.serverId = ProtocolUtil.readUTFBinInt(in);
/* 32 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 33 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 34 */     this.head = ProtocolUtil.readUTFStr(in);
/* 35 */     this.amount = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public QWinnerData clone() throws CloneNotSupportedException {
/* 40 */     QWinnerData clone = (QWinnerData)super.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 46 */     this.serverId = 0;
/* 47 */     this.playerId = 0L;
/* 48 */     this.playerName = null;
/* 49 */     this.head = null;
/* 50 */     this.amount = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     String retVal = "{\"serverId\":" + this.serverId + ",\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"amount\":" + this.amount + "}";
/* 56 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\QWinnerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */