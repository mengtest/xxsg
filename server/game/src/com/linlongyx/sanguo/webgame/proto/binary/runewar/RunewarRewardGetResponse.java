/*    */ package com.linlongyx.sanguo.webgame.proto.binary.runewar;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
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
/*    */ @Message
/*    */ public class RunewarRewardGetResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int id;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 28 */     ProtocolUtil.writeInt(out, this.type);
/* 29 */     ProtocolUtil.writeInt(out, this.id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 35 */     this.id = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RunewarRewardGetResponse clone() throws CloneNotSupportedException {
/* 40 */     RunewarRewardGetResponse clone = (RunewarRewardGetResponse)super.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 46 */     this.type = 0;
/* 47 */     this.id = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     String retVal = "{\"type\":" + this.type + ",\"id\":" + this.id + "}";
/* 53 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\runewar\RunewarRewardGetResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */