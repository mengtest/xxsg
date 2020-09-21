/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MyMentalStruct
/*    */   implements Serializable
/*    */ {
/* 13 */   public ArrayList<Reward> rewards = new ArrayList<>();
/*    */   
/*    */   public int createTime;
/*    */   
/*    */   public int point;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     int size_0 = this.rewards.size();
/* 21 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 22 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 24 */       Reward tmp_0 = this.rewards.get(index_0);
/* 25 */       tmp_0.serial(out);
/*    */     } 
/* 27 */     ProtocolUtil.writeInt(out, this.createTime);
/* 28 */     ProtocolUtil.writeInt(out, this.point);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 35 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 37 */       Reward tmp_0 = new Reward();
/* 38 */       tmp_0.unserial(in);
/* 39 */       this.rewards.add(tmp_0);
/*    */     } 
/* 41 */     this.createTime = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.point = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MyMentalStruct clone() throws CloneNotSupportedException {
/* 47 */     MyMentalStruct clone = (MyMentalStruct)super.clone();
/* 48 */     int size_0 = this.rewards.size();
/* 49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 51 */       Reward tmp_0 = this.rewards.get(index_0);
/* 52 */       clone.rewards.add(tmp_0.clone());
/*    */     } 
/* 54 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 59 */     this.rewards.clear();
/* 60 */     this.createTime = 0;
/* 61 */     this.point = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     String retVal = "{\"rewards\":" + this.rewards.toString() + ",\"createTime\":" + this.createTime + ",\"point\":" + this.point + "}";
/* 67 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\MyMentalStruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */