/*    */ package com.linlongyx.sanguo.webgame.proto.binary.warpet;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WarPetData;
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
/*    */ public class ActivityWarPetResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public WarPetData data = new WarPetData();
/*    */   
/*    */   public ActivityWarPetResponse() {
/* 22 */     this.eventResponseId = 26402;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ActivityWarPetResponse(short retCode) {
/* 27 */     this.eventResponseId = 26402;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     this.data.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.data = new WarPetData();
/* 40 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ActivityWarPetResponse clone() throws CloneNotSupportedException {
/* 45 */     ActivityWarPetResponse clone = (ActivityWarPetResponse)super.clone();
/* 46 */     clone.data = this.data.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.data.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"data\":" + this.data.toString() + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\warpet\ActivityWarPetResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */