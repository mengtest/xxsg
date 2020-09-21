/*    */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.MonsterFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TowerBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.TowerCountType;
/*    */ import com.linlongyx.sanguo.webgame.processors.tower.TowerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TowerFight
/*    */   extends AbstractFight
/*    */   implements IFight
/*    */ {
/*    */   private int instanceId;
/*    */   private TowerBean towerBean;
/*    */   private IPlayer player;
/*    */   
/*    */   public TowerFight(int instanceId, IPlayer player) {
/* 31 */     this.instanceId = instanceId;
/* 32 */     this.type = 9;
/* 33 */     this.player = player;
/* 34 */     this.towerBean = (TowerBean)JsonTableService.getJsonData(instanceId, TowerBean.class);
/*    */   }
/*    */   
/*    */   public short initFight() {
/* 38 */     if (null == this.towerBean) {
/* 39 */       return 13002;
/*    */     }
/* 41 */     PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(this.player);
/* 42 */     initSide(0, (IFightSide)playerFightSide);
/*    */     
/* 44 */     MonsterFightSide monsterFightSide = new MonsterFightSide(this.towerBean, (byte)1);
/* 45 */     initSide(1, (IFightSide)monsterFightSide);
/* 46 */     return 0;
/*    */   }
/*    */   
/*    */   public byte start(FightRecordResponse response) {
/* 50 */     init();
/* 51 */     response.type = this.type;
/* 52 */     response.id = this.instanceId + "";
/*    */ 
/*    */     
/* 55 */     getGroupData(response);
/*    */     
/* 57 */     action();
/* 58 */     getCandidateFighterData(response);
/* 59 */     List<IFighter> fightList = this.sides[0].getCurGroup().getFighters(true);
/* 60 */     long totalHp = TowerUtil.getToHp(fightList);
/* 61 */     if (this.towerBean.getCondition() != TowerCountType.allWin.getType() && 
/* 62 */       this.result == 1) {
/* 63 */       byte rse = TowerUtil.checkCodition(this.player, this.towerBean, fightList.size(), totalHp);
/* 64 */       this.result = rse;
/*    */     } 
/*    */     
/* 67 */     System.out.println(getDebugStr());
/* 68 */     getRecord(response);
/* 69 */     return this.result;
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
/*    */   
/*    */   protected void end() {
/* 85 */     if (this.result == 2)
/* 86 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\TowerFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */