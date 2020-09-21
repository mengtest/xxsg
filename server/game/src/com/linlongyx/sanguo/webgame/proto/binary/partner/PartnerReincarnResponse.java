/*    */ package com.linlongyx.sanguo.webgame.proto.binary.partner;
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
/*    */ public class PartnerReincarnResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long pid;
/*    */   public int reincarnId;
/* 21 */   public ArrayList<Integer> reincarnList = new ArrayList<>();
/*    */   
/*    */   public PartnerReincarnResponse() {
/* 24 */     this.eventResponseId = 23317;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public PartnerReincarnResponse(short retCode) {
/* 29 */     this.eventResponseId = 23317;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeLong(out, this.pid);
/* 37 */     ProtocolUtil.writeInt(out, this.reincarnId);
/*    */     
/* 39 */     int size_0 = this.reincarnList.size();
/* 40 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 43 */       int tmp_0 = ((Integer)this.reincarnList.get(index_0)).intValue();
/* 44 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 50 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 51 */     this.reincarnId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 53 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 54 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 56 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 57 */       this.reincarnList.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public PartnerReincarnResponse clone() throws CloneNotSupportedException {
/* 63 */     PartnerReincarnResponse clone = (PartnerReincarnResponse)super.clone();
/* 64 */     int size_0 = this.reincarnList.size();
/* 65 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 67 */       int tmp_0 = ((Integer)this.reincarnList.get(index_0)).intValue();
/* 68 */       clone.reincarnList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 70 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 75 */     this.pid = 0L;
/* 76 */     this.reincarnId = 0;
/* 77 */     this.reincarnList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     String retVal = "{\"pid\":" + this.pid + ",\"reincarnId\":" + this.reincarnId + ",\"reincarnList\":" + this.reincarnList.toString() + "}";
/* 83 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\partner\PartnerReincarnResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */