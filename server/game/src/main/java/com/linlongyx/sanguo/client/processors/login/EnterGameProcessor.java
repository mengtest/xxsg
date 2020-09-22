/*    */ package com.linlongyx.sanguo.client.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.client.actor.Actor;
/*    */ import com.linlongyx.sanguo.client.processors.ProcessorBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.EnterGameRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.EnterGameResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnterGameProcessor
/*    */   extends ProcessorBase<EnterGameRequest, EnterGameResponse>
/*    */ {
/*    */   protected void process(Actor actor, EnterGameResponse response) {
/* 18 */     System.out.println("玩家ID:" + response.playerId + ", 玩家名字:" + response.playerName + ", 场景ID:" + response.sceneId + ", 响应延迟:" + (
/* 19 */         TimeUtil.currentTimeMillis() - actor.loginTime));
/* 20 */     actor.playerId = response.playerId;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 31 */     FightRecordRequest request = new FightRecordRequest();
/* 32 */     request.type = 1;
/* 33 */     request.id = "20002";
/* 34 */     actor.sendMessage((RequestBase)request);
/*    */     
/* 36 */     actor.start();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\processors\login\EnterGameProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */