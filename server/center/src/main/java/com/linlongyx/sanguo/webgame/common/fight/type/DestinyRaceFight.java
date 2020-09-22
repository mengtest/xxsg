/*    */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.FightGroup;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.HpData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.PkPlayerData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.PkRecord;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyRaceFight
/*    */   extends AbstractFight
/*    */ {
/*    */   private PkPlayerData leftPlayerData;
/*    */   private PkPlayerData rightPlayerData;
/*    */   private String pkId;
/*    */   
/*    */   public DestinyRaceFight(String pkId, PkPlayerData leftPlayerData, PkPlayerData rightPlayerData) {
/* 27 */     this.leftPlayerData = leftPlayerData;
/* 28 */     this.rightPlayerData = rightPlayerData;
/* 29 */     this.pkId = pkId;
/*    */   }
/*    */   
/*    */   public PkRecord start() {
/* 33 */     PkRecord pkRecord = new PkRecord();
/* 34 */     pkRecord.pkId = this.pkId;
/* 35 */     pkRecord.type = this.type = 12;
/* 36 */     PlayerFightSide playerFightSide = FightUtil.createPvpFightSide(this.leftPlayerData.getFighterData(), this.leftPlayerData.getCandidateFighters(), this.leftPlayerData.getZhenfa(), this.leftPlayerData.getPlayerId(), this.leftPlayerData.getPlayerName(), this.leftPlayerData.getFirstHandVal(), true);
/* 37 */     initSide(0, (IFightSide)playerFightSide);
/* 38 */     PlayerFightSide monsterFightSide = FightUtil.createPvpFightSide(this.rightPlayerData.getFighterData(), this.rightPlayerData.getCandidateFighters(), this.rightPlayerData.getZhenfa(), this.rightPlayerData.getPlayerId(), this.rightPlayerData.getPlayerName(), this.rightPlayerData.getFirstHandVal(), true);
/* 39 */     initSide(1, (IFightSide)monsterFightSide);
/*    */     
/* 41 */     init();
/* 42 */     this.sides[0].getGroupData(pkRecord.lGroups);
/* 43 */     this.sides[1].getGroupData(pkRecord.rGroups);
/* 44 */     action();
/* 45 */     this.sides[0].getCandidateFighterData(pkRecord.lGroups);
/* 46 */     this.sides[1].getCandidateFighterData(pkRecord.rGroups);
/* 47 */     getRecord(pkRecord);
/* 48 */     if (pkRecord.result == 1) {
/* 49 */       pkRecord.winner = this.leftPlayerData.getPlayerId();
/*    */     } else {
/* 51 */       pkRecord.winner = this.rightPlayerData.getPlayerId();
/*    */     } 
/* 53 */     return pkRecord;
/*    */   }
/*    */   
/*    */   public void getRecord(PkRecord pkRecord) {
/* 57 */     pkRecord.boutPlayData.addAll(this.totalPlayDatas);
/* 58 */     pkRecord.result = this.result;
/* 59 */     for (IFightSide side : this.sides) {
/* 60 */       for (FightGroup fightGroup : side.getGroupList()) {
/* 61 */         for (IFighter fighter : fightGroup.getFighters(true)) {
/* 62 */           HpData hpData = new HpData();
/* 63 */           hpData.guid = fighter.getGuid();
/* 64 */           hpData.id = fighter.getId();
/* 65 */           hpData.hp = fighter.getHP();
/* 66 */           pkRecord.fightersHp.add(hpData);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void end() {
/* 78 */     if (this.result == 2)
/* 79 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\DestinyRaceFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */