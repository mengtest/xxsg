/*    */ package com.linlongyx.sanguo.webgame.proto.binary.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyPlayerData;
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
/*    */ public class DestinyMyMatchInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 21 */   public ArrayList<DestinyPlayerData> playerList = new ArrayList<>();
/*    */   public String pkId;
/*    */   
/*    */   public DestinyMyMatchInfoResponse() {
/* 25 */     this.eventResponseId = 27410;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 32 */     int size_0 = this.playerList.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       DestinyPlayerData tmp_0 = this.playerList.get(index_0);
/* 37 */       tmp_0.serial(out);
/*    */     } 
/* 39 */     ProtocolUtil.writeUTFBinary(out, this.pkId);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 45 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       DestinyPlayerData tmp_0 = new DestinyPlayerData();
/* 49 */       tmp_0.unserial(in);
/* 50 */       this.playerList.add(tmp_0);
/*    */     } 
/* 52 */     this.pkId = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyMyMatchInfoResponse clone() throws CloneNotSupportedException {
/* 57 */     DestinyMyMatchInfoResponse clone = (DestinyMyMatchInfoResponse)super.clone();
/* 58 */     int size_0 = this.playerList.size();
/* 59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 61 */       DestinyPlayerData tmp_0 = this.playerList.get(index_0);
/* 62 */       clone.playerList.add(tmp_0.clone());
/*    */     } 
/* 64 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 69 */     this.playerList.clear();
/* 70 */     this.pkId = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     String retVal = "{\"playerList\":" + this.playerList.toString() + ",\"pkId\":" + StringUtil.str2Str(this.pkId) + "}";
/* 76 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\destiny\DestinyMyMatchInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */