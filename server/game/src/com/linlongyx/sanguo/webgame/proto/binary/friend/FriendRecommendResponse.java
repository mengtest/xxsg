/*    */ package com.linlongyx.sanguo.webgame.proto.binary.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FriendInfo;
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
/*    */ public class FriendRecommendResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<FriendInfo> friendInfos = new ArrayList<>();
/*    */   
/*    */   public FriendRecommendResponse() {
/* 23 */     this.eventResponseId = 23421;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public FriendRecommendResponse(short retCode) {
/* 28 */     this.eventResponseId = 23421;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.friendInfos.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       FriendInfo tmp_0 = this.friendInfos.get(index_0);
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
/* 51 */       FriendInfo tmp_0 = new FriendInfo();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.friendInfos.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendRecommendResponse clone() throws CloneNotSupportedException {
/* 59 */     FriendRecommendResponse clone = (FriendRecommendResponse)super.clone();
/* 60 */     int size_0 = this.friendInfos.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       FriendInfo tmp_0 = this.friendInfos.get(index_0);
/* 64 */       clone.friendInfos.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.friendInfos.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"friendInfos\":" + this.friendInfos.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendRecommendResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */