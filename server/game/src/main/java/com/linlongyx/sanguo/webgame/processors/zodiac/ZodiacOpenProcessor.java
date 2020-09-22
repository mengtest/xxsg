/*    */ package com.linlongyx.sanguo.webgame.processors.zodiac;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.zodiac.ZodiacComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.zodiac.ZodiacEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ZodiacParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.zodiac.ZodiacOpenRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.zodiac.ZodiacOpenResponse;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ZodiacOpenProcessor
/*    */   extends ProcessorBase<ZodiacOpenRequest, ZodiacOpenResponse> {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new ZodiacOpenResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ZodiacOpenRequest request) {
/* 32 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 75))
/* 33 */       return 10061; 
/* 34 */     ZodiacParameter zodiacParameter = (ZodiacParameter)ParameterConstant.getParameter(73);
/* 35 */     ZodiacComponent zodiacComponent = (ZodiacComponent)playerSession.getPlayer().createIfNotExist(ZodiacComponent.class);
/* 36 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 37 */     List<Integer> list = zodiacParameter.getZodiacAct(true);
/* 38 */     if (!list.isEmpty()) {
/* 39 */       Map<Integer, FestivalTime> festivalTimes = zodiacParameter.getActTimes();
/* 40 */       for (Integer actId : list) {
/* 41 */         if (festivalTimes.containsKey(actId)) {
/* 42 */           ((ZodiacOpenResponse)this.response).list.add(festivalTimes.get(actId));
/* 43 */           ZodiacEntity zodiacEntity = zodiacComponent.getEntity(actId.intValue());
/* 44 */           if (!zodiacEntity.isOpen() && extendComponent.getZeroResetDate() == TimeUtil.getCurrentYearMonthDay()) {
/* 45 */             if (extendComponent.getTodayRecharge() != 0L) {
/* 46 */               ZodiacUtil.addSeriesRechargeConsumeCCY(zodiacEntity, TaskType.Charge.getType(), extendComponent.getTodayRecharge(), actId.intValue());
/*    */             }
/* 48 */             if (extendComponent.getDailyConsume() != 0L) {
/* 49 */               ZodiacUtil.addSeriesRechargeConsumeCCY(zodiacEntity, TaskType.ConsumeCCY.getType(), extendComponent.getDailyConsume(), actId.intValue());
/*    */             }
/*    */           } 
/* 52 */           zodiacEntity.setOpen(true);
/*    */           
/* 54 */           for (Iterator<Integer> iterator = zodiacEntity.getZodiaState().values().iterator(); iterator.hasNext(); ) { int state = ((Integer)iterator.next()).intValue();
/* 55 */             if (state == 1)
/*    */             {
/* 57 */               PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.Zodiac);
/*    */             } }
/*    */         
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 64 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\zodiac\ZodiacOpenProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */