/*    */ package com.linlongyx.sanguo.webgame.log;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum LogType
/*    */ {
/*  8 */   LOGIN("log_login"),
/*  9 */   ONLINE("log_data_per_hour"),
/* 10 */   REMAIN("log_player_remain"),
/* 11 */   PLAYER("log_player"),
/* 12 */   CURRENCY("log_currency"),
/* 13 */   BAG("log_bag"),
/* 14 */   CHARGE("log_charge"),
/* 15 */   LEVEL("log_level"),
/* 16 */   TASK("log_task"),
/* 17 */   GUIDE("log_guide"),
/* 18 */   VIP("log_vip"),
/* 19 */   FIRST_SCENE("log_new_player_scene"),
/* 20 */   PARTNER_EXP("log_partner_exp"),
/* 21 */   MALL("log_mall"),
/* 22 */   EQUIP("log_equip"),
/* 23 */   PARTNER("log_partner"),
/* 24 */   MAIL("log_mail"),
/* 25 */   RUNE("log_rune");
/*    */   private String tableName;
/*    */   
/*    */   LogType(String tableName) {
/* 29 */     this.tableName = tableName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTableName() {
/* 36 */     return this.tableName;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\log\LogType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */