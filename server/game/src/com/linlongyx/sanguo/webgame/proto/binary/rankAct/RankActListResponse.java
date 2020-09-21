/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rankAct;
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
/*    */ public class RankActListResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int rankId;
/* 21 */   public ArrayList<RankingData> list = new ArrayList<>();
/*    */   public int myRank;
/*    */   
/*    */   public RankActListResponse() {
/* 25 */     this.eventResponseId = 22003;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RankActListResponse(short retCode) {
/* 30 */     this.eventResponseId = 22003;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.rankId);
/*    */     
/* 39 */     int size_0 = this.list.size();
/* 40 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 43 */       RankingData tmp_0 = this.list.get(index_0);
/* 44 */       tmp_0.serial(out);
/*    */     } 
/* 46 */     ProtocolUtil.writeInt(out, this.myRank);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     this.rankId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 53 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 54 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 56 */       RankingData tmp_0 = new RankingData();
/* 57 */       tmp_0.unserial(in);
/* 58 */       this.list.add(tmp_0);
/*    */     } 
/* 60 */     this.myRank = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RankActListResponse clone() throws CloneNotSupportedException {
/* 65 */     RankActListResponse clone = (RankActListResponse)super.clone();
/* 66 */     int size_0 = this.list.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       RankingData tmp_0 = this.list.get(index_0);
/* 70 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.rankId = 0;
/* 78 */     this.list.clear();
/* 79 */     this.myRank = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     String retVal = "{\"rankId\":" + this.rankId + ",\"list\":" + this.list.toString() + ",\"myRank\":" + this.myRank + "}";
/* 85 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rankAct\RankActListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */