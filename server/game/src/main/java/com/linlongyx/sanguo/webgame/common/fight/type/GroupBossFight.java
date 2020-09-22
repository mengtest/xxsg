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
/*    */ public class GroupBossFight
/*    */   extends AbstractFight
/*    */   implements IFight
/*    */ {
/*    */   public byte start(IPlayer player, FightRecordResponse response) {
/* 27 */     getGroupData(response);
/*    */     
/* 29 */     action();
/* 30 */     getCandidateFighterData(response);
/* 31 */     System.out.println(getDebugStr());
/* 32 */     response.totalHurt = this.sides[0].getTotalHurt();
/* 33 */     getRecord(response);
/* 34 */     return this.result;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void end() {
/* 39 */     if (this.result == 2)
/* 40 */       this.result = 0; 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\GroupBossFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */