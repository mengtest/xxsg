/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HpData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int guid;
/*    */   public long id;
/*    */   public long hp;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     ProtocolUtil.writeInt(out, this.guid);
/* 20 */     ProtocolUtil.writeLong(out, this.id);
/* 21 */     ProtocolUtil.writeLong(out, this.hp);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 26 */     this.guid = ProtocolUtil.readUTFBinInt(in);
/* 27 */     this.id = ProtocolUtil.readUTFBinLong(in);
/* 28 */     this.hp = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public HpData clone() throws CloneNotSupportedException {
/* 33 */     HpData clone = (HpData)super.clone();
/* 34 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 39 */     this.guid = 0;
/* 40 */     this.id = 0L;
/* 41 */     this.hp = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     String retVal = "{\"guid\":" + this.guid + ",\"id\":" + this.id + ",\"hp\":" + this.hp + "}";
/* 47 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\HpData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */