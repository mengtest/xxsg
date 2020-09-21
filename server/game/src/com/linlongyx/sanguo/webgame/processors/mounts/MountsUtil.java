/*    */ package com.linlongyx.sanguo.webgame.processors.mounts;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.mounts.MountsEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.MountsFightValue;
/*    */ import com.linlongyx.sanguo.webgame.constant.CrossRankActType;
/*    */ import com.linlongyx.sanguo.webgame.constant.RankActType;
/*    */ import com.linlongyx.sanguo.webgame.processors.crossRankAct.CrossRankActUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.UpdateFightValueResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MountsUtil
/*    */ {
/*    */   public static final int TYPE_MOUNTS = 1;
/*    */   public static final int TYPE_WARPET = 2;
/*    */   public static final int TYPE_KUNGFU = 3;
/*    */   public static final int TYPE_STAGE = 4;
/*    */   public static final int TYPE_ZHENFA = 5;
/*    */   
/*    */   public static void updateMountsFightValue(MountsEntity mountsEntity, IPlayerSession playerSession, MountsComponent mountsComponent, boolean isActivity) {
/* 29 */     MountsFightValue mountsFightValue = new MountsFightValue(0, false);
/* 30 */     long total = mountsFightValue.getMountsFightValue(mountsEntity, playerSession);
/* 31 */     mountsEntity.setFightValue(total);
/* 32 */     mountsComponent.updateFightValueDB(mountsEntity.getMountsId());
/* 33 */     RankActUtil.refreshRankValue(RankActType.Mounts.getType(), total, mountsComponent.getPlayerId());
/* 34 */     RankActUtil.refreshRankValue(RankActType.Mounts2.getType(), total, mountsComponent.getPlayerId());
/* 35 */     CrossRankActUtil.refreshRankValue(CrossRankActType.Mounts.getType(), total, mountsComponent.getPlayerId());
/* 36 */     if (!isActivity) {
/* 37 */       updateFightValue(playerSession, 1, mountsEntity.getMountsId(), mountsEntity.getFightValue());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static long getMaxFightValue(MountsComponent mountsComponent) {
/* 47 */     long fightValue = 0L;
/* 48 */     for (IMapEntity iMapEntity : mountsComponent.getEntityMap().values()) {
/* 49 */       MountsEntity mountsEntity = (MountsEntity)iMapEntity;
/* 50 */       if (fightValue < mountsEntity.getFightValue()) {
/* 51 */         fightValue = mountsEntity.getFightValue();
/*    */       }
/*    */     } 
/* 54 */     return fightValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void updateFightValue(IPlayerSession playerSession, int type, int id, long fightValue) {
/* 65 */     UpdateFightValueResponse response = new UpdateFightValueResponse();
/* 66 */     response.type = type;
/* 67 */     response.id = id;
/* 68 */     response.fightValue = fightValue;
/* 69 */     playerSession.sendMessage((ResponseBase)response);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mounts\MountsUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */