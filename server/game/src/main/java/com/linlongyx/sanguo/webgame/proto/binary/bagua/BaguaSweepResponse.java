/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bagua;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
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
/*    */ public class BaguaSweepResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int insId;
/* 21 */   public ArrayList<Reward> rewardList = new ArrayList<>();
/*    */   
/*    */   public BaguaSweepResponse() {
/* 24 */     this.eventResponseId = 23604;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.insId);
/*    */     
/* 32 */     int size_0 = this.rewardList.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       Reward tmp_0 = this.rewardList.get(index_0);
/* 37 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 45 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       Reward tmp_0 = new Reward();
/* 49 */       tmp_0.unserial(in);
/* 50 */       this.rewardList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public BaguaSweepResponse clone() throws CloneNotSupportedException {
/* 56 */     BaguaSweepResponse clone = (BaguaSweepResponse)super.clone();
/* 57 */     int size_0 = this.rewardList.size();
/* 58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 60 */       Reward tmp_0 = this.rewardList.get(index_0);
/* 61 */       clone.rewardList.add(tmp_0.clone());
/*    */     } 
/* 63 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 68 */     this.insId = 0;
/* 69 */     this.rewardList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "{\"insId\":" + this.insId + ",\"rewardList\":" + this.rewardList.toString() + "}";
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bagua\BaguaSweepResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */