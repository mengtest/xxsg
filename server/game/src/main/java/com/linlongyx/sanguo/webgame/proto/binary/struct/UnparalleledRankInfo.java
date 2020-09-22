/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UnparalleledRankInfo
/*    */ {
/*    */   public int rank;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public long fightValuye;
/*    */   public int stars;
/*    */   public String head;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     ProtocolUtil.writeInt(out, this.rank);
/* 22 */     ProtocolUtil.writeLong(out, this.playerId);
/* 23 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 24 */     ProtocolUtil.writeLong(out, this.fightValuye);
/* 25 */     ProtocolUtil.writeInt(out, this.stars);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.head);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 31 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 32 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 33 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 34 */     this.fightValuye = ProtocolUtil.readUTFBinLong(in);
/* 35 */     this.stars = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.head = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public UnparalleledRankInfo clone() throws CloneNotSupportedException {
/* 41 */     UnparalleledRankInfo clone = (UnparalleledRankInfo)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.rank = 0;
/* 48 */     this.playerId = 0L;
/* 49 */     this.playerName = null;
/* 50 */     this.fightValuye = 0L;
/* 51 */     this.stars = 0;
/* 52 */     this.head = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"rank\":" + this.rank + ",\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"fightValuye\":" + this.fightValuye + ",\"stars\":" + this.stars + ",\"head\":" + StringUtil.str2Str(this.head) + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\UnparalleledRankInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */