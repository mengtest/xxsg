/*    */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.MonsterFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.PersonalInsBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NormalFight
/*    */   extends AbstractFight
/*    */   implements IFight
/*    */ {
/*    */   private int instanceId;
/*    */   private PersonalInsBean personalInsBean;
/*    */   private IPlayer player;
/*    */   
/*    */   public NormalFight(int instanceId, IPlayer player) {
/* 27 */     this.instanceId = instanceId;
/* 28 */     this.type = 2;
/*    */     
/* 30 */     this.player = player;
/* 31 */     this.personalInsBean = (PersonalInsBean)JsonTableService.getJsonData(instanceId, PersonalInsBean.class);
/*    */   }
/*    */   
/*    */   public short initFight() {
/* 35 */     if (null == this.personalInsBean) {
/* 36 */       return 13002;
/*    */     }
/* 38 */     PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(this.player);
/* 39 */     initSide(0, (IFightSide)playerFightSide);
/*    */ 
/*    */     
/* 42 */     MonsterFightSide monsterFightSide = new MonsterFightSide(this.personalInsBean, (byte)1);
/* 43 */     initSide(1, (IFightSide)monsterFightSide);
/* 44 */     return 0;
/*    */   }
/*    */   
/*    */   public byte start(FightRecordResponse response) {
/* 48 */     init();
/* 49 */     response.type = this.type;
/* 50 */     response.id = this.instanceId + "";
/*    */ 
/*    */     
/* 53 */     getGroupData(response);
/*    */     
/* 55 */     action();
/* 56 */     getCandidateFighterData(response);
/* 57 */     System.out.println(getDebugStr());
/* 58 */     getRecord(response);
/* 59 */     LogUtils.errorLog(new Object[] { "NormalFight", Long.valueOf(this.player.getPlayerId()), Integer.valueOf(this.instanceId), Byte.valueOf(this.result) });
/* 60 */     return this.result;
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
/* 75 */     if (this.result == 2)
/* 76 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\NormalFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */