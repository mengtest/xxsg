/*     */ package com.linlongyx.sanguo.webgame.app.kungfu;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.KungfuBean;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KungFuComponent
/*     */   extends AbstractMapComponent<KungFuEntity>
/*     */ {
/*     */   public KungFuComponent(long playerId) {
/*  16 */     super(KungFuEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public KungFuEntity getEntity(int id) {
/*  21 */     KungFuEntity kungFuEntity = (KungFuEntity)getEntity(String.valueOf(id));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  27 */     return kungFuEntity;
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
/*     */   public void addKungFu(int id) {
/*  39 */     KungfuBean kungfuBean = (KungfuBean)JsonTableService.getJsonData(id, KungfuBean.class);
/*  40 */     if (null == kungfuBean) {
/*     */       return;
/*     */     }
/*  43 */     KungFuEntity kungFuEntity = (KungFuEntity)getEntity(String.valueOf(id));
/*  44 */     if (kungFuEntity != null) {
/*     */       return;
/*     */     }
/*  47 */     kungFuEntity = new KungFuEntity(this.playerId, id);
/*  48 */     addEntity((IEntity)kungFuEntity);
/*  49 */     kungFuEntity.setLevel(1);
/*  50 */     saveToDB();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Skill getFurySkill() {
/*  58 */     KungFuEntity kungFuEntity = getBattleKungFu();
/*  59 */     if (kungFuEntity == null) {
/*  60 */       return null;
/*     */     }
/*     */     
/*  63 */     KungfuBean bean = (KungfuBean)JsonTableService.getJsonData(kungFuEntity.getKungFuId(), KungfuBean.class);
/*  64 */     if (null == bean) {
/*  65 */       return null;
/*     */     }
/*  67 */     KungfuBean.StarBean starBean = (KungfuBean.StarBean)bean.getStar().get(Integer.valueOf(kungFuEntity.getStar()));
/*  68 */     return new Skill(starBean.getKungfuSkill(), 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KungFuEntity getBattleKungFu() {
/*  75 */     KungFuEntity kungFuEntity = null;
/*  76 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/*  77 */       KungFuEntity kungFuEntity1 = (KungFuEntity)iMapEntity;
/*  78 */       if (kungFuEntity1.getBattle() == 1) {
/*  79 */         kungFuEntity = kungFuEntity1;
/*     */         break;
/*     */       } 
/*     */     } 
/*  83 */     return kungFuEntity;
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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\kungfu\KungFuComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */