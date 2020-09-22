/*    */ package com.linlongyx.sanguo.webgame.processors.souls;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.souls.SoulsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.souls.SoulsEntity;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.souls.SoulsInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.souls.SoulsInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SoulsData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SoulsInfoProcessor
/*    */   extends ProcessorBase<SoulsInfoRequest, SoulsInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new SoulsInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SoulsInfoRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 55))
/* 30 */       return 10061; 
/* 31 */     SoulsComponent soulsComponent = (SoulsComponent)playerSession.getPlayer().createIfNotExist(SoulsComponent.class);
/* 32 */     soulsComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */           SoulsEntity soulsEntity = (SoulsEntity)iMapEntity;
/*    */           
/*    */           SoulsData soulsData = new SoulsData();
/*    */           soulsData.level = soulsEntity.getLevel();
/*    */           soulsData.star = soulsEntity.getStar();
/*    */           soulsData.soulsId = soulsEntity.getId();
/*    */           soulsData.exp = soulsEntity.getExp();
/*    */           soulsData.fightValue = soulsEntity.getFightValue();
/*    */           ((SoulsInfoResponse)this.response).soulList.add(soulsData);
/*    */         });
/* 43 */     ArrayList<Integer> handBookList = SoulsUtil.getSoulsHandBook(soulsComponent);
/* 44 */     ((SoulsInfoResponse)this.response).combinationList = new ArrayList<>(handBookList);
/* 45 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 46 */     if (playerComponent.getSoulsFighter().isEmpty()) {
/* 47 */       ArrayList<Integer> arrayList = new ArrayList<>(6);
/* 48 */       for (int i = 0; i < 6; i++) {
/* 49 */         arrayList.add(Integer.valueOf(0));
/*    */       }
/* 51 */       playerComponent.setSoulsFighter(arrayList);
/*    */     } 
/* 53 */     for (Iterator<Integer> iterator = playerComponent.getSoulsFighter().iterator(); iterator.hasNext(); ) { int sId = ((Integer)iterator.next()).intValue();
/* 54 */       ((SoulsInfoResponse)this.response).soulsFighter.add(Integer.valueOf(sId)); }
/*    */     
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\souls\SoulsInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */