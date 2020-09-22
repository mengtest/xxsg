/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LimitData
/*    */ {
/*    */   public int itemId;
/*    */   public int num;
/*    */   public long value;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeInt(out, this.itemId);
/* 18 */     ProtocolUtil.writeInt(out, this.num);
/* 19 */     ProtocolUtil.writeLong(out, this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 25 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 26 */     this.value = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LimitData clone() throws CloneNotSupportedException {
/* 31 */     LimitData clone = (LimitData)super.clone();
/* 32 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.itemId = 0;
/* 38 */     this.num = 0;
/* 39 */     this.value = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "{\"itemId\":" + this.itemId + ",\"num\":" + this.num + ",\"value\":" + this.value + "}";
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\LimitData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */