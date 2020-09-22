/*    */ package com.linlongyx.sanguo.webgame.proto.binary.partner;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PartnerInfo;
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
/*    */ public class PartnerListResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<PartnerInfo> partnerList = new ArrayList<>();
/*    */   
/*    */   public PartnerListResponse() {
/* 23 */     this.eventResponseId = 23301;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public PartnerListResponse(short retCode) {
/* 28 */     this.eventResponseId = 23301;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.partnerList.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       PartnerInfo tmp_0 = this.partnerList.get(index_0);
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
/* 51 */       PartnerInfo tmp_0 = new PartnerInfo();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.partnerList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public PartnerListResponse clone() throws CloneNotSupportedException {
/* 59 */     PartnerListResponse clone = (PartnerListResponse)super.clone();
/* 60 */     int size_0 = this.partnerList.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       PartnerInfo tmp_0 = this.partnerList.get(index_0);
/* 64 */       clone.partnerList.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.partnerList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"partnerList\":" + this.partnerList.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\partner\PartnerListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */