/*     */ package com.linlongyx.sanguo.webgame.processors.cardbook;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.cardbook.CardBookComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.NameDataBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.CardBookParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.chat.ChatUtils;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookAskGiftRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatByChannelResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatInfo;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CardBookUtil
/*     */ {
/*     */   public static void cardBookAsk(IPlayerSession playerSession, CardBookAskGiftRequest request, int type, ItemBean itemBean, long infoId) {
/*     */     TaskComponent taskComponent;
/*     */     GroupMemberComponent groupMemberComponent;
/*     */     GroupMemberEntity memberEntity;
/*  48 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  49 */     ChatByChannelResponse response = new ChatByChannelResponse();
/*  50 */     String context = "";
/*  51 */     if (type == 0) {
/*  52 */       context = LanguageConstant.getAndReplaceLanguage(5006, new String[] { playerComponent.getPlayerName(), itemBean.getName(), String.valueOf(infoId), String.valueOf(request.itemId), String.valueOf(playerComponent.getPlayerId()) });
/*     */     } else {
/*  54 */       context = LanguageConstant.getAndReplaceLanguage(5005, new String[] { playerComponent.getPlayerName(), itemBean.getName(), String.valueOf(infoId), String.valueOf(request.itemId), String.valueOf(playerComponent.getPlayerId()) });
/*     */     } 
/*  56 */     ArrayList<ChatInfo> list = new ArrayList<>();
/*  57 */     ChatInfo chatInfo = new ChatInfo();
/*  58 */     chatInfo.type = (byte)request.chatType;
/*  59 */     chatInfo.sendPlayerId = playerSession.getPlayer().getPlayerId();
/*  60 */     chatInfo.sendPlayerName = playerSession.getPlayer().getPlayerName();
/*  61 */     chatInfo.vip = playerComponent.getVip();
/*  62 */     chatInfo.titleId = playerComponent.getWearTitle();
/*  63 */     chatInfo.sex = playerComponent.getSex();
/*  64 */     chatInfo.head = PlayerUtil.getHeadUrl(playerSession.getPlayer().getPlayerId());
/*  65 */     chatInfo.context = context;
/*  66 */     chatInfo.time = TimeUtil.currentTime();
/*  67 */     chatInfo.quality = playerComponent.getQuality();
/*  68 */     list.add(chatInfo);
/*  69 */     response.list = list;
/*  70 */     switch (request.chatType) {
/*     */       case 0:
/*  72 */         LookUpService.broadcast((ResponseBase)response);
/*  73 */         taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  74 */         if (taskComponent != null) {
/*  75 */           taskComponent.refreshSchedule(TaskType.ChatWorld, 0, 1L);
/*     */         }
/*  77 */         ChatUtils.addChatList(chatInfo);
/*     */         break;
/*     */       case 1:
/*  80 */         groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/*  81 */         memberEntity = (GroupMemberEntity)groupMemberComponent.getEntity();
/*  82 */         if (memberEntity != null && memberEntity.getId() != 0L) {
/*  83 */           GroupService blocBaseService = (GroupService)MContext.getBean("groupService");
/*  84 */           GroupEntity groupEntity = blocBaseService.getGroupEntity(memberEntity.getId());
/*  85 */           if (null != groupEntity) {
/*  86 */             LookUpService.campBoradcast((ResponseBase)response, groupEntity);
/*     */           }
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void robtorResponse(long playerId) {
/*  99 */     CardBookComponent cardBookComponent = (CardBookComponent)LookUpService.getComponent(playerId, CardBookComponent.class);
/* 100 */     CardBookParameter cardBookParameter = (CardBookParameter)ParameterConstant.getParameter(50);
/* 101 */     for (Iterator<Long> iterator = cardBookComponent.getEndTime().keySet().iterator(); iterator.hasNext(); ) { long infoId = ((Long)iterator.next()).longValue();
/* 102 */       if (((Integer)cardBookComponent.getEndTime().get(Long.valueOf(infoId))).intValue() > TimeUtil.currentTime()) {
/*     */         continue;
/*     */       }
/* 105 */       if (null == cardBookComponent.getItemAsk().get(Long.valueOf(infoId))) {
/*     */         continue;
/*     */       }
/* 108 */       if (cardBookComponent.getGetGive().containsKey(Long.valueOf(infoId))) {
/*     */         continue;
/*     */       }
/* 111 */       int itemId = ((Integer)cardBookComponent.getItemAsk().get(Long.valueOf(infoId))).intValue();
/* 112 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/* 113 */       if (null == itemBean) {
/*     */         continue;
/*     */       }
/* 116 */       if (cardBookParameter.getColorList().indexOf(Integer.valueOf(itemId)) < 0) {
/* 117 */         if (itemBean.getItemQuality() > cardBookParameter.getResponesQuality()) {
/*     */           continue;
/*     */         }
/* 120 */         if (cardBookComponent.getBottomCardAsk() >= cardBookParameter.getBomCardAskLimit()) {
/*     */           continue;
/*     */         }
/*     */       } else {
/* 124 */         if (itemBean.getItemQuality() > cardBookParameter.getResColorDanQuality()) {
/*     */           continue;
/*     */         }
/* 127 */         if (cardBookComponent.getColorDanAsk() >= cardBookParameter.getColorDanAsLimit()) {
/*     */           continue;
/*     */         }
/*     */       } 
/* 131 */       ArrayList<Reward> rewards = new ArrayList<>();
/* 132 */       Reward reward = new Reward();
/* 133 */       reward.type = 2;
/* 134 */       reward.id = itemId;
/* 135 */       reward.num = 1L;
/* 136 */       rewards.add(reward);
/*     */       
/* 138 */       cardBookComponent.getGetGive().put(Long.valueOf(infoId), Long.valueOf(-1L));
/* 139 */       if (cardBookParameter.getColorList().indexOf(Integer.valueOf(itemId)) >= 0) {
/* 140 */         cardBookComponent.setColorDanAsk(cardBookComponent.getColorDanAsk() + 1);
/*     */       } else {
/* 142 */         cardBookComponent.setBottomCardAsk(cardBookComponent.getBottomCardAsk() + 1);
/*     */       } 
/* 144 */       String name = getRobatorName(playerId);
/* 145 */       String title2 = LanguageConstant.getAndReplaceLanguage(5001, new String[0]);
/* 146 */       String content2 = LanguageConstant.getAndReplaceLanguage(5002, new String[] { name, itemBean.getName() });
/* 147 */       MailUtil.sendSysMail(playerId, rewards, title2, content2); }
/*     */   
/*     */   }
/*     */   
/*     */   public static String getRobatorName(long playerId) {
/* 152 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/* 153 */     if (null == playerComponent) {
/* 154 */       return "";
/*     */     }
/* 156 */     StringBuilder builder = new StringBuilder();
/* 157 */     Map<Integer, Object> map = JsonTableService.getJsonTable(NameDataBean.class);
/* 158 */     int rand = RandUtil.randNum(1, map.size());
/* 159 */     int rand1 = RandUtil.randNum(1, map.size());
/* 160 */     int rand2 = RandUtil.randNum(1, map.size());
/* 161 */     NameDataBean sex = (NameDataBean)JsonTableService.getJsonData(rand, NameDataBean.class);
/* 162 */     NameDataBean name1 = (NameDataBean)JsonTableService.getJsonData(rand1, NameDataBean.class);
/* 163 */     NameDataBean name2 = (NameDataBean)JsonTableService.getJsonData(rand2, NameDataBean.class);
/* 164 */     if (playerComponent.getSex() == 1) {
/* 165 */       builder.append(sex.getA());
/* 166 */       builder.append(name1.getB());
/* 167 */       if (null != name2) {
/* 168 */         builder.append(name2.getC());
/*     */       }
/*     */     } else {
/* 171 */       builder.append(sex.getA());
/* 172 */       builder.append(name1.getD());
/* 173 */       if (null != name2) {
/* 174 */         builder.append(name2.getE());
/*     */       }
/*     */     } 
/* 177 */     return builder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void checkAsk() {
/* 184 */     for (Iterator<Long> iterator = LookUpService.getOnlinePlayer().iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/* 185 */       robtorResponse(playerId); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/* 190 */   private static HashSet<Long> playerIds = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CardBookComponent getCardBookComponent(long playerId) {
/* 196 */     if (playerIds.contains(Long.valueOf(playerId))) {
/* 197 */       return null;
/*     */     }
/* 199 */     playerIds.add(Long.valueOf(playerId));
/* 200 */     return (CardBookComponent)LookUpService.getComponent(playerId, CardBookComponent.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void saveToDB(CardBookComponent cardBookComponent) {
/* 205 */     cardBookComponent.saveToDB();
/* 206 */     playerIds.remove(Long.valueOf(cardBookComponent.getPlayerId()));
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cardbook\CardBookUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */