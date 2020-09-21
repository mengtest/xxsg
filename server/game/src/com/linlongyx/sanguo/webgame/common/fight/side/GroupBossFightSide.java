/*     */ package com.linlongyx.sanguo.webgame.common.fight.side;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.type.GroupBossFight;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BlocBossBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.fight.ResultUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.group.GroupUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupInsNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GroupDamageData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GroupBossFightSide
/*     */ {
/*     */   private long groupId;
/*     */   private int instanceId;
/*     */   private BlocBossBean blocBossBean;
/*     */   private long maxHp;
/*  43 */   private Map<Byte, Long> curHp = new HashMap<>();
/*     */   private MonsterFightSide monsterFightSide;
/*  45 */   private HashMap<Long, GroupDamageData> damageMap = new HashMap<>();
/*  46 */   private Lock lock = new ReentrantLock();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short initGroupBoss(long groupId, boolean force) {
/*  52 */     this.groupId = groupId;
/*  53 */     GroupEntity groupEntity = GroupUtil.getGroupEntity(groupId);
/*  54 */     if (groupEntity == null) {
/*  55 */       return 11104;
/*     */     }
/*  57 */     if (groupEntity.getInsId() == 0) {
/*  58 */       Iterator<Integer> iterator = JsonTableService.getJsonTableKey(BlocBossBean.class).iterator();
/*  59 */       Integer firstId = iterator.next();
/*  60 */       BlocBossBean blocBossBean = (BlocBossBean)JsonTableService.getJsonData(firstId.intValue(), BlocBossBean.class);
/*  61 */       this.instanceId = blocBossBean.getId();
/*  62 */       this.blocBossBean = blocBossBean;
/*     */     } else {
/*  64 */       this.instanceId = groupEntity.getInsId();
/*  65 */       this.damageMap.putAll(groupEntity.getDamageRecords());
/*  66 */       this.blocBossBean = (BlocBossBean)JsonTableService.getJsonData(this.instanceId, BlocBossBean.class);
/*     */     } 
/*     */     
/*  69 */     if (null == this.blocBossBean) {
/*  70 */       return 10301;
/*     */     }
/*  72 */     this.monsterFightSide = new MonsterFightSide(this.blocBossBean, (byte)1);
/*  73 */     this.monsterFightSide.initGuid((byte)1);
/*     */     
/*  75 */     FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/*  76 */     this.maxHp = fightGroup.getAllMaxHp();
/*  77 */     if (force) {
/*  78 */       for (IFighter fighter : fightGroup.getFighters(true)) {
/*  79 */         this.curHp.put(Byte.valueOf(fighter.getGuid()), Long.valueOf(fighter.getHP()));
/*     */       }
/*     */     } else {
/*  82 */       this.curHp = groupEntity.getBossHp();
/*  83 */       if (this.curHp.isEmpty()) {
/*  84 */         for (IFighter fighter : fightGroup.getFighters(true)) {
/*  85 */           this.curHp.put(Byte.valueOf(fighter.getGuid()), Long.valueOf(fighter.getHP()));
/*     */         }
/*     */       }
/*     */       
/*  89 */       if (getCurTotalHp() <= 0L) {
/*  90 */         if (JsonTableService.getJsonTableKey(BlocBossBean.class).contains(Integer.valueOf(this.instanceId + 1)))
/*     */         {
/*     */ 
/*     */           
/*  94 */           for (IFighter fighter : fightGroup.getFighters(true)) {
/*  95 */             this.curHp.put(Byte.valueOf(fighter.getGuid()), Long.valueOf(fighter.getHP()));
/*     */           }
/*     */         }
/*     */       } else {
/*     */         
/* 100 */         for (IFighter fighter : this.monsterFightSide.getCurGroup().getFighters(true)) {
/* 101 */           if (this.curHp.containsKey(Byte.valueOf(fighter.getGuid()))) {
/* 102 */             fighter.setAttr(AttributeType.HP.getType(), ((Long)this.curHp.get(Byte.valueOf(fighter.getGuid()))).longValue());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 108 */     groupEntity.setBossHp(this.curHp);
/* 109 */     groupEntity.setInsId(this.instanceId);
/*     */     
/* 111 */     return 0;
/*     */   }
/*     */   
/*     */   public long getCurTotalHp() {
/* 115 */     long totalHp = 0L;
/* 116 */     for (Iterator<Long> iterator = this.curHp.values().iterator(); iterator.hasNext(); ) { long hp = ((Long)iterator.next()).longValue();
/* 117 */       totalHp += hp; }
/*     */     
/* 119 */     return totalHp;
/*     */   }
/*     */ 
/*     */   
/*     */   public short handleFight(IPlayer iPlayer, FightRecordResponse response) {
/*     */     try {
/* 125 */       this.lock.lock();
/*     */       
/* 127 */       GroupBossFight groupBossFight = new GroupBossFight();
/*     */       
/* 129 */       PlayerFightSide playerFightSide = FightUtil.initPlayerFightSide(iPlayer);
/* 130 */       FightUtil.addTempAttribute(iPlayer, 14, playerFightSide);
/*     */       
/* 132 */       playerFightSide.initGuid((byte)0);
/* 133 */       groupBossFight.initSide(0, playerFightSide);
/*     */       
/* 135 */       FightUtil.clearBuff(this.monsterFightSide);
/* 136 */       FightUtil.removeDeadFighters(this.monsterFightSide);
/* 137 */       groupBossFight.initSide(1, this.monsterFightSide);
/*     */ 
/*     */       
/* 140 */       groupBossFight.start(iPlayer, response);
/* 141 */       updateHp(iPlayer);
/* 142 */       return 0;
/*     */     } finally {
/* 144 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void saveGroupBoss() {
/* 149 */     GroupEntity groupEntity = GroupUtil.getGroupEntity(this.groupId);
/* 150 */     if (null != groupEntity) {
/* 151 */       groupEntity.setInsId(this.instanceId);
/* 152 */       groupEntity.setBossHp(this.curHp);
/* 153 */       groupEntity.setDamageRecords(this.damageMap);
/*     */     } 
/*     */   }
/*     */   
/*     */   public short resetToTargetIns(int insId) {
/*     */     try {
/* 159 */       this.lock.lock();
/* 160 */       BlocBossBean blocBossBean = (BlocBossBean)JsonTableService.getJsonData(insId, BlocBossBean.class);
/* 161 */       if (blocBossBean == null) {
/* 162 */         return 11129;
/*     */       }
/* 164 */       this.instanceId = insId;
/* 165 */       this.blocBossBean = blocBossBean;
/* 166 */       this.monsterFightSide = new MonsterFightSide(blocBossBean, (byte)1);
/* 167 */       this.monsterFightSide.initGuid((byte)1);
/*     */       
/* 169 */       FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/* 170 */       this.maxHp = fightGroup.getAllMaxHp();
/*     */       
/* 172 */       this.curHp.clear();
/* 173 */       for (IFighter fighter : fightGroup.getFighters(true)) {
/* 174 */         this.curHp.put(Byte.valueOf(fighter.getGuid()), Long.valueOf(fighter.getHP()));
/*     */       }
/* 176 */       saveGroupBoss();
/* 177 */       return 0;
/*     */     } finally {
/* 179 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateHp(IPlayer iPlayer) {
/*     */     try {
/* 190 */       this.lock.lock();
/* 191 */       FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/* 192 */       long temp = fightGroup.getAllHp();
/* 193 */       long preHp = getCurTotalHp();
/* 194 */       long delta = preHp - temp;
/* 195 */       LogUtils.errorLog(new Object[] { "groupBossFight", Integer.valueOf(this.instanceId), Long.valueOf(iPlayer.getPlayerId()), Long.valueOf(temp), Long.valueOf(preHp), Long.valueOf(delta) });
/* 196 */       updatePlayerDamage(iPlayer, delta);
/* 197 */       updateCurHp();
/* 198 */       if (temp <= 0L) {
/* 199 */         BlocBossBean nextBossBean = (BlocBossBean)JsonTableService.getJsonData(this.instanceId + 1, BlocBossBean.class);
/* 200 */         LogUtils.errorLog(new Object[] { Long.valueOf(this.groupId), "groupBossDead", Integer.valueOf(this.instanceId), ", killer", Long.valueOf(iPlayer.getPlayerId()) });
/* 201 */         updateGroupMemberRewrad();
/* 202 */         if (nextBossBean != null) {
/* 203 */           resetToTargetIns(nextBossBean.getId());
/*     */         }
/*     */       } 
/* 206 */       broadcast(delta, iPlayer.getPlayerName());
/*     */     } finally {
/* 208 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateCurHp() {
/*     */     try {
/* 217 */       this.lock.lock();
/* 218 */       this.curHp.clear();
/* 219 */       FightGroup fightGroup = this.monsterFightSide.getCurGroup();
/* 220 */       for (IFighter fighter : fightGroup.getFighters(true)) {
/* 221 */         this.curHp.put(Byte.valueOf(fighter.getGuid()), Long.valueOf(fighter.getHP()));
/*     */       }
/*     */     } finally {
/*     */       
/* 225 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updatePlayerDamage(IPlayer player, long damage) {
/*     */     GroupDamageData groupDamageData;
/* 231 */     Iterator<Integer> it = this.blocBossBean.getChall().keySet().iterator();
/* 232 */     int damageId = ((Integer)it.next()).intValue();
/* 233 */     while (it.hasNext() && damageId < damage) {
/* 234 */       damageId = ((Integer)it.next()).intValue();
/*     */     }
/* 236 */     ArrayList<Reward> rewardList = FinanceUtil.transform(((BlocBossBean.ChallBean)this.blocBossBean.getChall().get(Integer.valueOf(damageId))).getReward());
/* 237 */     FinanceUtil.reward(rewardList, player, ResourceEvent.GroupBossFight, true);
/* 238 */     ResultUtil.saveResult(player.getPlayerId(), (byte)14, this.instanceId, 3, rewardList, 0);
/*     */ 
/*     */     
/* 241 */     if (!this.damageMap.containsKey(Long.valueOf(player.getPlayerId()))) {
/* 242 */       PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 243 */       GroupMemberComponent groupMemberComponent = (GroupMemberComponent)player.createIfNotExist(GroupMemberComponent.class);
/* 244 */       groupDamageData = new GroupDamageData();
/* 245 */       groupDamageData.playerId = player.getPlayerId();
/* 246 */       groupDamageData.playerName = player.getPlayerName();
/* 247 */       groupDamageData.head = playerComponent.getHead();
/* 248 */       groupDamageData.fightCount = groupMemberComponent.getTotalTimes() + 1;
/* 249 */       groupDamageData.maxDamage = damage;
/*     */     } else {
/* 251 */       groupDamageData = this.damageMap.get(Long.valueOf(player.getPlayerId()));
/* 252 */       groupDamageData.fightCount++;
/* 253 */       groupDamageData.maxDamage = (groupDamageData.maxDamage < damage) ? damage : groupDamageData.maxDamage;
/*     */     } 
/* 255 */     this.damageMap.putIfAbsent(Long.valueOf(player.getPlayerId()), groupDamageData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateGroupMemberRewrad() {
/* 263 */     GroupEntity groupEntity = GroupUtil.getGroupEntity(this.groupId);
/* 264 */     Set<Long> memberList = groupEntity.getMemberList();
/* 265 */     for (Iterator<Long> iterator = memberList.iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/* 266 */       GroupMemberComponent groupMemberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId, GroupMemberComponent.class);
/* 267 */       if (groupMemberComponent != null) {
/* 268 */         groupMemberComponent.getPlayerRewards().putIfAbsent(Integer.valueOf(this.instanceId), Integer.valueOf(1));
/* 269 */         groupMemberComponent.setPlayerRewards(groupMemberComponent.getPlayerRewards());
/* 270 */         groupMemberComponent.saveToDB();
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void broadcast(long damage, String playerName) {
/* 279 */     GroupInsNoticeResponse response = new GroupInsNoticeResponse();
/* 280 */     response.insId = this.instanceId;
/* 281 */     response.curHp = getCurTotalHp();
/* 282 */     response.maxHp = this.maxHp;
/* 283 */     response.damage = damage;
/* 284 */     response.playerName = playerName;
/* 285 */     LookUpService.campBoradcast((ResponseBase)response, GroupUtil.getGroupEntity(this.groupId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMaxHp() {
/* 294 */     return this.maxHp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Byte, Long> getCurHp() {
/* 303 */     return this.curHp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInstanceId() {
/* 311 */     return this.instanceId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterFightSide getMonsterFightSide() {
/* 320 */     return this.monsterFightSide;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\side\GroupBossFightSide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */