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
/*    */ public class BossKillData
/*    */   implements Serializable
/*    */ {
/*    */   public int time;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public long fightValue;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeInt(out, this.time);
/* 21 */     ProtocolUtil.writeLong(out, this.playerId);
/* 22 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 23 */     ProtocolUtil.writeLong(out, this.fightValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 28 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 30 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 31 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BossKillData clone() throws CloneNotSupportedException {
/* 36 */     BossKillData clone = (BossKillData)super.clone();
/* 37 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 42 */     this.time = 0;
/* 43 */     this.playerId = 0L;
/* 44 */     this.playerName = null;
/* 45 */     this.fightValue = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "{\"time\":" + this.time + ",\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"fightValue\":" + this.fightValue + "}";
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\BossKillData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */