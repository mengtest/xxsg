/*    */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.RankBossFightSide;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
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
/*    */ public class RankBossFight
/*    */   extends AbstractFight
/*    */   implements IFight
/*    */ {
/*    */   public byte start(IPlayer player, RankBossFightSide rankBossFightSide, FightRecordResponse response) {
/* 29 */     response.type = this.type;
/* 30 */     response.id = rankBossFightSide.getInstanceId() + "";
/*    */ 
/*    */     
/* 33 */     getGroupData(response);
/*    */     
/* 35 */     action();
/*    */     
/* 37 */     getCandidateFighterData(response);
/* 38 */     TaskComponent taskComponent = (TaskComponent)player.createIfNotExist(TaskComponent.class);
/* 39 */     if (null != taskComponent) {
/* 40 */       taskComponent.refreshSchedule(TaskType.RankBossHurt, 0, this.sides[0].getTotalHurt());
/*    */     }
/*    */ 
/*    */     
/* 44 */     rankBossFightSide.updateHp(player.getPlayerId(), player.getPlayerName());
/* 45 */     System.out.println(getDebugStr());
/* 46 */     response.totalHurt = this.sides[0].getTotalHurt();
/* 47 */     getRecord(response);
/* 48 */     return this.result;
/*    */   }
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
/*    */   protected void end() {
/* 63 */     if (this.result == 2)
/* 64 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\RankBossFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */