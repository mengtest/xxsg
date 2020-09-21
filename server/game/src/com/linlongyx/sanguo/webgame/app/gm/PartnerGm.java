/*     */ package com.linlongyx.sanguo.webgame.app.gm;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterReincarnBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.GetReincarnInfoProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerDestinyUpProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerListProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerReincarnProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatByChannelResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.GetReincarnInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerDestinyUpRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerListRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerReincarnRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatInfo;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PartnerGm
/*     */   implements IGm
/*     */ {
/*     */   public void gm(IPlayerSession playerSession, String[] strings) {
/*  43 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/*  44 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().getComponent(PartnerComponent.class);
/*  45 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().getComponent(BagComponent.class);
/*  46 */     if (playerComponent == null)
/*  47 */       return;  if (strings[2].equals("addPartner")) {
/*  48 */       int cardId = Integer.parseInt(strings[3]);
/*  49 */       boolean isHash = partnerComponent.checkPartner(cardId);
/*  50 */       FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(cardId, FighterBean.class);
/*  51 */       if (null == fighterBean) {
/*     */         return;
/*     */       }
/*  54 */       if (isHash) {
/*  55 */         ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(fighterBean.getPiece(), ItemBean.class);
/*  56 */         if (null == itemBean) {
/*     */           return;
/*     */         }
/*     */         
/*  60 */         ArrayList<Reward> rewardList = FinanceUtil.transformPiceReward(itemBean.getReward(), fighterBean.getPieceNum());
/*  61 */         FinanceUtil.reward(rewardList, playerSession.getPlayer(), ResourceEvent.gmAdd, true);
/*     */       } else {
/*  63 */         partnerComponent.addPartner(playerSession.getPlayer(), cardId, ResourceEvent.gmAdd, true);
/*     */       } 
/*  65 */       bagComponent.addItem(fighterBean.getCardBook(), 1, ResourceEvent.gmAdd, true);
/*  66 */     } else if (strings[2].equals("addPartner2")) {
/*  67 */       int cardId = Integer.parseInt(strings[3]);
/*  68 */       int stars = Integer.parseInt(strings[4]);
/*  69 */       partnerComponent.addPartner2(playerSession.getPlayer(), cardId, stars, ResourceEvent.gmAdd, true);
/*  70 */     } else if (strings[2].equals("getPartner")) {
/*  71 */       PartnerListRequest request = new PartnerListRequest();
/*  72 */       (new PartnerListProcessor()).handle(playerSession, (RequestBase)request);
/*  73 */     } else if (strings[2].equals("deletePartner")) {
/*  74 */       ArrayList<IMapEntity> partnerEntities = new ArrayList<>(partnerComponent.getEntityMap().values());
/*  75 */       for (IMapEntity iMapEntity : partnerEntities) {
/*  76 */         PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  77 */         partnerComponent.getProxy().delEntity(String.valueOf(partnerEntity.getPid()));
/*     */       } 
/*  79 */     } else if (strings[2].equals("destiny")) {
/*  80 */       long pid = Integer.parseInt(strings[3]);
/*  81 */       IntIntValue intIntValue = new IntIntValue();
/*  82 */       intIntValue.key = 50000001;
/*  83 */       intIntValue.value = 100;
/*  84 */       PartnerDestinyUpRequest request = new PartnerDestinyUpRequest();
/*  85 */       request.pid = pid;
/*  86 */       (new PartnerDestinyUpProcessor()).handle(playerSession, (RequestBase)request);
/*  87 */     } else if (strings[2].equals("reinInfo")) {
/*  88 */       GetReincarnInfoRequest request = new GetReincarnInfoRequest();
/*  89 */       request.pid = Integer.parseInt(strings[3]);
/*  90 */       (new GetReincarnInfoProcessor()).handle(playerSession, (RequestBase)request);
/*  91 */     } else if (strings[2].equals("rein")) {
/*  92 */       PartnerReincarnRequest request = new PartnerReincarnRequest();
/*  93 */       request.pid = Integer.parseInt(strings[3]);
/*  94 */       request.reincarnId = Integer.parseInt(strings[4]);
/*  95 */       (new PartnerReincarnProcessor()).handle(playerSession, (RequestBase)request);
/*  96 */     } else if (strings[2].equals("reined")) {
/*  97 */       long pid = Integer.parseInt(strings[3]);
/*  98 */       long num = Integer.parseInt(strings[4]);
/*  99 */       ArrayList<Integer> reinIds = new ArrayList<>();
/* 100 */       Map<Integer, Object> map = JsonTableService.getJsonTable(FighterReincarnBean.class);
/* 101 */       reinIds.addAll(map.keySet());
/*     */       
/* 103 */       if (pid == -1L) {
/*     */         
/* 105 */         Collections.sort(reinIds);
/* 106 */         FighterBean fighterBean = null;
/* 107 */         if (pid == -1L) {
/* 108 */           fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*     */         } else {
/* 110 */           PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 111 */           if (null != partnerEntity) {
/* 112 */             fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/*     */           }
/*     */         } 
/* 115 */         int value = 0;
/* 116 */         for (Iterator<Integer> iterator = fighterBean.getReincarn().iterator(); iterator.hasNext(); ) { int reinId = ((Integer)iterator.next()).intValue();
/* 117 */           if (pid == -1L) {
/* 118 */             playerComponent.getReincarnationIds().add(Integer.valueOf(reinId));
/* 119 */             playerComponent.setReincarnationIds(playerComponent.getReincarnationIds());
/*     */           } else {
/* 121 */             PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 122 */             if (null != partnerEntity) {
/* 123 */               partnerEntity.getReincarnationIds().add(Integer.valueOf(reinId));
/* 124 */               partnerComponent.updateReincarnationIdsDB(pid);
/*     */             } 
/*     */           } 
/* 127 */           value++;
/* 128 */           if (value >= num) {
/*     */             break;
/*     */           } }
/*     */       
/*     */       } 
/* 133 */     } else if (strings[2].equals("attribute")) {
/*     */       
/* 135 */       long pid = Integer.parseInt(strings[3]);
/* 136 */       boolean hash = false;
/* 137 */       if (pid == playerComponent.getLeaderId()) {
/* 138 */         pid = -1L;
/* 139 */         hash = true;
/*     */       } else {
/* 141 */         for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/* 142 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 143 */           if (partnerEntity.getTableId() == pid) {
/* 144 */             pid = partnerEntity.getPid();
/* 145 */             hash = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 150 */       if (!hash) {
/*     */         return;
/*     */       }
/* 153 */       ChatByChannelResponse chatByChannelResponse = new ChatByChannelResponse();
/* 154 */       ChatInfo chatInfo = new ChatInfo();
/* 155 */       chatInfo.type = 0;
/* 156 */       chatInfo.sendPlayerId = playerSession.getPlayer().getPlayerId();
/* 157 */       chatInfo.sendPlayerName = playerSession.getPlayer().getPlayerName();
/* 158 */       chatInfo.vip = playerComponent.getVip();
/* 159 */       chatInfo.titleId = playerComponent.getWearTitle();
/* 160 */       chatInfo.sex = playerComponent.getSex();
/* 161 */       chatInfo.head = PlayerUtil.getHeadUrl(playerSession.getPlayer().getPlayerId());
/* 162 */       ArrayList<Long> attributes = new ArrayList<>();
/* 163 */       if (pid == -1L) {
/* 164 */         playerComponent.getPlayerAttrUp().getAttrList(attributes, pid);
/*     */       } else {
/* 166 */         partnerComponent.getPartnerAttrUp().getAttrList(attributes, pid);
/*     */       } 
/* 168 */       chatInfo.context = attributes.toString();
/* 169 */       chatByChannelResponse.list.add(chatInfo);
/* 170 */       chatInfo.time = TimeUtil.currentTime();
/* 171 */       playerSession.sendMessage((ResponseBase)chatByChannelResponse);
/* 172 */     } else if (strings[2].equals("showBattle")) {
/*     */       
/* 174 */       Map<Long, Integer> map = new HashMap<>();
/* 175 */       for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/* 176 */         if (pid == -1L) {
/* 177 */           map.put(Long.valueOf(pid), Integer.valueOf(playerComponent.getLeaderId())); continue;
/*     */         } 
/* 179 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 180 */         if (null != partnerEntity) {
/* 181 */           map.put(Long.valueOf(pid), Integer.valueOf(partnerEntity.getTableId()));
/*     */         } }
/*     */ 
/*     */       
/* 185 */       ChatByChannelResponse chatByChannelResponse = new ChatByChannelResponse();
/* 186 */       ChatInfo chatInfo = new ChatInfo();
/* 187 */       chatInfo.type = 0;
/* 188 */       chatInfo.sendPlayerId = playerSession.getPlayer().getPlayerId();
/* 189 */       chatInfo.sendPlayerName = playerSession.getPlayer().getPlayerName();
/* 190 */       chatInfo.vip = playerComponent.getVip();
/* 191 */       chatInfo.titleId = playerComponent.getWearTitle();
/* 192 */       chatInfo.sex = playerComponent.getSex();
/* 193 */       chatInfo.head = PlayerUtil.getHeadUrl(playerSession.getPlayer().getPlayerId());
/* 194 */       ArrayList<Long> attributes = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 200 */       chatInfo.context = map.toString();
/* 201 */       chatByChannelResponse.list.add(chatInfo);
/* 202 */       chatInfo.time = TimeUtil.currentTime();
/* 203 */       playerSession.sendMessage((ResponseBase)chatByChannelResponse);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\PartnerGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */