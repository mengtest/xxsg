/*     */ package com.linlongyx.sanguo.webgame.app.mail;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.MailParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailNoticeProcessor;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailNoticeRequest;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MailComponent
/*     */   extends AbstractMapComponent<MailEntity>
/*     */ {
/*  24 */   private Map<Byte, LinkedList<Integer>> sortedMap = new HashMap<>();
/*  25 */   private int size = 0;
/*  26 */   private static final Object LOCK = new Object();
/*     */   
/*     */   public MailComponent(long playerId) {
/*  29 */     super(MailEntity.class, playerId);
/*     */   }
/*     */   
/*     */   public void sort() {
/*  33 */     List<MailEntity> list = new LinkedList<>();
/*  34 */     for (Map.Entry<String, IMapEntity> entry : (Iterable<Map.Entry<String, IMapEntity>>)getEntityMap().entrySet()) {
/*  35 */       MailEntity mailEntity = (MailEntity)entry.getValue();
/*  36 */       list.add(mailEntity);
/*     */     } 
/*  38 */     list.sort(new MailEntityComparator());
/*  39 */     this.size = list.size();
/*  40 */     synchronized (LOCK) {
/*  41 */       this.sortedMap.clear();
/*  42 */       for (MailEntity mailEntity : list) {
/*  43 */         this.sortedMap.putIfAbsent(Byte.valueOf(mailEntity.getType()), new LinkedList<>());
/*  44 */         ((LinkedList<Integer>)this.sortedMap.get(Byte.valueOf(mailEntity.getType()))).add(Integer.valueOf(mailEntity.getId()));
/*     */       } 
/*  46 */       for (Map.Entry<Byte, LinkedList<Integer>> entry : this.sortedMap.entrySet()) {
/*     */         
/*  48 */         if (((LinkedList)entry.getValue()).size() > 0 && (((MailEntity)getEntity(String.valueOf(((LinkedList)entry.getValue()).get(0)))).getIsRead() == 0 || ((MailEntity)
/*  49 */           getEntity(String.valueOf(((LinkedList)entry.getValue()).get(0)))).getIsExtract() == 0)) {
/*  50 */           IPlayer iPlayer = LookUpService.getByPlayerId(getPlayerId());
/*  51 */           if (iPlayer != null && iPlayer.getSession() != null) {
/*  52 */             (new MailNoticeProcessor()).handle(iPlayer.getSession(), (RequestBase)new MailNoticeRequest());
/*     */           }
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<Integer> getMailIdsByType(byte type) {
/*  61 */     synchronized (LOCK) {
/*  62 */       return this.sortedMap.get(Byte.valueOf(type));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEntity(MailEntity entity) {
/*  71 */     getProxy().addEntity(entity);
/*  72 */     synchronized (LOCK) {
/*  73 */       this.size++;
/*  74 */       LinkedList<Integer> list = this.sortedMap.get(Byte.valueOf(entity.getType()));
/*  75 */       if (null == list) {
/*  76 */         list = new LinkedList<>();
/*     */       }
/*  78 */       list.add(0, Integer.valueOf(entity.getId()));
/*     */       
/*  80 */       MailParameter parameter = (MailParameter)ParameterConstant.getParameter(5);
/*  81 */       int maxNum = parameter.getMailMaxNum(entity.getType());
/*  82 */       while (list.size() > maxNum) {
/*  83 */         int id = ((Integer)list.get(list.size() - 1)).intValue();
/*  84 */         list.remove(list.size() - 1);
/*  85 */         MailEntity mailEntity = (MailEntity)getEntity(String.valueOf(id));
/*  86 */         delMailEntity(mailEntity);
/*     */       } 
/*  88 */       this.sortedMap.put(Byte.valueOf(entity.getType()), list);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short deleteMail(int id) {
/*  98 */     MailEntity mailEntity = (MailEntity)getEntity(String.valueOf(id));
/*  99 */     if (null == mailEntity) {
/* 100 */       return 10503;
/*     */     }
/* 102 */     synchronized (LOCK) {
/* 103 */       LinkedList<Integer> list = this.sortedMap.get(Byte.valueOf(mailEntity.getType()));
/* 104 */       if (list.size() > 0) {
/* 105 */         int index = 0;
/* 106 */         for (int i = 0; i < list.size(); i++) {
/* 107 */           if (((Integer)list.get(i)).intValue() == id) {
/* 108 */             index = i;
/*     */             break;
/*     */           } 
/*     */         } 
/* 112 */         list.remove(index);
/* 113 */         this.sortedMap.put(Byte.valueOf(mailEntity.getType()), list);
/* 114 */         delMailEntity(mailEntity);
/*     */       } 
/*     */     } 
/* 117 */     return 0;
/*     */   }
/*     */   
/*     */   public int getSize() {
/* 121 */     return this.size;
/*     */   }
/*     */   
/*     */   private void delMailEntity(MailEntity mailEntity) {
/* 125 */     this.size--;
/* 126 */     LogUtil.errorLog(new Object[] { "MailComponent:del", Long.valueOf(this.playerId), mailEntity.toString() });
/* 127 */     getProxy().delEntity(mailEntity);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/* 132 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/* 137 */     this.playerId = playerId;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\mail\MailComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */