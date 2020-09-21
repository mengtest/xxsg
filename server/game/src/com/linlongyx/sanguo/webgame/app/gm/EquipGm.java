/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.equip.EquipPurifyProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.equip.EquipStrengthUpProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.equip.UpOrDownEquipProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipPurifyRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipStrengthUpRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.equip.UpOrDownEquipRequest;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EquipGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 23 */     if (strings[2].equals("t1")) {
/* 24 */       EquipStrengthUpRequest equipStrengthUpRequest = new EquipStrengthUpRequest();
/* 25 */       equipStrengthUpRequest.pid = 9004000012L;
/* 26 */       equipStrengthUpRequest.mid = Long.parseLong(strings[3]);
/* 27 */       (new EquipStrengthUpProcessor()).handle(playerSession, (RequestBase)equipStrengthUpRequest);
/* 28 */     } else if (strings[2].equals("t2")) {
/* 29 */       EquipPurifyRequest equipPurifyRequest = new EquipPurifyRequest();
/* 30 */       equipPurifyRequest.pid = 9004000012L;
/* 31 */       equipPurifyRequest.count = Integer.parseInt(strings[4]);
/* 32 */       (new EquipPurifyProcessor()).handle(playerSession, (RequestBase)equipPurifyRequest);
/* 33 */     } else if (strings[2].equals("equipDown")) {
/* 34 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 35 */       ArrayList<IMapEntity> entities = new ArrayList<>(equipComponent.getEntityMap().values());
/* 36 */       for (IMapEntity iMapEntity : entities) {
/* 37 */         EquipEntity entity = (EquipEntity)iMapEntity;
/* 38 */         if (entity.getIsWear() == 1) {
/* 39 */           entity.setIsWear((byte)0);
/*    */         }
/*    */       } 
/* 42 */     } else if (strings[2].equals("clear")) {
/* 43 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 44 */       ArrayList<IMapEntity> entities = new ArrayList<>(equipComponent.getEntityMap().values());
/* 45 */       for (IMapEntity iMapEntity : entities) {
/* 46 */         EquipEntity entity = (EquipEntity)iMapEntity;
/* 47 */         if (entity.getIsWear() != 1) {
/* 48 */           equipComponent.deleteEquip(entity, ResourceEvent.gmUse);
/*    */         }
/*    */       } 
/* 51 */     } else if (strings[2].equals("down")) {
/* 52 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 53 */       UpOrDownEquipRequest request = new UpOrDownEquipRequest();
/* 54 */       request.type = Integer.parseInt(strings[3]);
/* 55 */       request.pid = Long.parseLong(strings[4]);
/* 56 */       request.equipId = Long.parseLong(strings[5]);
/* 57 */       (new UpOrDownEquipProcessor()).handle(playerSession, (RequestBase)request);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\EquipGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */