/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BossData;
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
/*    */ public class BossDamagePushResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public BossData data = new BossData();
/*    */   
/*    */   public BossDamagePushResponse() {
/* 22 */     this.eventResponseId = 20302;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public BossDamagePushResponse(short retCode) {
/* 27 */     this.eventResponseId = 20302;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     this.data.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.data = new BossData();
/* 40 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BossDamagePushResponse clone() throws CloneNotSupportedException {
/* 45 */     BossDamagePushResponse clone = (BossDamagePushResponse)super.clone();
/* 46 */     clone.data = this.data.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.data.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"data\":" + this.data.toString() + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bosshome\BossDamagePushResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */