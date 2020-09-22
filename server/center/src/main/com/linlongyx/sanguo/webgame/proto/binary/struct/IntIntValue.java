/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IntIntValue
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int key;
/*    */   public int value;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeInt(out, this.key);
/* 19 */     ProtocolUtil.writeInt(out, this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.key = ProtocolUtil.readUTFBinInt(in);
/* 25 */     this.value = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public IntIntValue clone() throws CloneNotSupportedException {
/* 30 */     IntIntValue clone = (IntIntValue)super.clone();
/* 31 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 36 */     this.key = 0;
/* 37 */     this.value = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     String retVal = "{\"key\":" + this.key + ",\"value\":" + this.value + "}";
/* 43 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\IntIntValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */