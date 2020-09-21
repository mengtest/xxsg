/*    */ package com.linlongyx.sanguo.webgame.processors.wander;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.WanderBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.wander.WanderInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WanderUtil
/*    */ {
/* 18 */   private static final Object wanderLock = new Object();
/*    */   
/*    */   public static void sendWander() {
/* 21 */     for (Long playerId : LookUpService.getOnlinePlayer()) {
/*    */       
/* 23 */       IPlayer iPlayer = LookUpService.getByPlayerId(playerId.longValue());
/* 24 */       if (iPlayer != null && iPlayer.getSession() != null) {
/* 25 */         (new WanderInfoProcessor()).handle(iPlayer.getSession(), (RequestBase)new WanderInfoRequest());
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static WanderBean getWander(long playerId) {
/* 32 */     Map<Integer, Integer> hmap = new ConcurrentHashMap<>();
/* 33 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/* 34 */     Map<Integer, Object> map = JsonTableService.getJsonTable(WanderBean.class);
/* 35 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 36 */       WanderBean wanderBean = (WanderBean)entry.getValue();
/* 37 */       if (wanderBean.getVip() == playerComponent.getVip()) {
/* 38 */         hmap.put(Integer.valueOf(wanderBean.getLevel()), Integer.valueOf(wanderBean.getId()));
/*    */       }
/*    */     } 
/* 41 */     int id = Integer.MAX_VALUE;
/* 42 */     for (Integer set : hmap.keySet()) {
/* 43 */       if (set.intValue() >= playerComponent.getLevel() && 
/* 44 */         id > set.intValue()) {
/* 45 */         id = set.intValue();
/*    */       }
/*    */     } 
/*    */     
/* 49 */     return (WanderBean)JsonTableService.getJsonData(((Integer)hmap.get(Integer.valueOf(id))).intValue(), WanderBean.class);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\wander\WanderUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */