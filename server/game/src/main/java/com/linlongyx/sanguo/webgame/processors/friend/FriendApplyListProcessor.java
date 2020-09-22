/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendApplyListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendApplyListResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FriendApplyListProcessor
/*    */   extends ProcessorBase<FriendApplyListRequest, FriendApplyListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new FriendApplyListResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendApplyListRequest request) {
/* 28 */     FriendComponent friendComponent = (FriendComponent)playerSession.getPlayer().createIfNotExist(FriendComponent.class);
/* 29 */     List<Long> deleteList = new ArrayList<>();
/* 30 */     for (Map.Entry<Long, Integer> entry : (Iterable<Map.Entry<Long, Integer>>)friendComponent.getApplyMap().entrySet()) {
/* 31 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(((Long)entry.getKey()).longValue());
/* 32 */       if (null != playerComponent) {
/* 33 */         ((FriendApplyListResponse)this.response).friendInfos.add(FriendUtil.buildFriendInfo(playerComponent)); continue;
/*    */       } 
/* 35 */       deleteList.add(entry.getKey());
/*    */     } 
/*    */     
/* 38 */     deleteList.forEach(id -> (Integer)friendComponent.getApplyMap().remove(id));
/* 39 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendApplyListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */