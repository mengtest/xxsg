/*    */ package com.linlongyx.sanguo.webgame.proto.binary.sanguozhi;
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
/*    */ public class ActivateRecordStarResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int recordStar;
/* 20 */   public ArrayList<Integer> starList = new ArrayList<>();
/*    */   public int quality;
/*    */   
/*    */   public ActivateRecordStarResponse() {
/* 24 */     this.eventResponseId = 25403;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ActivateRecordStarResponse(short retCode) {
/* 29 */     this.eventResponseId = 25403;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeInt(out, this.recordStar);
/*    */     
/* 38 */     int size_0 = this.starList.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       int tmp_0 = ((Integer)this.starList.get(index_0)).intValue();
/* 43 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/* 45 */     ProtocolUtil.writeInt(out, this.quality);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 50 */     this.recordStar = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 52 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 53 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 55 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 56 */       this.starList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 58 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ActivateRecordStarResponse clone() throws CloneNotSupportedException {
/* 63 */     ActivateRecordStarResponse clone = (ActivateRecordStarResponse)super.clone();
/* 64 */     int size_0 = this.starList.size();
/* 65 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 67 */       int tmp_0 = ((Integer)this.starList.get(index_0)).intValue();
/* 68 */       clone.starList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 70 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 75 */     this.recordStar = 0;
/* 76 */     this.starList.clear();
/* 77 */     this.quality = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     String retVal = "{\"recordStar\":" + this.recordStar + ",\"starList\":" + this.starList.toString() + ",\"quality\":" + this.quality + "}";
/* 83 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\sanguozhi\ActivateRecordStarResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */