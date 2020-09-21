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
/*    */ 
/*    */ public class BoutPlayData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public byte waveId;
/*    */   public int roundId;
/* 18 */   public ArrayList<ResultPlayData> buffActions = new ArrayList<>();
/* 19 */   public ArrayList<SkillPlayData> skillDatas = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 23 */     ProtocolUtil.writeByte(out, this.waveId);
/* 24 */     ProtocolUtil.writeInt(out, this.roundId);
/*    */     
/* 26 */     int size_0 = this.buffActions.size();
/* 27 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 28 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 30 */       ResultPlayData tmp_0 = this.buffActions.get(index_0);
/* 31 */       tmp_0.serial(out);
/*    */     } 
/*    */     
/* 34 */     int size_1 = this.skillDatas.size();
/* 35 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 36 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 38 */       SkillPlayData tmp_1 = this.skillDatas.get(index_1);
/* 39 */       tmp_1.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 45 */     this.waveId = ProtocolUtil.readUTFBinByte(in);
/* 46 */     this.roundId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 48 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 51 */       ResultPlayData tmp_0 = new ResultPlayData();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.buffActions.add(tmp_0);
/*    */     } 
/*    */     
/* 56 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 57 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 59 */       SkillPlayData tmp_1 = new SkillPlayData();
/* 60 */       tmp_1.unserial(in);
/* 61 */       this.skillDatas.add(tmp_1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public BoutPlayData clone() throws CloneNotSupportedException {
/* 67 */     BoutPlayData clone = (BoutPlayData)super.clone();
/* 68 */     int size_0 = this.buffActions.size();
/* 69 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 71 */       ResultPlayData tmp_0 = this.buffActions.get(index_0);
/* 72 */       clone.buffActions.add(tmp_0.clone());
/*    */     } 
/* 74 */     int size_1 = this.skillDatas.size();
/* 75 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 77 */       SkillPlayData tmp_1 = this.skillDatas.get(index_1);
/* 78 */       clone.skillDatas.add(tmp_1.clone());
/*    */     } 
/* 80 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 85 */     this.waveId = 0;
/* 86 */     this.roundId = 0;
/* 87 */     this.buffActions.clear();
/* 88 */     this.skillDatas.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 93 */     String retVal = "{\"waveId\":" + this.waveId + ",\"roundId\":" + this.roundId + ",\"buffActions\":" + this.buffActions.toString() + ",\"skillDatas\":" + this.skillDatas.toString() + "}";
/* 94 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\BoutPlayData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */