/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerForbidEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 18 */     if (p.containsKey("playerId") && p.containsKey("flag")) {
/*    */       try {
/* 20 */         String playerId = ((List<String>)p.get("playerId")).get(0);
/* 21 */         byte forbid = Byte.parseByte(((List<String>)p.get("flag")).get(0));
/* 22 */         int ed = Integer.parseInt(((List<String>)p.get("ed")).get(0));
/* 23 */         return String.valueOf(PlayerUtil.silentForbid(playerId, ed, forbid));
/* 24 */       } catch (Exception e) {
/* 25 */         LogUtil.errorLog(new Object[] { Arrays.toString((Object[])e.getStackTrace()) });
/* 26 */         return String.valueOf(10002);
/*    */       } 
/*    */     }
/* 29 */     return String.valueOf(10002);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\PlayerForbidEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */