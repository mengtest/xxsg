/*    */ package com.linlongyx.sanguo.webgame.proto.binary.group;
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
/*    */ public class GroupSacrificeInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int process;
/*    */   public int num;
/* 21 */   public ArrayList<Integer> rewards = new ArrayList<>();
/*    */   public int type;
/*    */   
/*    */   public GroupSacrificeInfoResponse() {
/* 25 */     this.eventResponseId = 21111;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GroupSacrificeInfoResponse(short retCode) {
/* 30 */     this.eventResponseId = 21111;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.process);
/* 38 */     ProtocolUtil.writeInt(out, this.num);
/*    */     
/* 40 */     int size_0 = this.rewards.size();
/* 41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       int tmp_0 = ((Integer)this.rewards.get(index_0)).intValue();
/* 45 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/* 47 */     ProtocolUtil.writeInt(out, this.type);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 52 */     this.process = ProtocolUtil.readUTFBinInt(in);
/* 53 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 55 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 56 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 58 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 59 */       this.rewards.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 61 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupSacrificeInfoResponse clone() throws CloneNotSupportedException {
/* 66 */     GroupSacrificeInfoResponse clone = (GroupSacrificeInfoResponse)super.clone();
/* 67 */     int size_0 = this.rewards.size();
/* 68 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 70 */       int tmp_0 = ((Integer)this.rewards.get(index_0)).intValue();
/* 71 */       clone.rewards.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 73 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 78 */     this.process = 0;
/* 79 */     this.num = 0;
/* 80 */     this.rewards.clear();
/* 81 */     this.type = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 86 */     String retVal = "{\"process\":" + this.process + ",\"num\":" + this.num + ",\"rewards\":" + this.rewards.toString() + ",\"type\":" + this.type + "}";
/* 87 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupSacrificeInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */