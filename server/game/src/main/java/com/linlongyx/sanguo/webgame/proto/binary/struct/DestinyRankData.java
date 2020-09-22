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
/*    */ public class DestinyRankData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public String head;
/*    */   public long fightValue;
/*    */   public int destinyPoint;
/*    */   public int quality;
/*    */   public int rank;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 24 */     ProtocolUtil.writeLong(out, this.playerId);
/* 25 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 27 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 28 */     ProtocolUtil.writeInt(out, this.destinyPoint);
/* 29 */     ProtocolUtil.writeInt(out, this.quality);
/* 30 */     ProtocolUtil.writeInt(out, this.rank);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 37 */     this.head = ProtocolUtil.readUTFStr(in);
/* 38 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 39 */     this.destinyPoint = ProtocolUtil.readUTFBinInt(in);
/* 40 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyRankData clone() throws CloneNotSupportedException {
/* 46 */     DestinyRankData clone = (DestinyRankData)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.playerId = 0L;
/* 53 */     this.playerName = null;
/* 54 */     this.head = null;
/* 55 */     this.fightValue = 0L;
/* 56 */     this.destinyPoint = 0;
/* 57 */     this.quality = 0;
/* 58 */     this.rank = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"fightValue\":" + this.fightValue + ",\"destinyPoint\":" + this.destinyPoint + ",\"quality\":" + this.quality + ",\"rank\":" + this.rank + "}";
/* 64 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\DestinyRankData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */