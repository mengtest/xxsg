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
/*    */ public class GroupSacrificeActionResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int process;
/*    */   public int num;
/*    */   public int type;
/*    */   
/*    */   public GroupSacrificeActionResponse() {
/* 23 */     this.eventResponseId = 21112;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GroupSacrificeActionResponse(short retCode) {
/* 28 */     this.eventResponseId = 21112;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.process);
/* 36 */     ProtocolUtil.writeInt(out, this.num);
/* 37 */     ProtocolUtil.writeInt(out, this.type);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.process = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupSacrificeActionResponse clone() throws CloneNotSupportedException {
/* 49 */     GroupSacrificeActionResponse clone = (GroupSacrificeActionResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.process = 0;
/* 56 */     this.num = 0;
/* 57 */     this.type = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"process\":" + this.process + ",\"num\":" + this.num + ",\"type\":" + this.type + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupSacrificeActionResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */