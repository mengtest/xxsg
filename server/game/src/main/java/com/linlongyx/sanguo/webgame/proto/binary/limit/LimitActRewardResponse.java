/*    */ package com.linlongyx.sanguo.webgame.proto.binary.limit;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LimitData;
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
/*    */ public class LimitActRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int actId;
/* 20 */   public LimitData data = new LimitData();
/*    */   
/*    */   public LimitActRewardResponse() {
/* 23 */     this.eventResponseId = 22703;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public LimitActRewardResponse(short retCode) {
/* 28 */     this.eventResponseId = 22703;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.actId);
/* 36 */     this.data.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.data = new LimitData();
/* 43 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LimitActRewardResponse clone() throws CloneNotSupportedException {
/* 48 */     LimitActRewardResponse clone = (LimitActRewardResponse)super.clone();
/* 49 */     clone.data = this.data.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.actId = 0;
/* 56 */     this.data.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"actId\":" + this.actId + ",\"data\":" + this.data.toString() + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\limit\LimitActRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */