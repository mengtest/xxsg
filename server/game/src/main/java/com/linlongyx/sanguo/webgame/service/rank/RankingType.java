/*    */ package com.linlongyx.sanguo.webgame.service.rank;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum RankingType
/*    */ {
/*  7 */   FIGHT(1, new RankingFight()),
/*  8 */   LEVEL(2, new RankingLevel()),
/*  9 */   ARENA(3, new RankingArena()),
/* 10 */   BARRIER(4, new RankingBarrier()),
/*    */ 
/*    */   
/* 13 */   GENERAL_STAR(11, new RankingGenStar()),
/* 14 */   UNPARALELLED_STAR(12, new RankingUnparaStar()),
/* 15 */   TOWER(13, new RankingTower()),
/* 16 */   CARDBOOK(14, new RankingCardBook()),
/* 17 */   GENERAL_STAR2(15, new RankingGenStar2()),
/* 18 */   GENERAL_STAR3(16, new RankingGenStar3());
/*    */   
/*    */   private int type;
/*    */   private AbstractRank abstractRank;
/*    */   
/*    */   RankingType(int type, AbstractRank abstractRank) {
/* 24 */     setType(type);
/* 25 */     setAbstractRank(abstractRank);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 30 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(int type) {
/* 34 */     this.type = type;
/*    */   }
/*    */   
/*    */   public AbstractRank getAbstractRank() {
/* 38 */     return this.abstractRank;
/*    */   }
/*    */   
/*    */   public void setAbstractRank(AbstractRank abstractRank) {
/* 42 */     this.abstractRank = abstractRank;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\rank\RankingType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */