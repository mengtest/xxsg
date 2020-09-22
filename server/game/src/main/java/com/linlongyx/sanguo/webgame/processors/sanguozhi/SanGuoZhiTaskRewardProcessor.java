/*    */ package com.linlongyx.sanguo.webgame.processors.sanguozhi;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RecordStarBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sanguozhi.SanGuoZhiTaskRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sanguozhi.SanGuoZhiTaskRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SanGuoZhiTaskRewardProcessor
/*    */   extends ProcessorBase<SanGuoZhiTaskRewardRequest, SanGuoZhiTaskRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new SanGuoZhiTaskRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SanGuoZhiTaskRewardRequest request) {
/* 28 */     SanGuoZhiComponent sanGuoZhiComponent = (SanGuoZhiComponent)playerSession.getPlayer().createIfNotExist(SanGuoZhiComponent.class);
/*    */     
/* 30 */     RecordStarBean recordStarBean = (RecordStarBean)JsonTableService.getJsonData(request.recordStar, RecordStarBean.class);
/* 31 */     if (null == recordStarBean) {
/* 32 */       return 15001;
/*    */     }
/* 34 */     SanGuoZhiUtil.updateRecord(playerSession, request.recordStar, 0, 0L, null);
/* 35 */     SanGuoZhiEntity sanGuoZhiEntity = sanGuoZhiComponent.getEntity(request.recordStar);
/* 36 */     if (sanGuoZhiEntity.getRewarded().contains(Integer.valueOf(request.taskType))) {
/* 37 */       return 15002;
/*    */     }
/* 39 */     if (!sanGuoZhiEntity.getFinishes().contains(Integer.valueOf(request.taskType))) {
/* 40 */       return 15003;
/*    */     }
/* 42 */     ((SanGuoZhiTaskRewardResponse)this.response).recordStar = request.recordStar;
/* 43 */     sanGuoZhiEntity.getRewarded().add(Integer.valueOf(request.taskType));
/* 44 */     sanGuoZhiComponent.updateRewardedDB(request.recordStar);
/* 45 */     Map<Integer, Long> values = sanGuoZhiEntity.getValues();
/* 46 */     for (RecordStarBean.TaskTpyeBean taskTpyeBean : recordStarBean.getTaskTpye()) {
/* 47 */       KeyValue keyValue = new KeyValue();
/* 48 */       keyValue.key = taskTpyeBean.getType();
/* 49 */       keyValue.value = ((Long)values.getOrDefault(Integer.valueOf(taskTpyeBean.getType()), Long.valueOf(0L))).longValue();
/* 50 */       if (sanGuoZhiEntity.getRewarded().contains(Integer.valueOf(taskTpyeBean.getType()))) {
/* 51 */         keyValue.valueStr = "2";
/* 52 */       } else if (sanGuoZhiEntity.getFinishes().contains(Integer.valueOf(taskTpyeBean.getType()))) {
/* 53 */         keyValue.valueStr = "1";
/*    */       } else {
/* 55 */         keyValue.valueStr = "0";
/*    */       } 
/* 57 */       ((SanGuoZhiTaskRewardResponse)this.response).info.add(keyValue);
/*    */     } 
/*    */     
/* 60 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\sanguozhi\SanGuoZhiTaskRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */