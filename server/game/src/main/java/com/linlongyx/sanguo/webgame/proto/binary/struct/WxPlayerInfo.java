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
/* 24 */     ProtocolUtil.writeLong(out, this.playerId);
/* 25 */     ProtocolUtil.writeUTFBinary(out, this.name);
/* 26 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 27 */     ProtocolUtil.writeInt(out, this.level);
/* 28 */     ProtocolUtil.writeLong(out, this.totalCharge);
/* 29 */     ProtocolUtil.writeInt(out, this.quality);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 35 */     this.name = ProtocolUtil.readUTFStr(in);
/* 36 */     this.head = ProtocolUtil.readUTFStr(in);
/* 37 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.totalCharge = ProtocolUtil.readUTFBinLong(in);
/* 39 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WxPlayerInfo clone() throws CloneNotSupportedException {
/* 44 */     WxPlayerInfo clone = (WxPlayerInfo)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.playerId = 0L;
/* 51 */     this.name = null;
/* 52 */     this.head = null;
/* 53 */     this.level = 0;
/* 54 */     this.totalCharge = 0L;
/* 55 */     this.quality = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     String retVal = "{\"playerId\":" + this.playerId + ",\"name\":" + StringUtil.str2Str(this.name) + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"level\":" + this.level + ",\"totalCharge\":" + this.totalCharge + ",\"quality\":" + this.quality + "}";
/* 61 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\WxPlayerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */