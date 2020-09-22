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
/*    */ public class PlayerDamageData
/*    */   implements Serializable
/*    */ {
/*    */   public int rank;
/*    */   public long fromId;
/*    */   public String name;
/*    */   public int damage;
/*    */   public int startTime;
/*    */   public long hp;
/*    */   public long hpMax;
/*    */   public String head;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 24 */     ProtocolUtil.writeInt(out, this.rank);
/* 25 */     ProtocolUtil.writeLong(out, this.fromId);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 27 */     ProtocolUtil.writeInt(out, this.damage);
/* 28 */     ProtocolUtil.writeInt(out, this.startTime);
/* 29 */     ProtocolUtil.writeLong(out, this.hp);
/* 30 */     ProtocolUtil.writeLong(out, this.hpMax);
/* 31 */     ProtocolUtil.writeUTFBinary(out, this.head);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 37 */     this.fromId = ProtocolUtil.readUTFBinLong(in);
/* 38 */     this.name = ProtocolUtil.readUTFStr(in);
/* 39 */     this.damage = ProtocolUtil.readUTFBinInt(in);
/* 40 */     this.startTime = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.hp = ProtocolUtil.readUTFBinLong(in);
/* 42 */     this.hpMax = ProtocolUtil.readUTFBinLong(in);
/* 43 */     this.head = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerDamageData clone() throws CloneNotSupportedException {
/* 48 */     PlayerDamageData clone = (PlayerDamageData)super.clone();
/* 49 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 54 */     this.rank = 0;
/* 55 */     this.fromId = 0L;
/* 56 */     this.name = null;
/* 57 */     this.damage = 0;
/* 58 */     this.startTime = 0;
/* 59 */     this.hp = 0L;
/* 60 */     this.hpMax = 0L;
/* 61 */     this.head = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     String retVal = "{\"rank\":" + this.rank + ",\"fromId\":" + this.fromId + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"damage\":" + this.damage + ",\"startTime\":" + this.startTime + ",\"hp\":" + this.hp + ",\"hpMax\":" + this.hpMax + ",\"head\":" + StringUtil.str2Str(this.head) + "}";
/* 67 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\PlayerDamageData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */