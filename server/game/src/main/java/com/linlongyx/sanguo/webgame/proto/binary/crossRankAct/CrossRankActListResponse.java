/*    */ package com.linlongyx.sanguo.webgame.proto.binary.crossRankAct;
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
/*    */ public class CrossRankActListResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int id;
/* 21 */   public ArrayList<RankingData> list = new ArrayList<>();
/*    */   public int myRank;
/*    */   
/*    */   public CrossRankActListResponse() {
/* 25 */     this.eventResponseId = 24101;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     ProtocolUtil.writeInt(out, this.id);
/*    */     
/* 33 */     int size_0 = this.list.size();
/* 34 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 35 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 37 */       RankingData tmp_0 = this.list.get(index_0);
/* 38 */       tmp_0.serial(out);
/*    */     } 
/* 40 */     ProtocolUtil.writeInt(out, this.myRank);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 45 */     this.id = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 47 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 48 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 50 */       RankingData tmp_0 = new RankingData();
/* 51 */       tmp_0.unserial(in);
/* 52 */       this.list.add(tmp_0);
/*    */     } 
/* 54 */     this.myRank = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossRankActListResponse clone() throws CloneNotSupportedException {
/* 59 */     CrossRankActListResponse clone = (CrossRankActListResponse)super.clone();
/* 60 */     int size_0 = this.list.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       RankingData tmp_0 = this.list.get(index_0);
/* 64 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.id = 0;
/* 72 */     this.list.clear();
/* 73 */     this.myRank = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     String retVal = "{\"id\":" + this.id + ",\"list\":" + this.list.toString() + ",\"myRank\":" + this.myRank + "}";
/* 79 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\crossRankAct\CrossRankActListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */