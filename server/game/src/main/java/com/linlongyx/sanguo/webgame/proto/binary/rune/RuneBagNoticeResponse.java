/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rune;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RuneItem;
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
/*    */ public class RuneBagNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<RuneItem> info = new ArrayList<>();
/*    */   public int changeType;
/*    */   
/*    */   public RuneBagNoticeResponse() {
/* 24 */     this.eventResponseId = 27802;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RuneBagNoticeResponse(short retCode) {
/* 29 */     this.eventResponseId = 27802;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     int size_0 = this.info.size();
/* 38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 41 */       RuneItem tmp_0 = this.info.get(index_0);
/* 42 */       tmp_0.serial(out);
/*    */     } 
/* 44 */     ProtocolUtil.writeInt(out, this.changeType);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 50 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 51 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 53 */       RuneItem tmp_0 = new RuneItem();
/* 54 */       tmp_0.unserial(in);
/* 55 */       this.info.add(tmp_0);
/*    */     } 
/* 57 */     this.changeType = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RuneBagNoticeResponse clone() throws CloneNotSupportedException {
/* 62 */     RuneBagNoticeResponse clone = (RuneBagNoticeResponse)super.clone();
/* 63 */     int size_0 = this.info.size();
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       RuneItem tmp_0 = this.info.get(index_0);
/* 67 */       clone.info.add(tmp_0.clone());
/*    */     } 
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.info.clear();
/* 75 */     this.changeType = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "{\"info\":" + this.info.toString() + ",\"changeType\":" + this.changeType + "}";
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rune\RuneBagNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */