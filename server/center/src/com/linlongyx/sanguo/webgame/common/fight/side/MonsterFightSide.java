/*    */ package com.linlongyx.sanguo.webgame.common.fight.side;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.MonsterFighter;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MultiInsBean;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MonsterFightSide
/*    */   extends AbstractFightSide
/*    */   implements IFightSide
/*    */ {
/*    */   public MonsterFightSide(MultiInsBean multiInsBean, byte side) {
/* 22 */     this.isAuto = true;
/* 23 */     this.side = side;
/* 24 */     for (MultiInsBean.WaveBean waveBean : multiInsBean.getWave().values()) {
/* 25 */       FightGroup group = new FightGroup();
/* 26 */       List<IFighter> fighters = new ArrayList<>();
/* 27 */       for (MultiInsBean.WaveBean.MonsterBean monsterBean : waveBean.getMonster()) {
/* 28 */         if (monsterBean.getId() == 0)
/*    */           continue; 
/* 30 */         MonsterFighter monsterFighter = new MonsterFighter(monsterBean.getId(), (byte)monsterBean.getPosi());
/* 31 */         fighters.add(monsterFighter);
/*    */       } 
/* 33 */       group.setFighters(fighters);
/* 34 */       group.setMaxRound(waveBean.getRound());
/* 35 */       this.groupList.add(group);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\side\MonsterFightSide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */