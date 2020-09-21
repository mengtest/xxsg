/*     */ package com.linlongyx.sanguo.webgame.app.warpet;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.PetBean;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WarPetComponent
/*     */   extends AbstractMapComponent<WarPetEntity>
/*     */ {
/*     */   public WarPetComponent(long playerId) {
/*  16 */     super(WarPetEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public WarPetEntity getEntity(int id) {
/*  21 */     WarPetEntity warPetEntity = (WarPetEntity)getEntity(String.valueOf(id));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  27 */     return warPetEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  32 */     super.init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addWarPet(int id) {
/*  41 */     PetBean petBean = (PetBean)JsonTableService.getJsonData(id, PetBean.class);
/*  42 */     if (null == petBean) {
/*     */       return;
/*     */     }
/*  45 */     WarPetEntity warPetEntity = (WarPetEntity)getEntity(String.valueOf(id));
/*  46 */     if (warPetEntity != null) {
/*     */       return;
/*     */     }
/*  49 */     warPetEntity = new WarPetEntity(this.playerId, id);
/*  50 */     addEntity((IEntity)warPetEntity);
/*  51 */     warPetEntity.setLevel(1);
/*  52 */     saveToDB();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Skill buildBattlePetSkill() {
/*  61 */     WarPetEntity warPetEntity = getBattleWarPet();
/*  62 */     if (warPetEntity == null) {
/*  63 */       return null;
/*     */     }
/*     */     
/*  66 */     PetBean bean = (PetBean)JsonTableService.getJsonData(warPetEntity.getWarPetId(), PetBean.class);
/*  67 */     PetBean.StarBean starBean = (PetBean.StarBean)bean.getStar().get(Integer.valueOf(warPetEntity.getStar()));
/*  68 */     return new Skill(starBean.getPetSkill(), 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WarPetEntity getBattleWarPet() {
/*  75 */     WarPetEntity warPetEntity = null;
/*  76 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/*  77 */       WarPetEntity warPetEntity1 = (WarPetEntity)iMapEntity;
/*  78 */       if (warPetEntity1.getBattle() == 1) {
/*  79 */         warPetEntity = warPetEntity1;
/*     */         break;
/*     */       } 
/*     */     } 
/*  83 */     return warPetEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  89 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  94 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public void updateBattleDB(int id) {
/*  98 */     getProxy().setUpdate(String.valueOf(id), "battle");
/*     */   }
/*     */   
/*     */   public void updateStarDB(int id) {
/* 102 */     getProxy().setUpdate(String.valueOf(id), "star");
/*     */   }
/*     */   
/*     */   public void updateLevelDB(int id) {
/* 106 */     getProxy().setUpdate(String.valueOf(id), "level");
/*     */   }
/*     */   
/*     */   public void updateExpDB(int id) {
/* 110 */     getProxy().setUpdate(String.valueOf(id), "exp");
/*     */   }
/*     */   
/*     */   public void updateFightValueDB(int id) {
/* 114 */     getProxy().setUpdate(String.valueOf(id), "fightValue");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\warpet\WarPetComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */