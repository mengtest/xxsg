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
/*    */ public class ArtifactData
/*    */ {
/*    */   public int id;
/* 14 */   public ArrayList<KeyValue> attrs = new ArrayList<>();
/* 15 */   public ArrayList<KeyValue> tempAttrs = new ArrayList<>();
/*    */   
/*    */   public int type;
/*    */   public int num;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     ProtocolUtil.writeInt(out, this.id);
/*    */     
/* 23 */     int size_0 = this.attrs.size();
/* 24 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 25 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 27 */       KeyValue tmp_0 = this.attrs.get(index_0);
/* 28 */       tmp_0.serial(out);
/*    */     } 
/*    */     
/* 31 */     int size_1 = this.tempAttrs.size();
/* 32 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 33 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 35 */       KeyValue tmp_1 = this.tempAttrs.get(index_1);
/* 36 */       tmp_1.serial(out);
/*    */     } 
/* 38 */     ProtocolUtil.writeInt(out, this.type);
/* 39 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 44 */     this.id = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 46 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 47 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 49 */       KeyValue tmp_0 = new KeyValue();
/* 50 */       tmp_0.unserial(in);
/* 51 */       this.attrs.add(tmp_0);
/*    */     } 
/*    */     
/* 54 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 57 */       KeyValue tmp_1 = new KeyValue();
/* 58 */       tmp_1.unserial(in);
/* 59 */       this.tempAttrs.add(tmp_1);
/*    */     } 
/* 61 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 62 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ArtifactData clone() throws CloneNotSupportedException {
/* 67 */     ArtifactData clone = (ArtifactData)super.clone();
/* 68 */     int size_0 = this.attrs.size();
/* 69 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 71 */       KeyValue tmp_0 = this.attrs.get(index_0);
/* 72 */       clone.attrs.add(tmp_0.clone());
/*    */     } 
/* 74 */     int size_1 = this.tempAttrs.size();
/* 75 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 77 */       KeyValue tmp_1 = this.tempAttrs.get(index_1);
/* 78 */       clone.tempAttrs.add(tmp_1.clone());
/*    */     } 
/* 80 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 85 */     this.id = 0;
/* 86 */     this.attrs.clear();
/* 87 */     this.tempAttrs.clear();
/* 88 */     this.type = 0;
/* 89 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 94 */     String retVal = "{\"id\":" + this.id + ",\"attrs\":" + this.attrs.toString() + ",\"tempAttrs\":" + this.tempAttrs.toString() + ",\"type\":" + this.type + ",\"num\":" + this.num + "}";
/* 95 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ArtifactData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */