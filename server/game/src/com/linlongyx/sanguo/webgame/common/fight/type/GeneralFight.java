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
/*    */ import com.linlongyx.sanguo.webgame.config.bean.GeneralInsBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GeneralFight
/*    */   extends AbstractFight
/*    */   implements IFight
/*    */ {
/*    */   private int insId;
/*    */   private GeneralInsBean generalInsBean;
/*    */   private IPlayer player;
/*    */   private int originNum;
/*    */   
/*    */   public GeneralFight(int insId, IPlayer player) {
/* 27 */     this.insId = insId;
/* 28 */     this.type = 7;
/*    */     
/* 30 */     this.player = player;
/* 31 */     this.generalInsBean = (GeneralInsBean)JsonTableService.getJsonData(insId, GeneralInsBean.class);
/*    */   }
/*    */   
/*    */   public short initFight() {
/* 35 */     if (null == this.generalInsBean) {
/* 36 */       return 11203;
/*    */     }
/* 38 */     PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(this.player);
/* 39 */     initSide(0, (IFightSide)playerFightSide);
/* 40 */     this.originNum = playerFightSide.getLeftNum();
/*    */     
/* 42 */     MonsterFightSide monsterFightSide = new MonsterFightSide(this.generalInsBean, (byte)1);
/* 43 */     initSide(1, (IFightSide)monsterFightSide);
/* 44 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int start(FightRecordResponse response) {
/* 53 */     init();
/* 54 */     response.type = this.type;
/* 55 */     response.id = this.insId + "";
/*    */ 
/*    */     
/* 58 */     getGroupData(response);
/*    */     
/* 60 */     action();
/* 61 */     getCandidateFighterData(response);
/* 62 */     System.out.println(getDebugStr());
/* 63 */     getRecord(response);
/* 64 */     PlayerFightSide playerFightSide = (PlayerFightSide)this.sides[0];
/* 65 */     int star = 0;
/* 66 */     if (1 == this.result) {
/* 67 */       int num = playerFightSide.getLeftNum();
/* 68 */       if (this.originNum == num) {
/* 69 */         star = 3;
/* 70 */       } else if (num + 1 == this.originNum) {
/* 71 */         star = 2;
/*    */       } else {
/* 73 */         star = 1;
/*    */       } 
/*    */     } 
/* 76 */     LogUtils.errorLog(new Object[] { "GeneralFight", Long.valueOf(this.player.getPlayerId()), Integer.valueOf(this.insId), Byte.valueOf(this.result), Integer.valueOf(star) });
/* 77 */     return star;
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
/* 92 */     if (this.result == 2)
/* 93 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\GeneralFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */