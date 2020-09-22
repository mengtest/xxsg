/*    */ package com.linlongyx.sanguo.webgame.proto.binary.welfare;
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
/*    */ public class DailiHaoLiInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int actId;
/*    */   public long todayCharge;
/*    */   public int getReward;
/*    */   
/*    */   public DailiHaoLiInfoResponse() {
/* 23 */     this.eventResponseId = 21920;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public DailiHaoLiInfoResponse(short retCode) {
/* 28 */     this.eventResponseId = 21920;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.actId);
/* 36 */     ProtocolUtil.writeLong(out, this.todayCharge);
/* 37 */     ProtocolUtil.writeInt(out, this.getReward);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.todayCharge = ProtocolUtil.readUTFBinLong(in);
/* 44 */     this.getReward = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DailiHaoLiInfoResponse clone() throws CloneNotSupportedException {
/* 49 */     DailiHaoLiInfoResponse clone = (DailiHaoLiInfoResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.actId = 0;
/* 56 */     this.todayCharge = 0L;
/* 57 */     this.getReward = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"actId\":" + this.actId + ",\"todayCharge\":" + this.todayCharge + ",\"getReward\":" + this.getReward + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\welfare\DailiHaoLiInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */