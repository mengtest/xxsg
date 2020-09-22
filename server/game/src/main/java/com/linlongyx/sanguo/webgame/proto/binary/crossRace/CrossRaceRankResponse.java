/*    */ package com.linlongyx.sanguo.webgame.proto.binary.crossRace;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
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
/*    */ public class CrossRaceRankResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<RankingData> ranklist = new ArrayList<>();
/* 21 */   public RankingData myRank = new RankingData();
/*    */   
/*    */   public CrossRaceRankResponse() {
/* 24 */     this.eventResponseId = 22803;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     int size_0 = this.ranklist.size();
/* 32 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 33 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 35 */       RankingData tmp_0 = this.ranklist.get(index_0);
/* 36 */       tmp_0.serial(out);
/*    */     } 
/* 38 */     this.myRank.serial(out);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 44 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 45 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 47 */       RankingData tmp_0 = new RankingData();
/* 48 */       tmp_0.unserial(in);
/* 49 */       this.ranklist.add(tmp_0);
/*    */     } 
/* 51 */     this.myRank = new RankingData();
/* 52 */     this.myRank.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossRaceRankResponse clone() throws CloneNotSupportedException {
/* 57 */     CrossRaceRankResponse clone = (CrossRaceRankResponse)super.clone();
/* 58 */     int size_0 = this.ranklist.size();
/* 59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 61 */       RankingData tmp_0 = this.ranklist.get(index_0);
/* 62 */       clone.ranklist.add(tmp_0.clone());
/*    */     } 
/* 64 */     clone.myRank = this.myRank.clone();
/* 65 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 70 */     this.ranklist.clear();
/* 71 */     this.myRank.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"ranklist\":" + this.ranklist.toString() + ",\"myRank\":" + this.myRank.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\crossRace\CrossRaceRankResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */