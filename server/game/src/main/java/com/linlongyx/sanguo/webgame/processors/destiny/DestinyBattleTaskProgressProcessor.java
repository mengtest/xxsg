/*    */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.destiny.DestinyComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBattleTaskProgressRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBattleTaskProgressResponse;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyBattleTaskProgressProcessor
/*    */   extends ProcessorBase<DestinyBattleTaskProgressRequest, DestinyBattleTaskProgressResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new DestinyBattleTaskProgressResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DestinyBattleTaskProgressRequest request) {
/* 26 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 74)) {
/* 27 */       return 10061;
/*    */     }
/* 29 */     DestinyComponent destinyComponent = (DestinyComponent)playerSession.getPlayer().createIfNotExist(DestinyComponent.class);
/* 30 */     ((DestinyBattleTaskProgressResponse)this.response).tasks = new ArrayList(destinyComponent.getTasks());
/* 31 */     ((DestinyBattleTaskProgressResponse)this.response).progress = destinyComponent.getTotalRobTimes();
/* 32 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyBattleTaskProgressProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */