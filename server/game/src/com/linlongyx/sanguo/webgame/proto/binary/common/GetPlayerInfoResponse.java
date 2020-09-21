/*    */ package com.linlongyx.sanguo.webgame.proto.binary.common;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class GetPlayerInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long playerId;
/*    */   public String playerName;
/*    */   public long fightValue;
/*    */   public String head;
/*    */   public int level;
/*    */   public String blocName;
/*    */   public int vip;
/*    */   public int titleId;
/*    */   public int fashionId;
/*    */   public int mounts;
/*    */   public int quality;
/*    */   
/*    */   public GetPlayerInfoResponse() {
/* 32 */     this.eventResponseId = 21010;
/* 33 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GetPlayerInfoResponse(short retCode) {
/* 37 */     this.eventResponseId = 21010;
/* 38 */     this.codeType = 2;
/* 39 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 44 */     ProtocolUtil.writeLong(out, this.playerId);
/* 45 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 46 */     ProtocolUtil.writeLong(out, this.fightValue);
/* 47 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 48 */     ProtocolUtil.writeInt(out, this.level);
/* 49 */     ProtocolUtil.writeUTFBinary(out, this.blocName);
/* 50 */     ProtocolUtil.writeInt(out, this.vip);
/* 51 */     ProtocolUtil.writeInt(out, this.titleId);
/* 52 */     ProtocolUtil.writeInt(out, this.fashionId);
/* 53 */     ProtocolUtil.writeInt(out, this.mounts);
/* 54 */     ProtocolUtil.writeInt(out, this.quality);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 59 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 60 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 61 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/* 62 */     this.head = ProtocolUtil.readUTFStr(in);
/* 63 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 64 */     this.blocName = ProtocolUtil.readUTFStr(in);
/* 65 */     this.vip = ProtocolUtil.readUTFBinInt(in);
/* 66 */     this.titleId = ProtocolUtil.readUTFBinInt(in);
/* 67 */     this.fashionId = ProtocolUtil.readUTFBinInt(in);
/* 68 */     this.mounts = ProtocolUtil.readUTFBinInt(in);
/* 69 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GetPlayerInfoResponse clone() throws CloneNotSupportedException {
/* 74 */     GetPlayerInfoResponse clone = (GetPlayerInfoResponse)super.clone();
/* 75 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 80 */     this.playerId = 0L;
/* 81 */     this.playerName = null;
/* 82 */     this.fightValue = 0L;
/* 83 */     this.head = null;
/* 84 */     this.level = 0;
/* 85 */     this.blocName = null;
/* 86 */     this.vip = 0;
/* 87 */     this.titleId = 0;
/* 88 */     this.fashionId = 0;
/* 89 */     this.mounts = 0;
/* 90 */     this.quality = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 95 */     String retVal = "{\"playerId\":" + this.playerId + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"fightValue\":" + this.fightValue + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"level\":" + this.level + ",\"blocName\":" + StringUtil.str2Str(this.blocName) + ",\"vip\":" + this.vip + ",\"titleId\":" + this.titleId + ",\"fashionId\":" + this.fashionId + ",\"mounts\":" + this.mounts + ",\"quality\":" + this.quality + "}";
/* 96 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\common\GetPlayerInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */