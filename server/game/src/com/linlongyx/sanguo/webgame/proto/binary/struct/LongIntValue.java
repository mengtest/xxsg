/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LongIntValue
/*    */ {
/*    */   public long key;
/*    */   public int value;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 16 */     ProtocolUtil.writeLong(out, this.key);
/* 17 */     ProtocolUtil.writeInt(out, this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 22 */     this.key = ProtocolUtil.readUTFBinLong(in);
/* 23 */     this.value = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LongIntValue clone() throws CloneNotSupportedException {
/* 28 */     LongIntValue clone = (LongIntValue)super.clone();
/* 29 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 34 */     this.key = 0L;
/* 35 */     this.value = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     String retVal = "{\"key\":" + this.key + ",\"value\":" + this.value + "}";
/* 41 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\LongIntValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */