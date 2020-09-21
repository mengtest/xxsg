/*    */ package com.linlongyx.sanguo.webgame.proto.binary.artifact;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArtifactData;
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
/*    */ public class ArtifactListResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<ArtifactData> list = new ArrayList<>();
/*    */   
/*    */   public ArtifactListResponse() {
/* 23 */     this.eventResponseId = 23105;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ArtifactListResponse(short retCode) {
/* 28 */     this.eventResponseId = 23105;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.list.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       ArtifactData tmp_0 = this.list.get(index_0);
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
/* 51 */       ArtifactData tmp_0 = new ArtifactData();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ArtifactListResponse clone() throws CloneNotSupportedException {
/* 59 */     ArtifactListResponse clone = (ArtifactListResponse)super.clone();
/* 60 */     int size_0 = this.list.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       ArtifactData tmp_0 = this.list.get(index_0);
/* 64 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"list\":" + this.list.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\artifact\ArtifactListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */