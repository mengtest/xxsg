/*     */ package com.linlongyx.sanguo.webgame.app.stage;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.StageBean;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StageComponent
/*     */   extends AbstractMapComponent<StageEntity>
/*     */ {
/*     */   public StageComponent(long playerId) {
/*  15 */     super(StageEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public StageEntity getEntity(int id) {
/*  20 */     StageEntity kungFuEntity = (StageEntity)getEntity(String.valueOf(id));
/*     */     
/*  22 */     return kungFuEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  27 */     super.init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStage(int id) {
/*  34 */     StageBean stageBean = (StageBean)JsonTableService.getJsonData(id, StageBean.class);
/*  35 */     if (null == stageBean) {
/*     */       return;
/*     */     }
/*  38 */     StageEntity stageEntity = (StageEntity)getEntity(String.valueOf(id));
/*  39 */     if (stageEntity != null) {
/*     */       return;
/*     */     }
/*  42 */     stageEntity = new StageEntity(this.playerId, id);
/*  43 */     addEntity((IEntity)stageEntity);
/*  44 */     stageEntity.setLevel(1);
/*  45 */     saveToDB();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBattleStageSkill() {
/*  53 */     int skillId = 0;
/*  54 */     StageEntity stageEntity1 = null;
/*  55 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/*  56 */       StageEntity stageEntity = (StageEntity)iMapEntity;
/*  57 */       if (stageEntity.getBattle() == 1) {
/*  58 */         stageEntity1 = stageEntity;
/*     */         break;
/*     */       } 
/*     */     } 
/*  62 */     if (null != stageEntity1) {
/*  63 */       StageBean stageBean = (StageBean)JsonTableService.getJsonData(stageEntity1.getId(), StageBean.class);
/*  64 */       skillId = ((StageBean.StarBean)stageBean.getStar().get(Integer.valueOf(stageEntity1.getStar()))).getSkillID();
/*     */     } 
/*  66 */     return skillId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StageEntity getBattleStage() {
/*  74 */     StageEntity stageEntity1 = null;
/*  75 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/*  76 */       StageEntity stageEntity = (StageEntity)iMapEntity;
/*  77 */       if (stageEntity.getBattle() == 1) {
/*  78 */         stageEntity1 = stageEntity;
/*     */         break;
/*     */       } 
/*     */     } 
/*  82 */     return stageEntity1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  88 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  93 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public void updateBattleDB(int id) {
/*  97 */     getProxy().setUpdate(String.valueOf(id), "battle");
/*     */   }
/*     */   
/*     */   public void updateStarDB(int id) {
/* 101 */     getProxy().setUpdate(String.valueOf(id), "star");
/*     */   }
/*     */   
/*     */   public void updateLevelDB(int id) {
/* 105 */     getProxy().setUpdate(String.valueOf(id), "level");
/*     */   }
/*     */   
/*     */   public void updateExpDB(int id) {
/* 109 */     getProxy().setUpdate(String.valueOf(id), "exp");
/*     */   }
/*     */   
/*     */   public void updateFightValueDB(int id) {
/* 113 */     getProxy().setUpdate(String.valueOf(id), "fightValue");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\stage\StageComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */