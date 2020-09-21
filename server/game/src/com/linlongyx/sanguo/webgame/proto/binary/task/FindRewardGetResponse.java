/*    */ package com.linlongyx.sanguo.webgame.proto.binary.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FindRewardData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class FindRewardGetResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public FindRewardData data = new FindRewardData();
/* 21 */   public ArrayList<Reward> list = new ArrayList<>();
/*    */   
/*    */   public FindRewardGetResponse() {
/* 24 */     this.eventResponseId = 22218;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public FindRewardGetResponse(short retCode) {
/* 29 */     this.eventResponseId = 22218;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     this.data.serial(out);
/*    */     
/* 38 */     int size_0 = this.list.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       Reward tmp_0 = this.list.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.data = new FindRewardData();
/* 50 */     this.data.unserial(in);
/*    */     
/* 52 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 53 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 55 */       Reward tmp_0 = new Reward();
/* 56 */       tmp_0.unserial(in);
/* 57 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public FindRewardGetResponse clone() throws CloneNotSupportedException {
/* 63 */     FindRewardGetResponse clone = (FindRewardGetResponse)super.clone();
/* 64 */     clone.data = this.data.clone();
/* 65 */     int size_0 = this.list.size();
/* 66 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 68 */       Reward tmp_0 = this.list.get(index_0);
/* 69 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 71 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 76 */     this.data.reset();
/* 77 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     String retVal = "{\"data\":" + this.data.toString() + ",\"list\":" + this.list.toString() + "}";
/* 83 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\FindRewardGetResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */