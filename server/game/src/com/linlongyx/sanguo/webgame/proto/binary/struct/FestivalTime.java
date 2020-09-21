/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FestivalTime
/*    */ {
/*    */   public int actId;
/*    */   public int startTime;
/*    */   public int endTime;
/*    */   public int closeTime;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeInt(out, this.actId);
/* 19 */     ProtocolUtil.writeInt(out, this.startTime);
/* 20 */     ProtocolUtil.writeInt(out, this.endTime);
/* 21 */     ProtocolUtil.writeInt(out, this.closeTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 26 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 27 */     this.startTime = ProtocolUtil.readUTFBinInt(in);
/* 28 */     this.endTime = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.closeTime = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FestivalTime clone() throws CloneNotSupportedException {
/* 34 */     FestivalTime clone = (FestivalTime)super.clone();
/* 35 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 40 */     this.actId = 0;
/* 41 */     this.startTime = 0;
/* 42 */     this.endTime = 0;
/* 43 */     this.closeTime = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     String retVal = "{\"actId\":" + this.actId + ",\"startTime\":" + this.startTime + ",\"endTime\":" + this.endTime + ",\"closeTime\":" + this.closeTime + "}";
/* 49 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\FestivalTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */