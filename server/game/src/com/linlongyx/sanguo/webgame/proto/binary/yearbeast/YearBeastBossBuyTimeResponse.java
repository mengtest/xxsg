/*    */ package com.linlongyx.sanguo.webgame.proto.binary.yearbeast;
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
/*    */ public class YearBeastBossBuyTimeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int actId;
/*    */   public int buyTimes;
/*    */   public int currTimes;
/*    */   
/*    */   public YearBeastBossBuyTimeResponse() {
/* 23 */     this.eventResponseId = 20327;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public YearBeastBossBuyTimeResponse(short retCode) {
/* 28 */     this.eventResponseId = 20327;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.actId);
/* 36 */     ProtocolUtil.writeInt(out, this.buyTimes);
/* 37 */     ProtocolUtil.writeInt(out, this.currTimes);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.buyTimes = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.currTimes = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public YearBeastBossBuyTimeResponse clone() throws CloneNotSupportedException {
/* 49 */     YearBeastBossBuyTimeResponse clone = (YearBeastBossBuyTimeResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.actId = 0;
/* 56 */     this.buyTimes = 0;
/* 57 */     this.currTimes = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"actId\":" + this.actId + ",\"buyTimes\":" + this.buyTimes + ",\"currTimes\":" + this.currTimes + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\yearbeast\YearBeastBossBuyTimeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */