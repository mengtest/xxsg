/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IntLongValue
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int key;
/*    */   public long value;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeInt(out, this.key);
/* 19 */     ProtocolUtil.writeLong(out, this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.key = ProtocolUtil.readUTFBinInt(in);
/* 25 */     this.value = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public IntLongValue clone() throws CloneNotSupportedException {
/* 30 */     IntLongValue clone = (IntLongValue)super.clone();
/* 31 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 36 */     this.key = 0;
/* 37 */     this.value = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     String retVal = "{\"key\":" + this.key + ",\"value\":" + this.value + "}";
/* 43 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\IntLongValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */