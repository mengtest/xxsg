/*    */ package com.linlongyx.sanguo.webgame.processors.warpet;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.WarPetFightValue;
/*    */ import com.linlongyx.sanguo.webgame.constant.CrossRankActType;
/*    */ import com.linlongyx.sanguo.webgame.constant.RankActType;
/*    */ import com.linlongyx.sanguo.webgame.processors.crossRankAct.CrossRankActUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.mounts.MountsUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WarPetUtil
/*    */ {
/*    */   public static void updateWarPetFightValue(WarPetEntity warPetEntity, IPlayerSession playerSession, WarPetComponent warPetComponent, boolean isActivity) {
/* 22 */     WarPetFightValue warPetFightValue = new WarPetFightValue(0, false);
/* 23 */     long total = warPetFightValue.getWarPetFightValue(warPetEntity, playerSession);
/* 24 */     warPetEntity.setFightValue(total);
/* 25 */     warPetComponent.updateFightValueDB(warPetEntity.getWarPetId());
/* 26 */     RankActUtil.refreshRankValue(RankActType.WarPet.getType(), total, warPetComponent.getPlayerId());
/* 27 */     RankActUtil.refreshRankValue(RankActType.WarPet2.getType(), total, warPetComponent.getPlayerId());
/* 28 */     CrossRankActUtil.refreshRankValue(CrossRankActType.WarPet.getType(), total, warPetComponent.getPlayerId());
/* 29 */     if (!isActivity) {
/* 30 */       MountsUtil.updateFightValue(playerSession, 2, warPetEntity.getWarPetId(), warPetEntity.getFightValue());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static long getMaxFightValue(WarPetComponent warPetComponent) {
/* 41 */     long fightValue = 0L;
/* 42 */     for (IMapEntity iMapEntity : warPetComponent.getEntityMap().values()) {
/* 43 */       WarPetEntity warPetEntity = (WarPetEntity)iMapEntity;
/* 44 */       if (fightValue < warPetEntity.getFightValue()) {
/* 45 */         fightValue = warPetEntity.getFightValue();
/*    */       }
/*    */     } 
/* 48 */     return fightValue;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warpet\WarPetUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */