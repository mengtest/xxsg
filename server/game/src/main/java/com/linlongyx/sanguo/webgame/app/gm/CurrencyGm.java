/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CurrencyGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 15 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/* 16 */     if (playerComponent == null)
/* 17 */       return;  if (strings[2].equals("add")) {
/* 18 */       int type = Integer.parseInt(strings[3]);
/* 19 */       int num = Integer.parseInt(strings[4]);
/* 20 */       FinanceUtil.addCurrency(playerSession.getPlayer(), CurrencyType.values()[type], num, ResourceEvent.gmAdd);
/* 21 */     } else if (strings[2].equals("desc")) {
/* 22 */       int type = Integer.parseInt(strings[3]);
/* 23 */       int num = Integer.parseInt(strings[4]);
/* 24 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.values()[type], num, ResourceEvent.gmUse);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\CurrencyGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */