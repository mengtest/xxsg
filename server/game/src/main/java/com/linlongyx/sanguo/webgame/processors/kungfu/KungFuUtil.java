/*    */ package com.linlongyx.sanguo.webgame.processors.kungfu;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.KungFuFightValue;
/*    */ import com.linlongyx.sanguo.webgame.processors.mounts.MountsUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KungFuUtil
/*    */ {
/*    */   public static void updateKungfuFightValue(KungFuEntity kungFuEntity, IPlayerSession playerSession, KungFuComponent kungFuComponent, boolean isActivity) {
/* 19 */     KungFuFightValue kungFuFightValue = new KungFuFightValue(0, false);
/* 20 */     long total = kungFuFightValue.getKungfuFightValue(kungFuEntity, playerSession);
/* 21 */     kungFuEntity.setFightValue(total);
/* 22 */     kungFuComponent.updateFightValueDB(kungFuEntity.getKungFuId());
/* 23 */     if (!isActivity)
/* 24 */       MountsUtil.updateFightValue(playerSession, 3, kungFuEntity.getKungFuId(), kungFuEntity.getFightValue()); 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\kungfu\KungFuUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */