/*    */ package com.linlongyx.sanguo.webgame.proto.binary.recruit;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
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
/*    */ public class RedRecruitBoxResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/* 20 */   public ArrayList<Integer> boxList = new ArrayList<>();
/*    */   public int score;
/*    */   
/*    */   public RedRecruitBoxResponse() {
/* 24 */     this.eventResponseId = 24006;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RedRecruitBoxResponse(short retCode) {
/* 29 */     this.eventResponseId = 24006;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeInt(out, this.type);
/*    */     
/* 38 */     int size_0 = this.boxList.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       int tmp_0 = ((Integer)this.boxList.get(index_0)).intValue();
/* 43 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/* 45 */     ProtocolUtil.writeInt(out, this.score);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 50 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 52 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 53 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 55 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 56 */       this.boxList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 58 */     this.score = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RedRecruitBoxResponse clone() throws CloneNotSupportedException {
/* 63 */     RedRecruitBoxResponse clone = (RedRecruitBoxResponse)super.clone();
/* 64 */     int size_0 = this.boxList.size();
/* 65 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 67 */       int tmp_0 = ((Integer)this.boxList.get(index_0)).intValue();
/* 68 */       clone.boxList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 70 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 75 */     this.type = 0;
/* 76 */     this.boxList.clear();
/* 77 */     this.score = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     String retVal = "{\"type\":" + this.type + ",\"boxList\":" + this.boxList.toString() + ",\"score\":" + this.score + "}";
/* 83 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\recruit\RedRecruitBoxResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */