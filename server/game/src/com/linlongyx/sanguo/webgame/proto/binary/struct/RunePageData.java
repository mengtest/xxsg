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
/*    */ public class RunePageData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long pageId;
/*    */   public String pageName;
/* 18 */   public ArrayList<RuneItem> runes = new ArrayList<>();
/*    */   public int isActive;
/* 20 */   public ArrayList<Integer> holes = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 24 */     ProtocolUtil.writeLong(out, this.pageId);
/* 25 */     ProtocolUtil.writeUTFBinary(out, this.pageName);
/*    */     
/* 27 */     int size_0 = this.runes.size();
/* 28 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 29 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 31 */       RuneItem tmp_0 = this.runes.get(index_0);
/* 32 */       tmp_0.serial(out);
/*    */     } 
/* 34 */     ProtocolUtil.writeInt(out, this.isActive);
/*    */     
/* 36 */     int size_1 = this.holes.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 38 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 40 */       int tmp_1 = ((Integer)this.holes.get(index_1)).intValue();
/* 41 */       ProtocolUtil.writeInt(out, tmp_1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 47 */     this.pageId = ProtocolUtil.readUTFBinLong(in);
/* 48 */     this.pageName = ProtocolUtil.readUTFStr(in);
/*    */     
/* 50 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 51 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 53 */       RuneItem tmp_0 = new RuneItem();
/* 54 */       tmp_0.unserial(in);
/* 55 */       this.runes.add(tmp_0);
/*    */     } 
/* 57 */     this.isActive = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 59 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 60 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 62 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/* 63 */       this.holes.add(Integer.valueOf(tmp_1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RunePageData clone() throws CloneNotSupportedException {
/* 69 */     RunePageData clone = (RunePageData)super.clone();
/* 70 */     int size_0 = this.runes.size();
/* 71 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 73 */       RuneItem tmp_0 = this.runes.get(index_0);
/* 74 */       clone.runes.add(tmp_0.clone());
/*    */     } 
/* 76 */     int size_1 = this.holes.size();
/* 77 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 79 */       int tmp_1 = ((Integer)this.holes.get(index_1)).intValue();
/* 80 */       clone.holes.add(Integer.valueOf(tmp_1));
/*    */     } 
/* 82 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 87 */     this.pageId = 0L;
/* 88 */     this.pageName = null;
/* 89 */     this.runes.clear();
/* 90 */     this.isActive = 0;
/* 91 */     this.holes.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 96 */     String retVal = "{\"pageId\":" + this.pageId + ",\"pageName\":" + StringUtil.str2Str(this.pageName) + ",\"runes\":" + this.runes.toString() + ",\"isActive\":" + this.isActive + ",\"holes\":" + this.holes.toString() + "}";
/* 97 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\RunePageData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */