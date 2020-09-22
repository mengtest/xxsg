/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerData
/*    */ {
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public long fightValue;
/*    */   public int level;
/*    */   public String head;
/*    */   public byte sex;
/*    */   public byte vip;
/*    */   public String blocName;
/*    */   public int quality;
/*    */   public int title;
/*    */   public int mounts;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 25 */     ProtocolUtil.writeLong(out, this.playerId);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 27 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 28 */     ProtocolUtil.writeInt(out, this.level);
/* 29 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 30 */     ProtocolUtil.writeByte(out, this.sex);
/* 31 */     ProtocolUtil.writeByte(out, this.vip);
/* 32 */     ProtocolUtil.writeUTFBinary(out, this.blocName);
/* 33 */     ProtocolUtil.writeInt(out, this.quality);
/* 34 */     ProtocolUtil.writeInt(out, this.title);
/* 35 */     ProtocolUtil.writeInt(out, this.mounts);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 41 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 42 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 43 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.head = ProtocolUtil.readUTFStr(in);
/* 45 */     this.sex = ProtocolUtil.readUTFBinByte(in);
/* 46 */     this.vip = ProtocolUtil.readUTFBinByte(in);
/* 47 */     this.blocName = ProtocolUtil.readUTFStr(in);
/* 48 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.title = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.mounts = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerData clone() throws CloneNotSupportedException {
/* 55 */     PlayerData clone = (PlayerData)super.clone();
/* 56 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 61 */     this.playerId = 0L;
/* 62 */     this.playerName = null;
/* 63 */     this.fightValue = 0L;
/* 64 */     this.level = 0;
/* 65 */     this.head = null;
/* 66 */     this.sex = 0;
/* 67 */     this.vip = 0;
/* 68 */     this.blocName = null;
/* 69 */     this.quality = 0;
/* 70 */     this.title = 0;
/* 71 */     this.mounts = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"fightValue\":" + this.fightValue + ",\"level\":" + this.level + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"sex\":" + this.sex + ",\"vip\":" + this.vip + ",\"blocName\":" + StringUtil.str2Str(this.blocName) + ",\"quality\":" + this.quality + ",\"title\":" + this.title + ",\"mounts\":" + this.mounts + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\PlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */