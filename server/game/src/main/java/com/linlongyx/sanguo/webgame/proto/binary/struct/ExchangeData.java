/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExchangeData
/*    */ {
/*    */   public int id;
/*    */   public int type;
/*    */   public int itemId;
/*    */   public long num;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeInt(out, this.id);
/* 18 */     ProtocolUtil.writeInt(out, this.type);
/* 19 */     ProtocolUtil.writeInt(out, this.itemId);
/* 20 */     ProtocolUtil.writeLong(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 25 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 26 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 27 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 28 */     this.num = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ExchangeData clone() throws CloneNotSupportedException {
/* 33 */     ExchangeData clone = (ExchangeData)super.clone();
/* 34 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 39 */     this.id = 0;
/* 40 */     this.type = 0;
/* 41 */     this.itemId = 0;
/* 42 */     this.num = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     String retVal = "{\"id\":" + this.id + ",\"type\":" + this.type + ",\"itemId\":" + this.itemId + ",\"num\":" + this.num + "}";
/* 48 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ExchangeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */