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
/*    */ public class GroupRecruitResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int nextSendTime;
/*    */   
/*    */   public GroupRecruitResponse() {
/* 21 */     this.eventResponseId = 21124;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GroupRecruitResponse(short retCode) {
/* 26 */     this.eventResponseId = 21124;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.nextSendTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.nextSendTime = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupRecruitResponse clone() throws CloneNotSupportedException {
/* 43 */     GroupRecruitResponse clone = (GroupRecruitResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.nextSendTime = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"nextSendTime\":" + this.nextSendTime + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupRecruitResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */