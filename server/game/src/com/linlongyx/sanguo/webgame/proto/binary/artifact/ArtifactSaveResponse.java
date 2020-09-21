/*    */ package com.linlongyx.sanguo.webgame.proto.binary.artifact;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArtifactData;
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
/*    */ public class ArtifactSaveResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public ArtifactData data = new ArtifactData();
/*    */   
/*    */   public ArtifactSaveResponse() {
/* 22 */     this.eventResponseId = 23106;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ArtifactSaveResponse(short retCode) {
/* 27 */     this.eventResponseId = 23106;
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
/* 39 */     this.data = new ArtifactData();
/* 40 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ArtifactSaveResponse clone() throws CloneNotSupportedException {
/* 45 */     ArtifactSaveResponse clone = (ArtifactSaveResponse)super.clone();
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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\artifact\ArtifactSaveResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */