/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rune;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class RuneSysInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long pid;
/* 21 */   public ArrayList<KeyValue> runes = new ArrayList<>();
/*    */   
/*    */   public RuneSysInfoResponse() {
/* 24 */     this.eventResponseId = 27804;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RuneSysInfoResponse(short retCode) {
/* 29 */     this.eventResponseId = 27804;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeLong(out, this.pid);
/*    */     
/* 38 */     int size_0 = this.runes.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       KeyValue tmp_0 = this.runes.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       KeyValue tmp_0 = new KeyValue();
/* 55 */       tmp_0.unserial(in);
/* 56 */       this.runes.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RuneSysInfoResponse clone() throws CloneNotSupportedException {
/* 62 */     RuneSysInfoResponse clone = (RuneSysInfoResponse)super.clone();
/* 63 */     int size_0 = this.runes.size();
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       KeyValue tmp_0 = this.runes.get(index_0);
/* 67 */       clone.runes.add(tmp_0.clone());
/*    */     } 
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.pid = 0L;
/* 75 */     this.runes.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "{\"pid\":" + this.pid + ",\"runes\":" + this.runes.toString() + "}";
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rune\RuneSysInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */