/*    */ package com.linlongyx.sanguo.webgame.proto.binary.draw;
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
/*    */ public class DrawCardNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public ArrayList<Integer> list = new ArrayList<>();
/*    */   
/*    */   public DrawCardNoticeResponse() {
/* 22 */     this.eventResponseId = 21801;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public DrawCardNoticeResponse(short retCode) {
/* 27 */     this.eventResponseId = 21801;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     int size_0 = this.list.size();
/* 36 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 37 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 39 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/* 40 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 47 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 48 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 50 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 51 */       this.list.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public DrawCardNoticeResponse clone() throws CloneNotSupportedException {
/* 57 */     DrawCardNoticeResponse clone = (DrawCardNoticeResponse)super.clone();
/* 58 */     int size_0 = this.list.size();
/* 59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 61 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/* 62 */       clone.list.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 64 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 69 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "{\"list\":" + this.list.toString() + "}";
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\draw\DrawCardNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */