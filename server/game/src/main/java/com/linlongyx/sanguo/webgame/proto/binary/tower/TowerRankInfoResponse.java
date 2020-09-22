/*    */ package com.linlongyx.sanguo.webgame.proto.binary.tower;
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
/*    */ public class TowerRankInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int rank;
/*    */   public int star;
/* 22 */   public ArrayList<RankingData> list = new ArrayList<>();
/*    */   
/*    */   public TowerRankInfoResponse() {
/* 25 */     this.eventResponseId = 26803;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TowerRankInfoResponse(short retCode) {
/* 30 */     this.eventResponseId = 26803;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.rank);
/* 38 */     ProtocolUtil.writeInt(out, this.star);
/*    */     
/* 40 */     int size_0 = this.list.size();
/* 41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       RankingData tmp_0 = this.list.get(index_0);
/* 45 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 52 */     this.star = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 54 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       RankingData tmp_0 = new RankingData();
/* 58 */       tmp_0.unserial(in);
/* 59 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public TowerRankInfoResponse clone() throws CloneNotSupportedException {
/* 65 */     TowerRankInfoResponse clone = (TowerRankInfoResponse)super.clone();
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
/* 77 */     this.rank = 0;
/* 78 */     this.star = 0;
/* 79 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     String retVal = "{\"rank\":" + this.rank + ",\"star\":" + this.star + ",\"list\":" + this.list.toString() + "}";
/* 85 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\tower\TowerRankInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */