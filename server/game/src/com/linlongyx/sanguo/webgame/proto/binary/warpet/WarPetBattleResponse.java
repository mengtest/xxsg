/*    */ package com.linlongyx.sanguo.webgame.proto.binary.warpet;
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
/*    */ public class WarPetBattleResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int warpet;
/*    */   public int battleState;
/*    */   
/*    */   public WarPetBattleResponse() {
/* 22 */     this.eventResponseId = 26403;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WarPetBattleResponse(short retCode) {
/* 27 */     this.eventResponseId = 26403;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.warpet);
/* 35 */     ProtocolUtil.writeInt(out, this.battleState);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.warpet = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.battleState = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WarPetBattleResponse clone() throws CloneNotSupportedException {
/* 46 */     WarPetBattleResponse clone = (WarPetBattleResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.warpet = 0;
/* 53 */     this.battleState = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"warpet\":" + this.warpet + ",\"battleState\":" + this.battleState + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\warpet\WarPetBattleResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */