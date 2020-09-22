/*     */ package com.linlongyx.sanguo.webgame.app.invitation;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InvitationComponent
/*     */   extends AbstractComponent<InvitationEntity>
/*     */ {
/*     */   public InvitationComponent(long playerId) {
/*  18 */     super(InvitationEntity.class);
/*  19 */     this.playerId = playerId;
/*  20 */     makeKey();
/*  21 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */   
/*     */   public String getType() {
/*  25 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  31 */     setPlayerId(playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  36 */     if (time == 0) {
/*  37 */       setRecruitInvitation(false);
/*  38 */       setInvitationNum(0);
/*     */     } 
/*  40 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isRecruitInvitation() {
/*  44 */     return ((InvitationEntity)getEntity()).isRecruitInvitation();
/*     */   }
/*     */   
/*     */   public void setRecruitInvitation(boolean recruitInvitation) {
/*  48 */     ((InvitationEntity)getEntity()).setRecruitInvitation(recruitInvitation);
/*     */   }
/*     */   
/*     */   public ArrayList<WxPlayerInfo> getWxInfo() {
/*  52 */     return ((InvitationEntity)getEntity()).getWxInfo();
/*     */   }
/*     */   
/*     */   public void setWxInfo(ArrayList<WxPlayerInfo> wxInfo) {
/*  56 */     ((InvitationEntity)getEntity()).setWxInfo(wxInfo);
/*     */   }
/*     */   
/*     */   public Map<Integer, ArrayList<Long>> getLevelRewards() {
/*  60 */     return ((InvitationEntity)getEntity()).getLevelRewards();
/*     */   }
/*     */   
/*     */   public void setLevelRewards(Map<Integer, ArrayList<Long>> levelRewards) {
/*  64 */     ((InvitationEntity)getEntity()).setLevelRewards(levelRewards);
/*     */   }
/*     */   
/*     */   public Map<Integer, ArrayList<Long>> getChargeRewards() {
/*  68 */     return ((InvitationEntity)getEntity()).getChargeRewards();
/*     */   }
/*     */   
/*     */   public void setChargeRewards(Map<Integer, ArrayList<Long>> chargeRewards) {
/*  72 */     ((InvitationEntity)getEntity()).setChargeRewards(chargeRewards);
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getNormalRewards() {
/*  76 */     return ((InvitationEntity)getEntity()).getNormalRewards();
/*     */   }
/*     */   
/*     */   public void setNormalRewards(ArrayList<Integer> normalRewards) {
/*  80 */     ((InvitationEntity)getEntity()).setNormalRewards(normalRewards);
/*     */   }
/*     */   
/*     */   public int getInvitationNum() {
/*  84 */     return ((InvitationEntity)getEntity()).getInvitationNum();
/*     */   }
/*     */   
/*     */   public void setInvitationNum(int invitationNum) {
/*  88 */     ((InvitationEntity)getEntity()).setInvitationNum(invitationNum);
/*     */   }
/*     */   
/*     */   public int getInvitationTotal() {
/*  92 */     return ((InvitationEntity)getEntity()).getInvitationTotal();
/*     */   }
/*     */   
/*     */   public void setInvitationTotal(int invitationTotal) {
/*  96 */     ((InvitationEntity)getEntity()).setInvitationTotal(invitationTotal);
/*     */   }
/*     */   
/*     */   public int getNextTime() {
/* 100 */     return ((InvitationEntity)getEntity()).getNextTime();
/*     */   }
/*     */   
/*     */   public void setNextTime(int nextTime) {
/* 104 */     ((InvitationEntity)getEntity()).setNextTime(nextTime);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getLevelNumMap() {
/* 108 */     return ((InvitationEntity)getEntity()).getLevelNumMap();
/*     */   }
/*     */   
/*     */   public void setLevelNumMap(Map<Integer, Integer> levelNumMap) {
/* 112 */     ((InvitationEntity)getEntity()).setLevelNumMap(levelNumMap);
/*     */   }
/*     */   
/*     */   public Map<Long, Integer> getNumMap() {
/* 116 */     return ((InvitationEntity)getEntity()).getNumMap();
/*     */   }
/*     */   
/*     */   public void setNumMap(Map<Long, Integer> numMap) {
/* 120 */     ((InvitationEntity)getEntity()).setNumMap(numMap);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\invitation\InvitationComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */