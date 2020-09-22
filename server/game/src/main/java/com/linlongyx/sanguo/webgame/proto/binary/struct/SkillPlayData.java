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
/*    */ public class SkillPlayData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public byte guid;
/*    */   public byte fury;
/*    */   public byte atomFury;
/*    */   public int skillId;
/* 19 */   public ArrayList<EffectPlayData> effectData = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 23 */     ProtocolUtil.writeByte(out, this.guid);
/* 24 */     ProtocolUtil.writeByte(out, this.fury);
/* 25 */     ProtocolUtil.writeByte(out, this.atomFury);
/* 26 */     ProtocolUtil.writeInt(out, this.skillId);
/*    */     
/* 28 */     int size_0 = this.effectData.size();
/* 29 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 30 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 32 */       EffectPlayData tmp_0 = this.effectData.get(index_0);
/* 33 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.guid = ProtocolUtil.readUTFBinByte(in);
/* 40 */     this.fury = ProtocolUtil.readUTFBinByte(in);
/* 41 */     this.atomFury = ProtocolUtil.readUTFBinByte(in);
/* 42 */     this.skillId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 44 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 45 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 47 */       EffectPlayData tmp_0 = new EffectPlayData();
/* 48 */       tmp_0.unserial(in);
/* 49 */       this.effectData.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public SkillPlayData clone() throws CloneNotSupportedException {
/* 55 */     SkillPlayData clone = (SkillPlayData)super.clone();
/* 56 */     int size_0 = this.effectData.size();
/* 57 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 59 */       EffectPlayData tmp_0 = this.effectData.get(index_0);
/* 60 */       clone.effectData.add(tmp_0.clone());
/*    */     } 
/* 62 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 67 */     this.guid = 0;
/* 68 */     this.fury = 0;
/* 69 */     this.atomFury = 0;
/* 70 */     this.skillId = 0;
/* 71 */     this.effectData.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"guid\":" + this.guid + ",\"fury\":" + this.fury + ",\"atomFury\":" + this.atomFury + ",\"skillId\":" + this.skillId + ",\"effectData\":" + this.effectData.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\SkillPlayData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */