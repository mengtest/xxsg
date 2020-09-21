/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ActivityItemInfo
/*    */ {
/*    */   public int itemId;
/*    */   public int end;
/*    */   public int num;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeInt(out, this.itemId);
/* 18 */     ProtocolUtil.writeInt(out, this.end);
/* 19 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 25 */     this.end = ProtocolUtil.readUTFBinInt(in);
/* 26 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ActivityItemInfo clone() throws CloneNotSupportedException {
/* 31 */     ActivityItemInfo clone = (ActivityItemInfo)super.clone();
/* 32 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.itemId = 0;
/* 38 */     this.end = 0;
/* 39 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "{\"itemId\":" + this.itemId + ",\"end\":" + this.end + ",\"num\":" + this.num + "}";
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ActivityItemInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */