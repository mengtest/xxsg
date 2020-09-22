/*    */ package com.linlongyx.sanguo.webgame.common.team;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TeamPlayerFighter
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int pos;
/*    */   private String name;
/*    */   private final boolean isLead;
/*    */   private IFighter fighter;
/*    */   
/*    */   public TeamPlayerFighter(boolean isLead, IFighter fighter) {
/* 21 */     this.pos = fighter.getPos();
/* 22 */     this.fighter = fighter;
/* 23 */     this.isLead = isLead;
/*    */   }
/*    */   
/*    */   public int getPos() {
/* 27 */     return this.pos;
/*    */   }
/*    */   
/*    */   public void setPos(int pos) {
/* 31 */     this.pos = pos;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 35 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 39 */     this.name = name;
/*    */   }
/*    */   
/*    */   public IFighter getFighter() {
/* 43 */     return this.fighter;
/*    */   }
/*    */   
/*    */   public void setFighter(IFighter fighter) {
/* 47 */     this.fighter = fighter;
/*    */   }
/*    */   
/*    */   public boolean isLead() {
/* 51 */     return this.isLead;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\team\TeamPlayerFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */