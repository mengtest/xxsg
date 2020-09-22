/*    */ package com.linlongyx.sanguo.webgame.proto.binary.recruit;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
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
/*    */ public class RecruitRebateInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public FestivalTime festivalTime = new FestivalTime();
/*    */   public int score;
/* 22 */   public ArrayList<Integer> list = new ArrayList<>();
/*    */   
/*    */   public RecruitRebateInfoResponse() {
/* 25 */     this.eventResponseId = 24007;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RecruitRebateInfoResponse(short retCode) {
/* 30 */     this.eventResponseId = 24007;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     this.festivalTime.serial(out);
/* 38 */     ProtocolUtil.writeInt(out, this.score);
/*    */     
/* 40 */     int size_0 = this.list.size();
/* 41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/* 45 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     this.festivalTime = new FestivalTime();
/* 52 */     this.festivalTime.unserial(in);
/* 53 */     this.score = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 55 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 56 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 58 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 59 */       this.list.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RecruitRebateInfoResponse clone() throws CloneNotSupportedException {
/* 65 */     RecruitRebateInfoResponse clone = (RecruitRebateInfoResponse)super.clone();
/* 66 */     clone.festivalTime = this.festivalTime.clone();
/* 67 */     int size_0 = this.list.size();
/* 68 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 70 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/* 71 */       clone.list.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 73 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 78 */     this.festivalTime.reset();
/* 79 */     this.score = 0;
/* 80 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 85 */     String retVal = "{\"festivalTime\":" + this.festivalTime.toString() + ",\"score\":" + this.score + ",\"list\":" + this.list.toString() + "}";
/* 86 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\recruit\RecruitRebateInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */