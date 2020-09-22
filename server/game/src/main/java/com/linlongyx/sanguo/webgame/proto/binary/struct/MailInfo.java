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
/*    */ public class MailInfo
/*    */ {
/*    */   public int id;
/*    */   public byte type;
/*    */   public long sendId;
/*    */   public String sendName;
/*    */   public int sendTime;
/*    */   public String title;
/*    */   public String context;
/* 21 */   public ArrayList<Reward> rewards = new ArrayList<>();
/*    */   
/*    */   public byte isRead;
/*    */   public byte isExtract;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 27 */     ProtocolUtil.writeInt(out, this.id);
/* 28 */     ProtocolUtil.writeByte(out, this.type);
/* 29 */     ProtocolUtil.writeLong(out, this.sendId);
/* 30 */     ProtocolUtil.writeUTFBinary(out, this.sendName);
/* 31 */     ProtocolUtil.writeInt(out, this.sendTime);
/* 32 */     ProtocolUtil.writeUTFBinary(out, this.title);
/* 33 */     ProtocolUtil.writeUTFBinary(out, this.context);
/*    */     
/* 35 */     int size_0 = this.rewards.size();
/* 36 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 37 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 39 */       Reward tmp_0 = this.rewards.get(index_0);
/* 40 */       tmp_0.serial(out);
/*    */     } 
/* 42 */     ProtocolUtil.writeByte(out, this.isRead);
/* 43 */     ProtocolUtil.writeByte(out, this.isExtract);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 50 */     this.sendId = ProtocolUtil.readUTFBinLong(in);
/* 51 */     this.sendName = ProtocolUtil.readUTFStr(in);
/* 52 */     this.sendTime = ProtocolUtil.readUTFBinInt(in);
/* 53 */     this.title = ProtocolUtil.readUTFStr(in);
/* 54 */     this.context = ProtocolUtil.readUTFStr(in);
/*    */     
/* 56 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 57 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 59 */       Reward tmp_0 = new Reward();
/* 60 */       tmp_0.unserial(in);
/* 61 */       this.rewards.add(tmp_0);
/*    */     } 
/* 63 */     this.isRead = ProtocolUtil.readUTFBinByte(in);
/* 64 */     this.isExtract = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MailInfo clone() throws CloneNotSupportedException {
/* 69 */     MailInfo clone = (MailInfo)super.clone();
/* 70 */     int size_0 = this.rewards.size();
/* 71 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 73 */       Reward tmp_0 = this.rewards.get(index_0);
/* 74 */       clone.rewards.add(tmp_0.clone());
/*    */     } 
/* 76 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 81 */     this.id = 0;
/* 82 */     this.type = 0;
/* 83 */     this.sendId = 0L;
/* 84 */     this.sendName = null;
/* 85 */     this.sendTime = 0;
/* 86 */     this.title = null;
/* 87 */     this.context = null;
/* 88 */     this.rewards.clear();
/* 89 */     this.isRead = 0;
/* 90 */     this.isExtract = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 95 */     String retVal = "{\"id\":" + this.id + ",\"type\":" + this.type + ",\"sendId\":" + this.sendId + ",\"sendName\":" + StringUtil.str2Str(this.sendName) + ",\"sendTime\":" + this.sendTime + ",\"title\":" + StringUtil.str2Str(this.title) + ",\"context\":" + StringUtil.str2Str(this.context) + ",\"rewards\":" + this.rewards.toString() + ",\"isRead\":" + this.isRead + ",\"isExtract\":" + this.isExtract + "}";
/* 96 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\MailInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */