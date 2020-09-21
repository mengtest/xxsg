/*     */ package com.linlongyx.sanguo.webgame.constant;
/*     */ 
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.AbstractParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.AchieveParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ArenaParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.BagParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.BaguaParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.CardBookParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ChargeRebateParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ConsumeRebateParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ContinuityParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.CrossRaceParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.CrossRankParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DailyHaoLiParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DestinyParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DivineParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DrawCardParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.EquipParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.FightParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.FortuneCatParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.FriendParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.FundParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.GeneralGiftParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.GeneralParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.GoldBuyParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.GroupParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.InvitationParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.KungFuParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LimitActParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LimitBuyActParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LuckyMoneyActParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LuckyTurntableParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.MailParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.MentalParamter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.MilitaryParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.MountsParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.PartnerBattleParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.PartnerParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.PlatformRewardParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RankActParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RecoveryParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RecruitParamter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RuneParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RunewarParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.SecretiParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ShopParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.SingleInsParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.SoulsParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.StageParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TaskParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TeamParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TowerOwnerParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TurnplateParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.UnparalleledParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.WanderParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.WarPetParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.WarZhenfaParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.WelfareParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ZodiacParameter;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Arrays;
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
/*     */ public class ParameterConstant
/*     */ {
/*     */   public static final int TYPE_LOGIN = 0;
/*     */   public static final int TYPE_FIGHT = 1;
/*     */   public static final int TYPE_BOSS_HOME = 3;
/*     */   public static final int TYPE_ACHIEVE = 4;
/*     */   public static final int TYPE_MAIL = 5;
/*     */   public static final int TYPE_BAG = 7;
/*     */   public static final int TYPE_RECOVERY = 8;
/*     */   public static final int TYPE_SKILL = 9;
/*     */   public static final int TYPE_GROUP = 11;
/*     */   public static final int TYPE_GENERAL = 12;
/*     */   public static final int TYPE_SINGLE_INS = 13;
/*     */   public static final int TYPE_RECRUIT = 15;
/*     */   public static final int TYPE_COMMON = 16;
/*     */   public static final int TYPE_ARENA = 17;
/*     */   public static final int TYPE_UNPARALLELED = 18;
/*     */   public static final int TYPE_WELFARE = 19;
/*     */   public static final int TYPE_RANK_ACT = 20;
/*     */   public static final int TYPE_CONTINUITY = 21;
/*     */   public static final int TYPE_TASK = 22;
/*     */   public static final int TYPE_LIMIT = 23;
/*     */   public static final int TYPE_TURNPLATE = 24;
/*     */   public static final int TYPE_FUND = 25;
/*     */   public static final int TYPE_CROSS_RACE = 28;
/*     */   public static final int TYPE_WARPET = 30;
/*     */   public static final int TYPE_SHOP = 31;
/*     */   public static final int TYPE_FRIEND = 32;
/*     */   public static final int TYPE_PARTNER = 33;
/*     */   public static final int TYPE_PARTNER_BATTLE = 34;
/*     */   public static final int TYPE_LUCKY_TURNTABLE = 35;
/*     */   public static final int TYPE_INVITATION = 36;
/*     */   public static final int TYPE_TEAM = 37;
/*     */   public static final int TYPE_BAGUA = 41;
/*     */   public static final int TYPE_MOUNTS = 42;
/*     */   public static final int TYPE_SECRETI = 44;
/*     */   public static final int TYPE_RUNE_WAR = 45;
/*     */   public static final int TYPE_GENERAL_GIFT = 46;
/*     */   public static final int TYPE_DRAW = 48;
/*     */   public static final int TYPE_REBATE = 49;
/*     */   public static final int TYPE_CARD_BOOK = 50;
/*     */   public static final int TYPE_MILITARY = 51;
/*     */   public static final int TYPE_CONSUME = 52;
/*     */   public static final int TYPE_KUNGFU = 53;
/*     */   public static final int TYPE_STAGE = 54;
/*     */   public static final int TYPE_SOULS = 55;
/*     */   public static final int TYPE_DAILY_HAOLI = 56;
/*     */   public static final int TYPE_DIVINE = 57;
/*     */   public static final int TYPE_MENTAL = 58;
/*     */   public static final int TYPE_CROSS_RANK = 59;
/*     */   public static final int TYPE_WARZHENFA = 60;
/*     */   public static final int TYPE_LUCKYMONEY = 61;
/*     */   public static final int TYPE_TOWER_OWNER = 67;
/*     */   public static final int TYPE_LIMITBUY = 68;
/*     */   public static final int TYPE_RUNE = 69;
/*     */   public static final int TYPE_GOLD_BUY = 70;
/*     */   public static final int TYPE_RUNWANDER = 71;
/*     */   public static final int TYPE_PLATFORM_REWARD = 72;
/*     */   public static final int TYPE_ZODIAC = 73;
/*     */   public static final int TYPE_DESTINY = 74;
/*     */   public static final int TYPE_EQUIP = 78;
/*     */   public static final int TYPE_CAT = 80;
/*     */   public static final int TYPE_PARAMETER_END = 81;
/* 141 */   private static AbstractParameter[] parameters = new AbstractParameter[81];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 148 */     parameters[0] = (AbstractParameter)new LoginParameter();
/* 149 */     parameters[1] = (AbstractParameter)new FightParameter();
/* 150 */     parameters[5] = (AbstractParameter)new MailParameter();
/* 151 */     parameters[7] = (AbstractParameter)new BagParameter();
/* 152 */     parameters[15] = (AbstractParameter)new RecruitParamter();
/* 153 */     parameters[31] = (AbstractParameter)new ShopParameter();
/* 154 */     parameters[33] = (AbstractParameter)new PartnerParameter();
/* 155 */     parameters[34] = (AbstractParameter)new PartnerBattleParameter();
/* 156 */     parameters[13] = (AbstractParameter)new SingleInsParameter();
/* 157 */     parameters[3] = (AbstractParameter)new BossHomeParameter();
/* 158 */     parameters[17] = (AbstractParameter)new ArenaParameter();
/* 159 */     parameters[32] = (AbstractParameter)new FriendParameter();
/* 160 */     parameters[18] = (AbstractParameter)new UnparalleledParameter();
/* 161 */     parameters[22] = (AbstractParameter)new TaskParameter();
/* 162 */     parameters[30] = (AbstractParameter)new WarPetParameter();
/* 163 */     parameters[4] = (AbstractParameter)new AchieveParameter();
/* 164 */     parameters[11] = (AbstractParameter)new GroupParameter();
/* 165 */     parameters[8] = (AbstractParameter)new RecoveryParameter();
/* 166 */     parameters[12] = (AbstractParameter)new GeneralParameter();
/* 167 */     parameters[19] = (AbstractParameter)new WelfareParameter();
/* 168 */     parameters[23] = (AbstractParameter)new LimitActParameter();
/* 169 */     parameters[24] = (AbstractParameter)new TurnplateParameter();
/* 170 */     parameters[20] = (AbstractParameter)new RankActParameter();
/* 171 */     parameters[21] = (AbstractParameter)new ContinuityParameter();
/* 172 */     parameters[36] = (AbstractParameter)new InvitationParameter();
/* 173 */     parameters[25] = (AbstractParameter)new FundParameter();
/* 174 */     parameters[80] = (AbstractParameter)new FortuneCatParameter();
/* 175 */     parameters[37] = (AbstractParameter)new TeamParameter();
/* 176 */     parameters[42] = (AbstractParameter)new MountsParameter();
/* 177 */     parameters[74] = (AbstractParameter)new DestinyParameter();
/* 178 */     parameters[78] = (AbstractParameter)new EquipParameter();
/* 179 */     parameters[35] = (AbstractParameter)new LuckyTurntableParameter();
/* 180 */     parameters[46] = (AbstractParameter)new GeneralGiftParameter();
/* 181 */     parameters[48] = (AbstractParameter)new DrawCardParameter();
/* 182 */     parameters[49] = (AbstractParameter)new ChargeRebateParameter();
/* 183 */     parameters[50] = (AbstractParameter)new CardBookParameter();
/* 184 */     parameters[41] = (AbstractParameter)new BaguaParameter();
/* 185 */     parameters[51] = (AbstractParameter)new MilitaryParameter();
/* 186 */     parameters[52] = (AbstractParameter)new ConsumeRebateParameter();
/* 187 */     parameters[53] = (AbstractParameter)new KungFuParameter();
/* 188 */     parameters[54] = (AbstractParameter)new StageParameter();
/* 189 */     parameters[56] = (AbstractParameter)new DailyHaoLiParameter();
/* 190 */     parameters[57] = (AbstractParameter)new DivineParameter();
/* 191 */     parameters[58] = (AbstractParameter)new MentalParamter();
/* 192 */     parameters[60] = (AbstractParameter)new WarZhenfaParameter();
/* 193 */     parameters[59] = (AbstractParameter)new CrossRankParameter();
/* 194 */     parameters[55] = (AbstractParameter)new CrossRankParameter();
/* 195 */     parameters[28] = (AbstractParameter)new CrossRaceParameter();
/* 196 */     parameters[55] = (AbstractParameter)new SoulsParameter();
/* 197 */     parameters[61] = (AbstractParameter)new LuckyMoneyActParameter();
/* 198 */     parameters[67] = (AbstractParameter)new TowerOwnerParameter();
/* 199 */     parameters[44] = (AbstractParameter)new SecretiParameter();
/* 200 */     parameters[68] = (AbstractParameter)new LimitBuyActParameter();
/* 201 */     parameters[71] = (AbstractParameter)new WanderParameter();
/* 202 */     parameters[69] = (AbstractParameter)new RuneParameter();
/* 203 */     parameters[70] = (AbstractParameter)new GoldBuyParameter();
/* 204 */     parameters[72] = (AbstractParameter)new PlatformRewardParameter();
/* 205 */     parameters[45] = (AbstractParameter)new RunewarParameter();
/* 206 */     parameters[73] = (AbstractParameter)new ZodiacParameter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void init() {
/*     */     try {
/* 215 */       for (AbstractParameter parameter : parameters) {
/* 216 */         if (parameter != null) {
/* 217 */           parameter.load();
/*     */         }
/*     */       } 
/* 220 */     } catch (Exception e) {
/* 221 */       LogUtils.errorLog(new Object[] { "parameter load", Arrays.asList(e.getStackTrace()) });
/* 222 */       e.printStackTrace();
/* 223 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AbstractParameter getParameter(int type) {
/* 233 */     return parameters[type];
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initMap(Map<Integer, Integer> map, int param, int secParam) {
/* 238 */     ParameterBean parameterBean = (ParameterBean)JsonTableService.getJsonData(param, ParameterBean.class);
/* 239 */     String[] strings = ((ParameterBean.SecBean)parameterBean.getSec().get(Integer.valueOf(secParam))).getValue().split(";");
/* 240 */     for (String string : strings) {
/* 241 */       String[] strings2 = string.split(":");
/* 242 */       if (strings2.length > 1) {
/* 243 */         map.put(Integer.valueOf(Integer.parseInt(strings2[0])), Integer.valueOf(Integer.parseInt(strings2[1])));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void initList(List<Integer> list, int param, int secParam) {
/* 249 */     ParameterBean parameterBean = (ParameterBean)JsonTableService.getJsonData(param, ParameterBean.class);
/* 250 */     String[] strings = ((ParameterBean.SecBean)parameterBean.getSec().get(Integer.valueOf(secParam))).getValue().split(";");
/* 251 */     for (String string : strings) {
/* 252 */       list.add(Integer.valueOf(Integer.parseInt(string)));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getParameterConstant(int type, int sec) {
/* 262 */     ParameterBean parameterBean = (ParameterBean)JsonTableService.getJsonData(type, ParameterBean.class);
/* 263 */     return Integer.parseInt(((ParameterBean.SecBean)parameterBean.getSec().get(Integer.valueOf(sec))).getValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public static class SimpleAttr
/*     */   {
/*     */     public int type;
/*     */     
/*     */     public int value;
/*     */     
/*     */     public SimpleAttr(int type, int value) {
/* 274 */       setType(type);
/* 275 */       setValue(value);
/*     */     }
/*     */     
/*     */     public int getType() {
/* 279 */       return this.type;
/*     */     }
/*     */     
/*     */     public void setType(int type) {
/* 283 */       this.type = type;
/*     */     }
/*     */     
/*     */     public int getValue() {
/* 287 */       return this.value;
/*     */     }
/*     */     
/*     */     public void setValue(int value) {
/* 291 */       this.value = value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\ParameterConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */