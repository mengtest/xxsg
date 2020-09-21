/*     */ package com.linlongyx.sanguo.webgame.common.fight.side;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.NeutralBossBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MilitaryInsType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.bosshome.BossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.offices.MilitaryUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.NeutralBossPushResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AlienRaceBossFightSide
/*     */ {
/*     */   private int instanceId;
/*     */   private NeutralBossBean neutralBossBean;
/*     */   private long maxHp;
/*     */   private long curHp;
/*     */   private int remainTime;
/*     */   private int nextTime;
/*     */   private int freshTime;
/*     */   private int sustainedTime;
/*     */   private volatile boolean open;
/*     */   private boolean isInit = false;
/*     */   private long belongToId;
/*     */   private String playerName;
/*     */   private int playerTime;
/*     */   private MonsterFightSide monsterFightSide;
/*     */   
/*     */   public AlienRaceBossFightSide(int instanceId) {
/*  47 */     this.instanceId = instanceId;
/*  48 */     this.neutralBossBean = (NeutralBossBean)JsonTableService.getJsonData(instanceId, NeutralBossBean.class);
/*  49 */     this.sustainedTime = this.neutralBossBean.getRunTime();
/*  50 */     this.freshTime = this.neutralBossBean.getReviveTime();
/*  51 */     this.remainTime = TimeUtil.currentTime() + this.sustainedTime;
/*  52 */     if (BossUtil.checkStartRefrsh()) {
/*  53 */       this.open = true;
/*  54 */       this.nextTime = TimeUtil.currentTime() + this.freshTime;
/*     */     } 
/*     */   }
/*     */   
/*     */   public short initAlienRaceBoss() {
/*  59 */     if (null == this.neutralBossBean) {
/*  60 */       return 10301;
/*     */     }
/*  62 */     this.monsterFightSide = new MonsterFightSide(this.neutralBossBean, (byte)1);
/*  63 */     this.monsterFightSide.initGuid((byte)1);
/*     */     
/*  65 */     FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/*  66 */     this.maxHp = fightGroup.getAllMaxHp();
/*  67 */     this.curHp = fightGroup.getAllHp();
/*  68 */     this.belongToId = 0L;
/*  69 */     this.playerTime = 0;
/*  70 */     this.playerName = "";
/*  71 */     this.isInit = true;
/*  72 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short check() {
/*  81 */     if (null == this.neutralBossBean) {
/*  82 */       return 10301;
/*     */     }
/*  84 */     if (!this.open) {
/*  85 */       return 10302;
/*     */     }
/*  87 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateHp(long playerId, String name) {
/*  94 */     FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/*  95 */     long temp = fightGroup.getAllHp();
/*  96 */     long delta = this.curHp - temp;
/*  97 */     LogUtils.errorLog(new Object[] { "rankBossFight", Integer.valueOf(this.neutralBossBean.getId()), Long.valueOf(playerId), Long.valueOf(temp), Long.valueOf(this.curHp), Long.valueOf(delta) });
/*     */     
/*  99 */     this.curHp = temp;
/* 100 */     if (this.curHp <= 0L) {
/* 101 */       this.belongToId = playerId;
/* 102 */       bossDie();
/*     */     } 
/*     */     
/* 105 */     broadcast();
/*     */   }
/*     */   
/*     */   public void bossDie() {
/* 109 */     if (!this.open) {
/*     */       return;
/*     */     }
/* 112 */     LogUtils.errorLog(new Object[] { "neutralBossBeanDead", Integer.valueOf(this.neutralBossBean.getId()), Long.valueOf(this.belongToId) });
/* 113 */     this.open = false;
/* 114 */     sendReward();
/*     */   }
/*     */   
/*     */   public void bossBorn() {
/* 118 */     if (this.open) {
/*     */       return;
/*     */     }
/* 121 */     this.remainTime = TimeUtil.currentTime() + this.sustainedTime;
/* 122 */     this.nextTime = TimeUtil.currentTime() + this.freshTime;
/* 123 */     this.open = true;
/* 124 */     initAlienRaceBoss();
/* 125 */     sendRebornNotice();
/* 126 */     LogUtils.errorLog(new Object[] { "neutralBossBeanReBorn", Integer.valueOf(this.neutralBossBean.getId()) });
/*     */   }
/*     */   
/*     */   public void timeCheck() {
/* 130 */     if (!this.isInit) {
/*     */       return;
/*     */     }
/*     */     
/* 134 */     int time = TimeUtil.currentTime();
/* 135 */     if (this.open) {
/* 136 */       if (!BossUtil.checkStartRefrsh()) {
/* 137 */         this.curHp = 0L;
/* 138 */         this.open = false;
/* 139 */         this.nextTime = 0;
/*     */         return;
/*     */       } 
/* 142 */       if (this.belongToId != 0L && time >= this.playerTime && this.playerTime != 0) {
/* 143 */         this.curHp = 0L;
/* 144 */         this.open = false;
/* 145 */         sendReward();
/*     */       } 
/* 147 */       if (0 != this.remainTime && time >= this.remainTime) {
/* 148 */         LogUtils.errorLog(new Object[] { "neutralBossBeanTimeOut", Integer.valueOf(this.neutralBossBean.getId()) });
/* 149 */         this.curHp = 0L;
/* 150 */         this.open = false;
/* 151 */         this.belongToId = 0L;
/* 152 */         this.playerTime = 0;
/*     */       } 
/*     */     } else {
/*     */       
/* 156 */       if (!BossUtil.checkStartRefrsh()) {
/* 157 */         this.nextTime = 0;
/*     */         return;
/*     */       } 
/* 160 */       if (time >= this.nextTime) {
/* 161 */         this.nextTime = time + this.neutralBossBean.getReviveTime();
/* 162 */         this.remainTime = time + this.neutralBossBean.getRunTime();
/* 163 */         this.open = true;
/* 164 */         initAlienRaceBoss();
/* 165 */         sendRebornNotice();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void broadcast() {
/* 175 */     NeutralBossPushResponse response = new NeutralBossPushResponse();
/* 176 */     response.data = BossUtil.getAlienRaceBossData(this.instanceId);
/* 177 */     LookUpService.broadcast((ResponseBase)response);
/*     */   }
/*     */   
/*     */   private void sendRebornNotice() {
/* 181 */     PlayerUtil.sendNotice(13, null, this.neutralBossBean.getLevel(), null);
/*     */   }
/*     */   
/*     */   private void sendReward() {
/* 185 */     if (this.curHp <= 0L || (TimeUtil.currentTime() >= this.playerTime && this.belongToId != 0L)) {
/* 186 */       BossHomeComponent bossHomeComponent = (BossHomeComponent)LookUpService.getComponent(this.belongToId, BossHomeComponent.class);
/* 187 */       BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 188 */       if (null != bossHomeComponent && bossHomeComponent.getAlienCount() <= bossHomeParameter.getAlienRewardCount() + bossHomeComponent.getBuyAlienCount()) {
/* 189 */         bossHomeComponent.setAlienCount(bossHomeComponent.getAlienCount() + 1);
/* 190 */         bossHomeComponent.saveToDB();
/* 191 */         String title = LanguageConstant.getLanguage(307);
/* 192 */         String content = LanguageConstant.getAndReplaceLanguage(308, new String[] { this.neutralBossBean.getLevel() + "" });
/* 193 */         ArrayList<Reward> list = new ArrayList<>();
/* 194 */         list.addAll(FinanceUtil.transform(this.neutralBossBean.getBelongReward()));
/* 195 */         list.addAll(FinanceUtil.transform(this.neutralBossBean.getProReward()));
/* 196 */         ArrayList<Reward> list1 = FinanceUtil.rewardGet(list);
/* 197 */         MilitaryUtil.getRewardResult(list1, MilitaryInsType.RankBoss.getType(), this.belongToId, 0);
/* 198 */         MailUtil.sendSysMail(this.belongToId, list1, title, content);
/* 199 */         TaskComponent taskComponent = (TaskComponent)LookUpService.getComponent(this.belongToId, TaskComponent.class);
/* 200 */         if (null != taskComponent) {
/* 201 */           taskComponent.refreshSchedule(TaskType.AlientBossReward, 0, 1L);
/* 202 */           taskComponent.refreshSchedule(TaskType.ChangeNeutralBoss, 0, 1L);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 207 */     this.belongToId = 0L;
/* 208 */     this.playerTime = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetMonsterFightSide() {
/* 213 */     for (FightGroup fightGroup : this.monsterFightSide.getGroupList()) {
/* 214 */       for (IFighter fighter : fightGroup.getFighters(true)) {
/* 215 */         fighter.clearBuff();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateBelongToId(long playerId, String playerName) {
/* 223 */     this.playerName = playerName;
/* 224 */     if (playerId != this.belongToId) {
/* 225 */       this.belongToId = playerId;
/* 226 */       BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 227 */       this.playerTime = TimeUtil.currentTime() + bossHomeParameter.getBelongToTime();
/*     */     } 
/* 229 */     broadcast();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMaxHp() {
/* 238 */     return this.maxHp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getCurHp() {
/* 247 */     return this.curHp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRemainTime() {
/* 257 */     return this.remainTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNextTime() {
/* 266 */     return this.nextTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOpen() {
/* 275 */     return this.open;
/*     */   }
/*     */   
/*     */   public int getInstanceId() {
/* 279 */     return this.instanceId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide getMonsterFightSide() {
/* 288 */     return this.monsterFightSide;
/*     */   }
/*     */   
/*     */   public long getBelongToId() {
/* 292 */     return this.belongToId;
/*     */   }
/*     */   
/*     */   public String getPlayerName() {
/* 296 */     return this.playerName;
/*     */   }
/*     */   
/*     */   public int getPlayerTime() {
/* 300 */     return this.playerTime;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\side\AlienRaceBossFightSide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */