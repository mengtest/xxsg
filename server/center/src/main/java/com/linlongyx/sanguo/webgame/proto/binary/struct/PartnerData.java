/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PartnerData
/*    */ {
/*    */   public int fid;
/*    */   public int step;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 15 */     ProtocolUtil.writeInt(out, this.fid);
/* 16 */     ProtocolUtil.writeInt(out, this.step);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 21 */     this.fid = ProtocolUtil.readUTFBinInt(in);
/* 22 */     this.step = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PartnerData clone() throws CloneNotSupportedException {
/* 27 */     PartnerData clone = (PartnerData)super.clone();
/* 28 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 33 */     this.fid = 0;
/* 34 */     this.step = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     String retVal = "{\"fid\":" + this.fid + ",\"step\":" + this.step + "}";
/* 40 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\PartnerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */