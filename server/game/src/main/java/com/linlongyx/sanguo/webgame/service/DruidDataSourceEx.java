/*    */ package com.linlongyx.sanguo.webgame.service;
/*    */ 
/*    */ import com.alibaba.druid.pool.DruidDataSource;
/*    */ import com.linlongyx.core.framework.concurrent.Fibers;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DruidDataSourceEx
/*    */   extends DruidDataSource
/*    */ {
/*    */   public void close() {
/* 14 */     LogUtil.errorLog(new Object[] { "DruidDataSourceEx close" });
/* 15 */     LookUpService.allLogout();
/* 16 */     super.close();
/* 17 */     Fibers.shutdown();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\DruidDataSourceEx.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */