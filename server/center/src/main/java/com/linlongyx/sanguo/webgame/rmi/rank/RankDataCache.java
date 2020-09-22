/*     */ package com.linlongyx.sanguo.webgame.rmi.rank;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class RankDataCache {
/*     */   private int actId;
/*     */   private int rankType;
/*     */   private int limit;
/*     */   private boolean desc;
/*     */   private int condition;
/*     */   
/*     */   public enum RankState {
/*  16 */     OPEN(1),
/*  17 */     CLOSE(2);
/*     */     
/*     */     private int state;
/*     */     
/*     */     RankState(int state) {
/*  22 */       this.state = state;
/*     */     }
/*     */     
/*     */     public int getState() {
/*  26 */       return this.state;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   private ConcurrentMap<Long, RankingData> rankDataCache = new ConcurrentHashMap<>();
/*  36 */   private volatile RankState rankState = RankState.OPEN;
/*     */   private volatile boolean needPush = true;
/*  38 */   private Set<Integer> serverList = new HashSet<>();
/*     */   @JsonIgnore
/*  40 */   private ArrayList<RankingData> closeCache = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RankDataCache(int actId, int rankType, int limit, boolean isDesc, int condition) {
/*  47 */     this.actId = actId;
/*  48 */     this.rankType = rankType;
/*  49 */     this.limit = limit;
/*  50 */     this.desc = isDesc;
/*  51 */     this.condition = condition;
/*     */   }
/*     */   
/*     */   public synchronized void updatePlayerRankData(int serverId, RankingData data) {
/*  55 */     this.serverList.add(Integer.valueOf(serverId));
/*  56 */     if (data != null && this.rankState == RankState.OPEN) {
/*  57 */       this.rankDataCache.put(Long.valueOf(data.playerId), data);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addServer(int serverId) {
/*  62 */     this.serverList.add(Integer.valueOf(serverId));
/*     */   }
/*     */   
/*     */   public synchronized ArrayList<RankingData> getActRankList(boolean isClose) {
/*  66 */     ArrayList<RankingData> resultList = new ArrayList<>();
/*     */     
/*  68 */     if (isClose && this.rankState == RankState.OPEN) {
/*  69 */       this.rankState = RankState.CLOSE;
/*     */     }
/*     */     
/*  72 */     if (this.rankState == RankState.CLOSE) {
/*  73 */       if (!this.closeCache.isEmpty()) {
/*  74 */         resultList = this.closeCache;
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/*  82 */         List<RankingData> list = (List<RankingData>)this.rankDataCache.values().stream().filter(o -> this.desc ? ((o.value >= this.condition)) : ((o.value <= this.condition))).sorted(CrossRankActType.getRankActType(this.rankType).getComparator()).limit(this.limit).collect(Collectors.toList());
/*  83 */         for (int i = 0; i < list.size(); i++) {
/*  84 */           RankingData data = list.get(i);
/*  85 */           data.rank = i + 1;
/*  86 */           resultList.add(data);
/*     */         } 
/*  88 */         this.closeCache = resultList;
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/*  97 */       List<RankingData> list = (List<RankingData>)this.rankDataCache.values().stream().filter(o -> this.desc ? ((o.value >= this.condition)) : ((o.value <= this.condition))).sorted(CrossRankActType.getRankActType(this.rankType).getComparator()).limit(this.limit).collect(Collectors.toList());
/*  98 */       for (int i = 0; i < list.size(); i++) {
/*  99 */         RankingData data = list.get(i);
/* 100 */         data.rank = i + 1;
/* 101 */         resultList.add(data);
/*     */       } 
/*     */     } 
/* 104 */     return resultList;
/*     */   }
/*     */   
/*     */   public int getActId() {
/* 108 */     return this.actId;
/*     */   }
/*     */   
/*     */   public void setActId(int actId) {
/* 112 */     this.actId = actId;
/*     */   }
/*     */   
/*     */   public int getRankType() {
/* 116 */     return this.rankType;
/*     */   }
/*     */   
/*     */   public void setRankType(int rankType) {
/* 120 */     this.rankType = rankType;
/*     */   }
/*     */   
/*     */   public int getLimit() {
/* 124 */     return this.limit;
/*     */   }
/*     */   
/*     */   public void setLimit(int limit) {
/* 128 */     this.limit = limit;
/*     */   }
/*     */   
/*     */   public ConcurrentMap<Long, RankingData> getRankDataCache() {
/* 132 */     return this.rankDataCache;
/*     */   }
/*     */   
/*     */   public void setRankDataCache(ConcurrentMap<Long, RankingData> rankDataCache) {
/* 136 */     this.rankDataCache = rankDataCache;
/*     */   }
/*     */   
/*     */   public RankState getRankState() {
/* 140 */     return this.rankState;
/*     */   }
/*     */   
/*     */   public void setRankState(RankState rankState) {
/* 144 */     this.rankState = rankState;
/*     */   }
/*     */   
/*     */   public Set<Integer> getServerList() {
/* 148 */     return this.serverList;
/*     */   }
/*     */   
/*     */   public void setServerList(Set<Integer> serverList) {
/* 152 */     this.serverList = serverList;
/*     */   }
/*     */   
/*     */   public boolean getNeedPush() {
/* 156 */     return this.needPush;
/*     */   }
/*     */   
/*     */   public void setNeedPush(boolean needPush) {
/* 160 */     this.needPush = needPush;
/*     */   }
/*     */   
/*     */   public boolean getDesc() {
/* 164 */     return this.desc;
/*     */   }
/*     */   
/*     */   public void setDesc(boolean desc) {
/* 168 */     this.desc = desc;
/*     */   }
/*     */   
/*     */   public int getCondition() {
/* 172 */     return this.condition;
/*     */   }
/*     */   
/*     */   public void setCondition(int condition) {
/* 176 */     this.condition = condition;
/*     */   }
/*     */   
/*     */   public RankDataCache() {}
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\rank\RankDataCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */