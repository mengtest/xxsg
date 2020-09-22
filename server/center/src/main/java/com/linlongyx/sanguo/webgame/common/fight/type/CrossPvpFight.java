/*    */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*    */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.CrossFightRecordResponse;
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
/*    */ 
/*    */ 
/*    */ public class CrossPvpFight
/*    */   extends AbstractFight
/*    */ {
/*    */   private PlayerFightSide leftSide;
/*    */   private PlayerFightSide rightSide;
/*    */   
/*    */   public CrossPvpFight(PlayerFightSide leftSide, PlayerFightSide rightSide) {
/* 31 */     this.leftSide = leftSide;
/* 32 */     this.rightSide = rightSide;
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
/* 46 */     initSide(0, (IFightSide)this.leftSide);
/* 47 */     initSide(1, (IFightSide)this.rightSide);
/*    */     
/* 49 */     init();
/*    */ 
/*    */     
/* 52 */     if (this.leftSide.getCurGroup().getFighters(true).isEmpty()) {
/* 53 */       List<IFighter> fighters = this.leftSide.getCurGroup().getFighters(false);
/* 54 */       if (!fighters.isEmpty()) {
/* 55 */         int index = RandUtil.randNum(fighters.size() - 1);
/* 56 */         ((IFighter)fighters.get(index)).setDead(false);
/* 57 */         ((IFighter)fighters.get(index)).setAttr(AttributeType.HP.getType(), 100L);
/*    */       } 
/*    */     } 
/*    */     
/* 61 */     if (this.rightSide.getCurGroup().getFighters(true).isEmpty()) {
/* 62 */       List<IFighter> fighters = this.rightSide.getCurGroup().getFighters(false);
/* 63 */       if (!fighters.isEmpty()) {
/* 64 */         int index = RandUtil.randNum(fighters.size() - 1);
/* 65 */         ((IFighter)fighters.get(index)).setDead(false);
/* 66 */         ((IFighter)fighters.get(index)).setAttr(AttributeType.HP.getType(), 100L);
/*    */       } 
/*    */     } 
/*    */     
/* 70 */     FightUtil.clearBuff((IFightSide)this.leftSide);
/* 71 */     FightUtil.removeDeadFighters((IFightSide)this.leftSide);
/*    */     
/* 73 */     FightUtil.clearBuff((IFightSide)this.rightSide);
/* 74 */     FightUtil.removeDeadFighters((IFightSide)this.rightSide);
/*    */     
/* 76 */     getGroupData(response);
/* 77 */     action();
/* 78 */     getCandidateFighterData(response);
/* 79 */     getCrossRecord(response);
/* 80 */     this.leftSide.syncRecord((ResponseBase)response);
/* 81 */     return this.result;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void end() {
/* 86 */     if (this.result == 2)
/* 87 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\CrossPvpFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */