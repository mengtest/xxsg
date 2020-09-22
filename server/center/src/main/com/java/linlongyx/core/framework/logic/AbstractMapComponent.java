/*     */ package linlongyx.core.framework.logic;
/*     */ 
/*     */ import linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.dao.proxy.MapProxy;
/*     */ import com.linlongyx.core.utils.ClassUtil;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractMapComponent<T extends IEntity>
/*     */   implements IComponent
/*     */ {
/*     */   protected long playerId;
/*     */   protected long userId;
/*     */   protected MapProxy proxy;
/*     */   private boolean isDBInit;
/*     */   private boolean needSaveToDB;
/*     */   
/*     */   protected AbstractMapComponent(Class<? extends IMapEntity> clazz, long playerId) {
/*  30 */     this.proxy = new MapProxy(clazz, playerId);
/*  31 */     this.playerId = playerId;
/*  32 */     makeKey();
/*     */   }
/*     */   
/*     */   protected void makeKey() {
/*  36 */     Table prefix = (Table)this.proxy.getEntityClass().getAnnotation(Table.class);
/*  37 */     String name = prefix.prefix();
/*     */     
/*  39 */     String dbKey = name.replace("%playerId", Long.toString(this.playerId)).replace("%serverId", AppContext.getServerId()).replace("%userId", Long.toString(this.userId));
/*  40 */     this.proxy.setPlayerId(this.playerId);
/*  41 */     this.proxy.setEntityKeyId(dbKey);
/*     */   }
/*     */   
/*     */   public boolean isDBInit() {
/*  45 */     return this.isDBInit;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getPlayerId() {
/*  50 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/*  54 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getUserId() {
/*  58 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(long userId) {
/*  62 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public MapProxy getProxy() {
/*  66 */     return this.proxy;
/*     */   }
/*     */   
/*     */   public void setProxy(MapProxy proxy) {
/*  70 */     this.proxy = proxy;
/*     */   }
/*     */   
/*     */   public void maybeSaveToDB() {
/*  74 */     if (this.needSaveToDB)
/*  75 */       saveToDB(); 
/*     */   }
/*     */   
/*     */   public void setNeedSaveToDB(boolean needSaveToDB) {
/*  79 */     this.needSaveToDB = needSaveToDB;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean saveToDB() {
/*  84 */     return this.proxy.save();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean saveAllToDB() {
/*  89 */     return this.proxy.saveAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  94 */     if (!getFromDB()) {
/*  95 */       build(this.playerId);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getFromDB() {
/* 101 */     if (this.proxy.get()) {
/* 102 */       this.isDBInit = true;
/* 103 */       return true;
/*     */     } 
/* 105 */     return false;
/*     */   }
/*     */   
/*     */   public void setUpdate(String key, String field) {
/* 109 */     getProxy().setUpdate(key, field);
/*     */   }
/*     */   
/*     */   public Map<String, IMapEntity> getEntityMap() {
/* 113 */     return getProxy().getEntityMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T getEntity(String key) {
/* 120 */     return (T)getProxy().getEntity(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T createEntity(Object key) {
/* 127 */     return (T)getProxy().createProxy(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEntity(T entity) {
/* 136 */     getProxy().addEntity((IMapEntity)entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 150 */     StringBuilder stringBuilder = new StringBuilder();
/* 151 */     stringBuilder.append(getClass().getSimpleName() + ":");
/* 152 */     this.proxy.getEntityMap().values().forEach(iMapEntity -> stringBuilder.append(iMapEntity.toString()));
/*     */ 
/*     */     
/* 155 */     stringBuilder.append(";<br>");
/* 156 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String setValue(String id, String fieldName, String value) {
/*     */     try {
/* 162 */       Field field = this.proxy.getEntityClass().getDeclaredField(fieldName);
/* 163 */       ClassUtil.invokeSet(getEntity(id), field, value);
/* 164 */     } catch (NoSuchFieldException e) {
/* 165 */       e.printStackTrace();
/*     */     } 
/* 167 */     return toString();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\logic\AbstractMapComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */