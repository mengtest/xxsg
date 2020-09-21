/*    */ package com.linlongyx.sanguo.webgame.service;
/*    */ 
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
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
/*    */ public class StatisticsService
/*    */ {
/* 20 */   private static int firstRechargeNum = 0;
/*    */ 
/*    */ 
/*    */   
/*    */   public static void init() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public static void reset() {}
/*    */ 
/*    */   
/*    */   public static void initFirstRechargeNum() {
/* 32 */     MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 33 */     JdbcTemplate template = mysql.getTemplate();
/* 34 */     int time = TimeUtil.timingTime(0, 0);
/* 35 */     String selectSql = "SELECT COUNT(a.playerId) FROM tb_player a, tb_extend b WHERE a.playerId = b.playerId and (a.firstRechargeTime >= " + time + " or  b.sevenRechargeTime >= " + time + ");";
/* 36 */     firstRechargeNum = ((Integer)template.queryForObject(selectSql, Integer.class)).intValue();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getDays() {
/* 46 */     return TimeUtil.getDayDiffToOpen(MContext.getOpenTime());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isSeven() {
/* 54 */     return (getDays() % 7 == 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean inSeven() {
/* 62 */     return (getDays() <= 7);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int weeks() {
/* 70 */     return (getDays() - 1) / 7;
/*    */   }
/*    */   
/*    */   public static int getFirstRechargeNum() {
/* 74 */     return firstRechargeNum;
/*    */   }
/*    */   
/*    */   public static synchronized void incrFirstRechargeNum() {
/* 78 */     firstRechargeNum++;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\StatisticsService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */