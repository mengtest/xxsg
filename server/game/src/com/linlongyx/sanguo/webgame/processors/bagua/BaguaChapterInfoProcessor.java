/*    */ package com.linlongyx.sanguo.webgame.processors.bagua;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.eightGraphic.BaguaComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BaguaChapterBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bagua.BaguaChapterInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bagua.BaguaChapterInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaguaChapterInfoProcessor
/*    */   extends ProcessorBase<BaguaChapterInfoRequest, BaguaChapterInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new BaguaChapterInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BaguaChapterInfoRequest request) {
/* 32 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 41)) {
/* 33 */       return 10061;
/*    */     }
/* 35 */     BaguaComponent baguaComponent = (BaguaComponent)playerSession.getPlayer().createIfNotExist(BaguaComponent.class);
/* 36 */     if (baguaComponent.getChapterMap().isEmpty()) {
/* 37 */       Set<Integer> chapterIdSet = JsonTableService.getJsonTableKey(BaguaChapterBean.class);
/* 38 */       Iterator<Integer> it = chapterIdSet.iterator();
/* 39 */       BaguaChapterBean firstBean = (BaguaChapterBean)JsonTableService.getJsonData(((Integer)it.next()).intValue(), BaguaChapterBean.class);
/* 40 */       baguaComponent.getChapterMap().put(Integer.valueOf(firstBean.getId()), new TreeSet());
/* 41 */       baguaComponent.setCurInsId(((Integer)firstBean.getIns().get(0)).intValue());
/*    */     } 
/* 43 */     boolean canSweep = false;
/* 44 */     for (Map.Entry<Integer, Set<Integer>> kv : (Iterable<Map.Entry<Integer, Set<Integer>>>)baguaComponent.getChapterMap().entrySet()) {
/* 45 */       BaguaChapterBean bean = (BaguaChapterBean)JsonTableService.getJsonData(((Integer)kv.getKey()).intValue(), BaguaChapterBean.class);
/* 46 */       if (((Set)kv.getValue()).size() == bean.getIns().size()) {
/* 47 */         ((BaguaChapterInfoResponse)this.response).chapterList.add(kv.getKey());
/* 48 */         if (!canSweep)
/* 49 */           for (Iterator<Integer> iterator = bean.getIns().iterator(); iterator.hasNext(); ) { int insId = ((Integer)iterator.next()).intValue();
/* 50 */             if (!baguaComponent.getInsIdMap().containsKey(Integer.valueOf(insId))) {
/* 51 */               canSweep = true;
/*    */             } }
/*    */            
/*    */         continue;
/*    */       } 
/* 56 */       ((BaguaChapterInfoResponse)this.response).curChapterId = ((Integer)kv.getKey()).intValue();
/* 57 */       if (!canSweep) {
/* 58 */         for (int i = 0; i < ((Set)kv.getValue()).size(); i++) {
/* 59 */           if (!baguaComponent.getInsIdMap().containsKey(bean.getIns().get(i))) {
/* 60 */             canSweep = true;
/*    */           }
/*    */         } 
/*    */       }
/*    */     } 
/*    */     
/* 66 */     for (Map.Entry<Integer, Integer> kv : (Iterable<Map.Entry<Integer, Integer>>)baguaComponent.getInsIdMap().entrySet()) {
/* 67 */       if (((Integer)kv.getValue()).intValue() == 2) {
/* 68 */         ((BaguaChapterInfoResponse)this.response).insId.add(kv.getKey()); continue;
/*    */       } 
/* 70 */       ((BaguaChapterInfoResponse)this.response).canSweepInsId.add(kv.getKey());
/*    */     } 
/*    */     
/* 73 */     ((BaguaChapterInfoResponse)this.response).sweep = baguaComponent.getSweep();
/* 74 */     ((BaguaChapterInfoResponse)this.response).curInsId = (((BaguaChapterInfoResponse)this.response).curChapterId == 0) ? 0 : baguaComponent.getCurInsId();
/* 75 */     ((BaguaChapterInfoResponse)this.response).canSweep = (byte)((baguaComponent.getSweep() == 0 && canSweep) ? 1 : 0);
/* 76 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bagua\BaguaChapterInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */