/*    */ package com.linlongyx.sanguo.webgame.app.souls;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SoulsSetBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SoulsStarBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SoulsComponent
/*    */   extends AbstractMapComponent<SoulsEntity>
/*    */ {
/*    */   public SoulsComponent(long playerId) {
/* 15 */     super(SoulsEntity.class, playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public SoulsEntity getEntity(int id) {
/* 20 */     SoulsEntity soulsEntity = (SoulsEntity)getEntity(String.valueOf(id));
/* 21 */     return soulsEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public void init() {
/* 26 */     super.init();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addSouls(int id) {
/* 33 */     SoulsSetBean soulsBean = (SoulsSetBean)JsonTableService.getJsonData(id, SoulsSetBean.class);
/* 34 */     if (null == soulsBean) {
/*    */       return;
/*    */     }
/* 37 */     SoulsEntity stageEntity = (SoulsEntity)getEntity(String.valueOf(id));
/* 38 */     if (stageEntity != null) {
/*    */       return;
/*    */     }
/* 41 */     stageEntity = new SoulsEntity(this.playerId, id);
/* 42 */     addEntity((IEntity)stageEntity);
/* 43 */     stageEntity.setLevel(1);
/* 44 */     saveToDB();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SoulsStarBean.StarBean getSouls(int id) {
/* 53 */     SoulsEntity soulsEntity = getEntity(id);
/* 54 */     if (null == soulsEntity) {
/* 55 */       return null;
/*    */     }
/* 57 */     SoulsStarBean soulsStarBean = (SoulsStarBean)JsonTableService.getJsonData(id, SoulsStarBean.class);
/* 58 */     return (SoulsStarBean.StarBean)soulsStarBean.getStar().get(Integer.valueOf(soulsEntity.getStar()));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 63 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 68 */     this.playerId = playerId;
/*    */   }
/*    */   
/*    */   public void updateStarDB(int id) {
/* 72 */     getProxy().setUpdate(String.valueOf(id), "star");
/*    */   }
/*    */   
/*    */   public void updateLevelDB(int id) {
/* 76 */     getProxy().setUpdate(String.valueOf(id), "level");
/*    */   }
/*    */   
/*    */   public void updateExpDB(int id) {
/* 80 */     getProxy().setUpdate(String.valueOf(id), "exp");
/*    */   }
/*    */   
/*    */   public void updateFightValueDB(int id) {
/* 84 */     getProxy().setUpdate(String.valueOf(id), "fightValue");
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\souls\SoulsComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */