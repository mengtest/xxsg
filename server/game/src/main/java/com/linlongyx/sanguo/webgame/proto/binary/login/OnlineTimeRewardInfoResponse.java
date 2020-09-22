/*    */ package com.linlongyx.sanguo.webgame.proto.binary.login;
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
/*    */ public class OnlineTimeRewardInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int time;
/*    */   public byte canGet;
/*    */   
/*    */   public OnlineTimeRewardInfoResponse() {
/* 22 */     this.eventResponseId = 20017;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public OnlineTimeRewardInfoResponse(short retCode) {
/* 27 */     this.eventResponseId = 20017;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.time);
/* 35 */     ProtocolUtil.writeByte(out, this.canGet);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.canGet = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public OnlineTimeRewardInfoResponse clone() throws CloneNotSupportedException {
/* 46 */     OnlineTimeRewardInfoResponse clone = (OnlineTimeRewardInfoResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.time = 0;
/* 53 */     this.canGet = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"time\":" + this.time + ",\"canGet\":" + this.canGet + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\OnlineTimeRewardInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */