/*     */ package com.linlongyx.sanguo.webgame.common.fight.side;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.comparator.DamageComparator;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BossHomeBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MilitaryInsType;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.bosshome.BossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.offices.MilitaryUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.BossDamagePushResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.RankBossBornNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BossDamageData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RankBossFightSide
/*     */ {
/*     */   private int instanceId;
/*     */   private BossHomeBean bossHomeBean;
/*     */   private long maxHp;
/*     */   private long curHp;
/*  40 */   private List<BossDamageData> damageList = new ArrayList<>();
/*     */   private int remainTime;
/*     */   private int nextTime;
/*     */   private int freshTime;
/*     */   private int sustainedTime;
/*     */   private volatile boolean open;
/*     */   private boolean isInit = false;
/*     */   private long killerId;
/*     */   private MonsterFightSide monsterFightSide;
/*     */   
/*     */   public RankBossFightSide(int instanceId) {
/*  51 */     this.instanceId = instanceId;
/*  52 */     this.bossHomeBean = (BossHomeBean)JsonTableService.getJsonData(instanceId, BossHomeBean.class);
/*  53 */     this.sustainedTime = this.bossHomeBean.getSustainedTime();
/*  54 */     this.freshTime = this.bossHomeBean.getFreshTime();
/*  55 */     this.remainTime = TimeUtil.currentTime() + this.sustainedTime;
/*  56 */     this.open = true;
/*     */   }
/*     */   
/*     */   public short initRankBoss() {
/*  60 */     if (null == this.bossHomeBean) {
/*  61 */       return 10301;
/*     */     }
/*  63 */     this.monsterFightSide = new MonsterFightSide(this.bossHomeBean, (byte)1);
/*  64 */     this.monsterFightSide.initGuid((byte)1);
/*     */     
/*  66 */     FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/*  67 */     this.maxHp = fightGroup.getAllMaxHp();
/*  68 */     this.curHp = fightGroup.getAllHp();
/*     */     
/*  70 */     this.isInit = true;
/*  71 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short check() {
/*  80 */     if (null == this.bossHomeBean) {
/*  81 */       return 10301;
/*     */     }
/*  83 */     if (!this.open) {
/*  84 */       return 10302;
/*     */     }
/*  86 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateHp(long playerId, String name) {
/*  93 */     FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/*  94 */     long temp = fightGroup.getAllHp();
/*  95 */     long delta = this.curHp - temp;
/*  96 */     LogUtils.errorLog(new Object[] { "rankBossFight", Integer.valueOf(this.bossHomeBean.getInsId()), Long.valueOf(playerId), Long.valueOf(temp), Long.valueOf(this.curHp), Long.valueOf(delta) });
/*  97 */     boolean isExist = false;
/*  98 */     for (BossDamageData bossDamageData : this.damageList) {
/*  99 */       if (bossDamageData.fromId == playerId) {
/* 100 */         bossDamageData.damage += delta;
/* 101 */         isExist = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 105 */     if (!isExist) {
/* 106 */       BossDamageData bossDamageData = new BossDamageData();
/* 107 */       bossDamageData.fromId = playerId;
/* 108 */       bossDamageData.name = name;
/* 109 */       bossDamageData.damage = delta;
/* 110 */       this.damageList.add(bossDamageData);
/*     */     } 
/* 112 */     this.damageList.sort((Comparator<? super BossDamageData>)new DamageComparator());
/* 113 */     int rank = 1;
/* 114 */     for (BossDamageData bossDamageData : this.damageList) {
/* 115 */       bossDamageData.rank = rank++;
/*     */     }
/* 117 */     this.curHp = temp;
/* 118 */     if (this.curHp <= 0L) {
/* 119 */       this.killerId = playerId;
/* 120 */       bossDie();
/*     */     } 
/*     */     
/* 123 */     broadcast();
/*     */   }
/*     */   
/*     */   public void bossDie() {
/* 127 */     if (!this.open) {
/*     */       return;
/*     */     }
/* 130 */     LogUtils.errorLog(new Object[] { "rankBossDead", Integer.valueOf(this.bossHomeBean.getInsId()), Long.valueOf(this.killerId) });
/* 131 */     this.nextTime = TimeUtil.currentTime() + this.freshTime;
/* 132 */     this.open = false;
/* 133 */     sendReward();
/*     */   }
/*     */   
/*     */   public void bossBorn() {
/* 137 */     if (this.open) {
/*     */       return;
/*     */     }
/* 140 */     this.remainTime = TimeUtil.currentTime() + this.sustainedTime;
/* 141 */     this.nextTime = this.remainTime + this.freshTime;
/* 142 */     this.open = true;
/* 143 */     this.damageList.clear();
/* 144 */     initRankBoss();
/* 145 */     sendRebornNotice();
/* 146 */     LogUtils.errorLog(new Object[] { "rankBossReBorn", Integer.valueOf(this.bossHomeBean.getInsId()) });
/*     */   }
/*     */   
/*     */   public void timeCheck() {
/* 150 */     if (!this.isInit) {
/*     */       return;
/*     */     }
/* 153 */     int time = TimeUtil.currentTime();
/* 154 */     if (this.open) {
/* 155 */       if (0 != this.remainTime && time >= this.remainTime) {
/* 156 */         LogUtils.errorLog(new Object[] { "rankBossTimeOut", Integer.valueOf(this.bossHomeBean.getInsId()) });
/* 157 */         this.remainTime = 0;
/* 158 */         this.nextTime = time + this.freshTime;
/* 159 */         this.curHp = 0L;
/* 160 */         this.open = false;
/* 161 */         sendReward();
/*     */       }
/*     */     
/* 164 */     } else if (time >= this.nextTime) {
/* 165 */       this.remainTime = time + this.sustainedTime;
/* 166 */       this.nextTime = this.remainTime + this.freshTime;
/* 167 */       this.open = true;
/* 168 */       this.damageList.clear();
/* 169 */       initRankBoss();
/* 170 */       sendRebornNotice();
/* 171 */       LogUtils.errorLog(new Object[] { "rankBossReBorn", Integer.valueOf(this.bossHomeBean.getInsId()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendReward() {
/* 180 */     String title = LanguageConstant.getLanguage(301);
/* 181 */     String content = LanguageConstant.getAndReplaceLanguage(302, new String[] { this.bossHomeBean.getName() });
/*     */     
/* 183 */     Map<Integer, BossHomeBean.RankRewardBean> rankReward = this.bossHomeBean.getRankReward();
/*     */ 
/*     */     
/* 186 */     for (BossDamageData bossDamageData : this.damageList) {
/* 187 */       LogUtils.errorLog(new Object[] { "rankBossReward", Integer.valueOf(this.bossHomeBean.getInsId()), bossDamageData.toString() });
/* 188 */       if (bossDamageData.fromId == this.killerId) {
/* 189 */         ArrayList<Reward> arrayList = new ArrayList<>();
/* 190 */         TaskComponent taskComponent = (TaskComponent)LookUpService.getComponent(bossDamageData.fromId, TaskComponent.class);
/* 191 */         if (null != taskComponent) {
/* 192 */           taskComponent.refreshSchedule(TaskType.KillRankBoss, 0, 1L);
/*     */         }
/* 194 */         this.killerId = 0L;
/* 195 */         arrayList.addAll(FinanceUtil.transform(this.bossHomeBean.getLastHitReward()));
/*     */         
/* 197 */         String kill_title = LanguageConstant.getLanguage(305);
/* 198 */         String kill_content = LanguageConstant.getAndReplaceLanguage(306, new String[] { this.bossHomeBean.getName() });
/* 199 */         MailUtil.sendSysMail(bossDamageData.fromId, FinanceUtil.rewardGet(arrayList), kill_title, kill_content);
/*     */       } 
/* 201 */       ArrayList<Reward> list = new ArrayList<>();
/* 202 */       int index = bossDamageData.rank;
/* 203 */       if (!rankReward.containsKey(Integer.valueOf(index))) {
/* 204 */         index = 0;
/*     */       }
/* 206 */       BossHomeBean.RankRewardBean rankRewardBean = rankReward.get(Integer.valueOf(index));
/* 207 */       if (null != rankRewardBean) {
/* 208 */         ArrayList<Integer> dropId = rankRewardBean.getDropId();
/* 209 */         for (Integer id : dropId) {
/* 210 */           Reward reward = new Reward();
/* 211 */           reward.type = 3;
/* 212 */           reward.id = id.intValue();
/* 213 */           reward.num = 1L;
/* 214 */           list.add(reward);
/*     */         } 
/*     */       } 
/* 217 */       ArrayList<Reward> list1 = FinanceUtil.rewardGet(list);
/* 218 */       MilitaryUtil.getRewardResult(list1, MilitaryInsType.RankBoss.getType(), bossDamageData.fromId, 0);
/* 219 */       MailUtil.sendSysMail(bossDamageData.fromId, list1, title, content);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void broadcast() {
/* 227 */     BossDamagePushResponse response = new BossDamagePushResponse();
/* 228 */     response.data = BossUtil.getBossData(this.instanceId);
/* 229 */     LookUpService.broadcast((ResponseBase)response);
/*     */   }
/*     */   
/*     */   private void sendRebornNotice() {
/* 233 */     RankBossBornNoticeResponse response = new RankBossBornNoticeResponse();
/* 234 */     response.rankBossId = this.instanceId;
/* 235 */     LookUpService.getOnlinePlayer().forEach(playerId -> {
/*     */           IPlayer player = LookUpService.getByPlayerId(playerId.longValue());
/*     */           BossHomeComponent bossHomeComponent = (BossHomeComponent)player.getComponent(BossHomeComponent.class);
/*     */           PlayerComponent playerComponent = (PlayerComponent)player.getComponent(PlayerComponent.class);
/*     */           if (bossHomeComponent != null && playerComponent.getLevel() >= this.bossHomeBean.getLevel() && !bossHomeComponent.getBossNeedNotice().contains(Integer.valueOf(this.instanceId))) {
/*     */             player.getSession().sendMessage((ResponseBase)response);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetMonsterFightSide() {
/* 252 */     for (FightGroup fightGroup : this.monsterFightSide.getGroupList()) {
/* 253 */       for (IFighter fighter : fightGroup.getFighters(true)) {
/* 254 */         fighter.clearBuff();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMaxHp() {
/* 265 */     return this.maxHp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getCurHp() {
/* 274 */     return this.curHp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BossDamageData> getDamageList() {
/* 283 */     return this.damageList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRemainTime() {
/* 292 */     return this.remainTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNextTime() {
/* 301 */     return this.nextTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOpen() {
/* 310 */     return this.open;
/*     */   }
/*     */   
/*     */   public int getInstanceId() {
/* 314 */     return this.instanceId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide getMonsterFightSide() {
/* 323 */     return this.monsterFightSide;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\side\RankBossFightSide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */