/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BagItemInfo
/*    */ {
/*    */   public int itemId;
/*    */   public int num;
/*    */   public int status;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeInt(out, this.itemId);
/* 18 */     ProtocolUtil.writeInt(out, this.num);
/* 19 */     ProtocolUtil.writeInt(out, this.status);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 25 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 26 */     this.status = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BagItemInfo clone() throws CloneNotSupportedException {
/* 31 */     BagItemInfo clone = (BagItemInfo)super.clone();
/* 32 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.itemId = 0;
/* 38 */     this.num = 0;
/* 39 */     this.status = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "{\"itemId\":" + this.itemId + ",\"num\":" + this.num + ",\"status\":" + this.status + "}";
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\BagItemInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */