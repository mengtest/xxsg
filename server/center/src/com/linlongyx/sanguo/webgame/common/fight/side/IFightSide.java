/*    */ package com.linlongyx.sanguo.webgame.common.fight.side;public interface IFightSide { void initGuid(byte paramByte); void getGroupData(List<FightGroupData> paramList); void getCandidateFighterData(List<FightGroupData> paramList); int preAction(byte paramByte, IFight paramIFight);
/*    */   void syncRecord(ResponseBase paramResponseBase);
/*    */   FightGroup getCurGroup();
/*    */   List<FightGroup> getGroupList();
/*    */   void addGroup(FightGroup paramFightGroup);
/*    */   END_STATUS checkEndStatus(int paramInt);
/*    */   long getTotalHurt();
/*    */   void updateTotalHurt(long paramLong);
/*    */   int getTotalFirstHandVal();
/*    */   IFighter getCandidateFighter();
/*    */   boolean addCandidateFigher(IFighter paramIFighter);
/*    */   IFighter getPetFighter();
/*    */   void setPetFighter(IFighter paramIFighter);
/*    */   IFighter getStageFighter();
/*    */   void setStageFighter(IFighter paramIFighter);
/*    */   Pair<Integer, Integer> getZhenfa();
/*    */   void setZhenfa(Pair<Integer, Integer> paramPair);
/* 18 */   public enum END_STATUS { END_STATUS_ON,
/* 19 */     END_STATUS_NEXT,
/* 20 */     END_STATUS_END,
/* 21 */     END_STATUS_MAXROUND; }
/*    */    }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\side\IFightSide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */