/*    */ package com.linlongyx.sanguo.webgame.proto.binary.welfare;
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
/*    */ public class GrowFundInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public ArrayList<Integer> fundLevel = new ArrayList<>();
/*    */   public int isBuy;
/*    */   public int groupBuyNum;
/*    */   
/*    */   public GrowFundInfoResponse() {
/* 24 */     this.eventResponseId = 21906;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GrowFundInfoResponse(short retCode) {
/* 29 */     this.eventResponseId = 21906;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     int size_0 = this.fundLevel.size();
/* 38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 41 */       int tmp_0 = ((Integer)this.fundLevel.get(index_0)).intValue();
/* 42 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/* 44 */     ProtocolUtil.writeInt(out, this.isBuy);
/* 45 */     ProtocolUtil.writeInt(out, this.groupBuyNum);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 55 */       this.fundLevel.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 57 */     this.isBuy = ProtocolUtil.readUTFBinInt(in);
/* 58 */     this.groupBuyNum = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GrowFundInfoResponse clone() throws CloneNotSupportedException {
/* 63 */     GrowFundInfoResponse clone = (GrowFundInfoResponse)super.clone();
/* 64 */     int size_0 = this.fundLevel.size();
/* 65 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 67 */       int tmp_0 = ((Integer)this.fundLevel.get(index_0)).intValue();
/* 68 */       clone.fundLevel.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 70 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 75 */     this.fundLevel.clear();
/* 76 */     this.isBuy = 0;
/* 77 */     this.groupBuyNum = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     String retVal = "{\"fundLevel\":" + this.fundLevel.toString() + ",\"isBuy\":" + this.isBuy + ",\"groupBuyNum\":" + this.groupBuyNum + "}";
/* 83 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\welfare\GrowFundInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */