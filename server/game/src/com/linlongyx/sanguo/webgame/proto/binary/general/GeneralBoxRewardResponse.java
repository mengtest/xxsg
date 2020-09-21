/*    */ package com.linlongyx.sanguo.webgame.proto.binary.general;
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
/*    */ public class GeneralBoxRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int chapter;
/*    */   public int level;
/*    */   public int box;
/*    */   
/*    */   public GeneralBoxRewardResponse() {
/* 23 */     this.eventResponseId = 21204;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GeneralBoxRewardResponse(short retCode) {
/* 28 */     this.eventResponseId = 21204;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.chapter);
/* 36 */     ProtocolUtil.writeInt(out, this.level);
/* 37 */     ProtocolUtil.writeInt(out, this.box);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.chapter = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.box = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GeneralBoxRewardResponse clone() throws CloneNotSupportedException {
/* 49 */     GeneralBoxRewardResponse clone = (GeneralBoxRewardResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.chapter = 0;
/* 56 */     this.level = 0;
/* 57 */     this.box = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"chapter\":" + this.chapter + ",\"level\":" + this.level + ",\"box\":" + this.box + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\general\GeneralBoxRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */