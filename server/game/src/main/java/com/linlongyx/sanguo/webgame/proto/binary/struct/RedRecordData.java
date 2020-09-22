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
/*    */ public class RedRecordData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long id;
/*    */   public int time;
/*    */   public String playerName;
/*    */   public int money;
/*    */   public String describe;
/*    */   public int redId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 23 */     ProtocolUtil.writeLong(out, this.id);
/* 24 */     ProtocolUtil.writeInt(out, this.time);
/* 25 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 26 */     ProtocolUtil.writeInt(out, this.money);
/* 27 */     ProtocolUtil.writeUTFBinary(out, this.describe);
/* 28 */     ProtocolUtil.writeInt(out, this.redId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.id = ProtocolUtil.readUTFBinLong(in);
/* 34 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 35 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 36 */     this.money = ProtocolUtil.readUTFBinInt(in);
/* 37 */     this.describe = ProtocolUtil.readUTFStr(in);
/* 38 */     this.redId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RedRecordData clone() throws CloneNotSupportedException {
/* 43 */     RedRecordData clone = (RedRecordData)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.id = 0L;
/* 50 */     this.time = 0;
/* 51 */     this.playerName = null;
/* 52 */     this.money = 0;
/* 53 */     this.describe = null;
/* 54 */     this.redId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     String retVal = "{\"id\":" + this.id + ",\"time\":" + this.time + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"money\":" + this.money + ",\"describe\":" + StringUtil.str2Str(this.describe) + ",\"redId\":" + this.redId + "}";
/* 60 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\RedRecordData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */