/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatPersonal
/*    */ {
/*    */   public long targetPlayerId;
/*    */   public String targetPlayerName;
/* 16 */   public ArrayList<ChatPersonalInfo> chatPersonalInfos = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeLong(out, this.targetPlayerId);
/* 21 */     ProtocolUtil.writeUTFBinary(out, this.targetPlayerName);
/*    */     
/* 23 */     int size_0 = this.chatPersonalInfos.size();
/* 24 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 25 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 27 */       ChatPersonalInfo tmp_0 = this.chatPersonalInfos.get(index_0);
/* 28 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.targetPlayerId = ProtocolUtil.readUTFBinLong(in);
/* 35 */     this.targetPlayerName = ProtocolUtil.readUTFStr(in);
/*    */     
/* 37 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       ChatPersonalInfo tmp_0 = new ChatPersonalInfo();
/* 41 */       tmp_0.unserial(in);
/* 42 */       this.chatPersonalInfos.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatPersonal clone() throws CloneNotSupportedException {
/* 48 */     ChatPersonal clone = (ChatPersonal)super.clone();
/* 49 */     int size_0 = this.chatPersonalInfos.size();
/* 50 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 52 */       ChatPersonalInfo tmp_0 = this.chatPersonalInfos.get(index_0);
/* 53 */       clone.chatPersonalInfos.add(tmp_0.clone());
/*    */     } 
/* 55 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 60 */     this.targetPlayerId = 0L;
/* 61 */     this.targetPlayerName = null;
/* 62 */     this.chatPersonalInfos.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 67 */     String retVal = "{\"targetPlayerId\":" + this.targetPlayerId + ",\"targetPlayerName\":" + StringUtil.str2Str(this.targetPlayerName) + ",\"chatPersonalInfos\":" + this.chatPersonalInfos.toString() + "}";
/* 68 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ChatPersonal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */