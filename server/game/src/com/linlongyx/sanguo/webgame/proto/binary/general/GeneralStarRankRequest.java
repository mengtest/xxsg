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
/*    */ public class GeneralStarRankRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int level;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 28 */     ProtocolUtil.writeInt(out, this.level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.level = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GeneralStarRankRequest clone() throws CloneNotSupportedException {
/* 38 */     GeneralStarRankRequest clone = (GeneralStarRankRequest)super.clone();
/* 39 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 44 */     this.level = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "{\"level\":" + this.level + "}";
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\general\GeneralStarRankRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */