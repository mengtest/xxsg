/*    */ package com.linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.ChatUtil;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossChatByChannelRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossChatByChannelResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatInfo;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossChatByChannelProcessor
/*    */   extends ProcessorBase<CrossChatByChannelRequest, CrossChatByChannelResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new CrossChatByChannelResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossChatByChannelRequest request) {
/* 33 */     PlayerData playerData = LookUpService.getPlayerData(playerSession.getPlayer().getPlayerId());
/* 34 */     if (playerData == null) return 0;
/*    */     
/* 36 */     if (PlayerUtil.isPrivilege(playerData.getPlayerId(), 1)) {
/* 37 */       return 10021;
/*    */     }
/* 39 */     ChatInfo chatInfo = new ChatInfo();
/* 40 */     chatInfo.type = request.type;
/* 41 */     chatInfo.sendPlayerId = playerSession.getPlayer().getPlayerId();
/* 42 */     chatInfo.sendPlayerName = playerSession.getPlayer().getPlayerName();
/* 43 */     chatInfo.vip = playerData.getVip();
/* 44 */     chatInfo.titleId = playerData.getTitleId();
/* 45 */     chatInfo.sex = playerData.getSex();
/* 46 */     chatInfo.head = playerData.getHead();
/* 47 */     chatInfo.context = ChatUtil.replaceSensitiveWord(request.context, "*");
/* 48 */     chatInfo.time = TimeUtil.currentTime();
/* 49 */     chatInfo.quality = playerData.getQuality();
/* 50 */     ((CrossChatByChannelResponse)this.response).list.add(chatInfo);
/*    */     
/* 52 */     switch (request.type) {
/*    */       case 0:
/* 54 */         LookUpService.getOnlinePlayer().stream().filter(id -> (id.longValue() != playerSession.getPlayer().getPlayerId())).forEach(id -> LookUpService.getByPlayerId(id.longValue()).getSession().sendMessage(this.response));
/*    */         break;
/*    */ 
/*    */       
/*    */       case 1:
/* 59 */         LookUpService.getOnlinePlayer().stream().filter(id -> (id.longValue() != playerSession.getPlayer().getPlayerId())).forEach(id -> LookUpService.getByPlayerId(id.longValue()).getSession().sendMessage(this.response));
/*    */         break;
/*    */ 
/*    */       
/*    */       case 2:
/* 64 */         BattleUtil.broadcastCampNotice(playerData.getPlayerId(), this.response);
/*    */         break;
/*    */     } 
/*    */     
/* 68 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossChatByChannelProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */