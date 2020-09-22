/*    */ package com.linlongyx.sanguo.webgame.proto.binary.wander;
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
/*    */ public class WanderInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public ArrayList<Integer> rewards = new ArrayList<>();
/*    */   public long todayRecharge;
/*    */   public long endTime;
/*    */   
/*    */   public WanderInfoResponse() {
/* 24 */     this.eventResponseId = 25301;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WanderInfoResponse(short retCode) {
/* 29 */     this.eventResponseId = 25301;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     int size_0 = this.rewards.size();
/* 38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 41 */       int tmp_0 = ((Integer)this.rewards.get(index_0)).intValue();
/* 42 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/* 44 */     ProtocolUtil.writeLong(out, this.todayRecharge);
/* 45 */     ProtocolUtil.writeLong(out, this.endTime);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 55 */       this.rewards.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 57 */     this.todayRecharge = ProtocolUtil.readUTFBinLong(in);
/* 58 */     this.endTime = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WanderInfoResponse clone() throws CloneNotSupportedException {
/* 63 */     WanderInfoResponse clone = (WanderInfoResponse)super.clone();
/* 64 */     int size_0 = this.rewards.size();
/* 65 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 67 */       int tmp_0 = ((Integer)this.rewards.get(index_0)).intValue();
/* 68 */       clone.rewards.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 70 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 75 */     this.rewards.clear();
/* 76 */     this.todayRecharge = 0L;
/* 77 */     this.endTime = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     String retVal = "{\"rewards\":" + this.rewards.toString() + ",\"todayRecharge\":" + this.todayRecharge + ",\"endTime\":" + this.endTime + "}";
/* 83 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\wander\WanderInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */