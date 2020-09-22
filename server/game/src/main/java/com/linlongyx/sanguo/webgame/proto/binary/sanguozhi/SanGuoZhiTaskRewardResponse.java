/*    */ package com.linlongyx.sanguo.webgame.proto.binary.sanguozhi;
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
/*    */ public class SanGuoZhiTaskRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int recordStar;
/* 21 */   public ArrayList<KeyValue> info = new ArrayList<>();
/*    */   
/*    */   public SanGuoZhiTaskRewardResponse() {
/* 24 */     this.eventResponseId = 25402;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public SanGuoZhiTaskRewardResponse(short retCode) {
/* 29 */     this.eventResponseId = 25402;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeInt(out, this.recordStar);
/*    */     
/* 38 */     int size_0 = this.info.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       KeyValue tmp_0 = this.info.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.recordStar = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       KeyValue tmp_0 = new KeyValue();
/* 55 */       tmp_0.unserial(in);
/* 56 */       this.info.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public SanGuoZhiTaskRewardResponse clone() throws CloneNotSupportedException {
/* 62 */     SanGuoZhiTaskRewardResponse clone = (SanGuoZhiTaskRewardResponse)super.clone();
/* 63 */     int size_0 = this.info.size();
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       KeyValue tmp_0 = this.info.get(index_0);
/* 67 */       clone.info.add(tmp_0.clone());
/*    */     } 
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.recordStar = 0;
/* 75 */     this.info.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "{\"recordStar\":" + this.recordStar + ",\"info\":" + this.info.toString() + "}";
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\sanguozhi\SanGuoZhiTaskRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */