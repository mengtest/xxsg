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
/*    */ public class KeyValue
/*    */ {
/*    */   public long key;
/*    */   public long value;
/*    */   public String valueStr;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeLong(out, this.key);
/* 19 */     ProtocolUtil.writeLong(out, this.value);
/* 20 */     ProtocolUtil.writeUTFBinary(out, this.valueStr);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 25 */     this.key = ProtocolUtil.readUTFBinLong(in);
/* 26 */     this.value = ProtocolUtil.readUTFBinLong(in);
/* 27 */     this.valueStr = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public KeyValue clone() throws CloneNotSupportedException {
/* 32 */     KeyValue clone = (KeyValue)super.clone();
/* 33 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 38 */     this.key = 0L;
/* 39 */     this.value = 0L;
/* 40 */     this.valueStr = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "{\"key\":" + this.key + ",\"value\":" + this.value + ",\"valueStr\":" + StringUtil.str2Str(this.valueStr) + "}";
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\KeyValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */