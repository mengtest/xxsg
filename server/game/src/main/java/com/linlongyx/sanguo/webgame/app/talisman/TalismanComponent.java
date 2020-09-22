/*    */ package com.linlongyx.sanguo.webgame.app.talisman;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TalismanComponent
/*    */   extends AbstractMapComponent<TalismanEntity>
/*    */ {
/* 19 */   public static int TYPE_TALISMAN_KEY = 1;
/* 20 */   private Map<Integer, ArrayList<Reward>> rewardMap = new HashMap<>();
/*    */ 
/*    */   
/*    */   public TalismanComponent(long playerId) {
/* 24 */     super(TalismanEntity.class, playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 29 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 34 */     this.playerId = playerId;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean reset(int time) {
/* 39 */     if (time == 0) {
/* 40 */       for (String key : getEntityMap().keySet()) {
/* 41 */         TalismanEntity talismanEntity = (TalismanEntity)getEntity(key);
/* 42 */         talismanEntity.setFreeTimes(1);
/* 43 */         talismanEntity.setFreeRefresh(1);
/* 44 */         talismanEntity.setRefreshTimes(0);
/* 45 */         LogUtil.errorLog(new Object[] { "talisman reset, id", key });
/*    */       } 
/*    */     }
/* 48 */     return true;
/*    */   }
/*    */   
/*    */   public Map<Integer, ArrayList<Reward>> getRewardMap() {
/* 52 */     return this.rewardMap;
/*    */   }
/*    */   
/*    */   public void setRewardMap(Map<Integer, ArrayList<Reward>> rewardMap) {
/* 56 */     this.rewardMap = rewardMap;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\talisman\TalismanComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */