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
/*    */ public class MarryAppointStruct
/*    */   implements Serializable
/*    */ {
/*    */   public int timeId;
/*    */   public long husbandId;
/*    */   public String husbandName;
/*    */   public long wifeId;
/*    */   public String wifeName;
/*    */   public long appointId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 22 */     ProtocolUtil.writeInt(out, this.timeId);
/* 23 */     ProtocolUtil.writeLong(out, this.husbandId);
/* 24 */     ProtocolUtil.writeUTFBinary(out, this.husbandName);
/* 25 */     ProtocolUtil.writeLong(out, this.wifeId);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.wifeName);
/* 27 */     ProtocolUtil.writeLong(out, this.appointId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 32 */     this.timeId = ProtocolUtil.readUTFBinInt(in);
/* 33 */     this.husbandId = ProtocolUtil.readUTFBinLong(in);
/* 34 */     this.husbandName = ProtocolUtil.readUTFStr(in);
/* 35 */     this.wifeId = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.wifeName = ProtocolUtil.readUTFStr(in);
/* 37 */     this.appointId = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MarryAppointStruct clone() throws CloneNotSupportedException {
/* 42 */     MarryAppointStruct clone = (MarryAppointStruct)super.clone();
/* 43 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 48 */     this.timeId = 0;
/* 49 */     this.husbandId = 0L;
/* 50 */     this.husbandName = null;
/* 51 */     this.wifeId = 0L;
/* 52 */     this.wifeName = null;
/* 53 */     this.appointId = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"timeId\":" + this.timeId + ",\"husbandId\":" + this.husbandId + ",\"husbandName\":" + StringUtil.str2Str(this.husbandName) + ",\"wifeId\":" + this.wifeId + ",\"wifeName\":" + StringUtil.str2Str(this.wifeName) + ",\"appointId\":" + this.appointId + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\MarryAppointStruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */