/*    */ package com.linlongyx.sanguo.webgame.proto.binary.runewar;
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
/*    */ public class RunewarRankInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<RankingData> rankList = new ArrayList<>();
/*    */   
/*    */   public RunewarRankInfoResponse() {
/* 23 */     this.eventResponseId = 24505;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     int size_0 = this.rankList.size();
/* 31 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 32 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 34 */       RankingData tmp_0 = this.rankList.get(index_0);
/* 35 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 43 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 45 */       RankingData tmp_0 = new RankingData();
/* 46 */       tmp_0.unserial(in);
/* 47 */       this.rankList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RunewarRankInfoResponse clone() throws CloneNotSupportedException {
/* 53 */     RunewarRankInfoResponse clone = (RunewarRankInfoResponse)super.clone();
/* 54 */     int size_0 = this.rankList.size();
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       RankingData tmp_0 = this.rankList.get(index_0);
/* 58 */       clone.rankList.add(tmp_0.clone());
/*    */     } 
/* 60 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 65 */     this.rankList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "{\"rankList\":" + this.rankList.toString() + "}";
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\runewar\RunewarRankInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */