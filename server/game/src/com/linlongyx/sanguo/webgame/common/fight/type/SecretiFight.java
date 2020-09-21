/*    */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.MonsterFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SecretiInsBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SecretiFight
/*    */   extends AbstractFight
/*    */   implements IFight
/*    */ {
/*    */   private int instanceId;
/*    */   private SecretiInsBean secretiInsBean;
/*    */   private IPlayer player;
/*    */   
/*    */   public SecretiFight(int instanceId, IPlayer player) {
/* 27 */     this.instanceId = instanceId;
/* 28 */     this.type = 19;
/*    */     
/* 30 */     this.player = player;
/* 31 */     this.secretiInsBean = (SecretiInsBean)JsonTableService.getJsonData(instanceId, SecretiInsBean.class);
/*    */   }
/*    */   
/*    */   public short initFight() {
/* 35 */     if (null == this.secretiInsBean) {
/* 36 */       return 14401;
/*    */     }
/* 38 */     PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(this.player);
/* 39 */     initSide(0, (IFightSide)playerFightSide);
/*    */ 
/*    */     
/* 42 */     MonsterFightSide monsterFightSide = new MonsterFightSide(this.secretiInsBean, (byte)1);
/* 43 */     initSide(1, (IFightSide)monsterFightSide);
/* 44 */     return 0;
/*    */   }
/*    */   
/*    */   public byte start(FightRecordResponse response) {
/* 48 */     init();
/* 49 */     response.type = this.type;
/* 50 */     response.id = this.instanceId + "";
/* 51 */     getGroupData(response);
/*    */     
/* 53 */     action();
/* 54 */     getCandidateFighterData(response);
/* 55 */     getRecord(response);
/* 56 */     return this.result;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void end() {
/* 61 */     if (this.result == 2)
/* 62 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\SecretiFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */