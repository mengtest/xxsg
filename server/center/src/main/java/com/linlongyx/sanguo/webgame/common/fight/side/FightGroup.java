/*     */ package com.linlongyx.sanguo.webgame.common.fight.side;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FighterData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FightGroup
/*     */ {
/*     */   private long id;
/*     */   private String name;
/*  30 */   private int maxRound = 50;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   private IFighter[] fighters = new IFighter[7];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   protected List<IFighter> candidateFighterList = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int curFightPos;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int firstHandVal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAuto(byte guid) {
/*  62 */     return true;
/*     */   }
/*     */   
/*     */   public void init(byte side) {
/*  66 */     boolean needInitFirstHand = (this.firstHandVal <= 0);
/*  67 */     this.curFightPos = 0;
/*  68 */     for (IFighter fighter : this.fighters) {
/*  69 */       if (null != fighter) {
/*  70 */         fighter.initGuid(side);
/*  71 */         if (needInitFirstHand) {
/*  72 */           this.firstHandVal = (int)(this.firstHandVal + fighter.getCalcAttr(AttributeType.FIRST_HAND));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFirstHandVal() {
/*  83 */     return this.firstHandVal;
/*     */   }
/*     */   
/*     */   public void roundStart(IFight fight) {
/*  87 */     this.curFightPos = 0;
/*  88 */     getFighters(true).forEach(fighter -> fighter.preStart(fight));
/*     */   }
/*     */   
/*     */   public IFighter nextActionFighter() {
/*  92 */     if (this.curFightPos > 6)
/*  93 */       return null; 
/*  94 */     while (this.curFightPos < 6) {
/*  95 */       this.curFightPos++;
/*  96 */       IFighter fighter = this.fighters[this.curFightPos];
/*  97 */       if (null == fighter || !fighter.canFight())
/*     */         continue; 
/*  99 */       return fighter;
/*     */     } 
/* 101 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void getFighterData(List<FighterData> fighterDatas) {
/* 106 */     for (IFighter fighter : this.fighters) {
/* 107 */       if (null != fighter)
/*     */       {
/* 109 */         fighterDatas.add(fighter.getFighterData());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IFighter getFighterByGuid(byte guid) {
/* 119 */     for (IFighter fighter : this.fighters) {
/* 120 */       if (null != fighter && fighter.getGuid() == guid)
/* 121 */         return fighter; 
/*     */     } 
/* 123 */     return null;
/*     */   }
/*     */   
/*     */   public long getId() {
/* 127 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(long id) {
/* 131 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 135 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 139 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IFighter getFighterByPos(byte pos) {
/* 148 */     for (IFighter fighter : this.fighters) {
/* 149 */       if (fighter != null && fighter.getCalcPos() == pos)
/* 150 */         return fighter; 
/*     */     } 
/* 152 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getAllHp() {
/* 160 */     long hp = 0L;
/* 161 */     for (IFighter fighter : this.fighters) {
/* 162 */       if (fighter != null) {
/* 163 */         hp += fighter.getHP();
/*     */       }
/*     */     } 
/* 166 */     return hp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getAllMaxHp() {
/* 174 */     long hp = 0L;
/* 175 */     for (IFighter fighter : this.fighters) {
/* 176 */       if (fighter != null) {
/* 177 */         hp += fighter.getMaxHp();
/*     */       }
/*     */     } 
/* 180 */     return hp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceFighter(IFighter newFighter, IFighter target) {
/* 189 */     this.fighters[target.getCalcPos()] = newFighter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<IFighter> getFighters(boolean isAlive) {
/* 198 */     List<IFighter> fighterList = new ArrayList<>();
/* 199 */     if (!isAlive) {
/* 200 */       for (IFighter fighter : this.fighters) {
/* 201 */         if (null != fighter)
/*     */         {
/* 203 */           fighterList.add(fighter); } 
/*     */       } 
/* 205 */       return fighterList;
/*     */     } 
/* 207 */     for (IFighter fighter : this.fighters) {
/* 208 */       if (null != fighter && !fighter.isDead())
/*     */       {
/* 210 */         fighterList.add(fighter); } 
/*     */     } 
/* 212 */     return fighterList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<IFighter> getDeadFighters() {
/* 220 */     List<IFighter> fighterList = new ArrayList<>();
/* 221 */     for (IFighter fighter : this.fighters) {
/* 222 */       if (null != fighter && fighter.isDead())
/* 223 */         fighterList.add(fighter); 
/*     */     } 
/* 225 */     return fighterList;
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
/*     */   public List<IFighter> getFightersExclude(boolean isAlive, byte guid) {
/* 256 */     List<IFighter> fighterList = new ArrayList<>();
/* 257 */     if (!isAlive) {
/* 258 */       for (IFighter fighter : this.fighters) {
/* 259 */         if (null != fighter && guid != fighter.getGuid())
/*     */         {
/* 261 */           fighterList.add(fighter); } 
/*     */       } 
/* 263 */       return fighterList;
/*     */     } 
/*     */     
/* 266 */     for (IFighter fighter : this.fighters) {
/* 267 */       if (null != fighter && guid != fighter.getGuid() && !fighter.isDead())
/*     */       {
/* 269 */         fighterList.add(fighter); } 
/*     */     } 
/* 271 */     return fighterList;
/*     */   }
/*     */   
/*     */   public void setFighters(List<IFighter> fighters) {
/* 275 */     for (IFighter fighter : fighters) {
/* 276 */       this.fighters[fighter.getCalcPos()] = fighter;
/*     */     }
/*     */   }
/*     */   
/*     */   public int removeDeadFighter() {
/* 281 */     int count = 0;
/* 282 */     for (int i = 0; i < this.fighters.length; i++) {
/* 283 */       if (this.fighters[i] != null && this.fighters[i].isDead()) {
/* 284 */         this.fighters[i] = null;
/* 285 */         count++;
/*     */       } 
/*     */     } 
/* 288 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxRound() {
/* 293 */     return this.maxRound;
/*     */   }
/*     */   
/*     */   public void setMaxRound(int maxRound) {
/* 297 */     this.maxRound = maxRound;
/*     */   }
/*     */   
/*     */   public void setFirstHandVal(int firstHandVal) {
/* 301 */     this.firstHandVal = firstHandVal;
/*     */   }
/*     */   
/*     */   public List<IFighter> getCandidateFighterList() {
/* 305 */     return this.candidateFighterList;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\side\FightGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */