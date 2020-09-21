/*    */ package com.linlongyx.sanguo.webgame.app.divine;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DivineComponent
/*    */   extends AbstractMapComponent<DivineEntity>
/*    */ {
/* 17 */   private ArrayList<Reward> rewardList = new ArrayList<>();
/*    */   
/*    */   public DivineComponent(long playerId) {
/* 20 */     super(DivineEntity.class, playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 25 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 30 */     this.playerId = playerId;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean reset(int time) {
/* 35 */     if (time == 0);
/*    */ 
/*    */     
/* 38 */     return true;
/*    */   }
/*    */   
/*    */   public DivineEntity getEntity(int id) {
/* 42 */     DivineEntity divineEntity = (DivineEntity)getEntity(String.valueOf(id));
/* 43 */     if (divineEntity == null) {
/* 44 */       divineEntity = new DivineEntity(this.playerId, id);
/* 45 */       addEntity((IEntity)divineEntity);
/*    */     } 
/* 47 */     return divineEntity;
/*    */   }
/*    */   
/*    */   public ArrayList<Reward> getRewardList() {
/* 51 */     return this.rewardList;
/*    */   }
/*    */   
/*    */   public void setRewardList(ArrayList<Reward> rewardList) {
/* 55 */     this.rewardList = rewardList;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\divine\DivineComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */