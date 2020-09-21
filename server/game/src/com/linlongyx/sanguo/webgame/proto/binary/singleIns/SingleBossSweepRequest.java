/*    */ package com.linlongyx.sanguo.webgame.proto.binary.singleIns;
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
/*    */ public class SingleBossSweepRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int type;
/*    */   public int bossId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.type);
/* 30 */     ProtocolUtil.writeInt(out, this.bossId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.bossId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SingleBossSweepRequest clone() throws CloneNotSupportedException {
/* 41 */     SingleBossSweepRequest clone = (SingleBossSweepRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.type = 0;
/* 48 */     this.bossId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"type\":" + this.type + ",\"bossId\":" + this.bossId + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\singleIns\SingleBossSweepRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */