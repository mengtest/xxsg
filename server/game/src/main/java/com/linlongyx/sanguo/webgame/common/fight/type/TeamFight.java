/*    */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.MonsterFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MultiInsBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TeamFight
/*    */   extends AbstractFight
/*    */ {
/*    */   private Team team;
/*    */   private MultiInsBean multiInsBean;
/*    */   
/*    */   public TeamFight(int insId, Team team) {
/* 24 */     this.team = team;
/* 25 */     this.multiInsBean = (MultiInsBean)JsonTableService.getJsonData(insId, MultiInsBean.class);
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
/*    */   public byte start(FightRecordResponse response) {
/* 39 */     this.team.setStatus(Team.TeamStatus.IN_FIGHT.getValue());
/* 40 */     PlayerFightSide playerFightSide = FightUtil.createTeamPlayerFightSide(this.team, false);
/* 41 */     initSide(0, (IFightSide)playerFightSide);
/* 42 */     MonsterFightSide monsterFightSide = new MonsterFightSide(this.multiInsBean, (byte)1);
/* 43 */     initSide(1, (IFightSide)monsterFightSide);
/*    */     
/* 45 */     init();
/* 46 */     getGroupData(response);
/* 47 */     action();
/* 48 */     getCandidateFighterData(response);
/* 49 */     getRecord(response);
/* 50 */     playerFightSide.syncRecord((ResponseBase)response);
/* 51 */     return this.result;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void end() {
/* 56 */     if (this.result == 2)
/* 57 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\TeamFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */