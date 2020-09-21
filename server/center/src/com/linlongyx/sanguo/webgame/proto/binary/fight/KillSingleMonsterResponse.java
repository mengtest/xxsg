/*    */ package com.linlongyx.sanguo.webgame.proto.binary.fight;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class KillSingleMonsterResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public KillSingleMonsterResponse() {
/* 17 */     this.eventResponseId = 20205;
/* 18 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public KillSingleMonsterResponse(short retCode) {
/* 22 */     this.eventResponseId = 20205;
/* 23 */     this.codeType = 2;
/* 24 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {}
/*    */ 
/*    */   
/*    */   public KillSingleMonsterResponse clone() throws CloneNotSupportedException {
/* 37 */     KillSingleMonsterResponse clone = (KillSingleMonsterResponse)super.clone();
/* 38 */     return clone;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void reset() {}
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     String retVal = "{}";
/* 48 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\fight\KillSingleMonsterResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */