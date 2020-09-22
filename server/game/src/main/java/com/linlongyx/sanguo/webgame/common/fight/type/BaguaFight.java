/*    */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.MonsterFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BaguaInsBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaguaFight
/*    */   extends AbstractFight
/*    */ {
/*    */   private Team team;
/*    */   private BaguaInsBean baguaInsBean;
/*    */   
/*    */   public BaguaFight(int insId, Team team) {
/* 24 */     this.team = team;
/* 25 */     this.baguaInsBean = (BaguaInsBean)JsonTableService.getJsonData(insId, BaguaInsBean.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte start(FightRecordResponse response) {
/* 30 */     this.team.setStatus(Team.TeamStatus.IN_FIGHT.getValue());
/* 31 */     PlayerFightSide playerFightSide = FightUtil.createTeamPlayerFightSide(this.team, false);
/* 32 */     initSide(0, (IFightSide)playerFightSide);
/* 33 */     MonsterFightSide monsterFightSide = new MonsterFightSide(this.baguaInsBean, (byte)1);
/* 34 */     initSide(1, (IFightSide)monsterFightSide);
/*    */     
/* 36 */     init();
/* 37 */     getGroupData(response);
/* 38 */     action();
/* 39 */     getCandidateFighterData(response);
/* 40 */     getRecord(response);
/* 41 */     playerFightSide.syncRecord((ResponseBase)response);
/* 42 */     return this.result;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void end() {
/* 47 */     if (this.result == 2)
/* 48 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\BaguaFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */