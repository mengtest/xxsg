/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.RapidCombatInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.RapidCombatInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RapidCombatInfoProcessor
/*    */   extends ProcessorBase<RapidCombatInfoRequest, RapidCombatInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new RapidCombatInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RapidCombatInfoRequest request) {
/* 27 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 7))
/* 28 */       return 10061; 
/* 29 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/* 30 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 31 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 32 */     int maxCount = VipUtil.getNum(playerComponent.getVip(), 1) + loginParameter.getInitCombatTimes();
/* 33 */     ((RapidCombatInfoResponse)this.response).times = maxCount - extendComponent.getCombatTimes();
/* 34 */     ((RapidCombatInfoResponse)this.response).combatCostTimes = extendComponent.getCombatCostTimes();
/* 35 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\RapidCombatInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */