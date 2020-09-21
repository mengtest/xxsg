/*     */ package com.linlongyx.sanguo.webgame.processors.offices;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.offices.MilitaryOfficeComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.MilitaryParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.offices.HelpInfoListRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.offices.HelpInfoListResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.HelpInfoData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class HelpInfoListProcessor
/*     */   extends ProcessorBase<HelpInfoListRequest, HelpInfoListResponse> {
/*     */   protected void initResponse() {
/*  27 */     this.response = (ResponseBase)new HelpInfoListResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, HelpInfoListRequest request) {
/*  32 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 80))
/*  33 */       return 10061; 
/*  34 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/*  35 */     if (0L == groupMemberComponent.getId()) {
/*  36 */       return 11101;
/*     */     }
/*  38 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/*  39 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/*  40 */     if (null == groupEntity) {
/*  41 */       return 11101;
/*     */     }
/*  43 */     MilitaryParameter militaryParameter = (MilitaryParameter)ParameterConstant.getParameter(51);
/*  44 */     MilitaryOfficeComponent militaryOfficeComponent = (MilitaryOfficeComponent)playerSession.getPlayer().createIfNotExist(MilitaryOfficeComponent.class);
/*  45 */     ArrayList<Long> offinePlayerIds = new ArrayList<>();
/*  46 */     ArrayList<KeyValue> info = new ArrayList<>();
/*  47 */     long myPlayerId = playerSession.getPlayer().getPlayerId();
/*  48 */     for (Iterator<Long> iterator = groupEntity.getOfficeList().keySet().iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/*  49 */       KeyValue keyValues = (KeyValue)groupEntity.getOfficeList().get(Long.valueOf(playerId));
/*  50 */       if (militaryOfficeComponent.getHelpList().containsKey(Long.valueOf(playerId))) {
/*  51 */         KeyValue kv = (KeyValue)militaryOfficeComponent.getHelpList().get(Long.valueOf(playerId));
/*  52 */         if (kv.key == keyValues.key && kv.value == keyValues.value) {
/*     */           continue;
/*     */         }
/*     */       } 
/*  56 */       if (Integer.parseInt(keyValues.valueStr) >= militaryParameter.getLimitHelp()) {
/*     */         continue;
/*     */       }
/*  59 */       if (LookUpService.getByPlayerId(playerId) == null) {
/*  60 */         offinePlayerIds.add(Long.valueOf(playerId));
/*  61 */         info.add((KeyValue)groupEntity.getOfficeList().get(Long.valueOf(playerId)));
/*     */         continue;
/*     */       } 
/*  64 */       PlayerComponent targetPlayerComponent = LookUpService.getPlayerComponent(playerId);
/*  65 */       if (militaryOfficeComponent.getHelpList().containsKey(Long.valueOf(playerId))) {
/*  66 */         KeyValue keyValue = (KeyValue)militaryOfficeComponent.getHelpList().get(Long.valueOf(playerId));
/*  67 */         if (keyValue.key == keyValues.key && keyValue.value == keyValues.value) {
/*     */           continue;
/*     */         }
/*  70 */         HelpInfoData helpInfoData1 = new HelpInfoData();
/*  71 */         helpInfoData1.playerId = playerId;
/*  72 */         helpInfoData1.head = targetPlayerComponent.getHead();
/*  73 */         helpInfoData1.playerLevel = targetPlayerComponent.getLevel();
/*  74 */         helpInfoData1.playerName = targetPlayerComponent.getPlayerName();
/*  75 */         helpInfoData1.officeId = (int)keyValues.key;
/*  76 */         helpInfoData1.times = Integer.parseInt(keyValues.valueStr);
/*  77 */         helpInfoData1.quality = targetPlayerComponent.getQuality();
/*  78 */         ((HelpInfoListResponse)this.response).list.add(helpInfoData1); continue;
/*     */       } 
/*  80 */       HelpInfoData helpInfoData = new HelpInfoData();
/*  81 */       helpInfoData.playerId = playerId;
/*  82 */       helpInfoData.head = targetPlayerComponent.getHead();
/*  83 */       helpInfoData.playerLevel = targetPlayerComponent.getLevel();
/*  84 */       helpInfoData.playerName = targetPlayerComponent.getPlayerName();
/*  85 */       helpInfoData.quality = targetPlayerComponent.getQuality();
/*  86 */       helpInfoData.officeId = (int)keyValues.key;
/*  87 */       helpInfoData.times = Integer.parseInt(keyValues.valueStr);
/*  88 */       ((HelpInfoListResponse)this.response).list.add(helpInfoData); }
/*     */ 
/*     */ 
/*     */     
/*  92 */     ArrayList<HelpInfoData> data = new ArrayList<>();
/*  93 */     MilitaryUtil.getPlayerInfos(data, offinePlayerIds);
/*  94 */     for (int i = 0; i < offinePlayerIds.size(); i++) {
/*  95 */       for (HelpInfoData helpInfoData : data) {
/*  96 */         if (((Long)offinePlayerIds.get(i)).longValue() == helpInfoData.playerId) {
/*  97 */           helpInfoData.officeId = (int)((KeyValue)info.get(i)).key;
/*  98 */           helpInfoData.times = Integer.parseInt(((KeyValue)info.get(i)).valueStr);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 103 */     ((HelpInfoListResponse)this.response).list.addAll(data);
/* 104 */     ((HelpInfoListResponse)this.response).helpTimes = militaryOfficeComponent.getOfficeHelpTimes();
/* 105 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\offices\HelpInfoListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */