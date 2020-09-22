/*    */ package com.linlongyx.sanguo.webgame.processors.souls;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.souls.SoulsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.souls.SoulsEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.SoulsParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.souls.SoulsRecastRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.souls.SoulsRecastResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class SoulsRecastProcessor
/*    */   extends ProcessorBase<SoulsRecastRequest, SoulsRecastResponse> {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new SoulsRecastResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SoulsRecastRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 55))
/* 30 */       return 10061; 
/* 31 */     SoulsComponent soulsComponent = (SoulsComponent)playerSession.getPlayer().createIfNotExist(SoulsComponent.class);
/* 32 */     SoulsEntity soulsEntity = soulsComponent.getEntity(request.soulsId);
/* 33 */     if (soulsEntity == null) {
/* 34 */       return 16009;
/*    */     }
/* 36 */     SoulsParameter soulsParameter = (SoulsParameter)ParameterConstant.getParameter(55);
/* 37 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, soulsParameter.getReCastCCY())) {
/* 38 */       return 10052;
/*    */     }
/* 40 */     ArrayList<Reward> rewards = SoulsUtil.soulsRecast(soulsEntity, soulsComponent);
/* 41 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, soulsParameter.getReCastCCY(), ResourceEvent.StageRecast);
/* 42 */     FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.StageRecast, true);
/*    */     
/* 44 */     AttrUpdateUtil.refreshSouls(playerSession);
/* 45 */     ((SoulsRecastResponse)this.response).data.soulsId = request.soulsId;
/* 46 */     ((SoulsRecastResponse)this.response).data.level = soulsEntity.getLevel();
/* 47 */     ((SoulsRecastResponse)this.response).data.star = soulsEntity.getStar();
/* 48 */     ((SoulsRecastResponse)this.response).data.fightValue = soulsEntity.getFightValue();
/* 49 */     ((SoulsRecastResponse)this.response).data.exp = soulsEntity.getExp();
/* 50 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\souls\SoulsRecastProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */