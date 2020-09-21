/*     */ package com.linlongyx.sanguo.webgame.app.group;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class GroupMemberComponent
/*     */   extends AbstractComponent<GroupMemberEntity>
/*     */ {
/*  15 */   private List<Long> showList = new ArrayList<>();
/*     */   
/*     */   public GroupMemberComponent(long playerId) {
/*  18 */     super(GroupMemberEntity.class, playerId);
/*  19 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  24 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  29 */     setPlayerId(playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  34 */     if (time == 0) {
/*  35 */       setFightTimes(0);
/*  36 */       setLastResettime(TimeUtil.currentTime() + 60);
/*  37 */       setTotalTimes(0);
/*  38 */       setSacrificeBox(new HashSet<>());
/*  39 */       setSacrificeType(0);
/*     */     } 
/*  41 */     return true;
/*     */   }
/*     */   
/*     */   public List<Long> getShowList() {
/*  45 */     return this.showList;
/*     */   }
/*     */   
/*     */   public void setShowList(List<Long> showList) {
/*  49 */     this.showList = showList;
/*     */   }
/*     */   
/*     */   public long getId() {
/*  53 */     return ((GroupMemberEntity)getEntity()).getId();
/*     */   }
/*     */   
/*     */   public void setId(long id) {
/*  57 */     ((GroupMemberEntity)getEntity()).setId(id);
/*     */   }
/*     */   
/*     */   public int getPosition() {
/*  61 */     return ((GroupMemberEntity)getEntity()).getPosition();
/*     */   }
/*     */   
/*     */   public void setPosition(int position) {
/*  65 */     ((GroupMemberEntity)getEntity()).setPosition(position);
/*     */   }
/*     */   
/*     */   public Set<Long> getApplySet() {
/*  69 */     return ((GroupMemberEntity)getEntity()).getApplySet();
/*     */   }
/*     */   
/*     */   public void setApplySet(Set<Long> applySet) {
/*  73 */     ((GroupMemberEntity)getEntity()).setApplySet(applySet);
/*     */   }
/*     */   
/*     */   public long getTotalOffer() {
/*  77 */     return ((GroupMemberEntity)getEntity()).getTotalOffer();
/*     */   }
/*     */   
/*     */   public void setTotalOffer(long totalOffer) {
/*  81 */     ((GroupMemberEntity)getEntity()).setTotalOffer(totalOffer);
/*     */   }
/*     */   
/*     */   public Set<Integer> getSacrificeBox() {
/*  85 */     return ((GroupMemberEntity)getEntity()).getSacrificeBox();
/*     */   }
/*     */   
/*     */   public void setSacrificeBox(Set<Integer> sacrificeBox) {
/*  89 */     ((GroupMemberEntity)getEntity()).setSacrificeBox(sacrificeBox);
/*     */   }
/*     */   
/*     */   public int getSacrificeType() {
/*  93 */     return ((GroupMemberEntity)getEntity()).getSacrificeType();
/*     */   }
/*     */   
/*     */   public void setSacrificeType(int sacrificeType) {
/*  97 */     ((GroupMemberEntity)getEntity()).setSacrificeType(sacrificeType);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getSkills() {
/* 101 */     return ((GroupMemberEntity)getEntity()).getSkills();
/*     */   }
/*     */   
/*     */   public void setSkills(Map<Integer, Integer> skills) {
/* 105 */     ((GroupMemberEntity)getEntity()).setSkills(skills);
/*     */   }
/*     */   
/*     */   public boolean isListOpen() {
/* 109 */     return ((GroupMemberEntity)getEntity()).isListOpen();
/*     */   }
/*     */   
/*     */   public void setListOpen(boolean listOpen) {
/* 113 */     ((GroupMemberEntity)getEntity()).setListOpen(listOpen);
/*     */   }
/*     */   
/*     */   public HashMap<Integer, Integer> getPlayerRewards() {
/* 117 */     return ((GroupMemberEntity)getEntity()).getPlayerRewards();
/*     */   }
/*     */   
/*     */   public void setPlayerRewards(HashMap<Integer, Integer> playerRewards) {
/* 121 */     ((GroupMemberEntity)getEntity()).setPlayerRewards(playerRewards);
/*     */   }
/*     */   
/*     */   public int getFightTimes() {
/* 125 */     return ((GroupMemberEntity)getEntity()).getFightTimes();
/*     */   }
/*     */   
/*     */   public void setFightTimes(int fightTimes) {
/* 129 */     ((GroupMemberEntity)getEntity()).setFightTimes(fightTimes);
/*     */   }
/*     */   
/*     */   public int getLastResettime() {
/* 133 */     return ((GroupMemberEntity)getEntity()).getLastResettime();
/*     */   }
/*     */   
/*     */   public void setLastResettime(int lastResettime) {
/* 137 */     ((GroupMemberEntity)getEntity()).setLastResettime(lastResettime);
/*     */   }
/*     */   
/*     */   public int getTotalTimes() {
/* 141 */     return ((GroupMemberEntity)getEntity()).getTotalTimes();
/*     */   }
/*     */   
/*     */   public void setTotalTimes(int totalTimes) {
/* 145 */     ((GroupMemberEntity)getEntity()).setTotalTimes(totalTimes);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\group\GroupMemberComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */