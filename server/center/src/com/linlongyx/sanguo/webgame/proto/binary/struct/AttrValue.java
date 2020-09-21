/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
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
/* 15 */     ProtocolUtil.writeByte(out, this.attrType);
/* 16 */     ProtocolUtil.writeLong(out, this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 21 */     this.attrType = ProtocolUtil.readUTFBinByte(in);
/* 22 */     this.value = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public AttrValue clone() throws CloneNotSupportedException {
/* 27 */     AttrValue clone = (AttrValue)super.clone();
/* 28 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 33 */     this.attrType = 0;
/* 34 */     this.value = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     String retVal = "{\"attrType\":" + this.attrType + ",\"value\":" + this.value + "}";
/* 40 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\AttrValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */