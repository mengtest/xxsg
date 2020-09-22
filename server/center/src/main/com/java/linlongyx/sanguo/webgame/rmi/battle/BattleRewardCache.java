/*    */ package linlongyx.sanguo.webgame.rmi.battle;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*    */ import linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ @JsonIgnoreProperties(ignoreUnknown = true)
/*    */ public class BattleRewardCache
/*    */ {
/*    */   private int serverId;
/*    */   private boolean campRank;
/*    */   private long playerId;
/*    */   private ArrayList<Long> playerList;
/*    */   private int rank;
/*    */   private int camp;
/*    */   private ArrayList<Reward> rewardList;
/*    */   
/*    */   public BattleRewardCache() {}
/*    */   
/*    */   public BattleRewardCache(int serverId, boolean campRank, long playerId, int rank, ArrayList<Reward> rewardList) {
/* 23 */     this.serverId = serverId;
/* 24 */     this.campRank = campRank;
/* 25 */     this.playerId = playerId;
/* 26 */     this.rank = rank;
/* 27 */     this.rewardList = rewardList;
/*    */   }
/*    */   
/*    */   public BattleRewardCache(int serverId, boolean campRank, ArrayList<Long> playerList, int rank, int camp, ArrayList<Reward> rewardList) {
/* 31 */     this.serverId = serverId;
/* 32 */     this.campRank = campRank;
/* 33 */     this.playerList = playerList;
/* 34 */     this.rank = rank;
/* 35 */     this.camp = camp;
/* 36 */     this.rewardList = rewardList;
/*    */   }
/*    */   
/*    */   public int getServerId() {
/* 40 */     return this.serverId;
/*    */   }
/*    */   
/*    */   public void setServerId(int serverId) {
/* 44 */     this.serverId = serverId;
/*    */   }
/*    */   
/*    */   public boolean isCampRank() {
/* 48 */     return this.campRank;
/*    */   }
/*    */   
/*    */   public void setCampRank(boolean campRank) {
/* 52 */     this.campRank = campRank;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 56 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public void setPlayerId(long playerId) {
/* 60 */     this.playerId = playerId;
/*    */   }
/*    */   
/*    */   public ArrayList<Long> getPlayerList() {
/* 64 */     return this.playerList;
/*    */   }
/*    */   
/*    */   public void setPlayerList(ArrayList<Long> playerList) {
/* 68 */     this.playerList = playerList;
/*    */   }
/*    */   
/*    */   public int getRank() {
/* 72 */     return this.rank;
/*    */   }
/*    */   
/*    */   public void setRank(int rank) {
/* 76 */     this.rank = rank;
/*    */   }
/*    */   
/*    */   public int getCamp() {
/* 80 */     return this.camp;
/*    */   }
/*    */   
/*    */   public void setCamp(int camp) {
/* 84 */     this.camp = camp;
/*    */   }
/*    */   
/*    */   public ArrayList<Reward> getRewardList() {
/* 88 */     return this.rewardList;
/*    */   }
/*    */   
/*    */   public void setRewardList(ArrayList<Reward> rewardList) {
/* 92 */     this.rewardList = rewardList;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\battle\BattleRewardCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */