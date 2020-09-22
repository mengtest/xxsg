/*     */ package com.linlongyx.sanguo.webgame.constant;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum TaskType
/*     */ {
/*  10 */   KillSingleMonster(101, "挂机场景击杀怪物N个"),
/*  11 */   EnterGame(102, "登陆游戏"),
/*  12 */   EquipStrengthUp(103, "强化装备任意部位n次"),
/*  13 */   MainIns(104, "通关指定副本X次"),
/*  14 */   EnterScene(105, "进入指定场景"),
/*  15 */   ChatWorld(106, "世界发言"),
/*  16 */   WearEquip(107, "主角装备武器"),
/*  17 */   ReachLevel(108, "主角等级达到N级"),
/*  18 */   QualityFighter(109, "获得X品质武将Y个"),
/*  19 */   StarFighter(110, "拥有X星武将Y个"),
/*  20 */   BreakFighter(111, "拥有突破X级武将Y个"),
/*  21 */   BattleFighter(112, "上阵武将达N个"),
/*  22 */   StrengthFighter(113, "上阵武将装备总强化等级达到n级"),
/*  23 */   PurifyFighter(114, "上阵武将装备总精炼等级达到n级"),
/*  24 */   ClearNormal(115, "通过日常副本n次"),
/*  25 */   ConsumeCCY(116, "活动累计消耗n元宝"),
/*  26 */   PurifyEquip(117, "精炼装备任意部位n次"),
/*  27 */   ChallengeRank(118, "挑战排名BOSS n次"),
/*  28 */   ChallengeWorld(119, "挑战世界BOSS n次"),
/*  29 */   FightValue(120, "战斗力达到n"),
/*  30 */   ChallengeArena(121, "挑战竞技场n次"),
/*  31 */   ArenaRank(122, "竞技场达到n名"),
/*  32 */   TotalNormal(123, "日常副本累计挑战n次"),
/*  33 */   ConsumeTotalCCY(124, "累计消耗n元宝"),
/*  34 */   RecruitCCY(125, "元宝招募n次"),
/*  35 */   PartnerLevelUp(126, "任意武将升级n次"),
/*  36 */   PartnerReachLevel(127, "任意武将达到n级"),
/*  37 */   ChapterReward(128, "领取第n章奖励"),
/*  38 */   PlayerBreak(129, "主角突破到n级"),
/*  39 */   PartnerEquipNum(130, "n名武将装备n件装备"),
/*  40 */   EquipLevel(131, "任意装备强化到n级"),
/*  41 */   ChapterSanGuoZhi(132, "完成三国志第n章"),
/*  42 */   UnparalleledNum(133, "三国无双挑战n次"),
/*  43 */   UnparalleledMaxPoint(134, "三国无双最高通过n关"),
/*  44 */   GeneralChallenge(135, "名将副本挑战n次"),
/*  45 */   GeneralChapter(136, "名将副本通过第n关"),
/*  46 */   OpenGroupList(137, "打开军团列表"),
/*  47 */   Charge(138, "充值n元宝"),
/*  48 */   SeriesRecharge(139, "连续X天充值y元宝"),
/*  49 */   OneCharge(140, "单笔充值n元宝"),
/*  50 */   Login(141, "登陆n天"),
/*  51 */   VipLevel(142, "vip等级达到n级"),
/*  52 */   MainChapter(143, "主线通过第n章"),
/*  53 */   UpEquip(144, "总共穿戴n件装备"),
/*  54 */   unparaReset(145, "无双副本重置n次"),
/*  55 */   Weapon(146, "法宝总精炼等级达到n级"),
/*  56 */   XWeapon(147, "x件法宝精炼等级达到n级"),
/*  57 */   UpQuiatyEquip(148, "穿戴x件y品质装备"),
/*  58 */   RankBossHurt(149, "排行boss累计n伤害"),
/*  59 */   UnparaMaxStar(150, "三国无双最高n星"),
/*  60 */   TotalRecruit(151, "累计招募n次"),
/*  61 */   EquipPurify(152, "装备精炼n次"),
/*  62 */   EquipStone(153, "镶嵌宝石n次"),
/*  63 */   RapitCombit(154, "快速作战n次"),
/*  64 */   PartnerDec(155, "武将分解n次"),
/*  65 */   CCYTenRecruit(156, "元宝十连招募n次"),
/*  66 */   CoinRecruit(157, "银币招募n次"),
/*  67 */   ChallegeSingeBoss(158, "挑战n级个人BOSS"),
/*  68 */   FinishRecice(159, "完成任意成就"),
/*  69 */   ActivityWarPet(160, "激活n只战宠"),
/*  70 */   PartnerEquipQ(161, "x名武将全身装备y品质装备"),
/*  71 */   PEquipStr(162, "6名武将全身装备强化至n级"),
/*  72 */   PetTotalStar(163, "战宠总星数达到n星"),
/*  73 */   KillRankBoss(164, "击杀排名BOSSn次"),
/*  74 */   GeneralBuy(165, "名将副本购买n次"),
/*  75 */   FinishMainTask(166, "开服开始，完成n个主线任务"),
/*  76 */   ComposePartner(167, "合成武将n次"),
/*  77 */   OpenRankBossPanel(168, "打开排行boss面板"),
/*  78 */   AchieveDone(169, "成就点达到n点"),
/*  79 */   NormalChallenge(170, "挑战某类型的日常副本n次"),
/*  80 */   TotalCharge(171, "充值n元宝"),
/*  81 */   TotalResetUnpra(172, "三国无双副本重置n次"),
/*  82 */   TotalChangeRankBoss(173, "挑战排行BOSSn次"),
/*  83 */   TotalComposePartner(174, "累计合成武将{1}次"),
/*  84 */   TotalNormalChallenge(175, "累计挑战x类型的日常副本n次"),
/*  85 */   ActiveTitle(176, "激活{1}个称号"),
/*  86 */   OnGeneralChallege(177, "接到任务后名将副本挑战n次"),
/*  87 */   MountsOpen(178, "坐骑功能开启"),
/*  88 */   ActivityTotalCharge(179, "活动时间内累计充值n元");
/*     */   private int type;
/*     */   private String name;
/*     */   private static Map<Integer, TaskType> map;
/*     */   
/*     */   static {
/*  94 */     map = new HashMap<>();
/*     */     
/*  96 */     for (TaskType taskType : values())
/*  97 */       map.put(Integer.valueOf(taskType.getType()), taskType); 
/*     */   }
/*     */   
/*     */   public static TaskType getTaskType(int type) {
/* 101 */     return map.get(Integer.valueOf(type));
/*     */   }
/*     */   
/*     */   TaskType(int type, String name) {
/* 105 */     this.type = type;
/* 106 */     this.name = name;
/*     */   }
/*     */   
/*     */   public int getType() {
/* 110 */     return this.type;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 114 */     return this.name;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\constant\TaskType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */