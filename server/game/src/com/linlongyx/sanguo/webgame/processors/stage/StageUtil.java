/*    */ package com.linlongyx.sanguo.webgame.processors.stage;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.sanguo.webgame.app.stage.StageComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.stage.StageEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.StageFightValue;
/*    */ import com.linlongyx.sanguo.webgame.processors.mounts.MountsUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StageUtil
/*    */ {
/*    */   public static void updateStageFightValue(StageEntity stageEntity, IPlayerSession playerSession, StageComponent stageComponent, boolean isActivity) {
/* 19 */     StageFightValue stageFightValue = new StageFightValue(0, false);
/* 20 */     long total = stageFightValue.getStageFightValue(stageEntity, playerSession);
/* 21 */     stageEntity.setFightValue(total);
/* 22 */     stageComponent.updateFightValueDB(stageEntity.getId());
/* 23 */     if (!isActivity)
/* 24 */       MountsUtil.updateFightValue(playerSession, 4, stageEntity.getId(), stageEntity.getFightValue()); 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\stage\StageUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */