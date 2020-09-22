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
/*    */ public class FriendOpenResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<FriendInfo> friendInfos = new ArrayList<>();
/*    */   public int sendNum;
/*    */   public int receiveNum;
/*    */   
/*    */   public FriendOpenResponse() {
/* 25 */     this.eventResponseId = 23401;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public FriendOpenResponse(short retCode) {
/* 30 */     this.eventResponseId = 23401;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     int size_0 = this.friendInfos.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       FriendInfo tmp_0 = this.friendInfos.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/* 45 */     ProtocolUtil.writeInt(out, this.sendNum);
/* 46 */     ProtocolUtil.writeInt(out, this.receiveNum);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 52 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 53 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 55 */       FriendInfo tmp_0 = new FriendInfo();
/* 56 */       tmp_0.unserial(in);
/* 57 */       this.friendInfos.add(tmp_0);
/*    */     } 
/* 59 */     this.sendNum = ProtocolUtil.readUTFBinInt(in);
/* 60 */     this.receiveNum = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendOpenResponse clone() throws CloneNotSupportedException {
/* 65 */     FriendOpenResponse clone = (FriendOpenResponse)super.clone();
/* 66 */     int size_0 = this.friendInfos.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       FriendInfo tmp_0 = this.friendInfos.get(index_0);
/* 70 */       clone.friendInfos.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.friendInfos.clear();
/* 78 */     this.sendNum = 0;
/* 79 */     this.receiveNum = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     String retVal = "{\"friendInfos\":" + this.friendInfos.toString() + ",\"sendNum\":" + this.sendNum + ",\"receiveNum\":" + this.receiveNum + "}";
/* 85 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendOpenResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */