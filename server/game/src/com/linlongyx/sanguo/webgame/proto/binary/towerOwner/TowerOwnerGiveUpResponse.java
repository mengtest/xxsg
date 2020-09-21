/*    */ package com.linlongyx.sanguo.webgame.proto.binary.towerOwner;
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
/*    */ public class TowerOwnerGiveUpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int state;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 27 */     ProtocolUtil.writeInt(out, this.state);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 32 */     this.state = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TowerOwnerGiveUpResponse clone() throws CloneNotSupportedException {
/* 37 */     TowerOwnerGiveUpResponse clone = (TowerOwnerGiveUpResponse)super.clone();
/* 38 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 43 */     this.state = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     String retVal = "{\"state\":" + this.state + "}";
/* 49 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\towerOwner\TowerOwnerGiveUpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */