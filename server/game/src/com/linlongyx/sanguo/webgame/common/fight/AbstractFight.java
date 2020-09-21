/*     */ package com.linlongyx.sanguo.webgame.common.fight;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.FightGroup;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaRestraintBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BoutPlayData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.HpData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public abstract class AbstractFight
/*     */   implements IFight
/*     */ {
/*     */   protected byte type;
/*  35 */   protected byte result = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   protected IFightSide[] sides = new IFightSide[2];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   protected int curRound = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   protected int totalRound = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   protected byte curWaveId = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected byte firstHand = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isZhenfaAffect = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   protected List<BoutPlayData> totalPlayDatas = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   protected StringBuffer debugBuff = new StringBuffer();
/*     */   
/*  81 */   protected boolean isDebug = MContext.getDebug();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IFightSide getSide(byte side) {
/*  92 */     return this.sides[side];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<IFighter> getCurFighters(byte side, boolean isAliveOnly) {
/* 102 */     IFightSide fightSide = getSide(side);
/* 103 */     if (null == fightSide)
/* 104 */       return null; 
/* 105 */     return fightSide.getCurGroup().getFighters(isAliveOnly);
/*     */   }
/*     */   
/*     */   public List<IFighter> getCurFightersExclude(byte side, boolean isAliveOnly, byte guid) {
/* 109 */     IFightSide fightSide = getSide(side);
/* 110 */     if (null == fightSide)
/* 111 */       return null; 
/* 112 */     return fightSide.getCurGroup().getFightersExclude(isAliveOnly, guid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initSide(int index, IFightSide fightSide) {
/* 122 */     this.sides[index] = fightSide;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoutPlayData genBoutPlayData() {
/* 132 */     if (this.totalPlayDatas.size() > this.totalRound) {
/* 133 */       return this.totalPlayDatas.get(this.totalRound);
/*     */     }
/* 135 */     BoutPlayData playData = new BoutPlayData();
/*     */     
/* 137 */     playData.waveId = this.curWaveId;
/* 138 */     playData.roundId = this.curRound;
/* 139 */     this.totalPlayDatas.add(playData);
/*     */     
/* 141 */     return playData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getGroupData(FightRecordResponse response) {
/* 149 */     this.sides[0].getGroupData(response.lGroups);
/* 150 */     this.sides[1].getGroupData(response.rGroups);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getCandidateFighterData(FightRecordResponse response) {
/* 158 */     this.sides[0].getCandidateFighterData(response.lGroups);
/* 159 */     this.sides[1].getCandidateFighterData(response.rGroups);
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
/*     */   protected void init() {
/* 174 */     for (byte side = 0; side < this.sides.length; side = (byte)(side + 1)) {
/* 175 */       this.sides[side].initGuid(side);
/*     */     }
/* 177 */     this.curRound = this.totalRound = 0;
/* 178 */     this.curWaveId = 0;
/*     */   }
/*     */   
/*     */   protected void init(byte side) {
/* 182 */     this.sides[side].initGuid(side);
/* 183 */     this.curRound = this.totalRound = 0;
/* 184 */     this.curWaveId = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reset() {
/* 191 */     for (byte side = 0; side < this.sides.length; side = (byte)(side + 1)) {
/* 192 */       this.sides[side] = null;
/*     */     }
/* 194 */     this.curRound = this.totalRound = 0;
/* 195 */     this.curWaveId = 0;
/* 196 */     this.result = 2;
/*     */   }
/*     */   
/*     */   private Map<Integer, List<Integer>> getZhenfaBuffMap(ZhenfaBean.StarBean starBean) {
/* 200 */     Map<Integer, List<Integer>> buffs = new HashMap<>();
/* 201 */     if (!starBean.getBuffOne().isEmpty()) {
/* 202 */       buffs.put(Integer.valueOf(1), starBean.getBuffOne());
/*     */     }
/* 204 */     if (!starBean.getBuffTwo().isEmpty()) {
/* 205 */       buffs.put(Integer.valueOf(2), starBean.getBuffTwo());
/*     */     }
/* 207 */     if (!starBean.getBuffThree().isEmpty()) {
/* 208 */       buffs.put(Integer.valueOf(3), starBean.getBuffThree());
/*     */     }
/* 210 */     if (!starBean.getBuffFour().isEmpty()) {
/* 211 */       buffs.put(Integer.valueOf(4), starBean.getBuffFour());
/*     */     }
/* 213 */     if (!starBean.getBuffFive().isEmpty()) {
/* 214 */       buffs.put(Integer.valueOf(5), starBean.getBuffFive());
/*     */     }
/* 216 */     if (!starBean.getBuffSix().isEmpty()) {
/* 217 */       buffs.put(Integer.valueOf(6), starBean.getBuffSix());
/*     */     }
/* 219 */     return buffs;
/*     */   }
/*     */   
/*     */   private void addZhenfaBuff(IFightSide side) {
/* 223 */     if (side.getZhenfa() != null && ((Integer)side.getZhenfa().getKey()).intValue() > 0) {
/* 224 */       ZhenfaBean zhenfaBean = (ZhenfaBean)JsonTableService.getJsonData(((Integer)side.getZhenfa().getKey()).intValue(), ZhenfaBean.class);
/* 225 */       if (zhenfaBean != null && zhenfaBean.getStar().containsKey(side.getZhenfa().getValue())) {
/* 226 */         ZhenfaBean.StarBean starBean = (ZhenfaBean.StarBean)zhenfaBean.getStar().get(side.getZhenfa().getValue());
/* 227 */         Map<Integer, List<Integer>> buffs = getZhenfaBuffMap(starBean);
/* 228 */         side.getGroupList().forEach(group -> group.getFighters(true).forEach(()));
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
/*     */   private void zhenfaRestraint(IFightSide side1, IFightSide sdie2) {
/* 242 */     ZhenfaRestraintBean zhenfaRestraintBean = (ZhenfaRestraintBean)JsonTableService.getJsonData(((Integer)side1.getZhenfa().getKey()).intValue(), ZhenfaRestraintBean.class);
/* 243 */     for (ZhenfaRestraintBean.BuffBean bean : zhenfaRestraintBean.getBuff()) {
/* 244 */       if (bean.getZhenfa() == ((Integer)sdie2.getZhenfa().getKey()).intValue()) {
/* 245 */         side1.getGroupList().forEach(group -> group.getFighters(true).forEach(()));
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkZhenfaBuff() {
/* 257 */     if (this.isZhenfaAffect) {
/*     */       return;
/*     */     }
/* 260 */     this.isZhenfaAffect = true;
/*     */     
/* 262 */     IFightSide leftSide = getSide((byte)(this.firstHand % 2));
/* 263 */     IFightSide rightSide = getSide((byte)((this.firstHand + 1) % 2));
/*     */ 
/*     */     
/* 266 */     addZhenfaBuff(leftSide);
/* 267 */     addZhenfaBuff(rightSide);
/*     */ 
/*     */     
/* 270 */     if (leftSide.getZhenfa() != null && rightSide.getZhenfa() != null) {
/* 271 */       zhenfaRestraint(leftSide, rightSide);
/* 272 */       zhenfaRestraint(rightSide, leftSide);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void action() {
/* 280 */     resetDebugStr();
/* 281 */     checkFirstHand();
/*     */     
/* 283 */     while (!checkEnd()) {
/*     */ 
/*     */ 
/*     */       
/* 287 */       this.debugBuff.append("第").append(this.curRound + 1).append("回合：\n");
/* 288 */       roundFight();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void petFighterAuto(IFightSide side) {
/* 293 */     if (!side.getCurGroup().getFighters(true).isEmpty() && side.getPetFighter() != null) {
/* 294 */       side.getPetFighter().auto(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void stageFighterAuto(IFightSide side) {
/* 300 */     if (side.getStageFighter() != null) {
/* 301 */       side.getStageFighter().auto(this);
/* 302 */       side.setStageFighter(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkCandidate(IFightSide side) {
/* 311 */     List<IFighter> deadFighters = side.getCurGroup().getDeadFighters();
/* 312 */     for (IFighter fighter : deadFighters) {
/* 313 */       if (fighter.isDead()) {
/* 314 */         IFighter newFighter = side.getCandidateFighter();
/* 315 */         if (newFighter == null)
/* 316 */           break;  newFighter.replace(this, fighter);
/* 317 */         this.debugBuff.append("名将上阵: ").append(newFighter.getId()).append(", 旧的武将: ").append(fighter.getId());
/* 318 */         side.getCurGroup().getCandidateFighterList().add(newFighter);
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
/*     */   protected void roundFight() {
/* 331 */     IFightSide side1 = getSide((byte)(this.firstHand % 2));
/* 332 */     IFightSide side2 = getSide((byte)((this.firstHand + 1) % 2));
/*     */ 
/*     */ 
/*     */     
/* 336 */     for (byte i = 0; i < 2; i = (byte)(i + 1)) {
/* 337 */       getSide(i).getCurGroup().roundStart(this);
/* 338 */       checkCandidate(getSide(i));
/* 339 */       if (checkEnd()) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 344 */     checkZhenfaBuff();
/*     */ 
/*     */     
/* 347 */     stageFighterAuto(side1);
/* 348 */     stageFighterAuto(side2);
/*     */     
/* 350 */     if (checkEnd()) {
/*     */       return;
/*     */     }
/*     */     
/* 354 */     for (byte pos = 1; pos <= 6; pos = (byte)(pos + 1)) {
/* 355 */       IFighter f1 = side1.getCurGroup().getFighterByPos(pos);
/* 356 */       if (f1 != null && f1.canFight()) {
/* 357 */         f1.auto(this);
/*     */       }
/*     */       
/* 360 */       checkCandidate(side2);
/* 361 */       checkCandidate(side1);
/*     */       
/* 363 */       if (checkEnd())
/*     */         return; 
/* 365 */       IFighter f2 = side2.getCurGroup().getFighterByPos(pos);
/* 366 */       if (f2 != null && f2.canFight()) {
/* 367 */         f2.auto(this);
/*     */       }
/*     */       
/* 370 */       checkCandidate(side1);
/* 371 */       checkCandidate(side2);
/*     */       
/* 373 */       if (checkEnd()) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 379 */     petFighterAuto(side1);
/* 380 */     checkCandidate(side2);
/* 381 */     checkCandidate(side1);
/* 382 */     if (checkEnd()) {
/*     */       return;
/*     */     }
/* 385 */     petFighterAuto(side2);
/* 386 */     checkCandidate(side1);
/* 387 */     checkCandidate(side2);
/* 388 */     if (checkEnd()) {
/*     */       return;
/*     */     }
/* 391 */     if (checkEnd())
/*     */       return; 
/* 393 */     this.curRound++;
/* 394 */     this.totalRound++;
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
/*     */   protected boolean checkEnd() {
/* 408 */     for (int side = 0; side < this.sides.length; side++) {
/* 409 */       IFightSide.END_STATUS status = this.sides[side].checkEndStatus(this.curRound);
/* 410 */       if (status == IFightSide.END_STATUS.END_STATUS_END) {
/* 411 */         this.result = (byte)side;
/* 412 */         end();
/* 413 */         if (side == 1) {
/* 414 */           appendDebugStr("\n挑战方胜利\n");
/*     */         } else {
/* 416 */           appendDebugStr("\n挑战方失败\n");
/* 417 */         }  return true;
/* 418 */       }  if (status == IFightSide.END_STATUS.END_STATUS_MAXROUND) {
/* 419 */         this.result = 0;
/* 420 */         end();
/* 421 */         appendDebugStr("\n最大回合数，挑战方失败\n");
/* 422 */         return true;
/* 423 */       }  if (status == IFightSide.END_STATUS.END_STATUS_NEXT) {
/* 424 */         this.curWaveId = (byte)(this.curWaveId + 1);
/* 425 */         this.curRound = 0;
/*     */         
/* 427 */         checkFirstHand();
/* 428 */         appendDebugStr("\n下一波\n");
/*     */       } 
/*     */     } 
/* 431 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getRecord(FightRecordResponse response) {
/* 439 */     response.boutPlayData.addAll(this.totalPlayDatas);
/* 440 */     response.result = this.result;
/* 441 */     for (IFightSide side : this.sides) {
/* 442 */       for (IFighter fighter : side.getCurGroup().getFighters(true)) {
/* 443 */         HpData hpData = new HpData();
/* 444 */         hpData.guid = fighter.getGuid();
/* 445 */         hpData.id = fighter.getId();
/* 446 */         hpData.hp = fighter.getHP();
/* 447 */         response.fightersHp.add(hpData);
/*     */       } 
/*     */     } 
/* 450 */     if (this.isDebug) {
/* 451 */       response.debugStr = getDebugStr();
/* 452 */       LogUtil.debugLog(new Object[] { getDebugStr() });
/*     */     } 
/*     */   }
/*     */   
/*     */   public void appendDebugStr(String string) {
/* 457 */     this.debugBuff.append(string);
/*     */   }
/*     */   
/*     */   public String getDebugStr() {
/* 461 */     return this.debugBuff.toString();
/*     */   }
/*     */   
/*     */   public void resetDebugStr() {
/* 465 */     this.debugBuff.setLength(0);
/*     */   }
/*     */   
/*     */   protected void checkFirstHand() {
/* 469 */     this.firstHand = 0;
/* 470 */     if (getSide((byte)1).getTotalFirstHandVal() > getSide((byte)0).getTotalFirstHandVal())
/* 471 */       this.firstHand = 1; 
/*     */   }
/*     */   
/*     */   protected abstract void end();
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\AbstractFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */