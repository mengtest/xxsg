/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.welfare.WelfareUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import com.linlongyx.sanguo.webgame.service.StatisticsService;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
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
/*    */ public class SetOpenTimeEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 30 */     if (LookUpService.onlineNum() >= 20) {
/* 31 */       return "Set openTime fail, server online player number > 20.";
/*    */     }
/* 33 */     if (!p.containsKey("openTime")) {
/* 34 */       return "para is error, may not contain openTime";
/*    */     }
/* 36 */     String openTime = ((List<String>)p.get("openTime")).get(0);
/* 37 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*    */     try {
/* 39 */       Date date = sdf.parse(openTime);
/* 40 */     } catch (ParseException e) {
/* 41 */       e.printStackTrace();
/* 42 */       return "para is error, openTime format is error.";
/*    */     } 
/* 44 */     ConstantService.update("openTime", openTime);
/* 45 */     MContext.setOpenTime(((List<String>)p.get("openTime")).get(0));
/* 46 */     MContext.initOpenTimeInt();
/* 47 */     StatisticsService.reset();
/* 48 */     ParameterConstant.init();
/* 49 */     RankActUtil.initRankActMap();
/* 50 */     WelfareUtil.initGrowFundSql();
/* 51 */     WelfareUtil.initGroupFirstChargeSql();
/* 52 */     return String.valueOf(10001);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\SetOpenTimeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */