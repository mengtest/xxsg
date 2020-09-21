/*    */ package com.linlongyx.sanguo.webgame.proto.binary.warpet;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
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
/*    */ public class WarPetHandBookInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int handType;
/* 21 */   public ArrayList<KeyValue> infos = new ArrayList<>();
/*    */   
/*    */   public WarPetHandBookInfoResponse() {
/* 24 */     this.eventResponseId = 26406;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WarPetHandBookInfoResponse(short retCode) {
/* 29 */     this.eventResponseId = 26406;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeInt(out, this.handType);
/*    */     
/* 38 */     int size_0 = this.infos.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       KeyValue tmp_0 = this.infos.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.handType = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       KeyValue tmp_0 = new KeyValue();
/* 55 */       tmp_0.unserial(in);
/* 56 */       this.infos.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public WarPetHandBookInfoResponse clone() throws CloneNotSupportedException {
/* 62 */     WarPetHandBookInfoResponse clone = (WarPetHandBookInfoResponse)super.clone();
/* 63 */     int size_0 = this.infos.size();
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       KeyValue tmp_0 = this.infos.get(index_0);
/* 67 */       clone.infos.add(tmp_0.clone());
/*    */     } 
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.handType = 0;
/* 75 */     this.infos.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "{\"handType\":" + this.handType + ",\"infos\":" + this.infos.toString() + "}";
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\warpet\WarPetHandBookInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */