/*     */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*     */ 
/*     */ import com.linlongyx.core.utils.StringUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TeamPlayerData
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public long playerId;
/*     */   public String head;
/*     */   public String name;
/*     */   public short level;
/*     */   public long fightValue;
/*     */   public byte isLeader;
/*     */   public byte status;
/*     */   public byte isRobot;
/*     */   public int quality;
/*     */   public int fashionId;
/*  26 */   public ArrayList<TeamFighterData> fighterList = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  30 */     ProtocolUtil.writeLong(out, this.playerId);
/*  31 */     ProtocolUtil.writeUTFBinary(out, this.head);
/*  32 */     ProtocolUtil.writeUTFBinary(out, this.name);
/*  33 */     ProtocolUtil.writeShort(out, this.level);
/*  34 */     ProtocolUtil.writeLong(out, this.fightValue);
/*  35 */     ProtocolUtil.writeByte(out, this.isLeader);
/*  36 */     ProtocolUtil.writeByte(out, this.status);
/*  37 */     ProtocolUtil.writeByte(out, this.isRobot);
/*  38 */     ProtocolUtil.writeInt(out, this.quality);
/*  39 */     ProtocolUtil.writeInt(out, this.fashionId);
/*     */     
/*  41 */     int size_0 = this.fighterList.size();
/*  42 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  43 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  45 */       TeamFighterData tmp_0 = this.fighterList.get(index_0);
/*  46 */       tmp_0.serial(out);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  52 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/*  53 */     this.head = ProtocolUtil.readUTFStr(in);
/*  54 */     this.name = ProtocolUtil.readUTFStr(in);
/*  55 */     this.level = ProtocolUtil.readUTFBinShort(in);
/*  56 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/*  57 */     this.isLeader = ProtocolUtil.readUTFBinByte(in);
/*  58 */     this.status = ProtocolUtil.readUTFBinByte(in);
/*  59 */     this.isRobot = ProtocolUtil.readUTFBinByte(in);
/*  60 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/*  61 */     this.fashionId = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  63 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  66 */       TeamFighterData tmp_0 = new TeamFighterData();
/*  67 */       tmp_0.unserial(in);
/*  68 */       this.fighterList.add(tmp_0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TeamPlayerData clone() throws CloneNotSupportedException {
/*  74 */     TeamPlayerData clone = (TeamPlayerData)super.clone();
/*  75 */     int size_0 = this.fighterList.size();
/*  76 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  78 */       TeamFighterData tmp_0 = this.fighterList.get(index_0);
/*  79 */       clone.fighterList.add(tmp_0.clone());
/*     */     } 
/*  81 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  86 */     this.playerId = 0L;
/*  87 */     this.head = null;
/*  88 */     this.name = null;
/*  89 */     this.level = 0;
/*  90 */     this.fightValue = 0L;
/*  91 */     this.isLeader = 0;
/*  92 */     this.status = 0;
/*  93 */     this.isRobot = 0;
/*  94 */     this.quality = 0;
/*  95 */     this.fashionId = 0;
/*  96 */     this.fighterList.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 101 */     String retVal = "{\"playerId\":" + this.playerId + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"level\":" + this.level + ",\"fightValue\":" + this.fightValue + ",\"isLeader\":" + this.isLeader + ",\"status\":" + this.status + ",\"isRobot\":" + this.isRobot + ",\"quality\":" + this.quality + ",\"fashionId\":" + this.fashionId + ",\"fighterList\":" + this.fighterList.toString() + "}";
/* 102 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\TeamPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */