/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GeneralPointData
/*    */ {
/*    */   public int insId;
/*    */   public int point;
/*    */   public int star;
/*    */   public int time;
/*    */   public int reset;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     ProtocolUtil.writeInt(out, this.insId);
/* 20 */     ProtocolUtil.writeInt(out, this.point);
/* 21 */     ProtocolUtil.writeInt(out, this.star);
/* 22 */     ProtocolUtil.writeInt(out, this.time);
/* 23 */     ProtocolUtil.writeInt(out, this.reset);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 28 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.point = ProtocolUtil.readUTFBinInt(in);
/* 30 */     this.star = ProtocolUtil.readUTFBinInt(in);
/* 31 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 32 */     this.reset = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GeneralPointData clone() throws CloneNotSupportedException {
/* 37 */     GeneralPointData clone = (GeneralPointData)super.clone();
/* 38 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 43 */     this.insId = 0;
/* 44 */     this.point = 0;
/* 45 */     this.star = 0;
/* 46 */     this.time = 0;
/* 47 */     this.reset = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     String retVal = "{\"insId\":" + this.insId + ",\"point\":" + this.point + ",\"star\":" + this.star + ",\"time\":" + this.time + ",\"reset\":" + this.reset + "}";
/* 53 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\GeneralPointData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */