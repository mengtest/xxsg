/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MyRecruit
/*    */ {
/* 13 */   public ArrayList<Reward> rewards = new ArrayList<>();
/*    */   
/*    */   public int createTime;
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     int size_0 = this.rewards.size();
/* 20 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 21 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 23 */       Reward tmp_0 = this.rewards.get(index_0);
/* 24 */       tmp_0.serial(out);
/*    */     } 
/* 26 */     ProtocolUtil.writeInt(out, this.createTime);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 32 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 33 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 35 */       Reward tmp_0 = new Reward();
/* 36 */       tmp_0.unserial(in);
/* 37 */       this.rewards.add(tmp_0);
/*    */     } 
/* 39 */     this.createTime = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MyRecruit clone() throws CloneNotSupportedException {
/* 44 */     MyRecruit clone = (MyRecruit)super.clone();
/* 45 */     int size_0 = this.rewards.size();
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       Reward tmp_0 = this.rewards.get(index_0);
/* 49 */       clone.rewards.add(tmp_0.clone());
/*    */     } 
/* 51 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 56 */     this.rewards.clear();
/* 57 */     this.createTime = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"rewards\":" + this.rewards.toString() + ",\"createTime\":" + this.createTime + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\MyRecruit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */