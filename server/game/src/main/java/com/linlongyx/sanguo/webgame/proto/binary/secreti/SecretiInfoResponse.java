/*    */ package com.linlongyx.sanguo.webgame.proto.binary.secreti;
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
/*    */ public class SecretiInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int rewardTimes;
/* 20 */   public ArrayList<Integer> insList = new ArrayList<>();
/*    */   
/*    */   public SecretiInfoResponse() {
/* 23 */     this.eventResponseId = 23201;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.rewardTimes);
/*    */     
/* 31 */     int size_0 = this.insList.size();
/* 32 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 33 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 35 */       int tmp_0 = ((Integer)this.insList.get(index_0)).intValue();
/* 36 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.rewardTimes = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 44 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 45 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 47 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 48 */       this.insList.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public SecretiInfoResponse clone() throws CloneNotSupportedException {
/* 54 */     SecretiInfoResponse clone = (SecretiInfoResponse)super.clone();
/* 55 */     int size_0 = this.insList.size();
/* 56 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 58 */       int tmp_0 = ((Integer)this.insList.get(index_0)).intValue();
/* 59 */       clone.insList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 61 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 66 */     this.rewardTimes = 0;
/* 67 */     this.insList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 72 */     String retVal = "{\"rewardTimes\":" + this.rewardTimes + ",\"insList\":" + this.insList.toString() + "}";
/* 73 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\secreti\SecretiInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */