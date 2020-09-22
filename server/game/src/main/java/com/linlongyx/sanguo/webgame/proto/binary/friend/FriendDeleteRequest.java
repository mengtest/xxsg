/*    */ package com.linlongyx.sanguo.webgame.proto.binary.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
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
/*    */ public class FriendDeleteRequest
/*    */   extends RequestBase
/*    */ {
/* 19 */   public ArrayList<Long> playerIds = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public FriendDeleteRequest() {
/* 23 */     this.eventRequestId = 13410;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     int size_0 = this.playerIds.size();
/* 31 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 32 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 34 */       long tmp_0 = ((Long)this.playerIds.get(index_0)).longValue();
/* 35 */       ProtocolUtil.writeLong(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 43 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 45 */       long tmp_0 = ProtocolUtil.readUTFBinLong(in);
/* 46 */       this.playerIds.add(Long.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendDeleteRequest clone() throws CloneNotSupportedException {
/* 52 */     FriendDeleteRequest clone = (FriendDeleteRequest)super.clone();
/* 53 */     int size_0 = this.playerIds.size();
/* 54 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 56 */       long tmp_0 = ((Long)this.playerIds.get(index_0)).longValue();
/* 57 */       clone.playerIds.add(Long.valueOf(tmp_0));
/*    */     } 
/* 59 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 64 */     this.playerIds.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     String retVal = "{\"playerIds\":" + this.playerIds.toString() + "}";
/* 70 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendDeleteRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */