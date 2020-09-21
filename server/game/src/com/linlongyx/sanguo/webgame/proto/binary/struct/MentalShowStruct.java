/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MentalShowStruct
/*    */   implements Serializable
/*    */ {
/*    */   public String playerName;
/* 16 */   public ArrayList<Reward> rewards = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/*    */     
/* 22 */     int size_0 = this.rewards.size();
/* 23 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 24 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 26 */       Reward tmp_0 = this.rewards.get(index_0);
/* 27 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.playerName = ProtocolUtil.readUTFStr(in);
/*    */     
/* 35 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 36 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 38 */       Reward tmp_0 = new Reward();
/* 39 */       tmp_0.unserial(in);
/* 40 */       this.rewards.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public MentalShowStruct clone() throws CloneNotSupportedException {
/* 46 */     MentalShowStruct clone = (MentalShowStruct)super.clone();
/* 47 */     int size_0 = this.rewards.size();
/* 48 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 50 */       Reward tmp_0 = this.rewards.get(index_0);
/* 51 */       clone.rewards.add(tmp_0.clone());
/*    */     } 
/* 53 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 58 */     this.playerName = null;
/* 59 */     this.rewards.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 64 */     String retVal = "{\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"rewards\":" + this.rewards.toString() + "}";
/* 65 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\MentalShowStruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */