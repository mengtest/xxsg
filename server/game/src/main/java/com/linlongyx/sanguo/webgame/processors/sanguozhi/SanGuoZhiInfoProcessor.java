/*    */ package com.linlongyx.sanguo.webgame.processors.sanguozhi;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RecordStarBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sanguozhi.SanGuoZhiInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sanguozhi.SanGuoZhiInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SanGuoZhiData;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SanGuoZhiInfoProcessor
/*    */   extends ProcessorBase<SanGuoZhiInfoRequest, SanGuoZhiInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new SanGuoZhiInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SanGuoZhiInfoRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 16))
/* 32 */       return 10061; 
/* 33 */     Map<Integer, Object> starMap = JsonTableService.getJsonTable(RecordStarBean.class);
/* 34 */     SanGuoZhiComponent sanGuoZhiComponent = (SanGuoZhiComponent)playerSession.getPlayer().createIfNotExist(SanGuoZhiComponent.class);
/*    */     
/* 36 */     for (Iterator<Integer> iterator = starMap.keySet().iterator(); iterator.hasNext(); ) { int recordStar = ((Integer)iterator.next()).intValue();
/* 37 */       SanGuoZhiEntity sanGuoZhiEntity = sanGuoZhiComponent.getEntity2(recordStar);
/* 38 */       RecordStarBean recordStarBean = (RecordStarBean)starMap.get(Integer.valueOf(recordStar));
/* 39 */       if (recordStarBean.getPreStar() != 0) {
/* 40 */         SanGuoZhiEntity sanGuoZhiEntity2 = sanGuoZhiComponent.getEntity2(recordStarBean.getPreStar());
/* 41 */         if (null != sanGuoZhiEntity2 && sanGuoZhiEntity2.isActivity()) {
/* 42 */           sanGuoZhiEntity = sanGuoZhiComponent.getEntity(recordStar);
/*    */         }
/*    */       } 
/* 45 */       SanGuoZhiUtil.updateRecord(playerSession, recordStar, 0, 0L, null);
/* 46 */       if (null != sanGuoZhiEntity) {
/* 47 */         Map<Integer, Long> values = sanGuoZhiEntity.getValues();
/* 48 */         if (sanGuoZhiEntity.isActivity()) {
/* 49 */           ((SanGuoZhiInfoResponse)this.response).stars.add(Integer.valueOf(recordStar));
/*    */         }
/* 51 */         SanGuoZhiData sanGuoZhiData1 = new SanGuoZhiData();
/* 52 */         sanGuoZhiData1.recordStar = recordStarBean.getRecordStar();
/* 53 */         for (RecordStarBean.TaskTpyeBean taskTpyeBean : recordStarBean.getTaskTpye()) {
/* 54 */           KeyValue keyValue = new KeyValue();
/* 55 */           keyValue.key = taskTpyeBean.getType();
/* 56 */           keyValue.value = ((Long)values.getOrDefault(Integer.valueOf(taskTpyeBean.getType()), Long.valueOf(0L))).longValue();
/* 57 */           if (sanGuoZhiEntity.getRewarded().contains(Integer.valueOf(taskTpyeBean.getType()))) {
/* 58 */             keyValue.valueStr = "2";
/* 59 */           } else if (sanGuoZhiEntity.getFinishes().contains(Integer.valueOf(taskTpyeBean.getType()))) {
/* 60 */             keyValue.valueStr = "1";
/*    */           } else {
/* 62 */             keyValue.valueStr = "0";
/*    */           } 
/* 64 */           sanGuoZhiData1.values.add(keyValue);
/*    */         } 
/* 66 */         ((SanGuoZhiInfoResponse)this.response).info.add(sanGuoZhiData1); continue;
/*    */       } 
/* 68 */       SanGuoZhiData sanGuoZhiData = new SanGuoZhiData();
/* 69 */       sanGuoZhiData.recordStar = recordStarBean.getRecordStar();
/* 70 */       for (RecordStarBean.TaskTpyeBean taskTpyeBean : recordStarBean.getTaskTpye()) {
/* 71 */         KeyValue keyValue = new KeyValue();
/* 72 */         keyValue.key = taskTpyeBean.getType();
/* 73 */         keyValue.value = 0L;
/* 74 */         keyValue.valueStr = "0";
/* 75 */         sanGuoZhiData.values.add(keyValue);
/*    */       } 
/* 77 */       ((SanGuoZhiInfoResponse)this.response).info.add(sanGuoZhiData); }
/*    */ 
/*    */     
/* 80 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\sanguozhi\SanGuoZhiInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */