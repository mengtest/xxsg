/*     */ package com.linlongyx.sanguo.webgame.processors.task;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ChannelGiftBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.task.ChannelGiftGetRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.task.ChannelGiftGetResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.WanbaUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChannelGiftGetProcessor
/*     */   extends ProcessorBase<ChannelGiftGetRequest, ChannelGiftGetResponse>
/*     */ {
/*  27 */   private static Map<Integer, Short> errorCode = new HashMap<>();
/*     */   static {
/*  29 */     errorCode.put(Integer.valueOf(2000), Short.valueOf((short)15413));
/*  30 */     errorCode.put(Integer.valueOf(2001), Short.valueOf((short)15414));
/*  31 */     errorCode.put(Integer.valueOf(2002), Short.valueOf((short)15415));
/*  32 */     errorCode.put(Integer.valueOf(2003), Short.valueOf((short)15416));
/*  33 */     errorCode.put(Integer.valueOf(2004), Short.valueOf((short)15417));
/*  34 */     errorCode.put(Integer.valueOf(2005), Short.valueOf((short)15418));
/*  35 */     errorCode.put(Integer.valueOf(2006), Short.valueOf((short)15419));
/*  36 */     errorCode.put(Integer.valueOf(2007), Short.valueOf((short)15420));
/*  37 */     errorCode.put(Integer.valueOf(2008), Short.valueOf((short)15421));
/*  38 */     errorCode.put(Integer.valueOf(2009), Short.valueOf((short)15422));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initResponse() {
/*  43 */     this.response = (ResponseBase)new ChannelGiftGetResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, ChannelGiftGetRequest request) {
/*  48 */     int id = request.id;
/*  49 */     ChannelGiftBean channelGiftBean = (ChannelGiftBean)JsonTableService.getJsonData(id, ChannelGiftBean.class);
/*  50 */     if (null == channelGiftBean) {
/*  51 */       return 15426;
/*     */     }
/*  53 */     if (PlayerUtil.getActPlatform() != 4) {
/*  54 */       return 15410;
/*     */     }
/*  56 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  57 */     Map<Integer, Integer> channelGift = taskComponent.getChannelGift();
/*  58 */     Map<Integer, Integer> channelTime = taskComponent.getChannelTime();
/*  59 */     int curTime = TimeUtil.currentTime();
/*  60 */     int preTime = ((Integer)channelGift.getOrDefault(Integer.valueOf(channelGiftBean.getId()), Integer.valueOf(0))).intValue();
/*  61 */     int count = ((Integer)channelTime.getOrDefault(Integer.valueOf(channelGiftBean.getId()), Integer.valueOf(0))).intValue();
/*  62 */     ((ChannelGiftGetResponse)this.response).id = id;
/*  63 */     ((ChannelGiftGetResponse)this.response).state = 2;
/*  64 */     boolean flag = false;
/*  65 */     if (channelGiftBean.getType() == 0) {
/*  66 */       if (channelGiftBean.getTime() == 1) {
/*  67 */         int value = Integer.valueOf(channelGiftBean.getValue()).intValue();
/*  68 */         if (7 == value) {
/*  69 */           if (0 == preTime || !TimeUtil.inSameDay(preTime)) {
/*  70 */             flag = true;
/*     */           }
/*     */         } else {
/*  73 */           if (getWeekday() != value) {
/*  74 */             return 15003;
/*     */           }
/*  76 */           if (0 == preTime || !TimeUtil.inSameDay(preTime)) {
/*  77 */             flag = true;
/*     */           }
/*     */         } 
/*  80 */       } else if (channelGiftBean.getTime() == 2) {
/*  81 */         String[] strings = channelGiftBean.getValue().split(",");
/*  82 */         int startTime = TimeUtil.getTimeFromDate(strings[0]);
/*  83 */         int endTime = TimeUtil.getTimeFromDate(strings[1]);
/*  84 */         if (curTime < startTime || endTime < curTime) {
/*  85 */           return 15411;
/*     */         }
/*  87 */         if (0 == preTime || !TimeUtil.inSameDay(preTime)) {
/*  88 */           flag = true;
/*     */         }
/*     */       } 
/*  91 */     } else if (channelGiftBean.getType() == 1) {
/*  92 */       if (count == 0) {
/*  93 */         flag = true;
/*     */       }
/*  95 */     } else if (channelGiftBean.getType() == 2) {
/*  96 */       int code = WanbaUtil.checkVIPGift(channelGiftBean.getId(), request.openId, request.openKey, request.pf);
/*  97 */       if (0 != code) {
/*  98 */         return ((Short)errorCode.getOrDefault(Integer.valueOf(code), Short.valueOf((short)0))).shortValue();
/*     */       }
/* 100 */       if (count == 0) {
/* 101 */         flag = true;
/*     */       }
/* 103 */     } else if (channelGiftBean.getType() == 3) {
/* 104 */       int code = WanbaUtil.checkVIPGift(channelGiftBean.getId(), request.openId, request.openKey, request.pf);
/* 105 */       if (0 != code) {
/* 106 */         return ((Short)errorCode.getOrDefault(Integer.valueOf(code), Short.valueOf((short)0))).shortValue();
/*     */       }
/* 108 */       if (0 == preTime || !TimeUtil.inSameDay(preTime)) {
/* 109 */         flag = true;
/*     */       }
/*     */     } 
/*     */     
/* 113 */     if (flag) {
/* 114 */       channelGift.put(Integer.valueOf(channelGiftBean.getId()), Integer.valueOf(curTime));
/* 115 */       channelTime.put(Integer.valueOf(channelGiftBean.getId()), Integer.valueOf(count + 1));
/* 116 */       taskComponent.setChannelGift(channelGift);
/* 117 */       taskComponent.setChannelTime(channelTime);
/* 118 */       FinanceUtil.reward(FinanceUtil.transform(channelGiftBean.getReward()), playerSession.getPlayer(), ResourceEvent.ChannelGift, true);
/* 119 */       ((ChannelGiftGetResponse)this.response).state = 1;
/* 120 */       LogUtils.errorLog(new Object[] { "channelGift", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.id) });
/*     */     } 
/*     */     
/* 123 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getWeekday() {
/* 131 */     int nowWeek = TimeUtil.nowWeek();
/* 132 */     return nowWeek;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\ChannelGiftGetProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */