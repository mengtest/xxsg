/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CardPlayRewardData
/*    */ {
/*    */   public int id;
/*    */   public int rewardTimes;
/*    */   public byte status;
/*    */   public int progress;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeInt(out, this.id);
/* 18 */     ProtocolUtil.writeInt(out, this.rewardTimes);
/* 19 */     ProtocolUtil.writeByte(out, this.status);
/* 20 */     ProtocolUtil.writeInt(out, this.progress);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 25 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 26 */     this.rewardTimes = ProtocolUtil.readUTFBinInt(in);
/* 27 */     this.status = ProtocolUtil.readUTFBinByte(in);
/* 28 */     this.progress = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CardPlayRewardData clone() throws CloneNotSupportedException {
/* 33 */     CardPlayRewardData clone = (CardPlayRewardData)super.clone();
/* 34 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 39 */     this.id = 0;
/* 40 */     this.rewardTimes = 0;
/* 41 */     this.status = 0;
/* 42 */     this.progress = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     String retVal = "{\"id\":" + this.id + ",\"rewardTimes\":" + this.rewardTimes + ",\"status\":" + this.status + ",\"progress\":" + this.progress + "}";
/* 48 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\CardPlayRewardData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */