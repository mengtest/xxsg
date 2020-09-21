/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InvitationData
/*    */ {
/*    */   public int value;
/* 13 */   public ArrayList<Long> rewards = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeInt(out, this.value);
/*    */     
/* 19 */     int size_0 = this.rewards.size();
/* 20 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 21 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 23 */       long tmp_0 = ((Long)this.rewards.get(index_0)).longValue();
/* 24 */       ProtocolUtil.writeLong(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 30 */     this.value = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 32 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 33 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 35 */       long tmp_0 = ProtocolUtil.readUTFBinLong(in);
/* 36 */       this.rewards.add(Long.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public InvitationData clone() throws CloneNotSupportedException {
/* 42 */     InvitationData clone = (InvitationData)super.clone();
/* 43 */     int size_0 = this.rewards.size();
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       long tmp_0 = ((Long)this.rewards.get(index_0)).longValue();
/* 47 */       clone.rewards.add(Long.valueOf(tmp_0));
/*    */     } 
/* 49 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 54 */     this.value = 0;
/* 55 */     this.rewards.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     String retVal = "{\"value\":" + this.value + ",\"rewards\":" + this.rewards.toString() + "}";
/* 61 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\InvitationData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */