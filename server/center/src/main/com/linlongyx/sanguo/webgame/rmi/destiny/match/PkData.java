/*     */ package com.linlongyx.sanguo.webgame.rmi.destiny.match;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkRecord;
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class PkData implements Serializable {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String id;
/*     */   private PkPlayerData leftPlayer;
/*     */   private PkPlayerData rightPlayer;
/*     */   private PkRecord pkRecord;
/*     */   private long winner;
/*     */   
/*     */   public enum PkState {
/*  16 */     prepare(0),
/*  17 */     fighting(1),
/*  18 */     end(2);
/*     */     
/*     */     int state;
/*     */     
/*     */     PkState(int state) {
/*  23 */       this.state = state;
/*     */     }
/*     */     
/*     */     public int getState() {
/*  27 */       return this.state;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum PkSideState {
/*  32 */     none(0),
/*  33 */     singleLeft(1),
/*  34 */     singleRight(2),
/*  35 */     doubleSide(3);
/*     */     
/*     */     int state;
/*     */ 
/*     */     
/*     */     PkSideState(int state) {
/*  41 */       this.state = state;
/*     */     }
/*     */     
/*     */     public int getState() {
/*  45 */       return this.state;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   private volatile PkState pkState = PkState.prepare;
/*     */ 
/*     */   
/*     */   public PkData() {}
/*     */ 
/*     */   
/*     */   public PkData(Zone zone, String id) {
/*  66 */     this.id = id;
/*  67 */     zone.pkRecords().putIfAbsent(id, this);
/*     */   }
/*     */   
/*     */   public void initLeftPlayer(DestinyPlayerData destinyPlayerData) {
/*  71 */     PkPlayerData pkPlayerData = new PkPlayerData();
/*  72 */     pkPlayerData.setDestinyPoint(destinyPlayerData.getDestinyPoint());
/*  73 */     pkPlayerData.setFightValue(destinyPlayerData.getFightValue());
/*  74 */     pkPlayerData.setPlayerId(destinyPlayerData.getPlayerId());
/*  75 */     pkPlayerData.setPlayerName(destinyPlayerData.getPlayerName());
/*  76 */     pkPlayerData.setHead(destinyPlayerData.getHead());
/*  77 */     pkPlayerData.setQuality(destinyPlayerData.getQuality());
/*  78 */     pkPlayerData.setModelData(destinyPlayerData.getModelData());
/*     */ 
/*     */     
/*  81 */     pkPlayerData.setCandidateFighters(destinyPlayerData.getCandidateFighters());
/*  82 */     pkPlayerData.setZhenfa(destinyPlayerData.getZhenfa());
/*     */     
/*  84 */     pkPlayerData.setDefaultFighterData(destinyPlayerData.getFighters());
/*  85 */     this.leftPlayer = pkPlayerData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initLeftPlayer(PkPlayerData playerData) {
/*  90 */     PkPlayerData pkPlayerData = new PkPlayerData();
/*  91 */     pkPlayerData.setDestinyPoint(playerData.getDestinyPoint());
/*  92 */     pkPlayerData.setFightValue(playerData.getFightValue());
/*  93 */     pkPlayerData.setPlayerId(playerData.getPlayerId());
/*  94 */     pkPlayerData.setPlayerName(playerData.getPlayerName());
/*  95 */     pkPlayerData.setQuality(playerData.getQuality());
/*  96 */     pkPlayerData.setDefaultFighterData(playerData.getFighterData());
/*  97 */     pkPlayerData.setHead(playerData.getHead());
/*  98 */     pkPlayerData.setModelData(playerData.getModelData());
/*  99 */     pkPlayerData.setCandidateFighters(playerData.getCandidateFighters());
/* 100 */     pkPlayerData.setZhenfa(playerData.getZhenfa());
/*     */     
/* 102 */     this.leftPlayer = pkPlayerData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initRightPlayer(DestinyPlayerData destinyPlayerData) {
/* 107 */     PkPlayerData pkPlayerData = new PkPlayerData();
/* 108 */     pkPlayerData.setDestinyPoint(destinyPlayerData.getDestinyPoint());
/* 109 */     pkPlayerData.setFightValue(destinyPlayerData.getFightValue());
/* 110 */     pkPlayerData.setPlayerId(destinyPlayerData.getPlayerId());
/* 111 */     pkPlayerData.setPlayerName(destinyPlayerData.getPlayerName());
/* 112 */     pkPlayerData.setHead(destinyPlayerData.getHead());
/* 113 */     pkPlayerData.setQuality(destinyPlayerData.getQuality());
/* 114 */     pkPlayerData.setModelData(destinyPlayerData.getModelData());
/*     */ 
/*     */     
/* 117 */     pkPlayerData.setCandidateFighters(destinyPlayerData.getCandidateFighters());
/* 118 */     pkPlayerData.setZhenfa(destinyPlayerData.getZhenfa());
/*     */     
/* 120 */     pkPlayerData.setDefaultFighterData(destinyPlayerData.getFighters());
/* 121 */     this.rightPlayer = pkPlayerData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initRightPlayer(PkPlayerData playerData) {
/* 126 */     PkPlayerData pkPlayerData = new PkPlayerData();
/* 127 */     pkPlayerData.setDestinyPoint(playerData.getDestinyPoint());
/* 128 */     pkPlayerData.setFightValue(playerData.getFightValue());
/* 129 */     pkPlayerData.setPlayerId(playerData.getPlayerId());
/* 130 */     pkPlayerData.setPlayerName(playerData.getPlayerName());
/* 131 */     pkPlayerData.setHead(playerData.getHead());
/* 132 */     pkPlayerData.setQuality(playerData.getQuality());
/* 133 */     pkPlayerData.setModelData(playerData.getModelData());
/* 134 */     pkPlayerData.setCandidateFighters(playerData.getCandidateFighters());
/* 135 */     pkPlayerData.setZhenfa(playerData.getZhenfa());
/*     */     
/* 137 */     pkPlayerData.setDefaultFighterData(playerData.getFighterData());
/* 138 */     this.rightPlayer = pkPlayerData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int startPkFight(Zone zone) {
/* 155 */     this.pkState = PkState.end;
/*     */     
/* 157 */     if (this.leftPlayer == null && this.rightPlayer == null) {
/* 158 */       return -1;
/*     */     }
/* 160 */     if (this.leftPlayer == null) {
/* 161 */       this.winner = this.rightPlayer.getPlayerId();
/*     */       
/* 163 */       return 1;
/*     */     } 
/* 165 */     if (this.rightPlayer == null) {
/* 166 */       this.winner = this.leftPlayer.getPlayerId();
/*     */       
/* 168 */       return 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     if (this.leftPlayer.getFighterData().isEmpty() && this.rightPlayer.getFighterData().isEmpty()) {
/* 175 */       return -1;
/*     */     }
/* 177 */     if (this.leftPlayer.getFighterData().isEmpty() && !this.rightPlayer.getFighterData().isEmpty()) {
/* 178 */       this.winner = this.rightPlayer.getPlayerId();
/* 179 */       return 1;
/*     */     } 
/* 181 */     if (!this.leftPlayer.getFighterData().isEmpty() && this.rightPlayer.getFighterData().isEmpty()) {
/* 182 */       this.winner = this.leftPlayer.getPlayerId();
/* 183 */       return 1;
/*     */     } 
/*     */     
/* 186 */     this.pkState = PkState.fighting;
/* 187 */     DestinyRaceFight destinyRaceFight = new DestinyRaceFight(this.id, this.leftPlayer, this.rightPlayer);
/* 188 */     this.pkRecord = destinyRaceFight.start();
/*     */     
/* 190 */     if (this.pkRecord != null) {
/* 191 */       this.winner = this.pkRecord.winner;
/*     */     }
/* 193 */     this.pkState = PkState.end;
/* 194 */     return 1;
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public PkPlayerData getWinnerData() {
/* 199 */     if (this.winner == 0L) return null; 
/* 200 */     return (this.winner == this.leftPlayer.getPlayerId()) ? this.leftPlayer : this.rightPlayer;
/*     */   }
/*     */   
/*     */   public PkPlayerData getLeftPlayer() {
/* 204 */     return this.leftPlayer;
/*     */   }
/*     */   
/*     */   public void setLeftPlayer(PkPlayerData leftPlayer) {
/* 208 */     this.leftPlayer = leftPlayer;
/*     */   }
/*     */   
/*     */   public PkPlayerData getRightPlayer() {
/* 212 */     return this.rightPlayer;
/*     */   }
/*     */   
/*     */   public void setRightPlayer(PkPlayerData rightPlayer) {
/* 216 */     this.rightPlayer = rightPlayer;
/*     */   }
/*     */   
/*     */   public PkRecord getPkRecord() {
/* 220 */     return this.pkRecord;
/*     */   }
/*     */   
/*     */   public void setPkRecord(PkRecord pkRecord) {
/* 224 */     this.pkRecord = pkRecord;
/*     */   }
/*     */   
/*     */   public long getWinner() {
/* 228 */     return this.winner;
/*     */   }
/*     */   
/*     */   public void setWinner(long winner) {
/* 232 */     this.winner = winner;
/*     */   }
/*     */   
/*     */   public PkState getPkState() {
/* 236 */     return this.pkState;
/*     */   }
/*     */   
/*     */   public void setPkState(PkState pkState) {
/* 240 */     this.pkState = pkState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 252 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/* 256 */     this.id = id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 261 */     return "PkData{id='" + this.id + '\'' + ", leftPlayer=" + this.leftPlayer + ", rightPlayer=" + this.rightPlayer + ", pkRecord=" + this.pkRecord + ", winner=" + this.winner + ", pkState=" + this.pkState + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\match\PkData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */