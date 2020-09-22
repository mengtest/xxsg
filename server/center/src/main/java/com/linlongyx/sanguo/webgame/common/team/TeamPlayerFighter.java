/*    */ package com.linlongyx.sanguo.webgame.common.team;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JsonIgnoreProperties(ignoreUnknown = true)
/*    */ public class TeamPlayerFighter
/*    */ {
/*    */   private int pos;
/*    */   private String name;
/*    */   private final boolean isLead;
/*    */   private IFighter fighter;
/*    */   
/*    */   public TeamPlayerFighter(boolean isLead, IFighter fighter) {
/* 20 */     this.pos = fighter.getPos();
/* 21 */     this.fighter = fighter;
/* 22 */     this.isLead = isLead;
/*    */   }
/*    */   
/*    */   public int getPos() {
/* 26 */     return this.pos;
/*    */   }
/*    */   
/*    */   public void setPos(int pos) {
/* 30 */     this.pos = pos;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 34 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 38 */     this.name = name;
/*    */   }
/*    */   
/*    */   public IFighter getFighter() {
/* 42 */     return this.fighter;
/*    */   }
/*    */   
/*    */   public void setFighter(IFighter fighter) {
/* 46 */     this.fighter = fighter;
/*    */   }
/*    */   
/*    */   public boolean isLead() {
/* 50 */     return this.isLead;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\team\TeamPlayerFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */