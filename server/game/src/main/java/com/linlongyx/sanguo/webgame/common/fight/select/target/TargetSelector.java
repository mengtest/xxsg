/*     */ package com.linlongyx.sanguo.webgame.common.fight.select.target;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.select.affect.AffectFunc;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.select.range.RANGE_TARGET;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.select.range.RangeSelectable;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Effect;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EffectPlayData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ResultPlayData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ public class TargetSelector
/*     */ {
/*  26 */   private static final Logger LOG = LoggerFactory.getLogger(TargetSelector.class);
/*     */   
/*  28 */   private static final Map<Integer, SingleSelectable> singleSelector = new HashMap<>();
/*     */   
/*  30 */   private static final Map<Integer, RangeSelectable> rangeSelector = new HashMap<>();
/*     */   
/*  32 */   private static final Map<Integer, AffectFunc> affectFuncSelector = new HashMap<>();
/*     */   static {
/*  34 */     for (SINGLE_TARGET single : SINGLE_TARGET.values()) {
/*  35 */       singleSelector.put(Integer.valueOf(single.getType()), single.getTargetSelect());
/*     */     }
/*  37 */     for (RANGE_TARGET single : RANGE_TARGET.values()) {
/*  38 */       rangeSelector.put(Integer.valueOf(single.getType()), single.getRangeSelect());
/*     */     }
/*  40 */     for (AffectFunc func : AffectFunc.values()) {
/*  41 */       affectFuncSelector.put(Integer.valueOf(func.getType()), func);
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
/*     */   public static List<IFighter> selectTarget(IFighter attacker, int targetSide, int targetType, int range, IFight fight, EffectPlayData effectPlayData) {
/*  60 */     List<IFighter> fighters = new ArrayList<>();
/*     */     
/*  62 */     if (attacker.isDisorder()) {
/*  63 */       List<IFighter> allFighters = fight.getCurFightersExclude(attacker.getSide(), true, attacker.getGuid());
/*     */       
/*  65 */       byte temp = FightUtil.getTargetSide(attacker, (byte)targetSide);
/*  66 */       allFighters.addAll(fight.getCurFighters(temp, true));
/*  67 */       int index = RandUtil.randNum(0, allFighters.size() - 1);
/*  68 */       effectPlayData.guid = ((IFighter)allFighters.get(index)).getGuid();
/*  69 */       fighters.add(allFighters.get(index));
/*  70 */       return fighters;
/*     */     } 
/*     */     
/*  73 */     SingleSelectable singleSelectable = singleSelector.get(Integer.valueOf(targetType));
/*  74 */     if (null == singleSelectable) {
/*  75 */       LOG.error("玩家选择对象出错： " + attacker + " target : " + targetType);
/*  76 */       return fighters;
/*     */     } 
/*  78 */     IFighter fighter = singleSelectable.target(attacker, (byte)targetSide, fight);
/*  79 */     if (null == fighter) {
/*  80 */       LOG.error("select no target： " + attacker + " target : " + targetType);
/*  81 */       return fighters;
/*     */     } 
/*  83 */     effectPlayData.guid = fighter.getGuid();
/*     */     
/*  85 */     RangeSelectable rangeSelectable = rangeSelector.get(Integer.valueOf(range));
/*  86 */     if (null == rangeSelectable) {
/*  87 */       LOG.error("range slect error： " + attacker + " range : " + range);
/*  88 */       return fighters;
/*     */     } 
/*  90 */     Set<IFighter> tempFighters = new HashSet<>();
/*  91 */     rangeSelectable.target(tempFighters, fighter, fight);
/*  92 */     fighters.addAll(tempFighters);
/*  93 */     return fighters;
/*     */   }
/*     */   
/*     */   public static List<Integer> affectTarget(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData) {
/*  97 */     List<Integer> affectList = new ArrayList<>();
/*  98 */     for (int i = 0; i < effect.getBean().getAffectType().size(); i++) {
/*  99 */       int affectType = ((Integer)effect.getBean().getAffectType().get(i)).intValue();
/* 100 */       if (0 != affectType && affectFuncSelector.containsKey(Integer.valueOf(affectType))) {
/*     */ 
/*     */         
/* 103 */         int ret = ((AffectFunc)affectFuncSelector.get(Integer.valueOf(affectType))).affect(fight, hurtValue, effect, attacker, target, resultPlayData, effect.getBean().getAffectPara().get(i));
/* 104 */         affectList.add(Integer.valueOf(ret));
/*     */       } 
/* 106 */     }  return affectList;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\target\TargetSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */