/*    */ package com.linlongyx.sanguo.webgame.service;
/*    */ 
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.dao.proxy.MapProxy;
/*    */ import com.linlongyx.core.framework.service.IBussinessService;
/*    */ import com.linlongyx.sanguo.webgame.app.mail.PubMailEntity;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PubMailService
/*    */   implements IBussinessService
/*    */ {
/* 16 */   public MapProxy entityProxy = new MapProxy(PubMailEntity.class);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxId() {
/* 22 */     int max = 0;
/* 23 */     for (Map.Entry<String, IMapEntity> entry : (Iterable<Map.Entry<String, IMapEntity>>)this.entityProxy.getEntityMap().entrySet()) {
/* 24 */       int id = Integer.parseInt(entry.getKey());
/* 25 */       if (max < id) {
/* 26 */         max = id;
/*    */       }
/*    */     } 
/* 29 */     return max;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PubMailEntity getMailInfoById(int id) {
/* 38 */     return (PubMailEntity)this.entityProxy.getEntity(String.valueOf(id));
/*    */   }
/*    */ 
/*    */   
/*    */   public void initFromDB() {
/* 43 */     Table prefix = (Table)this.entityProxy.getEntityClass().getAnnotation(Table.class);
/* 44 */     String name = prefix.prefix();
/* 45 */     String dbKey = name.replace("%serverId", AppContext.getServerId());
/* 46 */     this.entityProxy.setEntityKeyId(dbKey);
/* 47 */     this.entityProxy.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public void saveToDB() {
/* 52 */     this.entityProxy.save();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\PubMailService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */