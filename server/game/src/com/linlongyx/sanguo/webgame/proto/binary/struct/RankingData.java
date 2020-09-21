/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JsonIgnoreProperties(ignoreUnknown = true)
/*    */ public class RankingData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int rank;
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public long fightValue;
/*    */   public int level;
/*    */   public String head;
/*    */   public byte sex;
/*    */   public byte vip;
/*    */   public long value;
/*    */   public int titleId;
/*    */   public int fashionId;
/*    */   public int quality;
/*    */   public String blocName;
/*    */   public int mounts;
/*    */   public int time;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.rank);
/* 35 */     ProtocolUtil.writeLong(out, this.playerId);
/* 36 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 37 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 38 */     ProtocolUtil.writeInt(out, this.level);
/* 39 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 40 */     ProtocolUtil.writeByte(out, this.sex);
/* 41 */     ProtocolUtil.writeByte(out, this.vip);
/* 42 */     ProtocolUtil.writeLong(out, this.value);
/* 43 */     ProtocolUtil.writeInt(out, this.titleId);
/* 44 */     ProtocolUtil.writeInt(out, this.fashionId);
/* 45 */     ProtocolUtil.writeInt(out, this.quality);
/* 46 */     ProtocolUtil.writeUTFBinary(out, this.blocName);
/* 47 */     ProtocolUtil.writeInt(out, this.mounts);
/* 48 */     ProtocolUtil.writeInt(out, this.time);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 53 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 55 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 56 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 57 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 58 */     this.head = ProtocolUtil.readUTFStr(in);
/* 59 */     this.sex = ProtocolUtil.readUTFBinByte(in);
/* 60 */     this.vip = ProtocolUtil.readUTFBinByte(in);
/* 61 */     this.value = ProtocolUtil.readUTFBinLong(in);
/* 62 */     this.titleId = ProtocolUtil.readUTFBinInt(in);
/* 63 */     this.fashionId = ProtocolUtil.readUTFBinInt(in);
/* 64 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/* 65 */     this.blocName = ProtocolUtil.readUTFStr(in);
/* 66 */     this.mounts = ProtocolUtil.readUTFBinInt(in);
/* 67 */     this.time = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RankingData clone() throws CloneNotSupportedException {
/* 72 */     RankingData clone = (RankingData)super.clone();
/* 73 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 78 */     this.rank = 0;
/* 79 */     this.playerId = 0L;
/* 80 */     this.playerName = null;
/* 81 */     this.fightValue = 0L;
/* 82 */     this.level = 0;
/* 83 */     this.head = null;
/* 84 */     this.sex = 0;
/* 85 */     this.vip = 0;
/* 86 */     this.value = 0L;
/* 87 */     this.titleId = 0;
/* 88 */     this.fashionId = 0;
/* 89 */     this.quality = 0;
/* 90 */     this.blocName = null;
/* 91 */     this.mounts = 0;
/* 92 */     this.time = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 97 */     String retVal = "{\"rank\":" + this.rank + ",\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"fightValue\":" + this.fightValue + ",\"level\":" + this.level + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"sex\":" + this.sex + ",\"vip\":" + this.vip + ",\"value\":" + this.value + ",\"titleId\":" + this.titleId + ",\"fashionId\":" + this.fashionId + ",\"quality\":" + this.quality + ",\"blocName\":" + StringUtil.str2Str(this.blocName) + ",\"mounts\":" + this.mounts + ",\"time\":" + this.time + "}";
/* 98 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\RankingData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */