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
/*    */ public class TrainData
/*    */ {
/*    */   public int id;
/*    */   public boolean recommend;
/* 15 */   public ArrayList<KeyValue> attrs = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     ProtocolUtil.writeInt(out, this.id);
/* 20 */     out.writeBoolean(this.recommend);
/*    */     
/* 22 */     int size_0 = this.attrs.size();
/* 23 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 24 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 26 */       KeyValue tmp_0 = this.attrs.get(index_0);
/* 27 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 34 */     this.recommend = in.readBoolean();
/*    */     
/* 36 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 37 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 39 */       KeyValue tmp_0 = new KeyValue();
/* 40 */       tmp_0.unserial(in);
/* 41 */       this.attrs.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public TrainData clone() throws CloneNotSupportedException {
/* 47 */     TrainData clone = (TrainData)super.clone();
/* 48 */     int size_0 = this.attrs.size();
/* 49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 51 */       KeyValue tmp_0 = this.attrs.get(index_0);
/* 52 */       clone.attrs.add(tmp_0.clone());
/*    */     } 
/* 54 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 59 */     this.id = 0;
/* 60 */     this.recommend = false;
/* 61 */     this.attrs.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     String retVal = "{\"id\":" + this.id + ",\"recommend\":" + this.recommend + ",\"attrs\":" + this.attrs.toString() + "}";
/* 67 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\TrainData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */