/*     */ package com.linlongyx.sanguo.webgame.common.player;
/*     */ 
/*     */ import com.linlongyx.core.framework.concurrent.Fibers;
/*     */ import com.linlongyx.core.framework.concurrent.JetlangActor;
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.logic.IComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.IEvent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.achieve.AchieveComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.arena.ArenaComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.artifact.ArtifactComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.cardbook.CardBookComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.chat.ChatComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.consume.ConsumeRebateComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.continuity.ContinuityComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.crossRace.CrossRaceComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.destiny.DestinyComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.divine.DivineComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.draw.DrawCardComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.eightGraphic.BaguaComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.invitation.InvitationComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.limit.LimitComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.limitbuy.LimitBuyComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.limitexchange.LimitExchangeComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.luckymoney.LuckyMoneyComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.mail.MailComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.mental.MentalComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.offices.MilitaryOfficeComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.rankAct.RankActComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rebate.RechargeRebateComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rechargeActivity.RechargeActivityComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.recruit.RecruitComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.secreti.SecretiComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.shop.ShopComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.sign.SignComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.skill.SkillComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.souls.SoulsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.stage.StageComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.talisman.TalismanComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.tower.TowerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.turnplate.TurnplateComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.yearbeast.YearBeastComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.zodiac.ZodiacComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*     */ import com.linlongyx.sanguo.webgame.constant.GameConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.JmxAgent;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.TimeUnit;
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
/*     */ 
/*     */ public class Player
/*     */   extends JetlangActor
/*     */   implements IPlayer
/*     */ {
/*     */   private long userId;
/*     */   private long playerId;
/*     */   private String playerName;
/*     */   private IPlayerSession playerSession;
/* 102 */   private Map<String, IComponent> components = new ConcurrentHashMap<>();
/*     */ 
/*     */   
/*     */   public Player(IPlayerSession playerSession) {
/* 106 */     this.playerSession = playerSession;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeFromMap() {
/* 113 */     saveAll();
/* 114 */     System.out.println("removeFromMap: " + this.playerId);
/* 115 */     LookUpService.remove(this.userId, this.playerId);
/* 116 */     JmxAgent.deleteJmx(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getUserId() {
/* 121 */     return this.userId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserId(long userId) {
/* 126 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getPlayerId() {
/* 131 */     return this.playerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayerId(long uniqueKey) {
/*     */     try {
/* 137 */       this.playerId = uniqueKey;
/* 138 */       if (uniqueKey == 0L)
/* 139 */         throw new Exception("setPlayerId:" + uniqueKey); 
/* 140 */     } catch (Exception e) {
/* 141 */       StringWriter writer = new StringWriter();
/* 142 */       e.printStackTrace(new PrintWriter(writer, true));
/* 143 */       LogUtil.errorLog(new Object[] { "Player::setPlayerId exception", writer.toString() });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPlayerName() {
/* 149 */     return this.playerName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayerName(String playerName) {
/* 154 */     this.playerName = playerName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(IPlayerSession session) {
/* 159 */     this.playerSession = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public IPlayerSession getSession() {
/* 164 */     return this.playerSession;
/*     */   }
/*     */ 
/*     */   
/*     */   public void login() {
/* 169 */     initComponents();
/* 170 */     loginScheduler();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/* 178 */     createIfNotExist((Class)AchieveComponent.class);
/* 179 */     createIfNotExist((Class)ArenaComponent.class);
/* 180 */     createIfNotExist((Class)ArtifactComponent.class);
/* 181 */     createIfNotExist((Class)BossHomeComponent.class);
/* 182 */     createIfNotExist((Class)ChatComponent.class);
/* 183 */     createIfNotExist((Class)CardBookComponent.class);
/* 184 */     createIfNotExist((Class)ContinuityComponent.class);
/* 185 */     createIfNotExist((Class)ConsumeRebateComponent.class);
/* 186 */     createIfNotExist((Class)DrawCardComponent.class);
/* 187 */     createIfNotExist((Class)UserComponent.class);
/*     */     
/* 189 */     createIfNotExist((Class)BagComponent.class);
/* 190 */     createIfNotExist((Class)BaguaComponent.class);
/* 191 */     createIfNotExist((Class)EquipComponent.class);
/* 192 */     createIfNotExist((Class)ExtendComponent.class);
/* 193 */     createIfNotExist((Class)FriendComponent.class);
/* 194 */     createIfNotExist((Class)GeneralComponent.class);
/* 195 */     createIfNotExist((Class)GroupMemberComponent.class);
/* 196 */     createIfNotExist((Class)KungFuComponent.class);
/* 197 */     createIfNotExist((Class)LimitComponent.class);
/* 198 */     createIfNotExist((Class)LuckyMoneyComponent.class);
/* 199 */     createIfNotExist((Class)InvitationComponent.class);
/* 200 */     createIfNotExist((Class)MailComponent.class);
/* 201 */     createIfNotExist((Class)MentalComponent.class);
/* 202 */     createIfNotExist((Class)MilitaryOfficeComponent.class);
/* 203 */     createIfNotExist((Class)MountsComponent.class);
/* 204 */     MailUtil.initMailComponent(this);
/* 205 */     createIfNotExist((Class)PlayerComponent.class);
/* 206 */     createIfNotExist((Class)PartnerComponent.class);
/* 207 */     createIfNotExist((Class)RecruitComponent.class);
/* 208 */     createIfNotExist((Class)RankActComponent.class);
/* 209 */     createIfNotExist((Class)RechargeRebateComponent.class);
/* 210 */     createIfNotExist((Class)SanGuoZhiComponent.class);
/* 211 */     createIfNotExist((Class)ShopComponent.class);
/* 212 */     createIfNotExist((Class)SignComponent.class);
/* 213 */     createIfNotExist((Class)SkillComponent.class);
/* 214 */     createIfNotExist((Class)SingleInsComponent.class);
/* 215 */     createIfNotExist((Class)SoulsComponent.class);
/* 216 */     createIfNotExist((Class)StageComponent.class);
/* 217 */     createIfNotExist((Class)TaskComponent.class);
/* 218 */     createIfNotExist((Class)TowerComponent.class);
/* 219 */     createIfNotExist((Class)UnparalleledComponent.class);
/* 220 */     createIfNotExist((Class)WarLineupComponent.class);
/* 221 */     createIfNotExist((Class)WarPetComponent.class);
/* 222 */     createIfNotExist((Class)WelfareComponent.class);
/* 223 */     createIfNotExist((Class)YearBeastComponent.class);
/* 224 */     createIfNotExist((Class)RechargeActivityComponent.class);
/* 225 */     createIfNotExist((Class)CrossRaceComponent.class);
/* 226 */     createIfNotExist((Class)DestinyComponent.class);
/* 227 */     createIfNotExist((Class)LimitBuyComponent.class);
/* 228 */     createIfNotExist((Class)LimitExchangeComponent.class);
/* 229 */     createIfNotExist((Class)SecretiComponent.class);
/* 230 */     createIfNotExist((Class)TalismanComponent.class);
/* 231 */     createIfNotExist((Class)RuneComponent.class);
/* 232 */     createIfNotExist((Class)TurnplateComponent.class);
/* 233 */     createIfNotExist((Class)DivineComponent.class);
/* 234 */     createIfNotExist((Class)ZodiacComponent.class);
/*     */   }
/*     */ 
/*     */   
/*     */   private void loginScheduler() {
/* 239 */     if (this.scheduledFuture != null && !this.scheduledFuture.isCancelled()) {
/* 240 */       this.scheduledFuture.cancel(false);
/*     */     }
/* 242 */     this.scheduledFuture = this.scheduler.scheduleWithFixedDelay(this::scheduleSave, 100L, 100L, TimeUnit.SECONDS);
/*     */   }
/*     */ 
/*     */   
/*     */   public void logout() {
/* 247 */     logout(true);
/*     */   }
/*     */   
/*     */   public void enterLogout() {
/* 251 */     PlayerComponent playerComponent = (PlayerComponent)createIfNotExist((Class)PlayerComponent.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logout(boolean isInPool) {
/* 262 */     loginOutHandle();
/* 263 */     PlayerComponent playerComponent = (PlayerComponent)createIfNotExist((Class)PlayerComponent.class);
/*     */     
/* 265 */     String today = TimeUtil.getFormatDate();
/* 266 */     Date loginDate = playerComponent.getLoginTime();
/* 267 */     String strLoginDate = TimeUtil.getFormatDate(playerComponent.getLoginTime());
/*     */     
/* 269 */     int days = TimeUtil.getDayDiff(((PlayerEntity)playerComponent.getEntity()).getCreateTime());
/*     */     
/* 271 */     LogUtil.gameLog(LogType.LOGIN, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(AppContext.getDebug() ? 0 : 1), today, strLoginDate, today, 
/* 272 */           Integer.valueOf(playerComponent.getIp()), Integer.valueOf(playerComponent.getMainSceneId()) });
/*     */     
/* 274 */     if (this.scheduledFuture != null && !this.scheduledFuture.isCancelled())
/* 275 */       this.scheduledFuture.cancel(false); 
/* 276 */     this.scheduledFuture = this.scheduler.schedule(this::removeFromMap, 10L, TimeUnit.MINUTES);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 285 */     if (!TimeUtil.isSameDate(loginDate) && 
/* 286 */       GameConstant.GAME_REMAIN_DAYS.contains(Integer.valueOf(days - 1))) {
/* 287 */       LogUtil.gameLog(LogType.REMAIN, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(days - 1), today });
/*     */     }
/*     */     
/* 290 */     if (GameConstant.GAME_REMAIN_DAYS.contains(Integer.valueOf(days)))
/* 291 */       LogUtil.gameLog(LogType.REMAIN, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Integer.valueOf(days), today }); 
/* 292 */     if (isInPool) {
/* 293 */       Fibers.createExecutorService().execute(this::saveAll);
/*     */     } else {
/* 295 */       saveAll();
/*     */     } 
/*     */   }
/*     */   public void loginOutHandle() {
/* 299 */     PlayerComponent playerComponent = (PlayerComponent)getComponent(PlayerComponent.class);
/* 300 */     if (null != playerComponent) {
/* 301 */       int curTime = TimeUtil.currentTime();
/* 302 */       playerComponent.setLoginOutTime(curTime);
/* 303 */       int onlineTime = curTime - playerComponent.getLastOnlineTime();
/* 304 */       playerComponent.setOnlineTime(playerComponent.getOnlineTime() + onlineTime);
/* 305 */       TeamUtil.leaveTeam(getPlayerId(), TeamUtil.getTeamIdByPlayerId(getPlayerId()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addComponent(IComponent component) {
/*     */     try {
/* 312 */       if (!(component instanceof UserComponent) && component.getPlayerId() == 0L) {
/* 313 */         LogUtil.errorLog(new Object[] { "Player::addComponent:", "userId: " + getUserId() + " nanoTime: " + System.nanoTime() });
/* 314 */         throw new Exception("addComponent player null: " + component.toString());
/*     */       } 
/* 316 */       this.components.put(component.getType(), component);
/* 317 */     } catch (Exception e) {
/* 318 */       StringWriter writer = new StringWriter();
/* 319 */       e.printStackTrace(new PrintWriter(writer, true));
/* 320 */       LogUtil.errorLog(new Object[] { "Player::addComponent exception", writer.toString() });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IComponent getComponent(Class<?> clazz) {
/* 326 */     return this.components.get(clazz.getSimpleName());
/*     */   }
/*     */ 
/*     */   
/*     */   public IComponent createIfNotExist(Class<? extends IComponent> clazz) {
/* 331 */     IComponent component = this.components.get(clazz.getSimpleName());
/* 332 */     if (null == component) {
/*     */       try {
/* 334 */         if (getPlayerId() == 0L) {
/* 335 */           LogUtil.errorLog(new Object[] { "Player::createIfNotExist:", "userId: " + getUserId() + " nanoTime: " + System.nanoTime() + " com:: " + clazz.getSimpleName() });
/* 336 */           throw new Exception("userId: " + getUserId() + " playerId is 0: " + getPlayerId() + " com : " + clazz.getSimpleName());
/*     */         } 
/* 338 */         Constructor<? extends IComponent> c = clazz.getDeclaredConstructor(new Class[] { long.class });
/* 339 */         component = c.newInstance(new Object[] { Long.valueOf(getPlayerId()) });
/* 340 */         component.init();
/* 341 */         addComponent(component);
/* 342 */       } catch (Exception e) {
/* 343 */         StringWriter writer = new StringWriter();
/* 344 */         e.printStackTrace(new PrintWriter(writer, true));
/* 345 */         LogUtil.errorLog(new Object[] { "Player::createIfNotExist exception", writer.toString() });
/*     */       } 
/*     */     }
/*     */     
/* 349 */     return component;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void act(IEvent iEvent) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void save() {
/* 371 */     for (IComponent com : this.components.values()) {
/* 372 */       com.saveToDB();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveAll() {
/* 380 */     LogUtil.debugLog(new Object[] { "saveAll: " + this.playerId + ", name: " + this.playerName + ", time: " + TimeUtil.getFormatDate() });
/* 381 */     for (IComponent com : this.components.values()) {
/* 382 */       com.saveAllToDB();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void jmxlogout() {
/* 390 */     LookUpService.bePlayerLogout(this.playerId);
/*     */   }
/*     */   
/*     */   public void saveByGm() {
/* 394 */     save();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void scheduleSave() {
/*     */     try {
/* 403 */       save();
/* 404 */     } catch (Exception e) {
/* 405 */       LogUtil.errorLog(new Object[] { "player::scheduleSave:", Long.valueOf(getPlayerId()), TimeUtil.getFormatDate(), Arrays.toString((Object[])e.getStackTrace()) });
/* 406 */       System.out.println("player scheduleSave: " + getPlayerId() + ", e: " + Arrays.toString((Object[])e.getStackTrace()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset(int time) {
/* 412 */     for (IComponent com : this.components.values()) {
/*     */       try {
/* 414 */         com.reset(time);
/* 415 */       } catch (Exception e) {
/* 416 */         LogUtil.errorLog(new Object[] { "player::reset:", com.getType(), TimeUtil.getFormatDate() });
/* 417 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 424 */     StringBuilder stringBuilder = new StringBuilder();
/* 425 */     this.components.values().forEach(iComponent -> stringBuilder.append(iComponent.toString()));
/* 426 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public void setValue(String type, String id, String filedName, String value) {
/* 430 */     IComponent iComponent = this.components.get(type);
/* 431 */     iComponent.setValue(id, filedName, value);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\player\Player.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */