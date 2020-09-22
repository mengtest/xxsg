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
/*     */ public abstract class AbstractFightSide
/*     */   implements IFightSide
/*     */ {
/*     */   protected int curGroup;
/*  25 */   protected List<FightGroup> groupList = new ArrayList<>();
/*     */ 
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
/*  46 */   protected LinkedList<IFighter> candidateFighters = new LinkedList<>();
/*     */ 
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
/*  69 */     FightGroup group = getCurGroup();
/*  70 */     IFighter fighter = group.getFighterByGuid(guid);
/*  71 */     assert fighter != null;
/*     */ 
/*     */     
/*  74 */     if (fighter.isForbidAction()) {
/*  75 */       return 0;
/*     */     }
/*     */     
/*  78 */     return fighter.auto(fight);
/*     */   }
/*     */   
/*     */   public void initGuid(byte side) {
/*  82 */     this.groupList.forEach(fightGroup -> fightGroup.init(side));
/*  83 */     if (this.petFighter != null) {
/*  84 */       this.petFighter.setSide(side);
/*  85 */       this.petFighter.initGuid(side);
/*     */     } 
/*  87 */     if (this.stageFighter != null) {
/*  88 */       this.stageFighter.setSide(side);
/*  89 */       this.stageFighter.initGuid(side);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void getGroupData(List<FightGroupData> groupDatas) {
/*  95 */     this.groupList.forEach(group -> {
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
/* 109 */     if (groupDatas.size() > this.groupList.size())
/* 110 */       return;  for (int i = 0; i < groupDatas.size(); i++) {
/* 111 */       for (IFighter fighter : ((FightGroup)this.groupList.get(i)).getCandidateFighterList()) {
/* 112 */         ((FightGroupData)groupDatas.get(i)).candidateFighters.add(fighter.getFighterData());
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
/* 149 */     return this.groupList.get(this.curGroup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<FightGroup> getGroupList() {
/* 157 */     return this.groupList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGroup(FightGroup group) {
/* 166 */     this.groupList.add(group);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IFightSide.END_STATUS checkEndStatus(int curRound) {
/* 173 */     if (this.curGroup >= this.groupList.size())
/* 174 */       return IFightSide.END_STATUS.END_STATUS_END; 
/* 175 */     if (curRound >= getCurGroup().getMaxRound()) {
/* 176 */       return IFightSide.END_STATUS.END_STATUS_MAXROUND;
/*     */     }
/* 178 */     if (getCurGroup().getFighters(true).isEmpty()) {
/* 179 */       if (this.curGroup < this.groupList.size() - 1) {
/* 180 */         this.curGroup++;
/*     */         
/* 182 */         return IFightSide.END_STATUS.END_STATUS_NEXT;
/*     */       } 
/* 184 */       return IFightSide.END_STATUS.END_STATUS_END;
/*     */     } 
/* 186 */     return IFightSide.END_STATUS.END_STATUS_ON;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLeftNum() {
/* 194 */     return getCurGroup().getFighters(true).size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getTotalHurt() {
/* 202 */     return this.totalHurt;
/*     */   }
/*     */   
/*     */   public void updateTotalHurt(long hurtValue) {
/* 206 */     this.totalHurt += hurtValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalFirstHandVal() {
/* 214 */     return getCurGroup().getFirstHandVal();
/*     */   }
/*     */   
/*     */   public IFighter getCandidateFighter() {
/* 218 */     if (this.candidateFighters.isEmpty())
/* 219 */       return null; 
/* 220 */     return this.candidateFighters.remove();
/*     */   }
/*     */   
/*     */   public boolean addCandidateFigher(IFighter fighter) {
/* 224 */     if (this.candidateFighters.size() > 6)
/* 225 */       return false; 
/* 226 */     this.candidateFighters.add(fighter);
/* 227 */     return true;
/*     */   }
/*     */   
/*     */   public IFighter getPetFighter() {
/* 231 */     return this.petFighter;
/*     */   }
/*     */   
/*     */   public void setPetFighter(IFighter petFighter) {
/* 235 */     this.petFighter = petFighter;
/*     */   }
/*     */   
/*     */   public IFighter getStageFighter() {
/* 239 */     return this.stageFighter;
/*     */   }
/*     */   
/*     */   public void setStageFighter(IFighter stageFighter) {
/* 243 */     this.stageFighter = stageFighter;
/*     */   }
/*     */   
/*     */   public Pair<Integer, Integer> getZhenfa() {
/* 247 */     return this.zhenfa;
/*     */   }
/*     */   
/*     */   public void setZhenfa(Pair<Integer, Integer> zhenfa) {
/* 251 */     this.zhenfa = zhenfa;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\side\AbstractFightSide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */