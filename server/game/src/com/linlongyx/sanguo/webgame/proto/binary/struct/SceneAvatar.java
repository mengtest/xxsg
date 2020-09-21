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
/*    */ public class SceneAvatar
/*    */ {
/*    */   public long playerId;
/*    */   public byte type;
/*    */   public int objectId;
/*    */   public int job;
/*    */   public long hp;
/*    */   public long hpMax;
/*    */   public String name;
/*    */   public short level;
/*    */   public short posx;
/*    */   public short posy;
/*    */   public byte direction;
/*    */   public short flexPosx;
/*    */   public short flexPosy;
/*    */   public byte active;
/*    */   public byte sex;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeLong(out, this.playerId);
/* 31 */     ProtocolUtil.writeByte(out, this.type);
/* 32 */     ProtocolUtil.writeInt(out, this.objectId);
/* 33 */     ProtocolUtil.writeInt(out, this.job);
/* 34 */     ProtocolUtil.writeLong(out, this.hp);
/* 35 */     ProtocolUtil.writeLong(out, this.hpMax);
/* 36 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 37 */     ProtocolUtil.writeShort(out, this.level);
/* 38 */     ProtocolUtil.writeShort(out, this.posx);
/* 39 */     ProtocolUtil.writeShort(out, this.posy);
/* 40 */     ProtocolUtil.writeByte(out, this.direction);
/* 41 */     ProtocolUtil.writeShort(out, this.flexPosx);
/* 42 */     ProtocolUtil.writeShort(out, this.flexPosy);
/* 43 */     ProtocolUtil.writeByte(out, this.active);
/* 44 */     ProtocolUtil.writeByte(out, this.sex);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 50 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 51 */     this.objectId = ProtocolUtil.readUTFBinInt(in);
/* 52 */     this.job = ProtocolUtil.readUTFBinInt(in);
/* 53 */     this.hp = ProtocolUtil.readUTFBinLong(in);
/* 54 */     this.hpMax = ProtocolUtil.readUTFBinLong(in);
/* 55 */     this.name = ProtocolUtil.readUTFStr(in);
/* 56 */     this.level = ProtocolUtil.readUTFBinShort(in);
/* 57 */     this.posx = ProtocolUtil.readUTFBinShort(in);
/* 58 */     this.posy = ProtocolUtil.readUTFBinShort(in);
/* 59 */     this.direction = ProtocolUtil.readUTFBinByte(in);
/* 60 */     this.flexPosx = ProtocolUtil.readUTFBinShort(in);
/* 61 */     this.flexPosy = ProtocolUtil.readUTFBinShort(in);
/* 62 */     this.active = ProtocolUtil.readUTFBinByte(in);
/* 63 */     this.sex = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SceneAvatar clone() throws CloneNotSupportedException {
/* 68 */     SceneAvatar clone = (SceneAvatar)super.clone();
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.playerId = 0L;
/* 75 */     this.type = 0;
/* 76 */     this.objectId = 0;
/* 77 */     this.job = 0;
/* 78 */     this.hp = 0L;
/* 79 */     this.hpMax = 0L;
/* 80 */     this.name = null;
/* 81 */     this.level = 0;
/* 82 */     this.posx = 0;
/* 83 */     this.posy = 0;
/* 84 */     this.direction = 0;
/* 85 */     this.flexPosx = 0;
/* 86 */     this.flexPosy = 0;
/* 87 */     this.active = 0;
/* 88 */     this.sex = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 93 */     String retVal = "{\"playerId\":" + this.playerId + ",\"type\":" + this.type + ",\"objectId\":" + this.objectId + ",\"job\":" + this.job + ",\"hp\":" + this.hp + ",\"hpMax\":" + this.hpMax + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"level\":" + this.level + ",\"posx\":" + this.posx + ",\"posy\":" + this.posy + ",\"direction\":" + this.direction + ",\"flexPosx\":" + this.flexPosx + ",\"flexPosy\":" + this.flexPosy + ",\"active\":" + this.active + ",\"sex\":" + this.sex + "}";
/* 94 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\SceneAvatar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */