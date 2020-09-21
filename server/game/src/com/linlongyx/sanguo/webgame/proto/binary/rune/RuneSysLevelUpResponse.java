/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rune;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RuneItem;
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
/*    */ public class RuneSysLevelUpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int num;
/* 20 */   public RuneItem runeItem = new RuneItem();
/*    */   
/*    */   public RuneSysLevelUpResponse() {
/* 23 */     this.eventResponseId = 27805;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RuneSysLevelUpResponse(short retCode) {
/* 28 */     this.eventResponseId = 27805;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.num);
/* 36 */     this.runeItem.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.runeItem = new RuneItem();
/* 43 */     this.runeItem.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RuneSysLevelUpResponse clone() throws CloneNotSupportedException {
/* 48 */     RuneSysLevelUpResponse clone = (RuneSysLevelUpResponse)super.clone();
/* 49 */     clone.runeItem = this.runeItem.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.num = 0;
/* 56 */     this.runeItem.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"num\":" + this.num + ",\"runeItem\":" + this.runeItem.toString() + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rune\RuneSysLevelUpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */