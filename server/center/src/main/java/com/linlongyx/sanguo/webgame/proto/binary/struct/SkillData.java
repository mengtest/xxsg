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
/*    */ public class SkillData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int skillId;
/*    */   public short level;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     ProtocolUtil.writeInt(out, this.skillId);
/* 20 */     ProtocolUtil.writeShort(out, this.level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 25 */     this.skillId = ProtocolUtil.readUTFBinInt(in);
/* 26 */     this.level = ProtocolUtil.readUTFBinShort(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SkillData clone() throws CloneNotSupportedException {
/* 31 */     SkillData clone = (SkillData)super.clone();
/* 32 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.skillId = 0;
/* 38 */     this.level = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     String retVal = "{\"skillId\":" + this.skillId + ",\"level\":" + this.level + "}";
/* 44 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\SkillData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */