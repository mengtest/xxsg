/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BuffData
/*    */ {
/*    */   public int buffId;
/* 13 */   public ArrayList<Integer> atomData = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeInt(out, this.buffId);
/*    */     
/* 19 */     int size_0 = this.atomData.size();
/* 20 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 21 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 23 */       int tmp_0 = ((Integer)this.atomData.get(index_0)).intValue();
/* 24 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 30 */     this.buffId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 32 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 33 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 35 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 36 */       this.atomData.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public BuffData clone() throws CloneNotSupportedException {
/* 42 */     BuffData clone = (BuffData)super.clone();
/* 43 */     int size_0 = this.atomData.size();
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       int tmp_0 = ((Integer)this.atomData.get(index_0)).intValue();
/* 47 */       clone.atomData.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 49 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 54 */     this.buffId = 0;
/* 55 */     this.atomData.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     String retVal = "{\"buffId\":" + this.buffId + ",\"atomData\":" + this.atomData.toString() + "}";
/* 61 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\BuffData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */