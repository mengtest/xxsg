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
/*    */ public class PlayerData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
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
/*    */   public int fashionId;
/*    */   public int mounts;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeLong(out, this.playerId);
/* 30 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 31 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 32 */     ProtocolUtil.writeInt(out, this.level);
/* 33 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 34 */     ProtocolUtil.writeByte(out, this.sex);
/* 35 */     ProtocolUtil.writeByte(out, this.vip);
/* 36 */     ProtocolUtil.writeUTFBinary(out, this.blocName);
/* 37 */     ProtocolUtil.writeInt(out, this.quality);
/* 38 */     ProtocolUtil.writeInt(out, this.title);
/* 39 */     ProtocolUtil.writeInt(out, this.fashionId);
/* 40 */     ProtocolUtil.writeInt(out, this.mounts);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 45 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 46 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 47 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 48 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.head = ProtocolUtil.readUTFStr(in);
/* 50 */     this.sex = ProtocolUtil.readUTFBinByte(in);
/* 51 */     this.vip = ProtocolUtil.readUTFBinByte(in);
/* 52 */     this.blocName = ProtocolUtil.readUTFStr(in);
/* 53 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.title = ProtocolUtil.readUTFBinInt(in);
/* 55 */     this.fashionId = ProtocolUtil.readUTFBinInt(in);
/* 56 */     this.mounts = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerData clone() throws CloneNotSupportedException {
/* 61 */     PlayerData clone = (PlayerData)super.clone();
/* 62 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 67 */     this.playerId = 0L;
/* 68 */     this.playerName = null;
/* 69 */     this.fightValue = 0L;
/* 70 */     this.level = 0;
/* 71 */     this.head = null;
/* 72 */     this.sex = 0;
/* 73 */     this.vip = 0;
/* 74 */     this.blocName = null;
/* 75 */     this.quality = 0;
/* 76 */     this.title = 0;
/* 77 */     this.fashionId = 0;
/* 78 */     this.mounts = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 83 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"fightValue\":" + this.fightValue + ",\"level\":" + this.level + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"sex\":" + this.sex + ",\"vip\":" + this.vip + ",\"blocName\":" + StringUtil.str2Str(this.blocName) + ",\"quality\":" + this.quality + ",\"title\":" + this.title + ",\"fashionId\":" + this.fashionId + ",\"mounts\":" + this.mounts + "}";
/* 84 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\PlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */