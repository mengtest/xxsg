/*     */ package com.linlongyx.sanguo.webgame.processors.chat;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.logclient.ChatLogClient;
/*     */ import com.linlongyx.sanguo.logclient.event.DefaultChatEvent;
/*     */ import com.linlongyx.sanguo.logclient.event.Event;
/*     */ import com.linlongyx.sanguo.logclient.event.Events;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LanguageBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.KeyValueConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.welfare.WelfareUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatByChannelResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatInfo;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class ChatUtils
/*     */ {
/*  33 */   private static LinkedList<ChatInfo> msgList = new LinkedList<>();
/*  34 */   private static final Object LOCK = new Object();
/*     */   
/*  36 */   private static int firstTime = TimeUtil.currentTime();
/*  37 */   private static int nextTime = 0;
/*  38 */   private static int fundAddNextTime = 0;
/*  39 */   private static int groupChargeAddNextTime = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendSysChat(String context) {
/*  45 */     ChatByChannelResponse chatByChannelResponse = new ChatByChannelResponse();
/*  46 */     ChatInfo chatInfo = new ChatInfo();
/*  47 */     chatInfo.type = 2;
/*  48 */     chatInfo.sendPlayerId = 0L;
/*  49 */     chatInfo.sendPlayerName = "";
/*  50 */     chatInfo.vip = 0;
/*  51 */     chatInfo.titleId = 0;
/*  52 */     chatInfo.sex = 0;
/*  53 */     chatInfo.head = "";
/*  54 */     chatInfo.context = context;
/*  55 */     chatInfo.time = TimeUtil.currentTime();
/*  56 */     chatInfo.teamChat = 2;
/*  57 */     chatByChannelResponse.list.add(chatInfo);
/*  58 */     LookUpService.broadcast((ResponseBase)chatByChannelResponse);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addChatList(ChatInfo data) {
/*  66 */     synchronized (LOCK) {
/*  67 */       ChatInfo chatInfo = new ChatInfo();
/*  68 */       chatInfo.type = data.type;
/*  69 */       chatInfo.sendPlayerId = data.sendPlayerId;
/*  70 */       chatInfo.sendPlayerName = data.sendPlayerName;
/*  71 */       chatInfo.vip = data.vip;
/*  72 */       chatInfo.titleId = data.titleId;
/*  73 */       chatInfo.sex = data.sex;
/*  74 */       chatInfo.head = data.head;
/*  75 */       chatInfo.context = data.context;
/*  76 */       chatInfo.time = data.time;
/*  77 */       chatInfo.quality = data.quality;
/*  78 */       msgList.add(chatInfo);
/*  79 */       while (msgList.size() > 5) {
/*  80 */         msgList.removeFirst();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendChatList(IPlayerSession playerSession) {
/*  90 */     List<ChatInfo> list = new ArrayList<>();
/*  91 */     synchronized (LOCK) {
/*  92 */       if (msgList.size() == 0) {
/*     */         return;
/*     */       }
/*  95 */       list.addAll(msgList);
/*     */     } 
/*  97 */     ChatByChannelResponse chatByChannelResponse = new ChatByChannelResponse();
/*  98 */     chatByChannelResponse.list.addAll(list);
/*  99 */     playerSession.sendMessage((ResponseBase)chatByChannelResponse);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clearChatList() {
/* 106 */     synchronized (LOCK) {
/* 107 */       msgList.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendPlayerChatLog(int type, long playerId, String playerName, String context, long channelUID, String uid, int ip) {
/* 112 */     ChatLogClient chatLogClient = (ChatLogClient)AppContext.getBean("chatLogClient");
/* 113 */     if (chatLogClient.isOpen()) {
/*     */       
/* 115 */       DefaultChatEvent defaultChatEvent = new DefaultChatEvent(Events.getEventId(2, type), Long.parseLong(AppContext.getServerId()), playerId, playerName, context, channelUID, uid, ip);
/* 116 */       chatLogClient.sendMessage((Event)defaultChatEvent);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void randomRebootChat() {
/* 124 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*     */     
/* 126 */     int second = TimeUtil.getNowSecond();
/* 127 */     if (second < ((Integer)loginParameter.getSendRebotTime().get(0)).intValue() || second > ((Integer)loginParameter.getSendRebotTime().get(1)).intValue()) {
/*     */       return;
/*     */     }
/*     */     
/* 131 */     int openDay = 0;
/* 132 */     int curDay = TimeUtil.getDayDisTime(MContext.getOpenTimeInt());
/* 133 */     ArrayList<Integer> arrayList = new ArrayList<>(loginParameter.getDayToSecond().keySet());
/* 134 */     Collections.sort(arrayList);
/* 135 */     for (Iterator<Integer> iterator = arrayList.iterator(); iterator.hasNext(); ) { int day = ((Integer)iterator.next()).intValue();
/* 136 */       if (curDay <= day) {
/* 137 */         openDay = day;
/*     */         break;
/*     */       }  }
/*     */     
/* 141 */     if (openDay == 0) {
/* 142 */       openDay = ((Integer)arrayList.get(arrayList.size() - 1)).intValue();
/*     */     }
/*     */     
/* 145 */     int time = ((Integer)loginParameter.getDayToSecond().get(Integer.valueOf(openDay))).intValue();
/* 146 */     if (nextTime == 0) {
/* 147 */       nextTime = firstTime + time;
/*     */     }
/* 149 */     if (TimeUtil.currentTime() >= nextTime) {
/*     */       
/* 151 */       int randomNotice = RandUtil.randomList(loginParameter.getSendRebotId());
/*     */       
/* 153 */       int randomVip = RandUtil.randNum(((Integer)loginParameter.getRebotVip().get(0)).intValue(), ((Integer)loginParameter.getRebotVip().get(1)).intValue());
/* 154 */       nextTime += time;
/* 155 */       LanguageBean languageBean = (LanguageBean)JsonTableService.getJsonData(randomNotice, LanguageBean.class);
/* 156 */       if (null == languageBean) {
/*     */         return;
/*     */       }
/* 159 */       String text = languageBean.getText();
/* 160 */       if (text.contains("{vipLevel}")) {
/* 161 */         text = text.replace("{vipLevel}", randomVip + "");
/*     */       }
/* 163 */       sendSysChat(text);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void groupAdd(boolean isFund) {
/* 171 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*     */     
/* 173 */     int openDay = 0;
/* 174 */     int curDay1 = TimeUtil.getDayDiffToOpen(MContext.getOpenTimeInt(), TimeUtil.currentTime());
/* 175 */     if (curDay1 <= 0) {
/*     */       return;
/*     */     }
/* 178 */     int curDay = TimeUtil.getDayDisTime(MContext.getOpenTimeInt());
/* 179 */     ArrayList<Integer> arrayList = null;
/* 180 */     if (isFund) {
/* 181 */       arrayList = new ArrayList<>(loginParameter.getFundAdd().keySet());
/*     */     } else {
/* 183 */       arrayList = new ArrayList<>(loginParameter.getChargeAdd().keySet());
/*     */     } 
/* 185 */     Collections.sort(arrayList);
/* 186 */     for (Iterator<Integer> iterator = arrayList.iterator(); iterator.hasNext(); ) { int day = ((Integer)iterator.next()).intValue();
/* 187 */       if (curDay <= day) {
/* 188 */         openDay = day;
/*     */         break;
/*     */       }  }
/*     */     
/* 192 */     if (openDay == 0) {
/* 193 */       openDay = ((Integer)arrayList.get(arrayList.size() - 1)).intValue();
/*     */     }
/* 195 */     if (isFund) {
/*     */       
/* 197 */       int time = ((Integer)loginParameter.getFundAdd().get(Integer.valueOf(openDay))).intValue();
/* 198 */       if (fundAddNextTime == 0) {
/* 199 */         fundAddNextTime = firstTime + time;
/*     */       }
/* 201 */       if (TimeUtil.currentTime() >= fundAddNextTime) {
/* 202 */         fundAddNextTime += time;
/* 203 */         int num = WelfareUtil.growfundNum.incrementAndGet();
/* 204 */         PlayerUtil.sendKeyValue(KeyValueConstant.GROUPCHARGE.getKey(), num);
/* 205 */         LogUtil.errorLog(new Object[] { "groupAddFund", Integer.valueOf(WelfareUtil.growfundNum.get()), Integer.valueOf(curDay) });
/*     */       } 
/*     */     } else {
/*     */       
/* 209 */       int time = ((Integer)loginParameter.getChargeAdd().get(Integer.valueOf(openDay))).intValue();
/* 210 */       if (groupChargeAddNextTime == 0) {
/* 211 */         groupChargeAddNextTime = firstTime + time;
/*     */       }
/* 213 */       if (TimeUtil.currentTime() >= groupChargeAddNextTime && !MContext.isHeFu()) {
/* 214 */         groupChargeAddNextTime += time;
/* 215 */         int num = WelfareUtil.groupCharegeNum.incrementAndGet();
/* 216 */         PlayerUtil.sendKeyValue(KeyValueConstant.GROUPCHARGE.getKey(), num);
/* 217 */         LogUtil.errorLog(new Object[] { "groupAdd", Integer.valueOf(WelfareUtil.groupCharegeNum.get()), Integer.valueOf(curDay) });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\chat\ChatUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */