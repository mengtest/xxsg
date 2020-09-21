/*    */ package com.linlongyx.sanguo.webgame.proto.binary.arena;
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
/*    */ public class ArenaBuyTimesResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int buyTimes;
/*    */   public int fightTimes;
/*    */   
/*    */   public ArenaBuyTimesResponse() {
/* 22 */     this.eventResponseId = 21702;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ArenaBuyTimesResponse(short retCode) {
/* 27 */     this.eventResponseId = 21702;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.buyTimes);
/* 35 */     ProtocolUtil.writeInt(out, this.fightTimes);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.buyTimes = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.fightTimes = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ArenaBuyTimesResponse clone() throws CloneNotSupportedException {
/* 46 */     ArenaBuyTimesResponse clone = (ArenaBuyTimesResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.buyTimes = 0;
/* 53 */     this.fightTimes = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"buyTimes\":" + this.buyTimes + ",\"fightTimes\":" + this.fightTimes + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\arena\ArenaBuyTimesResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */