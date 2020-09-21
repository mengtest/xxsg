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
/*    */ public class SanGuoZhiData
/*    */ {
/*    */   public int recordStar;
/* 14 */   public ArrayList<KeyValue> values = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeInt(out, this.recordStar);
/*    */     
/* 20 */     int size_0 = this.values.size();
/* 21 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 22 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 24 */       KeyValue tmp_0 = this.values.get(index_0);
/* 25 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 31 */     this.recordStar = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 33 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       KeyValue tmp_0 = new KeyValue();
/* 37 */       tmp_0.unserial(in);
/* 38 */       this.values.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public SanGuoZhiData clone() throws CloneNotSupportedException {
/* 44 */     SanGuoZhiData clone = (SanGuoZhiData)super.clone();
/* 45 */     int size_0 = this.values.size();
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       KeyValue tmp_0 = this.values.get(index_0);
/* 49 */       clone.values.add(tmp_0.clone());
/*    */     } 
/* 51 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 56 */     this.recordStar = 0;
/* 57 */     this.values.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"recordStar\":" + this.recordStar + ",\"values\":" + this.values.toString() + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\SanGuoZhiData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */