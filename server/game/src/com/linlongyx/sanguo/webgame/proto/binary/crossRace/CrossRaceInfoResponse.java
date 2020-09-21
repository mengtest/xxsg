/*    */ package com.linlongyx.sanguo.webgame.proto.binary.crossRace;
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
/*    */ public class CrossRaceInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int point;
/*    */   public int rank;
/*    */   public int winTimes;
/*    */   public int fightTimes;
/*    */   public int times;
/*    */   public int state;
/* 25 */   public ArrayList<Integer> rewardList = new ArrayList<>();
/*    */   
/*    */   public CrossRaceInfoResponse() {
/* 28 */     this.eventResponseId = 22801;
/* 29 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.point);
/* 35 */     ProtocolUtil.writeInt(out, this.rank);
/* 36 */     ProtocolUtil.writeInt(out, this.winTimes);
/* 37 */     ProtocolUtil.writeInt(out, this.fightTimes);
/* 38 */     ProtocolUtil.writeInt(out, this.times);
/* 39 */     ProtocolUtil.writeInt(out, this.state);
/*    */     
/* 41 */     int size_0 = this.rewardList.size();
/* 42 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 43 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 45 */       int tmp_0 = ((Integer)this.rewardList.get(index_0)).intValue();
/* 46 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 52 */     this.point = ProtocolUtil.readUTFBinInt(in);
/* 53 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.winTimes = ProtocolUtil.readUTFBinInt(in);
/* 55 */     this.fightTimes = ProtocolUtil.readUTFBinInt(in);
/* 56 */     this.times = ProtocolUtil.readUTFBinInt(in);
/* 57 */     this.state = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 59 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 60 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 62 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 63 */       this.rewardList.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossRaceInfoResponse clone() throws CloneNotSupportedException {
/* 69 */     CrossRaceInfoResponse clone = (CrossRaceInfoResponse)super.clone();
/* 70 */     int size_0 = this.rewardList.size();
/* 71 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 73 */       int tmp_0 = ((Integer)this.rewardList.get(index_0)).intValue();
/* 74 */       clone.rewardList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 76 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 81 */     this.point = 0;
/* 82 */     this.rank = 0;
/* 83 */     this.winTimes = 0;
/* 84 */     this.fightTimes = 0;
/* 85 */     this.times = 0;
/* 86 */     this.state = 0;
/* 87 */     this.rewardList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 92 */     String retVal = "{\"point\":" + this.point + ",\"rank\":" + this.rank + ",\"winTimes\":" + this.winTimes + ",\"fightTimes\":" + this.fightTimes + ",\"times\":" + this.times + ",\"state\":" + this.state + ",\"rewardList\":" + this.rewardList.toString() + "}";
/* 93 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\crossRace\CrossRaceInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */