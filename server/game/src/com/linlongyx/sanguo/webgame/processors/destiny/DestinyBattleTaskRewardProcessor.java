/*    */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.destiny.DestinyComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DestinyProcessBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBattleTaskRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBattleTaskRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyBattleTaskRewardProcessor
/*    */   extends ProcessorBase<DestinyBattleTaskRewardRequest, DestinyBattleTaskRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new DestinyBattleTaskRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DestinyBattleTaskRewardRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 74)) {
/* 32 */       return 10061;
/*    */     }
/* 34 */     DestinyComponent destinyComponent = (DestinyComponent)playerSession.getPlayer().createIfNotExist(DestinyComponent.class);
/*    */ 
/*    */     
/* 37 */     if (destinyComponent.getTasks().contains(Integer.valueOf(request.id))) {
/* 38 */       return 17403;
/*    */     }
/* 40 */     DestinyProcessBean destinyProcessBean = (DestinyProcessBean)JsonTableService.getJsonData(request.id, DestinyProcessBean.class);
/* 41 */     if (null == destinyProcessBean) {
/* 42 */       return 17404;
/*    */     }
/* 44 */     if (destinyComponent.getTotalRobTimes() < destinyProcessBean.getTimes()) {
/* 45 */       return 17405;
/*    */     }
/* 47 */     destinyComponent.addTask(request.id);
/* 48 */     if (null != destinyProcessBean.getReward() && !destinyProcessBean.getReward().isEmpty()) {
/* 49 */       ArrayList<Reward> list = FinanceUtil.transform(destinyProcessBean.getReward());
/* 50 */       FinanceUtil.reward(list, playerSession.getPlayer(), ResourceEvent.DestinyBattleTaskReward, true);
/* 51 */       ((DestinyBattleTaskRewardResponse)this.response).list = new ArrayList<>(list);
/*    */     } 
/*    */     
/* 54 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyBattleTaskRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */