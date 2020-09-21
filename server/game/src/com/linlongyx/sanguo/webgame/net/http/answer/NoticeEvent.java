/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.destiny.DestinyComponent;
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.ArrayList;
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
/*    */ public class NoticeEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 24 */     if (!p.containsKey("type")) {
/* 25 */       LookUpService.getOnlinePlayer().forEach(id -> {
/*    */             IPlayer player = LookUpService.getByPlayerId(id.longValue());
/*    */             DestinyComponent destinyComponent = (DestinyComponent)player.getComponent(DestinyComponent.class);
/*    */             if (destinyComponent != null) {
/*    */               destinyComponent.reset(0);
/*    */             }
/*    */           });
/*    */     } else {
/* 33 */       String des = ((List<String>)p.get("des")).get(0);
/* 34 */       ArrayList<String> list = new ArrayList<>();
/* 35 */       list.add(des);
/* 36 */       LookUpService.radiate(603, list);
/*    */     } 
/* 38 */     return String.valueOf(10001);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\NoticeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */