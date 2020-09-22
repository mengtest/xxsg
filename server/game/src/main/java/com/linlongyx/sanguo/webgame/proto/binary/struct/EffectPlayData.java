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
/*    */ 
/*    */ public class EffectPlayData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int effectId;
/*    */   public byte guid;
/* 19 */   public ArrayList<ResultPlayData> resultData = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 23 */     ProtocolUtil.writeInt(out, this.effectId);
/* 24 */     ProtocolUtil.writeByte(out, this.guid);
/*    */     
/* 26 */     int size_0 = this.resultData.size();
/* 27 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 28 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 30 */       ResultPlayData tmp_0 = this.resultData.get(index_0);
/* 31 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.effectId = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.guid = ProtocolUtil.readUTFBinByte(in);
/*    */     
/* 40 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 43 */       ResultPlayData tmp_0 = new ResultPlayData();
/* 44 */       tmp_0.unserial(in);
/* 45 */       this.resultData.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public EffectPlayData clone() throws CloneNotSupportedException {
/* 51 */     EffectPlayData clone = (EffectPlayData)super.clone();
/* 52 */     int size_0 = this.resultData.size();
/* 53 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 55 */       ResultPlayData tmp_0 = this.resultData.get(index_0);
/* 56 */       clone.resultData.add(tmp_0.clone());
/*    */     } 
/* 58 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 63 */     this.effectId = 0;
/* 64 */     this.guid = 0;
/* 65 */     this.resultData.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "{\"effectId\":" + this.effectId + ",\"guid\":" + this.guid + ",\"resultData\":" + this.resultData.toString() + "}";
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\EffectPlayData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */