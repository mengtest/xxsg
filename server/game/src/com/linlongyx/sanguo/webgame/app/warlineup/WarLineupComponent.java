/*     */ package com.linlongyx.sanguo.webgame.app.warlineup;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaBean;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WarLineupComponent
/*     */   extends AbstractMapComponent<WarLineupEntity>
/*     */ {
/*     */   public WarLineupComponent(long playerId) {
/*  15 */     super(WarLineupEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public WarLineupEntity getEntity(int id) {
/*  20 */     WarLineupEntity warLineupEntity = (WarLineupEntity)getEntity(String.valueOf(id));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  26 */     return warLineupEntity;
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
/*     */ 
/*     */   
/*     */   public void addWarLineup(int id) {
/*  42 */     ZhenfaBean zhenfaBean = (ZhenfaBean)JsonTableService.getJsonData(id, ZhenfaBean.class);
/*  43 */     if (null == zhenfaBean) {
/*     */       return;
/*     */     }
/*  46 */     WarLineupEntity warLineupEntity = (WarLineupEntity)getEntity(String.valueOf(id));
/*  47 */     if (warLineupEntity != null) {
/*     */       return;
/*     */     }
/*  50 */     warLineupEntity = new WarLineupEntity(this.playerId, id);
/*  51 */     addEntity((IEntity)warLineupEntity);
/*  52 */     warLineupEntity.setLevel(1);
/*  53 */     saveToDB();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WarLineupEntity getBattleWarLineup() {
/*  63 */     WarLineupEntity warLineupEntity = null;
/*  64 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/*  65 */       WarLineupEntity warLineupEntity1 = (WarLineupEntity)iMapEntity;
/*  66 */       if (warLineupEntity1.getBattle() == 1) {
/*  67 */         warLineupEntity = warLineupEntity1;
/*     */         break;
/*     */       } 
/*     */     } 
/*  71 */     return warLineupEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBattleWarlineUpId() {
/*  80 */     WarLineupEntity warLineupEntity = getBattleWarLineup();
/*  81 */     return (warLineupEntity == null) ? 0 : warLineupEntity.getWarLineupId();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ZhenfaBean.StarBean getBattleStarBean() {
/*  90 */     WarLineupEntity warLineupEntity = getBattleWarLineup();
/*  91 */     if (null == warLineupEntity) {
/*  92 */       return null;
/*     */     }
/*  94 */     int star = warLineupEntity.getStar();
/*  95 */     ZhenfaBean zhenfaBean = (ZhenfaBean)JsonTableService.getJsonData(warLineupEntity.getWarLineupId(), ZhenfaBean.class);
/*  96 */     ZhenfaBean.StarBean starBean = (ZhenfaBean.StarBean)zhenfaBean.getStar().get(Integer.valueOf(star));
/*  97 */     return starBean;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/* 102 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/* 107 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public void updateBattleDB(int id) {
/* 111 */     getProxy().setUpdate(String.valueOf(id), "battle");
/*     */   }
/*     */   
/*     */   public void updateStarDB(int id) {
/* 115 */     getProxy().setUpdate(String.valueOf(id), "star");
/*     */   }
/*     */   
/*     */   public void updateLevelDB(int id) {
/* 119 */     getProxy().setUpdate(String.valueOf(id), "level");
/*     */   }
/*     */   
/*     */   public void updateExpDB(int id) {
/* 123 */     getProxy().setUpdate(String.valueOf(id), "exp");
/*     */   }
/*     */   
/*     */   public void updateFightValueDB(int id) {
/* 127 */     getProxy().setUpdate(String.valueOf(id), "fightValue");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\warlineup\WarLineupComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */