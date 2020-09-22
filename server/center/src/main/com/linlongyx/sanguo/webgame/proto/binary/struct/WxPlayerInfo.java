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
/*    */ public class WxPlayerInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public long playerId;
/*    */   public String name;
/*    */   public String head;
/*    */   public int level;
/*    */   public long totalCharge;
/*    */   public int quality;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 23 */     ProtocolUtil.writeLong(out, this.playerId);
/* 24 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 25 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 26 */     ProtocolUtil.writeInt(out, this.level);
/* 27 */     ProtocolUtil.writeLong(out, this.totalCharge);
/* 28 */     ProtocolUtil.writeInt(out, this.quality);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 34 */     this.name = ProtocolUtil.readUTFStr(in);
/* 35 */     this.head = ProtocolUtil.readUTFStr(in);
/* 36 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 37 */     this.totalCharge = ProtocolUtil.readUTFBinLong(in);
/* 38 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WxPlayerInfo clone() throws CloneNotSupportedException {
/* 43 */     WxPlayerInfo clone = (WxPlayerInfo)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.playerId = 0L;
/* 50 */     this.name = null;
/* 51 */     this.head = null;
/* 52 */     this.level = 0;
/* 53 */     this.totalCharge = 0L;
/* 54 */     this.quality = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     String retVal = "{\"playerId\":" + this.playerId + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"level\":" + this.level + ",\"totalCharge\":" + this.totalCharge + ",\"quality\":" + this.quality + "}";
/* 60 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\WxPlayerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */