/*    */ package com.linlongyx.sanguo.webgame.proto.binary.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SceneDynData;
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
/*    */ public class UpdateSceneDynDataResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<SceneDynData> dynNpcs = new ArrayList<>();
/*    */   
/*    */   public UpdateSceneDynDataResponse() {
/* 23 */     this.eventResponseId = 20106;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public UpdateSceneDynDataResponse(short retCode) {
/* 28 */     this.eventResponseId = 20106;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.dynNpcs.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       SceneDynData tmp_0 = this.dynNpcs.get(index_0);
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
/* 51 */       SceneDynData tmp_0 = new SceneDynData();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.dynNpcs.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public UpdateSceneDynDataResponse clone() throws CloneNotSupportedException {
/* 59 */     UpdateSceneDynDataResponse clone = (UpdateSceneDynDataResponse)super.clone();
/* 60 */     int size_0 = this.dynNpcs.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       SceneDynData tmp_0 = this.dynNpcs.get(index_0);
/* 64 */       clone.dynNpcs.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.dynNpcs.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"dynNpcs\":" + this.dynNpcs.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\UpdateSceneDynDataResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */