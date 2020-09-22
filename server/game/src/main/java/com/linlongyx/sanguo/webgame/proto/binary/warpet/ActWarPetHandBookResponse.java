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
/*    */ public class ActWarPetHandBookResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int handType;
/*    */   public int handbookId;
/*    */   public int star;
/* 23 */   public ArrayList<KeyValue> infos = new ArrayList<>();
/*    */   
/*    */   public ActWarPetHandBookResponse() {
/* 26 */     this.eventResponseId = 26407;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ActWarPetHandBookResponse(short retCode) {
/* 31 */     this.eventResponseId = 26407;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeInt(out, this.handType);
/* 39 */     ProtocolUtil.writeInt(out, this.handbookId);
/* 40 */     ProtocolUtil.writeInt(out, this.star);
/*    */     
/* 42 */     int size_0 = this.infos.size();
/* 43 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       KeyValue tmp_0 = this.infos.get(index_0);
/* 47 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 53 */     this.handType = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.handbookId = ProtocolUtil.readUTFBinInt(in);
/* 55 */     this.star = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 57 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 60 */       KeyValue tmp_0 = new KeyValue();
/* 61 */       tmp_0.unserial(in);
/* 62 */       this.infos.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ActWarPetHandBookResponse clone() throws CloneNotSupportedException {
/* 68 */     ActWarPetHandBookResponse clone = (ActWarPetHandBookResponse)super.clone();
/* 69 */     int size_0 = this.infos.size();
/* 70 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 72 */       KeyValue tmp_0 = this.infos.get(index_0);
/* 73 */       clone.infos.add(tmp_0.clone());
/*    */     } 
/* 75 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 80 */     this.handType = 0;
/* 81 */     this.handbookId = 0;
/* 82 */     this.star = 0;
/* 83 */     this.infos.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     String retVal = "{\"handType\":" + this.handType + ",\"handbookId\":" + this.handbookId + ",\"star\":" + this.star + ",\"infos\":" + this.infos.toString() + "}";
/* 89 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\warpet\ActWarPetHandBookResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */