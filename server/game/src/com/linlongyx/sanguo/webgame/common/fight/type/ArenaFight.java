/*     */ package com.linlongyx.sanguo.webgame.common.fight.type;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.logic.ISession;
/*     */ import com.linlongyx.core.framework.logic.PlayerSession;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.arena.ArenaComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.AbstractFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.MonsterFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.player.Player;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRobotBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRuleBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.PvpInfoBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ArenaParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MilitaryInsType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RankActType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.arena.ArenaUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.fight.ResultUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.offices.MilitaryUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArenaData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArenaReportData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArenaFight extends AbstractFight implements IFight {
/*     */   private ArenaData targetArenaData;
/*     */   private ArenaData myArenaData;
/*     */   private IPlayer myPlayer;
/*     */   
/*     */   public ArenaFight(IPlayer player, ArenaData myArenaData, ArenaData targetArenaData) {
/*  49 */     this.type = 4;
/*     */     
/*  51 */     this.myPlayer = player;
/*  52 */     this.myArenaData = myArenaData;
/*  53 */     this.targetArenaData = targetArenaData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short check() {
/*  61 */     if (this.targetArenaData.isRobot) {
/*  62 */       return checkRooter();
/*     */     }
/*  64 */     return checkPlayer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private short checkRooter() {
/*  73 */     if (!this.targetArenaData.isRobot) {
/*  74 */       return 11702;
/*     */     }
/*  76 */     ArenaRobotBean arenaRobotBean = (ArenaRobotBean)JsonTableService.getJsonData(this.targetArenaData.objectId, ArenaRobotBean.class);
/*  77 */     if (null == arenaRobotBean) {
/*  78 */       return 11701;
/*     */     }
/*     */     
/*  81 */     PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(this.myPlayer);
/*  82 */     initSide(0, (IFightSide)playerFightSide);
/*     */     
/*  84 */     MonsterFightSide monsterFightSide = new MonsterFightSide(arenaRobotBean, (byte)1);
/*  85 */     initSide(1, (IFightSide)monsterFightSide);
/*  86 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private short checkPlayer() {
/*     */     Player player;
/*  94 */     if (this.targetArenaData.isRobot) {
/*  95 */       return 11702;
/*     */     }
/*  97 */     long targetPlayerId = this.targetArenaData.playerId;
/*  98 */     PlayerComponent targetPlayerComponent = LookUpService.getPlayerComponent(targetPlayerId);
/*  99 */     if (null == targetPlayerComponent) {
/* 100 */       return 11703;
/*     */     }
/* 102 */     PvpInfoBean pvpInfoBean = (PvpInfoBean)JsonTableService.getJsonData(17, PvpInfoBean.class);
/* 103 */     if (null == pvpInfoBean) {
/* 104 */       return 11701;
/*     */     }
/*     */     
/* 107 */     IPlayer iPlayer = LookUpService.getByPlayerId(targetPlayerId);
/*     */     
/* 109 */     if (iPlayer == null) {
/* 110 */       PlayerSession.PlayerSessionBuilder builder = new PlayerSession.PlayerSessionBuilder();
/* 111 */       builder.validateAndSetValues();
/* 112 */       IPlayerSession playerSession = (IPlayerSession)builder.status(ISession.Status.CLOSED).isLogin(false).writeable(false).build();
/* 113 */       player = new Player(playerSession);
/* 114 */       player.setPlayerId(targetPlayerId);
/* 115 */       player.setPlayerName(targetPlayerComponent.getPlayerName());
/* 116 */       playerSession.setPlayer((IPlayer)player);
/*     */     } 
/*     */     
/* 119 */     PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(this.myPlayer);
/* 120 */     initSide(0, (IFightSide)playerFightSide);
/*     */     
/* 122 */     PlayerFightSide targetPlayerFightSide = FightUtil.initPlayerPVPFightSide((IPlayer)player, pvpInfoBean);
/* 123 */     initSide(1, (IFightSide)targetPlayerFightSide);
/*     */     
/* 125 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte start(FightRecordResponse response) {
/* 134 */     init();
/* 135 */     response.type = this.type;
/*     */ 
/*     */     
/* 138 */     getGroupData(response);
/*     */     
/* 140 */     action();
/* 141 */     getCandidateFighterData(response);
/*     */     
/* 143 */     System.out.println(getDebugStr());
/* 144 */     getRecord(response);
/* 145 */     endFight();
/* 146 */     return this.result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void end() {
/* 151 */     if (this.result == 2) {
/* 152 */       this.result = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   private void endFight() {
/* 157 */     boolean isWin = (this.result == 1);
/* 158 */     int myRank = this.myArenaData.rank;
/* 159 */     int targetRank = this.targetArenaData.rank;
/* 160 */     ArrayList<Reward> list = new ArrayList<>();
/* 161 */     boolean isNeedChangeRank = (targetRank < myRank && isWin);
/*     */ 
/*     */     
/* 164 */     int time = TimeUtil.currentTime();
/* 165 */     ArenaReportData myArenaReportData = new ArenaReportData();
/* 166 */     myArenaReportData.time = time;
/* 167 */     myArenaReportData.fightName = this.targetArenaData.playerName;
/* 168 */     myArenaReportData.isPlayer = 0;
/* 169 */     myArenaReportData.win = isWin ? 0 : 1;
/* 170 */     myArenaReportData.dt = isWin ? (myRank - targetRank) : 0;
/*     */ 
/*     */     
/* 173 */     updateMine(targetRank, list, isNeedChangeRank, myArenaReportData);
/*     */     
/* 175 */     if (!this.targetArenaData.isRobot) {
/*     */       
/* 177 */       ArenaReportData targetArenaReportData = new ArenaReportData();
/* 178 */       targetArenaReportData.time = time;
/* 179 */       targetArenaReportData.fightName = this.myArenaData.playerName;
/* 180 */       targetArenaReportData.isPlayer = 1;
/* 181 */       targetArenaReportData.win = isWin ? 1 : 0;
/* 182 */       targetArenaReportData.dt = isWin ? (targetRank - myRank) : 0;
/*     */ 
/*     */       
/* 185 */       updateTarget(myRank, this.targetArenaData, isNeedChangeRank, targetArenaReportData);
/* 186 */       if (isNeedChangeRank) {
/* 187 */         String title = LanguageConstant.getLanguage(1703);
/* 188 */         String content = LanguageConstant.getAndReplaceLanguage(1704, new String[] { this.myArenaData.playerName, String.valueOf(myRank) });
/* 189 */         MailUtil.sendSysMail(this.targetArenaData.playerId, new ArrayList(), title, content);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 194 */     int star = 3;
/* 195 */     ArenaRuleBean arenaRuleBean = ArenaUtil.getArenaRuleBean(targetRank);
/* 196 */     if (null != arenaRuleBean) {
/* 197 */       list.addAll(FinanceUtil.transform(arenaRuleBean.getWinReward()));
/*     */       
/* 199 */       MilitaryUtil.getRewardResult(list, MilitaryInsType.ArenaIns.getType(), this.myPlayer.getPlayerId(), 0);
/*     */     } 
/* 201 */     list = FinanceUtil.handleRepeat(list);
/* 202 */     ArrayList<Reward> rewards = FinanceUtil.rewardGet(list, this.myPlayer, ResourceEvent.ArenaFight, false);
/* 203 */     ResultUtil.saveResult(this.myArenaData.playerId, this.type, targetRank, star, rewards, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateMine(int newRank, ArrayList<Reward> list, boolean isNeedChangeRank, ArenaReportData arenaReportData) {
/* 214 */     ArenaComponent arenaComponent = (ArenaComponent)LookUpService.getComponent(this.myArenaData.playerId, ArenaComponent.class);
/* 215 */     ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/* 216 */     if (null != arenaComponent) {
/* 217 */       LogUtils.errorLog(new Object[] { "ArenaFightUpdate", Long.valueOf(arenaComponent.getPlayerId()), "主动挑战胜利", Integer.valueOf(arenaComponent.getRank()), Integer.valueOf(newRank), this.myArenaData.toString() });
/*     */       
/* 219 */       ArrayList<ArenaReportData> reports = arenaComponent.getReports();
/* 220 */       if (reports.size() >= arenaParameter.getMaxReport()) {
/* 221 */         reports.remove(0);
/*     */       }
/* 223 */       reports.add(arenaReportData);
/* 224 */       arenaComponent.setReports(reports);
/* 225 */       if (!isNeedChangeRank) {
/* 226 */         arenaComponent.saveToDB();
/*     */         
/*     */         return;
/*     */       } 
/* 230 */       int originMaxRank = arenaComponent.getMaxRank();
/* 231 */       if (originMaxRank == 0) {
/* 232 */         originMaxRank = arenaParameter.getRewardRank();
/*     */       }
/* 234 */       if (arenaComponent.getMaxRank() == 0 || arenaComponent.getMaxRank() > newRank) {
/* 235 */         arenaComponent.setMaxRank(newRank);
/* 236 */         int i = originMaxRank;
/* 237 */         while (i > newRank) {
/* 238 */           int num; ArenaRuleBean arenaRuleBean = ArenaUtil.getArenaRuleBean(i);
/* 239 */           if (null == arenaRuleBean) {
/* 240 */             i--;
/*     */             
/*     */             continue;
/*     */           } 
/* 244 */           if (newRank <= arenaRuleBean.getHighId()) {
/* 245 */             num = i - arenaRuleBean.getHighId();
/*     */           } else {
/* 247 */             num = i - newRank;
/*     */           } 
/* 249 */           if (num == 0) {
/* 250 */             num = 1;
/*     */           }
/* 252 */           i -= num;
/* 253 */           ArrayList<Reward> breaks = new ArrayList<>();
/* 254 */           breaks.addAll(FinanceUtil.transform(arenaRuleBean.getBreakReward()));
/* 255 */           for (Reward reward : breaks) {
/* 256 */             reward.num *= num;
/*     */           }
/* 258 */           list.addAll(breaks);
/*     */         } 
/*     */       } 
/* 261 */       arenaComponent.setRank(newRank);
/* 262 */       arenaComponent.saveToDB();
/* 263 */       ArenaUtil.removeRank(this.myArenaData.rank);
/* 264 */       this.myArenaData.rank = newRank;
/* 265 */       ArenaUtil.updateRank(this.myArenaData);
/* 266 */       TaskComponent taskComponent = (TaskComponent)LookUpService.getComponent(this.myArenaData.playerId, TaskComponent.class);
/* 267 */       if (taskComponent != null) {
/* 268 */         taskComponent.refreshSchedule(TaskType.ArenaRank, 0, newRank);
/*     */       }
/* 270 */       RankActUtil.refreshRankValue(RankActType.Arena.getType(), newRank, this.myArenaData.playerId);
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
/*     */   private void updateTarget(int rank, ArenaData arenaData, boolean isNeedChangeRank, ArenaReportData arenaReportData) {
/* 282 */     ArenaComponent arenaComponent = (ArenaComponent)LookUpService.getComponent(arenaData.playerId, ArenaComponent.class);
/* 283 */     ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/* 284 */     if (null != arenaComponent) {
/*     */       
/* 286 */       ArrayList<ArenaReportData> reports = arenaComponent.getReports();
/* 287 */       if (reports.size() >= arenaParameter.getMaxReport()) {
/* 288 */         reports.remove(0);
/*     */       }
/* 290 */       reports.add(arenaReportData);
/* 291 */       arenaComponent.setReports(reports);
/* 292 */       if (!isNeedChangeRank) {
/* 293 */         arenaComponent.saveToDB();
/*     */         
/*     */         return;
/*     */       } 
/* 297 */       if (arenaComponent.getMaxRank() == 0 || arenaComponent.getMaxRank() > rank) {
/* 298 */         arenaComponent.setMaxRank(rank);
/*     */       }
/* 300 */       int oldRank = arenaComponent.getRank();
/* 301 */       arenaComponent.setRank(rank);
/* 302 */       arenaComponent.saveToDB();
/* 303 */       arenaData.rank = rank;
/* 304 */       ArenaUtil.updateRank(arenaData);
/* 305 */       RankActUtil.refreshRankValue(RankActType.Arena.getType(), rank, arenaData.playerId);
/* 306 */       LogUtils.errorLog(new Object[] { "ArenaFightUpdate", Long.valueOf(arenaData.playerId), "被打败", Integer.valueOf(oldRank), Integer.valueOf(rank), Long.valueOf(this.myArenaData.playerId) });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\type\ArenaFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */