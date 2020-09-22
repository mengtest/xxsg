/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightConstant;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.AbstractParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
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
/*    */ public class ReloadXmlEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 31 */     if (p.containsKey("tbName")) {
/* 32 */       String[] strings = ((String)((List<String>)p.get("tbName")).get(0)).split(",");
/* 33 */       for (String string : strings) {
/* 34 */         JsonTableService.hotImportJsonTable("com.linlongyx.sanguo.webgame.config.bean." + string);
/* 35 */         if (string.equals("AttrNameBean")) {
/* 36 */           FightConstant.reload();
/*    */         }
/*    */       } 
/* 39 */       ParameterConstant.init();
/* 40 */     } else if (p.containsKey("param")) {
/* 41 */       int type = Integer.parseInt(((List<String>)p.get("param")).get(0));
/* 42 */       AbstractParameter abstractParameter = ParameterConstant.getParameter(type);
/* 43 */       abstractParameter.load();
/*    */     } else {
/* 45 */       JsonTableService.hotImportAllJsonTable();
/* 46 */       ParameterConstant.init();
/*    */     } 
/* 48 */     return String.valueOf(10001);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\ReloadXmlEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */