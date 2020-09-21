/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bosshome;
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
/*    */ public class WorldRewardInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public ArrayList<Integer> list = new ArrayList<>();
/*    */   public int killNum;
/*    */   public long hurt;
/*    */   
/*    */   public WorldRewardInfoResponse() {
/* 24 */     this.eventResponseId = 20309;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WorldRewardInfoResponse(short retCode) {
/* 29 */     this.eventResponseId = 20309;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     int size_0 = this.list.size();
/* 38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 41 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/* 42 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/* 44 */     ProtocolUtil.writeInt(out, this.killNum);
/* 45 */     ProtocolUtil.writeLong(out, this.hurt);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 55 */       this.list.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 57 */     this.killNum = ProtocolUtil.readUTFBinInt(in);
/* 58 */     this.hurt = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldRewardInfoResponse clone() throws CloneNotSupportedException {
/* 63 */     WorldRewardInfoResponse clone = (WorldRewardInfoResponse)super.clone();
/* 64 */     int size_0 = this.list.size();
/* 65 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 67 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/* 68 */       clone.list.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 70 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 75 */     this.list.clear();
/* 76 */     this.killNum = 0;
/* 77 */     this.hurt = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     String retVal = "{\"list\":" + this.list.toString() + ",\"killNum\":" + this.killNum + ",\"hurt\":" + this.hurt + "}";
/* 83 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bosshome\WorldRewardInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */