/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MonsterData
/*    */   implements Serializable
/*    */ {
/*    */   public long playerId;
/*    */   public int objectId;
/*    */   public long hp;
/*    */   public long hpMax;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     ProtocolUtil.writeLong(out, this.playerId);
/* 20 */     ProtocolUtil.writeInt(out, this.objectId);
/* 21 */     ProtocolUtil.writeLong(out, this.hp);
/* 22 */     ProtocolUtil.writeLong(out, this.hpMax);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 27 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 28 */     this.objectId = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.hp = ProtocolUtil.readUTFBinLong(in);
/* 30 */     this.hpMax = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MonsterData clone() throws CloneNotSupportedException {
/* 35 */     MonsterData clone = (MonsterData)super.clone();
/* 36 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 41 */     this.playerId = 0L;
/* 42 */     this.objectId = 0;
/* 43 */     this.hp = 0L;
/* 44 */     this.hpMax = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "{\"playerId\":" + this.playerId + ",\"objectId\":" + this.objectId + ",\"hp\":" + this.hp + ",\"hpMax\":" + this.hpMax + "}";
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\MonsterData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */