/*    */ package com.linlongyx.sanguo.webgame.processors.towerOwner;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.tower.TowerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TowerOwnerBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TowerOwnerInfoProcessor
/*    */   extends ProcessorBase<TowerOwnerInfoRequest, TowerOwnerInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new TowerOwnerInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TowerOwnerInfoRequest request) {
/* 32 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 67)) {
/* 33 */       return 10061;
/*    */     }
/* 35 */     TowerComponent towerComponent = (TowerComponent)playerSession.getPlayer().createIfNotExist(TowerComponent.class);
/* 36 */     int curLayerId = towerComponent.getCurLayers();
/* 37 */     int targetId = 1;
/* 38 */     for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(TowerOwnerBean.class).iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 39 */       TowerOwnerBean bean = (TowerOwnerBean)JsonTableService.getJsonData(id, TowerOwnerBean.class);
/* 40 */       if (bean.getTowerNumber() <= curLayerId) {
/* 41 */         targetId = id;
/*    */       } }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 47 */     List<RankingData> dataList = CrossUtil.getTowerData("TowerOwnerInfoProcessor::handleRequest", CrossUtil.buildLocalPlayerData(playerSession.getPlayer().getPlayerId()), targetId);
/* 48 */     for (RankingData rankingData : dataList) {
/* 49 */       if (rankingData.playerId == playerSession.getPlayer().getPlayerId()) {
/* 50 */         ((TowerOwnerInfoResponse)this.response).towerLevel = rankingData.rank; break;
/*    */       } 
/*    */     } 
/* 53 */     ((TowerOwnerInfoResponse)this.response).towerOwnerList = (ArrayList)dataList;
/* 54 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\towerOwner\TowerOwnerInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */