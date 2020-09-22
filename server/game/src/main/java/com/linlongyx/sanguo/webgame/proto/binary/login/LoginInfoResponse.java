/*    */ package com.linlongyx.sanguo.webgame.proto.binary.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlayerInfo;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class LoginInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long userId;
/* 22 */   public ArrayList<PlayerInfo> players = new ArrayList<>();
/*    */   public String version;
/*    */   public String tag;
/*    */   public String key;
/*    */   
/*    */   public LoginInfoResponse() {
/* 28 */     this.eventResponseId = 20001;
/* 29 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public LoginInfoResponse(short retCode) {
/* 33 */     this.eventResponseId = 20001;
/* 34 */     this.codeType = 2;
/* 35 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 40 */     ProtocolUtil.writeLong(out, this.userId);
/*    */     
/* 42 */     int size_0 = this.players.size();
/* 43 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       PlayerInfo tmp_0 = this.players.get(index_0);
/* 47 */       tmp_0.serial(out);
/*    */     } 
/* 49 */     ProtocolUtil.writeUTFBinary(out, this.version);
/* 50 */     ProtocolUtil.writeUTFBinary(out, this.tag);
/* 51 */     ProtocolUtil.writeUTFBinary(out, this.key);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 56 */     this.userId = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 58 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 61 */       PlayerInfo tmp_0 = new PlayerInfo();
/* 62 */       tmp_0.unserial(in);
/* 63 */       this.players.add(tmp_0);
/*    */     } 
/* 65 */     this.version = ProtocolUtil.readUTFStr(in);
/* 66 */     this.tag = ProtocolUtil.readUTFStr(in);
/* 67 */     this.key = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LoginInfoResponse clone() throws CloneNotSupportedException {
/* 72 */     LoginInfoResponse clone = (LoginInfoResponse)super.clone();
/* 73 */     int size_0 = this.players.size();
/* 74 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 76 */       PlayerInfo tmp_0 = this.players.get(index_0);
/* 77 */       clone.players.add(tmp_0.clone());
/*    */     } 
/* 79 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 84 */     this.userId = 0L;
/* 85 */     this.players.clear();
/* 86 */     this.version = null;
/* 87 */     this.tag = null;
/* 88 */     this.key = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 93 */     String retVal = "{\"userId\":" + this.userId + ",\"players\":" + this.players.toString() + ",\"version\":" + StringUtil.str2Str(this.version) + ",\"tag\":" + StringUtil.str2Str(this.tag) + ",\"key\":" + StringUtil.str2Str(this.key) + "}";
/* 94 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\LoginInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */