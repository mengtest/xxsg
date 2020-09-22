/*    */ package com.linlongyx.sanguo.webgame.app.chat;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatPersonalInfo;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_chat", prefix = "chat_%serverId_%playerId", isPlayerIdKey = true, keyField = "toId")
/*    */ public class ChatEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final long toId;
/*    */   private String toName;
/* 20 */   private ArrayList<ChatPersonalInfo> privateList = new ArrayList<>();
/*    */   
/*    */   public ChatEntity(long playerId, long toId) {
/* 23 */     this.playerId = playerId;
/* 24 */     this.toId = toId;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 28 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public long getToId() {
/* 32 */     return this.toId;
/*    */   }
/*    */   
/*    */   public String getToName() {
/* 36 */     return this.toName;
/*    */   }
/*    */   
/*    */   public void setToName(String toName) {
/* 40 */     this.toName = toName;
/*    */   }
/*    */   
/*    */   public ArrayList<ChatPersonalInfo> getPrivateList() {
/* 44 */     return this.privateList;
/*    */   }
/*    */   
/*    */   public void setPrivateList(ArrayList<ChatPersonalInfo> privateList) {
/* 48 */     this.privateList = privateList;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     return "ChatEntity{playerId=" + this.playerId + ", toId=" + this.toId + ", toName='" + this.toName + '\'' + ", privateList=" + this.privateList + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 63 */     return Long.valueOf(this.toId);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\chat\ChatEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */