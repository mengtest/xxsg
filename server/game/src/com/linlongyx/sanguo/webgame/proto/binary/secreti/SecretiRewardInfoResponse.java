/*    */ package com.linlongyx.sanguo.webgame.proto.binary.secreti;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
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
/*    */ public class SecretiRewardInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<IntIntValue> rewardList = new ArrayList<>();
/*    */   public int total;
/*    */   
/*    */   public SecretiRewardInfoResponse() {
/* 24 */     this.eventResponseId = 23203;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     int size_0 = this.rewardList.size();
/* 32 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 33 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 35 */       IntIntValue tmp_0 = this.rewardList.get(index_0);
/* 36 */       tmp_0.serial(out);
/*    */     } 
/* 38 */     ProtocolUtil.writeInt(out, this.total);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 44 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 45 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 47 */       IntIntValue tmp_0 = new IntIntValue();
/* 48 */       tmp_0.unserial(in);
/* 49 */       this.rewardList.add(tmp_0);
/*    */     } 
/* 51 */     this.total = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SecretiRewardInfoResponse clone() throws CloneNotSupportedException {
/* 56 */     SecretiRewardInfoResponse clone = (SecretiRewardInfoResponse)super.clone();
/* 57 */     int size_0 = this.rewardList.size();
/* 58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 60 */       IntIntValue tmp_0 = this.rewardList.get(index_0);
/* 61 */       clone.rewardList.add(tmp_0.clone());
/*    */     } 
/* 63 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 68 */     this.rewardList.clear();
/* 69 */     this.total = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "{\"rewardList\":" + this.rewardList.toString() + ",\"total\":" + this.total + "}";
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\secreti\SecretiRewardInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */