/*    */ package com.linlongyx.sanguo.webgame.proto.binary.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlayerData;
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
/*    */ public class EnterSceneResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int sceneId;
/*    */   public short x;
/*    */   public short y;
/* 23 */   public ArrayList<PlayerData> playerList = new ArrayList<>();
/*    */   
/*    */   public EnterSceneResponse() {
/* 26 */     this.eventResponseId = 20101;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public EnterSceneResponse(short retCode) {
/* 31 */     this.eventResponseId = 20101;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeInt(out, this.sceneId);
/* 39 */     ProtocolUtil.writeShort(out, this.x);
/* 40 */     ProtocolUtil.writeShort(out, this.y);
/*    */     
/* 42 */     int size_0 = this.playerList.size();
/* 43 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       PlayerData tmp_0 = this.playerList.get(index_0);
/* 47 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 53 */     this.sceneId = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.x = ProtocolUtil.readUTFBinShort(in);
/* 55 */     this.y = ProtocolUtil.readUTFBinShort(in);
/*    */     
/* 57 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 60 */       PlayerData tmp_0 = new PlayerData();
/* 61 */       tmp_0.unserial(in);
/* 62 */       this.playerList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public EnterSceneResponse clone() throws CloneNotSupportedException {
/* 68 */     EnterSceneResponse clone = (EnterSceneResponse)super.clone();
/* 69 */     int size_0 = this.playerList.size();
/* 70 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 72 */       PlayerData tmp_0 = this.playerList.get(index_0);
/* 73 */       clone.playerList.add(tmp_0.clone());
/*    */     } 
/* 75 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 80 */     this.sceneId = 0;
/* 81 */     this.x = 0;
/* 82 */     this.y = 0;
/* 83 */     this.playerList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     String retVal = "{\"sceneId\":" + this.sceneId + ",\"x\":" + this.x + ",\"y\":" + this.y + ",\"playerList\":" + this.playerList.toString() + "}";
/* 89 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\EnterSceneResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */