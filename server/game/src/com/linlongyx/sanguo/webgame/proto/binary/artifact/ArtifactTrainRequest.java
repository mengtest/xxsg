/*    */ package com.linlongyx.sanguo.webgame.proto.binary.artifact;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class ArtifactTrainRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int id;
/*    */   public int type;
/*    */   public int num;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.id);
/* 31 */     ProtocolUtil.writeInt(out, this.type);
/* 32 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ArtifactTrainRequest clone() throws CloneNotSupportedException {
/* 44 */     ArtifactTrainRequest clone = (ArtifactTrainRequest)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.id = 0;
/* 51 */     this.type = 0;
/* 52 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"id\":" + this.id + ",\"type\":" + this.type + ",\"num\":" + this.num + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\artifact\ArtifactTrainRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */