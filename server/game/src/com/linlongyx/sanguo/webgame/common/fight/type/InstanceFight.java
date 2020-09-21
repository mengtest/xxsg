/*    */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.MonsterFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MainInsBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InstanceFight
/*    */   extends AbstractFight
/*    */   implements IFight
/*    */ {
/*    */   private int instanceId;
/*    */   private MainInsBean mainInsBean;
/*    */   private IPlayer player;
/*    */   
/*    */   public InstanceFight(int instanceId, IPlayer player) {
/* 31 */     this.instanceId = instanceId;
/* 32 */     this.type = 1;
/*    */     
/* 34 */     this.player = player;
/* 35 */     this.mainInsBean = (MainInsBean)JsonTableService.getJsonData(instanceId, MainInsBean.class);
/*    */   }
/*    */   
/*    */   public short initFight() {
/* 39 */     PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(this.player);
/* 40 */     initSide(0, (IFightSide)playerFightSide);
/*    */     
/* 42 */     MonsterFightSide right = new MonsterFightSide(this.mainInsBean, (byte)1);
/* 43 */     initSide(1, (IFightSide)right);
/* 44 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void end() {
/* 49 */     if (this.result == 2) {
/* 50 */       this.result = 0;
/*    */     }
/*    */   }
/*    */   
/*    */   public int start(FightRecordResponse response) {
/* 55 */     init();
/* 56 */     response.type = this.type;
/* 57 */     response.id = this.instanceId + "";
/*    */ 
/*    */ 
/*    */     
/* 61 */     getGroupData(response);
/* 62 */     action();
/* 63 */     getCandidateFighterData(response);
/* 64 */     getRecord(response);
/* 65 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\InstanceFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */