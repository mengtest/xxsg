/*    */ package com.linlongyx.sanguo.webgame.proto.binary.aoi;
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
/*    */ public class RemoveAvatarResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public ArrayList<Long> delAvatars = new ArrayList<>();
/*    */   
/*    */   public RemoveAvatarResponse() {
/* 22 */     this.eventResponseId = 20111;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RemoveAvatarResponse(short retCode) {
/* 27 */     this.eventResponseId = 20111;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     int size_0 = this.delAvatars.size();
/* 36 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 37 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 39 */       long tmp_0 = ((Long)this.delAvatars.get(index_0)).longValue();
/* 40 */       ProtocolUtil.writeLong(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 47 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 48 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 50 */       long tmp_0 = ProtocolUtil.readUTFBinLong(in);
/* 51 */       this.delAvatars.add(Long.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RemoveAvatarResponse clone() throws CloneNotSupportedException {
/* 57 */     RemoveAvatarResponse clone = (RemoveAvatarResponse)super.clone();
/* 58 */     int size_0 = this.delAvatars.size();
/* 59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 61 */       long tmp_0 = ((Long)this.delAvatars.get(index_0)).longValue();
/* 62 */       clone.delAvatars.add(Long.valueOf(tmp_0));
/*    */     } 
/* 64 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 69 */     this.delAvatars.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "{\"delAvatars\":" + this.delAvatars.toString() + "}";
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\RemoveAvatarResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */