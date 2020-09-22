/*      */ package com.linlongyx.sanguo.webgame.processors;
/*      */ import com.linlongyx.sanguo.webgame.processors.Invitation.NormalInvitationFinishProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.achieve.AchieveRewardProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.aoi.EnterConfirmProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.aoi.LeaveSceneProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.aoi.PlayerPositionProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.aoi.SceneAOIMessageProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.aoi.WalkSyncProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.arena.ArenaSweepProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.artifact.ArtifactActivateProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.artifact.ArtifactSaveProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.artifact.ArtifactTrainProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bag.BagBuyGoldTaxProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bag.BagDropProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bag.BagNoticeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bag.BagUseItemProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bag.GetRebornListProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bagua.BaguaChapterInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bagua.BaguaRecordInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bosshome.BossDamagePushProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bosshome.BossListProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bosshome.RankBossSettingInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bosshome.ReviveRankBossProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bosshome.WorldDamageListProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bosshome.WorldPushProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.bosshome.WorldRewardInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.charge.ChargeInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.charge.GetChargeGroupRewardProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.chat.ChatByChannelProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.chat.ChatProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.common.AdditionUpdateProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.common.FunctionTimeNoticeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.common.GetPlayerBattleInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.common.GetPlayerInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.common.RewardNoticeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.consume.ConsumeRebateNoticeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.consume.ConsumeRebateRewardProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.cross.CrossTokenProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.crossRankAct.CrossRankActInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.crossRankAct.CrossRankActListProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.destiny.DestinyBattleInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.destiny.DestinyBattleTaskRewardProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.destiny.DestinyBetMatchInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.destiny.DestinyBetRecordInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.destiny.DestinyMatchStateInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.divine.DivineDrawTenProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.draw.DrawCardCleanProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.draw.DrawCardInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.draw.DrawCardPlayProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.equip.DeleteEquipNoticeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.equip.EquipListProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.equip.EquipStarUpProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.equip.EquipStrengthUpProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.equip.EquipZhuUpProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.equip.TalismanRankUpProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.equip.TalismanUpOrDownProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.fashion.FashionActivateProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.fashion.FashionStrengthenProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.fight.InsRewardProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.fight.KillSingleMonsterProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.friend.FriendApplyProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.friend.FriendDealApplyProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.friend.FriendFavorValueNoticeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.friend.FriendGetPointProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.friend.FriendOpenProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.friend.FriendSendChatProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.friend.FriendSendPointProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.friend.FriendSerachProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.friend.FriendSerachTwoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.general.GeneralChapterInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.group.GroupApplyProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.group.GroupInsInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.group.GroupInsResetProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.group.GroupNoticeChangeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.group.GroupRecruitProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.group.GroupSkillInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.kungfu.KungFuBattleProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.limit.LimitActRedProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.limit.LimitActRewardProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.login.CheckAccountProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.login.ConstantInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.login.OfflineIncomeInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.login.RapidCombatProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.luckyTurntable.LuckyTurntableRankProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.luckyTurntable.PetTurntableDrawProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.luckyTurntable.PetTurntableInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.luckymoney.LuckyMoneyAddProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.mail.MailListProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.mental.MentalRankProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.mounts.MoountsUpgradeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.mounts.MountsInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.mounts.MountsRideProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.mounts.MountsStarUpProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.mounts.UpdateFightValueProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.offices.StartResearchProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.offices.UseSpeedUpItemProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.partner.AddPartnerNoticeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.partner.GetAssistInBattleListProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerBreachProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerDestinyUpProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerHandbookInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerSoulLevelProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerStarUpProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActRewardProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.rechargeAct.RechargeActRewardProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.recruit.BuyRedRecruitItemProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.rune.RuneRedNoticeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.rune.RuneSysInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.rune.RuneSysLevelUpProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.runewar.RunewarRefreshProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.runewar.RunewarRewardGetProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.shop.BuyGoodsProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.shop.MysteryRefreshProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.shop.MysteryShopListProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.shop.RoutineShopListProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.sign.BuyLevelPackageProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.sign.LevelPackageInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.singleIns.NormalInsInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.skill.SkillListProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.skill.SkillNewProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.stage.ActivityStageProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.stage.StageUpgradeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.task.ChannelGiftGetProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.task.FindRewardInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.task.GetChapterBoxProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.task.QQVIPDailyInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.task.TaskCompleteNoticeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.task.TaskCompleteProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.task.TaskGuideProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.team.JoinTeamProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.team.KickoutTeamProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.team.LeaveTeamProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.team.SendFighterProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.team.TeamPlayerDataNoticeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.team.TeamPlayerReadyProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.timelimit.TimeLimitBuyOpenProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.title.TitleActivateProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.title.TitleDelayProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.tower.TowerRankInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.tower.TowerRewardProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.towerOwner.TowerOwnerFightProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.towerOwner.TowerOwnerRecordProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.turnplate.TurnplateActNoticeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.turnplate.TurnplateDrawProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.turnplate.TurnplateInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.unparalleled.GetAttrbuteInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.warlineup.WarLineupBattleProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.warlineup.WarLineupStarUpProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.warpet.WarPetBattleProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.warpet.WarPetHandBookInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.warpet.WarPetUpgradeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.welfare.ActiveCodeGetProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.welfare.BuyGrowFundProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.welfare.DailiHaoLiNoticeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.welfare.GeneralGiftInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.welfare.GetBackGiftProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.welfare.GrowFundInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.welfare.MonthCardInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.welfare.OneBuyInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.welfare.OneRechargeInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.welfare.PushDailyShopInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.welfare.WelfareVipDailyProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.zodiac.ZodiacExchangeProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.zodiac.ZodiacInfoProcessor;
/*      */ import com.linlongyx.sanguo.webgame.processors.zodiac.ZodiacOpenProcessor;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.achieve.AchieveRewardRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.AvatarDieRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.AvatarUpdateRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.ChangeModelRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.LeaveSceneRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.SceneAOIMessageRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.WalkSceneRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.WalkSyncRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.artifact.ArtifactListRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.artifact.ArtifactSaveRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.bagua.BaguaBuyTreasureRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.bagua.BaguaSweepRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.BossDamagePushRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.BossHitInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.ReviveRankBossRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldRewardRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookProcessRewardRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookRankInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.charge.FirstChargeRewardRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.charge.GetDaliyChargeRewardRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatByChannelRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatRadiateRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.common.AttrUpdateRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.common.PartnerAttrUpdateRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.common.RedNoticeRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.continuity.ContinueRechargeInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.continuity.ContinueRechargeRewardRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.crossRace.CrossRaceMatchRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.crossRankAct.CrossRankActListRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.crossRankAct.CrossRankActNoticeRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBattleInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBattleRankRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBattleTaskProgressRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBetNumInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyMatchGroupInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyMatchStateInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.divine.DivineDrawTenRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.divine.DivineRankInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardCleanRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardNoticeRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardPlayRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipArtificeUpRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipDataSysRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipPurifyRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipZhuUpRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.fashion.FashionActivateRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendApplyListRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendDealApplyRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendOpenRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendReadChatRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralBoxRewardRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralBuyOrderRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralChapterInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralPointSweepRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupApplyListRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupChangeNameRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupCreateRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupNoticeChangeRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupSacrificeActionRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupSkillInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.kungfu.KungFuStarUpRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActNoticeRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActRewardRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.login.CheckAccountRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.login.ConstantInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.LuckyTurntableOpenRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.luckymoney.LuckyMoneyAddRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailReadRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.mental.MentalLotteryInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.mental.MentalRankRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.mental.MyMentalInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.mental.OpenMentalBoxRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.mental.RaffleLotteryRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsActivityRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsBreakRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsStarUpRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.offices.HelpInfoListRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.offices.HelpOthersRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.offices.MilitaryOfficeInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.offices.ResearchEndRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.offices.StartResearchRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.offices.UseSpeedUpItemRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.partner.AssistInBattleRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerHandbookInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerSoulLevelRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PushPartnerInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.rank.RankingInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.GetPersonalRecordRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.PushServicRecordRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.BuyRedRecruitItemRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.GetRecruitInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RecruitRebateInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RecruitRebateRewardRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RedRecruitInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.StartRecruitRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.rune.RuneBagOpenRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.secreti.SecretiInsInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.shop.MysteryRefreshRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.shop.RoutineShopListRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.sign.BuyLevelPackageRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.sign.SignInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.singleIns.NormalInsSweepRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.skill.SkillListRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.souls.SoulsActOrStarUpRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.stage.ActivityStageRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.stage.StageStarUpRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.talisman.TalismanDrawRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.talisman.TalismanInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.task.FindRewardGetRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.task.GetChapterBoxRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskGuideRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.task.TaskPlatformRewardRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.team.ConvenientInviteRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamKickoutNoticeRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamPlayerFighterInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamPlayerReadyNoticeRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamPlayerReadyRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitBuyOpenRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitExchangeInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.title.TitleActivateRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.title.TitleInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.title.TitleWearRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.tower.TowerInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.tower.TowerRewardRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerFightRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerGiveUpRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerMobaiRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.turnplate.TurnplateDrawRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.turnplate.TurnplateInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.wander.WanderInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.ActivityWarLineupRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.warlineup.WarLineupInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.ActWarPetHandBookRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.WarPetStarUpRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.DailiHaoLiInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.DailyShopInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GetGrowFundRewardRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GrowFundInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.MonthCardRewardRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.WelfareVipInfoRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.WelfareVipWeekRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.yearbeast.YearBeastBossNoticeRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.zodiac.ZodiacExchangeRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.zodiac.ZodiacTaskRequest;
/*      */ 
/*      */ public enum MsgProcessorRegister {
/*  317 */   AchieveInfo((short)10401, AchieveInfoProcessor.class, AchieveInfoRequest.class),
/*      */ 
/*      */   
/*  320 */   AchieveReward((short)10402, AchieveRewardProcessor.class, AchieveRewardRequest.class),
/*      */ 
/*      */   
/*  323 */   GetActivityBagInfo((short)11401, GetActivityBagInfoProcessor.class, GetActivityBagInfoRequest.class),
/*      */ 
/*      */   
/*  326 */   AvatarDie((short)10112, AvatarDieProcessor.class, AvatarDieRequest.class),
/*      */ 
/*      */   
/*  329 */   AvatarUpdate((short)10113, AvatarUpdateProcessor.class, AvatarUpdateRequest.class),
/*      */ 
/*      */   
/*  332 */   ChangeModel((short)10107, ChangeModelProcessor.class, ChangeModelRequest.class),
/*      */ 
/*      */   
/*  335 */   EnterConfirm((short)10109, EnterConfirmProcessor.class, EnterConfirmRequest.class),
/*      */ 
/*      */   
/*  338 */   EnterScene((short)10101, EnterSceneProcessor.class, EnterSceneRequest.class),
/*      */ 
/*      */   
/*  341 */   LeaveScene((short)10103, LeaveSceneProcessor.class, LeaveSceneRequest.class),
/*      */ 
/*      */   
/*  344 */   PlayerPosition((short)10110, PlayerPositionProcessor.class, PlayerPositionRequest.class),
/*      */ 
/*      */   
/*  347 */   RemoveAvatar((short)10111, RemoveAvatarProcessor.class, RemoveAvatarRequest.class),
/*      */ 
/*      */   
/*  350 */   SceneAOIMessage((short)10100, SceneAOIMessageProcessor.class, SceneAOIMessageRequest.class),
/*      */ 
/*      */   
/*  353 */   UpdateSceneDynData((short)10106, UpdateSceneDynDataProcessor.class, UpdateSceneDynDataRequest.class),
/*      */ 
/*      */   
/*  356 */   WalkScene((short)10102, WalkSceneProcessor.class, WalkSceneRequest.class),
/*      */ 
/*      */   
/*  359 */   WalkStop((short)10105, WalkStopProcessor.class, WalkStopRequest.class),
/*      */ 
/*      */   
/*  362 */   WalkSync((short)10104, WalkSyncProcessor.class, WalkSyncRequest.class),
/*      */ 
/*      */   
/*  365 */   ArenaBuyTimes((short)11702, ArenaBuyTimesProcessor.class, ArenaBuyTimesRequest.class),
/*      */ 
/*      */   
/*  368 */   ArenaInfo((short)11701, ArenaInfoProcessor.class, ArenaInfoRequest.class),
/*      */ 
/*      */   
/*  371 */   ArenaReport((short)11704, ArenaReportProcessor.class, ArenaReportRequest.class),
/*      */ 
/*      */   
/*  374 */   ArenaSweep((short)11705, ArenaSweepProcessor.class, ArenaSweepRequest.class),
/*      */ 
/*      */   
/*  377 */   BagAddGrid((short)10709, BagAddGridProcessor.class, BagAddGridRequest.class),
/*      */ 
/*      */   
/*  380 */   BagDrop((short)10710, BagDropProcessor.class, BagDropRequest.class),
/*      */ 
/*      */   
/*  383 */   BagNotice((short)10704, BagNoticeProcessor.class, BagNoticeRequest.class),
/*      */ 
/*      */   
/*  386 */   BagOpen((short)10701, BagOpenProcessor.class, BagOpenRequest.class),
/*      */ 
/*      */   
/*  389 */   BagSellEquip((short)10706, BagSellEquipProcessor.class, BagSellEquipRequest.class),
/*      */ 
/*      */   
/*  392 */   BagSellItem((short)10703, BagSellItemProcessor.class, BagSellItemRequest.class),
/*      */ 
/*      */   
/*  395 */   BagUseItem((short)10702, BagUseItemProcessor.class, BagUseItemRequest.class),
/*      */ 
/*      */   
/*  398 */   BossDamagePush((short)10302, BossDamagePushProcessor.class, BossDamagePushRequest.class),
/*      */ 
/*      */   
/*  401 */   BossHitInfo((short)10303, BossHitInfoProcessor.class, BossHitInfoRequest.class),
/*      */ 
/*      */   
/*  404 */   BossList((short)10301, BossListProcessor.class, BossListRequest.class),
/*      */ 
/*      */   
/*  407 */   WorldBuyTime((short)10308, WorldBuyTimeProcessor.class, WorldBuyTimeRequest.class),
/*      */ 
/*      */   
/*  410 */   WorldDamageList((short)10307, WorldDamageListProcessor.class, WorldDamageListRequest.class),
/*      */ 
/*      */   
/*  413 */   WorldInfo((short)10304, WorldInfoProcessor.class, WorldInfoRequest.class),
/*      */ 
/*      */   
/*  416 */   WorldPush((short)10306, WorldPushProcessor.class, WorldPushRequest.class),
/*      */ 
/*      */   
/*  419 */   WorldReward((short)10305, WorldRewardProcessor.class, WorldRewardRequest.class),
/*      */ 
/*      */   
/*  422 */   WorldRewardInfo((short)10309, WorldRewardInfoProcessor.class, WorldRewardInfoRequest.class),
/*      */ 
/*      */   
/*  425 */   Chat((short)10602, ChatProcessor.class, ChatRequest.class),
/*      */ 
/*      */   
/*  428 */   ChatBroadcast((short)10603, ChatBroadcastProcessor.class, ChatBroadcastRequest.class),
/*      */ 
/*      */   
/*  431 */   ChatByChannel((short)10604, ChatByChannelProcessor.class, ChatByChannelRequest.class),
/*      */ 
/*      */   
/*  434 */   ChatOpen((short)10605, ChatOpenProcessor.class, ChatOpenRequest.class),
/*      */ 
/*      */   
/*  437 */   ChatRadiate((short)10601, ChatRadiateProcessor.class, ChatRadiateRequest.class),
/*      */ 
/*      */   
/*  440 */   AdditionUpdate((short)11004, AdditionUpdateProcessor.class, AdditionUpdateRequest.class),
/*      */ 
/*      */   
/*  443 */   AttrUpdate((short)11003, AttrUpdateProcessor.class, AttrUpdateRequest.class),
/*      */ 
/*      */   
/*  446 */   DelayRewardNotice((short)11005, DelayRewardNoticeProcessor.class, DelayRewardNoticeRequest.class),
/*      */ 
/*      */   
/*  449 */   FunctionTimeNotice((short)11006, FunctionTimeNoticeProcessor.class, FunctionTimeNoticeRequest.class),
/*      */ 
/*      */   
/*  452 */   GetPlayerInfo((short)11010, GetPlayerInfoProcessor.class, GetPlayerInfoRequest.class),
/*      */ 
/*      */   
/*  455 */   KeyValueUpdate((short)11001, KeyValueUpdateProcessor.class, KeyValueUpdateRequest.class),
/*      */ 
/*      */   
/*  458 */   PartnerAttrUpdate((short)11007, PartnerAttrUpdateProcessor.class, PartnerAttrUpdateRequest.class),
/*      */ 
/*      */   
/*  461 */   RedNotice((short)11008, RedNoticeProcessor.class, RedNoticeRequest.class),
/*      */ 
/*      */   
/*  464 */   RewardNotice((short)11002, RewardNoticeProcessor.class, RewardNoticeRequest.class),
/*      */ 
/*      */   
/*  467 */   AddEquipNotice((short)10808, AddEquipNoticeProcessor.class, AddEquipNoticeRequest.class),
/*      */ 
/*      */   
/*  470 */   EquipList((short)10806, EquipListProcessor.class, EquipListRequest.class),
/*      */ 
/*      */   
/*  473 */   EquipPurify((short)10812, EquipPurifyProcessor.class, EquipPurifyRequest.class),
/*      */ 
/*      */   
/*  476 */   EquipStrengthUp((short)10811, EquipStrengthUpProcessor.class, EquipStrengthUpRequest.class),
/*      */ 
/*      */   
/*  479 */   UpOrDownEquip((short)10807, UpOrDownEquipProcessor.class, UpOrDownEquipRequest.class),
/*      */ 
/*      */   
/*  482 */   EndPlayFight((short)10201, EndPlayFightProcessor.class, EndPlayFightRequest.class),
/*      */ 
/*      */   
/*  485 */   FightRecord((short)10204, FightRecordProcessor.class, FightRecordRequest.class),
/*      */ 
/*      */   
/*  488 */   InsReward((short)10206, InsRewardProcessor.class, InsRewardRequest.class),
/*      */ 
/*      */   
/*  491 */   KillSingleMonster((short)10205, KillSingleMonsterProcessor.class, KillSingleMonsterRequest.class),
/*      */ 
/*      */   
/*  494 */   FriendApply((short)13408, FriendApplyProcessor.class, FriendApplyRequest.class),
/*      */ 
/*      */   
/*  497 */   FriendApplyList((short)13406, FriendApplyListProcessor.class, FriendApplyListRequest.class),
/*      */ 
/*      */   
/*  500 */   FriendDealApply((short)13409, FriendDealApplyProcessor.class, FriendDealApplyRequest.class),
/*      */ 
/*      */   
/*  503 */   FriendDelete((short)13410, FriendDeleteProcessor.class, FriendDeleteRequest.class),
/*      */ 
/*      */   
/*  506 */   FriendFavorValueNotice((short)13419, FriendFavorValueNoticeProcessor.class, FriendFavorValueNoticeRequest.class),
/*      */ 
/*      */   
/*  509 */   FriendGetPoint((short)13417, FriendGetPointProcessor.class, FriendGetPointRequest.class),
/*      */ 
/*      */   
/*  512 */   FriendInfoNotice((short)13414, FriendInfoNoticeProcessor.class, FriendInfoNoticeRequest.class),
/*      */ 
/*      */   
/*  515 */   FriendLoginNotice((short)13402, FriendLoginNoticeProcessor.class, FriendLoginNoticeRequest.class),
/*      */ 
/*      */   
/*  518 */   FriendOpen((short)13401, FriendOpenProcessor.class, FriendOpenRequest.class),
/*      */ 
/*      */   
/*  521 */   FriendReadChat((short)13403, FriendReadChatProcessor.class, FriendReadChatRequest.class),
/*      */ 
/*      */   
/*  524 */   FriendSendChat((short)13404, FriendSendChatProcessor.class, FriendSendChatRequest.class),
/*      */ 
/*      */   
/*  527 */   FriendSendPoint((short)13416, FriendSendPointProcessor.class, FriendSendPointRequest.class),
/*      */ 
/*      */   
/*  530 */   FriendSerach((short)13405, FriendSerachProcessor.class, FriendSerachRequest.class),
/*      */ 
/*      */   
/*  533 */   FriendStateNotice((short)13413, FriendStateNoticeProcessor.class, FriendStateNoticeRequest.class),
/*      */ 
/*      */   
/*  536 */   GroupApply((short)11103, GroupApplyProcessor.class, GroupApplyRequest.class),
/*      */ 
/*      */   
/*  539 */   GroupApplyDeal((short)11109, GroupApplyDealProcessor.class, GroupApplyDealRequest.class),
/*      */ 
/*      */   
/*  542 */   GroupApplyList((short)11108, GroupApplyListProcessor.class, GroupApplyListRequest.class),
/*      */ 
/*      */   
/*  545 */   GroupCancelApply((short)11104, GroupCancelApplyProcessor.class, GroupCancelApplyRequest.class),
/*      */ 
/*      */   
/*  548 */   GroupCreate((short)11105, GroupCreateProcessor.class, GroupCreateRequest.class),
/*      */ 
/*      */   
/*  551 */   GroupInfo((short)11101, GroupInfoProcessor.class, GroupInfoRequest.class),
/*      */ 
/*      */   
/*  554 */   GroupList((short)11102, GroupListProcessor.class, GroupListRequest.class),
/*      */ 
/*      */   
/*  557 */   GroupMemberList((short)11107, GroupMemberListProcessor.class, GroupMemberListRequest.class),
/*      */ 
/*      */   
/*  560 */   GroupMemberManage((short)11110, GroupMemberManageProcessor.class, GroupMemberManageRequest.class),
/*      */ 
/*      */   
/*  563 */   GroupNoticeChange((short)11106, GroupNoticeChangeProcessor.class, GroupNoticeChangeRequest.class),
/*      */ 
/*      */   
/*  566 */   GroupSacrificeAction((short)11112, GroupSacrificeActionProcessor.class, GroupSacrificeActionRequest.class),
/*      */ 
/*      */   
/*  569 */   GroupSacrificeInfo((short)11111, GroupSacrificeInfoProcessor.class, GroupSacrificeInfoRequest.class),
/*      */ 
/*      */   
/*  572 */   GroupSacrificeReward((short)11113, GroupSacrificeRewardProcessor.class, GroupSacrificeRewardRequest.class),
/*      */ 
/*      */   
/*  575 */   GroupSkillInfo((short)11114, GroupSkillInfoProcessor.class, GroupSkillInfoRequest.class),
/*      */ 
/*      */   
/*  578 */   GroupSkillLevelUp((short)11115, GroupSkillLevelUpProcessor.class, GroupSkillLevelUpRequest.class),
/*      */ 
/*      */   
/*  581 */   CheckAccount((short)10011, CheckAccountProcessor.class, CheckAccountRequest.class),
/*      */ 
/*      */   
/*  584 */   CreatePlayer((short)10002, CreatePlayerProcessor.class, CreatePlayerRequest.class),
/*      */ 
/*      */   
/*  587 */   EnterGame((short)10003, EnterGameProcessor.class, EnterGameRequest.class),
/*      */ 
/*      */   
/*  590 */   LoginInfo((short)10001, LoginInfoProcessor.class, LoginInfoRequest.class),
/*      */ 
/*      */   
/*  593 */   LoginInfoDebug((short)10010, LoginInfoDebugProcessor.class, LoginInfoDebugRequest.class),
/*      */ 
/*      */   
/*  596 */   OfflineIncomeInfo((short)10006, OfflineIncomeInfoProcessor.class, OfflineIncomeInfoRequest.class),
/*      */ 
/*      */   
/*  599 */   OfflineIncomeReward((short)10007, OfflineIncomeRewardProcessor.class, OfflineIncomeRewardRequest.class),
/*      */ 
/*      */   
/*  602 */   OfflineNotice((short)10004, OfflineNoticeProcessor.class, OfflineNoticeRequest.class),
/*      */ 
/*      */   
/*  605 */   RapidCombat((short)10008, RapidCombatProcessor.class, RapidCombatRequest.class),
/*      */ 
/*      */   
/*  608 */   RapidCombatInfo((short)10009, RapidCombatInfoProcessor.class, RapidCombatInfoRequest.class),
/*      */ 
/*      */   
/*  611 */   TimeSync((short)10005, TimeSyncProcessor.class, TimeSyncRequest.class),
/*      */ 
/*      */   
/*  614 */   MailExtract((short)10503, MailExtractProcessor.class, MailExtractRequest.class),
/*      */ 
/*      */   
/*  617 */   MailList((short)10501, MailListProcessor.class, MailListRequest.class),
/*      */ 
/*      */   
/*  620 */   MailNotice((short)10505, MailNoticeProcessor.class, MailNoticeRequest.class),
/*      */ 
/*      */   
/*  623 */   MailRead((short)10502, MailReadProcessor.class, MailReadRequest.class),
/*      */ 
/*      */   
/*  626 */   AddPartnerNotice((short)13307, AddPartnerNoticeProcessor.class, AddPartnerNoticeRequest.class),
/*      */ 
/*      */   
/*  629 */   BattlePartner((short)13303, BattlePartnerProcessor.class, BattlePartnerRequest.class),
/*      */ 
/*      */   
/*  632 */   GetBattleList((short)13311, GetBattleListProcessor.class, GetBattleListRequest.class),
/*      */ 
/*      */   
/*  635 */   GetHandbookReward((short)13309, GetHandbookRewardProcessor.class, GetHandbookRewardRequest.class),
/*      */ 
/*      */   
/*  638 */   PartnerBreach((short)13305, PartnerBreachProcessor.class, PartnerBreachRequest.class),
/*      */ 
/*      */   
/*  641 */   PartnerHandbookInfo((short)13310, PartnerHandbookInfoProcessor.class, PartnerHandbookInfoRequest.class),
/*      */ 
/*      */   
/*  644 */   PartnerList((short)13301, PartnerListProcessor.class, PartnerListRequest.class),
/*      */ 
/*      */   
/*  647 */   PartnerStarUp((short)13306, PartnerStarUpProcessor.class, PartnerStarUpRequest.class),
/*      */ 
/*      */   
/*  650 */   PartnerUpgrade((short)13304, PartnerUpgradeProcessor.class, PartnerUpgradeRequest.class),
/*      */ 
/*      */   
/*  653 */   PushPartnerInfo((short)13308, PushPartnerInfoProcessor.class, PushPartnerInfoRequest.class),
/*      */ 
/*      */   
/*  656 */   RankingInfo((short)17001, RankingInfoProcessor.class, RankingInfoRequest.class),
/*      */ 
/*      */   
/*  659 */   Worship((short)17002, WorshipProcessor.class, WorshipRequest.class),
/*      */ 
/*      */   
/*  662 */   BuyRedRecruitItem((short)14005, BuyRedRecruitItemProcessor.class, BuyRedRecruitItemRequest.class),
/*      */ 
/*      */   
/*  665 */   GetRecruitInfo((short)14002, GetRecruitInfoProcessor.class, GetRecruitInfoRequest.class),
/*      */ 
/*      */   
/*  668 */   RedRecruitBox((short)14006, RedRecruitBoxProcessor.class, RedRecruitBoxRequest.class),
/*      */ 
/*      */   
/*  671 */   RedRecruitInfo((short)14003, RedRecruitInfoProcessor.class, RedRecruitInfoRequest.class),
/*      */ 
/*      */   
/*  674 */   RedRecruitRefresh((short)14004, RedRecruitRefreshProcessor.class, RedRecruitRefreshRequest.class),
/*      */ 
/*      */   
/*  677 */   StartRecruit((short)14001, StartRecruitProcessor.class, StartRecruitRequest.class),
/*      */ 
/*      */   
/*  680 */   ActivateRecordStar((short)15403, ActivateRecordStarProcessor.class, ActivateRecordStarRequest.class),
/*      */ 
/*      */   
/*  683 */   SanGuoZhiInfo((short)15401, SanGuoZhiInfoProcessor.class, SanGuoZhiInfoRequest.class),
/*      */ 
/*      */   
/*  686 */   SanGuoZhiTaskReward((short)15402, SanGuoZhiTaskRewardProcessor.class, SanGuoZhiTaskRewardRequest.class),
/*      */ 
/*      */   
/*  689 */   BuyGoods((short)13102, BuyGoodsProcessor.class, BuyGoodsRequest.class),
/*      */ 
/*      */   
/*  692 */   MysteryRefresh((short)14203, MysteryRefreshProcessor.class, MysteryRefreshRequest.class),
/*      */ 
/*      */   
/*  695 */   MysteryShopList((short)14201, MysteryShopListProcessor.class, MysteryShopListRequest.class),
/*      */ 
/*      */   
/*  698 */   RoutineShopList((short)14601, RoutineShopListProcessor.class, RoutineShopListRequest.class),
/*      */ 
/*      */   
/*  701 */   NormalInsInfo((short)13001, NormalInsInfoProcessor.class, NormalInsInfoRequest.class),
/*      */ 
/*      */   
/*  704 */   NormalInsSweep((short)13002, NormalInsSweepProcessor.class, NormalInsSweepRequest.class),
/*      */ 
/*      */   
/*  707 */   SkillLevelUp((short)10903, SkillLevelUpProcessor.class, SkillLevelUpRequest.class),
/*      */ 
/*      */   
/*  710 */   SkillList((short)10901, SkillListProcessor.class, SkillListRequest.class),
/*      */ 
/*      */   
/*  713 */   SkillNew((short)10902, SkillNewProcessor.class, SkillNewRequest.class),
/*      */ 
/*      */   
/*  716 */   GetChapterBox((short)12203, GetChapterBoxProcessor.class, GetChapterBoxRequest.class),
/*      */ 
/*      */   
/*  719 */   TaskComplete((short)12202, TaskCompleteProcessor.class, TaskCompleteRequest.class),
/*      */ 
/*      */   
/*  722 */   TaskDailyInfo((short)12210, TaskDailyInfoProcessor.class, TaskDailyInfoRequest.class),
/*      */ 
/*      */   
/*  725 */   TaskDailyReward((short)12211, TaskDailyRewardProcessor.class, TaskDailyRewardRequest.class),
/*      */ 
/*      */   
/*  728 */   TaskInfo((short)12201, TaskInfoProcessor.class, TaskInfoRequest.class),
/*      */ 
/*      */   
/*  731 */   GetAttrbuteInfo((short)14417, GetAttrbuteInfoProcessor.class, GetAttrbuteInfoRequest.class),
/*      */ 
/*      */   
/*  734 */   GetBoxReward((short)14415, GetBoxRewardProcessor.class, GetBoxRewardRequest.class),
/*      */ 
/*      */   
/*  737 */   GetUnparalleledInfo((short)14408, GetUnparalleledInfoProcessor.class, GetUnparalleledInfoRequest.class),
/*      */ 
/*      */   
/*  740 */   GetUnparalleledRank((short)14411, GetUnparalleledRankProcessor.class, GetUnparalleledRankRequest.class),
/*      */ 
/*      */   
/*  743 */   ResetUnparalleled((short)14409, ResetUnparalleledProcessor.class, ResetUnparalleledRequest.class),
/*      */ 
/*      */   
/*  746 */   SelectUnparalleledAttrbute((short)14414, SelectUnparalleledAttrbuteProcessor.class, SelectUnparalleledAttrbuteRequest.class),
/*      */ 
/*      */   
/*  749 */   SweepUnparalleled((short)14410, SweepUnparalleledProcessor.class, SweepUnparalleledRequest.class),
/*      */ 
/*      */   
/*  752 */   ActivityWarPet((short)16402, ActivityWarPetProcessor.class, ActivityWarPetRequest.class),
/*      */ 
/*      */   
/*  755 */   ActWarPetHandBook((short)16407, ActWarPetHandBookProcessor.class, ActWarPetHandBookRequest.class),
/*      */ 
/*      */   
/*  758 */   WarPetBattle((short)16403, WarPetBattleProcessor.class, WarPetBattleRequest.class),
/*      */ 
/*      */   
/*  761 */   WarPetHandBookInfo((short)16406, WarPetHandBookInfoProcessor.class, WarPetHandBookInfoRequest.class),
/*      */ 
/*      */   
/*  764 */   WarPetInfo((short)16401, WarPetInfoProcessor.class, WarPetInfoRequest.class),
/*      */ 
/*      */   
/*  767 */   WarPetStarUp((short)16405, WarPetStarUpProcessor.class, WarPetStarUpRequest.class),
/*      */ 
/*      */   
/*  770 */   WarPetUpgrade((short)16404, WarPetUpgradeProcessor.class, WarPetUpgradeRequest.class),
/*      */ 
/*      */   
/*  773 */   BagReborn((short)10707, BagRebornProcessor.class, BagRebornRequest.class),
/*      */ 
/*      */   
/*  776 */   BagRecovery((short)10708, BagRecoveryProcessor.class, BagRecoveryRequest.class),
/*      */ 
/*      */   
/*  779 */   GetRebornList((short)10711, GetRebornListProcessor.class, GetRebornListRequest.class),
/*      */ 
/*      */   
/*  782 */   BossBuyTime((short)10310, BossBuyTimeProcessor.class, BossBuyTimeRequest.class),
/*      */ 
/*      */   
/*  785 */   EquipDataSys((short)10815, EquipDataSysProcessor.class, EquipDataSysRequest.class),
/*      */ 
/*      */   
/*  788 */   EquipStoneUp((short)10813, EquipStoneUpProcessor.class, EquipStoneUpRequest.class),
/*      */ 
/*      */   
/*  791 */   EquipZhuUp((short)10814, EquipZhuUpProcessor.class, EquipZhuUpRequest.class),
/*      */ 
/*      */   
/*  794 */   GeneralBoxReward((short)11204, GeneralBoxRewardProcessor.class, GeneralBoxRewardRequest.class),
/*      */ 
/*      */   
/*  797 */   GeneralBuyOrder((short)11205, GeneralBuyOrderProcessor.class, GeneralBuyOrderRequest.class),
/*      */ 
/*      */   
/*  800 */   GeneralChapterInfo((short)11202, GeneralChapterInfoProcessor.class, GeneralChapterInfoRequest.class),
/*      */ 
/*      */   
/*  803 */   GeneralPointReset((short)11206, GeneralPointResetProcessor.class, GeneralPointResetRequest.class),
/*      */ 
/*      */   
/*  806 */   GeneralPointSweep((short)11203, GeneralPointSweepProcessor.class, GeneralPointSweepRequest.class),
/*      */ 
/*      */   
/*  809 */   GeneralStarRank((short)11207, GeneralStarRankProcessor.class, GeneralStarRankRequest.class),
/*      */ 
/*      */   
/*  812 */   GeneralTotalInfo((short)11201, GeneralTotalInfoProcessor.class, GeneralTotalInfoRequest.class),
/*      */ 
/*      */   
/*  815 */   VipInfo((short)10012, VipInfoProcessor.class, VipInfoRequest.class),
/*      */ 
/*      */   
/*  818 */   VipReward((short)10013, VipRewardProcessor.class, VipRewardRequest.class),
/*      */ 
/*      */   
/*  821 */   TaskGuide((short)12208, TaskGuideProcessor.class, TaskGuideRequest.class),
/*      */ 
/*      */   
/*  824 */   WelfareVipDaily((short)11902, WelfareVipDailyProcessor.class, WelfareVipDailyRequest.class),
/*      */ 
/*      */   
/*  827 */   WelfareVipInfo((short)11901, WelfareVipInfoProcessor.class, WelfareVipInfoRequest.class),
/*      */ 
/*      */   
/*  830 */   WelfareVipWeek((short)11903, WelfareVipWeekProcessor.class, WelfareVipWeekRequest.class),
/*      */ 
/*      */   
/*  833 */   BuyBossTimes((short)13005, BuyBossTimesProcessor.class, BuyBossTimesRequest.class),
/*      */ 
/*      */   
/*  836 */   SingleBossInfo((short)13003, SingleBossInfoProcessor.class, SingleBossInfoRequest.class),
/*      */ 
/*      */   
/*  839 */   SingleBossSweep((short)13006, SingleBossSweepProcessor.class, SingleBossSweepRequest.class),
/*      */ 
/*      */   
/*  842 */   RankActInfo((short)12002, RankActInfoProcessor.class, RankActInfoRequest.class),
/*      */ 
/*      */   
/*  845 */   RankActList((short)12003, RankActListProcessor.class, RankActListRequest.class),
/*      */ 
/*      */   
/*  848 */   RankActNotice((short)12001, RankActNoticeProcessor.class, RankActNoticeRequest.class),
/*      */ 
/*      */   
/*  851 */   RankActReward((short)12004, RankActRewardProcessor.class, RankActRewardRequest.class),
/*      */ 
/*      */   
/*  854 */   ContinueRechargeInfo((short)12102, ContinueRechargeInfoProcessor.class, ContinueRechargeInfoRequest.class),
/*      */ 
/*      */   
/*  857 */   ContinueRechargeNotice((short)12101, ContinueRechargeNoticeProcessor.class, ContinueRechargeNoticeRequest.class),
/*      */ 
/*      */   
/*  860 */   ContinueRechargeReward((short)12103, ContinueRechargeRewardProcessor.class, ContinueRechargeRewardRequest.class),
/*      */   
/*  862 */   ChargeInfo((short)11601, ChargeInfoProcessor.class, ChargeInfoRequest.class),
/*      */ 
/*      */   
/*  865 */   FirstChargeReward((short)11602, FirstChargeRewardProcessor.class, FirstChargeRewardRequest.class),
/*      */ 
/*      */   
/*  868 */   BagMultiUseItem((short)10713, BagMultiUseItemProcessor.class, BagMultiUseItemRequest.class),
/*      */ 
/*      */   
/*  871 */   LimitActInfo((short)12702, LimitActInfoProcessor.class, LimitActInfoRequest.class),
/*      */ 
/*      */   
/*  874 */   LimitActNotice((short)12701, LimitActNoticeProcessor.class, LimitActNoticeRequest.class),
/*      */ 
/*      */   
/*  877 */   LimitActRed((short)12704, LimitActRedProcessor.class, LimitActRedRequest.class),
/*      */ 
/*      */   
/*  880 */   LimitActReward((short)12703, LimitActRewardProcessor.class, LimitActRewardRequest.class),
/*      */ 
/*      */   
/*  883 */   GetInvitationReward((short)15002, GetInvitationRewardProcessor.class, GetInvitationRewardRequest.class),
/*      */ 
/*      */   
/*  886 */   InvitationInfo((short)15001, InvitationInfoProcessor.class, InvitationInfoRequest.class),
/*      */ 
/*      */   
/*  889 */   NormalInvitationFinish((short)15003, NormalInvitationFinishProcessor.class, NormalInvitationFinishRequest.class),
/*      */ 
/*      */   
/*  892 */   RecruitInvitationFinish((short)15004, RecruitInvitationFinishProcessor.class, RecruitInvitationFinishRequest.class),
/*      */ 
/*      */   
/*  895 */   GetSignReward((short)15202, GetSignRewardProcessor.class, GetSignRewardRequest.class),
/*      */ 
/*      */   
/*  898 */   SignInfo((short)15201, SignInfoProcessor.class, SignInfoRequest.class),
/*      */ 
/*      */   
/*  901 */   SignToday((short)15203, SignTodayProcessor.class, SignTodayRequest.class),
/*      */ 
/*      */   
/*  904 */   BuyLevelPackage((short)15207, BuyLevelPackageProcessor.class, BuyLevelPackageRequest.class),
/*      */ 
/*      */   
/*  907 */   LevelPackageInfo((short)15206, LevelPackageInfoProcessor.class, LevelPackageInfoRequest.class),
/*      */ 
/*      */   
/*  910 */   MonthCardInfo((short)11904, MonthCardInfoProcessor.class, MonthCardInfoRequest.class),
/*      */ 
/*      */   
/*  913 */   MonthCardReward((short)11905, MonthCardRewardProcessor.class, MonthCardRewardRequest.class),
/*      */ 
/*      */   
/*  916 */   TitleActivate((short)13802, TitleActivateProcessor.class, TitleActivateRequest.class),
/*      */ 
/*      */   
/*  919 */   TitleDelay((short)13804, TitleDelayProcessor.class, TitleDelayRequest.class),
/*      */ 
/*      */   
/*  922 */   TitleInfo((short)13801, TitleInfoProcessor.class, TitleInfoRequest.class),
/*      */ 
/*      */   
/*  925 */   TitleWear((short)13803, TitleWearProcessor.class, TitleWearRequest.class),
/*      */ 
/*      */   
/*  928 */   BuyGrowFund((short)11908, BuyGrowFundProcessor.class, BuyGrowFundRequest.class),
/*      */ 
/*      */   
/*  931 */   DailyShopInfo((short)11909, DailyShopInfoProcessor.class, DailyShopInfoRequest.class),
/*      */ 
/*      */   
/*  934 */   GetGrowFundReward((short)11907, GetGrowFundRewardProcessor.class, GetGrowFundRewardRequest.class),
/*      */ 
/*      */   
/*  937 */   GrowFundInfo((short)11906, GrowFundInfoProcessor.class, GrowFundInfoRequest.class),
/*      */ 
/*      */   
/*  940 */   TaskCompleteNotice((short)12204, TaskCompleteNoticeProcessor.class, TaskCompleteNoticeRequest.class),
/*      */ 
/*      */   
/*  943 */   PushDailyShopInfo((short)11910, PushDailyShopInfoProcessor.class, PushDailyShopInfoRequest.class),
/*      */ 
/*      */   
/*  946 */   FriendRecommend((short)13421, FriendRecommendProcessor.class, FriendRecommendRequest.class),
/*      */ 
/*      */   
/*  949 */   FriendSerachTwo((short)13420, FriendSerachTwoProcessor.class, FriendSerachTwoRequest.class),
/*      */ 
/*      */   
/*  952 */   FortuneCatInfo((short)18002, FortuneCatInfoProcessor.class, FortuneCatInfoRequest.class),
/*      */ 
/*      */   
/*  955 */   FortuneCatList((short)18004, FortuneCatListProcessor.class, FortuneCatListRequest.class),
/*      */ 
/*      */   
/*  958 */   FortuneCatNotice((short)18001, FortuneCatNoticeProcessor.class, FortuneCatNoticeRequest.class),
/*      */ 
/*      */   
/*  961 */   FortuneCatPlay((short)18003, FortuneCatPlayProcessor.class, FortuneCatPlayRequest.class),
/*      */ 
/*      */   
/*  964 */   FortuneCatPush((short)18005, FortuneCatPushProcessor.class, FortuneCatPushRequest.class),
/*      */ 
/*      */   
/*  967 */   RechargeActInfo((short)12301, RechargeActInfoProcessor.class, RechargeActInfoRequest.class),
/*      */ 
/*      */   
/*  970 */   RechargeActNotice((short)12303, RechargeActNoticeProcessor.class, RechargeActNoticeRequest.class),
/*      */ 
/*      */   
/*  973 */   RechargeActReward((short)12302, RechargeActRewardProcessor.class, RechargeActRewardRequest.class),
/*      */ 
/*      */   
/*  976 */   FashionActivate((short)13902, FashionActivateProcessor.class, FashionActivateRequest.class),
/*      */ 
/*      */   
/*  979 */   FashionInfo((short)13901, FashionInfoProcessor.class, FashionInfoRequest.class),
/*      */ 
/*      */   
/*  982 */   FashionTakeOff((short)13904, FashionTakeOffProcessor.class, FashionTakeOffRequest.class),
/*      */ 
/*      */   
/*  985 */   FashionWear((short)13903, FashionWearProcessor.class, FashionWearRequest.class),
/*      */ 
/*      */   
/*  988 */   FashionStrengthen((short)13905, FashionStrengthenProcessor.class, FashionStrengthenRequest.class),
/*      */ 
/*      */   
/*  991 */   RankBossBornNotice((short)10312, RankBossBornNoticeProcessor.class, RankBossBornNoticeRequest.class),
/*      */ 
/*      */   
/*  994 */   RankBossSetting((short)10311, RankBossSettingProcessor.class, RankBossSettingRequest.class),
/*      */ 
/*      */   
/*  997 */   RankBossSettingInfo((short)10313, RankBossSettingInfoProcessor.class, RankBossSettingInfoRequest.class),
/*      */ 
/*      */   
/* 1000 */   ConvenientInvite((short)13706, ConvenientInviteProcessor.class, ConvenientInviteRequest.class),
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1006 */   JoinTeam((short)13703, JoinTeamProcessor.class, JoinTeamRequest.class),
/*      */ 
/*      */   
/* 1009 */   KickoutTeam((short)13705, KickoutTeamProcessor.class, KickoutTeamRequest.class),
/*      */ 
/*      */   
/* 1012 */   SendFighter((short)13707, SendFighterProcessor.class, SendFighterRequest.class),
/*      */ 
/*      */   
/* 1015 */   TeamFighterChangeNotice((short)13709, TeamFighterChangeNoticeProcessor.class, TeamFighterChangeNoticeRequest.class),
/*      */ 
/*      */   
/* 1018 */   TeamInfo((short)13704, TeamInfoProcessor.class, TeamInfoRequest.class),
/*      */ 
/*      */   
/* 1021 */   TeamKickoutNotice((short)13710, TeamKickoutNoticeProcessor.class, TeamKickoutNoticeRequest.class),
/*      */ 
/*      */   
/* 1024 */   TeamListInfo((short)13701, TeamListInfoProcessor.class, TeamListInfoRequest.class),
/*      */ 
/*      */   
/* 1027 */   LeaveTeam((short)13711, LeaveTeamProcessor.class, LeaveTeamRequest.class),
/*      */ 
/*      */   
/* 1030 */   TeamPlayerDataNotice((short)13712, TeamPlayerDataNoticeProcessor.class, TeamPlayerDataNoticeRequest.class),
/*      */ 
/*      */   
/* 1033 */   TeamPlayerFighterInfo((short)13708, TeamPlayerFighterInfoProcessor.class, TeamPlayerFighterInfoRequest.class),
/*      */ 
/*      */   
/* 1036 */   TeamPlayerReady((short)13713, TeamPlayerReadyProcessor.class, TeamPlayerReadyRequest.class),
/*      */ 
/*      */   
/* 1039 */   TeamPlayerReadyNotice((short)13714, TeamPlayerReadyNoticeProcessor.class, TeamPlayerReadyNoticeRequest.class),
/*      */ 
/*      */   
/* 1042 */   TowerInfo((short)16801, TowerInfoProcessor.class, TowerInfoRequest.class),
/*      */ 
/*      */   
/* 1045 */   TowerReward((short)16802, TowerRewardProcessor.class, TowerRewardRequest.class),
/*      */ 
/*      */   
/* 1048 */   MountsActivity((short)19002, MountsActivityProcessor.class, MountsActivityRequest.class),
/*      */ 
/*      */   
/* 1051 */   MountsBreak((short)19006, MountsBreakProcessor.class, MountsBreakRequest.class),
/*      */ 
/*      */   
/* 1054 */   MountsInfo((short)19001, MountsInfoProcessor.class, MountsInfoRequest.class),
/*      */ 
/*      */   
/* 1057 */   MountsRide((short)19003, MountsRideProcessor.class, MountsRideRequest.class),
/*      */ 
/*      */   
/* 1060 */   MountsStarUp((short)19005, MountsStarUpProcessor.class, MountsStarUpRequest.class),
/*      */ 
/*      */   
/* 1063 */   MoountsUpgrade((short)19004, MoountsUpgradeProcessor.class, MoountsUpgradeRequest.class),
/*      */ 
/*      */   
/* 1066 */   TowerRankInfo((short)16803, TowerRankInfoProcessor.class, TowerRankInfoRequest.class),
/*      */ 
/*      */   
/* 1069 */   LuckyTurntableDraw((short)13502, LuckyTurntableDrawProcessor.class, LuckyTurntableDrawRequest.class),
/*      */ 
/*      */   
/* 1072 */   LuckyTurntableInfo((short)13501, LuckyTurntableInfoProcessor.class, LuckyTurntableInfoRequest.class),
/*      */ 
/*      */   
/* 1075 */   ChangeNameAndSex((short)10018, ChangeNameAndSexProcessor.class, ChangeNameAndSexRequest.class),
/*      */ 
/*      */   
/* 1078 */   OnlineTimeReward((short)10016, OnlineTimeRewardProcessor.class, OnlineTimeRewardRequest.class),
/*      */ 
/*      */   
/* 1081 */   OnlineTimeRewardInfo((short)10017, OnlineTimeRewardInfoProcessor.class, OnlineTimeRewardInfoRequest.class),
/*      */ 
/*      */   
/* 1084 */   GetOneBuyReward((short)11912, GetOneBuyRewardProcessor.class, GetOneBuyRewardRequest.class),
/*      */ 
/*      */   
/* 1087 */   GroupBuyFundReward((short)11913, GroupBuyFundRewardProcessor.class, GroupBuyFundRewardRequest.class),
/*      */ 
/*      */   
/* 1090 */   OneBuyInfo((short)11911, OneBuyInfoProcessor.class, OneBuyInfoRequest.class),
/*      */ 
/*      */   
/* 1093 */   TurnplateDraw((short)12402, TurnplateDrawProcessor.class, TurnplateDrawRequest.class),
/*      */ 
/*      */   
/* 1096 */   TurnplateInfo((short)12401, TurnplateInfoProcessor.class, TurnplateInfoRequest.class),
/*      */ 
/*      */   
/* 1099 */   TurnplateNotice((short)12403, TurnplateNoticeProcessor.class, TurnplateNoticeRequest.class),
/*      */ 
/*      */   
/* 1102 */   TurnplateActNotice((short)12404, TurnplateActNoticeProcessor.class, TurnplateActNoticeRequest.class),
/*      */ 
/*      */   
/* 1105 */   GeneralGiftInfo((short)11914, GeneralGiftInfoProcessor.class, GeneralGiftInfoRequest.class),
/*      */ 
/*      */   
/* 1108 */   GetGeneralGiftReward((short)11915, GetGeneralGiftRewardProcessor.class, GetGeneralGiftRewardRequest.class),
/*      */ 
/*      */   
/* 1111 */   DestinyBattleRefresh((short)17405, DestinyBattleRefreshProcessor.class, DestinyBattleRefreshRequest.class),
/*      */ 
/*      */   
/* 1114 */   DestinyBattleInfo((short)17401, DestinyBattleInfoProcessor.class, DestinyBattleInfoRequest.class),
/*      */ 
/*      */   
/* 1117 */   DestinyBattleRank((short)17403, DestinyBattleRankProcessor.class, DestinyBattleRankRequest.class),
/*      */ 
/*      */   
/* 1120 */   DestinyBattleRecord((short)17402, DestinyBattleRecordProcessor.class, DestinyBattleRecordRequest.class),
/*      */ 
/*      */   
/* 1123 */   DestinyBattleTaskProgress((short)17406, DestinyBattleTaskProgressProcessor.class, DestinyBattleTaskProgressRequest.class),
/*      */ 
/*      */   
/* 1126 */   DestinyBattleTaskReward((short)17407, DestinyBattleTaskRewardProcessor.class, DestinyBattleTaskRewardRequest.class),
/*      */ 
/*      */   
/* 1129 */   DestinyBuyBattleTimes((short)17408, DestinyBuyBattleTimesProcessor.class, DestinyBuyBattleTimesRequest.class),
/*      */ 
/*      */   
/* 1132 */   DestinyBet((short)17413, DestinyBetProcessor.class, DestinyBetRequest.class),
/*      */ 
/*      */   
/* 1135 */   DestinyBetMatchInfo((short)17411, DestinyBetMatchInfoProcessor.class, DestinyBetMatchInfoRequest.class),
/*      */ 
/*      */   
/* 1138 */   DestinyBetNumInfo((short)17412, DestinyBetNumInfoProcessor.class, DestinyBetNumInfoRequest.class),
/*      */ 
/*      */   
/* 1141 */   DestinyBetRecordInfo((short)17414, DestinyBetRecordInfoProcessor.class, DestinyBetRecordInfoRequest.class),
/*      */ 
/*      */   
/* 1144 */   DestinyMatchGroupInfo((short)17409, DestinyMatchGroupInfoProcessor.class, DestinyMatchGroupInfoRequest.class),
/*      */ 
/*      */   
/* 1147 */   DestinyMyMatchInfo((short)17410, DestinyMyMatchInfoProcessor.class, DestinyMyMatchInfoRequest.class),
/*      */ 
/*      */ 
/*      */   
/* 1151 */   PartnerDestinyUp((short)13312, PartnerDestinyUpProcessor.class, PartnerDestinyUpRequest.class),
/*      */ 
/*      */   
/* 1154 */   NormalInsBuySweep((short)13007, NormalInsBuySweepProcessor.class, NormalInsBuySweepRequest.class),
/*      */ 
/*      */   
/* 1157 */   OneRechargeGet((short)11916, OneRechargeGetProcessor.class, OneRechargeGetRequest.class),
/*      */ 
/*      */   
/* 1160 */   OneRechargeInfo((short)11917, OneRechargeInfoProcessor.class, OneRechargeInfoRequest.class),
/*      */ 
/*      */   
/* 1163 */   GetChargeGroupReward((short)11603, GetChargeGroupRewardProcessor.class, GetChargeGroupRewardRequest.class),
/*      */   
/* 1165 */   LuckyTurntableOpen((short)13505, LuckyTurntableOpenProcessor.class, LuckyTurntableOpenRequest.class),
/*      */   
/* 1167 */   PetTurntableDraw((short)13506, PetTurntableDrawProcessor.class, PetTurntableDrawRequest.class),
/*      */ 
/*      */   
/* 1170 */   PetTurntableInfo((short)13504, PetTurntableInfoProcessor.class, PetTurntableInfoRequest.class),
/*      */ 
/*      */   
/* 1173 */   ConstantInfo((short)10015, ConstantInfoProcessor.class, ConstantInfoRequest.class),
/*      */ 
/*      */   
/* 1176 */   ConstantSetting((short)10014, ConstantSettingProcessor.class, ConstantSettingRequest.class),
/*      */ 
/*      */   
/* 1179 */   DrawCardClean((short)11803, DrawCardCleanProcessor.class, DrawCardCleanRequest.class),
/*      */ 
/*      */   
/* 1182 */   DrawCardInfo((short)11802, DrawCardInfoProcessor.class, DrawCardInfoRequest.class),
/*      */ 
/*      */   
/* 1185 */   DrawCardNotice((short)11801, DrawCardNoticeProcessor.class, DrawCardNoticeRequest.class),
/*      */ 
/*      */   
/* 1188 */   DrawCardPlay((short)11804, DrawCardPlayProcessor.class, DrawCardPlayRequest.class),
/*      */ 
/*      */   
/* 1191 */   DrawCardPush((short)11806, DrawCardPushProcessor.class, DrawCardPushRequest.class),
/*      */ 
/*      */   
/* 1194 */   DrawCardReward((short)11805, DrawCardRewardProcessor.class, DrawCardRewardRequest.class),
/*      */ 
/*      */   
/* 1197 */   RecruitRebateInfo((short)14007, RecruitRebateInfoProcessor.class, RecruitRebateInfoRequest.class),
/*      */ 
/*      */   
/* 1200 */   RecruitRebateReward((short)14008, RecruitRebateRewardProcessor.class, RecruitRebateRewardRequest.class),
/*      */ 
/*      */   
/* 1203 */   ChargeRebateInfo((short)14009, ChargeRebateInfoProcessor.class, ChargeRebateInfoRequest.class),
/*      */ 
/*      */   
/* 1206 */   ChargeRebateNotice((short)14010, ChargeRebateNoticeProcessor.class, ChargeRebateNoticeRequest.class),
/*      */ 
/*      */   
/* 1209 */   ChargeRebateReward((short)14011, ChargeRebateRewardProcessor.class, ChargeRebateRewardRequest.class),
/*      */ 
/*      */   
/* 1212 */   ChargeRebateTurn((short)14012, ChargeRebateTurnProcessor.class, ChargeRebateTurnRequest.class),
/*      */ 
/*      */   
/* 1215 */   GetPersonalRecord((short)14013, GetPersonalRecordProcessor.class, GetPersonalRecordRequest.class),
/*      */ 
/*      */   
/* 1218 */   PushServicRecord((short)14014, PushServicRecordProcessor.class, PushServicRecordRequest.class),
/*      */   
/* 1220 */   CardBookAskGift((short)12603, CardBookAskGiftProcessor.class, CardBookAskGiftRequest.class),
/*      */ 
/*      */   
/* 1223 */   CardBookColoring((short)12602, CardBookColoringProcessor.class, CardBookColoringRequest.class),
/*      */ 
/*      */   
/* 1226 */   CardBookGive((short)12604, CardBookGiveProcessor.class, CardBookGiveRequest.class),
/*      */ 
/*      */   
/* 1229 */   CardBookInfo((short)12601, CardBookInfoProcessor.class, CardBookInfoRequest.class),
/*      */ 
/*      */   
/* 1232 */   CardBookPairUpgrade((short)12606, CardBookPairUpgradeProcessor.class, CardBookPairUpgradeRequest.class),
/*      */ 
/*      */   
/* 1235 */   CardBookProcessReward((short)12605, CardBookProcessRewardProcessor.class, CardBookProcessRewardRequest.class),
/*      */ 
/*      */   
/* 1238 */   CardBookRankInfo((short)12607, CardBookRankInfoProcessor.class, CardBookRankInfoRequest.class),
/*      */ 
/*      */   
/* 1241 */   DestinyMatchInfo((short)17404, DestinyMatchInfoProcessor.class, DestinyMatchInfoRequest.class),
/*      */ 
/*      */   
/* 1244 */   DestinyMatchStateInfo((short)17415, DestinyMatchStateInfoProcessor.class, DestinyMatchStateInfoRequest.class),
/*      */ 
/*      */   
/* 1247 */   HelpInfoList((short)12502, HelpInfoListProcessor.class, HelpInfoListRequest.class),
/*      */ 
/*      */   
/* 1250 */   HelpOthers((short)12506, HelpOthersProcessor.class, HelpOthersRequest.class),
/*      */ 
/*      */   
/* 1253 */   MilitaryOfficeInfo((short)12501, MilitaryOfficeInfoProcessor.class, MilitaryOfficeInfoRequest.class),
/*      */ 
/*      */   
/* 1256 */   ResearchEnd((short)12504, ResearchEndProcessor.class, ResearchEndRequest.class),
/*      */ 
/*      */   
/* 1259 */   StartResearch((short)12503, StartResearchProcessor.class, StartResearchRequest.class),
/*      */ 
/*      */   
/* 1262 */   UseSpeedUpItem((short)12505, UseSpeedUpItemProcessor.class, UseSpeedUpItemRequest.class),
/*      */ 
/*      */   
/* 1265 */   GetDaliyChargeReward((short)11604, GetDaliyChargeRewardProcessor.class, GetDaliyChargeRewardRequest.class),
/*      */ 
/*      */   
/* 1268 */   BaguaBuyTreasure((short)13603, BaguaBuyTreasureProcessor.class, BaguaBuyTreasureRequest.class),
/*      */ 
/*      */   
/* 1271 */   BaguaChapterInfo((short)13601, BaguaChapterInfoProcessor.class, BaguaChapterInfoRequest.class),
/*      */ 
/*      */   
/* 1274 */   BaguaSweep((short)13604, BaguaSweepProcessor.class, BaguaSweepRequest.class),
/*      */ 
/*      */   
/* 1277 */   BaguaRecordInfo((short)13602, BaguaRecordInfoProcessor.class, BaguaRecordInfoRequest.class),
/*      */ 
/*      */   
/* 1280 */   GetPlayerBattleInfo((short)11116, GetPlayerBattleInfoProcessor.class, GetPlayerBattleInfoRequest.class),
/*      */ 
/*      */   
/* 1283 */   MilitaryAskForHelp((short)12507, MilitaryAskForHelpProcessor.class, MilitaryAskForHelpRequest.class),
/*      */ 
/*      */   
/* 1286 */   GroupDamageList((short)11117, GroupDamageListProcessor.class, GroupDamageListRequest.class),
/*      */ 
/*      */   
/* 1289 */   GroupInsInfo((short)11119, GroupInsInfoProcessor.class, GroupInsInfoRequest.class),
/*      */ 
/*      */   
/* 1292 */   GroupInsReward((short)11118, GroupInsRewardProcessor.class, GroupInsRewardRequest.class),
/*      */ 
/*      */   
/* 1295 */   GroupInsReset((short)11120, GroupInsResetProcessor.class, GroupInsResetRequest.class),
/*      */ 
/*      */   
/* 1298 */   GroupInsNotice((short)11121, GroupInsNoticeProcessor.class, GroupInsNoticeRequest.class),
/*      */ 
/*      */   
/* 1301 */   GroupInsRewardGet((short)11122, GroupInsRewardGetProcessor.class, GroupInsRewardGetRequest.class),
/*      */ 
/*      */   
/* 1304 */   ReviveRankBoss((short)10314, ReviveRankBossProcessor.class, ReviveRankBossRequest.class),
/*      */ 
/*      */   
/* 1307 */   ActiveCodeGet((short)11918, ActiveCodeGetProcessor.class, ActiveCodeGetRequest.class),
/*      */ 
/*      */   
/* 1310 */   ConsumeRebateInfo((short)15501, ConsumeRebateInfoProcessor.class, ConsumeRebateInfoRequest.class),
/*      */ 
/*      */   
/* 1313 */   ConsumeRebateNotice((short)15502, ConsumeRebateNoticeProcessor.class, ConsumeRebateNoticeRequest.class),
/*      */ 
/*      */   
/* 1316 */   ConsumeRebateReward((short)15503, ConsumeRebateRewardProcessor.class, ConsumeRebateRewardRequest.class),
/*      */ 
/*      */   
/* 1319 */   EquipArtificeUp((short)10816, EquipArtificeUpProcessor.class, EquipArtificeUpRequest.class),
/*      */ 
/*      */   
/* 1322 */   ActivityKungFu((short)15602, ActivityKungFuProcessor.class, ActivityKungFuRequest.class),
/*      */ 
/*      */   
/* 1325 */   KungFuBattle((short)15603, KungFuBattleProcessor.class, KungFuBattleRequest.class),
/*      */ 
/*      */   
/* 1328 */   KungFuInfo((short)15601, KungFuInfoProcessor.class, KungFuInfoRequest.class),
/*      */ 
/*      */   
/* 1331 */   KungFuStarUp((short)15605, KungFuStarUpProcessor.class, KungFuStarUpRequest.class),
/*      */ 
/*      */   
/* 1334 */   KungFuUpgrade((short)15604, KungFuUpgradeProcessor.class, KungFuUpgradeRequest.class),
/*      */ 
/*      */   
/* 1337 */   UpdateFightValue((short)19007, UpdateFightValueProcessor.class, UpdateFightValueRequest.class),
/*      */ 
/*      */   
/* 1340 */   ActivityStage((short)15609, ActivityStageProcessor.class, ActivityStageRequest.class),
/*      */ 
/*      */   
/* 1343 */   StageBattle((short)15610, StageBattleProcessor.class, StageBattleRequest.class),
/*      */ 
/*      */   
/* 1346 */   StageInfo((short)15608, StageInfoProcessor.class, StageInfoRequest.class),
/*      */ 
/*      */   
/* 1349 */   StageStarUp((short)15612, StageStarUpProcessor.class, StageStarUpRequest.class),
/*      */ 
/*      */   
/* 1352 */   StageUpgrade((short)15611, StageUpgradeProcessor.class, StageUpgradeRequest.class),
/*      */ 
/*      */   
/* 1355 */   ChangeHead((short)10019, ChangeHeadProcessor.class, ChangeHeadRequest.class),
/*      */ 
/*      */   
/* 1358 */   PushMilitaryHelp((short)12508, PushMilitaryHelpProcessor.class, PushMilitaryHelpRequest.class),
/*      */ 
/*      */   
/* 1361 */   CrossToken((short)11301, CrossTokenProcessor.class, CrossTokenRequest.class),
/*      */ 
/*      */   
/* 1364 */   TaskGetAskReward((short)12206, TaskGetAskRewardProcessor.class, TaskGetAskRewardRequest.class),
/*      */ 
/*      */   
/* 1367 */   TaskOpenAsk((short)12205, TaskOpenAskProcessor.class, TaskOpenAskRequest.class),
/*      */ 
/*      */   
/* 1370 */   LuckyTurntableRank((short)13507, LuckyTurntableRankProcessor.class, LuckyTurntableRankRequest.class),
/*      */ 
/*      */   
/* 1373 */   BagOpenTax((short)10714, BagOpenTaxProcessor.class, BagOpenTaxRequest.class),
/*      */ 
/*      */   
/* 1376 */   BagBuyGoldTax((short)10715, BagBuyGoldTaxProcessor.class, BagBuyGoldTaxRequest.class),
/*      */ 
/*      */   
/* 1379 */   DailiHaoLiInfo((short)11920, DailiHaoLiInfoProcessor.class, DailiHaoLiInfoRequest.class),
/*      */ 
/*      */   
/* 1382 */   DailiHaoLiNotice((short)11919, DailiHaoLiNoticeProcessor.class, DailiHaoLiNoticeRequest.class),
/*      */ 
/*      */   
/* 1385 */   DailiHaoLiReward((short)11921, DailiHaoLiRewardProcessor.class, DailiHaoLiRewardRequest.class),
/*      */ 
/*      */   
/* 1388 */   DivineDrawOne((short)14302, DivineDrawOneProcessor.class, DivineDrawOneRequest.class),
/*      */ 
/*      */   
/* 1391 */   DivineDrawTen((short)14303, DivineDrawTenProcessor.class, DivineDrawTenRequest.class),
/*      */ 
/*      */   
/* 1394 */   DivineInfo((short)14301, DivineInfoProcessor.class, DivineInfoRequest.class),
/*      */ 
/*      */   
/* 1397 */   DivineRankInfo((short)14304, DivineRankInfoProcessor.class, DivineRankInfoRequest.class),
/*      */ 
/*      */   
/* 1400 */   DivineRecordInfo((short)14305, DivineRecordInfoProcessor.class, DivineRecordInfoRequest.class),
/*      */ 
/*      */   
/* 1403 */   PartnerSoulLevel((short)13313, PartnerSoulLevelProcessor.class, PartnerSoulLevelRequest.class),
/*      */   
/* 1405 */   CrossRankActInfo((short)14102, CrossRankActInfoProcessor.class, CrossRankActInfoRequest.class),
/*      */ 
/*      */   
/* 1408 */   ActivityWarLineup((short)16501, ActivityWarLineupProcessor.class, ActivityWarLineupRequest.class),
/*      */ 
/*      */   
/* 1411 */   WarLineupBattle((short)16503, WarLineupBattleProcessor.class, WarLineupBattleRequest.class),
/*      */ 
/*      */   
/* 1414 */   WarLineupInfo((short)16502, WarLineupInfoProcessor.class, WarLineupInfoRequest.class),
/*      */ 
/*      */   
/* 1417 */   WarLineupStarUp((short)16504, WarLineupStarUpProcessor.class, WarLineupStarUpRequest.class),
/*      */ 
/*      */   
/* 1420 */   WarLineuprUpgrade((short)16505, WarLineuprUpgradeProcessor.class, WarLineuprUpgradeRequest.class),
/*      */ 
/*      */   
/* 1423 */   TaskPlatformReward((short)12212, TaskPlatformRewardProcessor.class, TaskPlatformRewardRequest.class),
/*      */   
/* 1425 */   ChannelGiftGet((short)12215, ChannelGiftGetProcessor.class, ChannelGiftGetRequest.class),
/*      */ 
/*      */   
/* 1428 */   QQBuyGoods((short)12214, QQBuyGoodsProcessor.class, QQBuyGoodsRequest.class),
/*      */ 
/*      */   
/* 1431 */   QQVIPDailyInfo((short)12213, QQVIPDailyInfoProcessor.class, QQVIPDailyInfoRequest.class),
/*      */ 
/*      */   
/* 1434 */   MentalLotteryInfo((short)17301, MentalLotteryInfoProcessor.class, MentalLotteryInfoRequest.class),
/*      */ 
/*      */   
/* 1437 */   RaffleLottery((short)17305, RaffleLotteryProcessor.class, RaffleLotteryRequest.class),
/*      */ 
/*      */   
/* 1440 */   MentalRank((short)17302, MentalRankProcessor.class, MentalRankRequest.class),
/*      */ 
/*      */   
/* 1443 */   MyMentalInfo((short)17303, MyMentalInfoProcessor.class, MyMentalInfoRequest.class),
/*      */ 
/*      */   
/* 1446 */   OpenMentalBox((short)17304, OpenMentalBoxProcessor.class, OpenMentalBoxRequest.class),
/*      */ 
/*      */   
/* 1449 */   PushLotteryRecord((short)17306, PushLotteryRecordProcessor.class, PushLotteryRecordRequest.class),
/*      */ 
/*      */   
/* 1452 */   NeutralBossInfo((short)10315, NeutralBossInfoProcessor.class, NeutralBossInfoRequest.class),
/*      */ 
/*      */   
/* 1455 */   NeutralBossPush((short)10316, NeutralBossPushProcessor.class, NeutralBossPushRequest.class),
/*      */ 
/*      */   
/* 1458 */   SoulsActOrStarUp((short)11209, SoulsActOrStarUpProcessor.class, SoulsActOrStarUpRequest.class),
/*      */ 
/*      */   
/* 1461 */   SoulsInfo((short)11208, SoulsInfoProcessor.class, SoulsInfoRequest.class),
/*      */ 
/*      */   
/* 1464 */   SoulsRecast((short)11211, SoulsRecastProcessor.class, SoulsRecastRequest.class),
/*      */ 
/*      */   
/* 1467 */   SoulsUpgrade((short)11210, SoulsUpgradeProcessor.class, SoulsUpgradeRequest.class),
/*      */ 
/*      */   
/* 1470 */   GroupChangeName((short)11123, GroupChangeNameProcessor.class, GroupChangeNameRequest.class),
/*      */ 
/*      */   
/* 1473 */   LuckyMoneyAdd((short)14307, LuckyMoneyAddProcessor.class, LuckyMoneyAddRequest.class),
/*      */ 
/*      */   
/* 1476 */   LuckyMoneyOpen((short)14306, LuckyMoneyOpenProcessor.class, LuckyMoneyOpenRequest.class),
/*      */ 
/*      */   
/* 1479 */   LuckyMoneyReceive((short)14308, LuckyMoneyReceiveProcessor.class, LuckyMoneyReceiveRequest.class),
/*      */ 
/*      */   
/* 1482 */   LuckyMoneyOpenInfo((short)14309, LuckyMoneyOpenInfoProcessor.class, LuckyMoneyOpenInfoRequest.class),
/*      */ 
/*      */   
/* 1485 */   YearBeastBossBuyTime((short)10327, YearBeastBossBuyTimeProcessor.class, YearBeastBossBuyTimeRequest.class),
/*      */ 
/*      */   
/* 1488 */   YearBeastBossInfo((short)10326, YearBeastBossInfoProcessor.class, YearBeastBossInfoRequest.class),
/*      */ 
/*      */   
/* 1491 */   YearBeastBossNotice((short)10328, YearBeastBossNoticeProcessor.class, YearBeastBossNoticeRequest.class),
/*      */ 
/*      */   
/* 1494 */   AssistInBattle((short)13314, AssistInBattleProcessor.class, AssistInBattleRequest.class),
/*      */ 
/*      */   
/* 1497 */   GetAssistInBattleList((short)13315, GetAssistInBattleListProcessor.class, GetAssistInBattleListRequest.class),
/*      */ 
/*      */   
/* 1500 */   WelfareVipGift((short)11922, WelfareVipGiftProcessor.class, WelfareVipGiftRequest.class),
/*      */ 
/*      */   
/* 1503 */   TalismanRankUp((short)10817, TalismanRankUpProcessor.class, TalismanRankUpRequest.class),
/*      */ 
/*      */   
/* 1506 */   TalismanUpOrDown((short)10818, TalismanUpOrDownProcessor.class, TalismanUpOrDownRequest.class),
/*      */ 
/*      */   
/* 1509 */   DeleteEquipNotice((short)10820, DeleteEquipNoticeProcessor.class, DeleteEquipNoticeRequest.class),
/*      */ 
/*      */   
/* 1512 */   EquipStarUp((short)10819, EquipStarUpProcessor.class, EquipStarUpRequest.class),
/*      */ 
/*      */   
/* 1515 */   TimeLimitBuyInfo((short)15209, TimeLimitBuyInfoProcessor.class, TimeLimitBuyInfoRequest.class),
/*      */ 
/*      */   
/* 1518 */   TimeLimitBuy((short)15210, TimeLimitBuyProcessor.class, TimeLimitBuyRequest.class),
/*      */ 
/*      */   
/* 1521 */   TimeLimitExchange((short)15211, TimeLimitExchangeProcessor.class, TimeLimitExchangeRequest.class),
/*      */ 
/*      */   
/* 1524 */   TimeLimitExchangeInfo((short)15212, TimeLimitExchangeInfoProcessor.class, TimeLimitExchangeInfoRequest.class),
/*      */ 
/*      */   
/* 1527 */   TimeLimitBuyOpen((short)15208, TimeLimitBuyOpenProcessor.class, TimeLimitBuyOpenRequest.class),
/*      */ 
/*      */ 
/*      */   
/* 1531 */   TimeLimitExchangeOpen((short)15213, TimeLimitExchangeOpenProcessor.class, TimeLimitExchangeOpenRequest.class),
/*      */ 
/*      */   
/* 1534 */   ArtifactActivate((short)13103, ArtifactActivateProcessor.class, ArtifactActivateRequest.class),
/*      */ 
/*      */   
/* 1537 */   ArtifactList((short)13105, ArtifactListProcessor.class, ArtifactListRequest.class),
/*      */ 
/*      */   
/* 1540 */   ArtifactSave((short)13106, ArtifactSaveProcessor.class, ArtifactSaveRequest.class),
/*      */ 
/*      */   
/* 1543 */   ArtifactTrain((short)13107, ArtifactTrainProcessor.class, ArtifactTrainRequest.class),
/*      */ 
/*      */   
/* 1546 */   RuneBagNotice((short)17802, RuneBagNoticeProcessor.class, RuneBagNoticeRequest.class),
/*      */ 
/*      */   
/* 1549 */   RuneBagOpen((short)17801, RuneBagOpenProcessor.class, RuneBagOpenRequest.class),
/*      */ 
/*      */   
/* 1552 */   RuneSysInfo((short)17804, RuneSysInfoProcessor.class, RuneSysInfoRequest.class),
/*      */ 
/*      */   
/* 1555 */   RuneSysLevelUp((short)17805, RuneSysLevelUpProcessor.class, RuneSysLevelUpRequest.class),
/*      */ 
/*      */   
/* 1558 */   RuneSysReplace((short)17806, RuneSysReplaceProcessor.class, RuneSysReplaceRequest.class),
/*      */ 
/*      */   
/* 1561 */   TaskPreviewReward((short)12216, TaskPreviewRewardProcessor.class, TaskPreviewRewardRequest.class),
/*      */ 
/*      */   
/* 1564 */   WanderBuy((short)15302, WanderBuyProcessor.class, WanderBuyRequest.class),
/*      */ 
/*      */   
/* 1567 */   WanderInfo((short)15301, WanderInfoProcessor.class, WanderInfoRequest.class),
/*      */ 
/*      */   
/* 1570 */   GroupRecruit((short)11124, GroupRecruitProcessor.class, GroupRecruitRequest.class),
/*      */ 
/*      */ 
/*      */   
/* 1574 */   CrossRankActList((short)14101, CrossRankActListProcessor.class, CrossRankActListRequest.class),
/*      */ 
/*      */   
/* 1577 */   CrossRankActNotice((short)14103, CrossRankActNoticeProcessor.class, CrossRankActNoticeRequest.class),
/*      */ 
/*      */   
/* 1580 */   CrossRankActReward((short)14104, CrossRankActRewardProcessor.class, CrossRankActRewardRequest.class),
/*      */ 
/*      */   
/* 1583 */   TabNotice((short)11011, TabNoticeProcessor.class, TabNoticeRequest.class),
/*      */ 
/*      */   
/* 1586 */   CrossRaceInfo((short)12801, CrossRaceInfoProcessor.class, CrossRaceInfoRequest.class),
/*      */ 
/*      */   
/* 1589 */   CrossRaceMatch((short)12802, CrossRaceMatchProcessor.class, CrossRaceMatchRequest.class),
/*      */ 
/*      */   
/* 1592 */   CrossRaceRank((short)12803, CrossRaceRankProcessor.class, CrossRaceRankRequest.class),
/*      */ 
/*      */   
/* 1595 */   CrossRaceRewardGet((short)12804, CrossRaceRewardGetProcessor.class, CrossRaceRewardGetRequest.class),
/*      */ 
/*      */   
/* 1598 */   TalismanDraw((short)12902, TalismanDrawProcessor.class, TalismanDrawRequest.class),
/*      */ 
/*      */   
/* 1601 */   TalismanInfo((short)12903, TalismanInfoProcessor.class, TalismanInfoRequest.class),
/*      */ 
/*      */   
/* 1604 */   TalismanNotice((short)12904, TalismanNoticeProcessor.class, TalismanNoticeRequest.class),
/*      */ 
/*      */   
/* 1607 */   TalismanRefresh((short)12901, TalismanRefreshProcessor.class, TalismanRefreshRequest.class),
/*      */ 
/*      */   
/* 1610 */   TowerOwnerFight((short)16702, TowerOwnerFightProcessor.class, TowerOwnerFightRequest.class),
/*      */ 
/*      */   
/* 1613 */   TowerOwnerInfo((short)16701, TowerOwnerInfoProcessor.class, TowerOwnerInfoRequest.class),
/*      */ 
/*      */   
/* 1616 */   TowerOwnerRank((short)16703, TowerOwnerRankProcessor.class, TowerOwnerRankRequest.class),
/*      */ 
/*      */   
/* 1619 */   TowerOwnerRecord((short)16704, TowerOwnerRecordProcessor.class, TowerOwnerRecordRequest.class),
/*      */ 
/*      */   
/* 1622 */   TowerOwnerMobai((short)16705, TowerOwnerMobaiProcessor.class, TowerOwnerMobaiRequest.class),
/*      */ 
/*      */   
/* 1625 */   TowerOwnerLayerState((short)16706, TowerOwnerLayerStateProcessor.class, TowerOwnerLayerStateRequest.class),
/*      */ 
/*      */   
/* 1628 */   TowerOwnerGiveUp((short)16707, TowerOwnerGiveUpProcessor.class, TowerOwnerGiveUpRequest.class),
/*      */ 
/*      */   
/* 1631 */   SecretiInfo((short)13201, SecretiInfoProcessor.class, SecretiInfoRequest.class),
/*      */ 
/*      */   
/* 1634 */   SecretiInsInfo((short)13202, SecretiInsInfoProcessor.class, SecretiInsInfoRequest.class),
/*      */ 
/*      */   
/* 1637 */   SecretiRewardInfo((short)13203, SecretiRewardInfoProcessor.class, SecretiRewardInfoRequest.class),
/*      */ 
/*      */   
/* 1640 */   SecretiRewardGet((short)13204, SecretiRewardGetProcessor.class, SecretiRewardGetRequest.class),
/*      */ 
/*      */   
/* 1643 */   RunewarFightersInfo((short)14502, RunewarFightersInfoProcessor.class, RunewarFightersInfoRequest.class),
/*      */   
/* 1645 */   RunewarInfo((short)14501, RunewarInfoProcessor.class, RunewarInfoRequest.class),
/*      */ 
/*      */   
/* 1648 */   RunewarRankInfo((short)14505, RunewarRankInfoProcessor.class, RunewarRankInfoRequest.class),
/*      */ 
/*      */   
/* 1651 */   RunewarRewardGet((short)14504, RunewarRewardGetProcessor.class, RunewarRewardGetRequest.class),
/*      */ 
/*      */   
/* 1654 */   RunewarRewardInfo((short)14503, RunewarRewardInfoProcessor.class, RunewarRewardInfoRequest.class),
/*      */ 
/*      */   
/* 1657 */   RunewarBattleRecord((short)14506, RunewarBattleRecordProcessor.class, RunewarBattleRecordRequest.class),
/*      */ 
/*      */   
/* 1660 */   RunewarRefresh((short)14507, RunewarRefreshProcessor.class, RunewarRefreshRequest.class),
/*      */ 
/*      */   
/* 1663 */   RuneRedNotice((short)17803, RuneRedNoticeProcessor.class, RuneRedNoticeRequest.class),
/*      */ 
/*      */   
/* 1666 */   ZodiacExchange((short)17501, ZodiacExchangeProcessor.class, ZodiacExchangeRequest.class),
/*      */ 
/*      */   
/* 1669 */   ZodiacInfo((short)17505, ZodiacInfoProcessor.class, ZodiacInfoRequest.class),
/*      */ 
/*      */   
/* 1672 */   ZodiacOpen((short)17502, ZodiacOpenProcessor.class, ZodiacOpenRequest.class),
/*      */ 
/*      */   
/* 1675 */   ZodiacReward((short)17503, ZodiacRewardProcessor.class, ZodiacRewardRequest.class),
/*      */ 
/*      */   
/* 1678 */   ZodiacTask((short)17504, ZodiacTaskProcessor.class, ZodiacTaskRequest.class),
/*      */ 
/*      */   
/* 1681 */   GetReincarnInfo((short)13316, GetReincarnInfoProcessor.class, GetReincarnInfoRequest.class),
/*      */ 
/*      */   
/* 1684 */   PartnerReincarn((short)13317, PartnerReincarnProcessor.class, PartnerReincarnRequest.class),
/*      */   
/* 1686 */   FindRewardInfo((short)12217, FindRewardInfoProcessor.class, FindRewardInfoRequest.class),
/*      */ 
/*      */   
/* 1689 */   FindRewardGet((short)12218, FindRewardGetProcessor.class, FindRewardGetRequest.class),
/*      */ 
/*      */   
/* 1692 */   GetBackGift((short)11923, GetBackGiftProcessor.class, GetBackGiftRequest.class);
/*      */ 
/*      */   
/*      */   private short msgCode;
/*      */ 
/*      */   
/*      */   private Class processor;
/*      */ 
/*      */   
/*      */   private Class request;
/*      */   
/*      */   private boolean isClose;
/*      */ 
/*      */   
/*      */   MsgProcessorRegister(short msgCode, Class processor, Class request) {
/* 1707 */     this.msgCode = msgCode;
/* 1708 */     this.processor = processor;
/* 1709 */     this.request = request;
/* 1710 */     this.isClose = false;
/*      */   }
/*      */ 
/*      */   
/*      */   public short getMsgCode() {
/* 1715 */     return this.msgCode;
/*      */   }
/*      */   
/*      */   private boolean isClose() {
/* 1719 */     return this.isClose;
/*      */   }
/*      */   
/*      */   private void setClose(boolean isClose) {
/* 1723 */     this.isClose = isClose;
/*      */   }
/*      */   
/*      */   public Class getMsgProcessor() {
/* 1727 */     return this.processor;
/*      */   }
/*      */   
/*      */   public Class getRequest() {
/* 1731 */     return this.request;
/*      */   }
/*      */   
/*      */   public void setRequest(Class request) {
/* 1735 */     this.request = request;
/*      */   }
/*      */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\MsgProcessorRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */