/*    */ package com.linlongyx.sanguo.webgame.processors.sign;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.sign.SignComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.CheckDeluxeBean;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SignUtil
/*    */ {
/*    */   public static void refreshShowLevel(IPlayer player) {
/* 24 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 25 */     SignComponent signComponent = (SignComponent)player.createIfNotExist(SignComponent.class);
/*    */     
/* 27 */     Map<Integer, Object> checkMap = JsonTableService.getJsonTable(CheckDeluxeBean.class);
/* 28 */     ArrayList<Integer> list = new ArrayList<>();
/* 29 */     int type = PlayerUtil.getPlatformType();
/*    */     
/* 31 */     for (Object object : checkMap.values()) {
/* 32 */       CheckDeluxeBean checkDeluxeBean = (CheckDeluxeBean)object;
/* 33 */       if (checkDeluxeBean.getConnectortype() == type && !list.contains(Integer.valueOf(checkDeluxeBean.getLevel()))) {
/* 34 */         list.add(Integer.valueOf(checkDeluxeBean.getLevel()));
/*    */       }
/*    */     } 
/* 37 */     list.sort(Integer::compareTo);
/* 38 */     for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int level = ((Integer)iterator.next()).intValue();
/* 39 */       if (playerComponent.getLevel() <= level) {
/* 40 */         signComponent.setShowLevel(level);
/*    */         break;
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\sign\SignUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */