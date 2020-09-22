/*     */ package com.linlongyx.sanguo.webgame.constant;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum TaskType
/*     */ {
/*  11 */   KillSingleMonster(101, "挂机场景击杀怪物N个"),
/*  12 */   EnterGame(102, "登陆游戏"),
/*  13 */   EquipStrengthUp(103, "强化装备任意部位n次"),
/*  14 */   MainIns(104, "通关指定副本X次"),
/*  15 */   EnterScene(105, "进入指定场景"),
/*  16 */   ChatWorld(106, "世界发言"),
/*  17 */   WearEquip(107, "主角装备武器"),
/*  18 */   ReachLevel(108, "主角等级达到N级"),
/*  19 */   QualityFighter(109, "获得X品质武将Y个"),
/*  20 */   StarFighter(110, "拥有X星武将Y个"),
/*  21 */   BreakFighter(111, "拥有突破X级武将Y个"),
/*  22 */   BattleFighter(112, "上阵武将达N个"),
/*  23 */   StrengthFighter(113, "上阵武将装备总强化等级达到n级"),
/*  24 */   PurifyFighter(114, "上阵武将装备总精炼等级达到n级"),
/*  25 */   ClearNormal(115, "通过日常副本n次"),
/*  26 */   ConsumeCCY(116, "活动累计消耗n元宝"),
/*  27 */   PurifyEquip(117, "精炼装备任意部位n次"),
/*  28 */   ChallengeRank(118, "挑战排名BOSS n次"),
/*  29 */   ChallengeWorld(119, "挑战世界BOSS n次"),
/*  30 */   FightValue(120, "战斗力达到n"),
/*  31 */   ChallengeArena(121, "挑战竞技场n次"),
/*  32 */   ArenaRank(122, "竞技场达到n名"),
/*  33 */   TotalNormal(123, "日常副本累计挑战n次"),
/*  34 */   ConsumeTotalCCY(124, "累计消耗n元宝"),
/*  35 */   RecruitCCY(125, "元宝招募n次"),
/*  36 */   PartnerLevelUp(126, "任意武将升级n次"),
/*  37 */   PartnerReachLevel(127, "任意武将达到n级"),
/*  38 */   ChapterReward(128, "领取第n章奖励"),
/*  39 */   PlayerBreak(129, "主角突破到n级"),
/*  40 */   PartnerEquipNum(130, "n名武将装备n件装备"),
/*  41 */   EquipLevel(131, "任意装备强化到n级"),
/*  42 */   ChapterSanGuoZhi(132, "完成三国志第n章"),
/*  43 */   UnparalleledNum(133, "三国无双挑战n次"),
/*  44 */   UnparalleledMaxPoint(134, "三国无双最高通过n关"),
/*  45 */   GeneralChallenge(135, "名将副本挑战n次"),
/*  46 */   GeneralChapter(136, "名将副本通过第n关"),
/*  47 */   OpenGroupList(137, "打开军团列表"),
/*  48 */   Charge(138, "充值n元宝"),
/*  49 */   SeriesRecharge(139, "连续X天充值y元宝"),
/*  50 */   OneCharge(140, "单笔充值n元宝"),
/*  51 */   Login(141, "登陆n天"),
/*  52 */   VipLevel(142, "vip等级达到n级"),
/*  53 */   MainChapter(143, "主线通过第n章"),
/*  54 */   UpEquip(144, "总共穿戴n件装备"),
/*  55 */   unparaReset(145, "无双副本重置n次"),
/*  56 */   Weapon(146, "法宝总精炼等级达到n级"),
/*  57 */   XWeapon(147, "x件法宝精炼等级达到n级"),
/*  58 */   UpQuiatyEquip(148, "穿戴x件y品质装备"),
/*  59 */   RankBossHurt(149, "排行boss累计n伤害"),
/*  60 */   UnparaMaxStar(150, "三国无双最高n星"),
/*  61 */   TotalRecruit(151, "累计招募n次"),
/*  62 */   EquipPurify(152, "装备精炼n次"),
/*  63 */   EquipStone(153, "镶嵌宝石n次"),
/*  64 */   RapitCombit(154, "快速作战n次"),
/*  65 */   PartnerDec(155, "武将分解n次"),
/*  66 */   CCYTenRecruit(156, "元宝十连招募n次"),
/*  67 */   CoinRecruit(157, "银币招募n次"),
/*  68 */   ChallegeSingeBoss(158, "挑战n级个人BOSS"),
/*  69 */   FinishRecice(159, "完成任意成就"),
/*  70 */   ActivityWarPet(160, "激活n只战宠"),
/*  71 */   PartnerEquipQ(161, "x名武将全身装备y品质装备"),
/*  72 */   PEquipStr(162, "6名武将全身装备强化至n级"),
/*  73 */   PetTotalStar(163, "战宠总星数达到n星"),
/*  74 */   KillRankBoss(164, "击杀排名BOSSn次"),
/*  75 */   GeneralBuy(165, "名将副本购买n次"),
/*  76 */   FinishMainTask(166, "开服开始，完成n个主线任务"),
/*  77 */   ComposePartner(167, "合成武将n次"),
/*  78 */   OpenRankBossPanel(168, "打开排行boss面板"),
/*  79 */   AchieveDone(169, "成就点达到n点"),
/*  80 */   NormalChallenge(170, "挑战某类型的日常副本n次"),
/*  81 */   TotalCharge(171, "充值n元宝"),
/*  82 */   TotalResetUnpra(172, "三国无双副本重置n次"),
/*  83 */   TotalChangeRankBoss(173, "挑战排行BOSSn次"),
/*  84 */   TotalComposePartner(174, "累计合成武将{1}次"),
/*  85 */   TotalNormalChallenge(175, "累计挑战x类型的日常副本n次"),
/*  86 */   ActiveTitle(176, "激活{1}个称号"),
/*  87 */   OnGeneralChallege(177, "接到任务后名将副本挑战n次"),
/*  88 */   MountsOpen(178, "坐骑功能开启"),
/*  89 */   ActivityTotalCharge(179, "多个活动时间内累计充值n元，庆典专用"),
/*  90 */   GetOnePartner(180, "获得某个武将"),
/*  91 */   TotalFightTimes(181, "挑战竞技场n次"),
/*  92 */   ChangeTower(182, "挑战妖凰塔{1}次"),
/*  93 */   ChangeTeamIns(183, "挑战组队副本{1}次"),
/*  94 */   MonthYearWeek(184, "拥有月卡、年卡、周卡之一"),
/*  95 */   GroupWorship(185, "军团祭天{1}次"),
/*  96 */   RefreshMySteryShop(186, "神秘商店刷新{1}次"),
/*  97 */   RankWorship(187, "膜拜{1}次"),
/*  98 */   GetOnlineGift(188, "领取在线礼包{1}次"),
/*  99 */   WarPetFeed(189, "战宠喂食{1}次"),
/* 100 */   MountsFeed(190, "坐骑喂食{1}次"),
/* 101 */   TreasureStrenth(191, "宝物强化{1}次"),
/* 102 */   MountsTotalStar(192, "坐骑总星数达到{1}星"),
/* 103 */   ActivityMounts(193, "激活{1}只坐骑"),
/* 104 */   TowerMaxPoint(194, "妖凰塔最高{0}层"),
/* 105 */   TotalTeamTimes(195, "挑战组队副本{1}次"),
/* 106 */   TowerExtendReward(196, "妖凰塔额外奖励购买"),
/* 107 */   RankBossBuy(197, "排行boss次数购买{1}次"),
/* 108 */   OpenAskPanel(198, "打开求助大奖界面"),
/* 109 */   NPartnerLevel(199, "x个武将达到y级"),
/* 110 */   JoinTeamIns(200, "参加组队副本n次"),
/* 111 */   AlientBossReward(201, "异族BOSS获得n次归属奖励"),
/* 112 */   ZhenFaDian(202, "阵法殿抽奖n次"),
/* 113 */   JoinDestiny(203, "参加天命战场n次"),
/* 114 */   CardBookColor(204, "三国策着色n次"),
/* 115 */   ActZhenFa(205, "激活n个阵法"),
/* 116 */   TotalZhenFaLevel(206, "阵法总等级达到n级"),
/* 117 */   TotalZhenFaStar(207, "阵法总星数达到n级"),
/* 118 */   ActStage(208, "激活n个将台"),
/* 119 */   TotalStageLevel(209, "将台总等级达到n级"),
/* 120 */   TotalStageStar(210, "将台总星数达到n级"),
/* 121 */   ActKungFu(211, "激活{0}个招式"),
/* 122 */   TotalKungFuLevel(212, "招式总等级达到{1}级"),
/* 123 */   TotalKungFuStar(213, "招式总星数达到{1}级"),
/* 124 */   OneMoreCharge(214, "多次单笔充值领取多次"),
/* 125 */   MilitaryResearch(215, "计算军务府研究n次"),
/* 126 */   EquipZhuhun(216, "计算装备铸魂n次"),
/* 127 */   PartnerDenstiny(217, "历史x名武将天命达到y级"),
/* 128 */   BaGuaPass(218, "历史通关八卦阵第n关"),
/* 129 */   AssistPartner(219, "历史助阵上阵n名武将"),
/* 130 */   SecretiTimes(220, "计算秘境挑战{1}次"),
/* 131 */   KuaFuTimes(221, "计算跨服段位赛参加{1}次"),
/* 132 */   TalismanTurn(222, "计算法宝宝库抽奖{1}次"),
/* 133 */   TalimanUp(223, "历史{1}名武将装备{0}品质法宝"),
/* 134 */   ChangeNeutralBoss(224, "计算挑战{1}次异族BOSS"),
/* 135 */   EquipArtifice(225, "历史{1}件装备炼化达到{0}级"),
/* 136 */   ArtificeTimes(226, "计算炼化{1}次"),
/* 137 */   EquipStarTarget(227, "历史{1}件装备升到{0}星"),
/* 138 */   TotalBattleSoul(228, "历史上阵{1}个英魂"),
/* 139 */   TotalPartentSoul(229, "历史{1}名武将精魄达到{0}级"),
/* 140 */   TotalMilitaryResearch(230, "历史军务府研究{1}次"),
/* 141 */   TotalEquipZhuhun(231, "历史装备铸魂{1}次"),
/* 142 */   TotalSecretiTimes(232, "历史秘境挑战{1}次"),
/* 143 */   TotalKuaFuTimes(233, "历史跨服段位赛参加{1}次"),
/* 144 */   TotalTalismanTurn(234, "历史法宝宝库抽奖{1}次"),
/* 145 */   TatalChangeNeutralBoss(235, "历史挑战{1}次异族BOSS"),
/* 146 */   TatalArtificeTimes(236, "历史炼化{1}次"),
/* 147 */   PartnerReincarnStar(237, "转生武将升星到{1}星目标id是-1则去当前武将的表id，目前用在转生"),
/* 148 */   PartnerReincarnDestiny(238, "转生武将天命{1}级目标id是-1则去当前武将的表id，目前用在转生"),
/* 149 */   PartnerReincarnSouls(239, "转生武将精魄{1}级目标id是-1则去当前武将的表id，目前用在转生"),
/* 150 */   PartnerReincarnBreak(240, "转生武将突破{1}级目标id是-1则去当前武将的表id，目前用在转生"),
/* 151 */   SecretiFloor(241, "秘境通关到n层");
/*     */   private int type;
/*     */   private String name;
/*     */   private static Map<Integer, TaskType> map;
/*     */   
/*     */   static {
/* 157 */     map = new HashMap<>();
/*     */ 
/*     */     
/* 160 */     for (TaskType taskType : values())
/* 161 */       map.put(Integer.valueOf(taskType.getType()), taskType); 
/*     */   }
/*     */   
/*     */   public static TaskType getTaskType(int type) {
/* 165 */     return map.get(Integer.valueOf(type));
/*     */   }
/*     */   
/*     */   TaskType(int type, String name) {
/* 169 */     this.type = type;
/* 170 */     this.name = name;
/*     */   }
/*     */   
/*     */   public int getType() {
/* 174 */     return this.type;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 178 */     return this.name;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\TaskType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */