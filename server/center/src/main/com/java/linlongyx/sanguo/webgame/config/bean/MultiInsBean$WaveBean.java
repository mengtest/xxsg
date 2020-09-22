/*    */ package linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
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
/*    */ public class WaveBean
/*    */ {
/*    */   private int round;
/*    */   private ArrayList<MonsterBean> monster;
/*    */   
/*    */   public int getRound() {
/* 62 */     return this.round;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<MonsterBean> getMonster() {
/* 68 */     return this.monster;
/*    */   }
/*    */   
/*    */   public class MonsterBean {
/*    */     private int posi;
/*    */     
/*    */     public int getPosi() {
/* 75 */       return this.posi;
/*    */     }
/*    */     private int id;
/*    */     
/*    */     public int getId() {
/* 80 */       return this.id;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 85 */       String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 86 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 92 */     String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 93 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MultiInsBean$WaveBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */