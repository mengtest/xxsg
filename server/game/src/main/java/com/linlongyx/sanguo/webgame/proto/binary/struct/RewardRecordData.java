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
/*    */ public class RewardRecordData
/*    */   implements Serializable
/*    */ {
/*    */   public int time;
/*    */   public long playerId;
/*    */   public String playername;
/*    */   public String desc;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeInt(out, this.time);
/* 21 */     ProtocolUtil.writeLong(out, this.playerId);
/* 22 */     ProtocolUtil.writeUTFBinary(out, this.playername);
/* 23 */     ProtocolUtil.writeUTFBinary(out, this.desc);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 28 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 30 */     this.playername = ProtocolUtil.readUTFStr(in);
/* 31 */     this.desc = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RewardRecordData clone() throws CloneNotSupportedException {
/* 36 */     RewardRecordData clone = (RewardRecordData)super.clone();
/* 37 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 42 */     this.time = 0;
/* 43 */     this.playerId = 0L;
/* 44 */     this.playername = null;
/* 45 */     this.desc = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "{\"time\":" + this.time + ",\"playerId\":" + this.playerId + ",\"playername\":" + StringUtil.str2Str(this.playername) + ",\"desc\":" + StringUtil.str2Str(this.desc) + "}";
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\RewardRecordData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */