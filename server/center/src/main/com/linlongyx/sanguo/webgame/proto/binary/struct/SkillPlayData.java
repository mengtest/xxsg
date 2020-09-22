/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillPlayData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public byte guid;
/*    */   public byte fury;
/*    */   public byte atomFury;
/*    */   public int skillId;
/* 18 */   public ArrayList<EffectPlayData> effectData = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 22 */     ProtocolUtil.writeByte(out, this.guid);
/* 23 */     ProtocolUtil.writeByte(out, this.fury);
/* 24 */     ProtocolUtil.writeByte(out, this.atomFury);
/* 25 */     ProtocolUtil.writeInt(out, this.skillId);
/*    */     
/* 27 */     int size_0 = this.effectData.size();
/* 28 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 29 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 31 */       EffectPlayData tmp_0 = this.effectData.get(index_0);
/* 32 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.guid = ProtocolUtil.readUTFBinByte(in);
/* 39 */     this.fury = ProtocolUtil.readUTFBinByte(in);
/* 40 */     this.atomFury = ProtocolUtil.readUTFBinByte(in);
/* 41 */     this.skillId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 43 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       EffectPlayData tmp_0 = new EffectPlayData();
/* 47 */       tmp_0.unserial(in);
/* 48 */       this.effectData.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public SkillPlayData clone() throws CloneNotSupportedException {
/* 54 */     SkillPlayData clone = (SkillPlayData)super.clone();
/* 55 */     int size_0 = this.effectData.size();
/* 56 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 58 */       EffectPlayData tmp_0 = this.effectData.get(index_0);
/* 59 */       clone.effectData.add(tmp_0.clone());
/*    */     } 
/* 61 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 66 */     this.guid = 0;
/* 67 */     this.fury = 0;
/* 68 */     this.atomFury = 0;
/* 69 */     this.skillId = 0;
/* 70 */     this.effectData.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     String retVal = "{\"guid\":" + this.guid + ",\"fury\":" + this.fury + ",\"atomFury\":" + this.atomFury + ",\"skillId\":" + this.skillId + ",\"effectData\":" + this.effectData.toString() + "}";
/* 76 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\SkillPlayData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */