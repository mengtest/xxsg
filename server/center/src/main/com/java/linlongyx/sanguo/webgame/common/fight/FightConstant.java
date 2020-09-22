/*     */ package linlongyx.sanguo.webgame.common.fight;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.buff.BuffType;
/*     */ import linlongyx.sanguo.webgame.config.bean.AttrNameBean;
/*     */ import linlongyx.sanguo.webgame.config.parameter.FightParameter;
/*     */ import linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
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
/*     */ public class FightConstant
/*     */ {
/*     */   public static final byte HURT_TYPE_PHY = 1;
/*     */   public static final byte HURT_TYPE_DEF = 2;
/*     */   public static final byte MAX_ROUND_LIMIT = 50;
/*     */   public static final byte SKILL_TYPE_CMM = 1;
/*     */   public static final byte SKILL_TYPE_FURY = 2;
/*     */   public static final byte SKILL_TYPE_FIT = 3;
/*     */   public static final byte SKILL_TYPE_PET = 5;
/*     */   public static final byte SKILL_TYPE_STAGE = 6;
/*     */   public static final byte FIGHT_RESULT_OPPOSITE_WIN = 0;
/*     */   public static final byte FIGHT_RESULT_SELF_WIN = 1;
/*     */   public static final byte FIGHT_RESULT_DRAW = 2;
/*     */   public static final byte MAIN_INS_FIGHT = 1;
/*     */   public static final byte NORMAL_FIGHT = 2;
/*     */   public static final byte RANK_BOSS_FIGHT = 3;
/*     */   public static final byte ARENA_FIGHT = 4;
/*     */   public static final byte UNPARALLELED_FIGHT = 5;
/*     */   public static final byte WORLD_FIGHT = 6;
/*     */   public static final byte GENERAL_FIGHT = 7;
/*     */   public static final byte SINGLE_BOSS = 8;
/*     */   public static final byte TOWER_FIGNT = 9;
/*     */   public static final byte TEAM_FIGHT = 10;
/*     */   public static final byte DESTINY_FIGHT = 11;
/*     */   public static final byte MATCH_FIGHT = 12;
/*     */   public static final byte BAGUA_FIGHT = 13;
/*     */   public static final byte GROUP_FIGHT = 14;
/*     */   public static final byte BATTLE_FIGHT = 50;
/*     */   public static final byte FIGHTER_TYPE_PLAYER = 0;
/*     */   public static final byte FIGHTER_TYPE_PARTNER = 1;
/*     */   public static final byte FIGHTER_TYPE_MONSTER = 2;
/*     */   public static final byte FIGHTER_TYPE_PET = 3;
/*     */   public static final byte FIGHTER_TYPE_STAGE = 4;
/*     */   public static final byte FIGHTER_TYPE_CANDIDATE = 5;
/*     */   public static final byte PET_FIGHTER_POS = 0;
/*     */   public static final byte PLAYER_FIGHTER_POS = 1;
/*     */   public static final byte STAGE_FIGHTER_POS = 10;
/*     */   public static final byte PET_FIGHTER_OPPOSITE_POS = 100;
/*     */   public static final byte STAGE_FIGHTER_OPPOSITE_POS = 110;
/*     */   public static final byte POS_SIDE_PARTITION = 6;
/*     */   public static final byte HALF_POS_SIDE_PARTITION = 3;
/*     */   public static final byte GUID_SIDE_PARTITION = 100;
/*     */   public static final int FIGHT_SIDE_NUM = 2;
/* 100 */   byte SKILL_INDEX_1 = 1;
/* 101 */   byte SKILL_INDEX_2 = 2;
/*     */   
/*     */   public static final byte FIGHTER_ACTION_CRIT = 1;
/*     */   
/*     */   public static final byte FIGHTER_ACTION_DODGE = 2;
/*     */   
/*     */   public static final int BUFF_ADD_TIME_BEFORE = 0;
/*     */   
/*     */   public static final int BUFF_ADD_TIME_AFTER = 1;
/*     */   
/*     */   public static final int ATOM_TYPE_AFFECT_BUFF = 0;
/*     */   
/*     */   public static final int ATOM_TYPE_ADD_BUFF = 1;
/*     */   
/*     */   public static final int ATOM_TYPE_DEC_BUFF = 2;
/*     */   
/*     */   public static final int ATOM_TYPE_RECOVER_HP = 3;
/*     */   
/*     */   public static final int ATOM_TYPE_SUCK_HP = 4;
/*     */   
/*     */   public static final int ATOM_TYPE_SUCK_FURY = 5;
/*     */   
/*     */   public static final int ATOM_TYPE_ADD_FURY = 6;
/*     */   
/*     */   public static final int ATOM_TYPE_DEL_FURY = 7;
/*     */   
/*     */   public static final int ATOM_TYPE_EFFECT_ADD = 8;
/*     */   
/*     */   public static final int ATOM_TYPE_EFFECT_DEL = 9;
/*     */   
/*     */   public static final int ATOM_POSION_BUFF = 10;
/*     */   
/*     */   public static final int ATOM_BURN_BUFF = 11;
/*     */   
/*     */   public static final int BUFF_TYPE_BUFF = 0;
/*     */   
/*     */   public static final int BUFF_TYPE_DEBUFF = 1;
/*     */   
/*     */   public static final int BUFF_EFFECT_BEFORE_ROUND = 0;
/*     */   
/*     */   public static final int BUFF_EFFECT_AFTER_ROUND = 1;
/*     */   
/*     */   public static final int HURT_TYPE_NON = 0;
/*     */   
/*     */   public static final int HURT_TYPE_PHY_ATK = 1;
/*     */   
/*     */   public static final int HURT_TYPE_MAGIC_ATK = 2;
/*     */   
/*     */   public static final int HURT_TYPE_HP = 3;
/*     */   
/*     */   public static final int HURT_TYPE_FIXED = 4;
/*     */   
/*     */   public static final int HURT_TYPE_HP_FIXED = 5;
/*     */   
/*     */   public static final int HURT_TYPE_KILL_MUST = 6;
/*     */   
/*     */   public static final int AFFECT_TYPE_RECOVER_HP_1 = 101;
/*     */   
/*     */   public static final int AFFECT_TYPE_RECOVER_HP_2 = 102;
/*     */   
/*     */   public static final int AFFECT_TYPE_RECOVER_HP_3 = 103;
/*     */   
/*     */   public static final int AFFECT_TYPE_SUCK_FURY = 201;
/*     */   
/*     */   public static final int AFFECT_TYPE_ADD_FURY = 202;
/*     */   
/*     */   public static final int AFFECT_TYPE_DEL_FURY = 203;
/*     */   
/*     */   public static final int AFFECT_TYPE_CLEAR_FURY = 204;
/*     */   
/*     */   public static final int AFFECT_TYPE_SUCK_HP_1 = 301;
/*     */   
/*     */   public static final int AFFECT_TYPE_SUCK_HP_2 = 302;
/*     */   
/*     */   public static final int AFFECT_TYPE_SUCK_HP_3 = 303;
/*     */   
/*     */   public static final int AFFECT_TYPE_DEC_BUFF = 401;
/*     */   public static final int AFFECT_TYPE_DEC_DEBUF = 402;
/*     */   public static final int AFFECT_TYPE_ADD_DEBUF = 403;
/*     */   public static final int AFFECT_TYPE_HURT_ADD = 501;
/*     */   public static final int AFFECT_TYPE_BURN_HURT = 601;
/*     */   public static final int AFFECT_TYPE_BURN_BUFF = 602;
/*     */   public static final int AFFECT_TYPE_BURN_CRIT = 603;
/* 184 */   public static final Set<Integer> SEAL_BUFF_TYPE = new HashSet<>(Collections.singletonList(Integer.valueOf(BuffType.DIZZY.getType())));
/*     */   
/* 186 */   public static final Set<Integer> SILENT_BUFF_TYPE = new HashSet<>(Arrays.asList(new Integer[] { Integer.valueOf(BuffType.SILENT.getType()), Integer.valueOf(BuffType.DISORDER.getType()) }));
/*     */   
/* 188 */   private static final FightParameter fightParamter = (FightParameter)ParameterConstant.getParameter(1);
/*     */ 
/*     */   
/*     */   public static double getHitBaseValue() {
/* 192 */     return fightParamter.getHitBaseValue();
/*     */   }
/*     */   
/*     */   public static double getCritBaseValue() {
/* 196 */     return fightParamter.getCritBaseValue();
/*     */   }
/*     */   
/*     */   public static double getCritHurtBaseValue() {
/* 200 */     return fightParamter.getCritHurtBaseValue();
/*     */   }
/*     */   
/*     */   public static double getHitRange() {
/* 204 */     return fightParamter.getHitRange();
/*     */   }
/*     */   
/*     */   public static double getCriticalRange() {
/* 208 */     return fightParamter.getCriticalRange();
/*     */   }
/*     */   
/*     */   public static double getMinHurtRate() {
/* 212 */     return fightParamter.getMinHurtRate();
/*     */   }
/*     */   
/*     */   public static double getMinDogeRate() {
/* 216 */     return fightParamter.getMinDogeRate();
/*     */   }
/*     */   
/* 219 */   private static long[] weights = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/*     */   
/*     */   public static final int HURT_DEFAULT_KEY = 2147483647;
/*     */   
/*     */   public static final int HIT_HURT_VALUE = -1;
/*     */   public static final int ZERO_HURT_VALUE = -2;
/*     */   
/*     */   public static void reload() {
/* 227 */     for (int i = 1; i < weights.length; i++) {
/* 228 */       AttrNameBean attrNameBean = (AttrNameBean)JsonTableService.getJsonData(i, AttrNameBean.class);
/* 229 */       if (null != attrNameBean) {
/* 230 */         weights[attrNameBean.getId()] = attrNameBean.getPower();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long[] getWeights() {
/* 240 */     return weights;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getWeight(int type) {
/* 249 */     if (type >= weights.length)
/* 250 */       return 0L; 
/* 251 */     return weights[type];
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\FightConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */