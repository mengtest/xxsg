/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
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
/* 18 */     ProtocolUtil.writeInt(out, this.rank);
/* 19 */     ProtocolUtil.writeLong(out, this.fromId);
/* 20 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 21 */     ProtocolUtil.writeLong(out, this.damage);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 26 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 27 */     this.fromId = ProtocolUtil.readUTFBinLong(in);
/* 28 */     this.name = ProtocolUtil.readUTFStr(in);
/* 29 */     this.damage = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BossDamageData clone() throws CloneNotSupportedException {
/* 34 */     BossDamageData clone = (BossDamageData)super.clone();
/* 35 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 40 */     this.rank = 0;
/* 41 */     this.fromId = 0L;
/* 42 */     this.name = null;
/* 43 */     this.damage = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     String retVal = "{\"rank\":" + this.rank + ",\"fromId\":" + this.fromId + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"damage\":" + this.damage + "}";
/* 49 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\BossDamageData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */