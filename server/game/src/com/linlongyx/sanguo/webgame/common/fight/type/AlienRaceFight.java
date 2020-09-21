/*    */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.AlienRaceBossFightSide;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AlienRaceFight
/*    */   extends AbstractFight
/*    */   implements IFight
/*    */ {
/*    */   private boolean isRobot;
/*    */   private long targetPlayerId;
/*    */   private IPlayer myPlayer;
/*    */   
/*    */   public AlienRaceFight(IPlayer player, boolean isRobot, long targetPlayerId) {
/* 21 */     this.type = 15;
/* 22 */     this.myPlayer = player;
/* 23 */     this.isRobot = isRobot;
/* 24 */     this.targetPlayerId = targetPlayerId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte start(IPlayer player, AlienRaceBossFightSide alienRaceBossFightSide, FightRecordResponse response) {
/* 35 */     if (!this.isRobot) {
/* 36 */       init();
/*    */     }
/* 38 */     response.type = this.type;
/* 39 */     response.id = alienRaceBossFightSide.getInstanceId() + "";
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 44 */     getGroupData(response);
/* 45 */     action();
/* 46 */     getCandidateFighterData(response);
/* 47 */     System.out.println(getDebugStr());
/* 48 */     getRecord(response);
/* 49 */     if (this.isRobot) {
/* 50 */       alienRaceBossFightSide.updateHp(player.getPlayerId(), player.getPlayerName());
/* 51 */       response.totalHurt = this.sides[0].getTotalHurt();
/*    */     } 
/* 53 */     return this.result;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void end() {
/* 58 */     if (this.result == 2)
/* 59 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\AlienRaceFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */