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
/*    */ public class BattlePlayerData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public String head;
/* 18 */   public ModelData model = new ModelData();
/*    */   
/*    */   public long fightValue;
/*    */   public int destinyPoint;
/*    */   public int destinyStone;
/*    */   public int quality;
/*    */   public byte win;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 27 */     ProtocolUtil.writeLong(out, this.playerId);
/* 28 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 29 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 30 */     this.model.serial(out);
/* 31 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 32 */     ProtocolUtil.writeInt(out, this.destinyPoint);
/* 33 */     ProtocolUtil.writeInt(out, this.destinyStone);
/* 34 */     ProtocolUtil.writeInt(out, this.quality);
/* 35 */     ProtocolUtil.writeByte(out, this.win);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 41 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 42 */     this.head = ProtocolUtil.readUTFStr(in);
/* 43 */     this.model = new ModelData();
/* 44 */     this.model.unserial(in);
/* 45 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 46 */     this.destinyPoint = ProtocolUtil.readUTFBinInt(in);
/* 47 */     this.destinyStone = ProtocolUtil.readUTFBinInt(in);
/* 48 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.win = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BattlePlayerData clone() throws CloneNotSupportedException {
/* 54 */     BattlePlayerData clone = (BattlePlayerData)super.clone();
/* 55 */     clone.model = this.model.clone();
/* 56 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 61 */     this.playerId = 0L;
/* 62 */     this.playerName = null;
/* 63 */     this.head = null;
/* 64 */     this.model.reset();
/* 65 */     this.fightValue = 0L;
/* 66 */     this.destinyPoint = 0;
/* 67 */     this.destinyStone = 0;
/* 68 */     this.quality = 0;
/* 69 */     this.win = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"model\":" + this.model.toString() + ",\"fightValue\":" + this.fightValue + ",\"destinyPoint\":" + this.destinyPoint + ",\"destinyStone\":" + this.destinyStone + ",\"quality\":" + this.quality + ",\"win\":" + this.win + "}";
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\BattlePlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */