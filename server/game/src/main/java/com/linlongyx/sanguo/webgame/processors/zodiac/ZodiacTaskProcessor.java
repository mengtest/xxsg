/*    */ package com.linlongyx.sanguo.webgame.processors.zodiac;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.zodiac.ZodiacComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ZodiacTaskBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ZodiacParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.zodiac.ZodiacTaskRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.zodiac.ZodiacTaskResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class ZodiacTaskProcessor
/*    */   extends ProcessorBase<ZodiacTaskRequest, ZodiacTaskResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new ZodiacTaskResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ZodiacTaskRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 75))
/* 32 */       return 10061; 
/* 33 */     ZodiacParameter zodiacParameter = (ZodiacParameter)ParameterConstant.getParameter(73);
/* 34 */     if (!zodiacParameter.isZodiacAct(request.actId)) {
/* 35 */       return 17506;
/*    */     }
/* 37 */     ZodiacTaskBean zodiacTaskBean = (ZodiacTaskBean)JsonTableService.getJsonData(request.taskId, ZodiacTaskBean.class);
/* 38 */     if (zodiacTaskBean == null) {
/* 39 */       return 17501;
/*    */     }
/* 41 */     ZodiacComponent zodiacComponent = (ZodiacComponent)playerSession.getPlayer().createIfNotExist(ZodiacComponent.class);
/* 42 */     Map<Integer, Integer> zodiaTasks = zodiacComponent.getZodiaTasks(request.actId);
/* 43 */     Map<Integer, Integer> zodiaState = zodiacComponent.getZodiaState(request.actId);
/* 44 */     if (((Integer)zodiaTasks.getOrDefault(Integer.valueOf(request.taskId), (V)Integer.valueOf(0))).intValue() < ((ZodiacTaskBean.TypeBean)zodiacTaskBean.getType().get(0)).getNum()) {
/* 45 */       return 10095;
/*    */     }
/* 47 */     if (((Integer)zodiaState.get(Integer.valueOf(request.taskId))).intValue() == 2) {
/* 48 */       return 10091;
/*    */     }
/* 50 */     FinanceUtil.reward(FinanceUtil.transform(zodiacTaskBean.getReward()), playerSession.getPlayer(), ResourceEvent.ZodiacReward, true);
/* 51 */     zodiaState.put(Integer.valueOf(request.taskId), Integer.valueOf(2));
/* 52 */     zodiacComponent.setZodiaState(request.actId, zodiaState);
/* 53 */     ((ZodiacTaskResponse)this.response).actId = request.actId;
/* 54 */     KeyValue task = new KeyValue();
/* 55 */     task.key = request.taskId;
/* 56 */     task.value = ((Integer)zodiaState.get(Integer.valueOf(request.taskId))).intValue();
/* 57 */     task.valueStr = ((Integer)zodiaTasks.get(Integer.valueOf(request.taskId))).toString();
/* 58 */     ((ZodiacTaskResponse)this.response).task = task;
/* 59 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\zodiac\ZodiacTaskProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */