/*     */ package com.linlongyx.sanguo.webgame.app.mounts;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MountBean;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MountsComponent
/*     */   extends AbstractMapComponent<MountsEntity>
/*     */ {
/*     */   public MountsComponent(long playerId) {
/*  15 */     super(MountsEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public MountsEntity getEntity(int id) {
/*  20 */     MountsEntity warPetEntity = (MountsEntity)getEntity(String.valueOf(id));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  26 */     return warPetEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  31 */     super.init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMounts(int id) {
/*  40 */     MountBean mountBean = (MountBean)JsonTableService.getJsonData(id, MountBean.class);
/*  41 */     if (null == mountBean) {
/*     */       return;
/*     */     }
/*  44 */     MountsEntity warPetEntity = (MountsEntity)getEntity(String.valueOf(id));
/*  45 */     if (warPetEntity != null) {
/*     */       return;
/*     */     }
/*  48 */     warPetEntity = new MountsEntity(this.playerId, id);
/*  49 */     addEntity((IEntity)warPetEntity);
/*  50 */     warPetEntity.setLevel(1);
/*  51 */     saveToDB();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTurnMounts() {
/*  60 */     int mounts = 0;
/*  61 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/*  62 */       MountsEntity mountsEntity1 = (MountsEntity)iMapEntity;
/*  63 */       if (mountsEntity1.getBattle() == 1) {
/*  64 */         mounts = mountsEntity1.getMountsId();
/*     */         break;
/*     */       } 
/*     */     } 
/*  68 */     return mounts;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MountsEntity getTurnMountEntity() {
/*  76 */     MountsEntity mountsEntity = null;
/*  77 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/*  78 */       MountsEntity mountsEntity1 = (MountsEntity)iMapEntity;
/*  79 */       if (mountsEntity1.getBattle() == 1) {
/*  80 */         mountsEntity = mountsEntity1;
/*     */         break;
/*     */       } 
/*     */     } 
/*  84 */     return mountsEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  90 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  95 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public void updateBattleDB(int id) {
/*  99 */     getProxy().setUpdate(String.valueOf(id), "battle");
/*     */   }
/*     */   
/*     */   public void updateStarDB(int id) {
/* 103 */     getProxy().setUpdate(String.valueOf(id), "star");
/*     */   }
/*     */   
/*     */   public void updateLevelDB(int id) {
/* 107 */     getProxy().setUpdate(String.valueOf(id), "level");
/*     */   }
/*     */   
/*     */   public void updateExpDB(int id) {
/* 111 */     getProxy().setUpdate(String.valueOf(id), "exp");
/*     */   }
/*     */   
/*     */   public void updateBreakDB(int id) {
/* 115 */     getProxy().setUpdate(String.valueOf(id), "breaksLevel");
/*     */   }
/*     */   
/*     */   public void updateFightValueDB(int id) {
/* 119 */     getProxy().setUpdate(String.valueOf(id), "fightValue");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\mounts\MountsComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */