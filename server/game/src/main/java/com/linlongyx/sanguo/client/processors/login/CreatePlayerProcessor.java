/*    */ package com.linlongyx.sanguo.client.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.client.actor.Actor;
/*    */ import com.linlongyx.sanguo.client.processors.ProcessorBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.CreatePlayerRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.CreatePlayerResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.EnterGameRequest;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CreatePlayerProcessor
/*    */   extends ProcessorBase<CreatePlayerRequest, CreatePlayerResponse>
/*    */ {
/*    */   protected void process(Actor actor, CreatePlayerResponse response) {
/* 18 */     EnterGameRequest enterGameRequest = new EnterGameRequest();
/* 19 */     enterGameRequest.playerId = response.playerId;
/* 20 */     actor.sendMessage((RequestBase)enterGameRequest);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\processors\login\CreatePlayerProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */