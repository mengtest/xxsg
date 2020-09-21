/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FollowerModel
/*    */ {
/*    */   public int fid;
/*    */   public int skin;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 16 */     ProtocolUtil.writeInt(out, this.fid);
/* 17 */     ProtocolUtil.writeInt(out, this.skin);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 22 */     this.fid = ProtocolUtil.readUTFBinInt(in);
/* 23 */     this.skin = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FollowerModel clone() throws CloneNotSupportedException {
/* 28 */     FollowerModel clone = (FollowerModel)super.clone();
/* 29 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 34 */     this.fid = 0;
/* 35 */     this.skin = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     String retVal = "{\"fid\":" + this.fid + ",\"skin\":" + this.skin + "}";
/* 41 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\FollowerModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */