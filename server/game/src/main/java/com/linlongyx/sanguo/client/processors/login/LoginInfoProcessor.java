/*    */ package com.linlongyx.sanguo.client.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.client.actor.Actor;
/*    */ import com.linlongyx.sanguo.client.processors.ProcessorBase;
/*    */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.CreatePlayerRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.EnterGameRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.LoginInfoDebugRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.LoginInfoDebugResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlayerInfo;
/*    */ 
/*    */ 
/*    */ public class LoginInfoProcessor
/*    */   extends ProcessorBase<LoginInfoDebugRequest, LoginInfoDebugResponse>
/*    */ {
/*    */   protected void process(Actor actor, LoginInfoDebugResponse response) {
/* 19 */     if (response.players.isEmpty()) {
/* 20 */       CreatePlayerRequest createPlayerRequest = new CreatePlayerRequest();
/* 21 */       createPlayerRequest.userId = response.userId;
/* 22 */       byte sex = 1;
/* 23 */       if (RandUtil.isRandTrue()) {
/* 24 */         sex = 2;
/*    */       }
/* 26 */       createPlayerRequest.sex = sex;
/* 27 */       createPlayerRequest.playerName = "xh" + response.userId;
/* 28 */       actor.sendMessage((RequestBase)createPlayerRequest);
/*    */     } else {
/* 30 */       EnterGameRequest enterGameRequest = new EnterGameRequest();
/* 31 */       enterGameRequest.playerId = ((PlayerInfo)response.players.get(0)).playerId;
/* 32 */       actor.sendMessage((RequestBase)enterGameRequest);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\processors\login\LoginInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */