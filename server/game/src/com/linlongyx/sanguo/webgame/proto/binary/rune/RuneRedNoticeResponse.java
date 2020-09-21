/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rune;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
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
/*    */ public class RuneRedNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public ArrayList<Long> pids = new ArrayList<>();
/*    */   
/*    */   public RuneRedNoticeResponse() {
/* 22 */     this.eventResponseId = 27803;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RuneRedNoticeResponse(short retCode) {
/* 27 */     this.eventResponseId = 27803;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     int size_0 = this.pids.size();
/* 36 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 37 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 39 */       long tmp_0 = ((Long)this.pids.get(index_0)).longValue();
/* 40 */       ProtocolUtil.writeLong(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 47 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 48 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 50 */       long tmp_0 = ProtocolUtil.readUTFBinLong(in);
/* 51 */       this.pids.add(Long.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RuneRedNoticeResponse clone() throws CloneNotSupportedException {
/* 57 */     RuneRedNoticeResponse clone = (RuneRedNoticeResponse)super.clone();
/* 58 */     int size_0 = this.pids.size();
/* 59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 61 */       long tmp_0 = ((Long)this.pids.get(index_0)).longValue();
/* 62 */       clone.pids.add(Long.valueOf(tmp_0));
/*    */     } 
/* 64 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 69 */     this.pids.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "{\"pids\":" + this.pids.toString() + "}";
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rune\RuneRedNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */