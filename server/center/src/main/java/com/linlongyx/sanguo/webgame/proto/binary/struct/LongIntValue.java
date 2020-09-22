/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
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
/* 15 */     ProtocolUtil.writeLong(out, this.key);
/* 16 */     ProtocolUtil.writeInt(out, this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 21 */     this.key = ProtocolUtil.readUTFBinLong(in);
/* 22 */     this.value = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LongIntValue clone() throws CloneNotSupportedException {
/* 27 */     LongIntValue clone = (LongIntValue)super.clone();
/* 28 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 33 */     this.key = 0L;
/* 34 */     this.value = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     String retVal = "{\"key\":" + this.key + ",\"value\":" + this.value + "}";
/* 40 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\LongIntValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */