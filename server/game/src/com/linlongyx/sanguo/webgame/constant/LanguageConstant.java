/*     */ package com.linlongyx.sanguo.webgame.constant;
/*     */ 
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LanguageBean;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Arrays;
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
/*     */ public class LanguageConstant
/*     */ {
/*     */   public static final int TYPE_VIP = 1;
/*     */   public static final int TYPE_BOSS_OPEN = 2;
/*     */   public static final int TYPE_GET_PET = 3;
/*     */   public static final int TYPE_GET_MOUNTS = 4;
/*     */   public static final int TYPE_PLAYER_LEVEL_FULL = 5;
/*     */   public static final int TYPE_PARTNER_LEVEL_FULL = 6;
/*     */   public static final int TYPE_PARTNER_STAR_FULL = 7;
/*     */   public static final int TYPE_ARENA_BEFORE_THIRE = 8;
/*     */   public static final int TYPE_BUY_GROWTH_FUND = 9;
/*     */   public static final int TYPE_CHARGE_REBATE = 10;
/*     */   public static final int TYPE_PARTNER_STAR_NOT_FULL = 11;
/*     */   public static final int TYPE_ZHEN_FA_LOTTERY = 12;
/*     */   public static final int TYPE_ALIEN_RACE_BOSS = 13;
/*     */   public static final int TYPE_TURNPLATE = 24;
/*     */   public static final int WELCOME_TITLE = 103;
/*     */   public static final int WELCOME_CONTENT = 104;
/*     */   public static final int MOUNTS_FIXED_TITLE = 105;
/*     */   public static final int MOUNTS_FIXED_CONTENT = 106;
/*     */   public static final int BUY_GROWTH_FUND = 109;
/*     */   public static final int PLATFORM_GOLLOW_TITLE = 110;
/*     */   public static final int PLATFORM_GOLLOW_CONTENT = 111;
/*     */   public static final int PLATFORM_CHARGE_RABATE_TITLE = 112;
/*     */   public static final int PLATFORM_CHARGE_RABATE_CONTENT = 113;
/*     */   public static final int PLATFORM_ARTIFACT_TITLE = 114;
/*     */   public static final int PLATFORM_ARTIFACT_CONTENT = 115;
/*     */   public static final int RECRUIT_ORANGE = 201;
/*     */   public static final int RECRUIT_RED = 202;
/*     */   public static final int RECRUIT_GOLD = 203;
/*     */   public static final int RECRUIT_BACK_GOLD = 204;
/*     */   public static final int VIP_NOTICE = 701;
/*     */   public static final int WORLDBOSS_START_NOTICE = 702;
/*     */   public static final int RED_PET_NOTICE = 704;
/*     */   public static final int ORANGE_PET_NOTICE = 703;
/*     */   public static final int JIN_PET_NOTICE = 705;
/*     */   public static final int ORANGE_MOUNTS_NOTICE = 706;
/*     */   public static final int RED_MOUNTS_NOTICE = 707;
/*     */   public static final int JIN_MOUNTS_NOTICE = 708;
/*     */   public static final int FULL_LEVEL_NOTICE = 709;
/*     */   public static final int PURPLE_PARTNER_FULL_NOTICE = 710;
/*     */   public static final int ORANGE_PARTNER_FULL_NOTICE = 711;
/*     */   public static final int RED_PARTNER_FULL_NOTICE = 712;
/*     */   public static final int JIN_PARTNER_FULL_NOTICE = 713;
/*     */   public static final int PURPLE_PARTNER_STAR_NOTICE = 714;
/*     */   public static final int ORANGE_PARTNER_STAR_NOTICE = 715;
/*     */   public static final int RED_PARTNER_STAR_NOTICE = 716;
/*     */   public static final int JIN_PARTNER_STAR_NOTICE = 717;
/*     */   public static final int ARENA_BEFORE_THIRE = 718;
/*     */   public static final int RED_PARTNER_UP_STAR_NOTICE = 719;
/*     */   public static final int JIN_PARTNER_UP_STAR_NOTICE = 720;
/*     */   public static final int BACKJIN_PARTNER_UP_STAR_NOTICE = 721;
/*     */   public static final int BACKJIN_PARTNER_STAR_NOTICE = 722;
/*     */   public static final int BACKJIN_PARTNER_FULL_NOTICE = 723;
/*     */   public static final int CHARGE_REBATE_NOTICE = 801;
/*     */   public static final int RANK_BOSS_HURT_TITLE = 301;
/*     */   public static final int RANK_BOSS_HURT_CONTENT = 302;
/*     */   public static final int WORLD_BOSS_HURT_TITLE = 303;
/*     */   public static final int WORLD_BOSS_HURT_CONTENT = 304;
/*     */   public static final int RANK_BOSS_KILL_TITLE = 305;
/*     */   public static final int RANK_BOSS_KILL_CONTENT = 306;
/*     */   public static final int ALIEN_RACE_BOSS_TITLE = 307;
/*     */   public static final int ALIEN_RACE_BOSS_CONTENT = 308;
/*     */   public static final int ALIEN_RACE_BOSS_REBORN_NOTICE = 309;
/*     */   public static final int BACKEND_NOTICE = 603;
/*     */   public static final int CHANGENAME_SEND_FRIEND_TITLE = 901;
/*     */   public static final int CHANGENAME_SEND_FRIEND_CONTENT = 902;
/*     */   public static final int GROUP_MILITAY_HELP_NOTICE = 903;
/*     */   public static final int GROUP_RECRUIT_NOTICE = 904;
/*     */   public static final int GROUP_LEADER_AUTO_CHANGE_TITLE = 1101;
/*     */   public static final int GROUP_LEADER_AUTO_CHANGE_CONTENT = 1102;
/*     */   public static final int GROUP_LEADER_CHANGE_CONTENT = 1104;
/*     */   public static final int GROUP_LEADER_CHANGE_TITLE = 1103;
/*     */   public static final int GROUP_MEMBER_OUT_TITLE = 1105;
/*     */   public static final int GROUP_MEMBER_OUT_CONTENT = 1106;
/*     */   public static final int RANK_ACT_LIST_TITLE = 1201;
/*     */   public static final int RANK_ACT_LIST_CONTENT = 1202;
/*     */   public static final int RANK_ACT_LOSE_TITLE = 1203;
/*     */   public static final int RANK_ACT_LOSE_CONTENT = 1204;
/*     */   public static final int RANK_ACT_PLAY_TITLE = 1205;
/*     */   public static final int RANK_ACT_PLAY_CONTENT = 1206;
/*     */   public static final int MAIL_TITLE_ARENA_ACCOUNT = 1701;
/*     */   public static final int MAIL_CONTENT_ARENA_ACCOUNT = 1702;
/*     */   public static final int MAIL_TITLE_ARENA_RANK_CHANGE = 1703;
/*     */   public static final int MAIL_CONTENT_ARENA_RANK_CHANGE = 1704;
/*     */   public static final int MONTH_CARD_NOTICE1 = 1901;
/*     */   public static final int MONTH_CARD_NOTICE2 = 1902;
/*     */   public static final int MONTH_CARD_NOTICE3 = 1903;
/*     */   public static final int CONTINUITY_TITLE = 2101;
/*     */   public static final int CONTINUITY_CONTENT = 2102;
/*     */   public static final int RECHARGE_ACTIVITY_TITLE = 2301;
/*     */   public static final int RECHARGE_ACTIVITY_CONTENT = 2302;
/*     */   public static final int TYPE_TURNPLATE_NOTICE = 2401;
/*     */   public static final int TOWER_TITLE = 4001;
/*     */   public static final int TOWER_CONTENT = 4002;
/*     */   public static final int TEAM_CREATE_NOTICE = 3701;
/*     */   public static final int TEAM_NOTICE = 3702;
/*     */   public static final int TEAM_BAGUA_CREATE_NOTICE = 3703;
/*     */   public static final int TITLE_VALID_TITLE = 3801;
/*     */   public static final int TITLE_VALID_CONTENT = 3802;
/*     */   public static final int ONEYUAN_TITLE = 4101;
/*     */   public static final int ONEYUAN_CONTENT = 4102;
/*     */   public static final int RUNE_WAR_RANK_TITLE = 4501;
/*     */   public static final int RUNE_WAR_RANK_CONTENT = 4502;
/*     */   public static final int DRAW_CARD_ANNOUNCE = 4801;
/*     */   public static final int CARDBOOK_ASK_REWARD_TITLE = 5001;
/*     */   public static final int CARDBOOK_ASK_REWARD_CONTENT = 5002;
/*     */   public static final int CARDBOOK_GIVE_REWARD_TITLE = 5003;
/*     */   public static final int CARDBOOK_GIVE_REWARD_CONTEN = 5004;
/*     */   public static final int CARDBOOK_BOTTOM_CARD = 5005;
/*     */   public static final int CARDBOOK_COLOR_DAN = 5006;
/*     */   public static final int REDUNDANT_CARD_REWARD_TITLE = 5509;
/*     */   public static final int REDUNDANT_CARD_REWARD_CONTENT = 5510;
/*     */   public static final int DESTINY_RANK_WEEK_TITLE = 7401;
/*     */   public static final int DESTINY_RANK_WEEK_CONTENT = 7402;
/*     */   public static final int DESTINY_MATCH_BET_TITLE = 7403;
/*     */   public static final int DESTINY_MATCH_BET_CONTENT = 7404;
/*     */   public static final int DESTINY_MATCH_RANK_TITLE = 7405;
/*     */   public static final int DESTINY_MATCH_RANK_CONTENT = 7406;
/*     */   public static final int DESTINY_MATCH_PREPARE_TITLE = 7407;
/*     */   public static final int DESTINY_MATCH_PREPARE_CONTENT = 7408;
/*     */   public static final int EVERY_DAY_CHARGE_TITLE = 7501;
/*     */   public static final int EVERY_DAY_CHARGE_CONTENT = 7502;
/*     */   public static final int DAILY_SIGN_TITLE = 7601;
/*     */   public static final int DAILY_SIGN_CONTEN = 7602;
/*     */   public static final int WEEK_SIGN_TITLE = 7603;
/*     */   public static final int WEEK_SIGN_CONTENT = 7604;
/*     */   public static final int TURNTABLE_RANK_TITLE = 5101;
/*     */   public static final int TURNTABLE_RANK_CONTENT = 5102;
/*     */   public static final int DIVINE_RANK_TITLE = 5701;
/*     */   public static final int DIVINE_RANK_CONTENT = 5702;
/*     */   public static final int DIVINE_NOTICE = 5703;
/*     */   public static final int ZHEN_FA_LOTTERY_NOTICE = 5801;
/*     */   public static final int MENTAL_MAIL_TITLE = 5802;
/*     */   public static final int MENTAL_MAIL_CONTENT = 5803;
/*     */   public static final int TOWER_OWNER_RANK_TITLE = 6701;
/*     */   public static final int TOWER_OWNER_RANK_CONTENT = 6702;
/*     */   public static final int CROSS_BATTLE_PREPARE = 8102;
/*     */   public static final int CROSS_BATTLE_OPEN = 8103;
/*     */   public static final int CROSS_BATTLE_POINT_RANK_TITLE = 8106;
/*     */   public static final int CROSS_BATTLE_POINT_RANK_CONTENT = 8107;
/*     */   public static final int CROSS_BATTLE_CAMP_RANK_TITLE = 8108;
/*     */   public static final int CROSS_BATTLE_CAMP_RANK_CONTENT = 8109;
/*     */   public static final int CROSS_RANK_ACT_LIST_TITLE = 5901;
/*     */   public static final int CROSS_RANK_ACT_LIST_CONTENT = 5902;
/*     */   public static final int CROSS_RANK_ACT_LOSE_TITLE = 5903;
/*     */   public static final int CROSS_RANK_ACT_LOSE_CONTENT = 5904;
/*     */   public static final int DIVINE_LUCKLY_TITLE = 5601;
/*     */   public static final int DIVINE_LUCKLY_CONTENT = 5602;
/*     */   public static final int CROSS_RACE_RANK_TITLE = 2801;
/*     */   public static final int CROSS_RACE_RANK_CONTENT = 2802;
/*     */   public static final int CROSS_RACE_JOIN_REWARD_TITLE = 2803;
/*     */   public static final int CROSS_RACE_JOIN_REWARD_CONTENT = 2804;
/*     */   public static final int CROSS_RACE_OPEN = 2805;
/*     */   public static final int TYPE_TALISMAN = 2901;
/*     */   
/*     */   public static String getLanguage(int langId) {
/* 220 */     LanguageBean languageBean = (LanguageBean)JsonTableService.getJsonData(langId, LanguageBean.class);
/* 221 */     if (null != languageBean) {
/* 222 */       return languageBean.getText();
/*     */     }
/* 224 */     return "";
/*     */   }
/*     */   
/*     */   public static String getAndReplaceLanguage(int langId, String... param) {
/* 228 */     LanguageBean languageBean = (LanguageBean)JsonTableService.getJsonData(langId, LanguageBean.class);
/* 229 */     if (null == languageBean) {
/* 230 */       return "";
/*     */     }
/* 232 */     String text = languageBean.getText();
/* 233 */     String[] strings = languageBean.getServerParameter().split(";");
/* 234 */     if (param.length != strings.length) {
/* 235 */       LogUtils.errorLog(new Object[] { "getAndReplaceLanguage", Integer.valueOf(param.length), Arrays.toString((Object[])strings), Arrays.toString((Object[])param) });
/* 236 */       return text;
/*     */     } 
/*     */     
/* 239 */     for (int i = 0; i < strings.length; i++) {
/* 240 */       text = text.replace("{" + strings[i] + "}", param[i]);
/*     */     }
/* 242 */     return text;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\LanguageConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */