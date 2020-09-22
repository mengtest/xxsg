/*    */ package linlongyx.sanguo.webgame.rmi.rank;
/*    */ 
/*    */ import linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.rank.comparator.RankType1Comparator;
/*    */ import com.linlongyx.sanguo.webgame.rmi.rank.comparator.RankType2Comparator;
/*    */ import java.util.Comparator;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum CrossRankActType
/*    */ {
/* 15 */   Recruit(1, "招募榜", true, (Comparator<RankingData>)new RankType1Comparator()),
/* 16 */   WarPet(2, "战宠榜", true, (Comparator<RankingData>)new RankType1Comparator()),
/* 17 */   Equip(3, "装备榜", true, (Comparator<RankingData>)new RankType1Comparator()),
/* 18 */   Level(4, "等级榜", true, (Comparator<RankingData>)new RankType1Comparator()),
/* 19 */   Cost(5, "消费榜", true, (Comparator<RankingData>)new RankType1Comparator()),
/* 20 */   Mounts(6, "坐骑榜", true, (Comparator<RankingData>)new RankType1Comparator()),
/* 21 */   PlayerFight(7, "战力榜", true, (Comparator<RankingData>)new RankType2Comparator()),
/* 22 */   Charge(8, "充值榜", true, (Comparator<RankingData>)new RankType1Comparator());
/*    */   private int type;
/*    */   private String name;
/*    */   private boolean isDesc;
/*    */   private Comparator<RankingData> comparator;
/*    */   private static Map<Integer, CrossRankActType> map;
/*    */   
/*    */   static {
/* 30 */     map = new HashMap<>();
/*    */ 
/*    */     
/* 33 */     for (CrossRankActType rankActType : values()) {
/* 34 */       map.put(Integer.valueOf(rankActType.getType()), rankActType);
/*    */     }
/*    */   }
/*    */   
/*    */   public static CrossRankActType getRankActType(int type) {
/* 39 */     return map.get(Integer.valueOf(type));
/*    */   }
/*    */   
/*    */   CrossRankActType(int type, String name, boolean isDesc, Comparator<RankingData> comparator) {
/* 43 */     this.type = type;
/* 44 */     this.name = name;
/* 45 */     this.isDesc = isDesc;
/* 46 */     this.comparator = comparator;
/*    */   }
/*    */   public int getType() {
/* 49 */     return this.type;
/*    */   } public String getName() {
/* 51 */     return this.name;
/*    */   }
/*    */   public boolean isDesc() {
/* 54 */     return this.isDesc;
/*    */   }
/*    */   
/*    */   public Comparator<RankingData> getComparator() {
/* 58 */     return this.comparator;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\rank\CrossRankActType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */