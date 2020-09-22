/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KeyValue
/*    */ {
/*    */   public long key;
/*    */   public long value;
/*    */   public String valueStr;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeLong(out, this.key);
/* 18 */     ProtocolUtil.writeLong(out, this.value);
/* 19 */     ProtocolUtil.writeUTFBinary(out, this.valueStr);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.key = ProtocolUtil.readUTFBinLong(in);
/* 25 */     this.value = ProtocolUtil.readUTFBinLong(in);
/* 26 */     this.valueStr = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public KeyValue clone() throws CloneNotSupportedException {
/* 31 */     KeyValue clone = (KeyValue)super.clone();
/* 32 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.key = 0L;
/* 38 */     this.value = 0L;
/* 39 */     this.valueStr = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "{\"key\":" + this.key + ",\"value\":" + this.value + ",\"valueStr\":" + StringUtil.str2Str(this.valueStr) + "}";
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\KeyValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */