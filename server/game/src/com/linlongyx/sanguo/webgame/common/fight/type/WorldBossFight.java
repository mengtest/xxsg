/*    */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldBossFight
/*    */   extends AbstractFight
/*    */   implements IFight
/*    */ {
/*    */   public byte start(IPlayer player, FightRecordResponse response) {
/* 42 */     getGroupData(response);
/*    */     
/* 44 */     action();
/* 45 */     getCandidateFighterData(response);
/*    */     
/* 47 */     getRecord(response);
/* 48 */     return this.result;
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
/* 76 */     if (this.result == 2) {
/* 77 */       this.result = 0;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 83 */     super.reset();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\WorldBossFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */