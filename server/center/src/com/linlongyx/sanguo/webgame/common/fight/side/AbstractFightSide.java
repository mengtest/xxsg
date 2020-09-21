/*     */ package com.linlongyx.sanguo.webgame.common.fight.side;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FightGroupData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
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
/*     */ public abstract class AbstractFightSide
/*     */   implements IFightSide
/*     */ {
/*     */   protected int curGroup;
/*  29 */   protected List<FightGroup> groupList = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IFighter petFighter;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IFighter stageFighter;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Pair<Integer, Integer> zhenfa;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   protected LinkedList<IFighter> candidateFighters = new LinkedList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isAuto;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected byte side;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected long totalHurt;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int preAction(byte guid, IFight fight) {
/*  71 */     FightGroup group = getCurGroup();
/*  72 */     IFighter fighter = group.getFighterByGuid(guid);
/*  73 */     assert fighter != null;
/*     */ 
/*     */     
/*  76 */     if (fighter.isForbidAction()) {
/*  77 */       return 0;
/*     */     }
/*     */     
/*  80 */     return fighter.auto(fight);
/*     */   }
/*     */   
/*     */   public void initGuid(byte side) {
/*  84 */     this.groupList.forEach(fightGroup -> fightGroup.init(side));
/*  85 */     if (this.petFighter != null) {
/*  86 */       this.petFighter.setSide(side);
/*  87 */       this.petFighter.initGuid(side);
/*     */     } 
/*  89 */     if (this.stageFighter != null) {
/*  90 */       this.stageFighter.setSide(side);
/*  91 */       this.stageFighter.initGuid(side);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void getGroupData(List<FightGroupData> groupDatas) {
/*  97 */     this.groupList.forEach(group -> {
/*     */           FightGroupData groupData = new FightGroupData();
/*     */           if (this.petFighter != null)
/*     */             groupData.fighters.add(this.petFighter.getFighterData()); 
/*     */           if (this.stageFighter != null)
/*     */             groupData.fighters.add(this.stageFighter.getFighterData()); 
/*     */           group.getFighterData(groupData.fighters);
/*     */           groupData.name = group.getName();
/*     */           groupData.firstHandVal = group.getFirstHandVal();
/*     */           groupDatas.add(groupData);
/*     */         });
/*     */   }
/*     */   
/*     */   public void getCandidateFighterData(List<FightGroupData> groupDatas) {
/* 111 */     if (groupDatas.size() > this.groupList.size())
/* 112 */       return;  for (int i = 0; i < groupDatas.size(); i++) {
/* 113 */       for (IFighter fighter : ((FightGroup)this.groupList.get(i)).getCandidateFighterList()) {
/* 114 */         ((FightGroupData)groupDatas.get(i)).candidateFighters.add(fighter.getFighterData());
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void syncRecord(ResponseBase response) {}
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
/*     */   public FightGroup getCurGroup() {
/* 151 */     return this.groupList.get(this.curGroup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<FightGroup> getGroupList() {
/* 159 */     return this.groupList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGroup(FightGroup group) {
/* 168 */     this.groupList.add(group);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IFightSide.END_STATUS checkEndStatus(int curRound) {
/* 175 */     if (this.curGroup >= this.groupList.size())
/* 176 */       return IFightSide.END_STATUS.END_STATUS_END; 
/* 177 */     if (curRound >= getCurGroup().getMaxRound()) {
/* 178 */       return IFightSide.END_STATUS.END_STATUS_MAXROUND;
/*     */     }
/* 180 */     if (getCurGroup().getFighters(true).isEmpty()) {
/* 181 */       if (this.curGroup < this.groupList.size() - 1) {
/* 182 */         this.curGroup++;
/*     */         
/* 184 */         return IFightSide.END_STATUS.END_STATUS_NEXT;
/*     */       } 
/* 186 */       return IFightSide.END_STATUS.END_STATUS_END;
/*     */     } 
/* 188 */     return IFightSide.END_STATUS.END_STATUS_ON;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLeftNum() {
/* 196 */     return getCurGroup().getFighters(true).size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getTotalHurt() {
/* 204 */     return this.totalHurt;
/*     */   }
/*     */   
/*     */   public void updateTotalHurt(long hurtValue) {
/* 208 */     this.totalHurt += hurtValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalFirstHandVal() {
/* 216 */     return getCurGroup().getFirstHandVal();
/*     */   }
/*     */   
/*     */   public IFighter getCandidateFighter() {
/* 220 */     if (this.candidateFighters.isEmpty())
/* 221 */       return null; 
/* 222 */     return this.candidateFighters.remove();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addCandidateFigher(IFighter fighter) {
/* 227 */     if (this.candidateFighters.size() > 6)
/* 228 */       return false; 
/* 229 */     this.candidateFighters.add(fighter);
/* 230 */     return true;
/*     */   }
/*     */   
/*     */   public IFighter getPetFighter() {
/* 234 */     return this.petFighter;
/*     */   }
/*     */   
/*     */   public void setPetFighter(IFighter petFighter) {
/* 238 */     this.petFighter = petFighter;
/*     */   }
/*     */   
/*     */   public IFighter getStageFighter() {
/* 242 */     return this.stageFighter;
/*     */   }
/*     */   
/*     */   public void setStageFighter(IFighter stageFighter) {
/* 246 */     this.stageFighter = stageFighter;
/*     */   }
/*     */   
/*     */   public Pair<Integer, Integer> getZhenfa() {
/* 250 */     return this.zhenfa;
/*     */   }
/*     */   
/*     */   public void setZhenfa(Pair<Integer, Integer> zhenfa) {
/* 254 */     this.zhenfa = zhenfa;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\side\AbstractFightSide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */