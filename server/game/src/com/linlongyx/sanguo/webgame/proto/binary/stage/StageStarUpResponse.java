/*    */ package com.linlongyx.sanguo.webgame.proto.binary.stage;
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
/*    */ public class StageStarUpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int stage;
/*    */   public int star;
/*    */   
/*    */   public StageStarUpResponse() {
/* 22 */     this.eventResponseId = 25612;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public StageStarUpResponse(short retCode) {
/* 27 */     this.eventResponseId = 25612;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.stage);
/* 35 */     ProtocolUtil.writeInt(out, this.star);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.stage = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.star = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public StageStarUpResponse clone() throws CloneNotSupportedException {
/* 46 */     StageStarUpResponse clone = (StageStarUpResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.stage = 0;
/* 53 */     this.star = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"stage\":" + this.stage + ",\"star\":" + this.star + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\stage\StageStarUpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */