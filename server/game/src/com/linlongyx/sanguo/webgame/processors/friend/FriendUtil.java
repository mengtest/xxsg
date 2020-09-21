/*     */ package com.linlongyx.sanguo.webgame.processors.friend;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.group.GroupUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendStateNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FriendInfo;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FriendUtil
/*     */ {
/*     */   public static void loginNotice(IPlayer iPlayer) {
/*  29 */     FriendStateNoticeResponse friendStateNoticeResponse = new FriendStateNoticeResponse();
/*  30 */     friendStateNoticeResponse.type = 3;
/*  31 */     friendStateNoticeResponse.playerId = iPlayer.getPlayerId();
/*  32 */     friendStateNoticeResponse.name = iPlayer.getPlayerName();
/*  33 */     sendResponseToOnlineFriend(iPlayer, (ResponseBase)friendStateNoticeResponse);
/*     */   }
/*     */   
/*     */   public static void logoutNotice(IPlayer iPlayer) {
/*  37 */     FriendStateNoticeResponse friendStateNoticeResponse = new FriendStateNoticeResponse();
/*  38 */     friendStateNoticeResponse.type = 4;
/*  39 */     friendStateNoticeResponse.playerId = iPlayer.getPlayerId();
/*  40 */     friendStateNoticeResponse.name = iPlayer.getPlayerName();
/*  41 */     sendResponseToOnlineFriend(iPlayer, (ResponseBase)friendStateNoticeResponse);
/*     */   }
/*     */   
/*     */   private static void sendResponseToOnlineFriend(IPlayer iPlayer, ResponseBase responseBase) {
/*  45 */     FriendComponent friendComponent = (FriendComponent)iPlayer.createIfNotExist(FriendComponent.class);
/*  46 */     for (Long playerId : friendComponent.getIds()) {
/*  47 */       if (LookUpService.getByPlayerId(playerId.longValue()) != null && 
/*  48 */         LookUpService.getByPlayerId(playerId.longValue()).getSession() != null && 
/*  49 */         LookUpService.getByPlayerId(playerId.longValue()).getSession().isLogin()) {
/*  50 */         LookUpService.getByPlayerId(playerId.longValue()).getSession().sendMessage(responseBase);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int getLogoutTime(PlayerComponent playerComponent) {
/*  56 */     if (LookUpService.isOnline(playerComponent.getPlayerId())) {
/*  57 */       return 0;
/*     */     }
/*  59 */     return playerComponent.getLoginOutTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static HashSet<Long> getFriendInfos(ArrayList<FriendInfo> list, HashSet<Long> playerIds, FriendComponent myFriendComponent, HashSet<Long> friendIds) {
/*  69 */     int size = playerIds.size();
/*  70 */     if (0 == size) {
/*  71 */       return friendIds;
/*     */     }
/*  73 */     String add = "(";
/*  74 */     int i = 0;
/*  75 */     for (Long id : playerIds) {
/*  76 */       add = add + id + ((i == size - 1) ? "" : ",");
/*  77 */       i++;
/*     */     } 
/*  79 */     add = add + ")";
/*     */ 
/*     */     
/*     */     try {
/*  83 */       MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  84 */       JdbcTemplate template = mysql.getTemplate();
/*  85 */       String selectSql = "SELECT distinct p.playerId,p.playerName,p.head,p.sex,p.level,p.loginOutTime,p.totalValue,p.vip  FROM tb_player p where p.playerId IN " + add;
/*     */ 
/*     */       
/*  88 */       List<Map<String, Object>> info = template.queryForList(selectSql);
/*  89 */       for (Map<String, Object> map : info) {
/*  90 */         FriendInfo friendInfo = new FriendInfo();
/*  91 */         long playerId = ((Long)map.get("playerId")).longValue();
/*     */         
/*  93 */         friendIds.add(Long.valueOf(playerId));
/*     */         
/*  95 */         friendInfo.playerId = playerId;
/*  96 */         friendInfo.playerName = (String)map.get("playerName");
/*     */         
/*  98 */         String head = (String)map.get("head");
/*     */         
/* 100 */         friendInfo.head = head;
/* 101 */         friendInfo.level = ((Integer)map.get("level")).intValue();
/* 102 */         friendInfo.logoutTime = LookUpService.isOnline(playerId) ? 0 : ((Integer)map.get("loginOutTime")).intValue();
/* 103 */         friendInfo.fightVale = ((Long)map.get("totalValue")).longValue();
/* 104 */         friendInfo.blocName = GroupUtil.getGroupName(playerId);
/* 105 */         friendInfo.vip = Byte.parseByte(map.get("vip").toString());
/* 106 */         if (myFriendComponent.getSendIds().contains(Long.valueOf(playerId))) {
/* 107 */           friendInfo.sendState = 1;
/*     */         }
/* 109 */         if (myFriendComponent.getNeedReceiveIds().contains(Long.valueOf(playerId))) {
/* 110 */           if (!myFriendComponent.getReceivedIds().contains(Long.valueOf(playerId))) {
/* 111 */             friendInfo.receiveState = 1;
/*     */           } else {
/* 113 */             friendInfo.receiveState = 2;
/*     */           } 
/*     */         }
/* 116 */         friendInfo.favorValue = myFriendComponent.getFavorValue(playerId);
/* 117 */         list.add(friendInfo);
/*     */       } 
/* 119 */     } catch (Exception e) {
/* 120 */       LogUtil.errorLog(new Object[] { "FriendUtil::getFriendInfos", Arrays.toString((Object[])e.getStackTrace()) });
/* 121 */       e.printStackTrace();
/*     */     } 
/* 123 */     return friendIds;
/*     */   }
/*     */   
/*     */   public static FriendInfo buildFriendInfo(PlayerComponent playerComponent) {
/* 127 */     FriendInfo friendInfo = new FriendInfo();
/* 128 */     friendInfo.playerId = playerComponent.getPlayerId();
/* 129 */     friendInfo.playerName = playerComponent.getPlayerName();
/* 130 */     friendInfo.head = PlayerUtil.getHeadUrl(playerComponent.getPlayerId());
/* 131 */     friendInfo.level = playerComponent.getLevel();
/* 132 */     friendInfo.logoutTime = playerComponent.getLoginOutTime();
/* 133 */     friendInfo.fightVale = playerComponent.getTotalValue();
/* 134 */     friendInfo.logoutTime = getLogoutTime(playerComponent);
/* 135 */     friendInfo.blocName = GroupUtil.getGroupName(playerComponent.getPlayerId());
/* 136 */     friendInfo.vip = playerComponent.getVip();
/* 137 */     return friendInfo;
/*     */   }
/*     */   
/*     */   public static FriendInfo buildFriendInfo(Long myPlayerId, PlayerComponent friendPlayerComponent) {
/* 141 */     FriendInfo friendInfo = new FriendInfo();
/* 142 */     friendInfo.playerId = friendPlayerComponent.getPlayerId();
/* 143 */     friendInfo.playerName = friendPlayerComponent.getPlayerName();
/* 144 */     friendInfo.head = PlayerUtil.getHeadUrl(friendPlayerComponent.getPlayerId());
/* 145 */     friendInfo.level = friendPlayerComponent.getLevel();
/* 146 */     friendInfo.fightVale = friendPlayerComponent.getTotalValue();
/* 147 */     friendInfo.logoutTime = getLogoutTime(friendPlayerComponent);
/* 148 */     friendInfo.blocName = GroupUtil.getGroupName(friendPlayerComponent.getPlayerId());
/* 149 */     friendInfo.vip = friendPlayerComponent.getVip();
/* 150 */     FriendComponent friendComponent = (FriendComponent)LookUpService.getComponent(myPlayerId.longValue(), FriendComponent.class);
/* 151 */     if (friendComponent != null) {
/* 152 */       if (friendComponent.getSendIds().contains(Long.valueOf(friendPlayerComponent.getPlayerId()))) {
/* 153 */         friendInfo.sendState = 1;
/*     */       }
/* 155 */       if (friendComponent.getNeedReceiveIds().contains(Long.valueOf(friendPlayerComponent.getPlayerId()))) {
/* 156 */         if (!friendComponent.getReceivedIds().contains(Long.valueOf(friendPlayerComponent.getPlayerId()))) {
/* 157 */           friendInfo.receiveState = 1;
/*     */         } else {
/* 159 */           friendInfo.receiveState = 2;
/*     */         } 
/*     */       }
/* 162 */       friendInfo.favorValue = friendComponent.getFavorValue(friendPlayerComponent.getPlayerId());
/*     */     } 
/* 164 */     return friendInfo;
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
/*     */   public static void selectFriend(IPlayer iPlayer, byte sex, long favorValue, ArrayList<KeyValue> list) {
/* 225 */     FriendComponent friendComponent = (FriendComponent)iPlayer.createIfNotExist(FriendComponent.class);
/* 226 */     for (Long playerId : friendComponent.getIds()) {
/* 227 */       if (friendComponent.getFavorValue(playerId.longValue()) < favorValue) {
/*     */         continue;
/*     */       }
/* 230 */       IPlayer otherPlayer = LookUpService.getByPlayerId(playerId.longValue());
/* 231 */       if (otherPlayer != null) {
/* 232 */         PlayerComponent playerComponent = (PlayerComponent)otherPlayer.getComponent(PlayerComponent.class);
/*     */         
/* 234 */         if (playerComponent.getSex() != sex) {
/*     */           continue;
/*     */         }
/* 237 */         KeyValue keyValue = new KeyValue();
/* 238 */         keyValue.value = otherPlayer.getPlayerId();
/* 239 */         keyValue.valueStr = otherPlayer.getPlayerName();
/* 240 */         list.add(keyValue);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void checkFriend() {
/* 249 */     MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 250 */     JdbcTemplate template = mysql.getTemplate();
/* 251 */     String selectSql = "SELECT playerId FROM tb_friend;";
/* 252 */     List<Map<String, Object>> info = template.queryForList(selectSql);
/* 253 */     for (Map<String, Object> map : info) {
/* 254 */       Long playerId = Long.valueOf(((Long)map.get("playerId")).longValue());
/* 255 */       FriendComponent friendComponent = (FriendComponent)LookUpService.getComponent(playerId.longValue(), FriendComponent.class);
/* 256 */       if (null == friendComponent)
/*     */         continue; 
/* 258 */       for (Long id : friendComponent.getIds()) {
/* 259 */         FriendComponent otherFriendComponent = (FriendComponent)LookUpService.getComponent(id.longValue(), FriendComponent.class);
/* 260 */         if (null == otherFriendComponent || !otherFriendComponent.getIds().contains(playerId)) {
/* 261 */           System.out.println(playerId + ":" + id);
/*     */         }
/*     */       } 
/*     */     } 
/* 265 */     System.out.println("end");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */