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
/*    */ public class ResultPlayData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public byte targetGuid;
/*    */   public byte targeAction;
/*    */   public int hp;
/* 18 */   public ArrayList<Integer> atomData = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 22 */     ProtocolUtil.writeByte(out, this.targetGuid);
/* 23 */     ProtocolUtil.writeByte(out, this.targeAction);
/* 24 */     ProtocolUtil.writeInt(out, this.hp);
/*    */     
/* 26 */     int size_0 = this.atomData.size();
/* 27 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 28 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 30 */       int tmp_0 = ((Integer)this.atomData.get(index_0)).intValue();
/* 31 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.targetGuid = ProtocolUtil.readUTFBinByte(in);
/* 38 */     this.targeAction = ProtocolUtil.readUTFBinByte(in);
/* 39 */     this.hp = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 41 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 45 */       this.atomData.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ResultPlayData clone() throws CloneNotSupportedException {
/* 51 */     ResultPlayData clone = (ResultPlayData)super.clone();
/* 52 */     int size_0 = this.atomData.size();
/* 53 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 55 */       int tmp_0 = ((Integer)this.atomData.get(index_0)).intValue();
/* 56 */       clone.atomData.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 58 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 63 */     this.targetGuid = 0;
/* 64 */     this.targeAction = 0;
/* 65 */     this.hp = 0;
/* 66 */     this.atomData.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 71 */     String retVal = "{\"targetGuid\":" + this.targetGuid + ",\"targeAction\":" + this.targeAction + ",\"hp\":" + this.hp + ",\"atomData\":" + this.atomData.toString() + "}";
/* 72 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ResultPlayData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */