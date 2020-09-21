/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.GroupBossFightSide;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupBossUtil
/*    */ {
/* 19 */   private static final Map<Long, GroupBossFightSide> GROUP_BOSS_MAP = new HashMap<>();
/*    */ 
/*    */   
/*    */   public static void saveBossData() {
/* 23 */     for (GroupBossFightSide fightSide : GROUP_BOSS_MAP.values()) {
/* 24 */       fightSide.saveGroupBoss();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static GroupBossFightSide getGroupBossFightSide(long groupId) {
/* 30 */     if (!GROUP_BOSS_MAP.containsKey(Long.valueOf(groupId))) {
/* 31 */       synchronized (GROUP_BOSS_MAP) {
/* 32 */         if (!GROUP_BOSS_MAP.containsKey(Long.valueOf(groupId))) {
/* 33 */           GroupBossFightSide groupBossFightSide = new GroupBossFightSide();
/* 34 */           groupBossFightSide.initGroupBoss(groupId, false);
/* 35 */           GROUP_BOSS_MAP.put(Long.valueOf(groupId), groupBossFightSide);
/*    */         } 
/*    */       } 
/*    */     }
/* 39 */     return GROUP_BOSS_MAP.get(Long.valueOf(groupId));
/*    */   }
/*    */   
/*    */   public static void resetGroupBossFightSide(GroupEntity groupEntity, GroupBossFightSide groupBossFightSide, List<Integer> resetInsList) {
/* 43 */     groupEntity.setBossHp(groupBossFightSide.getCurHp());
/* 44 */     for (Iterator<Long> iterator = groupEntity.getMemberList().iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/* 45 */       GroupMemberComponent groupMemberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId, GroupMemberComponent.class);
/* 46 */       if (groupMemberComponent != null) {
/* 47 */         for (Iterator<Integer> iterator1 = resetInsList.iterator(); iterator1.hasNext(); ) { int insId = ((Integer)iterator1.next()).intValue();
/* 48 */           if (groupMemberComponent.getPlayerRewards().containsKey(Integer.valueOf(insId)) && ((Integer)groupMemberComponent.getPlayerRewards().get(Integer.valueOf(insId))).intValue() == 2) {
/* 49 */             groupMemberComponent.getPlayerRewards().remove(Integer.valueOf(insId));
/*    */           } }
/*    */         
/* 52 */         groupMemberComponent.setPlayerRewards(groupMemberComponent.getPlayerRewards());
/* 53 */         groupMemberComponent.saveToDB();
/*    */       }  }
/*    */     
/* 56 */     synchronized (GROUP_BOSS_MAP) {
/* 57 */       GROUP_BOSS_MAP.put(Long.valueOf(groupEntity.getId()), groupBossFightSide);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupBossUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */