/*    */ package com.linlongyx.sanguo.webgame.processors.partner;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.HandbookBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.GetHandbookRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.GetHandbookRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetHandbookRewardProcessor
/*    */   extends ProcessorBase<GetHandbookRewardRequest, GetHandbookRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new GetHandbookRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetHandbookRewardRequest request) {
/* 35 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 10)) {
/* 36 */       return 10061;
/*    */     }
/* 38 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 39 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 40 */     Map<Integer, Object> map = JsonTableService.getJsonTable(HandbookBean.class);
/* 41 */     int campStars = 0;
/* 42 */     for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/* 43 */       PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 44 */       FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/* 45 */       if (fighterBean.getCamp() == request.type) {
/* 46 */         campStars += partnerEntity.getStars();
/*    */       }
/*    */     } 
/*    */     
/* 50 */     Map<Integer, HandbookBean.StarsBean> starsBeanMap = null;
/* 51 */     for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext(); ) { int camp = ((Integer)iterator.next()).intValue();
/* 52 */       if (camp == request.type) {
/* 53 */         HandbookBean handbookBean = (HandbookBean)map.get(Integer.valueOf(camp));
/* 54 */         starsBeanMap = handbookBean.getStars();
/*    */         break;
/*    */       }  }
/*    */     
/* 58 */     boolean isEnough = false;
/* 59 */     if (null != starsBeanMap) {
/* 60 */       for (Iterator<Integer> iterator1 = starsBeanMap.keySet().iterator(); iterator1.hasNext(); ) { int star = ((Integer)iterator1.next()).intValue();
/* 61 */         if (extendComponent.getHandbookMap().get(Integer.valueOf(request.type)) != null && ((ArrayList)extendComponent.getHandbookMap().get(Integer.valueOf(request.type))).indexOf(Integer.valueOf(star)) >= 0) {
/*    */           continue;
/*    */         }
/* 64 */         if (campStars >= star) {
/* 65 */           if (extendComponent.getHandbookMap().containsKey(Integer.valueOf(request.type))) {
/* 66 */             ((ArrayList<Integer>)extendComponent.getHandbookMap().get(Integer.valueOf(request.type))).add(Integer.valueOf(star));
/*    */           } else {
/* 68 */             ArrayList<Integer> arrayList = new ArrayList<>();
/* 69 */             arrayList.add(Integer.valueOf(star));
/* 70 */             extendComponent.getHandbookMap().put(Integer.valueOf(request.type), arrayList);
/*    */           } 
/* 72 */           isEnough = true;
/* 73 */           FinanceUtil.reward(FinanceUtil.transformHandbook(((HandbookBean.StarsBean)starsBeanMap.get(Integer.valueOf(star))).getReward()), playerSession.getPlayer(), ResourceEvent.HandBookBox, true);
/*    */           break;
/*    */         }  }
/*    */     
/*    */     } else {
/* 78 */       return 13324;
/*    */     } 
/* 80 */     if (!isEnough) {
/* 81 */       return 13326;
/*    */     }
/* 83 */     ((GetHandbookRewardResponse)this.response).type = request.type;
/* 84 */     ((GetHandbookRewardResponse)this.response).rewardList = (ArrayList)extendComponent.getHandbookMap().get(Integer.valueOf(request.type));
/* 85 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\GetHandbookRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */