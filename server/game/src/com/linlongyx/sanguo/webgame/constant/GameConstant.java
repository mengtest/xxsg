/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface GameConstant
/*    */ {
/*    */   public static final int SKILL_INIT_ID = 100100;
/*    */   public static final int ROOTER_INC_INIT_ID = 1000000;
/*    */   public static final int MOUNT_INC_INIT_ID = 100000;
/*    */   public static final int NPC_INC_INIT_ID = 10000;
/*    */   public static final int RAND_BASE = 10000;
/*    */   public static final int RATE_BASE = 10000;
/*    */   public static final double DOUBLE_RATE_BASE = 10000.0D;
/*    */   public static final long LONG_BASE = 10000L;
/*    */   public static final int CHARGE_RATE = 100;
/*    */   public static final int SERVER_STATUS_NORMAL = 0;
/*    */   public static final int SERVER_STATUS_NEW = 1;
/*    */   public static final int SERVER_STATUS_HOT = 2;
/*    */   public static final int SERVER_STATUS_REPAIR = 3;
/*    */   public static final int SERVER_STATUS_DEBUG = 4;
/* 29 */   public static final Set<Integer> GAME_REMAIN_DAYS = new HashSet<Integer>() {
/*    */     
/*    */     };
/*    */   public static final byte PLAYER_STATUS_FORBID_LOGIN = 0;
/*    */   public static final byte PLAYER_STATUS_FORBID_TALK = 1;
/*    */   public static final byte PLAYER_STATUS_ISGM = 4;
/*    */   public static final byte PLAYER_STATUS_ISCHEAT = 6;
/*    */   public static final byte PLAYER_TYPE_UNFORBID_TALK = 2;
/*    */   public static final byte PLAYER_TYPE_UNFORBID_LOGIN = 3;
/*    */   public static final byte PLAYER_TYPE_NOTGM = 5;
/*    */   public static final byte PLAYER_TYPE_NOCHEAT = 7;
/*    */   public static final int SEX_MALE = 1;
/*    */   public static final int SEX_FEMALE = 2;
/*    */   public static final int SEX_YOUNG = 3;
/*    */   public static final byte REWARD_TYPE_RESOURCE = 1;
/*    */   public static final byte REWARD_TYPE_ITEM = 2;
/*    */   public static final byte REWARD_TYPE_DROP = 3;
/*    */   public static final byte REWARD_TYPE_PARTNER = 4;
/*    */   public static final byte REWARD_TYPE_EQUIP = 5;
/*    */   public static final byte REWARD_TYPE_BLESSUP = 6;
/*    */   public static final byte REWARD_TYPE_RUNE = 7;
/*    */   public static final byte REWARD_TYPE_BEST = 8;
/*    */   public static final byte REWARD_TYPE_TIMING = 9;
/*    */   public static final byte RULE_TYPE_LEVEL_UP = 1;
/*    */   public static final byte RULE_TYPE_LEVEL_DOWN = 2;
/*    */   public static final byte RULE_TYPE_GANG = 3;
/*    */   public static final byte RULE_TYPE_PLAYERS = 4;
/*    */   public static final byte RULE_TYPE_STREN_UP = 11;
/*    */   public static final byte RULE_TYPE_STREN_DOWN = 12;
/*    */   public static final byte RULE_TYPE_DAY_UP = 13;
/*    */   public static final byte RULE_TYPE_DAY_DOWN = 14;
/*    */   public static final byte RULE_TYPE_VIP_UP = 15;
/*    */   public static final byte RULE_TYPE_VIP_DOWN = 16;
/*    */   public static final byte RULE_TYPE_CHANNEL = 17;
/*    */   public static final byte MAIL_TYPE_SYS = 1;
/*    */   public static final byte MAIL_TYPE_GUILD = 2;
/*    */   public static final int USE_TYPE_NONE = 0;
/*    */   public static final int USE_TYPE_SINGLE = 1;
/*    */   public static final int USE_TYPE_ANY = 2;
/*    */   public static final int USE_TYPE_PARTNER = 4;
/*    */   public static final int SYS_TO_PLAYER_SENDID = 0;
/*    */   public static final int BOSS_DAMAGE_RANK_SIZE = 10;
/*    */   public static final int HATE_RANK_SIZE = 10;
/*    */   public static final int SKILL_TARGETCHOOSE_GROUP = 2;
/*    */   public static final int EQUIL_BIG_TYPE = 2;
/*    */   public static final int PROP_BIG_TYPE = 1;
/*    */   public static final int PROP_DRAGON_TYPE = 6;
/*    */   public static final int EQUIL_STONE_PARAM_1 = 1;
/*    */   public static final int EQUIL_STONE_PARAM_2 = 2;
/*    */   public static final int BLESS_BIG_TYPE = 5;
/*    */   public static final int BLESS_FLAIR_TYPE = 2;
/*    */   public static final int BLESS_GROWTH_TYPE = 3;
/*    */   public static final int BLESS_BLESSVAL_DAILY_CLEANUP = 1;
/*    */   public static final int SKILL_BELONG_PLAYER = 1;
/*    */   public static final int SKILL_BELONG_MONSTER = 2;
/*    */   public static final int SKILL_BELONG_BOTH = 3;
/*    */   public static final int SKILL_BELONG_PARTNER = 4;
/*    */   public static final int SKILL_TYPE_PASSIVE = 4;
/*    */   public static final String ARENA_WORSHIPS_KEY = "arena_worships";
/*    */   public static final String MAIL_SEND_SYSTEM = "系统邮件";
/*    */   public static final int PART_BASE = 10000;
/*    */   public static final int SKILL_BASE_PARAM = 1000;
/*    */   public static final int BLESS_BASE_PARAM = 100;
/*    */   public static final int BLESS_SKILL_PARAM_FIR = 10000;
/*    */   public static final int BLESS_SKILL_PARAM_SEC = 1000;
/*    */   public static final int BLOC_SKILL_PARAM = 1000;
/*    */   public static final int EXCHANGE_ID_PARAM = 1000;
/*    */   public static final int BLOC_POSITION_LEADER = 1;
/*    */   public static final int BLOC_POSITION_VICE = 2;
/*    */   public static final int BLOC_POSITION_MEMBER = 3;
/*    */   public static final int BLOC_LEAVE_TYPE_ACTIVE = 0;
/*    */   public static final int BLOC_LEAVE_TYPE_PASSIVE = 1;
/*    */   public static final int USERTYPE_CURRENCY = 1;
/*    */   public static final int USERTYPE_ITEM = 2;
/*    */   public static final int USERTYPE_ADDITION = 3;
/*    */   public static final int USERTYPE_GET_PARTNER = 4;
/*    */   public static final int USERTYPE_HE_CHENG = 5;
/*    */   public static final int ITEM_USE_TYPE_RECHARGE = 7;
/*    */   public static final int USERTYPE_MULTI_USE_ITEM = 22;
/*    */   public static final int INSDROP_TYPE_MAIN = 1;
/*    */   public static final int EXCHANGE_TYPE_DALIYRESET = 1;
/*    */   public static final byte ACHIEVEI_TYPE_PUB = 0;
/*    */   public static final byte ACHIEVEI_TYPE_USER = 1;
/*    */   public static final byte ACHIEVEI_TYPE_PRI = 2;
/*    */   public static final byte BLOCBOSS_DAYOPEN_OPENNOT = 0;
/*    */   public static final byte BLOCBOSS_DAYOPEN_OPENING = 1;
/*    */   public static final byte BLOCBOSS_DAYOPEN_OPENED = 2;
/*    */   public static final byte STATUS_RECNOT = 0;
/*    */   public static final byte STATUS_RECING = 1;
/*    */   public static final byte STATUS_RECED = 2;
/*    */   public static final int CARDPLAY_DAILY = 1;
/*    */   public static final int CARDPLAY_ROUND = 2;
/*    */   public static final int CARDPLAY_CUM = 3;
/*    */   public static final int GOLD_EGG_TYPE = 7;
/*    */   public static final int SKIN_FASHION_TYPE = 0;
/*    */   public static final int SKIN_PARTNER_TYPE = 11;
/*    */   public static final int BEST_ENDTYPE_STABLE = 0;
/*    */   public static final int BEST_ENDTYPE_TABLE = 1;
/*    */   public static final int BEST_ENDTYPE_DATE = 2;
/*    */   public static final int BEST_ENDTYPE_EFFECTIVE_DAY = 3;
/*    */   public static final int BEST_ENDTYPE_EFFECTIVE_TIME = 4;
/*    */   public static final int CHARGE_MONTH_NORMAL_CARD = 1;
/*    */   public static final int CHARGE_MONTH_SPECIAL_CARD = 2;
/*    */   public static final int CHARGE_MONTH_WEEK_CARD = 5;
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\GameConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */