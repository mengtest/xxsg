/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FriendChatInfo
/*    */ {
/*    */   public long playerId;
/* 14 */   public ArrayList<KeyValue> chats = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeLong(out, this.playerId);
/*    */     
/* 20 */     int size_0 = this.chats.size();
/* 21 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 22 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 24 */       KeyValue tmp_0 = this.chats.get(index_0);
/* 25 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 31 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 33 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       KeyValue tmp_0 = new KeyValue();
/* 37 */       tmp_0.unserial(in);
/* 38 */       this.chats.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendChatInfo clone() throws CloneNotSupportedException {
/* 44 */     FriendChatInfo clone = (FriendChatInfo)super.clone();
/* 45 */     int size_0 = this.chats.size();
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       KeyValue tmp_0 = this.chats.get(index_0);
/* 49 */       clone.chats.add(tmp_0.clone());
/*    */     } 
/* 51 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 56 */     this.playerId = 0L;
/* 57 */     this.chats.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"playerId\":" + this.playerId + ",\"chats\":" + this.chats.toString() + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\FriendChatInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */