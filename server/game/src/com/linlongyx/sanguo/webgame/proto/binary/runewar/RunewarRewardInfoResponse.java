/*    */ package com.linlongyx.sanguo.webgame.proto.binary.runewar;
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
/*    */ public class RunewarRewardInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<IntIntValue> rewardList = new ArrayList<>();
/*    */   public int progress;
/*    */   public int type;
/*    */   
/*    */   public RunewarRewardInfoResponse() {
/* 25 */     this.eventResponseId = 24503;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 32 */     int size_0 = this.rewardList.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       IntIntValue tmp_0 = this.rewardList.get(index_0);
/* 37 */       tmp_0.serial(out);
/*    */     } 
/* 39 */     ProtocolUtil.writeInt(out, this.progress);
/* 40 */     ProtocolUtil.writeInt(out, this.type);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 46 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 47 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 49 */       IntIntValue tmp_0 = new IntIntValue();
/* 50 */       tmp_0.unserial(in);
/* 51 */       this.rewardList.add(tmp_0);
/*    */     } 
/* 53 */     this.progress = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RunewarRewardInfoResponse clone() throws CloneNotSupportedException {
/* 59 */     RunewarRewardInfoResponse clone = (RunewarRewardInfoResponse)super.clone();
/* 60 */     int size_0 = this.rewardList.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       IntIntValue tmp_0 = this.rewardList.get(index_0);
/* 64 */       clone.rewardList.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.rewardList.clear();
/* 72 */     this.progress = 0;
/* 73 */     this.type = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     String retVal = "{\"rewardList\":" + this.rewardList.toString() + ",\"progress\":" + this.progress + ",\"type\":" + this.type + "}";
/* 79 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\runewar\RunewarRewardInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */