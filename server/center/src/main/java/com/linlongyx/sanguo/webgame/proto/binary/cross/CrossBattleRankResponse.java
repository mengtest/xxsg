/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.CrossRankData;
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
/*    */ public class CrossBattleRankResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<CrossRankData> rankList = new ArrayList<>();
/*    */   public int myRank;
/*    */   
/*    */   public CrossBattleRankResponse() {
/* 24 */     this.eventResponseId = 21316;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     int size_0 = this.rankList.size();
/* 32 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 33 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 35 */       CrossRankData tmp_0 = this.rankList.get(index_0);
/* 36 */       tmp_0.serial(out);
/*    */     } 
/* 38 */     ProtocolUtil.writeInt(out, this.myRank);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 44 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 45 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 47 */       CrossRankData tmp_0 = new CrossRankData();
/* 48 */       tmp_0.unserial(in);
/* 49 */       this.rankList.add(tmp_0);
/*    */     } 
/* 51 */     this.myRank = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossBattleRankResponse clone() throws CloneNotSupportedException {
/* 56 */     CrossBattleRankResponse clone = (CrossBattleRankResponse)super.clone();
/* 57 */     int size_0 = this.rankList.size();
/* 58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 60 */       CrossRankData tmp_0 = this.rankList.get(index_0);
/* 61 */       clone.rankList.add(tmp_0.clone());
/*    */     } 
/* 63 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 68 */     this.rankList.clear();
/* 69 */     this.myRank = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "{\"rankList\":" + this.rankList.toString() + ",\"myRank\":" + this.myRank + "}";
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossBattleRankResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */