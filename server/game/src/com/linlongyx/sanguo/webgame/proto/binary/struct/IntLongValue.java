/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
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
/* 19 */     ProtocolUtil.writeInt(out, this.key);
/* 20 */     ProtocolUtil.writeLong(out, this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 25 */     this.key = ProtocolUtil.readUTFBinInt(in);
/* 26 */     this.value = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public IntLongValue clone() throws CloneNotSupportedException {
/* 31 */     IntLongValue clone = (IntLongValue)super.clone();
/* 32 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.key = 0;
/* 38 */     this.value = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     String retVal = "{\"key\":" + this.key + ",\"value\":" + this.value + "}";
/* 44 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\IntLongValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */