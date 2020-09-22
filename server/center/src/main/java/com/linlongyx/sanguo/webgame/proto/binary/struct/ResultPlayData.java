/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
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
/* 17 */   public ArrayList<Integer> atomData = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     ProtocolUtil.writeByte(out, this.targetGuid);
/* 22 */     ProtocolUtil.writeByte(out, this.targeAction);
/* 23 */     ProtocolUtil.writeInt(out, this.hp);
/*    */     
/* 25 */     int size_0 = this.atomData.size();
/* 26 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 27 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 29 */       int tmp_0 = ((Integer)this.atomData.get(index_0)).intValue();
/* 30 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.targetGuid = ProtocolUtil.readUTFBinByte(in);
/* 37 */     this.targeAction = ProtocolUtil.readUTFBinByte(in);
/* 38 */     this.hp = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 40 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 43 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 44 */       this.atomData.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ResultPlayData clone() throws CloneNotSupportedException {
/* 50 */     ResultPlayData clone = (ResultPlayData)super.clone();
/* 51 */     int size_0 = this.atomData.size();
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       int tmp_0 = ((Integer)this.atomData.get(index_0)).intValue();
/* 55 */       clone.atomData.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 57 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 62 */     this.targetGuid = 0;
/* 63 */     this.targeAction = 0;
/* 64 */     this.hp = 0;
/* 65 */     this.atomData.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "{\"targetGuid\":" + this.targetGuid + ",\"targeAction\":" + this.targeAction + ",\"hp\":" + this.hp + ",\"atomData\":" + this.atomData.toString() + "}";
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ResultPlayData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */