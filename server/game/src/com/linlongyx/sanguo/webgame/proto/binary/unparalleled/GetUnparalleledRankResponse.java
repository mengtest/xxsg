/*    */ package com.linlongyx.sanguo.webgame.proto.binary.unparalleled;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.UnparalleledRankInfo;
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
/*    */ public class GetUnparalleledRankResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<UnparalleledRankInfo> rankList = new ArrayList<>();
/*    */   public int myRank;
/*    */   public int stars;
/*    */   
/*    */   public GetUnparalleledRankResponse() {
/* 25 */     this.eventResponseId = 24411;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GetUnparalleledRankResponse(short retCode) {
/* 30 */     this.eventResponseId = 24411;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     int size_0 = this.rankList.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       UnparalleledRankInfo tmp_0 = this.rankList.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/* 45 */     ProtocolUtil.writeInt(out, this.myRank);
/* 46 */     ProtocolUtil.writeInt(out, this.stars);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 52 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 53 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 55 */       UnparalleledRankInfo tmp_0 = new UnparalleledRankInfo();
/* 56 */       tmp_0.unserial(in);
/* 57 */       this.rankList.add(tmp_0);
/*    */     } 
/* 59 */     this.myRank = ProtocolUtil.readUTFBinInt(in);
/* 60 */     this.stars = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GetUnparalleledRankResponse clone() throws CloneNotSupportedException {
/* 65 */     GetUnparalleledRankResponse clone = (GetUnparalleledRankResponse)super.clone();
/* 66 */     int size_0 = this.rankList.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       UnparalleledRankInfo tmp_0 = this.rankList.get(index_0);
/* 70 */       clone.rankList.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.rankList.clear();
/* 78 */     this.myRank = 0;
/* 79 */     this.stars = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     String retVal = "{\"rankList\":" + this.rankList.toString() + ",\"myRank\":" + this.myRank + ",\"stars\":" + this.stars + "}";
/* 85 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binar\\unparalleled\GetUnparalleledRankResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */