/*    */ package com.linlongyx.sanguo.webgame.proto.binary.general;
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
/*    */ public class GeneralBoxRewardRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int chapter;
/*    */   public int level;
/*    */   public int box;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.chapter);
/* 31 */     ProtocolUtil.writeInt(out, this.level);
/* 32 */     ProtocolUtil.writeInt(out, this.box);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.chapter = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.box = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GeneralBoxRewardRequest clone() throws CloneNotSupportedException {
/* 44 */     GeneralBoxRewardRequest clone = (GeneralBoxRewardRequest)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.chapter = 0;
/* 51 */     this.level = 0;
/* 52 */     this.box = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"chapter\":" + this.chapter + ",\"level\":" + this.level + ",\"box\":" + this.box + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\general\GeneralBoxRewardRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */