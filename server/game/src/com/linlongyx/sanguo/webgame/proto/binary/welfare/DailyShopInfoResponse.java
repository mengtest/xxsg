/*    */ package com.linlongyx.sanguo.webgame.proto.binary.welfare;
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
/*    */ public class DailyShopInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public ArrayList<Integer> dailyOrderId = new ArrayList<>();
/*    */   public int worldLevel;
/*    */   
/*    */   public DailyShopInfoResponse() {
/* 23 */     this.eventResponseId = 21909;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public DailyShopInfoResponse(short retCode) {
/* 28 */     this.eventResponseId = 21909;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.dailyOrderId.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       int tmp_0 = ((Integer)this.dailyOrderId.get(index_0)).intValue();
/* 41 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/* 43 */     ProtocolUtil.writeInt(out, this.worldLevel);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 50 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 52 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 53 */       this.dailyOrderId.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 55 */     this.worldLevel = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DailyShopInfoResponse clone() throws CloneNotSupportedException {
/* 60 */     DailyShopInfoResponse clone = (DailyShopInfoResponse)super.clone();
/* 61 */     int size_0 = this.dailyOrderId.size();
/* 62 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 64 */       int tmp_0 = ((Integer)this.dailyOrderId.get(index_0)).intValue();
/* 65 */       clone.dailyOrderId.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 67 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 72 */     this.dailyOrderId.clear();
/* 73 */     this.worldLevel = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     String retVal = "{\"dailyOrderId\":" + this.dailyOrderId.toString() + ",\"worldLevel\":" + this.worldLevel + "}";
/* 79 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\welfare\DailyShopInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */