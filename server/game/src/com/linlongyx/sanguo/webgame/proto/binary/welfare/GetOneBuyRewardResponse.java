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
/*    */ public class GetOneBuyRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int oneBuyId;
/* 20 */   public ArrayList<Integer> gotReward = new ArrayList<>();
/*    */   
/*    */   public GetOneBuyRewardResponse() {
/* 23 */     this.eventResponseId = 21912;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GetOneBuyRewardResponse(short retCode) {
/* 28 */     this.eventResponseId = 21912;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.oneBuyId);
/*    */     
/* 37 */     int size_0 = this.gotReward.size();
/* 38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 41 */       int tmp_0 = ((Integer)this.gotReward.get(index_0)).intValue();
/* 42 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     this.oneBuyId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 50 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 51 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 53 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 54 */       this.gotReward.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public GetOneBuyRewardResponse clone() throws CloneNotSupportedException {
/* 60 */     GetOneBuyRewardResponse clone = (GetOneBuyRewardResponse)super.clone();
/* 61 */     int size_0 = this.gotReward.size();
/* 62 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 64 */       int tmp_0 = ((Integer)this.gotReward.get(index_0)).intValue();
/* 65 */       clone.gotReward.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 67 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 72 */     this.oneBuyId = 0;
/* 73 */     this.gotReward.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     String retVal = "{\"oneBuyId\":" + this.oneBuyId + ",\"gotReward\":" + this.gotReward.toString() + "}";
/* 79 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\welfare\GetOneBuyRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */