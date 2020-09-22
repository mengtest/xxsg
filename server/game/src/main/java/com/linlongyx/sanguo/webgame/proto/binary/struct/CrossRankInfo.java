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
/*    */ public class CrossRankInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -4130094399034841034L;
/*    */   public int rank;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public int rankScore;
/*    */   public long fightValue;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 22 */     ProtocolUtil.writeInt(out, this.rank);
/* 23 */     ProtocolUtil.writeLong(out, this.playerId);
/* 24 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 25 */     ProtocolUtil.writeInt(out, this.rankScore);
/* 26 */     ProtocolUtil.writeLong(out, this.fightValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 31 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 32 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 33 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 34 */     this.rankScore = ProtocolUtil.readUTFBinInt(in);
/* 35 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossRankInfo clone() throws CloneNotSupportedException {
/* 40 */     CrossRankInfo clone = (CrossRankInfo)super.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 46 */     this.rank = 0;
/* 47 */     this.playerId = 0L;
/* 48 */     this.playerName = null;
/* 49 */     this.rankScore = 0;
/* 50 */     this.fightValue = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     String retVal = "{\"rank\":" + this.rank + ",\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"rankScore\":" + this.rankScore + ",\"fightValue\":" + this.fightValue + "}";
/* 56 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\CrossRankInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */