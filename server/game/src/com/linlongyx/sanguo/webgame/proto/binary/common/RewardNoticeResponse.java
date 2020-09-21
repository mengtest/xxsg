/*    */ package com.linlongyx.sanguo.webgame.proto.binary.common;
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
/*    */ public class RewardNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/* 21 */   public ArrayList<Reward> rewards = new ArrayList<>();
/*    */   
/*    */   public RewardNoticeResponse() {
/* 24 */     this.eventResponseId = 21002;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RewardNoticeResponse(short retCode) {
/* 29 */     this.eventResponseId = 21002;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeInt(out, this.type);
/*    */     
/* 38 */     int size_0 = this.rewards.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       Reward tmp_0 = this.rewards.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       Reward tmp_0 = new Reward();
/* 55 */       tmp_0.unserial(in);
/* 56 */       this.rewards.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RewardNoticeResponse clone() throws CloneNotSupportedException {
/* 62 */     RewardNoticeResponse clone = (RewardNoticeResponse)super.clone();
/* 63 */     int size_0 = this.rewards.size();
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       Reward tmp_0 = this.rewards.get(index_0);
/* 67 */       clone.rewards.add(tmp_0.clone());
/*    */     } 
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.type = 0;
/* 75 */     this.rewards.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "{\"type\":" + this.type + ",\"rewards\":" + this.rewards.toString() + "}";
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\common\RewardNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */