/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public byte sex;
/*    */   public int title;
/*    */   public int fashion;
/*    */   public String nickname;
/*    */   public int mounts;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 24 */     ProtocolUtil.writeLong(out, this.playerId);
/* 25 */     ProtocolUtil.writeByte(out, this.sex);
/* 26 */     ProtocolUtil.writeInt(out, this.title);
/* 27 */     ProtocolUtil.writeInt(out, this.fashion);
/* 28 */     ProtocolUtil.writeUTFBinary(out, this.nickname);
/* 29 */     ProtocolUtil.writeInt(out, this.mounts);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 35 */     this.sex = ProtocolUtil.readUTFBinByte(in);
/* 36 */     this.title = ProtocolUtil.readUTFBinInt(in);
/* 37 */     this.fashion = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.nickname = ProtocolUtil.readUTFStr(in);
/* 39 */     this.mounts = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelData clone() throws CloneNotSupportedException {
/* 44 */     ModelData clone = (ModelData)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.playerId = 0L;
/* 51 */     this.sex = 0;
/* 52 */     this.title = 0;
/* 53 */     this.fashion = 0;
/* 54 */     this.nickname = null;
/* 55 */     this.mounts = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     String retVal = "{\"playerId\":" + this.playerId + ",\"sex\":" + this.sex + ",\"title\":" + this.title + ",\"fashion\":" + this.fashion + ",\"nickname\":" + StringUtil.str2Str(this.nickname) + ",\"mounts\":" + this.mounts + "}";
/* 61 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ModelData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */