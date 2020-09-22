/*     */ package linlongyx.sanguo.webgame.constant;
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum ResourceEvent
/*     */ {
/*   7 */   gmAdd(1, "GM获得"),
/*   8 */   gmUse(2, "GM消耗"),
/*   9 */   EquipStrengthUp(3, "装备强化"),
/*  10 */   BagSell(4, "卖道具获得"),
/*  11 */   MailExtract(5, "邮件获得"),
/*  12 */   charge(6, "充值"),
/*  13 */   EquipPurify(7, "装备精炼"),
/*  14 */   OnlineIncome(8, "挂机收益"),
/*  15 */   OfflineIncome(9, "离线收益"),
/*  16 */   SingleInsSweep(10, "日常副本扫荡"),
/*  17 */   ArenaClearCD(11, "竞技场消除CD"),
/*  18 */   ArenaBuyTime(12, "竞技场购买次数"),
/*  19 */   ArenaSweep(13, "竞技场扫荡"),
/*  20 */   ArenaFight(14, "竞技场挑战"),
/*  21 */   BossInsPass(15, "主线关卡boss副本通关固定奖励"),
/*  22 */   Recruit(16, "招募"),
/*  23 */   BagUseItem(17, "道具使用"),
/*  24 */   TaskReward(18, "完成任务"),
/*  25 */   BagBuyGrid(19, "购买格子"),
/*  26 */   MysteryBuy(20, "神秘商店购买"),
/*  27 */   RoutineBuy(21, "常规商店购买"),
/*  28 */   ShopRefresh(22, "商店刷新"),
/*  29 */   PartnerAddExp(23, "武将升级"),
/*  30 */   PartnerBreach(24, "武将突破"),
/*  31 */   HandBookBox(25, "图鉴宝箱"),
/*  32 */   RapidCombat(26, "快速作战"),
/*  33 */   FriendSendFlower(27, "好友送花"),
/*  34 */   RewardNotice(28, "奖励弹窗"),
/*  35 */   UnparBoxReward(29, "无双副本宝箱奖励"),
/*  36 */   UnparSweepReward(30, "无双副本扫荡奖励"),
/*  37 */   UnparReset(31, "无双副本重置"),
/*  38 */   WorldBossReward(32, "世界Boss奖励"),
/*  39 */   WorldBossTime(33, "世界Boss次数购买"),
/*  40 */   MainTaskBox(34, "主线章节宝箱奖励"),
/*  41 */   RecordStar(35, "激活命星奖励"),
/*  42 */   ActWarPet(36, "激活战宠"),
/*  43 */   TaskDailyReward(37, "日常奖励"),
/*  44 */   WarPetStarUp(38, "战宠升星"),
/*  45 */   AchieveReward(39, "成就奖励"),
/*  46 */   MainInsChallenge(40, "主线通关奖励"),
/*  47 */   WorshipReward(41, "膜拜奖励"),
/*  48 */   RecruitRed(42, "红将招募"),
/*  49 */   RecruitRedBuy(43, "购买招贤令"),
/*  50 */   RecruitRedBox(44, "红将招募宝箱"),
/*  51 */   EquipSell(45, "装备分解"),
/*  52 */   GeneralBoxReward(46, "名将副本宝箱奖励"),
/*  53 */   GeneralPointSweep(47, "名将副本扫荡"),
/*  54 */   GeneralBuyOrder(48, "名将副本购买军令"),
/*  55 */   GeneralPointReset(49, "名将副本关卡重置"),
/*  56 */   EquipZhuUp(50, "装备铸魂升级"),
/*  57 */   EquipStoneUp(51, "装备宝石升级"),
/*  58 */   PartnerReborn(52, "重生"),
/*  59 */   PartnerSell(53, "武将分解"),
/*  60 */   VipReward(54, "购买vip专享礼包"),
/*  61 */   WelfareVipDaily(55, "福利vip每日领取"),
/*  62 */   WelfareVipWeek(56, "福利vip每周购买"),
/*  63 */   BuySingleBoss(57, "购买个人boss次数"),
/*  64 */   SingleBossSweep(58, "个人boss扫荡"),
/*     */   
/*  66 */   RankActReward(59, "开服竞赛奖励"),
/*  67 */   ContinueRechargeReward(60, "开服连充领奖"),
/*  68 */   FirstChargeReward(61, "领取首充奖励"),
/*  69 */   LimitAct(62, "限时活动"),
/*  70 */   MultiUseItem(63, "多选一"),
/*  71 */   OverdueItem(64, "过期道具"),
/*  72 */   NormalInvite(65, "普通邀请"),
/*  73 */   DailySign(66, "每日签到"),
/*  74 */   LuxurySign(67, "豪华签到"),
/*  75 */   LevelPackage(68, "等级礼包"),
/*  76 */   MonthCardReward(69, "月卡奖励"),
/*  77 */   TitleActivate(70, "称号激活"),
/*  78 */   TitleDelay(71, "称号延期"),
/*  79 */   BuyGrowFund(72, "购买成长基金"),
/*  80 */   GetGrowFund(73, "领取成长基金奖励"),
/*  81 */   DailyShop(74, "每日特惠"),
/*  82 */   FortuneCatReward(75, "招财猫奖励"),
/*  83 */   FortuneCatCost(76, "招财猫花费"),
/*  84 */   RechargeAct(77, "限时累充活动"),
/*  85 */   TowerPassReward(78, "千层塔通关奖励"),
/*  86 */   TowerDailyReward(79, "千层塔结算奖励"),
/*  87 */   MountsBreak(80, "坐骑突破"),
/*  88 */   MountsUpgrade(81, "坐骑升级"),
/*  89 */   MountsActivity(82, "坐骑激活"),
/*  90 */   PiceRecovery(83, "碎片分解"),
/*  91 */   LuckyTurntable(84, "幸运转盘"),
/*  92 */   FirstRecruit(85, "第一次招募"),
/*  93 */   MountsStarUp(86, "坐骑升星"),
/*  94 */   ChangeNameSex(87, "改名或性别"),
/*  95 */   OnlineReward(88, "在线奖励"),
/*  96 */   CreatePlayer(89, "创角"),
/*  97 */   TeamFight(90, "组队副本"),
/*  98 */   GroupCreate(91, "军团创建"),
/*  99 */   GroupSacrificeAction(92, "进行祭天"),
/* 100 */   GroupSacrificeReward(93, "祭天宝箱"),
/* 101 */   GroupSkillLevelUp(94, "军团技能升级"),
/* 102 */   TowerBoxCost(95, "千层塔宝箱消耗"),
/* 103 */   GroupGrowFund(96, "成长基金团购奖励"),
/* 104 */   OneBuyReward(97, "一元购奖励"),
/*     */   
/* 106 */   GeneralGift(98, "武将送礼"),
/* 107 */   Turnplate(99, "天金轮盘"),
/* 108 */   CrossBattle(100, "争霸赛采集奖励");
/*     */   
/*     */   private int type;
/*     */   private String detail;
/*     */   
/*     */   ResourceEvent(int type, String detail) {
/* 114 */     this.type = type;
/* 115 */     this.detail = detail;
/*     */   }
/*     */   
/*     */   public int getType() {
/* 119 */     return this.type;
/*     */   }
/*     */   
/*     */   public String getDetail() {
/* 123 */     return this.detail;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\constant\ResourceEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */