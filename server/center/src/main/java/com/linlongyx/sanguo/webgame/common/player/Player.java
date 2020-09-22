/*     */ package com.linlongyx.sanguo.webgame.common.player;
/*     */ 
/*     */ import com.linlongyx.core.framework.concurrent.JetlangActor;
/*     */ import com.linlongyx.core.framework.logic.IComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.IEvent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.service.JmxAgent;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.util.Arrays;
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
/*     */   private PlayerData playerData;
/*     */   
/*     */   public Player(IPlayerSession playerSession) {
/*  47 */     this.playerSession = playerSession;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeFromMap() {
/*  54 */     System.out.println("removeFromMap: " + this.playerId);
/*  55 */     LookUpService.remove(this.userId, this.playerId);
/*  56 */     JmxAgent.deleteJmx(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getUserId() {
/*  61 */     return this.userId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserId(long userId) {
/*  66 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getPlayerId() {
/*  71 */     return this.playerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayerId(long uniqueKey) {
/*     */     try {
/*  77 */       this.playerId = uniqueKey;
/*  78 */       if (uniqueKey == 0L)
/*  79 */         throw new Exception("setPlayerId:" + uniqueKey); 
/*  80 */     } catch (Exception e) {
/*  81 */       StringWriter writer = new StringWriter();
/*  82 */       e.printStackTrace(new PrintWriter(writer, true));
/*  83 */       LogUtil.errorLog(new Object[] { "Player::setPlayerId exception", writer.toString() });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPlayerName() {
/*  89 */     return this.playerName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayerName(String playerName) {
/*  94 */     this.playerName = playerName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSession(IPlayerSession session) {
/*  99 */     this.playerSession = session;
/*     */   }
/*     */ 
/*     */   
/*     */   public IPlayerSession getSession() {
/* 104 */     return this.playerSession;
/*     */   }
/*     */ 
/*     */   
/*     */   public void login() {
/* 109 */     loginScheduler();
/*     */   }
/*     */ 
/*     */   
/*     */   public void logout() {
/* 114 */     logout(true);
/*     */   }
/*     */ 
/*     */   
/*     */   private void loginScheduler() {
/* 119 */     if (this.scheduledFuture != null && !this.scheduledFuture.isCancelled()) {
/* 120 */       this.scheduledFuture.cancel(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enterLogout() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logout(boolean isInPool) {
/* 138 */     if (this.scheduledFuture != null && !this.scheduledFuture.isCancelled()) {
/* 139 */       this.scheduledFuture.cancel(false);
/*     */     }
/* 141 */     this.scheduledFuture = this.scheduler.schedule(new Runnable()
/*     */         {
/*     */           public void run() {
/* 144 */             Player.this.removeFromMap();
/*     */           }
/*     */         },  5L, TimeUnit.MINUTES);
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
/*     */   public void addComponent(IComponent component) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IComponent getComponent(Class<?> clazz) {
/* 168 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IComponent createIfNotExist(Class<? extends IComponent> clazz) {
/* 173 */     return null;
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
/*     */   public void save() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveAll() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveByGm() {
/* 213 */     save();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void scheduleSave() {
/*     */     try {
/* 222 */       save();
/* 223 */     } catch (Exception e) {
/* 224 */       LogUtil.errorLog(new Object[] { "player::scheduleSave:", Long.valueOf(getPlayerId()), TimeUtil.getFormatDate(), Arrays.toString((Object[])e.getStackTrace()) });
/* 225 */       System.out.println("player scheduleSave: " + getPlayerId() + ", e: " + Arrays.toString((Object[])e.getStackTrace()));
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
/*     */   public void reset(int time) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 246 */     return super.toString();
/*     */   }
/*     */   
/*     */   public PlayerData getPlayerData() {
/* 250 */     return this.playerData;
/*     */   }
/*     */   
/*     */   public void setPlayerData(PlayerData playerData) {
/* 254 */     this.playerData = playerData;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\player\Player.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */