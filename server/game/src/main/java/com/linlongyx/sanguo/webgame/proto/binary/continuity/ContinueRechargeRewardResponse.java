/*    */ package com.linlongyx.sanguo.webgame.proto.binary.continuity;
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
/*    */ public class ContinueRechargeRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int id;
/*    */   public int tid;
/*    */   
/*    */   public ContinueRechargeRewardResponse() {
/* 22 */     this.eventResponseId = 22103;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ContinueRechargeRewardResponse(short retCode) {
/* 27 */     this.eventResponseId = 22103;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.id);
/* 35 */     ProtocolUtil.writeInt(out, this.tid);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.tid = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ContinueRechargeRewardResponse clone() throws CloneNotSupportedException {
/* 46 */     ContinueRechargeRewardResponse clone = (ContinueRechargeRewardResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.id = 0;
/* 53 */     this.tid = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"id\":" + this.id + ",\"tid\":" + this.tid + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\continuity\ContinueRechargeRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */