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
/*    */ public class RunePageSimpleData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long pageId;
/*    */   public String pageName;
/*    */   public int isRed;
/*    */   public int isActive;
/* 19 */   public ArrayList<Integer> holes = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 23 */     ProtocolUtil.writeLong(out, this.pageId);
/* 24 */     ProtocolUtil.writeUTFBinary(out, this.pageName);
/* 25 */     ProtocolUtil.writeInt(out, this.isRed);
/* 26 */     ProtocolUtil.writeInt(out, this.isActive);
/*    */     
/* 28 */     int size_0 = this.holes.size();
/* 29 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 30 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 32 */       int tmp_0 = ((Integer)this.holes.get(index_0)).intValue();
/* 33 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.pageId = ProtocolUtil.readUTFBinLong(in);
/* 40 */     this.pageName = ProtocolUtil.readUTFStr(in);
/* 41 */     this.isRed = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.isActive = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 44 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 45 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 47 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 48 */       this.holes.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RunePageSimpleData clone() throws CloneNotSupportedException {
/* 54 */     RunePageSimpleData clone = (RunePageSimpleData)super.clone();
/* 55 */     int size_0 = this.holes.size();
/* 56 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 58 */       int tmp_0 = ((Integer)this.holes.get(index_0)).intValue();
/* 59 */       clone.holes.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 61 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 66 */     this.pageId = 0L;
/* 67 */     this.pageName = null;
/* 68 */     this.isRed = 0;
/* 69 */     this.isActive = 0;
/* 70 */     this.holes.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     String retVal = "{\"pageId\":" + this.pageId + ",\"pageName\":" + StringUtil.str2Str(this.pageName) + ",\"isRed\":" + this.isRed + ",\"isActive\":" + this.isActive + ",\"holes\":" + this.holes.toString() + "}";
/* 76 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\RunePageSimpleData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */