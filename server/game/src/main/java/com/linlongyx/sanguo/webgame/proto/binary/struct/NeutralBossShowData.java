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
/*    */ public class NeutralBossShowData
/*    */   implements Serializable
/*    */ {
/*    */   public long playerId;
/*    */   public int objectId;
/*    */   public long hp;
/*    */   public long hpMax;
/*    */   public int nextTime;
/*    */   public long belongId;
/*    */   public String belongName;
/*    */   public String belongBlocName;
/*    */   public String head;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 25 */     ProtocolUtil.writeLong(out, this.playerId);
/* 26 */     ProtocolUtil.writeInt(out, this.objectId);
/* 27 */     ProtocolUtil.writeLong(out, this.hp);
/* 28 */     ProtocolUtil.writeLong(out, this.hpMax);
/* 29 */     ProtocolUtil.writeInt(out, this.nextTime);
/* 30 */     ProtocolUtil.writeLong(out, this.belongId);
/* 31 */     ProtocolUtil.writeUTFBinary(out, this.belongName);
/* 32 */     ProtocolUtil.writeUTFBinary(out, this.belongBlocName);
/* 33 */     ProtocolUtil.writeUTFBinary(out, this.head);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 39 */     this.objectId = ProtocolUtil.readUTFBinInt(in);
/* 40 */     this.hp = ProtocolUtil.readUTFBinLong(in);
/* 41 */     this.hpMax = ProtocolUtil.readUTFBinLong(in);
/* 42 */     this.nextTime = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.belongId = ProtocolUtil.readUTFBinLong(in);
/* 44 */     this.belongName = ProtocolUtil.readUTFStr(in);
/* 45 */     this.belongBlocName = ProtocolUtil.readUTFStr(in);
/* 46 */     this.head = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public NeutralBossShowData clone() throws CloneNotSupportedException {
/* 51 */     NeutralBossShowData clone = (NeutralBossShowData)super.clone();
/* 52 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 57 */     this.playerId = 0L;
/* 58 */     this.objectId = 0;
/* 59 */     this.hp = 0L;
/* 60 */     this.hpMax = 0L;
/* 61 */     this.nextTime = 0;
/* 62 */     this.belongId = 0L;
/* 63 */     this.belongName = null;
/* 64 */     this.belongBlocName = null;
/* 65 */     this.head = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "{\"playerId\":" + this.playerId + ",\"objectId\":" + this.objectId + ",\"hp\":" + this.hp + ",\"hpMax\":" + this.hpMax + ",\"nextTime\":" + this.nextTime + ",\"belongId\":" + this.belongId + ",\"belongName\":" + StringUtil.str2Str(this.belongName) + ",\"belongBlocName\":" + StringUtil.str2Str(this.belongBlocName) + ",\"head\":" + StringUtil.str2Str(this.head) + "}";
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\NeutralBossShowData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */