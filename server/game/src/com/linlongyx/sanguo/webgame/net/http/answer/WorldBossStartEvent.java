/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.bosshome.WorldBossUtil;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldBossStartEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 18 */     WorldBossUtil.openBoss();
/* 19 */     return String.valueOf(10001);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\WorldBossStartEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */