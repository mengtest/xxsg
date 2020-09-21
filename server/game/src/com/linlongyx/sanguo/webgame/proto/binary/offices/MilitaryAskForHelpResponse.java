/*    */ package com.linlongyx.sanguo.webgame.proto.binary.offices;
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
/*    */ public class MilitaryAskForHelpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int status;
/*    */   
/*    */   public MilitaryAskForHelpResponse() {
/* 21 */     this.eventResponseId = 22507;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public MilitaryAskForHelpResponse(short retCode) {
/* 26 */     this.eventResponseId = 22507;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.status);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.status = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MilitaryAskForHelpResponse clone() throws CloneNotSupportedException {
/* 43 */     MilitaryAskForHelpResponse clone = (MilitaryAskForHelpResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.status = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"status\":" + this.status + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\offices\MilitaryAskForHelpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */