/*    */ package com.linlongyx.sanguo.webgame.proto.binary.divine;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DivineRankData;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class DivineRankInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 22 */   public List<DivineRankData> rankList = new ArrayList<>();
/* 23 */   public DivineRankData myRankData = new DivineRankData();
/*    */   
/*    */   public DivineRankInfoResponse() {
/* 26 */     this.eventResponseId = 24304;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     int size_0 = this.rankList.size();
/* 34 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 35 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 37 */       DivineRankData tmp_0 = this.rankList.get(index_0);
/* 38 */       tmp_0.serial(out);
/*    */     } 
/* 40 */     this.myRankData.serial(out);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 46 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 47 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 49 */       DivineRankData tmp_0 = new DivineRankData();
/* 50 */       tmp_0.unserial(in);
/* 51 */       this.rankList.add(tmp_0);
/*    */     } 
/* 53 */     this.myRankData = new DivineRankData();
/* 54 */     this.myRankData.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DivineRankInfoResponse clone() throws CloneNotSupportedException {
/* 59 */     DivineRankInfoResponse clone = (DivineRankInfoResponse)super.clone();
/* 60 */     int size_0 = this.rankList.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       DivineRankData tmp_0 = this.rankList.get(index_0);
/* 64 */       clone.rankList.add(tmp_0.clone());
/*    */     } 
/* 66 */     clone.myRankData = this.myRankData.clone();
/* 67 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 72 */     this.rankList.clear();
/* 73 */     this.myRankData.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     String retVal = "{\"rankList\":" + this.rankList.toString() + ",\"myRankData\":" + this.myRankData.toString() + "}";
/* 79 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\divine\DivineRankInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */