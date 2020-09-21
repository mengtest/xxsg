/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatPersonalInfo
/*    */ {
/*    */   public long sendPlayerId;
/*    */   public int time;
/*    */   public String context;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeLong(out, this.sendPlayerId);
/* 19 */     ProtocolUtil.writeInt(out, this.time);
/* 20 */     ProtocolUtil.writeUTFBinary(out, this.context);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 25 */     this.sendPlayerId = ProtocolUtil.readUTFBinLong(in);
/* 26 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 27 */     this.context = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatPersonalInfo clone() throws CloneNotSupportedException {
/* 32 */     ChatPersonalInfo clone = (ChatPersonalInfo)super.clone();
/* 33 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 38 */     this.sendPlayerId = 0L;
/* 39 */     this.time = 0;
/* 40 */     this.context = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "{\"sendPlayerId\":" + this.sendPlayerId + ",\"time\":" + this.time + ",\"context\":" + StringUtil.str2Str(this.context) + "}";
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ChatPersonalInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */