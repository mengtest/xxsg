/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bag;
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
/*    */ public class GetRebornListResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/* 20 */   public ArrayList<Long> ids = new ArrayList<>();
/*    */   
/*    */   public GetRebornListResponse() {
/* 23 */     this.eventResponseId = 20711;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GetRebornListResponse(short retCode) {
/* 28 */     this.eventResponseId = 20711;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.type);
/*    */     
/* 37 */     int size_0 = this.ids.size();
/* 38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 41 */       long tmp_0 = ((Long)this.ids.get(index_0)).longValue();
/* 42 */       ProtocolUtil.writeLong(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 50 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 51 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 53 */       long tmp_0 = ProtocolUtil.readUTFBinLong(in);
/* 54 */       this.ids.add(Long.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public GetRebornListResponse clone() throws CloneNotSupportedException {
/* 60 */     GetRebornListResponse clone = (GetRebornListResponse)super.clone();
/* 61 */     int size_0 = this.ids.size();
/* 62 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 64 */       long tmp_0 = ((Long)this.ids.get(index_0)).longValue();
/* 65 */       clone.ids.add(Long.valueOf(tmp_0));
/*    */     } 
/* 67 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 72 */     this.type = 0;
/* 73 */     this.ids.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     String retVal = "{\"type\":" + this.type + ",\"ids\":" + this.ids.toString() + "}";
/* 79 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bag\GetRebornListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */