/*    */ package com.linlongyx.sanguo.webgame.proto.binary.aoi;
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
/*    */ public class EnterConfirmResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int sceneId;
/*    */   
/*    */   public EnterConfirmResponse() {
/* 21 */     this.eventResponseId = 20109;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public EnterConfirmResponse(short retCode) {
/* 26 */     this.eventResponseId = 20109;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.sceneId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.sceneId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnterConfirmResponse clone() throws CloneNotSupportedException {
/* 43 */     EnterConfirmResponse clone = (EnterConfirmResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.sceneId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"sceneId\":" + this.sceneId + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\EnterConfirmResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */