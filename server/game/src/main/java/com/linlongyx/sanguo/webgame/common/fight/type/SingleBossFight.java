/*    */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.MonsterFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BossHomeBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ public class SingleBossFight
/*    */   extends AbstractFight
/*    */   implements IFight
/*    */ {
/*    */   private int instanceId;
/*    */   private BossHomeBean bossHomeBean;
/*    */   private IPlayer player;
/*    */   
/*    */   public SingleBossFight(int instanceId, IPlayer player) {
/* 24 */     this.instanceId = instanceId;
/* 25 */     this.type = 8;
/* 26 */     this.player = player;
/* 27 */     this.bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(instanceId, BossHomeBean.class);
/*    */   }
/*    */   
/*    */   public short initFight() {
/* 31 */     if (null == this.bossHomeBean) {
/* 32 */       return 13002;
/*    */     }
/* 34 */     PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(this.player);
/* 35 */     FightUtil.addTempAttribute(this.player, this.type, playerFightSide);
/* 36 */     initSide(0, (IFightSide)playerFightSide);
/*    */     
/* 38 */     MonsterFightSide monsterFightSide = new MonsterFightSide(this.bossHomeBean, (byte)1);
/* 39 */     initSide(1, (IFightSide)monsterFightSide);
/* 40 */     return 0;
/*    */   }
/*    */   
/*    */   public byte start(FightRecordResponse response) {
/* 44 */     init();
/* 45 */     response.type = this.type;
/* 46 */     response.id = this.instanceId + "";
/*    */ 
/*    */     
/* 49 */     getGroupData(response);
/*    */     
/* 51 */     action();
/* 52 */     getCandidateFighterData(response);
/* 53 */     System.out.println(getDebugStr());
/* 54 */     getRecord(response);
/* 55 */     return this.result;
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
/* 71 */     if (this.result == 2)
/* 72 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\SingleBossFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */