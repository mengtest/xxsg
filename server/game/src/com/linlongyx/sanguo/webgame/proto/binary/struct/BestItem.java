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
/*    */ public class BestItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long mid;
/*    */   public int itemId;
/* 17 */   public ArrayList<AttrKey> attrs = new ArrayList<>();
/*    */   
/*    */   public int end;
/*    */   public int num;
/*    */   public boolean used;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 24 */     ProtocolUtil.writeLong(out, this.mid);
/* 25 */     ProtocolUtil.writeInt(out, this.itemId);
/*    */     
/* 27 */     int size_0 = this.attrs.size();
/* 28 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 29 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 31 */       AttrKey tmp_0 = this.attrs.get(index_0);
/* 32 */       tmp_0.serial(out);
/*    */     } 
/* 34 */     ProtocolUtil.writeInt(out, this.end);
/* 35 */     ProtocolUtil.writeInt(out, this.num);
/* 36 */     out.writeBoolean(this.used);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.mid = ProtocolUtil.readUTFBinLong(in);
/* 42 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 44 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 45 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 47 */       AttrKey tmp_0 = new AttrKey();
/* 48 */       tmp_0.unserial(in);
/* 49 */       this.attrs.add(tmp_0);
/*    */     } 
/* 51 */     this.end = ProtocolUtil.readUTFBinInt(in);
/* 52 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 53 */     this.used = in.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public BestItem clone() throws CloneNotSupportedException {
/* 58 */     BestItem clone = (BestItem)super.clone();
/* 59 */     int size_0 = this.attrs.size();
/* 60 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 62 */       AttrKey tmp_0 = this.attrs.get(index_0);
/* 63 */       clone.attrs.add(tmp_0.clone());
/*    */     } 
/* 65 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 70 */     this.mid = 0L;
/* 71 */     this.itemId = 0;
/* 72 */     this.attrs.clear();
/* 73 */     this.end = 0;
/* 74 */     this.num = 0;
/* 75 */     this.used = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "{\"mid\":" + this.mid + ",\"itemId\":" + this.itemId + ",\"attrs\":" + this.attrs.toString() + ",\"end\":" + this.end + ",\"num\":" + this.num + ",\"used\":" + this.used + "}";
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\BestItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */