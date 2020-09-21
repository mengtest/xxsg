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
/*    */ public class BossDamageData
/*    */ {
/*    */   public int rank;
/*    */   public long fromId;
/*    */   public String name;
/*    */   public long damage;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     ProtocolUtil.writeInt(out, this.rank);
/* 20 */     ProtocolUtil.writeLong(out, this.fromId);
/* 21 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 22 */     ProtocolUtil.writeLong(out, this.damage);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 27 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 28 */     this.fromId = ProtocolUtil.readUTFBinLong(in);
/* 29 */     this.name = ProtocolUtil.readUTFStr(in);
/* 30 */     this.damage = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BossDamageData clone() throws CloneNotSupportedException {
/* 35 */     BossDamageData clone = (BossDamageData)super.clone();
/* 36 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 41 */     this.rank = 0;
/* 42 */     this.fromId = 0L;
/* 43 */     this.name = null;
/* 44 */     this.damage = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "{\"rank\":" + this.rank + ",\"fromId\":" + this.fromId + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"damage\":" + this.damage + "}";
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\BossDamageData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */