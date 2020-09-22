/*    */ package com.linlongyx.sanguo.webgame.processors.continuity;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.continuity.ContinuityComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.continuity.ContinuityEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ContinFillingBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ContinFillinglistBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.continuity.ContinueRechargeRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.continuity.ContinueRechargeRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContinueRechargeRewardProcessor
/*    */   extends ProcessorBase<ContinueRechargeRewardRequest, ContinueRechargeRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new ContinueRechargeRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ContinueRechargeRewardRequest request) {
/* 33 */     int id = request.id;
/* 34 */     int tid = request.tid;
/* 35 */     ContinFillingBean continFillingBean = (ContinFillingBean)JsonTableService.getJsonData(id, ContinFillingBean.class);
/* 36 */     if (null == continFillingBean) {
/* 37 */       return 12101;
/*    */     }
/* 39 */     ContinuityComponent continuityComponent = (ContinuityComponent)playerSession.getPlayer().createIfNotExist(ContinuityComponent.class);
/* 40 */     ContinuityEntity continuityEntity = continuityComponent.getContinuityEntity(id);
/* 41 */     Map<Integer, Integer> states = continuityEntity.getStates();
/* 42 */     int state = ((Integer)states.getOrDefault(Integer.valueOf(tid), Integer.valueOf(0))).intValue();
/* 43 */     if (0 == state)
/* 44 */       return 12103; 
/* 45 */     if (2 == state) {
/* 46 */       return 10091;
/*    */     }
/*    */     
/* 49 */     boolean isTotal = (-1 == tid);
/* 50 */     if (isTotal) {
/* 51 */       states.put(Integer.valueOf(tid), Integer.valueOf(2));
/* 52 */       FinanceUtil.reward(FinanceUtil.transform(continFillingBean.getTargetList()), playerSession.getPlayer(), ResourceEvent.ContinueRechargeReward, true);
/*    */     } else {
/* 54 */       ContinFillinglistBean continFillinglistBean = (ContinFillinglistBean)JsonTableService.getJsonData(tid, ContinFillinglistBean.class);
/* 55 */       if (null == continFillinglistBean) {
/* 56 */         return 12101;
/*    */       }
/* 58 */       if (continFillinglistBean.getConnectortype() != PlayerUtil.getPlatformType()) {
/* 59 */         return 11808;
/*    */       }
/* 61 */       states.put(Integer.valueOf(tid), Integer.valueOf(2));
/* 62 */       FinanceUtil.reward(FinanceUtil.transform(continFillinglistBean.getReward()), playerSession.getPlayer(), ResourceEvent.ContinueRechargeReward, true);
/*    */     } 
/* 64 */     continuityEntity.setStates(states);
/* 65 */     continuityComponent.updateStatesDB(id);
/*    */     
/* 67 */     ((ContinueRechargeRewardResponse)this.response).id = id;
/* 68 */     ((ContinueRechargeRewardResponse)this.response).tid = tid;
/* 69 */     LogUtils.errorLog(new Object[] { "ContinueRecharge", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(id), Integer.valueOf(tid) });
/* 70 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\continuity\ContinueRechargeRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */