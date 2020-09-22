/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.sanguo.webgame.net.http.answer.HttpEventType;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.destiny.DestinyUtil;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 17 */     if (strings[2].equals("clear")) {
/* 18 */       HttpEventType.CLEARCROSSDATA.getiHttpEvent().process(new HashMap<>());
/* 19 */     } else if (strings[2].equals("state")) {
/* 20 */       CrossUtil.gmMatch("CrossGm::matchState", Integer.parseInt(strings[3]));
/* 21 */     } else if (strings[2].equals("clearGroup")) {
/* 22 */       DestinyUtil.groupPkDataMap.clear();
/* 23 */     } else if (strings[2].equals("battle")) {
/* 24 */       int state = Integer.parseInt(strings[3]);
/* 25 */       CrossUtil.gmBattle("CrossGm::batleState", state);
/* 26 */     } else if (strings[2].equals("destiny")) {
/* 27 */       int serverId = Integer.parseInt(strings[3]);
/* 28 */       long playerId = Long.parseLong(strings[4]);
/* 29 */       int point = Integer.parseInt(strings[5]);
/* 30 */       CrossUtil.addDestinyPoint("CrossGm::addPoint", serverId, playerId, point);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\CrossGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */