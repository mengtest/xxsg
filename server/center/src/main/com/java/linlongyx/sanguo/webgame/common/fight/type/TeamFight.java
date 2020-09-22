/*    */ package linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.MonsterFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import linlongyx.sanguo.webgame.config.bean.MultiInsBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.CrossFightRecordResponse;
/*    */ import linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class TeamFight
/*    */   extends AbstractFight
/*    */ {
/*    */   private Team team;
/*    */   private MultiInsBean multiInsBean;
/*    */   
/*    */   public TeamFight(int insId, Team team) {
/* 26 */     this.team = team;
/* 27 */     this.multiInsBean = (MultiInsBean)JsonTableService.getJsonData(insId, MultiInsBean.class);
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
/*    */   public byte start(CrossFightRecordResponse response) {
/* 41 */     this.team.setStatus(Team.TeamStatus.IN_FIGHT.getValue());
/* 42 */     PlayerFightSide playerFightSide = FightUtil.createTeamPlayerFightSide(this.team, true, false);
/* 43 */     initSide(0, (IFightSide)playerFightSide);
/* 44 */     MonsterFightSide monsterFightSide = new MonsterFightSide(this.multiInsBean, (byte)1);
/* 45 */     initSide(1, (IFightSide)monsterFightSide);
/*    */     
/* 47 */     init();
/* 48 */     this.sides[0].getGroupData(response.lGroups);
/* 49 */     this.sides[1].getGroupData(response.rGroups);
/* 50 */     action();
/* 51 */     getCandidateFighterData(response);
/* 52 */     getCrossRecord(response);
/* 53 */     playerFightSide.syncRecord((ResponseBase)response);
/* 54 */     return this.result;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void end() {
/* 59 */     if (this.result == 2)
/* 60 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\TeamFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */