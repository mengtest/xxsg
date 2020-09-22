/*    */ package com.linlongyx.sanguo.webgame.processors.zodiac;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.zodiac.ZodiacComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ZodiacBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ZodiacTaskBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ZodiacParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.zodiac.ZodiacInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.zodiac.ZodiacInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ZodiacInfoProcessor
/*    */   extends ProcessorBase<ZodiacInfoRequest, ZodiacInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 31 */     this.response = (ResponseBase)new ZodiacInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ZodiacInfoRequest request) {
/* 36 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 75))
/* 37 */       return 10061; 
/* 38 */     ZodiacParameter zodiacParameter = (ZodiacParameter)ParameterConstant.getParameter(73);
/* 39 */     if (!zodiacParameter.isZodiacAct(request.actId)) {
/* 40 */       return 17506;
/*    */     }
/* 42 */     ZodiacComponent zodiacComponent = (ZodiacComponent)playerSession.getPlayer().createIfNotExist(ZodiacComponent.class);
/* 43 */     ((ZodiacInfoResponse)this.response).festivalTim = zodiacParameter.getActTime(request.actId);
/*    */     
/* 45 */     Map<Integer, Integer> zodiacGoods = zodiacComponent.getZodiacGoods(request.actId);
/* 46 */     ((ZodiacInfoResponse)this.response).zodiacRewar = new ArrayList(zodiacGoods.keySet());
/* 47 */     ZodiacBean zodiacBean = (ZodiacBean)JsonTableService.getJsonData(request.actId, ZodiacBean.class);
/*    */     
/* 49 */     Map<Integer, Integer> zodiacShop = zodiacComponent.getZodiacShop(request.actId);
/* 50 */     for (Integer actId : zodiacBean.getShopList()) {
/* 51 */       IntIntValue intIntValue = new IntIntValue();
/* 52 */       intIntValue.key = actId.intValue();
/* 53 */       intIntValue.value = ((Integer)zodiacShop.getOrDefault(actId, Integer.valueOf(0))).intValue();
/* 54 */       ((ZodiacInfoResponse)this.response).zodiacSh.add(intIntValue);
/*    */     } 
/*    */     
/* 57 */     List<Integer> listTasks = ZodiacUtil.getZodiacTasks(zodiacComponent.getFirstLever(request.actId), request.actId);
/* 58 */     Map<Integer, Integer> zodiaTasks = zodiacComponent.getZodiaTasks(request.actId);
/* 59 */     Map<Integer, Integer> zodiaState = zodiacComponent.getZodiaState(request.actId);
/* 60 */     for (Integer taskId : listTasks) {
/* 61 */       int progress = ((Integer)zodiaTasks.getOrDefault(taskId, Integer.valueOf(0))).intValue();
/* 62 */       ZodiacTaskBean zodiacTaskBean = (ZodiacTaskBean)JsonTableService.getJsonData(taskId.intValue(), ZodiacTaskBean.class);
/* 63 */       KeyValue keyValue = new KeyValue();
/* 64 */       keyValue.key = taskId.intValue();
/* 65 */       if (progress < ((ZodiacTaskBean.TypeBean)zodiacTaskBean.getType().get(0)).getNum()) {
/* 66 */         keyValue.value = 0L;
/* 67 */         keyValue.valueStr = progress + "";
/* 68 */         ((ZodiacInfoResponse)this.response).palaceLis.add(keyValue); continue;
/* 69 */       }  if (progress >= ((ZodiacTaskBean.TypeBean)zodiacTaskBean.getType().get(0)).getNum()) {
/* 70 */         keyValue.value = (((Integer)zodiaState.getOrDefault(taskId, Integer.valueOf(2))).intValue() == 2) ? 2L : 1L;
/* 71 */         keyValue.valueStr = progress + "";
/* 72 */         ((ZodiacInfoResponse)this.response).palaceLis.add(keyValue);
/*    */       } 
/*    */     } 
/* 75 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\zodiac\ZodiacInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */