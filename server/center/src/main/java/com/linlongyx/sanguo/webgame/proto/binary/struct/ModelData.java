/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JsonIgnoreProperties(ignoreUnknown = true)
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
/* 25 */     ProtocolUtil.writeLong(out, this.playerId);
/* 26 */     ProtocolUtil.writeByte(out, this.sex);
/* 27 */     ProtocolUtil.writeInt(out, this.title);
/* 28 */     ProtocolUtil.writeInt(out, this.fashion);
/* 29 */     ProtocolUtil.writeUTFBinary(out, this.nickname);
/* 30 */     ProtocolUtil.writeInt(out, this.mounts);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.sex = ProtocolUtil.readUTFBinByte(in);
/* 37 */     this.title = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.fashion = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.nickname = ProtocolUtil.readUTFStr(in);
/* 40 */     this.mounts = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelData clone() throws CloneNotSupportedException {
/* 45 */     ModelData clone = (ModelData)super.clone();
/* 46 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 51 */     this.playerId = 0L;
/* 52 */     this.sex = 0;
/* 53 */     this.title = 0;
/* 54 */     this.fashion = 0;
/* 55 */     this.nickname = null;
/* 56 */     this.mounts = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"playerId\":" + this.playerId + ",\"sex\":" + this.sex + ",\"title\":" + this.title + ",\"fashion\":" + this.fashion + ",\"nickname\":" + StringUtil.str2Str(this.nickname) + ",\"mounts\":" + this.mounts + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ModelData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */