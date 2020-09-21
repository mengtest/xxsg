/*    */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyFight
/*    */   extends AbstractFight
/*    */ {
/*    */   private IPlayer myPlayer;
/*    */   private DestinyPlayerData targetPlayerData;
/*    */   
/*    */   public DestinyFight(IPlayer player, DestinyPlayerData targetPlayerData) {
/* 21 */     this.myPlayer = player;
/* 22 */     this.targetPlayerData = targetPlayerData;
/*    */   }
/*    */   
/*    */   public byte start(FightRecordResponse response) {
/* 26 */     this.type = 11;
/* 27 */     PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(this.myPlayer);
/* 28 */     initSide(0, (IFightSide)playerFightSide);
/* 29 */     PlayerFightSide monsterFightSide = FightUtil.createPvpPlayerFightSide(this.targetPlayerData.getPlayerId(), this.targetPlayerData.getPlayerName(), this.targetPlayerData.getFighters(), this.targetPlayerData.getCandidateFighters(), this.targetPlayerData
/* 30 */         .getZhenfa(), this.targetPlayerData.getFirstHandVal(), true);
/* 31 */     initSide(1, (IFightSide)monsterFightSide);
/*    */     
/* 33 */     init();
/*    */ 
/*    */     
/* 36 */     getGroupData(response);
/* 37 */     action();
/* 38 */     getCandidateFighterData(response);
/* 39 */     getRecord(response);
/* 40 */     return this.result;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void end() {
/* 45 */     if (this.result == 2)
/* 46 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\DestinyFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */