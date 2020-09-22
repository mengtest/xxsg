/*    */ package com.linlongyx.sanguo.webgame.proto.binary.skill;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SkillData;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class SkillNewResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public SkillData skill = new SkillData();
/*    */   
/*    */   public SkillNewResponse() {
/* 22 */     this.eventResponseId = 20902;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public SkillNewResponse(short retCode) {
/* 27 */     this.eventResponseId = 20902;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     this.skill.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.skill = new SkillData();
/* 40 */     this.skill.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SkillNewResponse clone() throws CloneNotSupportedException {
/* 45 */     SkillNewResponse clone = (SkillNewResponse)super.clone();
/* 46 */     clone.skill = this.skill.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.skill.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"skill\":" + this.skill.toString() + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\skill\SkillNewResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */