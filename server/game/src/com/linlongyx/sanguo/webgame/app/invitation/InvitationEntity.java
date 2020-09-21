/*     */ package com.linlongyx.sanguo.webgame.app.invitation;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_invitation", prefix = "invitation_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class InvitationEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   private boolean recruitInvitation;
/*  22 */   private ArrayList<WxPlayerInfo> wxInfo = new ArrayList<>();
/*  23 */   private Map<Integer, ArrayList<Long>> levelRewards = (Map<Integer, ArrayList<Long>>)new HashedMap();
/*  24 */   private Map<Integer, ArrayList<Long>> chargeRewards = (Map<Integer, ArrayList<Long>>)new HashedMap();
/*  25 */   private ArrayList<Integer> normalRewards = new ArrayList<>();
/*     */   private int invitationNum;
/*     */   private int invitationTotal;
/*     */   private int nextTime;
/*  29 */   private Map<Integer, Integer> levelNumMap = new HashMap<>();
/*  30 */   private Map<Long, Integer> numMap = new HashMap<>();
/*     */   
/*     */   public InvitationEntity(long playerId) {
/*  33 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  37 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public boolean isRecruitInvitation() {
/*  41 */     return this.recruitInvitation;
/*     */   }
/*     */   
/*     */   public void setRecruitInvitation(boolean recruitInvitation) {
/*  45 */     this.recruitInvitation = recruitInvitation;
/*     */   }
/*     */   
/*     */   public ArrayList<WxPlayerInfo> getWxInfo() {
/*  49 */     return this.wxInfo;
/*     */   }
/*     */   
/*     */   public void setWxInfo(ArrayList<WxPlayerInfo> wxInfo) {
/*  53 */     this.wxInfo = wxInfo;
/*     */   }
/*     */   @Deprecated
/*     */   public Map<Integer, ArrayList<Long>> getLevelRewards() {
/*  57 */     return this.levelRewards;
/*     */   }
/*     */   @Deprecated
/*     */   public void setLevelRewards(Map<Integer, ArrayList<Long>> levelRewards) {
/*  61 */     this.levelRewards = levelRewards;
/*     */   }
/*     */   @Deprecated
/*     */   public Map<Integer, ArrayList<Long>> getChargeRewards() {
/*  65 */     return this.chargeRewards;
/*     */   }
/*     */   @Deprecated
/*     */   public void setChargeRewards(Map<Integer, ArrayList<Long>> chargeRewards) {
/*  69 */     this.chargeRewards = chargeRewards;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getNormalRewards() {
/*  73 */     return this.normalRewards;
/*     */   }
/*     */   
/*     */   public void setNormalRewards(ArrayList<Integer> normalRewards) {
/*  77 */     this.normalRewards = normalRewards;
/*     */   }
/*     */   
/*     */   public int getInvitationNum() {
/*  81 */     return this.invitationNum;
/*     */   }
/*     */   
/*     */   public void setInvitationNum(int invitationNum) {
/*  85 */     this.invitationNum = invitationNum;
/*     */   }
/*     */   
/*     */   public int getInvitationTotal() {
/*  89 */     return this.invitationTotal;
/*     */   }
/*     */   
/*     */   public void setInvitationTotal(int invitationTotal) {
/*  93 */     this.invitationTotal = invitationTotal;
/*     */   }
/*     */   
/*     */   public int getNextTime() {
/*  97 */     return this.nextTime;
/*     */   }
/*     */   
/*     */   public void setNextTime(int nextTime) {
/* 101 */     this.nextTime = nextTime;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getLevelNumMap() {
/* 105 */     return this.levelNumMap;
/*     */   }
/*     */   
/*     */   public void setLevelNumMap(Map<Integer, Integer> levelNumMap) {
/* 109 */     this.levelNumMap = levelNumMap;
/*     */   }
/*     */   
/*     */   public Map<Long, Integer> getNumMap() {
/* 113 */     return this.numMap;
/*     */   }
/*     */   
/*     */   public void setNumMap(Map<Long, Integer> numMap) {
/* 117 */     this.numMap = numMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 122 */     return "ExtendEntity{playerId=" + this.playerId + ", recruitInvitation=" + this.recruitInvitation + ", wxInfo=" + this.wxInfo + ", levelRewards=" + this.levelRewards + ", chargeRewards=" + this.chargeRewards + ", normalRewards=" + this.normalRewards + ", invitationNum=" + this.invitationNum + ", invitationTotal=" + this.invitationTotal + ", nextTime=" + this.nextTime + ", levelNumMap=" + this.levelNumMap + ", numMap=" + this.numMap + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\invitation\InvitationEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */