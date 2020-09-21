/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendOpenRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendOpenResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FriendOpenProcessor
/*    */   extends ProcessorBase<FriendOpenRequest, FriendOpenResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new FriendOpenResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendOpenRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 32)) {
/* 30 */       return 10061;
/*    */     }
/* 32 */     FriendComponent friendComponent = (FriendComponent)playerSession.getPlayer().createIfNotExist(FriendComponent.class);
/* 33 */     HashSet<Long> playerIds = new HashSet<>();
/* 34 */     long myPlayerId = playerSession.getPlayer().getPlayerId();
/* 35 */     for (Long id : friendComponent.getIds()) {
/* 36 */       PlayerComponent targetComponent = (PlayerComponent)LookUpService.getOnlineComponent(id.longValue(), PlayerComponent.class);
/* 37 */       if (null == targetComponent) {
/* 38 */         playerIds.add(id);
/*    */         continue;
/*    */       } 
/* 41 */       ((FriendOpenResponse)this.response).friendInfos.add(FriendUtil.buildFriendInfo(Long.valueOf(myPlayerId), targetComponent));
/*    */     } 
/*    */ 
/*    */     
/* 45 */     HashSet<Long> friendIds = new HashSet<>();
/* 46 */     HashSet<Long> removeIds = new HashSet<>();
/* 47 */     friendIds = FriendUtil.getFriendInfos(((FriendOpenResponse)this.response).friendInfos, playerIds, friendComponent, friendIds);
/*    */     
/* 49 */     if (MContext.isHeFu() && !friendIds.isEmpty()) {
/* 50 */       Iterator<Long> iterator; for (iterator = playerIds.iterator(); iterator.hasNext(); ) { long p = ((Long)iterator.next()).longValue();
/* 51 */         if (!friendIds.contains(Long.valueOf(p))) {
/* 52 */           removeIds.add(Long.valueOf(p));
/*    */         } }
/*    */       
/* 55 */       for (iterator = removeIds.iterator(); iterator.hasNext(); ) { long player = ((Long)iterator.next()).longValue();
/* 56 */         PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(player, PlayerComponent.class);
/* 57 */         FriendComponent targetFriendComponent = (FriendComponent)LookUpService.getComponent(player, FriendComponent.class);
/* 58 */         if (null == playerComponent && null == targetFriendComponent) {
/* 59 */           friendComponent.getIds().remove(Long.valueOf(player));
/*    */         } }
/*    */     
/*    */     } 
/*    */     
/* 64 */     ((FriendOpenResponse)this.response).sendNum = friendComponent.getSendIds().size();
/* 65 */     ((FriendOpenResponse)this.response).receiveNum = friendComponent.getReceivedIds().size();
/*    */     
/* 67 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendOpenProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */