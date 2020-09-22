/*    */ package linlongyx.sanguo.webgame.rmi.data;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class ServerData implements Serializable {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int serverId;
/*    */   private int opentime;
/*    */   private int worldLevel;
/*    */   private HashSet<Integer> hefu;
/*    */   
/*    */   public static long getSerialVersionUID() {
/* 14 */     return 1L;
/*    */   }
/*    */   
/*    */   public int getServerId() {
/* 18 */     return this.serverId;
/*    */   }
/*    */   
/*    */   public void setServerId(int serverId) {
/* 22 */     this.serverId = serverId;
/*    */   }
/*    */   
/*    */   public int getOpentime() {
/* 26 */     return this.opentime;
/*    */   }
/*    */   
/*    */   public void setOpentime(int opentime) {
/* 30 */     this.opentime = opentime;
/*    */   }
/*    */   
/*    */   public int getWorldLevel() {
/* 34 */     return this.worldLevel;
/*    */   }
/*    */   
/*    */   public void setWorldLevel(int worldLevel) {
/* 38 */     this.worldLevel = worldLevel;
/*    */   }
/*    */   
/*    */   public HashSet<Integer> getHefu() {
/* 42 */     return this.hefu;
/*    */   }
/*    */   
/*    */   public void setHefu(HashSet<Integer> hefu) {
/* 46 */     this.hefu = hefu;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\ServerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */