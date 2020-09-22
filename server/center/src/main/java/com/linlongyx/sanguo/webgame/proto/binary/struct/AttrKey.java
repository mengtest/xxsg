/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttrKey
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int id;
/*    */   public int libkey;
/*    */   public int value;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     ProtocolUtil.writeInt(out, this.id);
/* 20 */     ProtocolUtil.writeInt(out, this.libkey);
/* 21 */     ProtocolUtil.writeInt(out, this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 26 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 27 */     this.libkey = ProtocolUtil.readUTFBinInt(in);
/* 28 */     this.value = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public AttrKey clone() throws CloneNotSupportedException {
/* 33 */     AttrKey clone = (AttrKey)super.clone();
/* 34 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 39 */     this.id = 0;
/* 40 */     this.libkey = 0;
/* 41 */     this.value = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     String retVal = "{\"id\":" + this.id + ",\"libkey\":" + this.libkey + ",\"value\":" + this.value + "}";
/* 47 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\AttrKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */