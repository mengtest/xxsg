/*    */ package com.linlongyx.sanguo.webgame.proto.binary.warlineup;
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
/*    */ public class WarLineupBattleResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int warlineup;
/*    */   public int battleState;
/*    */   
/*    */   public WarLineupBattleResponse() {
/* 22 */     this.eventResponseId = 26503;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WarLineupBattleResponse(short retCode) {
/* 27 */     this.eventResponseId = 26503;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.warlineup);
/* 35 */     ProtocolUtil.writeInt(out, this.battleState);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.warlineup = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.battleState = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WarLineupBattleResponse clone() throws CloneNotSupportedException {
/* 46 */     WarLineupBattleResponse clone = (WarLineupBattleResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.warlineup = 0;
/* 53 */     this.battleState = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"warlineup\":" + this.warlineup + ",\"battleState\":" + this.battleState + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\warlineup\WarLineupBattleResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */