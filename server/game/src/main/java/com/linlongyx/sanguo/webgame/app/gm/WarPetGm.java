/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.warpet.ActivityWarPetProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.warpet.WarPetBattleProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.ActivityWarPetRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.WarPetBattleRequest;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WarPetGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 18 */     if (strings[2].equals("add")) {
/* 19 */       int id = Integer.valueOf(strings[3]).intValue();
/* 20 */       WarPetComponent warPetComponent = (WarPetComponent)playerSession.getPlayer().createIfNotExist(WarPetComponent.class);
/* 21 */       warPetComponent.addWarPet(id);
/* 22 */       AttrUpdateUtil.refreshWarPet(playerSession);
/* 23 */     } else if (strings[2].equals("act")) {
/* 24 */       ActivityWarPetRequest request = new ActivityWarPetRequest();
/* 25 */       request.warpet = Integer.valueOf(strings[3]).intValue();
/* 26 */       (new ActivityWarPetProcessor()).handle(playerSession, (RequestBase)request);
/* 27 */     } else if (strings[2].equals("battle")) {
/* 28 */       WarPetBattleRequest request = new WarPetBattleRequest();
/* 29 */       request.warpet = Integer.valueOf(strings[3]).intValue();
/* 30 */       (new WarPetBattleProcessor()).handle(playerSession, (RequestBase)request);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\WarPetGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */