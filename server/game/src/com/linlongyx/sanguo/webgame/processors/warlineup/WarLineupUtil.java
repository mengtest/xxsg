/*    */ package com.linlongyx.sanguo.webgame.processors.warlineup;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.WarLineupFightValue;
/*    */ import com.linlongyx.sanguo.webgame.constant.RankActType;
/*    */ import com.linlongyx.sanguo.webgame.processors.mounts.MountsUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WarLineupUtil
/*    */ {
/*    */   public static void updateWarLineupFightValue(WarLineupEntity warLineupEntity, IPlayerSession playerSession, WarLineupComponent warLineupComponent, boolean isActivity) {
/* 20 */     WarLineupFightValue warLineupFightValue = new WarLineupFightValue(0, false);
/* 21 */     long total = warLineupFightValue.getWarLineupFightValue(warLineupEntity, playerSession);
/* 22 */     warLineupEntity.setFightValue(total);
/* 23 */     warLineupComponent.updateFightValueDB(warLineupEntity.getWarLineupId());
/* 24 */     if (!isActivity) {
/* 25 */       MountsUtil.updateFightValue(playerSession, 5, warLineupEntity.getWarLineupId(), warLineupEntity.getFightValue());
/*    */     }
/* 27 */     long totalLevel = 0L;
/* 28 */     for (IMapEntity iMapEntity : warLineupComponent.getEntityMap().values()) {
/* 29 */       WarLineupEntity warLineupEntity1 = (WarLineupEntity)iMapEntity;
/* 30 */       totalLevel += warLineupEntity1.getLevel();
/*    */     } 
/* 32 */     RankActUtil.refreshRankValue(RankActType.ZheFa.getType(), totalLevel, warLineupComponent.getPlayerId());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warlineup\WarLineupUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */