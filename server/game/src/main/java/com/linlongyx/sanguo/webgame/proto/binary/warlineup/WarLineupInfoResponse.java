/*    */ package com.linlongyx.sanguo.webgame.proto.binary.warlineup;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WarLineupData;
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
/*    */ public class WarLineupInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<WarLineupData> warLineupList = new ArrayList<>();
/*    */   
/*    */   public WarLineupInfoResponse() {
/* 23 */     this.eventResponseId = 26502;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WarLineupInfoResponse(short retCode) {
/* 28 */     this.eventResponseId = 26502;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.warLineupList.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       WarLineupData tmp_0 = this.warLineupList.get(index_0);
/* 41 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 51 */       WarLineupData tmp_0 = new WarLineupData();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.warLineupList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public WarLineupInfoResponse clone() throws CloneNotSupportedException {
/* 59 */     WarLineupInfoResponse clone = (WarLineupInfoResponse)super.clone();
/* 60 */     int size_0 = this.warLineupList.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       WarLineupData tmp_0 = this.warLineupList.get(index_0);
/* 64 */       clone.warLineupList.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.warLineupList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"warLineupList\":" + this.warLineupList.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\warlineup\WarLineupInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */