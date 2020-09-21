/*     */ package com.linlongyx.sanguo.webgame.common.fight;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.FightGroup;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaRestraintBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.fight.CrossFightRecordResponse;
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
/*     */ 
/*     */ public abstract class AbstractFight
/*     */   implements IFight
/*     */ {
/*     */   protected byte type;
/*  37 */   protected byte result = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   protected IFightSide[] sides = new IFightSide[2];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   protected int curRound = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   protected int totalRound = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   protected byte curWaveId = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   protected byte firstHand = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isZhenfaAffect = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   protected List<BoutPlayData> totalPlayDatas = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   protected StringBuffer debugBuff = new StringBuffer();
/*     */   
/*  82 */   protected boolean isDebug = MContext.getDebug();
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
/*     */   public List<IFighter> getCurFighters(byte side, boolean isAliveOnly) {
/* 101 */     IFightSide fightSide = getSide(side);
/* 102 */     if (null == fightSide)
/* 103 */       return null; 
/* 104 */     return fightSide.getCurGroup().getFighters(isAliveOnly);
/*     */   }
/*     */   
/*     */   public List<IFighter> getCurFightersExclude(byte side, boolean isAliveOnly, byte guid) {
/* 108 */     IFightSide fightSide = getSide(side);
/* 109 */     if (null == fightSide)
/* 110 */       return null; 
/* 111 */     return fightSide.getCurGroup().getFightersExclude(isAliveOnly, guid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initSide(int index, IFightSide fightSide) {
/* 120 */     this.sides[index] = fightSide;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoutPlayData genBoutPlayData() {
/* 130 */     if (this.totalPlayDatas.size() > this.totalRound) {
/* 131 */       return this.totalPlayDatas.get(this.totalRound);
/*     */     }
/* 133 */     BoutPlayData playData = new BoutPlayData();
/*     */     
/* 135 */     playData.waveId = this.curWaveId;
/* 136 */     playData.roundId = this.curRound;
/* 137 */     this.totalPlayDatas.add(playData);
/*     */     
/* 139 */     return playData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getGroupData(CrossFightRecordResponse response) {
/* 147 */     this.sides[0].getGroupData(response.lGroups);
/* 148 */     this.sides[1].getGroupData(response.rGroups);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getCandidateFighterData(CrossFightRecordResponse response) {
/* 156 */     this.sides[0].getCandidateFighterData(response.lGroups);
/* 157 */     this.sides[1].getCandidateFighterData(response.rGroups);
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
/*     */   protected void init() {
/* 171 */     for (byte side = 0; side < this.sides.length; side = (byte)(side + 1)) {
/* 172 */       this.sides[side].initGuid(side);
/*     */     }
/* 174 */     this.curRound = this.totalRound = 0;
/* 175 */     this.curWaveId = 0;
/*     */   }
/*     */   
/*     */   protected void init(byte side) {
/* 179 */     this.sides[side].initGuid(side);
/* 180 */     this.curRound = this.totalRound = 0;
/* 181 */     this.curWaveId = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reset() {
/* 188 */     for (byte side = 0; side < this.sides.length; side = (byte)(side + 1)) {
/* 189 */       this.sides[side] = null;
/*     */     }
/* 191 */     this.curRound = this.totalRound = 0;
/* 192 */     this.curWaveId = 0;
/* 193 */     this.result = 2;
/*     */   }
/*     */   
/*     */   private Map<Integer, List<Integer>> getZhenfaBuffMap(ZhenfaBean.StarBean starBean) {
/* 197 */     Map<Integer, List<Integer>> buffs = new HashMap<>();
/* 198 */     if (!starBean.getBuffOne().isEmpty()) {
/* 199 */       buffs.put(Integer.valueOf(1), starBean.getBuffOne());
/*     */     }
/* 201 */     if (!starBean.getBuffTwo().isEmpty()) {
/* 202 */       buffs.put(Integer.valueOf(2), starBean.getBuffTwo());
/*     */     }
/* 204 */     if (!starBean.getBuffThree().isEmpty()) {
/* 205 */       buffs.put(Integer.valueOf(3), starBean.getBuffThree());
/*     */     }
/* 207 */     if (!starBean.getBuffFour().isEmpty()) {
/* 208 */       buffs.put(Integer.valueOf(4), starBean.getBuffFour());
/*     */     }
/* 210 */     if (!starBean.getBuffFive().isEmpty()) {
/* 211 */       buffs.put(Integer.valueOf(5), starBean.getBuffFive());
/*     */     }
/* 213 */     if (!starBean.getBuffSix().isEmpty()) {
/* 214 */       buffs.put(Integer.valueOf(6), starBean.getBuffSix());
/*     */     }
/* 216 */     return buffs;
/*     */   }
/*     */   
/*     */   private void addZhenfaBuff(IFightSide side) {
/* 220 */     if (side.getZhenfa() != null && ((Integer)side.getZhenfa().getKey()).intValue() > 0) {
/* 221 */       ZhenfaBean zhenfaBean = (ZhenfaBean)JsonTableService.getJsonData(((Integer)side.getZhenfa().getKey()).intValue(), ZhenfaBean.class);
/* 222 */       if (zhenfaBean != null && zhenfaBean.getStar().containsKey(side.getZhenfa().getValue())) {
/* 223 */         ZhenfaBean.StarBean starBean = (ZhenfaBean.StarBean)zhenfaBean.getStar().get(side.getZhenfa().getValue());
/* 224 */         Map<Integer, List<Integer>> buffs = getZhenfaBuffMap(starBean);
/* 225 */         side.getGroupList().forEach(group -> group.getFighters(true).forEach(()));
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
/* 239 */     ZhenfaRestraintBean zhenfaRestraintBean = (ZhenfaRestraintBean)JsonTableService.getJsonData(((Integer)side1.getZhenfa().getKey()).intValue(), ZhenfaRestraintBean.class);
/* 240 */     for (ZhenfaRestraintBean.BuffBean bean : zhenfaRestraintBean.getBuff()) {
/* 241 */       if (bean.getZhenfa() == ((Integer)sdie2.getZhenfa().getKey()).intValue()) {
/* 242 */         side1.getGroupList().forEach(group -> group.getFighters(true).forEach(()));
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
/*     */   
/*     */   private void checkZhenfaBuff() {
/* 255 */     if (this.isZhenfaAffect)
/*     */       return; 
/* 257 */     this.isZhenfaAffect = true;
/*     */     
/* 259 */     IFightSide leftSide = getSide((byte)(this.firstHand % 2));
/* 260 */     IFightSide rightSide = getSide((byte)((this.firstHand + 1) % 2));
/*     */ 
/*     */     
/* 263 */     addZhenfaBuff(leftSide);
/* 264 */     addZhenfaBuff(rightSide);
/*     */ 
/*     */     
/* 267 */     if (leftSide.getZhenfa() != null && rightSide.getZhenfa() != null) {
/* 268 */       zhenfaRestraint(leftSide, rightSide);
/* 269 */       zhenfaRestraint(rightSide, leftSide);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void action() {
/* 277 */     resetDebugStr();
/* 278 */     checkFirstHand();
/* 279 */     checkZhenfaBuff();
/*     */     
/* 281 */     while (!checkEnd()) {
/*     */ 
/*     */ 
/*     */       
/* 285 */       this.debugBuff.append("第").append(this.curRound + 1).append("回合：\n");
/* 286 */       roundFight();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void petFighterAuto(IFightSide side) {
/* 291 */     if (!side.getCurGroup().getFighters(true).isEmpty() && side.getPetFighter() != null) {
/* 292 */       side.getPetFighter().auto(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void stageFighterAuto(IFightSide side) {
/* 298 */     if (side.getStageFighter() != null) {
/* 299 */       side.getStageFighter().auto(this);
/* 300 */       side.setStageFighter(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkCandidate(IFightSide side) {
/* 309 */     if (side instanceof com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide) {
/* 310 */       for (IFighter fighter : side.getCurGroup().getDeadFighters()) {
/* 311 */         if (fighter.isDead()) {
/* 312 */           IFighter newFighter = side.getCandidateFighter();
/* 313 */           if (newFighter == null)
/* 314 */             break;  newFighter.replace(this, fighter);
/* 315 */           side.getCurGroup().getCandidateFighterList().add(newFighter);
/*     */         } 
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
/* 329 */     IFightSide side1 = getSide((byte)(this.firstHand % 2));
/* 330 */     IFightSide side2 = getSide((byte)((this.firstHand + 1) % 2));
/*     */ 
/*     */     
/* 333 */     stageFighterAuto(side1);
/* 334 */     stageFighterAuto(side2);
/* 335 */     if (checkEnd()) {
/*     */       return;
/*     */     }
/*     */     
/* 339 */     for (byte i = 0; i < 2; i = (byte)(i + 1)) {
/* 340 */       getSide(i).getCurGroup().roundStart(this);
/* 341 */       checkCandidate(getSide(i));
/* 342 */       if (checkEnd()) {
/*     */         return;
/*     */       }
/*     */     } 
/* 346 */     for (byte pos = 1; pos <= 6; pos = (byte)(pos + 1)) {
/* 347 */       IFighter f1 = side1.getCurGroup().getFighterByPos(pos);
/* 348 */       if (f1 != null && f1.canFight()) {
/* 349 */         f1.auto(this);
/*     */       }
/* 351 */       checkCandidate(side2);
/* 352 */       if (checkEnd()) {
/*     */         return;
/*     */       }
/* 355 */       IFighter f2 = side2.getCurGroup().getFighterByPos(pos);
/* 356 */       if (f2 != null && f2.canFight()) {
/* 357 */         f2.auto(this);
/*     */       }
/* 359 */       checkCandidate(side1);
/* 360 */       if (checkEnd()) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 365 */     petFighterAuto(side1);
/* 366 */     checkCandidate(side2);
/* 367 */     if (checkEnd()) {
/*     */       return;
/*     */     }
/* 370 */     petFighterAuto(side2);
/* 371 */     checkCandidate(side1);
/* 372 */     if (checkEnd())
/*     */       return; 
/* 374 */     this.curRound++;
/* 375 */     this.totalRound++;
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
/*     */   protected boolean checkEnd() {
/* 388 */     for (int side = 0; side < this.sides.length; side++) {
/* 389 */       IFightSide.END_STATUS status = this.sides[side].checkEndStatus(this.curRound);
/* 390 */       if (status == IFightSide.END_STATUS.END_STATUS_END) {
/* 391 */         this.result = (byte)side;
/* 392 */         end();
/* 393 */         if (side == 1) {
/* 394 */           appendDebugStr("\n挑战方胜利\n");
/*     */         } else {
/* 396 */           appendDebugStr("\n挑战方失败\n");
/* 397 */         }  return true;
/*     */       } 
/* 399 */       if (status == IFightSide.END_STATUS.END_STATUS_MAXROUND) {
/* 400 */         this.result = 0;
/* 401 */         end();
/* 402 */         appendDebugStr("\n最大回合数，挑战方失败\n");
/* 403 */         return true;
/*     */       } 
/* 405 */       if (status == IFightSide.END_STATUS.END_STATUS_NEXT) {
/* 406 */         this.curWaveId = (byte)(this.curWaveId + 1);
/* 407 */         this.curRound = 0;
/*     */         
/* 409 */         checkFirstHand();
/* 410 */         appendDebugStr("\n下一波\n");
/*     */       } 
/*     */     } 
/* 413 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getRecord(FightRecordResponse response) {
/* 421 */     response.boutPlayData.addAll(this.totalPlayDatas);
/* 422 */     response.result = this.result;
/* 423 */     for (IFightSide side : this.sides) {
/* 424 */       for (IFighter fighter : side.getCurGroup().getFighters(true)) {
/* 425 */         HpData hpData = new HpData();
/* 426 */         hpData.guid = fighter.getGuid();
/* 427 */         hpData.id = fighter.getId();
/* 428 */         hpData.hp = fighter.getHP();
/* 429 */         response.fightersHp.add(hpData);
/*     */       } 
/*     */     } 
/* 432 */     if (this.isDebug) {
/* 433 */       response.debugStr = getDebugStr();
/* 434 */       LogUtil.debugLog(new Object[] { getDebugStr() });
/*     */     } 
/*     */   }
/*     */   
/*     */   public void getCrossRecord(CrossFightRecordResponse response) {
/* 439 */     response.boutPlayData.addAll(this.totalPlayDatas);
/* 440 */     response.result = this.result;
/* 441 */     for (IFightSide side : this.sides) {
/* 442 */       for (FightGroup fightGroup : side.getGroupList()) {
/* 443 */         for (IFighter fighter : fightGroup.getFighters(true)) {
/* 444 */           HpData hpData = new HpData();
/* 445 */           hpData.guid = fighter.getGuid();
/* 446 */           hpData.id = fighter.getId();
/* 447 */           hpData.hp = fighter.getHP();
/* 448 */           response.fightersHp.add(hpData);
/*     */         } 
/*     */       } 
/*     */     } 
/* 452 */     if (this.isDebug) {
/* 453 */       response.debugStr = getDebugStr();
/* 454 */       LogUtil.debugLog(new Object[] { getDebugStr() });
/*     */     } 
/*     */   }
/*     */   
/*     */   public void appendDebugStr(String string) {
/* 459 */     this.debugBuff.append(string);
/*     */   }
/*     */   
/*     */   public String getDebugStr() {
/* 463 */     return this.debugBuff.toString();
/*     */   }
/*     */   
/*     */   public void resetDebugStr() {
/* 467 */     this.debugBuff.setLength(0);
/*     */   }
/*     */   
/*     */   protected void checkFirstHand() {
/* 471 */     this.firstHand = 0;
/* 472 */     if (getSide((byte)1).getTotalFirstHandVal() > getSide((byte)0).getTotalFirstHandVal())
/* 473 */       this.firstHand = 1; 
/*     */   }
/*     */   
/*     */   protected abstract void end();
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\AbstractFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */