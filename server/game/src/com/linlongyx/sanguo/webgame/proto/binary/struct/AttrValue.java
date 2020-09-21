/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttrValue
/*    */ {
/*    */   public byte attrType;
/*    */   public long value;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 16 */     ProtocolUtil.writeByte(out, this.attrType);
/* 17 */     ProtocolUtil.writeLong(out, this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 22 */     this.attrType = ProtocolUtil.readUTFBinByte(in);
/* 23 */     this.value = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public AttrValue clone() throws CloneNotSupportedException {
/* 28 */     AttrValue clone = (AttrValue)super.clone();
/* 29 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 34 */     this.attrType = 0;
/* 35 */     this.value = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     String retVal = "{\"attrType\":" + this.attrType + ",\"value\":" + this.value + "}";
/* 41 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\AttrValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */