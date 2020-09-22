/*     */ package linlongyx.sanguo.webgame.common.fight.select.target;
/*     */ 
/*     */ import linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import linlongyx.sanguo.webgame.common.fight.IFight;
/*     */ import linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.select.affect.AffectFunc;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.select.range.RANGE_TARGET;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.select.range.RangeSelectable;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Effect;
/*     */ import linlongyx.sanguo.webgame.common.structure.Pair;
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
/*     */ public class TargetSelector
/*     */ {
/*  25 */   private static final Logger LOG = LoggerFactory.getLogger(TargetSelector.class);
/*     */   
/*  27 */   private static final Map<Integer, SingleSelectable> singleSelector = new HashMap<>();
/*     */   
/*  29 */   private static final Map<Integer, RangeSelectable> rangeSelector = new HashMap<>();
/*     */   
/*  31 */   private static final Map<Integer, AffectFunc> affectFuncSelector = new HashMap<>();
/*     */   static {
/*  33 */     for (SINGLE_TARGET single : SINGLE_TARGET.values()) {
/*  34 */       singleSelector.put(Integer.valueOf(single.getType()), single.getTargetSelect());
/*     */     }
/*  36 */     for (RANGE_TARGET single : RANGE_TARGET.values()) {
/*  37 */       rangeSelector.put(Integer.valueOf(single.getType()), single.getRangeSelect());
/*     */     }
/*  39 */     for (AffectFunc func : AffectFunc.values()) {
/*  40 */       affectFuncSelector.put(Integer.valueOf(func.getType()), func);
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
/*  59 */     List<IFighter> fighters = new ArrayList<>();
/*     */     
/*  61 */     if (attacker.isDisorder()) {
/*  62 */       List<IFighter> allFighters = fight.getCurFightersExclude(attacker.getSide(), true, attacker.getGuid());
/*     */       
/*  64 */       byte temp = FightUtil.getTargetSide(attacker, (byte)targetSide);
/*  65 */       allFighters.addAll(fight.getCurFighters(temp, true));
/*  66 */       int index = RandUtil.randNum(0, allFighters.size() - 1);
/*  67 */       effectPlayData.guid = ((IFighter)allFighters.get(index)).getGuid();
/*  68 */       fighters.add(allFighters.get(index));
/*  69 */       return fighters;
/*     */     } 
/*     */     
/*  72 */     SingleSelectable singleSelectable = singleSelector.get(Integer.valueOf(targetType));
/*  73 */     if (null == singleSelectable) {
/*  74 */       LOG.error("玩家选择对象出错： " + attacker + " target : " + targetType);
/*  75 */       return fighters;
/*     */     } 
/*  77 */     IFighter fighter = singleSelectable.target(attacker, (byte)targetSide, fight);
/*  78 */     if (null == fighter) {
/*  79 */       LOG.error("select no target： " + attacker + " target : " + targetType);
/*  80 */       return fighters;
/*     */     } 
/*  82 */     effectPlayData.guid = fighter.getGuid();
/*     */     
/*  84 */     RangeSelectable rangeSelectable = rangeSelector.get(Integer.valueOf(range));
/*  85 */     if (null == rangeSelectable) {
/*  86 */       LOG.error("range slect error： " + attacker + " range : " + range);
/*  87 */       return fighters;
/*     */     } 
/*  89 */     Set<IFighter> tempFighters = new HashSet<>();
/*  90 */     rangeSelectable.target(tempFighters, fighter, fight);
/*  91 */     fighters.addAll(tempFighters);
/*  92 */     return fighters;
/*     */   }
/*     */   
/*     */   public static List<Integer> affectTarget(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData) {
/*  96 */     List<Integer> affectList = new ArrayList<>();
/*  97 */     for (int i = 0; i < effect.getBean().getAffectType().size(); i++) {
/*  98 */       int affectType = ((Integer)effect.getBean().getAffectType().get(i)).intValue();
/*  99 */       if (0 != affectType && affectFuncSelector.containsKey(Integer.valueOf(affectType))) {
/*     */ 
/*     */         
/* 102 */         int ret = ((AffectFunc)affectFuncSelector.get(Integer.valueOf(affectType))).affect(fight, hurtValue, effect, attacker, target, resultPlayData, effect.getBean().getAffectPara().get(i));
/* 103 */         affectList.add(Integer.valueOf(ret));
/*     */       } 
/* 105 */     }  return affectList;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\target\TargetSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */