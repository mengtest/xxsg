/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScoreData
/*    */ {
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public int score;
/*    */   public int rank;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeLong(out, this.playerId);
/* 19 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 20 */     ProtocolUtil.writeInt(out, this.score);
/* 21 */     ProtocolUtil.writeInt(out, this.rank);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 26 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 27 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 28 */     this.score = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ScoreData clone() throws CloneNotSupportedException {
/* 34 */     ScoreData clone = (ScoreData)super.clone();
/* 35 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 40 */     this.playerId = 0L;
/* 41 */     this.playerName = null;
/* 42 */     this.score = 0;
/* 43 */     this.rank = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"score\":" + this.score + ",\"rank\":" + this.rank + "}";
/* 49 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ScoreData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */