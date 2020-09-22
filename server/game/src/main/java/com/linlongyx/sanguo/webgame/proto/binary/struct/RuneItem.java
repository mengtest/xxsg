/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RuneItem
/*    */ {
/*    */   public long mid;
/*    */   public int itemId;
/*    */   public int level;
/*    */   public int hole;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeLong(out, this.mid);
/* 19 */     ProtocolUtil.writeInt(out, this.itemId);
/* 20 */     ProtocolUtil.writeInt(out, this.level);
/* 21 */     ProtocolUtil.writeInt(out, this.hole);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 26 */     this.mid = ProtocolUtil.readUTFBinLong(in);
/* 27 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 28 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.hole = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RuneItem clone() throws CloneNotSupportedException {
/* 34 */     RuneItem clone = (RuneItem)super.clone();
/* 35 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 40 */     this.mid = 0L;
/* 41 */     this.itemId = 0;
/* 42 */     this.level = 0;
/* 43 */     this.hole = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     String retVal = "{\"mid\":" + this.mid + ",\"itemId\":" + this.itemId + ",\"level\":" + this.level + ",\"hole\":" + this.hole + "}";
/* 49 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\RuneItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */