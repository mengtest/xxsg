/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KeyValueList
/*    */   implements Serializable
/*    */ {
/* 14 */   public ArrayList<KeyValue> kvList = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     int size_0 = this.kvList.size();
/* 20 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 21 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 23 */       KeyValue tmp_0 = this.kvList.get(index_0);
/* 24 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 31 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 32 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 34 */       KeyValue tmp_0 = new KeyValue();
/* 35 */       tmp_0.unserial(in);
/* 36 */       this.kvList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public KeyValueList clone() throws CloneNotSupportedException {
/* 42 */     KeyValueList clone = (KeyValueList)super.clone();
/* 43 */     int size_0 = this.kvList.size();
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       KeyValue tmp_0 = this.kvList.get(index_0);
/* 47 */       clone.kvList.add(tmp_0.clone());
/*    */     } 
/* 49 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 54 */     this.kvList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     String retVal = "{\"kvList\":" + this.kvList.toString() + "}";
/* 60 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\KeyValueList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */