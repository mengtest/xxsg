/*    */ package com.linlongyx.sanguo.webgame.proto.binary.artifact;
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
/*    */ public class ArtifactTrainResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int id;
/*    */   public int type;
/*    */   public int num;
/* 23 */   public ArrayList<KeyValue> tempAttrs = new ArrayList<>();
/*    */   
/*    */   public ArtifactTrainResponse() {
/* 26 */     this.eventResponseId = 23107;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ArtifactTrainResponse(short retCode) {
/* 31 */     this.eventResponseId = 23107;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeInt(out, this.id);
/* 39 */     ProtocolUtil.writeInt(out, this.type);
/* 40 */     ProtocolUtil.writeInt(out, this.num);
/*    */     
/* 42 */     int size_0 = this.tempAttrs.size();
/* 43 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       KeyValue tmp_0 = this.tempAttrs.get(index_0);
/* 47 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 53 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 55 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 57 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 60 */       KeyValue tmp_0 = new KeyValue();
/* 61 */       tmp_0.unserial(in);
/* 62 */       this.tempAttrs.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ArtifactTrainResponse clone() throws CloneNotSupportedException {
/* 68 */     ArtifactTrainResponse clone = (ArtifactTrainResponse)super.clone();
/* 69 */     int size_0 = this.tempAttrs.size();
/* 70 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 72 */       KeyValue tmp_0 = this.tempAttrs.get(index_0);
/* 73 */       clone.tempAttrs.add(tmp_0.clone());
/*    */     } 
/* 75 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 80 */     this.id = 0;
/* 81 */     this.type = 0;
/* 82 */     this.num = 0;
/* 83 */     this.tempAttrs.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     String retVal = "{\"id\":" + this.id + ",\"type\":" + this.type + ",\"num\":" + this.num + ",\"tempAttrs\":" + this.tempAttrs.toString() + "}";
/* 89 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\artifact\ArtifactTrainResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */