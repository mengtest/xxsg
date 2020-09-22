/*    */ package com.linlongyx.sanguo.webgame.processors.bagua;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.eightGraphic.BaguaComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BaguaInsBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.processors.task.FindRewardType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bagua.BaguaSweepRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bagua.BaguaSweepResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaguaSweepProcessor
/*    */   extends ProcessorBase<BaguaSweepRequest, BaguaSweepResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 31 */     this.response = (ResponseBase)new BaguaSweepResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BaguaSweepRequest request) {
/* 36 */     BaguaComponent baguaComponent = (BaguaComponent)playerSession.getPlayer().createIfNotExist(BaguaComponent.class);
/* 37 */     if (baguaComponent.getSweep() == 1) {
/* 38 */       return 14104;
/*    */     }
/* 40 */     ArrayList<Reward> rewardList = new ArrayList<>();
/* 41 */     boolean sweepSuccess = false;
/* 42 */     if (request.type == 1) {
/* 43 */       if (baguaComponent.getInsIdMap().containsKey(Integer.valueOf(request.insId))) {
/* 44 */         return 14103;
/*    */       }
/* 46 */       BaguaInsBean baguaInsBean = (BaguaInsBean)JsonTableService.getJsonData(request.insId, BaguaInsBean.class);
/* 47 */       rewardList = FinanceUtil.transform(baguaInsBean.getSweep());
/* 48 */       baguaComponent.getInsIdMap().put(Integer.valueOf(request.insId), Integer.valueOf(1));
/* 49 */       sweepSuccess = true;
/* 50 */     } else if (request.type == 2) {
/*    */       
/* 52 */       for (Iterator<Integer> iterator = baguaComponent.getChapterMap().keySet().iterator(); iterator.hasNext(); ) { int chapterId = ((Integer)iterator.next()).intValue();
/* 53 */         for (Iterator<Integer> iterator1 = ((Set)baguaComponent.getChapterMap().get(Integer.valueOf(chapterId))).iterator(); iterator1.hasNext(); ) { int insId = ((Integer)iterator1.next()).intValue();
/* 54 */           if (!baguaComponent.getInsIdMap().containsKey(Integer.valueOf(insId))) {
/* 55 */             sweepSuccess = true;
/* 56 */             BaguaInsBean baguaInsBean = (BaguaInsBean)JsonTableService.getJsonData(insId, BaguaInsBean.class);
/* 57 */             rewardList.addAll(FinanceUtil.transform(baguaInsBean.getSweep()));
/* 58 */             rewardList.addAll(FinanceUtil.transform(baguaInsBean.getProReward()));
/* 59 */             baguaComponent.getInsIdMap().put(Integer.valueOf(insId), Integer.valueOf(1));
/*    */           }  }
/*    */          }
/*    */       
/* 63 */       SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/* 64 */       singleInsComponent.saveFindTimes(true, FindRewardType.BAGUA.getType(), 1);
/*    */     } 
/*    */     
/* 67 */     Map<Long, Long> mapRewards = new HashMap<>();
/* 68 */     FinanceUtil.overlyingMap(mapRewards, rewardList);
/* 69 */     ArrayList<Reward> reward3 = FinanceUtil.overlyingReward(mapRewards);
/* 70 */     if (sweepSuccess) {
/* 71 */       baguaComponent.setSweep((byte)1);
/* 72 */       rewardList = FinanceUtil.rewardGet(reward3, playerSession.getPlayer(), ResourceEvent.BaguaSweep, true);
/*    */     } 
/* 74 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 75 */     taskComponent.refreshSchedule(TaskType.BaGuaPass, 0, 0L);
/* 76 */     ((BaguaSweepResponse)this.response).rewardList.addAll(rewardList);
/* 77 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bagua\BaguaSweepProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */