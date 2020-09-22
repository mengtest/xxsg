/*     */ package linlongyx.sanguo.webgame.service;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.player.Player;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import linlongyx.sanguo.webgame.processors.login.LoginUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LookUpService
/*     */ {
/*  24 */   private static AtomicBoolean running = new AtomicBoolean(true);
/*     */   
/*  26 */   private static final Map<Long, IPlayer> playerIdToPlayer = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IPlayer getByPlayerId(long playerId) {
/*  35 */     return playerIdToPlayer.get(Long.valueOf(playerId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void add(IPlayer player) {
/*  44 */     playerIdToPlayer.put(Long.valueOf(player.getPlayerId()), player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void remove(long userId, long playerId) {
/*  53 */     playerIdToPlayer.remove(Long.valueOf(playerId));
/*     */   }
/*     */   
/*     */   public static void sendMessage(long playerId, ResponseBase response) {
/*  57 */     if (playerIdToPlayer.containsKey(Long.valueOf(playerId))) {
/*  58 */       IPlayer player = playerIdToPlayer.get(Long.valueOf(playerId));
/*  59 */       if (null != player && null != player.getSession()) {
/*  60 */         player.getSession().sendMessage(response);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PlayerData getPlayerData(long playerId) {
/*  70 */     if (playerIdToPlayer.containsKey(Long.valueOf(playerId))) {
/*  71 */       Player player = (Player)playerIdToPlayer.get(Long.valueOf(playerId));
/*  72 */       return player.getPlayerData();
/*     */     } 
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void shutdown() {
/*  84 */     LogUtil.errorLog(new Object[] { "LookUpService::shutdown", TimeUtil.getFormatDate() });
/*  85 */     playerIdToPlayer.values().forEach(IPlayer::saveAll);
/*  86 */     playerIdToPlayer.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Long> getOnlinePlayer() {
/*  95 */     return new ArrayList<>((Collection<? extends Long>)playerIdToPlayer.values().stream().filter(player -> player.getSession().isLogin()).map(IPlayer::getPlayerId).collect(Collectors.toList()));
/*     */   }
/*     */   
/*     */   public static void broadcast(ResponseBase response) {
/*  99 */     playerIdToPlayer.values().stream().filter(player -> (player.getSession() != null)).forEach(player -> player.getSession().sendMessage(response));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isOnline(long playerId) {
/* 107 */     if (!playerIdToPlayer.containsKey(Long.valueOf(playerId)))
/* 108 */       return false; 
/* 109 */     IPlayer player = playerIdToPlayer.get(Long.valueOf(playerId));
/* 110 */     return player.getSession().isLogin();
/*     */   }
/*     */   
/*     */   public static void logoutWithoutNotice(IPlayer player) {
/* 114 */     if (null == player)
/*     */       return; 
/* 116 */     if (player.getSession() != null) {
/* 117 */       player.logout(false);
/* 118 */       player.getSession().setLogin(false);
/* 119 */       player.getSession().getTcpSender().close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void logout(IPlayer player) {
/* 124 */     if (null == player)
/*     */       return; 
/* 126 */     if (player.getSession() != null) {
/* 127 */       LoginUtil.loginCrossLogout(player.getSession());
/* 128 */       player.logout(false);
/* 129 */       player.getSession().setLogin(false);
/* 130 */       player.getSession().getTcpSender().close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void beUserLogout(long userId) {
/* 135 */     System.out.println("beUserLogout");
/* 136 */     playerIdToPlayer.values().forEach(iPlayer -> {
/*     */           if (iPlayer.getUserId() == userId)
/*     */             logout(iPlayer); 
/*     */         });
/*     */   }
/*     */   
/*     */   public static void bePlayerLogout(long playerId) {
/* 143 */     System.out.println("bePlayerLogout");
/* 144 */     if (playerIdToPlayer.containsKey(Long.valueOf(playerId))) {
/* 145 */       logout(playerIdToPlayer.get(Long.valueOf(playerId)));
/*     */     }
/*     */   }
/*     */   
/*     */   public static void allLogout() {
/* 150 */     System.out.println("allLogout");
/* 151 */     playerIdToPlayer.values().forEach(LookUpService::logout);
/*     */   }
/*     */   
/*     */   public static void allLogoutForJmx() {
/* 155 */     allLogout();
/* 156 */     setRunning(new AtomicBoolean(false));
/*     */   }
/*     */   
/*     */   public static void runningTrue() {
/* 160 */     setRunning(new AtomicBoolean(true));
/*     */   }
/*     */   
/*     */   public static AtomicBoolean getRunning() {
/* 164 */     return running;
/*     */   }
/*     */   
/*     */   public static void setRunning(AtomicBoolean running) {
/* 168 */     LookUpService.running = running;
/*     */   }
/*     */   
/*     */   public static int onlineNum() {
/* 172 */     return playerIdToPlayer.size();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\service\LookUpService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */