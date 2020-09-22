/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.CrossBattleTeamData;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class CrossPlayerDataNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public CrossBattleTeamData data = new CrossBattleTeamData();
/*    */   
/*    */   public CrossPlayerDataNoticeResponse() {
/* 22 */     this.eventResponseId = 21315;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 28 */     this.data.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.data = new CrossBattleTeamData();
/* 34 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossPlayerDataNoticeResponse clone() throws CloneNotSupportedException {
/* 39 */     CrossPlayerDataNoticeResponse clone = (CrossPlayerDataNoticeResponse)super.clone();
/* 40 */     clone.data = this.data.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 46 */     this.data.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "{\"data\":" + this.data.toString() + "}";
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossPlayerDataNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */