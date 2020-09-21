/*    */ package com.linlongyx.sanguo.webgame.proto.binary.achieve;
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
/*    */ public class AchieveRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int point;
/*    */   public int type;
/*    */   public int id;
/*    */   
/*    */   public AchieveRewardResponse() {
/* 23 */     this.eventResponseId = 20402;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public AchieveRewardResponse(short retCode) {
/* 28 */     this.eventResponseId = 20402;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.point);
/* 36 */     ProtocolUtil.writeInt(out, this.type);
/* 37 */     ProtocolUtil.writeInt(out, this.id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.point = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.id = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public AchieveRewardResponse clone() throws CloneNotSupportedException {
/* 49 */     AchieveRewardResponse clone = (AchieveRewardResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.point = 0;
/* 56 */     this.type = 0;
/* 57 */     this.id = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"point\":" + this.point + ",\"type\":" + this.type + ",\"id\":" + this.id + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\achieve\AchieveRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */