/*     */ package com.linlongyx.sanguo.webgame.proto.binary.login;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.StringUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import org.msgpack.annotation.Message;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Message
/*     */ public class EnterGameResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public long playerId;
/*     */   public String playerName;
/*     */   public int timestamp;
/*     */   public long hp;
/*     */   public long hpMax;
/*     */   public long fightValue;
/*     */   public long totalValue;
/*     */   public int sceneId;
/*     */   public short level;
/*     */   public long exp;
/*     */   public String head;
/*     */   public byte sex;
/*  32 */   public ArrayList<Long> attributes = new ArrayList<>();
/*  33 */   public ArrayList<Long> currencies = new ArrayList<>();
/*     */   public int openTime;
/*     */   public int createTime;
/*     */   public String checksum;
/*     */   public String nickname;
/*     */   public int lastSceneId;
/*  39 */   public ArrayList<Integer> functionIds = new ArrayList<>();
/*     */   public int quality;
/*     */   public int titleId;
/*     */   public int fashionId;
/*     */   public int mounts;
/*     */   public int partnerTableId;
/*     */   public String extend;
/*     */   
/*     */   public EnterGameResponse() {
/*  48 */     this.eventResponseId = 20003;
/*  49 */     this.codeType = 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  54 */     ProtocolUtil.writeLong(out, this.playerId);
/*  55 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/*  56 */     ProtocolUtil.writeInt(out, this.timestamp);
/*  57 */     ProtocolUtil.writeLong(out, this.hp);
/*  58 */     ProtocolUtil.writeLong(out, this.hpMax);
/*  59 */     ProtocolUtil.writeLong(out, this.fightValue);
/*  60 */     ProtocolUtil.writeLong(out, this.totalValue);
/*  61 */     ProtocolUtil.writeInt(out, this.sceneId);
/*  62 */     ProtocolUtil.writeShort(out, this.level);
/*  63 */     ProtocolUtil.writeLong(out, this.exp);
/*  64 */     ProtocolUtil.writeUTFBinary(out, this.head);
/*  65 */     ProtocolUtil.writeByte(out, this.sex);
/*     */     
/*  67 */     int size_0 = this.attributes.size();
/*  68 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  69 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  71 */       long tmp_0 = ((Long)this.attributes.get(index_0)).longValue();
/*  72 */       ProtocolUtil.writeLong(out, tmp_0);
/*     */     } 
/*     */     
/*  75 */     int size_1 = this.currencies.size();
/*  76 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  77 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  79 */       long tmp_1 = ((Long)this.currencies.get(index_1)).longValue();
/*  80 */       ProtocolUtil.writeLong(out, tmp_1);
/*     */     } 
/*  82 */     ProtocolUtil.writeInt(out, this.openTime);
/*  83 */     ProtocolUtil.writeInt(out, this.createTime);
/*  84 */     ProtocolUtil.writeUTFBinary(out, this.checksum);
/*  85 */     ProtocolUtil.writeUTFBinary(out, this.nickname);
/*  86 */     ProtocolUtil.writeInt(out, this.lastSceneId);
/*     */     
/*  88 */     int size_2 = this.functionIds.size();
/*  89 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  90 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  92 */       int tmp_2 = ((Integer)this.functionIds.get(index_2)).intValue();
/*  93 */       ProtocolUtil.writeInt(out, tmp_2);
/*     */     } 
/*  95 */     ProtocolUtil.writeInt(out, this.quality);
/*  96 */     ProtocolUtil.writeInt(out, this.titleId);
/*  97 */     ProtocolUtil.writeInt(out, this.fashionId);
/*  98 */     ProtocolUtil.writeInt(out, this.mounts);
/*  99 */     ProtocolUtil.writeInt(out, this.partnerTableId);
/* 100 */     ProtocolUtil.writeUTFBinary(out, this.extend);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/* 105 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 106 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 107 */     this.timestamp = ProtocolUtil.readUTFBinInt(in);
/* 108 */     this.hp = ProtocolUtil.readUTFBinLong(in);
/* 109 */     this.hpMax = ProtocolUtil.readUTFBinLong(in);
/* 110 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 111 */     this.totalValue = ProtocolUtil.readUTFBinLong(in);
/* 112 */     this.sceneId = ProtocolUtil.readUTFBinInt(in);
/* 113 */     this.level = ProtocolUtil.readUTFBinShort(in);
/* 114 */     this.exp = ProtocolUtil.readUTFBinLong(in);
/* 115 */     this.head = ProtocolUtil.readUTFStr(in);
/* 116 */     this.sex = ProtocolUtil.readUTFBinByte(in);
/*     */     
/* 118 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 119 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 121 */       long tmp_0 = ProtocolUtil.readUTFBinLong(in);
/* 122 */       this.attributes.add(Long.valueOf(tmp_0));
/*     */     } 
/*     */     
/* 125 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 126 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 128 */       long tmp_1 = ProtocolUtil.readUTFBinLong(in);
/* 129 */       this.currencies.add(Long.valueOf(tmp_1));
/*     */     } 
/* 131 */     this.openTime = ProtocolUtil.readUTFBinInt(in);
/* 132 */     this.createTime = ProtocolUtil.readUTFBinInt(in);
/* 133 */     this.checksum = ProtocolUtil.readUTFStr(in);
/* 134 */     this.nickname = ProtocolUtil.readUTFStr(in);
/* 135 */     this.lastSceneId = ProtocolUtil.readUTFBinInt(in);
/*     */     
/* 137 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/* 138 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 140 */       int tmp_2 = ProtocolUtil.readUTFBinInt(in);
/* 141 */       this.functionIds.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 143 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/* 144 */     this.titleId = ProtocolUtil.readUTFBinInt(in);
/* 145 */     this.fashionId = ProtocolUtil.readUTFBinInt(in);
/* 146 */     this.mounts = ProtocolUtil.readUTFBinInt(in);
/* 147 */     this.partnerTableId = ProtocolUtil.readUTFBinInt(in);
/* 148 */     this.extend = ProtocolUtil.readUTFStr(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnterGameResponse clone() throws CloneNotSupportedException {
/* 153 */     EnterGameResponse clone = (EnterGameResponse)super.clone();
/* 154 */     int size_0 = this.attributes.size();
/* 155 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 157 */       long tmp_0 = ((Long)this.attributes.get(index_0)).longValue();
/* 158 */       clone.attributes.add(Long.valueOf(tmp_0));
/*     */     } 
/* 160 */     int size_1 = this.currencies.size();
/* 161 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 163 */       long tmp_1 = ((Long)this.currencies.get(index_1)).longValue();
/* 164 */       clone.currencies.add(Long.valueOf(tmp_1));
/*     */     } 
/* 166 */     int size_2 = this.functionIds.size();
/* 167 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 169 */       int tmp_2 = ((Integer)this.functionIds.get(index_2)).intValue();
/* 170 */       clone.functionIds.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 172 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 177 */     this.playerId = 0L;
/* 178 */     this.playerName = null;
/* 179 */     this.timestamp = 0;
/* 180 */     this.hp = 0L;
/* 181 */     this.hpMax = 0L;
/* 182 */     this.fightValue = 0L;
/* 183 */     this.totalValue = 0L;
/* 184 */     this.sceneId = 0;
/* 185 */     this.level = 0;
/* 186 */     this.exp = 0L;
/* 187 */     this.head = null;
/* 188 */     this.sex = 0;
/* 189 */     this.attributes.clear();
/* 190 */     this.currencies.clear();
/* 191 */     this.openTime = 0;
/* 192 */     this.createTime = 0;
/* 193 */     this.checksum = null;
/* 194 */     this.nickname = null;
/* 195 */     this.lastSceneId = 0;
/* 196 */     this.functionIds.clear();
/* 197 */     this.quality = 0;
/* 198 */     this.titleId = 0;
/* 199 */     this.fashionId = 0;
/* 200 */     this.mounts = 0;
/* 201 */     this.partnerTableId = 0;
/* 202 */     this.extend = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 207 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"timestamp\":" + this.timestamp + ",\"hp\":" + this.hp + ",\"hpMax\":" + this.hpMax + ",\"fightValue\":" + this.fightValue + ",\"totalValue\":" + this.totalValue + ",\"sceneId\":" + this.sceneId + ",\"level\":" + this.level + ",\"exp\":" + this.exp + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"sex\":" + this.sex + ",\"attributes\":" + this.attributes.toString() + ",\"currencies\":" + this.currencies.toString() + ",\"openTime\":" + this.openTime + ",\"createTime\":" + this.createTime + ",\"checksum\":" + StringUtil.str2Str(this.checksum) + ",\"nickname\":" + StringUtil.str2Str(this.nickname) + ",\"lastSceneId\":" + this.lastSceneId + ",\"functionIds\":" + this.functionIds.toString() + ",\"quality\":" + this.quality + ",\"titleId\":" + this.titleId + ",\"fashionId\":" + this.fashionId + ",\"mounts\":" + this.mounts + ",\"partnerTableId\":" + this.partnerTableId + ",\"extend\":" + StringUtil.str2Str(this.extend) + "}";
/* 208 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\EnterGameResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */