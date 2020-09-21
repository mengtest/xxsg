/*    */ package com.linlongyx.sanguo.webgame.proto.binary.general;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GeneralPointData;
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
/*    */ public class GeneralPointResetResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int chapter;
/*    */   public GeneralPointData data;
/*    */   
/*    */   public GeneralPointResetResponse() {
/* 23 */     this.eventResponseId = 21206;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GeneralPointResetResponse(short retCode) {
/* 28 */     this.eventResponseId = 21206;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.chapter);
/* 36 */     this.data.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.chapter = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.data = new GeneralPointData();
/* 43 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GeneralPointResetResponse clone() throws CloneNotSupportedException {
/* 48 */     GeneralPointResetResponse clone = (GeneralPointResetResponse)super.clone();
/* 49 */     clone.data = this.data.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.chapter = 0;
/* 56 */     this.data.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"chapter\":" + this.chapter + ",\"data\":" + this.data + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\general\GeneralPointResetResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */