/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.utils.GsonUtil;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GmUtil
/*    */ {
/* 16 */   private static final Logger logger = LoggerFactory.getLogger(GmUtil.class);
/*    */   
/* 18 */   private static final Map<String, IGm> GM_MAP = new HashMap<>();
/*    */   
/*    */   static {
/* 21 */     for (GmType gmType : GmType.values()) {
/* 22 */       GM_MAP.put(gmType.getType(), gmType.getGm());
/*    */     }
/*    */   }
/*    */   
/*    */   public static void gm(IPlayerSession playerSession, String context) {
/* 27 */     String[] strings = context.split("\\s+");
/* 28 */     IGm iGm = GM_MAP.get(strings[1]);
/* 29 */     if (iGm == null) {
/*    */       return;
/*    */     }
/* 32 */     logger.debug(playerSession.getPlayer().getPlayerId() + ":" + GsonUtil.toJson(strings));
/* 33 */     System.out.println(GsonUtil.toJson(strings));
/* 34 */     long bTime = TimeUtil.currentTimeMillis();
/* 35 */     iGm.gm(playerSession, strings);
/* 36 */     long aTime = TimeUtil.currentTimeMillis();
/* 37 */     System.out.println("耗时：" + (aTime - bTime));
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\GmUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */