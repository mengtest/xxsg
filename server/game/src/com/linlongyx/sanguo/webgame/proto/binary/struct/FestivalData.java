/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FestivalData
/*    */   implements Serializable
/*    */ {
/*    */   public int itemId;
/*    */   public int num;
/*    */   public long value;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeInt(out, this.itemId);
/* 19 */     ProtocolUtil.writeInt(out, this.num);
/* 20 */     ProtocolUtil.writeLong(out, this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 25 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 26 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 27 */     this.value = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FestivalData clone() throws CloneNotSupportedException {
/* 32 */     FestivalData clone = (FestivalData)super.clone();
/* 33 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 38 */     this.itemId = 0;
/* 39 */     this.num = 0;
/* 40 */     this.value = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "{\"itemId\":" + this.itemId + ",\"num\":" + this.num + ",\"value\":" + this.value + "}";
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\FestivalData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */