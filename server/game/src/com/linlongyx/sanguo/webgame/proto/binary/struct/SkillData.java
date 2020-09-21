/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int skillId;
/*    */   public short level;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeInt(out, this.skillId);
/* 21 */     ProtocolUtil.writeShort(out, this.level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 26 */     this.skillId = ProtocolUtil.readUTFBinInt(in);
/* 27 */     this.level = ProtocolUtil.readUTFBinShort(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SkillData clone() throws CloneNotSupportedException {
/* 32 */     SkillData clone = (SkillData)super.clone();
/* 33 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 38 */     this.skillId = 0;
/* 39 */     this.level = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "{\"skillId\":" + this.skillId + ",\"level\":" + this.level + "}";
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\SkillData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */