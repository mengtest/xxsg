/*    */ package com.linlongyx.sanguo.webgame.processors.common;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.processors.group.GroupUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.GetPlayerInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.GetPlayerInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetPlayerInfoProcessor
/*    */   extends ProcessorBase<GetPlayerInfoRequest, GetPlayerInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new GetPlayerInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetPlayerInfoRequest request) {
/* 25 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(request.playerId);
/* 26 */     MountsComponent mountsComponent = (MountsComponent)LookUpService.getComponent(request.playerId, MountsComponent.class);
/* 27 */     if (playerComponent != null) {
/* 28 */       ((GetPlayerInfoResponse)this.response).blocName = GroupUtil.getGroupName(playerComponent.getPlayerId());
/* 29 */       ((GetPlayerInfoResponse)this.response).fightValue = playerComponent.getTotalValue();
/* 30 */       ((GetPlayerInfoResponse)this.response).level = playerComponent.getLevel();
/* 31 */       ((GetPlayerInfoResponse)this.response).playerName = playerComponent.getPlayerName();
/* 32 */       ((GetPlayerInfoResponse)this.response).head = playerComponent.getHead();
/* 33 */       ((GetPlayerInfoResponse)this.response).vip = playerComponent.getVip();
/* 34 */       ((GetPlayerInfoResponse)this.response).playerId = playerComponent.getPlayerId();
/* 35 */       ((GetPlayerInfoResponse)this.response).titleId = playerComponent.getWearTitle();
/* 36 */       ((GetPlayerInfoResponse)this.response).fashionId = playerComponent.getWearFashion();
/* 37 */       ((GetPlayerInfoResponse)this.response).quality = playerComponent.getQuality();
/* 38 */       if (mountsComponent != null) {
/* 39 */         ((GetPlayerInfoResponse)this.response).mounts = mountsComponent.getTurnMounts();
/* 40 */         mountsComponent.maybeSaveToDB();
/*    */       } 
/* 42 */       playerComponent.maybeSaveToDB();
/*    */     } 
/*    */     
/* 45 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\common\GetPlayerInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */