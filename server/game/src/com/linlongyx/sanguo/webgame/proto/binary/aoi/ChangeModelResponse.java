/*    */ package com.linlongyx.sanguo.webgame.proto.binary.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ModelData;
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
/*    */ public class ChangeModelResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public ModelData model = new ModelData();
/*    */   
/*    */   public ChangeModelResponse() {
/* 22 */     this.eventResponseId = 20107;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ChangeModelResponse(short retCode) {
/* 27 */     this.eventResponseId = 20107;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     this.model.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.model = new ModelData();
/* 40 */     this.model.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChangeModelResponse clone() throws CloneNotSupportedException {
/* 45 */     ChangeModelResponse clone = (ChangeModelResponse)super.clone();
/* 46 */     clone.model = this.model.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.model.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"model\":" + this.model.toString() + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\ChangeModelResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */