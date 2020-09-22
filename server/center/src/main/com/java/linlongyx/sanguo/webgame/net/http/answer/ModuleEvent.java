/*    */ package linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import linlongyx.sanguo.webgame.rmi.destiny.DestinyCache;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModuleEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 34 */     Long playerId = Long.valueOf(Long.parseLong(((List<String>)p.get("playerId")).get(0)));
/* 35 */     Integer serverId = Integer.valueOf(Integer.parseInt(((List<String>)p.get("serverId")).get(0)));
/* 36 */     Integer point = Integer.valueOf(Integer.parseInt(((List<String>)p.get("point")).get(0)));
/* 37 */     DestinyCache.addDestinyPoint(serverId.intValue(), playerId.longValue(), point.intValue());
/* 38 */     return String.valueOf(10001);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\ModuleEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */