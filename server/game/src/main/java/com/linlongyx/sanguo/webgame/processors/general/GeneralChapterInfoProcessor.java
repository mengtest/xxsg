/*    */ package com.linlongyx.sanguo.webgame.processors.general;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.GeneralInsBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GeneralParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralChapterInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralChapterInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GeneralPointData;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class GeneralChapterInfoProcessor
/*    */   extends ProcessorBase<GeneralChapterInfoRequest, GeneralChapterInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new GeneralChapterInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GeneralChapterInfoRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 12)) {
/* 32 */       return 10061;
/*    */     }
/*    */     
/* 35 */     int chapter = request.chapter;
/* 36 */     int level = request.level;
/* 37 */     if (level != 1 && level != 2 && level != 3) {
/* 38 */       return 11808;
/*    */     }
/* 40 */     if (level == 2) {
/* 41 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 1201)) {
/* 42 */         return 10061;
/*    */       }
/* 44 */     } else if (level == 3 && 
/* 45 */       !FunctionOpenConstant.isFunctionOpen(playerSession, 1202)) {
/* 46 */       return 10061;
/*    */     } 
/*    */ 
/*    */     
/* 50 */     GeneralParameter generalParameter = (GeneralParameter)ParameterConstant.getParameter(12);
/* 51 */     Map<Integer, Integer> pointIdMap = generalParameter.getPointIdMap(chapter);
/*    */     
/* 53 */     if (null == pointIdMap) {
/* 54 */       return 11203;
/*    */     }
/* 56 */     GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/* 57 */     ((GeneralChapterInfoResponse)this.response).num = GeneralUtil.getLeftOrder(generalComponent);
/* 58 */     ((GeneralChapterInfoResponse)this.response).time = generalComponent.getNextTime();
/*    */     
/* 60 */     for (Map.Entry<Integer, Integer> entry : pointIdMap.entrySet()) {
/* 61 */       GeneralInsBean generalInsBean = (GeneralInsBean)JsonTableService.getJsonData(((Integer)entry.getValue()).intValue(), GeneralInsBean.class);
/* 62 */       if (null != generalInsBean && level == generalInsBean.getDifficult()) {
/* 63 */         GeneralPointData generalPointData = GeneralUtil.getGeneralPointData(generalInsBean, generalComponent);
/* 64 */         ((GeneralChapterInfoResponse)this.response).list.add(generalPointData);
/*    */       } 
/*    */     } 
/* 67 */     List<Integer> list = new ArrayList<>();
/* 68 */     Map<Integer, List<Integer>> boxReward = new HashMap<>();
/* 69 */     if (level == 1) {
/* 70 */       boxReward = generalComponent.getBoxReward();
/* 71 */       list = boxReward.getOrDefault(Integer.valueOf(chapter), new ArrayList<>());
/* 72 */     } else if (level == 2) {
/* 73 */       boxReward = generalComponent.getDiffboxReward();
/* 74 */       list = boxReward.getOrDefault(Integer.valueOf(chapter), new ArrayList<>());
/* 75 */     } else if (level == 3) {
/* 76 */       boxReward = generalComponent.getNightboxReward();
/* 77 */       list = boxReward.getOrDefault(Integer.valueOf(chapter), new ArrayList<>());
/*    */     } 
/* 79 */     ((GeneralChapterInfoResponse)this.response).box = new ArrayList<>(list);
/* 80 */     ((GeneralChapterInfoResponse)this.response).chapter = chapter;
/* 81 */     ((GeneralChapterInfoResponse)this.response).level = level;
/* 82 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\general\GeneralChapterInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */