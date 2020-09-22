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
/*     */ 
/*     */ public class FightGroup
/*     */ {
/*     */   private long id;
/*     */   private String name;
/*  31 */   private int maxRound = 50;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   private IFighter[] fighters = new IFighter[7];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   protected List<IFighter> candidateFighterList = new ArrayList<>();
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
/*  63 */     return true;
/*     */   }
/*     */   
/*     */   public void init(byte side) {
/*  67 */     boolean needInitFirstHand = (this.firstHandVal <= 0);
/*     */     
/*  69 */     this.curFightPos = 0;
/*  70 */     for (IFighter fighter : this.fighters) {
/*  71 */       if (null != fighter) {
/*  72 */         fighter.initGuid(side);
/*  73 */         if (needInitFirstHand) {
/*  74 */           this.firstHandVal = (int)(this.firstHandVal + fighter.getCalcAttr(AttributeType.FIRST_HAND));
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
/*  85 */     return this.firstHandVal;
/*     */   }
/*     */   
/*     */   public void roundStart(IFight fight) {
/*  89 */     this.curFightPos = 0;
/*  90 */     getFighters(true).forEach(fighter -> fighter.preStart(fight));
/*     */   }
/*     */   
/*     */   public IFighter nextActionFighter() {
/*  94 */     if (this.curFightPos > 6)
/*  95 */       return null; 
/*  96 */     while (this.curFightPos < 6) {
/*  97 */       this.curFightPos++;
/*  98 */       IFighter fighter = this.fighters[this.curFightPos];
/*  99 */       if (null == fighter || !fighter.canFight())
/*     */         continue; 
/* 101 */       return fighter;
/*     */     } 
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void getFighterData(List<FighterData> fighterDatas) {
/* 108 */     for (IFighter fighter : this.fighters) {
/* 109 */       if (null != fighter)
/*     */       {
/* 111 */         fighterDatas.add(fighter.getFighterData());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IFighter getFighterByGuid(byte guid) {
/* 121 */     for (IFighter fighter : this.fighters) {
/* 122 */       if (null != fighter && fighter.getGuid() == guid)
/* 123 */         return fighter; 
/*     */     } 
/* 125 */     return null;
/*     */   }
/*     */   
/*     */   public long getId() {
/* 129 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(long id) {
/* 133 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 137 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 141 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IFighter getFighterByPos(byte pos) {
/* 150 */     for (IFighter fighter : this.fighters) {
/* 151 */       if (fighter != null && fighter.getCalcPos() == pos)
/* 152 */         return fighter; 
/*     */     } 
/* 154 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getAllHp() {
/* 162 */     long hp = 0L;
/* 163 */     for (IFighter fighter : this.fighters) {
/* 164 */       if (fighter != null) {
/* 165 */         hp += fighter.getHP();
/*     */       }
/*     */     } 
/* 168 */     return hp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getAllMaxHp() {
/* 176 */     long hp = 0L;
/* 177 */     for (IFighter fighter : this.fighters) {
/* 178 */       if (fighter != null) {
/* 179 */         hp += fighter.getMaxHp();
/*     */       }
/*     */     } 
/* 182 */     return hp;
/*     */   }
/*     */   
/*     */   public int removeDeadFighter() {
/* 186 */     int count = 0;
/* 187 */     for (int i = 0; i < this.fighters.length; i++) {
/* 188 */       if (this.fighters[i] != null && this.fighters[i].isDead()) {
/* 189 */         this.fighters[i] = null;
/* 190 */         count++;
/*     */       } 
/*     */     } 
/* 193 */     return count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void replaceFighter(IFighter newFighter, IFighter target) {
/* 202 */     this.fighters[target.getCalcPos()] = newFighter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<IFighter> getFighters(boolean isAlive) {
/* 211 */     List<IFighter> fighterList = new ArrayList<>();
/* 212 */     if (!isAlive) {
/* 213 */       for (IFighter fighter : this.fighters) {
/* 214 */         if (null != fighter)
/*     */         {
/* 216 */           fighterList.add(fighter); } 
/*     */       } 
/* 218 */       return fighterList;
/*     */     } 
/* 220 */     for (IFighter fighter : this.fighters) {
/* 221 */       if (null != fighter && !fighter.isDead())
/*     */       {
/* 223 */         fighterList.add(fighter); } 
/*     */     } 
/* 225 */     return fighterList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<IFighter> getDeadFighters() {
/* 233 */     List<IFighter> fighterList = new ArrayList<>();
/* 234 */     for (IFighter fighter : this.fighters) {
/* 235 */       if (null != fighter && fighter.isDead())
/* 236 */         fighterList.add(fighter); 
/*     */     } 
/* 238 */     return fighterList;
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
/* 269 */     List<IFighter> fighterList = new ArrayList<>();
/* 270 */     if (!isAlive) {
/* 271 */       for (IFighter fighter : this.fighters) {
/* 272 */         if (null != fighter && guid != fighter.getGuid())
/*     */         {
/* 274 */           fighterList.add(fighter); } 
/*     */       } 
/* 276 */       return fighterList;
/*     */     } 
/*     */     
/* 279 */     for (IFighter fighter : this.fighters) {
/* 280 */       if (null != fighter && guid != fighter.getGuid() && !fighter.isDead())
/*     */       {
/* 282 */         fighterList.add(fighter); } 
/*     */     } 
/* 284 */     return fighterList;
/*     */   }
/*     */   
/*     */   public void setFighters(List<IFighter> fighters) {
/* 288 */     for (IFighter fighter : fighters) {
/* 289 */       this.fighters[fighter.getCalcPos()] = fighter;
/*     */     }
/*     */   }
/*     */   
/*     */   public int getMaxRound() {
/* 294 */     return this.maxRound;
/*     */   }
/*     */   
/*     */   public void setMaxRound(int maxRound) {
/* 298 */     this.maxRound = maxRound;
/*     */   }
/*     */   
/*     */   public void setFirstHandVal(int firstHandVal) {
/* 302 */     this.firstHandVal = firstHandVal;
/*     */   }
/*     */   
/*     */   public List<IFighter> getCandidateFighterList() {
/* 306 */     return this.candidateFighterList;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\side\FightGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */