/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.NeutralBossData;
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
/*    */ public class NeutralBossPushResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public NeutralBossData data = new NeutralBossData();
/*    */   
/*    */   public NeutralBossPushResponse() {
/* 22 */     this.eventResponseId = 20316;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public NeutralBossPushResponse(short retCode) {
/* 27 */     this.eventResponseId = 20316;
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
/* 39 */     this.data = new NeutralBossData();
/* 40 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public NeutralBossPushResponse clone() throws CloneNotSupportedException {
/* 45 */     NeutralBossPushResponse clone = (NeutralBossPushResponse)super.clone();
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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bosshome\NeutralBossPushResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */