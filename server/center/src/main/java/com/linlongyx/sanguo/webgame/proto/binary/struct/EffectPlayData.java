/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EffectPlayData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int effectId;
/*    */   public byte guid;
/* 16 */   public ArrayList<ResultPlayData> resultData = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeInt(out, this.effectId);
/* 21 */     ProtocolUtil.writeByte(out, this.guid);
/*    */     
/* 23 */     int size_0 = this.resultData.size();
/* 24 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 25 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 27 */       ResultPlayData tmp_0 = this.resultData.get(index_0);
/* 28 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.effectId = ProtocolUtil.readUTFBinInt(in);
/* 35 */     this.guid = ProtocolUtil.readUTFBinByte(in);
/*    */     
/* 37 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       ResultPlayData tmp_0 = new ResultPlayData();
/* 41 */       tmp_0.unserial(in);
/* 42 */       this.resultData.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public EffectPlayData clone() throws CloneNotSupportedException {
/* 48 */     EffectPlayData clone = (EffectPlayData)super.clone();
/* 49 */     int size_0 = this.resultData.size();
/* 50 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 52 */       ResultPlayData tmp_0 = this.resultData.get(index_0);
/* 53 */       clone.resultData.add(tmp_0.clone());
/*    */     } 
/* 55 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 60 */     this.effectId = 0;
/* 61 */     this.guid = 0;
/* 62 */     this.resultData.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 67 */     String retVal = "{\"effectId\":" + this.effectId + ",\"guid\":" + this.guid + ",\"resultData\":" + this.resultData.toString() + "}";
/* 68 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\EffectPlayData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */