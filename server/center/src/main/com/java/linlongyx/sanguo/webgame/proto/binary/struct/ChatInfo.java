/*    */ package linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatInfo
/*    */ {
/*    */   public byte type;
/*    */   public long sendPlayerId;
/*    */   public String sendPlayerName;
/*    */   public int vip;
/*    */   public int titleId;
/*    */   public int sex;
/*    */   public String head;
/*    */   public String context;
/*    */   public int time;
/*    */   public byte teamChat;
/*    */   public int quality;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 26 */     ProtocolUtil.writeByte(out, this.type);
/* 27 */     ProtocolUtil.writeLong(out, this.sendPlayerId);
/* 28 */     ProtocolUtil.writeUTFBinary(out, this.sendPlayerName);
/* 29 */     ProtocolUtil.writeInt(out, this.vip);
/* 30 */     ProtocolUtil.writeInt(out, this.titleId);
/* 31 */     ProtocolUtil.writeInt(out, this.sex);
/* 32 */     ProtocolUtil.writeUTFBinary(out, this.head);
/* 33 */     ProtocolUtil.writeUTFBinary(out, this.context);
/* 34 */     ProtocolUtil.writeInt(out, this.time);
/* 35 */     ProtocolUtil.writeByte(out, this.teamChat);
/* 36 */     ProtocolUtil.writeInt(out, this.quality);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 42 */     this.sendPlayerId = ProtocolUtil.readUTFBinLong(in);
/* 43 */     this.sendPlayerName = ProtocolUtil.readUTFStr(in);
/* 44 */     this.vip = ProtocolUtil.readUTFBinInt(in);
/* 45 */     this.titleId = ProtocolUtil.readUTFBinInt(in);
/* 46 */     this.sex = ProtocolUtil.readUTFBinInt(in);
/* 47 */     this.head = ProtocolUtil.readUTFStr(in);
/* 48 */     this.context = ProtocolUtil.readUTFStr(in);
/* 49 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.teamChat = ProtocolUtil.readUTFBinByte(in);
/* 51 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatInfo clone() throws CloneNotSupportedException {
/* 56 */     ChatInfo clone = (ChatInfo)super.clone();
/* 57 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 62 */     this.type = 0;
/* 63 */     this.sendPlayerId = 0L;
/* 64 */     this.sendPlayerName = null;
/* 65 */     this.vip = 0;
/* 66 */     this.titleId = 0;
/* 67 */     this.sex = 0;
/* 68 */     this.head = null;
/* 69 */     this.context = null;
/* 70 */     this.time = 0;
/* 71 */     this.teamChat = 0;
/* 72 */     this.quality = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     String retVal = "{\"type\":" + this.type + ",\"sendPlayerId\":" + this.sendPlayerId + ",\"sendPlayerName\":" + StringUtil.str2Str(this.sendPlayerName) + ",\"vip\":" + this.vip + ",\"titleId\":" + this.titleId + ",\"sex\":" + this.sex + ",\"head\":" + StringUtil.str2Str(this.head) + ",\"context\":" + StringUtil.str2Str(this.context) + ",\"time\":" + this.time + ",\"teamChat\":" + this.teamChat + ",\"quality\":" + this.quality + "}";
/* 78 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\ChatInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */