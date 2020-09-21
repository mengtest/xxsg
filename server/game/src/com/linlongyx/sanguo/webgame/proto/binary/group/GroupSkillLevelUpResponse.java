/*    */ package com.linlongyx.sanguo.webgame.proto.binary.group;
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
/*    */ public class GroupSkillLevelUpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int index;
/*    */   public int level;
/*    */   
/*    */   public GroupSkillLevelUpResponse() {
/* 22 */     this.eventResponseId = 21115;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GroupSkillLevelUpResponse(short retCode) {
/* 27 */     this.eventResponseId = 21115;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.index);
/* 35 */     ProtocolUtil.writeInt(out, this.level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.index = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.level = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupSkillLevelUpResponse clone() throws CloneNotSupportedException {
/* 46 */     GroupSkillLevelUpResponse clone = (GroupSkillLevelUpResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.index = 0;
/* 53 */     this.level = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"index\":" + this.index + ",\"level\":" + this.level + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupSkillLevelUpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */