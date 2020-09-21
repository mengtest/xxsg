/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bosshome;
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
/*    */ public class WorldBuyTimeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int fightTimes;
/*    */   public int buyTime;
/*    */   
/*    */   public WorldBuyTimeResponse() {
/* 22 */     this.eventResponseId = 20308;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WorldBuyTimeResponse(short retCode) {
/* 27 */     this.eventResponseId = 20308;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.fightTimes);
/* 35 */     ProtocolUtil.writeInt(out, this.buyTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.fightTimes = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.buyTime = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldBuyTimeResponse clone() throws CloneNotSupportedException {
/* 46 */     WorldBuyTimeResponse clone = (WorldBuyTimeResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.fightTimes = 0;
/* 53 */     this.buyTime = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"fightTimes\":" + this.fightTimes + ",\"buyTime\":" + this.buyTime + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bosshome\WorldBuyTimeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */