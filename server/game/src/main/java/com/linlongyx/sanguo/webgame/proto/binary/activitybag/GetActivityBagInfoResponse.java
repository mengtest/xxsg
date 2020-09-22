/*    */ package com.linlongyx.sanguo.webgame.proto.binary.activitybag;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ActivityItemInfo;
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
/*    */ public class GetActivityBagInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<ActivityItemInfo> bagItemInfos = new ArrayList<>();
/*    */   
/*    */   public GetActivityBagInfoResponse() {
/* 23 */     this.eventResponseId = 21401;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GetActivityBagInfoResponse(short retCode) {
/* 28 */     this.eventResponseId = 21401;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.bagItemInfos.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       ActivityItemInfo tmp_0 = this.bagItemInfos.get(index_0);
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
/* 51 */       ActivityItemInfo tmp_0 = new ActivityItemInfo();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.bagItemInfos.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public GetActivityBagInfoResponse clone() throws CloneNotSupportedException {
/* 59 */     GetActivityBagInfoResponse clone = (GetActivityBagInfoResponse)super.clone();
/* 60 */     int size_0 = this.bagItemInfos.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       ActivityItemInfo tmp_0 = this.bagItemInfos.get(index_0);
/* 64 */       clone.bagItemInfos.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.bagItemInfos.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"bagItemInfos\":" + this.bagItemInfos.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\activitybag\GetActivityBagInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */