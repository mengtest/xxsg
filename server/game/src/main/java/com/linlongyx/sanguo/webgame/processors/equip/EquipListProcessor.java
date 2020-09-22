/*    */ package com.linlongyx.sanguo.webgame.processors.equip;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipListResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EquipListProcessor
/*    */   extends ProcessorBase<EquipListRequest, EquipListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new EquipListResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, EquipListRequest request) {
/* 26 */     EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 27 */     equipComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */           EquipEntity equipEntity = (EquipEntity)iMapEntity;
/*    */           EquipData equipData = EquipUtil.getEquipData(equipEntity);
/*    */           ((EquipListResponse)this.response).equips.add(equipData);
/*    */         });
/* 32 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\EquipListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */