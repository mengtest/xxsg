/*    */ package com.linlongyx.sanguo.webgame.processors.fashion;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.fashion.FashionComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.fashion.FashionEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FashionBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FashionStrengthenBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fashion.FashionStrengthenRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fashion.FashionStrengthenResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FashionStrengthenProcessor
/*    */   extends ProcessorBase<FashionStrengthenRequest, FashionStrengthenResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new FashionStrengthenResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FashionStrengthenRequest request) {
/* 33 */     FashionBean fashionBean = (FashionBean)JsonTableService.getJsonData(request.fashionId, FashionBean.class);
/* 34 */     if (fashionBean == null) {
/* 35 */       return 13901;
/*    */     }
/*    */     
/* 38 */     FashionComponent fashionComponent = (FashionComponent)playerSession.getPlayer().createIfNotExist(FashionComponent.class);
/* 39 */     FashionEntity fashionEntity = fashionComponent.getFashionEntity(request.fashionId);
/* 40 */     if (fashionEntity == null) {
/* 41 */       return 13901;
/*    */     }
/* 43 */     FashionStrengthenBean fashionStrengthenBean = (FashionStrengthenBean)JsonTableService.getJsonData(request.fashionId, FashionStrengthenBean.class);
/*    */     
/* 45 */     if (request.type == 1) {
/* 46 */       int targetLevel = fashionEntity.getLevel() + 1;
/* 47 */       FashionStrengthenBean.LevelBean levelBean = (FashionStrengthenBean.LevelBean)fashionStrengthenBean.getLevel().get(Integer.valueOf(targetLevel));
/* 48 */       if (levelBean == null) {
/* 49 */         return 13906;
/*    */       }
/* 51 */       short errCode = CostUtil.handleCost(levelBean.getConsumeItem(), playerSession, ResourceEvent.FashionStrengthen);
/* 52 */       if (errCode != 0) {
/* 53 */         return errCode;
/*    */       }
/* 55 */       fashionEntity.setLevel(targetLevel);
/* 56 */       AttrUpdateUtil.refreshFashion(playerSession);
/* 57 */     } else if (request.type == 2) {
/* 58 */       int targetLevel = 0;
/* 59 */       List<Integer> levelList = (List<Integer>)fashionStrengthenBean.getLevel().keySet().stream().filter(level -> (level.intValue() > fashionEntity.getLevel())).sorted(Integer::compareTo).collect(Collectors.toList());
/* 60 */       if (levelList.isEmpty()) {
/* 61 */         return 13906;
/*    */       }
/* 63 */       for (Iterator<Integer> iterator = levelList.iterator(); iterator.hasNext(); ) { int level = ((Integer)iterator.next()).intValue();
/* 64 */         FashionStrengthenBean.LevelBean levelBean = (FashionStrengthenBean.LevelBean)fashionStrengthenBean.getLevel().get(Integer.valueOf(level));
/* 65 */         short errCode = CostUtil.handleCost(levelBean.getConsumeItem(), playerSession, ResourceEvent.FashionStrengthen);
/* 66 */         if (errCode == 0) {
/* 67 */           targetLevel = level;
/*    */         } }
/*    */ 
/*    */ 
/*    */       
/* 72 */       if (targetLevel > 0) {
/* 73 */         fashionEntity.setLevel(targetLevel);
/* 74 */         AttrUpdateUtil.refreshFashion(playerSession);
/*    */       } 
/*    */     } 
/* 77 */     ((FashionStrengthenResponse)this.response).fashion.key = fashionEntity.getFashionId();
/* 78 */     ((FashionStrengthenResponse)this.response).fashion.value = fashionEntity.getLevel();
/*    */     
/* 80 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\fashion\FashionStrengthenProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */