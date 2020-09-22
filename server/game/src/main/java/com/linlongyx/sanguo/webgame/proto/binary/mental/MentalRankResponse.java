/*    */ package com.linlongyx.sanguo.webgame.proto.binary.mental;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MentalRankStruct;
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
/*    */ public class MentalRankResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<MentalRankStruct> ranks = new ArrayList<>();
/*    */   
/*    */   public MentalRankResponse() {
/* 23 */     this.eventResponseId = 27302;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public MentalRankResponse(short retCode) {
/* 28 */     this.eventResponseId = 27302;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.ranks.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       MentalRankStruct tmp_0 = this.ranks.get(index_0);
/* 41 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 51 */       MentalRankStruct tmp_0 = new MentalRankStruct();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.ranks.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public MentalRankResponse clone() throws CloneNotSupportedException {
/* 59 */     MentalRankResponse clone = (MentalRankResponse)super.clone();
/* 60 */     int size_0 = this.ranks.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       MentalRankStruct tmp_0 = this.ranks.get(index_0);
/* 64 */       clone.ranks.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.ranks.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"ranks\":" + this.ranks.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\mental\MentalRankResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */