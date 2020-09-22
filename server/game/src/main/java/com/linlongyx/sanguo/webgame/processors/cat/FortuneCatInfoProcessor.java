/*    */ package com.linlongyx.sanguo.webgame.processors.cat;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.cat.FortuneCatComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.cat.FortuneCatEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FortuneCatBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FortuneChargeBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FortuneTimeBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FortuneCatParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cat.FortuneCatInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cat.FortuneCatInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ 
/*    */ public class FortuneCatInfoProcessor
/*    */   extends ProcessorBase<FortuneCatInfoRequest, FortuneCatInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new FortuneCatInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FortuneCatInfoRequest request) {
/* 35 */     int id = request.id;
/* 36 */     FortuneTimeBean fortuneTimeBean = (FortuneTimeBean)JsonTableService.getJsonData(id, FortuneTimeBean.class);
/* 37 */     if (null == fortuneTimeBean) {
/* 38 */       return 18001;
/*    */     }
/* 40 */     FortuneCatParameter fortuneCatParameter = (FortuneCatParameter)ParameterConstant.getParameter(80);
/* 41 */     FestivalTime festivalTime = fortuneCatParameter.getFestivalTime(id);
/* 42 */     if (null == festivalTime) {
/* 43 */       return 18001;
/*    */     }
/* 45 */     if (!fortuneCatParameter.isActOpen(id)) {
/* 46 */       return 18002;
/*    */     }
/* 48 */     FortuneCatComponent fortuneCatComponent = (FortuneCatComponent)playerSession.getPlayer().createIfNotExist(FortuneCatComponent.class);
/* 49 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 50 */     FortuneCatEntity fortuneCatEntity = fortuneCatComponent.getEntity(id);
/* 51 */     int count = fortuneCatEntity.getCount();
/* 52 */     ((FortuneCatInfoResponse)this.response).festivalTime = festivalTime;
/* 53 */     if (fortuneTimeBean.getType() == 1) {
/* 54 */       ((FortuneCatInfoResponse)this.response).leftCount = fortuneCatParameter.getCount(playerComponent.getVip()) - count;
/* 55 */     } else if (fortuneTimeBean.getType() == 2) {
/* 56 */       TreeSet<Integer> treeSet = JsonTableService.getJsonTableKey(FortuneChargeBean.class);
/* 57 */       Collections.sort(new ArrayList<>((Collection)treeSet));
/* 58 */       int times = 0;
/* 59 */       for (Iterator<Integer> iterator = treeSet.iterator(); iterator.hasNext(); ) { int treeId = ((Integer)iterator.next()).intValue();
/* 60 */         long currCharge = fortuneCatEntity.getActCharge();
/* 61 */         FortuneChargeBean fortuneChargeBean = (FortuneChargeBean)JsonTableService.getJsonData(treeId, FortuneChargeBean.class);
/* 62 */         if (currCharge >= fortuneChargeBean.getCharge()) {
/* 63 */           times = fortuneChargeBean.getNum();
/*    */         } }
/*    */       
/* 66 */       ((FortuneCatInfoResponse)this.response).leftCount = times + fortuneCatParameter.getDefaultNum() - count;
/*    */     } 
/* 68 */     FortuneCatBean.TimesBean timesBean = FortuneCatUtil.getFortuneCatBean(count, fortuneTimeBean.getType());
/* 69 */     if (fortuneCatEntity.getMaxCCY() == 0 || fortuneCatEntity.getMinCCY() == 0) {
/* 70 */       FortuneCatUtil.getFortuneCatCCY(timesBean, fortuneCatEntity);
/*    */     }
/* 72 */     if (null != timesBean) {
/* 73 */       ((FortuneCatInfoResponse)this.response).cost = timesBean.getCost();
/*    */     }
/* 75 */     ((FortuneCatInfoResponse)this.response).maxValue = timesBean.getThirdL();
/* 76 */     ((FortuneCatInfoResponse)this.response).minValue = timesBean.getFirstR();
/* 77 */     ((FortuneCatInfoResponse)this.response).actCharge = fortuneCatEntity.getActCharge();
/* 78 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cat\FortuneCatInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */