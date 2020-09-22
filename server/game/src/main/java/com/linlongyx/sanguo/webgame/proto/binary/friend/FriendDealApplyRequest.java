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
/*    */ public class FriendDealApplyRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public byte agree;
/* 20 */   public ArrayList<Long> playerIds = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public FriendDealApplyRequest() {
/* 24 */     this.eventRequestId = 13409;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeByte(out, this.agree);
/*    */     
/* 32 */     int size_0 = this.playerIds.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       long tmp_0 = ((Long)this.playerIds.get(index_0)).longValue();
/* 37 */       ProtocolUtil.writeLong(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     this.agree = ProtocolUtil.readUTFBinByte(in);
/*    */     
/* 45 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       long tmp_0 = ProtocolUtil.readUTFBinLong(in);
/* 49 */       this.playerIds.add(Long.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendDealApplyRequest clone() throws CloneNotSupportedException {
/* 55 */     FriendDealApplyRequest clone = (FriendDealApplyRequest)super.clone();
/* 56 */     int size_0 = this.playerIds.size();
/* 57 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 59 */       long tmp_0 = ((Long)this.playerIds.get(index_0)).longValue();
/* 60 */       clone.playerIds.add(Long.valueOf(tmp_0));
/*    */     } 
/* 62 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 67 */     this.agree = 0;
/* 68 */     this.playerIds.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 73 */     String retVal = "{\"agree\":" + this.agree + ",\"playerIds\":" + this.playerIds.toString() + "}";
/* 74 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendDealApplyRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */