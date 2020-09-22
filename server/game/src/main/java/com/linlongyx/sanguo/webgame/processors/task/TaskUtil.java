/*      */ package com.linlongyx.sanguo.webgame.processors.task;
/*      */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*      */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*      */ import com.linlongyx.core.utils.TimeUtil;
/*      */ import com.linlongyx.sanguo.webgame.app.achieve.AchieveComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.arena.ArenaComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.crossRace.CrossRaceComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.eightGraphic.BaguaComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.mounts.MountsEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.offices.MilitaryOfficeComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.recruit.RecruitComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.secreti.SecretiComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.stage.StageComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.stage.StageEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.tower.TowerComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*      */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRuleBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.BaguaInsBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.BossHomeBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.CrossRankBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.DestinyWarBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.MainInsBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.MainTaskBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.MultiInsBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.PersonalInsBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.WushuangInsBean;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.ArenaParameter;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.CrossRaceParameter;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.TaskParameter;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.TeamParameter;
/*      */ import com.linlongyx.sanguo.webgame.constant.EquipPart;
/*      */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*      */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*      */ import com.linlongyx.sanguo.webgame.processors.equip.EquipUtil;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FindRewardData;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*      */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*      */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ 
/*      */ public class TaskUtil {
/*      */   public static long getSchedule(long playerId, int type, int targetId) {
/*   72 */     long num = 0L;
/*   73 */     if (type == TaskType.ReachLevel.getType()) {
/*   74 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*   75 */       if (null != playerComponent) {
/*   76 */         num = playerComponent.getLevel();
/*      */       }
/*   78 */     } else if (type == TaskType.WearEquip.getType()) {
/*   79 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*   80 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*   81 */       if (null != playerComponent && null != equipComponent) {
/*   82 */         Map<Integer, Long> playerEquips = playerComponent.getEquips();
/*   83 */         for (Map.Entry<Integer, Long> entry : playerEquips.entrySet()) {
/*   84 */           EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/*   85 */           if (null != equipEntity) {
/*   86 */             num = 1L;
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*   91 */     } else if (type == TaskType.QualityFighter.getType()) {
/*   92 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*   93 */       if (null != partnerComponent) {
/*   94 */         Map<String, IMapEntity> map = partnerComponent.getEntityMap();
/*   95 */         for (IMapEntity iMapEntity : map.values()) {
/*   96 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*   97 */           FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/*   98 */           if (null != fighterBean && fighterBean.getQuality() >= targetId) {
/*   99 */             num++;
/*      */           }
/*      */         } 
/*      */       } 
/*  103 */     } else if (type == TaskType.StarFighter.getType()) {
/*  104 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  105 */       if (null != partnerComponent) {
/*  106 */         Map<String, IMapEntity> map = partnerComponent.getEntityMap();
/*  107 */         for (IMapEntity iMapEntity : map.values()) {
/*  108 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  109 */           if (partnerEntity.getStars() >= targetId) {
/*  110 */             num++;
/*      */           }
/*      */         } 
/*      */       } 
/*  114 */     } else if (type == TaskType.BreakFighter.getType()) {
/*  115 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  116 */       if (null != partnerComponent) {
/*  117 */         Map<String, IMapEntity> map = partnerComponent.getEntityMap();
/*  118 */         for (IMapEntity iMapEntity : map.values()) {
/*  119 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  120 */           if (partnerEntity.getBreakthroughs() >= targetId) {
/*  121 */             num++;
/*      */           }
/*      */         } 
/*      */       } 
/*  125 */     } else if (type == TaskType.BattleFighter.getType()) {
/*  126 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  127 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  128 */       if (null != partnerComponent && null != playerComponent) {
/*  129 */         num++;
/*  130 */         ArrayList<Long> fighters = playerComponent.getFighters();
/*  131 */         for (Long id : fighters) {
/*  132 */           PartnerEntity partnerEntity = partnerComponent.getEntity(id.longValue());
/*  133 */           if (null == partnerEntity) {
/*      */             continue;
/*      */           }
/*  136 */           num++;
/*      */         } 
/*      */       } 
/*  139 */     } else if (type == TaskType.StrengthFighter.getType()) {
/*  140 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  141 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*  142 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  143 */       if (null != partnerComponent && null != playerComponent && null != equipComponent) {
/*  144 */         ArrayList<Long> fighters = playerComponent.getFighters();
/*  145 */         for (Long id : fighters) {
/*  146 */           if (id.longValue() == -1L) {
/*  147 */             Map<Integer, Long> playerEquips = playerComponent.getEquips();
/*  148 */             for (Map.Entry<Integer, Long> entry : playerEquips.entrySet()) {
/*  149 */               EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/*  150 */               if (null != equipEntity && !EquipUtil.isTreasure(equipEntity.getItemId()))
/*  151 */                 num += equipEntity.getStrengthLv(); 
/*      */             } 
/*      */             continue;
/*      */           } 
/*  155 */           PartnerEntity partnerEntity = partnerComponent.getEntity(id.longValue());
/*  156 */           if (null == partnerEntity) {
/*      */             continue;
/*      */           }
/*  159 */           Map<Integer, Long> equips = partnerEntity.getEquips();
/*  160 */           for (Map.Entry<Integer, Long> entry : equips.entrySet()) {
/*  161 */             EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/*  162 */             if (null != equipEntity && !EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  163 */               num += equipEntity.getStrengthLv();
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/*  169 */     } else if (type == TaskType.PurifyFighter.getType()) {
/*  170 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  171 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*  172 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  173 */       if (null != partnerComponent && null != playerComponent && null != equipComponent) {
/*  174 */         ArrayList<Long> fighters = playerComponent.getFighters();
/*  175 */         for (Long id : fighters) {
/*  176 */           if (id.longValue() == -1L) {
/*  177 */             Map<Integer, Long> playerEquips = playerComponent.getEquips();
/*  178 */             for (Map.Entry<Integer, Long> entry : playerEquips.entrySet()) {
/*  179 */               EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/*  180 */               if (null != equipEntity && !EquipUtil.isTreasure(equipEntity.getItemId()))
/*  181 */                 num += equipEntity.getRefineLv(); 
/*      */             } 
/*      */             continue;
/*      */           } 
/*  185 */           PartnerEntity partnerEntity = partnerComponent.getEntity(id.longValue());
/*  186 */           if (null == partnerEntity) {
/*      */             continue;
/*      */           }
/*  189 */           Map<Integer, Long> equips = partnerEntity.getEquips();
/*  190 */           for (Map.Entry<Integer, Long> entry : equips.entrySet()) {
/*  191 */             EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/*  192 */             if (null != equipEntity && !EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  193 */               num += equipEntity.getRefineLv();
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/*  199 */     } else if (type == TaskType.FightValue.getType()) {
/*  200 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  201 */       if (null != playerComponent) {
/*  202 */         num = playerComponent.getTotalValue();
/*      */       }
/*  204 */     } else if (type == TaskType.ArenaRank.getType()) {
/*  205 */       ArenaComponent arenaComponent = (ArenaComponent)LookUpService.getComponent(playerId, ArenaComponent.class);
/*  206 */       if (null != arenaComponent) {
/*  207 */         num = arenaComponent.getRank();
/*  208 */         if (0L == num) {
/*  209 */           ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/*  210 */           return arenaParameter.getDefaultRank();
/*      */         } 
/*      */       } 
/*  213 */     } else if (type == TaskType.TotalNormal.getType()) {
/*  214 */       SingleInsComponent singleInsComponent = (SingleInsComponent)LookUpService.getComponent(playerId, SingleInsComponent.class);
/*  215 */       if (null != singleInsComponent) {
/*  216 */         num = singleInsComponent.getTotalTime();
/*      */       }
/*  218 */     } else if (type == TaskType.ConsumeTotalCCY.getType()) {
/*  219 */       ExtendComponent extendComponent = (ExtendComponent)LookUpService.getComponent(playerId, ExtendComponent.class);
/*  220 */       if (null != extendComponent) {
/*  221 */         num = ((Long)extendComponent.getConsumeCurrency().getOrDefault(Integer.valueOf(CurrencyType.CCY.getType()), Long.valueOf(0L))).longValue();
/*      */       }
/*  223 */     } else if (type == TaskType.RecruitCCY.getType()) {
/*  224 */       RecruitComponent recruitComponent = (RecruitComponent)LookUpService.getComponent(playerId, RecruitComponent.class);
/*  225 */       if (null != recruitComponent) {
/*  226 */         num = recruitComponent.getTotalRecruitTimes();
/*      */       }
/*  228 */     } else if (type == TaskType.PartnerReachLevel.getType()) {
/*  229 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  230 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  231 */       if (null != partnerComponent && null != playerComponent) {
/*  232 */         ArrayList<Long> fighters = playerComponent.getFighters();
/*  233 */         for (Long id : fighters) {
/*  234 */           PartnerEntity partnerEntity = partnerComponent.getEntity(id.longValue());
/*  235 */           if (null == partnerEntity) {
/*      */             continue;
/*      */           }
/*  238 */           if (partnerEntity.getLevel() >= targetId) {
/*  239 */             num = 1L;
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*  244 */     } else if (type == TaskType.ChapterReward.getType()) {
/*  245 */       TaskComponent taskComponent = (TaskComponent)LookUpService.getComponent(playerId, TaskComponent.class);
/*  246 */       if (null != taskComponent) {
/*  247 */         Map<Integer, Integer> chapterReward = taskComponent.getChapterReward();
/*  248 */         for (Map.Entry<Integer, Integer> entry : chapterReward.entrySet()) {
/*  249 */           if (((Integer)entry.getKey()).intValue() == targetId) {
/*  250 */             num = 1L;
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*  255 */     } else if (type == TaskType.PlayerBreak.getType()) {
/*  256 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  257 */       if (null != playerComponent) {
/*  258 */         num = playerComponent.getBreakthroughs();
/*      */       }
/*  260 */     } else if (type == TaskType.PartnerEquipNum.getType()) {
/*  261 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  262 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*  263 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  264 */       if (null != partnerComponent && null != equipComponent && null != playerComponent) {
/*  265 */         int size = 0;
/*  266 */         for (Map.Entry<Integer, Long> entry : (Iterable<Map.Entry<Integer, Long>>)playerComponent.getEquips().entrySet()) {
/*  267 */           EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/*  268 */           if (null != equipEntity && !EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  269 */             size++;
/*      */           }
/*      */         } 
/*  272 */         if (size >= targetId) {
/*  273 */           num++;
/*      */         }
/*  275 */         for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/*  276 */           size = 0;
/*  277 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  278 */           if (null == partnerEntity) {
/*      */             continue;
/*      */           }
/*  281 */           for (Map.Entry<Integer, Long> entry : (Iterable<Map.Entry<Integer, Long>>)partnerEntity.getEquips().entrySet()) {
/*  282 */             EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/*  283 */             if (null != equipEntity && !EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  284 */               size++;
/*      */             }
/*      */           } 
/*  287 */           if (size >= targetId) {
/*  288 */             num++;
/*      */           }
/*      */         } 
/*      */       } 
/*  292 */     } else if (type == TaskType.EquipLevel.getType()) {
/*  293 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  294 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*  295 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  296 */       if (null != partnerComponent && null != equipComponent && null != playerComponent) {
/*  297 */         ArrayList<Long> fighters = playerComponent.getFighters();
/*  298 */         for (Long id : fighters) {
/*  299 */           if (id.longValue() == -1L) {
/*  300 */             for (Map.Entry<Integer, Long> entry : (Iterable<Map.Entry<Integer, Long>>)playerComponent.getEquips().entrySet()) {
/*  301 */               EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/*  302 */               if (null != equipEntity && equipEntity.getStrengthLv() >= targetId && !EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  303 */                 num = 1L;
/*      */               }
/*      */             } 
/*      */             continue;
/*      */           } 
/*  308 */           PartnerEntity partnerEntity = partnerComponent.getEntity(id.longValue());
/*  309 */           if (null == partnerEntity) {
/*      */             continue;
/*      */           }
/*  312 */           for (Map.Entry<Integer, Long> entry : (Iterable<Map.Entry<Integer, Long>>)partnerEntity.getEquips().entrySet()) {
/*  313 */             EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/*  314 */             if (null != equipEntity && equipEntity.getStrengthLv() >= targetId && !EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  315 */               num = 1L;
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } 
/*  322 */     } else if (type == TaskType.ChapterSanGuoZhi.getType()) {
/*  323 */       SanGuoZhiComponent sanGuoZhiComponent = (SanGuoZhiComponent)LookUpService.getComponent(playerId, SanGuoZhiComponent.class);
/*  324 */       SanGuoZhiEntity sanGuoZhiEntity = sanGuoZhiComponent.getEntity2(targetId);
/*  325 */       if (null != sanGuoZhiEntity && 
/*  326 */         sanGuoZhiEntity.isActivity() && sanGuoZhiEntity.getRecordStarId() == targetId) {
/*  327 */         num = 1L;
/*      */       }
/*      */     }
/*  330 */     else if (type == TaskType.UnparalleledNum.getType()) {
/*  331 */       UnparalleledComponent unparalleledComponent = (UnparalleledComponent)LookUpService.getComponent(playerId, UnparalleledComponent.class);
/*  332 */       if (null != unparalleledComponent) {
/*  333 */         num = unparalleledComponent.getChallengeNum();
/*      */       }
/*  335 */     } else if (type == TaskType.UnparalleledMaxPoint.getType()) {
/*  336 */       UnparalleledComponent unparalleledComponent = (UnparalleledComponent)LookUpService.getComponent(playerId, UnparalleledComponent.class);
/*  337 */       if (null != unparalleledComponent) {
/*  338 */         num = unparalleledComponent.getMaxPoint();
/*      */       }
/*  340 */     } else if (type == TaskType.GeneralChallenge.getType()) {
/*  341 */       GeneralComponent generalComponent = (GeneralComponent)LookUpService.getComponent(playerId, GeneralComponent.class);
/*  342 */       if (null != generalComponent) {
/*  343 */         num = generalComponent.getTotalNum();
/*      */       }
/*  345 */     } else if (type == TaskType.GeneralChapter.getType()) {
/*  346 */       GeneralComponent generalComponent = (GeneralComponent)LookUpService.getComponent(playerId, GeneralComponent.class);
/*  347 */       if (null != generalComponent)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  358 */         num = generalComponent.getStars().size();
/*      */       }
/*  360 */     } else if (type == TaskType.OpenGroupList.getType()) {
/*  361 */       GroupMemberComponent groupMemberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId, GroupMemberComponent.class);
/*  362 */       if (null != groupMemberComponent && groupMemberComponent.isListOpen()) {
/*  363 */         num = 1L;
/*      */       }
/*  365 */     } else if (type == TaskType.MainChapter.getType()) {
/*  366 */       TaskComponent taskComponent = (TaskComponent)LookUpService.getComponent(playerId, TaskComponent.class);
/*  367 */       int ins = taskComponent.getChapter();
/*  368 */       MainInsBean mainInsBean = (MainInsBean)JsonTableService.getJsonData(ins, MainInsBean.class);
/*  369 */       if (null != mainInsBean) {
/*  370 */         num = mainInsBean.getChapter();
/*      */       }
/*  372 */     } else if (type == TaskType.VipLevel.getType()) {
/*  373 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  374 */       num = playerComponent.getVip();
/*  375 */     } else if (type == TaskType.UpEquip.getType()) {
/*  376 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  377 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  378 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*  379 */       if (null != playerComponent) {
/*  380 */         for (Iterator<Long> iterator = playerComponent.getEquips().values().iterator(); iterator.hasNext(); ) { long equipId = ((Long)iterator.next()).longValue();
/*  381 */           EquipEntity equipEntity = equipComponent.getEquipEntity(equipId);
/*  382 */           if (null != equipEntity && !EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  383 */             num++;
/*      */           } }
/*      */       
/*      */       }
/*  387 */       if (null != partnerComponent) {
/*  388 */         for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/*  389 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  390 */           for (Iterator<Long> iterator = partnerEntity.getEquips().values().iterator(); iterator.hasNext(); ) { long equipId = ((Long)iterator.next()).longValue();
/*  391 */             EquipEntity equipEntity = equipComponent.getEquipEntity(equipId);
/*  392 */             if (null != equipEntity && !EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  393 */               num++;
/*      */             } }
/*      */         
/*      */         } 
/*      */       }
/*  398 */     } else if (type == TaskType.Weapon.getType()) {
/*  399 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  400 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  401 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*  402 */       List<Long> equipIds = new ArrayList<>();
/*  403 */       if (null != playerComponent) {
/*  404 */         equipIds.addAll(playerComponent.getEquips().values());
/*      */       }
/*  406 */       if (null != partnerComponent) {
/*  407 */         for (PartnerEntity partnerEntity : partnerComponent.getBattlePartner()) {
/*  408 */           equipIds.addAll(partnerEntity.getEquips().values());
/*      */         }
/*      */       }
/*  411 */       if (null != equipComponent) {
/*  412 */         for (Iterator<Long> iterator = equipIds.iterator(); iterator.hasNext(); ) { long equipId = ((Long)iterator.next()).longValue();
/*  413 */           EquipEntity equipEntity = equipComponent.getEquipEntity(equipId);
/*  414 */           if (null != equipEntity && EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  415 */             num += equipEntity.getRefineLv();
/*      */           } }
/*      */       
/*      */       }
/*  419 */     } else if (type == TaskType.XWeapon.getType()) {
/*  420 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  421 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  422 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*  423 */       List<Long> equipIds = new ArrayList<>();
/*  424 */       if (null != playerComponent) {
/*  425 */         equipIds.addAll(playerComponent.getEquips().values());
/*      */       }
/*  427 */       if (null != partnerComponent) {
/*  428 */         for (PartnerEntity partnerEntity : partnerComponent.getBattlePartner()) {
/*  429 */           equipIds.addAll(partnerEntity.getEquips().values());
/*      */         }
/*      */       }
/*  432 */       if (null != equipComponent) {
/*  433 */         for (Iterator<Long> iterator = equipIds.iterator(); iterator.hasNext(); ) { long equipId = ((Long)iterator.next()).longValue();
/*  434 */           EquipEntity equipEntity = equipComponent.getEquipEntity(equipId);
/*  435 */           if (null != equipEntity && equipEntity.getRefineLv() >= targetId && EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  436 */             num++;
/*      */           } }
/*      */       
/*      */       }
/*  440 */     } else if (type == TaskType.UpQuiatyEquip.getType()) {
/*  441 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  442 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  443 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*  444 */       List<Long> equipIds = new ArrayList<>();
/*  445 */       if (null != playerComponent) {
/*  446 */         equipIds.addAll(playerComponent.getEquips().values());
/*      */       }
/*  448 */       if (null != partnerComponent) {
/*  449 */         for (PartnerEntity partnerEntity : partnerComponent.getBattlePartner()) {
/*  450 */           equipIds.addAll(partnerEntity.getEquips().values());
/*      */         }
/*      */       }
/*  453 */       if (null != equipComponent) {
/*  454 */         for (Iterator<Long> iterator = equipIds.iterator(); iterator.hasNext(); ) { long equipId = ((Long)iterator.next()).longValue();
/*  455 */           EquipEntity equipEntity = equipComponent.getEquipEntity(equipId);
/*  456 */           if (null != equipEntity && !EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  457 */             ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  458 */             if (null != itemBean && 
/*  459 */               itemBean.getItemQuality() >= targetId) {
/*  460 */               num++;
/*      */             }
/*      */           }
/*      */            }
/*      */       
/*      */       }
/*  466 */     } else if (type == TaskType.UnparaMaxStar.getType()) {
/*  467 */       UnparalleledComponent unparalleledComponent = (UnparalleledComponent)LookUpService.getComponent(playerId, UnparalleledComponent.class);
/*  468 */       if (null != unparalleledComponent) {
/*  469 */         num = unparalleledComponent.getLastMaxStar();
/*      */       }
/*  471 */     } else if (type == TaskType.ActivityWarPet.getType()) {
/*  472 */       WarPetComponent warPetComponent = (WarPetComponent)LookUpService.getComponent(playerId, WarPetComponent.class);
/*  473 */       num = warPetComponent.getEntityMap().size();
/*  474 */     } else if (type == TaskType.PartnerEquipQ.getType()) {
/*  475 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  476 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  477 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*  478 */       int count = 0;
/*  479 */       if (null != playerComponent) {
/*  480 */         for (Iterator<Long> iterator = playerComponent.getEquips().values().iterator(); iterator.hasNext(); ) { long equipId = ((Long)iterator.next()).longValue();
/*  481 */           EquipEntity equipEntity = equipComponent.getEquipEntity(equipId);
/*  482 */           if (null != equipEntity && !EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  483 */             ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  484 */             if (null != itemBean && itemBean.getItemQuality() >= targetId) {
/*  485 */               count++;
/*      */             }
/*      */           }  }
/*      */       
/*      */       }
/*  490 */       if (count >= EquipPart.CLOTH.getPart()) {
/*  491 */         num++;
/*      */       }
/*  493 */       count = 0;
/*  494 */       if (null != partnerComponent) {
/*  495 */         for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/*  496 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  497 */           for (Iterator<Long> iterator = partnerEntity.getEquips().values().iterator(); iterator.hasNext(); ) { long equipId = ((Long)iterator.next()).longValue();
/*  498 */             EquipEntity equipEntity = equipComponent.getEquipEntity(equipId);
/*  499 */             if (null != equipEntity && !EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  500 */               ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  501 */               if (null != itemBean && itemBean.getItemQuality() >= targetId) {
/*  502 */                 count++;
/*      */               }
/*      */             }  }
/*      */           
/*  506 */           if (count >= EquipPart.CLOTH.getPart()) {
/*  507 */             num++;
/*      */           }
/*  509 */           count = 0;
/*      */         } 
/*      */       }
/*  512 */     } else if (type == TaskType.PEquipStr.getType()) {
/*  513 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  514 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  515 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*  516 */       int count = 0;
/*  517 */       for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  518 */         if (pid == -1L) {
/*  519 */           for (Iterator<Long> iterator2 = playerComponent.getEquips().values().iterator(); iterator2.hasNext(); ) { long equipId = ((Long)iterator2.next()).longValue();
/*  520 */             EquipEntity equipEntity = equipComponent.getEquipEntity(equipId);
/*  521 */             if (null != equipEntity && 
/*  522 */               !EquipUtil.isTreasure(equipEntity.getItemId()) && equipEntity.getStrengthLv() >= targetId) {
/*  523 */               count++;
/*      */             } }
/*      */ 
/*      */           
/*  527 */           if (count >= EquipPart.CLOTH.getPart())
/*  528 */             num++; 
/*      */           continue;
/*      */         } 
/*  531 */         count = 0;
/*  532 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  533 */         if (null == partnerEntity) {
/*      */           continue;
/*      */         }
/*  536 */         for (Iterator<Long> iterator1 = partnerEntity.getEquips().values().iterator(); iterator1.hasNext(); ) { long equipId = ((Long)iterator1.next()).longValue();
/*  537 */           EquipEntity equipEntity = equipComponent.getEquipEntity(equipId);
/*  538 */           if (null != equipEntity && 
/*  539 */             !EquipUtil.isTreasure(equipEntity.getItemId()) && equipEntity.getStrengthLv() >= targetId) {
/*  540 */             count++;
/*      */           } }
/*      */ 
/*      */         
/*  544 */         if (count >= EquipPart.CLOTH.getPart()) {
/*  545 */           num++;
/*      */         } }
/*      */ 
/*      */     
/*  549 */     } else if (type == TaskType.PetTotalStar.getType()) {
/*  550 */       WarPetComponent warPetComponent = (WarPetComponent)LookUpService.getComponent(playerId, WarPetComponent.class);
/*  551 */       for (IMapEntity iMapEntity : warPetComponent.getEntityMap().values()) {
/*  552 */         WarPetEntity warPetEntity = (WarPetEntity)iMapEntity;
/*  553 */         num += warPetEntity.getStar();
/*      */       } 
/*  555 */     } else if (type == TaskType.FinishMainTask.getType()) {
/*  556 */       TaskComponent taskComponent = (TaskComponent)LookUpService.getComponent(playerId, TaskComponent.class);
/*  557 */       if (taskComponent.getId() == 1) {
/*  558 */         num = 0L;
/*      */       } else {
/*  560 */         num = (taskComponent.getId() / 10 - 1);
/*      */       } 
/*  562 */     } else if (type == TaskType.AchieveDone.getType()) {
/*  563 */       AchieveComponent achieveComponent = (AchieveComponent)LookUpService.getComponent(playerId, AchieveComponent.class);
/*  564 */       num = achieveComponent.getPoint();
/*  565 */     } else if (type == TaskType.ChallegeSingeBoss.getType()) {
/*  566 */       SingleInsComponent singleInsComponent = (SingleInsComponent)LookUpService.getComponent(playerId, SingleInsComponent.class);
/*  567 */       for (Iterator<Integer> iterator = singleInsComponent.getBossMap().keySet().iterator(); iterator.hasNext(); ) { int bossId = ((Integer)iterator.next()).intValue();
/*  568 */         BossHomeBean bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(bossId, BossHomeBean.class);
/*  569 */         if (null != bossHomeBean && bossHomeBean.getLevel() == targetId) {
/*  570 */           num++;
/*      */         } }
/*      */     
/*  573 */     } else if (type == TaskType.TotalCharge.getType()) {
/*  574 */       ExtendComponent extendComponent = (ExtendComponent)LookUpService.getComponent(playerId, ExtendComponent.class);
/*  575 */       num = extendComponent.getTotalChargeCCB();
/*  576 */     } else if (type == TaskType.TotalResetUnpra.getType()) {
/*  577 */       UnparalleledComponent unparalleledComponent = (UnparalleledComponent)LookUpService.getComponent(playerId, UnparalleledComponent.class);
/*  578 */       num = unparalleledComponent.getTotalResetTimes();
/*  579 */     } else if (type == TaskType.TotalChangeRankBoss.getType()) {
/*  580 */       ExtendComponent extendComponent = (ExtendComponent)LookUpService.getComponent(playerId, ExtendComponent.class);
/*  581 */       num = extendComponent.getTotalChangeBoss();
/*  582 */     } else if (type == TaskType.TotalComposePartner.getType()) {
/*  583 */       ExtendComponent extendComponent = (ExtendComponent)LookUpService.getComponent(playerId, ExtendComponent.class);
/*  584 */       num = extendComponent.getTotalComPartner();
/*  585 */     } else if (type == TaskType.TotalNormalChallenge.getType()) {
/*  586 */       SingleInsComponent singleInsComponent = (SingleInsComponent)LookUpService.getComponent(playerId, SingleInsComponent.class);
/*  587 */       num = ((Integer)singleInsComponent.getTotalChallengeMap().getOrDefault(Integer.valueOf(targetId), Integer.valueOf(0))).intValue();
/*  588 */     } else if (type == TaskType.ActiveTitle.getType()) {
/*  589 */       PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/*  590 */       num = playerComponent.getActiveTitles().size();
/*  591 */     } else if (type == TaskType.GetOnePartner.getType()) {
/*  592 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  593 */       for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/*  594 */         PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  595 */         if (partnerEntity.getTableId() == targetId) {
/*  596 */           num = 1L;
/*      */           break;
/*      */         } 
/*      */       } 
/*  600 */     } else if (type == TaskType.TotalFightTimes.getType()) {
/*  601 */       ArenaComponent arenaComponent = (ArenaComponent)LookUpService.getComponent(playerId, ArenaComponent.class);
/*  602 */       num = arenaComponent.getTotalFightTimes();
/*  603 */     } else if (type == TaskType.MonthYearWeek.getType()) {
/*  604 */       WelfareComponent welfareComponent = (WelfareComponent)LookUpService.getComponent(playerId, WelfareComponent.class);
/*  605 */       int monthEndTime = ((Integer)welfareComponent.getMonthEndTime().getOrDefault(Integer.valueOf(1), Integer.valueOf(0))).intValue();
/*  606 */       int specialEndTime = ((Integer)welfareComponent.getMonthEndTime().getOrDefault(Integer.valueOf(2), Integer.valueOf(0))).intValue();
/*  607 */       int weekEndTime = ((Integer)welfareComponent.getMonthEndTime().getOrDefault(Integer.valueOf(5), Integer.valueOf(0))).intValue();
/*  608 */       if (weekEndTime > TimeUtil.currentTime() || monthEndTime > TimeUtil.currentTime() || specialEndTime != 0) {
/*  609 */         num = 1L;
/*      */       }
/*  611 */     } else if (type == TaskType.MountsTotalStar.getType()) {
/*  612 */       MountsComponent mountsComponent = (MountsComponent)LookUpService.getComponent(playerId, MountsComponent.class);
/*  613 */       for (IMapEntity iMapEntity : mountsComponent.getEntityMap().values()) {
/*  614 */         MountsEntity mountsEntity = (MountsEntity)iMapEntity;
/*  615 */         num += mountsEntity.getStar();
/*      */       } 
/*  617 */     } else if (type == TaskType.ActivityMounts.getType()) {
/*  618 */       MountsComponent mountsComponent = (MountsComponent)LookUpService.getComponent(playerId, MountsComponent.class);
/*  619 */       num = mountsComponent.getEntityMap().size();
/*  620 */     } else if (type == TaskType.TowerMaxPoint.getType()) {
/*  621 */       TowerComponent towerComponent = (TowerComponent)LookUpService.getComponent(playerId, TowerComponent.class);
/*  622 */       num = towerComponent.getCurLayers();
/*  623 */     } else if (type == TaskType.TotalTeamTimes.getType()) {
/*  624 */       BossHomeComponent bossHomeComponent = (BossHomeComponent)LookUpService.getComponent(playerId, BossHomeComponent.class);
/*  625 */       num = bossHomeComponent.getTotalFightTimes();
/*  626 */     } else if (type == TaskType.NPartnerLevel.getType()) {
/*  627 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  628 */       if (null != partnerComponent) {
/*  629 */         Map<String, IMapEntity> map = partnerComponent.getEntityMap();
/*  630 */         for (IMapEntity iMapEntity : map.values()) {
/*  631 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  632 */           if (partnerEntity.getLevel() >= targetId) {
/*  633 */             num++;
/*      */           }
/*      */         } 
/*      */       } 
/*  637 */     } else if (type == TaskType.ActZhenFa.getType()) {
/*  638 */       WarLineupComponent warLineupComponent = (WarLineupComponent)LookUpService.getComponent(playerId, WarLineupComponent.class);
/*  639 */       num = warLineupComponent.getEntityMap().size();
/*  640 */     } else if (type == TaskType.TotalZhenFaLevel.getType()) {
/*  641 */       WarLineupComponent warLineupComponent = (WarLineupComponent)LookUpService.getComponent(playerId, WarLineupComponent.class);
/*  642 */       for (IMapEntity iMapEntity : warLineupComponent.getEntityMap().values()) {
/*  643 */         WarLineupEntity warLineupEntity = (WarLineupEntity)iMapEntity;
/*  644 */         num += warLineupEntity.getLevel();
/*      */       } 
/*  646 */     } else if (type == TaskType.TotalZhenFaStar.getType()) {
/*  647 */       WarLineupComponent warLineupComponent = (WarLineupComponent)LookUpService.getComponent(playerId, WarLineupComponent.class);
/*  648 */       for (IMapEntity iMapEntity : warLineupComponent.getEntityMap().values()) {
/*  649 */         WarLineupEntity warLineupEntity = (WarLineupEntity)iMapEntity;
/*  650 */         num += warLineupEntity.getStar();
/*      */       } 
/*  652 */     } else if (type == TaskType.ActStage.getType()) {
/*  653 */       StageComponent stageComponent = (StageComponent)LookUpService.getComponent(playerId, StageComponent.class);
/*  654 */       num = stageComponent.getEntityMap().size();
/*  655 */     } else if (type == TaskType.TotalStageLevel.getType()) {
/*  656 */       StageComponent stageComponent = (StageComponent)LookUpService.getComponent(playerId, StageComponent.class);
/*  657 */       for (IMapEntity iMapEntity : stageComponent.getEntityMap().values()) {
/*  658 */         StageEntity stageEntity = (StageEntity)iMapEntity;
/*  659 */         num += stageEntity.getLevel();
/*      */       } 
/*  661 */     } else if (type == TaskType.TotalStageStar.getType()) {
/*  662 */       StageComponent stageComponent = (StageComponent)LookUpService.getComponent(playerId, StageComponent.class);
/*  663 */       for (IMapEntity iMapEntity : stageComponent.getEntityMap().values()) {
/*  664 */         StageEntity stageEntity = (StageEntity)iMapEntity;
/*  665 */         num += stageEntity.getStar();
/*      */       } 
/*  667 */     } else if (type == TaskType.ActKungFu.getType()) {
/*  668 */       KungFuComponent kungFuComponent = (KungFuComponent)LookUpService.getComponent(playerId, KungFuComponent.class);
/*  669 */       num = kungFuComponent.getEntityMap().size();
/*  670 */     } else if (type == TaskType.TotalKungFuLevel.getType()) {
/*  671 */       KungFuComponent kungFuComponent = (KungFuComponent)LookUpService.getComponent(playerId, KungFuComponent.class);
/*  672 */       for (IMapEntity iMapEntity : kungFuComponent.getEntityMap().values()) {
/*  673 */         KungFuEntity kungFuEntity = (KungFuEntity)iMapEntity;
/*  674 */         num += kungFuEntity.getLevel();
/*      */       } 
/*  676 */     } else if (type == TaskType.TotalKungFuStar.getType()) {
/*  677 */       KungFuComponent kungFuComponent = (KungFuComponent)LookUpService.getComponent(playerId, KungFuComponent.class);
/*  678 */       for (IMapEntity iMapEntity : kungFuComponent.getEntityMap().values()) {
/*  679 */         KungFuEntity kungFuEntity = (KungFuEntity)iMapEntity;
/*  680 */         num += kungFuEntity.getStar();
/*      */       } 
/*  682 */     } else if (type == TaskType.TotalMilitaryResearch.getType()) {
/*  683 */       MilitaryOfficeComponent militaryOfficeComponent = (MilitaryOfficeComponent)LookUpService.getComponent(playerId, MilitaryOfficeComponent.class);
/*  684 */       for (Iterator<Integer> iterator = militaryOfficeComponent.getOfficeList().values().iterator(); iterator.hasNext(); ) { int level = ((Integer)iterator.next()).intValue();
/*  685 */         num += level; }
/*      */     
/*  687 */     } else if (type == TaskType.TotalEquipZhuhun.getType()) {
/*  688 */       RuneComponent runeComponent = (RuneComponent)LookUpService.getComponent(playerId, RuneComponent.class);
/*  689 */       num = runeComponent.getTotalZhuHun();
/*  690 */     } else if (type == TaskType.PartnerDenstiny.getType()) {
/*  691 */       PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/*  692 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  693 */       for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  694 */         if (pid == 0L) {
/*      */           continue;
/*      */         }
/*  697 */         if (pid == -1L) {
/*  698 */           if (playerComponent.getDesLv() >= targetId) {
/*  699 */             num++;
/*      */           }
/*      */           continue;
/*      */         } 
/*  703 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  704 */         if (null == partnerEntity) {
/*      */           continue;
/*      */         }
/*  707 */         if (partnerEntity.getDesLv() >= targetId) {
/*  708 */           num++;
/*      */         } }
/*      */ 
/*      */     
/*  712 */     } else if (type == TaskType.BaGuaPass.getType()) {
/*  713 */       BaguaComponent baguaComponent = (BaguaComponent)LookUpService.getComponent(playerId, BaguaComponent.class);
/*  714 */       if (null != baguaComponent) {
/*  715 */         for (Set<Integer> points : (Iterable<Set<Integer>>)baguaComponent.getChapterMap().values()) {
/*  716 */           num += points.size();
/*      */         }
/*      */       }
/*  719 */     } else if (type == TaskType.AssistPartner.getType()) {
/*  720 */       GeneralComponent generalComponent = (GeneralComponent)LookUpService.getComponent(playerId, GeneralComponent.class);
/*  721 */       for (Iterator<Long> iterator = generalComponent.getAssistInBattle().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  722 */         if (pid != 0L) {
/*  723 */           num++;
/*      */         } }
/*      */     
/*  726 */     } else if (type == TaskType.TalimanUp.getType()) {
/*  727 */       PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/*  728 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  729 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*  730 */       for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  731 */         long talismanId = 0L;
/*  732 */         if (pid == 0L) {
/*      */           continue;
/*      */         }
/*  735 */         if (pid == -1L) {
/*  736 */           talismanId = playerComponent.getTalisman();
/*      */         } else {
/*  738 */           PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  739 */           if (null == partnerEntity) {
/*      */             continue;
/*      */           }
/*  742 */           talismanId = partnerEntity.getTalisman();
/*      */         } 
/*  744 */         if (talismanId != 0L) {
/*  745 */           EquipEntity equipEntity = equipComponent.getEquipEntity(talismanId);
/*  746 */           if (null != equipEntity) {
/*  747 */             ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  748 */             if (null != itemBean && itemBean.getItemQuality() >= targetId) {
/*  749 */               num++;
/*      */             }
/*      */           } 
/*      */         }  }
/*      */     
/*  754 */     } else if (type == TaskType.TatalChangeNeutralBoss.getType()) {
/*  755 */       BossHomeComponent bossHomeComponent = (BossHomeComponent)LookUpService.getComponent(playerId, BossHomeComponent.class);
/*  756 */       num = bossHomeComponent.getTotalNeutralBoss();
/*  757 */     } else if (type == TaskType.EquipArtifice.getType()) {
/*  758 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  759 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  760 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*  761 */       for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  762 */         if (pid == -1L) {
/*  763 */           for (Iterator<Long> iterator2 = playerComponent.getEquips().values().iterator(); iterator2.hasNext(); ) { long equipId = ((Long)iterator2.next()).longValue();
/*  764 */             EquipEntity equipEntity = equipComponent.getEquipEntity(equipId);
/*  765 */             if (null != equipEntity && 
/*  766 */               !EquipUtil.isTreasure(equipEntity.getItemId()) && equipEntity.getArtificeLevel() >= targetId) {
/*  767 */               num++;
/*      */             } }
/*      */           
/*      */           continue;
/*      */         } 
/*  772 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  773 */         if (null == partnerEntity) {
/*      */           continue;
/*      */         }
/*  776 */         for (Iterator<Long> iterator1 = partnerEntity.getEquips().values().iterator(); iterator1.hasNext(); ) { long equipId = ((Long)iterator1.next()).longValue();
/*  777 */           EquipEntity equipEntity = equipComponent.getEquipEntity(equipId);
/*  778 */           if (null != equipEntity && 
/*  779 */             !EquipUtil.isTreasure(equipEntity.getItemId()) && equipEntity.getArtificeLevel() >= targetId) {
/*  780 */             num++;
/*      */           } }
/*      */         
/*      */          }
/*      */ 
/*      */     
/*  786 */     } else if (type == TaskType.EquipStarTarget.getType()) {
/*  787 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  788 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  789 */       EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/*  790 */       for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  791 */         if (pid == -1L) {
/*  792 */           for (Iterator<Long> iterator2 = playerComponent.getEquips().values().iterator(); iterator2.hasNext(); ) { long equipId = ((Long)iterator2.next()).longValue();
/*  793 */             EquipEntity equipEntity = equipComponent.getEquipEntity(equipId);
/*  794 */             if (null != equipEntity && 
/*  795 */               !EquipUtil.isTreasure(equipEntity.getItemId()) && equipEntity.getStar() >= targetId) {
/*  796 */               num++;
/*      */             } }
/*      */           
/*      */           continue;
/*      */         } 
/*  801 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  802 */         if (null == partnerEntity) {
/*      */           continue;
/*      */         }
/*  805 */         for (Iterator<Long> iterator1 = partnerEntity.getEquips().values().iterator(); iterator1.hasNext(); ) { long equipId = ((Long)iterator1.next()).longValue();
/*  806 */           EquipEntity equipEntity = equipComponent.getEquipEntity(equipId);
/*  807 */           if (null != equipEntity && 
/*  808 */             !EquipUtil.isTreasure(equipEntity.getItemId()) && equipEntity.getStar() >= targetId) {
/*  809 */             num++;
/*      */           } }
/*      */         
/*      */          }
/*      */ 
/*      */     
/*  815 */     } else if (type == TaskType.TotalBattleSoul.getType()) {
/*  816 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  817 */       for (Iterator<Integer> iterator = playerComponent.getSoulsFighter().iterator(); iterator.hasNext(); ) { int sid = ((Integer)iterator.next()).intValue();
/*  818 */         if (sid != 0) {
/*  819 */           num++;
/*      */         } }
/*      */     
/*  822 */     } else if (type == TaskType.TotalPartentSoul.getType()) {
/*  823 */       PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/*  824 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  825 */       for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  826 */         int soulLevel = 0;
/*  827 */         if (pid == 0L) {
/*      */           continue;
/*      */         }
/*  830 */         if (pid == -1L) {
/*  831 */           soulLevel = playerComponent.getSoulLevel();
/*      */         } else {
/*  833 */           PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  834 */           if (null == partnerEntity) {
/*      */             continue;
/*      */           }
/*  837 */           soulLevel = partnerEntity.getSoulLevel();
/*      */         } 
/*  839 */         if (soulLevel >= targetId) {
/*  840 */           num++;
/*      */         } }
/*      */     
/*  843 */     } else if (type == TaskType.TotalSecretiTimes.getType()) {
/*  844 */       SecretiComponent secretiComponent = (SecretiComponent)LookUpService.getComponent(playerId, SecretiComponent.class);
/*  845 */       num = secretiComponent.getTotalTimes();
/*  846 */     } else if (type == TaskType.TotalKuaFuTimes.getType()) {
/*  847 */       CrossRaceComponent crossRaceComponent = (CrossRaceComponent)LookUpService.getComponent(playerId, CrossRaceComponent.class);
/*  848 */       num = crossRaceComponent.getTotalTimes();
/*  849 */     } else if (type == TaskType.TotalTalismanTurn.getType()) {
/*  850 */       RuneComponent runeComponent = (RuneComponent)LookUpService.getComponent(playerId, RuneComponent.class);
/*  851 */       num = runeComponent.getTalismanTurn();
/*  852 */     } else if (type == TaskType.TatalArtificeTimes.getType()) {
/*  853 */       RuneComponent runeComponent = (RuneComponent)LookUpService.getComponent(playerId, RuneComponent.class);
/*  854 */       num = runeComponent.getTatalArtificeTimes();
/*  855 */     } else if (type == TaskType.PartnerReincarnStar.getType()) {
/*  856 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  857 */       if (playerComponent.getLeaderId() == targetId) {
/*  858 */         num = playerComponent.getStars();
/*      */       } else {
/*  860 */         PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  861 */         for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/*  862 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  863 */           if (partnerEntity.getTableId() == targetId) {
/*  864 */             num = partnerEntity.getStars();
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*  869 */     } else if (type == TaskType.PartnerReincarnDestiny.getType()) {
/*  870 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  871 */       if (playerComponent.getLeaderId() == targetId) {
/*  872 */         num = playerComponent.getDesLv();
/*      */       } else {
/*  874 */         PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  875 */         for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/*  876 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  877 */           if (partnerEntity.getTableId() == targetId) {
/*  878 */             num = partnerEntity.getDesLv();
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*  883 */     } else if (type == TaskType.PartnerReincarnSouls.getType()) {
/*  884 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  885 */       if (playerComponent.getLeaderId() == targetId) {
/*  886 */         num = playerComponent.getSoulLevel();
/*      */       } else {
/*  888 */         PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  889 */         for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/*  890 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  891 */           if (partnerEntity.getTableId() == targetId) {
/*  892 */             num = partnerEntity.getSoulLevel();
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*  897 */     } else if (type == TaskType.PartnerReincarnBreak.getType()) {
/*  898 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  899 */       if (playerComponent.getLeaderId() == targetId) {
/*  900 */         num = playerComponent.getBreakthroughs();
/*      */       } else {
/*  902 */         PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/*  903 */         for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/*  904 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  905 */           if (partnerEntity.getTableId() == targetId) {
/*  906 */             num = partnerEntity.getBreakthroughs();
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*  911 */     } else if (type == TaskType.SecretiFloor.getType()) {
/*  912 */       SecretiComponent secretiComponent = (SecretiComponent)LookUpService.getComponent(playerId, SecretiComponent.class);
/*  913 */       num = secretiComponent.getMaxLayer();
/*      */     } 
/*  915 */     return num;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int MAIN_TASK_INT = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isTaskDone(long curValue, long requireValue, int type) {
/*  927 */     if (type == 0)
/*  928 */       return (curValue >= requireValue); 
/*  929 */     if (type == 1)
/*  930 */       return (curValue != 0L && curValue <= requireValue); 
/*  931 */     if (type == 2) {
/*  932 */       return (curValue == requireValue);
/*      */     }
/*  934 */     return false;
/*      */   }
/*      */   
/*      */   public static void updateSchedule(IPlayerSession playerSession) {
/*  938 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  939 */     MainTaskBean mainTaskBean = (MainTaskBean)JsonTableService.getJsonData(taskComponent.getId(), MainTaskBean.class);
/*  940 */     if (mainTaskBean != null) {
/*  941 */       TaskType taskType = TaskType.getTaskType(mainTaskBean.getPara());
/*  942 */       TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(mainTaskBean.getPara(), TaskTypeBean.class);
/*  943 */       if (null == taskType || null == taskTypeBean || taskTypeBean.getCountType() == 1) {
/*      */         return;
/*      */       }
/*  946 */       taskComponent.refreshSchedule(taskType, 0, 0L);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isTaskDone2(long target, long requireTarget) {
/*  959 */     if (target == requireTarget) {
/*  960 */       return true;
/*      */     }
/*  962 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private static FindRewardData getFindRewardData1(IPlayerSession playerSession, FindRewardType findRewardType, int value, int maxValue, boolean check) {
/*  967 */     if (0 == maxValue || maxValue <= value) {
/*  968 */       return null;
/*      */     }
/*  970 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  971 */     ArrayList<Reward> goldCost = new ArrayList<>();
/*  972 */     ArrayList<Reward> moneyCost = new ArrayList<>();
/*  973 */     boolean getReward = false;
/*  974 */     if (value < maxValue && check) {
/*  975 */       getReward = true;
/*  976 */       rewards = getFindReward(findRewardType.getType(), playerSession, goldCost, moneyCost);
/*      */     } 
/*  978 */     if (getReward && rewards.isEmpty()) {
/*  979 */       return null;
/*      */     }
/*  981 */     FindRewardData findRewardData = new FindRewardData();
/*  982 */     findRewardData.type = findRewardType.getType();
/*  983 */     findRewardData.mold = findRewardType.getMold();
/*  984 */     findRewardData.curValue = value;
/*  985 */     findRewardData.maxValue = maxValue;
/*  986 */     if (!rewards.isEmpty()) {
/*  987 */       findRewardData.list = rewards;
/*      */     }
/*  989 */     if (!goldCost.isEmpty()) {
/*  990 */       findRewardData.goldCost = goldCost;
/*      */     }
/*  992 */     if (!moneyCost.isEmpty()) {
/*  993 */       findRewardData.moneyCost = moneyCost;
/*      */     }
/*  995 */     return findRewardData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static FindRewardData getFindRewardData(FindRewardType findRewardType, IPlayerSession playerSession, boolean check) {
/* 1008 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, findRewardType.getFunctionId())) {
/* 1009 */       return null;
/*      */     }
/* 1011 */     TaskParameter taskParameter = (TaskParameter)ParameterConstant.getParameter(22);
/* 1012 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 1013 */     int curDay = TimeUtil.getDayDisTime(playerComponent.getCreateTime());
/* 1014 */     if (curDay == 1) {
/* 1015 */       return null;
/*      */     }
/*      */     
/* 1018 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/* 1019 */     Map<Integer, Map<Integer, Integer>> actPlayTime = singleInsComponent.getActPlayTime();
/* 1020 */     Map<Integer, Integer> t = actPlayTime.getOrDefault(Integer.valueOf(findRewardType.getType()), new HashMap<>());
/* 1021 */     int value = 0;
/* 1022 */     int maxTime = getMaxCheckPoint(findRewardType.getType(), playerSession.getPlayer().getPlayerId());
/* 1023 */     for (int i = 1; i <= taskParameter.getDays(); i++) {
/* 1024 */       int day = TimeUtil.getYearMonthDay(TimeUtil.currentTime() - i * 86400);
/* 1025 */       if (taskParameter.getEffectDate() <= day) {
/*      */ 
/*      */         
/* 1028 */         int times = ((Integer)t.getOrDefault(Integer.valueOf(day), Integer.valueOf(0))).intValue();
/* 1029 */         value += times;
/*      */       } 
/*      */     } 
/* 1032 */     int maxValue = getMaxValue(playerSession.getPlayer().getPlayerId(), findRewardType.getType());
/* 1033 */     if (maxValue == 0 && taskParameter.getEffectDate() < TimeUtil.getCurrentYearMonthDay()) {
/* 1034 */       for (int j = 1; j <= taskParameter.getDays(); j++) {
/* 1035 */         int day = TimeUtil.getYearMonthDay(TimeUtil.currentTime() - j * 86400);
/* 1036 */         if (taskParameter.getEffectDate() <= day)
/*      */         {
/*      */           
/* 1039 */           maxValue += maxTime;
/*      */         }
/*      */       } 
/*      */     }
/* 1043 */     if (maxValue == 0) {
/* 1044 */       return null;
/*      */     }
/*      */     
/* 1047 */     FindRewardData findRewardData = getFindRewardData1(playerSession, findRewardType, value, maxValue, check);
/* 1048 */     return findRewardData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void saveMaxReward(IPlayerSession playerSession, FindRewardData findRewardData) {
/* 1058 */     if (null == findRewardData || null == playerSession) {
/*      */       return;
/*      */     }
/* 1061 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 1062 */     FindRewardType findRewardType = FindRewardType.getFindRewardType(findRewardData.type);
/* 1063 */     if (null == findRewardType) {
/*      */       return;
/*      */     }
/* 1066 */     int maxTimes = getMaxCheckPoint(findRewardType.getType(), playerSession.getPlayer().getPlayerId());
/* 1067 */     TaskParameter taskParameter = (TaskParameter)ParameterConstant.getParameter(22);
/* 1068 */     if (taskParameter.getEffectDate() >= TimeUtil.getCurrentYearMonthDay()) {
/*      */       return;
/*      */     }
/* 1071 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 1072 */     int curDay = TimeUtil.getDayDisTime(playerComponent.getCreateTime());
/* 1073 */     if (curDay == 1) {
/*      */       return;
/*      */     }
/* 1076 */     findRewardData.maxValue = 0;
/* 1077 */     for (int i = 1; i <= taskParameter.getDays(); i++) {
/* 1078 */       int day = TimeUtil.getYearMonthDay(TimeUtil.currentTime() - i * 86400);
/* 1079 */       if (taskParameter.getEffectDate() <= day)
/*      */       {
/*      */ 
/*      */         
/* 1083 */         findRewardData.maxValue += maxTimes;
/*      */       }
/*      */     } 
/* 1086 */     if (findRewardData.maxValue / maxTimes > taskParameter.getDays()) {
/* 1087 */       findRewardData.maxValue = maxTimes * taskParameter.getDays();
/*      */     }
/*      */     
/* 1090 */     Map<Integer, Integer> findRewardMax = taskComponent.getFindRewardMax();
/* 1091 */     findRewardMax.put(Integer.valueOf(findRewardData.type), Integer.valueOf(findRewardData.maxValue));
/* 1092 */     taskComponent.setFindRewardMax(findRewardMax);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getMaxValue(long playerId, int type) {
/* 1103 */     TaskComponent taskComponent = (TaskComponent)LookUpService.getComponent(playerId, TaskComponent.class);
/* 1104 */     FindRewardType findRewardType = FindRewardType.getFindRewardType(type);
/* 1105 */     if (null == findRewardType) {
/* 1106 */       return 0;
/*      */     }
/* 1108 */     if (findRewardType.getMold() == 1) {
/* 1109 */       Map<Integer, Integer> findRewardMax = taskComponent.getFindRewardMax();
/* 1110 */       return ((Integer)findRewardMax.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/*      */     } 
/* 1112 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getMaxCheckPoint(int copyType, long playerId) {
/* 1124 */     if (copyType == FindRewardType.NORMAL_1.getType() || copyType == FindRewardType.NORMAL_2.getType() || copyType == FindRewardType.NORMAL_3.getType() || copyType == FindRewardType.NORMAL_4.getType() || copyType == FindRewardType.NORMAL_5.getType() || copyType == FindRewardType.NORMAL_6.getType())
/* 1125 */     { PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/* 1126 */       if (null != playerComponent) {
/* 1127 */         int dailyTime = VipUtil.getNum(playerComponent.getVip(), 8);
/* 1128 */         return dailyTime;
/*      */       }  }
/* 1130 */     else { if (copyType == FindRewardType.TEAM.getType()) {
/* 1131 */         TeamParameter teamParameter = (TeamParameter)ParameterConstant.getParameter(37);
/* 1132 */         return teamParameter.getMaxFightTimes();
/* 1133 */       }  if (copyType == FindRewardType.BAGUA.getType())
/* 1134 */         return 1; 
/* 1135 */       if (copyType == FindRewardType.UNPARM.getType())
/* 1136 */         return 1; 
/* 1137 */       if (copyType == FindRewardType.DENSTINY.getType()) {
/* 1138 */         DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/* 1139 */         return destinyParameter.getInitRobTimes() * 2;
/* 1140 */       }  if (copyType == FindRewardType.ARENA.getType()) {
/* 1141 */         ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/* 1142 */         return arenaParameter.getMaxChallengeTime();
/* 1143 */       }  if (copyType == FindRewardType.CROSS.getType()) {
/* 1144 */         CrossRaceParameter crossRaceParameter = (CrossRaceParameter)ParameterConstant.getParameter(28);
/* 1145 */         return crossRaceParameter.getFightTimes();
/* 1146 */       }  if (copyType == FindRewardType.PERSONAL_BOSS.getType()) {
/* 1147 */         return 1;
/*      */       } }
/*      */     
/* 1150 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ArrayList<Reward> getFindReward(int type, IPlayerSession playerSession, ArrayList<Reward> goldCost, ArrayList<Reward> moneyCost) {
/* 1161 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 1162 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/* 1163 */     if (type >= FindRewardType.NORMAL_1.getType() && type <= FindRewardType.NORMAL_6.getType())
/* 1164 */     { SingleInsParameter singleInsParameter = (SingleInsParameter)ParameterConstant.getParameter(13);
/* 1165 */       int maxPoint = ((Integer)singleInsComponent.getMaxPoints().getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 1166 */       Map<Integer, Integer> pointIns = singleInsParameter.getInsListByType(type);
/* 1167 */       if (pointIns == null) {
/* 1168 */         return rewards;
/*      */       }
/* 1170 */       int ins = ((Integer)pointIns.getOrDefault(Integer.valueOf(maxPoint), Integer.valueOf(0))).intValue();
/* 1171 */       if (ins == 0) {
/* 1172 */         return rewards;
/*      */       }
/* 1174 */       PersonalInsBean personalInsBean = (PersonalInsBean)JsonTableService.getJsonData(ins, PersonalInsBean.class);
/* 1175 */       goldCost.addAll(FinanceUtil.transform(personalInsBean.getOtherCost()));
/* 1176 */       moneyCost.addAll(FinanceUtil.transform(personalInsBean.getMoneyCost()));
/* 1177 */       rewards.addAll(FinanceUtil.transform(personalInsBean.getPassReward())); }
/* 1178 */     else if (type == FindRewardType.TEAM.getType())
/* 1179 */     { int insId = 0;
/* 1180 */       int level = 0;
/* 1181 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 1182 */       Map<Integer, Object> map = JsonTableService.getJsonTable(MultiInsBean.class);
/* 1183 */       for (Object o : map.values()) {
/* 1184 */         MultiInsBean multiInsBean1 = (MultiInsBean)o;
/* 1185 */         if (multiInsBean1.getLevel() <= playerComponent.getLevel() && level < multiInsBean1.getLevel()) {
/* 1186 */           insId = multiInsBean1.getInsId();
/* 1187 */           level = multiInsBean1.getLevel();
/*      */         } 
/*      */       } 
/* 1190 */       MultiInsBean multiInsBean = (MultiInsBean)JsonTableService.getJsonData(insId, MultiInsBean.class);
/*      */       
/* 1192 */       rewards.addAll(FinanceUtil.transform(multiInsBean.getReward()));
/* 1193 */       rewards.addAll(FinanceUtil.transform(multiInsBean.getDoubleReward()));
/* 1194 */       goldCost.addAll(FinanceUtil.transform(multiInsBean.getOtherCost()));
/* 1195 */       moneyCost.addAll(FinanceUtil.transform(multiInsBean.getMoneyCost())); }
/* 1196 */     else if (type == FindRewardType.BAGUA.getType())
/* 1197 */     { BaguaComponent baguaComponent = (BaguaComponent)playerSession.getPlayer().createIfNotExist(BaguaComponent.class);
/*      */       
/* 1199 */       for (Iterator<Integer> iterator = baguaComponent.getChapterMap().keySet().iterator(); iterator.hasNext(); ) { int chapterId = ((Integer)iterator.next()).intValue();
/* 1200 */         for (Iterator<Integer> iterator1 = ((Set)baguaComponent.getChapterMap().get(Integer.valueOf(chapterId))).iterator(); iterator1.hasNext(); ) { int insId = ((Integer)iterator1.next()).intValue();
/* 1201 */           BaguaInsBean baguaInsBean1 = (BaguaInsBean)JsonTableService.getJsonData(insId, BaguaInsBean.class);
/* 1202 */           rewards.addAll(FinanceUtil.transform(baguaInsBean1.getSweep())); }
/*      */          }
/*      */       
/* 1205 */       BaguaInsBean baguaInsBean = (BaguaInsBean)JsonTableService.getJsonData(baguaComponent.getCurInsId(), BaguaInsBean.class);
/* 1206 */       int lastIns = baguaInsBean.getLast();
/* 1207 */       BaguaInsBean baguaInsBean2 = (BaguaInsBean)JsonTableService.getJsonData(lastIns, BaguaInsBean.class);
/* 1208 */       if (baguaInsBean2 != null) {
/* 1209 */         goldCost.addAll(FinanceUtil.transform(baguaInsBean2.getOtherCost()));
/* 1210 */         moneyCost.addAll(FinanceUtil.transform(baguaInsBean2.getMoneyCost()));
/*      */       }  }
/* 1212 */     else if (type == FindRewardType.UNPARM.getType())
/* 1213 */     { UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/* 1214 */       int maxInsId = 0;
/* 1215 */       int lastIns = 0;
/* 1216 */       for (Iterator<Integer> iterator = unparalleledComponent.getInsMap().keySet().iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/* 1217 */         int ins = ((Integer)unparalleledComponent.getInsMap().get(Integer.valueOf(key))).intValue();
/* 1218 */         WushuangInsBean wushuangInsBean1 = (WushuangInsBean)JsonTableService.getJsonData(ins, WushuangInsBean.class);
/* 1219 */         if (wushuangInsBean1.getCheckPoint() == unparalleledComponent.getMaxPoint()) {
/* 1220 */           maxInsId = lastIns;
/*      */           break;
/*      */         } 
/* 1223 */         if (wushuangInsBean1.getStar() != 3) {
/* 1224 */           maxInsId = lastIns;
/*      */           break;
/*      */         } 
/* 1227 */         lastIns = ins;
/* 1228 */         rewards.addAll(FinanceUtil.transform(wushuangInsBean1.getPassReward())); }
/*      */       
/* 1230 */       WushuangInsBean wushuangInsBean = (WushuangInsBean)JsonTableService.getJsonData(maxInsId, WushuangInsBean.class);
/* 1231 */       if (wushuangInsBean != null) {
/* 1232 */         goldCost.addAll(FinanceUtil.transform(wushuangInsBean.getOtherCost()));
/* 1233 */         moneyCost.addAll(FinanceUtil.transform(wushuangInsBean.getMoneyCost()));
/*      */       }  }
/* 1235 */     else { if (type == FindRewardType.DENSTINY.getType()) {
/* 1236 */         DestinyWarBean bean = null;
/* 1237 */         for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(DestinyWarBean.class).iterator(); iterator.hasNext(); ) { int pointId = ((Integer)iterator.next()).intValue();
/* 1238 */           bean = (DestinyWarBean)JsonTableService.getJsonData(pointId, DestinyWarBean.class);
/* 1239 */           if (0 <= bean.getAnger()) {
/*      */             break;
/*      */           } }
/*      */ 
/*      */         
/* 1244 */         rewards.addAll(FinanceUtil.transform(bean.getMaxReward()));
/* 1245 */         ArrayList<Reward> arrayList = FinanceUtil.rewardGet(rewards);
/* 1246 */         FinanceUtil.mergeRewardList(arrayList);
/* 1247 */         goldCost.addAll(FinanceUtil.transform(bean.getOtherCost()));
/* 1248 */         moneyCost.addAll(FinanceUtil.transform(bean.getMoneyCost()));
/* 1249 */         return arrayList;
/* 1250 */       }  if (type == FindRewardType.ARENA.getType()) {
/* 1251 */         ArenaComponent arenaComponent = (ArenaComponent)playerSession.getPlayer().createIfNotExist(ArenaComponent.class);
/* 1252 */         int rank = arenaComponent.getRank();
/* 1253 */         ArenaRuleBean arenaRuleBean = ArenaUtil.getArenaRuleBean(rank);
/* 1254 */         if (null != arenaRuleBean) {
/* 1255 */           rewards.addAll(FinanceUtil.transform(arenaRuleBean.getWinReward()));
/*      */         }
/* 1257 */         goldCost.addAll(FinanceUtil.transform(arenaRuleBean.getOtherCost()));
/* 1258 */         moneyCost.addAll(FinanceUtil.transform(arenaRuleBean.getMoneyCost()));
/* 1259 */       } else if (type == FindRewardType.CROSS.getType()) {
/* 1260 */         CrossRaceComponent crossRaceComponent = (CrossRaceComponent)playerSession.getPlayer().createIfNotExist(CrossRaceComponent.class);
/* 1261 */         CrossRankBean crossRankBean = CrossRaceUtil.chooseTargetBean(crossRaceComponent.getPoint());
/* 1262 */         if (null != crossRankBean) {
/* 1263 */           rewards.addAll(FinanceUtil.transform(crossRankBean.getReward()));
/* 1264 */           goldCost.addAll(FinanceUtil.transform(crossRankBean.getOtherCost()));
/* 1265 */           moneyCost.addAll(FinanceUtil.transform(crossRankBean.getMoneyCost()));
/*      */         } 
/* 1267 */       } else if (type == FindRewardType.PERSONAL_BOSS.getType()) {
/* 1268 */         BossHomeBean bossHomeBean2 = null;
/* 1269 */         int level = 0;
/* 1270 */         for (Iterator<Integer> iterator = singleInsComponent.getBossMap().keySet().iterator(); iterator.hasNext(); ) { int insId = ((Integer)iterator.next()).intValue();
/* 1271 */           if (!singleInsComponent.getTimesMap().containsKey(Integer.valueOf(insId))) {
/* 1272 */             BossHomeBean bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(insId, BossHomeBean.class);
/* 1273 */             if (null == bossHomeBean) {
/*      */               continue;
/*      */             }
/* 1276 */             if (bossHomeBean.getLevel() > level) {
/* 1277 */               level = bossHomeBean.getLevel();
/* 1278 */               bossHomeBean2 = bossHomeBean;
/*      */             } 
/* 1280 */             rewards.addAll(FinanceUtil.transform(bossHomeBean.getLastHitReward()));
/*      */           }  }
/*      */         
/* 1283 */         if (null != bossHomeBean2) {
/* 1284 */           goldCost.addAll(FinanceUtil.transform(bossHomeBean2.getOtherCost()));
/* 1285 */           moneyCost.addAll(FinanceUtil.transform(bossHomeBean2.getMoneyCost()));
/*      */         } 
/*      */       }  }
/* 1288 */      ArrayList<Reward> rewards1 = FinanceUtil.rewardGet(rewards);
/* 1289 */     FinanceUtil.mergeRewardList(rewards1);
/* 1290 */     return rewards1;
/*      */   }
/*      */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\TaskUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */