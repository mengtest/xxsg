/*    */ package com.linlongyx.sanguo.webgame.proto.binary.skill;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class SkillLevelUpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int skillId;
/*    */   public int level;
/*    */   
/*    */   public SkillLevelUpResponse() {
/* 22 */     this.eventResponseId = 20903;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public SkillLevelUpResponse(short retCode) {
/* 27 */     this.eventResponseId = 20903;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.skillId);
/* 35 */     ProtocolUtil.writeInt(out, this.level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.skillId = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.level = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SkillLevelUpResponse clone() throws CloneNotSupportedException {
/* 46 */     SkillLevelUpResponse clone = (SkillLevelUpResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.skillId = 0;
/* 53 */     this.level = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"skillId\":" + this.skillId + ",\"level\":" + this.level + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\skill\SkillLevelUpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */