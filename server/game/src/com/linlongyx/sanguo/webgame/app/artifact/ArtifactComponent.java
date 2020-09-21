/*    */ package com.linlongyx.sanguo.webgame.app.artifact;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArtifactComponent
/*    */   extends AbstractMapComponent<ArtifactEntity>
/*    */ {
/*    */   public ArtifactComponent(long playerId) {
/* 12 */     super(ArtifactEntity.class, playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 17 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 22 */     this.playerId = playerId;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean reset(int time) {
/* 27 */     if (time == 0);
/*    */ 
/*    */     
/* 30 */     return true;
/*    */   }
/*    */   
/*    */   public ArtifactEntity getArtifactEntity(int id) {
/* 34 */     return (ArtifactEntity)getEntity(String.valueOf(id));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addArtifactEntity(long playerId, int id) {
/* 43 */     if (null != getArtifactEntity(id)) {
/*    */       return;
/*    */     }
/* 46 */     ArtifactEntity artifactEntity = new ArtifactEntity(playerId, id);
/* 47 */     addEntity((IEntity)artifactEntity);
/*    */   }
/*    */   
/*    */   public void updateAttrsToDB(int id) {
/* 51 */     getProxy().setUpdate(String.valueOf(id), "attrs");
/*    */   }
/*    */   
/*    */   public void updateTrainDataMapToDB(int id) {
/* 55 */     getProxy().setUpdate(String.valueOf(id), "trainDataMap");
/*    */   }
/*    */   
/*    */   public void updateTrianTypeToDB(int id) {
/* 59 */     getProxy().setUpdate(String.valueOf(id), "trianType");
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\artifact\ArtifactComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */