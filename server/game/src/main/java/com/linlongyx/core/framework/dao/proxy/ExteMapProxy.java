/*     */ package com.linlongyx.core.framework.dao.proxy;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.EntityUtil;
/*     */ import com.linlongyx.core.framework.dao.IDAO;
/*     */ import com.linlongyx.core.framework.dao.entity.IExteData;
/*     */ import com.linlongyx.core.framework.dao.entity.IExteMapEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExteMapProxy
/*     */   implements IMapProxy
/*     */ {
/*     */   private Long playerId;
/*     */   private String tableName;
/*     */   private Class<? extends IExteMapEntity> entityClass;
/*     */   
/*     */   public long getPlayerId() {
/*  27 */     return this.playerId.longValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getEntityClass() {
/*  35 */     return this.entityClass;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/*  39 */     this.playerId = Long.valueOf(playerId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   private ConcurrentHashMap<String, IExteMapEntity> exteMaps = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   private ConcurrentLinkedQueue<String> deleteQueue = new ConcurrentLinkedQueue<>();
/*     */   
/*     */   public ExteMapProxy(Class<? extends IExteMapEntity> entityClass) {
/*  54 */     this.entityClass = entityClass;
/*  55 */     this.tableName = EntityUtil.getTableName(this.entityClass);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  60 */     Iterator<Map.Entry<String, IExteMapEntity>> iter = this.exteMaps.entrySet().iterator();
/*  61 */     while (iter.hasNext()) {
/*  62 */       Map.Entry<String, IExteMapEntity> entry = iter.next();
/*  63 */       ((IExteMapEntity)entry.getValue()).getData().reset();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getTableName() {
/*  68 */     return this.tableName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEntity(IExteMapEntity entity) {
/*  76 */     if (this.exteMaps.containsKey(entity.getSysClass()))
/*  77 */       return;  this.exteMaps.put(entity.getSysClass(), entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeEntity(IExteMapEntity entity) {
/*  86 */     if (this.exteMaps.containsKey(entity.getSysClass())) {
/*  87 */       this.exteMaps.remove(entity.getSysClass());
/*  88 */       this.deleteQueue.add(entity.getSysClass());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEntityKeyId() {
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityKeyId(String entityKeyId) {}
/*     */ 
/*     */   
/*     */   public IExteMapEntity getEntity(String key) {
/* 102 */     return this.exteMaps.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveAll() {
/* 108 */     return save();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean save() {
/* 113 */     IDAO dao = AppContext.getDAO();
/* 114 */     return (dao.getType() != IDAO.TYPE.REDIS && saveMY(dao));
/*     */   }
/*     */   
/*     */   private boolean saveMY(IDAO dao) {
/* 118 */     JdbcTemplate template = (JdbcTemplate)dao.getTemplate();
/* 119 */     String sql = makeUpdateSql();
/* 120 */     if (null != sql && !"".equals(sql)) template.execute(sql);
/*     */ 
/*     */     
/* 123 */     if (!this.deleteQueue.isEmpty()) {
/* 124 */       String delSql = makeDeleteSql();
/* 125 */       if (null != delSql && !"".equals(delSql)) {
/* 126 */         template.execute(delSql);
/* 127 */         this.deleteQueue.clear();
/*     */       } 
/*     */     } 
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean get() {
/* 135 */     IDAO dao = AppContext.getDAO();
/* 136 */     if (dao.getType() == IDAO.TYPE.REDIS) {
/* 137 */       return false;
/*     */     }
/* 139 */     return getMY(dao);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean getMY(IDAO dao) {
/* 144 */     JdbcTemplate template = (JdbcTemplate)dao.getTemplate();
/* 145 */     List<Map<String, Object>> info = template.queryForList(makeSelectSql());
/* 146 */     if (info.isEmpty()) return false; 
/* 147 */     initMY(info);
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   private void initMY(List<Map<String, Object>> initInfo) {
/* 152 */     for (Map<String, Object> info : initInfo) {
/*     */       try {
/* 154 */         IExteMapEntity o = this.entityClass.newInstance();
/* 155 */         String key = (String)info.get("sysClass");
/* 156 */         if (key == null || "".equals(key))
/* 157 */           continue;  o.setSysClass(key);
/* 158 */         o.setPlayerId(((Long)info.get("playerId")).longValue());
/* 159 */         IExteData exteData = (IExteData)Class.forName(key).newInstance();
/* 160 */         Object dataObj = info.get("data");
/* 161 */         exteData.setVal(dataObj.toString());
/* 162 */         o.setData(exteData);
/* 163 */         this.exteMaps.put(key, o);
/* 164 */       } catch (Exception e) {
/* 165 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends IExteMapEntity> T getSingleExteMapEntity(Class<? extends IExteData> clazz) {
/* 176 */     IExteMapEntity data = this.exteMaps.get(clazz.getName());
/* 177 */     if (data != null) return (T)data; 
/* 178 */     JdbcTemplate template = (JdbcTemplate)AppContext.getDAO();
/* 179 */     List<Map<String, Object>> info = template.queryForList(makeSingleSelectSql(clazz.getName()));
/* 180 */     if (info.isEmpty()) return null; 
/* 181 */     initMY(info);
/* 182 */     return (T)this.exteMaps.get(clazz.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public String makeSelectSql() {
/* 187 */     StringBuilder stringBuilder = new StringBuilder();
/* 188 */     stringBuilder.append("SELECT * FROM `");
/* 189 */     stringBuilder.append(getTableName()).append("`");
/*     */     
/* 191 */     stringBuilder.append(" WHERE `playerId` = '")
/* 192 */       .append(this.playerId).append("'");
/* 193 */     String res = stringBuilder.toString();
/* 194 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeSingleSelectSql(String name) {
/* 203 */     StringBuilder stringBuilder = new StringBuilder();
/* 204 */     stringBuilder.append("SELECT * FROM `");
/* 205 */     stringBuilder.append(getTableName()).append("`");
/*     */     
/* 207 */     stringBuilder.append(" WHERE `playerId` = '")
/* 208 */       .append(this.playerId).append("' and sysClass='" + name + "'");
/* 209 */     String res = stringBuilder.toString();
/* 210 */     return res;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, IMapEntity> getEntityMap() {
/* 215 */     return null;
/*     */   }
/*     */   
/*     */   private String makeUpdateSql() {
/* 219 */     StringBuilder stringBuilder = new StringBuilder();
/* 220 */     stringBuilder.append("REPLACE INTO `").append(getTableName()).append("` (");
/* 221 */     stringBuilder.append("`playerId`,");
/* 222 */     stringBuilder.append("`sysClass`,");
/* 223 */     stringBuilder.append("`data`");
/* 224 */     stringBuilder.append(") VALUES ");
/* 225 */     boolean notEmpty = false;
/* 226 */     Iterator<Map.Entry<String, IExteMapEntity>> iter = this.exteMaps.entrySet().iterator();
/* 227 */     while (iter.hasNext()) {
/* 228 */       Map.Entry<String, IExteMapEntity> entry = iter.next();
/* 229 */       if (entry.getValue() == null || ((IExteMapEntity)entry.getValue()).getData() == null)
/* 230 */         continue;  notEmpty = (((IExteMapEntity)entry.getValue()).getData().generateSql(stringBuilder, this.playerId.longValue()) || notEmpty);
/*     */     } 
/* 232 */     if (!notEmpty) return ""; 
/* 233 */     stringBuilder.setLength(stringBuilder.length() - 1);
/* 234 */     String s = stringBuilder.toString();
/* 235 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String makeDeleteSql() {
/* 243 */     StringBuilder stringBuilder = new StringBuilder();
/* 244 */     stringBuilder.append("DELETE FROM `").append(getTableName()).append("`");
/* 245 */     stringBuilder.append(" where `playerId` = ");
/* 246 */     stringBuilder.append(this.playerId);
/* 247 */     stringBuilder.append(" and `sysClass` in (");
/* 248 */     for (String delete : this.deleteQueue) {
/* 249 */       stringBuilder.append("'").append(delete).append("',");
/*     */     }
/* 251 */     stringBuilder.setLength(stringBuilder.length() - 1);
/* 252 */     stringBuilder.append(")");
/* 253 */     String s = stringBuilder.toString();
/* 254 */     return s;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\proxy\ExteMapProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */