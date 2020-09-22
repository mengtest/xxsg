/*    */ package com.linlongyx.sanguo.webgame.processors.luckyTurntable;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.luckyTurntable.LuckyTurntableComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.luckyTurntable.LuckyTurntableEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.LuckylistBean;
/*    */ import com.linlongyx.sanguo.webgame.processors.limit.LimitUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.LuckyTurntableOpenRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.LuckyTurntableOpenResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LuckyTurntableOpenProcessor
/*    */   extends ProcessorBase<LuckyTurntableOpenRequest, LuckyTurntableOpenResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new LuckyTurntableOpenResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, LuckyTurntableOpenRequest request) {
/* 29 */     Map<Integer, Object> luckyListBeanMap = JsonTableService.getJsonTable(LuckylistBean.class);
/* 30 */     for (Map.Entry<Integer, Object> kv : luckyListBeanMap.entrySet()) {
/* 31 */       LuckylistBean bean = (LuckylistBean)kv.getValue();
/* 32 */       if (LimitUtil.isValid(bean.getServerTypeb(), bean.getDay()) && LimitUtil.isActOpen(bean.getServerTypeb(), bean
/* 33 */           .getBeginTimeb(), bean.getEndTimeb())) {
/* 34 */         if (!ConstantService.turntableStateMap.containsKey(Integer.valueOf(bean.getActId()))) {
/* 35 */           ConstantService.turntableStateMap.put(Integer.valueOf(bean.getActId()), Byte.valueOf((byte)1));
/*    */         }
/* 37 */         LuckyTurntableComponent luckyTurntableComponent = (LuckyTurntableComponent)playerSession.getPlayer().createIfNotExist(LuckyTurntableComponent.class);
/* 38 */         LuckyTurntableEntity luckyTurntableEntity = luckyTurntableComponent.getEntity(bean.getActId());
/* 39 */         if (luckyTurntableEntity == null) {
/* 40 */           luckyTurntableEntity = new LuckyTurntableEntity(playerSession.getPlayer().getPlayerId(), bean.getActId());
/* 41 */           luckyTurntableEntity.setFreeTimes(bean.getFreeTimes());
/* 42 */           luckyTurntableComponent.addEntity((IEntity)luckyTurntableEntity);
/*    */         } 
/* 44 */         ((LuckyTurntableOpenResponse)this.response).actIdList.add(Integer.valueOf(bean.getActId()));
/*    */       } 
/*    */     } 
/* 47 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\luckyTurntable\LuckyTurntableOpenProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */