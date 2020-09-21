/*    */ package com.linlongyx.sanguo.webgame.proto.binary.warpet;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WarPetData;
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
/*    */ public class WarPetInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<WarPetData> warPetList = new ArrayList<>();
/*    */   
/*    */   public WarPetInfoResponse() {
/* 23 */     this.eventResponseId = 26401;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WarPetInfoResponse(short retCode) {
/* 28 */     this.eventResponseId = 26401;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.warPetList.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       WarPetData tmp_0 = this.warPetList.get(index_0);
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
/* 51 */       WarPetData tmp_0 = new WarPetData();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.warPetList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public WarPetInfoResponse clone() throws CloneNotSupportedException {
/* 59 */     WarPetInfoResponse clone = (WarPetInfoResponse)super.clone();
/* 60 */     int size_0 = this.warPetList.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       WarPetData tmp_0 = this.warPetList.get(index_0);
/* 64 */       clone.warPetList.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.warPetList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"warPetList\":" + this.warPetList.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\warpet\WarPetInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */