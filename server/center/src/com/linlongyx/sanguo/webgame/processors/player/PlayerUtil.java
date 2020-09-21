/*    */ package com.linlongyx.sanguo.webgame.processors.player;
/*    */ 
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.structure.ODTime;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerUtil
/*    */ {
/*    */   public static final long PLAYER_PID = -1L;
/* 22 */   public static Map<String, String> tokenToKeyMap = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isRemotePlayer(long playerId) {
/* 31 */     return false;
/*    */   }
/*    */   
/*    */   public static boolean isPrivilege(long playerId, int type) {
/* 35 */     PlayerData playerData = LookUpService.getPlayerData(playerId);
/* 36 */     if (playerData == null) {
/* 37 */       return false;
/*    */     }
/* 39 */     if (AppContext.getDebug() && type == 4)
/* 40 */       return true; 
/* 41 */     ODTime odTime = (ODTime)playerData.getStatus().get(Integer.valueOf(type));
/* 42 */     return (null != odTime && odTime.isValidate(TimeUtil.currentTime()));
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\player\PlayerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */