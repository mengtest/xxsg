/*    */ package com.linlongyx.sanguo.webgame.proto.binary.turnplate;
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
/*    */ public class TurnplateDrawResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public ArrayList<Integer> rewardList = new ArrayList<>();
/*    */   public int point;
/*    */   public int goldPool;
/*    */   
/*    */   public TurnplateDrawResponse() {
/* 24 */     this.eventResponseId = 22402;
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
/* 35 */       int tmp_0 = ((Integer)this.rewardList.get(index_0)).intValue();
/* 36 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/* 38 */     ProtocolUtil.writeInt(out, this.point);
/* 39 */     ProtocolUtil.writeInt(out, this.goldPool);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 45 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 49 */       this.rewardList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 51 */     this.point = ProtocolUtil.readUTFBinInt(in);
/* 52 */     this.goldPool = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TurnplateDrawResponse clone() throws CloneNotSupportedException {
/* 57 */     TurnplateDrawResponse clone = (TurnplateDrawResponse)super.clone();
/* 58 */     int size_0 = this.rewardList.size();
/* 59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 61 */       int tmp_0 = ((Integer)this.rewardList.get(index_0)).intValue();
/* 62 */       clone.rewardList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 64 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 69 */     this.rewardList.clear();
/* 70 */     this.point = 0;
/* 71 */     this.goldPool = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"rewardList\":" + this.rewardList.toString() + ",\"point\":" + this.point + ",\"goldPool\":" + this.goldPool + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\turnplate\TurnplateDrawResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */