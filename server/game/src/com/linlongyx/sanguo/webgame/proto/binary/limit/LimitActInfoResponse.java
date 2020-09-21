/*    */ package com.linlongyx.sanguo.webgame.proto.binary.limit;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LimitData;
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
/*    */ public class LimitActInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int actId;
/* 21 */   public ArrayList<LimitData> list = new ArrayList<>();
/*    */   
/*    */   public LimitActInfoResponse() {
/* 24 */     this.eventResponseId = 22702;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public LimitActInfoResponse(short retCode) {
/* 29 */     this.eventResponseId = 22702;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeInt(out, this.actId);
/*    */     
/* 38 */     int size_0 = this.list.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       LimitData tmp_0 = this.list.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       LimitData tmp_0 = new LimitData();
/* 55 */       tmp_0.unserial(in);
/* 56 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public LimitActInfoResponse clone() throws CloneNotSupportedException {
/* 62 */     LimitActInfoResponse clone = (LimitActInfoResponse)super.clone();
/* 63 */     int size_0 = this.list.size();
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       LimitData tmp_0 = this.list.get(index_0);
/* 67 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.actId = 0;
/* 75 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "{\"actId\":" + this.actId + ",\"list\":" + this.list.toString() + "}";
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\limit\LimitActInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */