/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
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
/*    */ public enum HttpEventType
/*    */ {
/* 16 */   ReloadXml(1, new ReloadXmlEvent()),
/* 17 */   HotUpdate(2, new HotUpdateEvent()),
/* 18 */   Mail(3, new MailEvent()),
/* 19 */   MODULE(4, new ModuleEvent()),
/* 20 */   CHARGE(5, new ChargeEvent()),
/* 21 */   PLAYER(6, new PlayerForbidEvent()),
/* 22 */   GMList(57, new GmListEvent()),
/* 23 */   LOGOUT(9, new LogoutEvent()),
/* 24 */   AllLOGOUT(10, new AllLogoutEvent()),
/* 25 */   NOTICE(12, new NoticeEvent()),
/*    */   
/* 27 */   RUNNING(13, new RunningEvent()),
/* 28 */   ReloadSensitive(14, new ReloadSensitiveEvent()),
/* 29 */   SETOPENTIME(17, new SetOpenTimeEvent()),
/* 30 */   CLEARCROSSDATA(18, new ClearCrossDataEvent()),
/*    */   
/* 32 */   RefreshRanking(103, new RefreshRankingEvent()),
/* 33 */   MenTalReset(104, new MentalResetEvent()),
/*    */   
/* 35 */   DAILY_RESET(10000, new DailyResetEvent()),
/* 36 */   ArenaReward(10001, new ArenaRewardEvent()),
/* 37 */   SundayEvent(10002, new SundayEvent()),
/*    */   
/* 39 */   WorldBossStart(10010, new WorldBossStartEvent()),
/* 40 */   WorldBossEnd(10011, new WorldBossEndEvent()),
/* 41 */   TitleCheck(10012, new PerHourEvent());
/*    */   
/*    */   private int cmd;
/*    */   
/*    */   private IAnswerHttpEvent iHttpEvent;
/*    */   
/*    */   HttpEventType(int cmd, IAnswerHttpEvent iHttpEvent) {
/* 48 */     this.cmd = cmd;
/* 49 */     this.iHttpEvent = iHttpEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCmd() {
/* 56 */     return this.cmd;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnswerHttpEvent getiHttpEvent() {
/* 64 */     return this.iHttpEvent;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\HttpEventType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */