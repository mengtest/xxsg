/*    */ package linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class StarcraftWorldBean
/*    */ {
/*    */   private int worldLevel;
/*    */   
/*    */   public int getWorldLevel() {
/* 10 */     return this.worldLevel;
/*    */   }
/*    */   private ArrayList<Integer> reward;
/*    */   private ArrayList<Integer> rankReward;
/*    */   
/*    */   public ArrayList<Integer> getReward() {
/* 16 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getRankReward() {
/* 22 */     return this.rankReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     String retVal = "worldLevel= " + this.worldLevel + ", reward= " + this.reward + ", rankReward= " + this.rankReward;
/* 28 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\StarcraftWorldBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */