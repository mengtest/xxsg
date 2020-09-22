/*    */ package com.linlongyx.sanguo.webgame.proto.binary.sanguozhi;
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
/*    */ public class SanGuoZhiTaskRewardRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int recordStar;
/*    */   public int taskType;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.recordStar);
/* 30 */     ProtocolUtil.writeInt(out, this.taskType);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.recordStar = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.taskType = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SanGuoZhiTaskRewardRequest clone() throws CloneNotSupportedException {
/* 41 */     SanGuoZhiTaskRewardRequest clone = (SanGuoZhiTaskRewardRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.recordStar = 0;
/* 48 */     this.taskType = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"recordStar\":" + this.recordStar + ",\"taskType\":" + this.taskType + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\sanguozhi\SanGuoZhiTaskRewardRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */