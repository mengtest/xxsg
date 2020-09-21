/*    */ package com.linlongyx.sanguo.webgame.app.luckyTurntable;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.LuckylistBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
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
/*    */ public class LuckyTurntableComponent
/*    */   extends AbstractMapComponent<LuckyTurntableEntity>
/*    */ {
/* 20 */   public static int TYPE_LUCKY_TURNTABLE_KEY = 1;
/* 21 */   public static int TYPE_PET_TURNTABLE_KEY = 2;
/* 22 */   private Map<Integer, ArrayList<Reward>> rewardMap = new HashMap<>();
/*    */   
/*    */   public LuckyTurntableComponent(long playerId) {
/* 25 */     super(LuckyTurntableEntity.class, playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 30 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 35 */     this.playerId = playerId;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean reset(int time) {
/* 40 */     if (time == 0) {
/* 41 */       for (Integer actId : JsonTableService.getJsonTableKey(LuckylistBean.class)) {
/* 42 */         if (getEntity(actId.intValue()) != null) {
/* 43 */           LuckylistBean bean = (LuckylistBean)JsonTableService.getJsonData(actId.intValue(), LuckylistBean.class);
/* 44 */           getEntity(actId.intValue()).setFreeTimes(bean.getFreeTimes());
/*    */         } 
/*    */       } 
/* 47 */       saveToDB();
/*    */     } 
/* 49 */     return true;
/*    */   }
/*    */   
/*    */   public LuckyTurntableEntity getEntity(int id) {
/* 53 */     return (LuckyTurntableEntity)getEntity(String.valueOf(id));
/*    */   }
/*    */   
/*    */   public Map<Integer, ArrayList<Reward>> getRewardMap() {
/* 57 */     return this.rewardMap;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\luckyTurntable\LuckyTurntableComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */