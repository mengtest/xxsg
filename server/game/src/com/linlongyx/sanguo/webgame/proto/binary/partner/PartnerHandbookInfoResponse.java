/*    */ package com.linlongyx.sanguo.webgame.proto.binary.partner;
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
/*    */ public class PartnerHandbookInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int camps;
/* 20 */   public ArrayList<Integer> currRewardIndex = new ArrayList<>();
/*    */   
/*    */   public PartnerHandbookInfoResponse() {
/* 23 */     this.eventResponseId = 23310;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public PartnerHandbookInfoResponse(short retCode) {
/* 28 */     this.eventResponseId = 23310;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.camps);
/*    */     
/* 37 */     int size_0 = this.currRewardIndex.size();
/* 38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 41 */       int tmp_0 = ((Integer)this.currRewardIndex.get(index_0)).intValue();
/* 42 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     this.camps = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 50 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 51 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 53 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 54 */       this.currRewardIndex.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public PartnerHandbookInfoResponse clone() throws CloneNotSupportedException {
/* 60 */     PartnerHandbookInfoResponse clone = (PartnerHandbookInfoResponse)super.clone();
/* 61 */     int size_0 = this.currRewardIndex.size();
/* 62 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 64 */       int tmp_0 = ((Integer)this.currRewardIndex.get(index_0)).intValue();
/* 65 */       clone.currRewardIndex.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 67 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 72 */     this.camps = 0;
/* 73 */     this.currRewardIndex.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     String retVal = "{\"camps\":" + this.camps + ",\"currRewardIndex\":" + this.currRewardIndex.toString() + "}";
/* 79 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\partner\PartnerHandbookInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */