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
/*    */ public class RuneSysReplaceResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long pid;
/* 20 */   public RuneItem rune = new RuneItem();
/*    */   
/*    */   public RuneSysReplaceResponse() {
/* 23 */     this.eventResponseId = 27806;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RuneSysReplaceResponse(short retCode) {
/* 28 */     this.eventResponseId = 27806;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeLong(out, this.pid);
/* 36 */     this.rune.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 42 */     this.rune = new RuneItem();
/* 43 */     this.rune.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RuneSysReplaceResponse clone() throws CloneNotSupportedException {
/* 48 */     RuneSysReplaceResponse clone = (RuneSysReplaceResponse)super.clone();
/* 49 */     clone.rune = this.rune.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.pid = 0L;
/* 56 */     this.rune.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"pid\":" + this.pid + ",\"rune\":" + this.rune.toString() + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rune\RuneSysReplaceResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */