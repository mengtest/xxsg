/*     */ package com.linlongyx.sanguo.webgame.service;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.core.io.Resource;
/*     */ 
/*     */ public class JsonTableService {
/*  21 */   private static final Logger LOG = LoggerFactory.getLogger(JsonTableService.class);
/*     */   
/*     */   private static JsonTableService instance;
/*     */   
/*     */   private Map<String, Resource> jsonTables;
/*     */   private HashMap<String, HashMap<Integer, Object>> jsonMap;
/*     */   private HashMap<String, TreeSet<Integer>> jsonKeyMap;
/*     */   
/*     */   public void setJsonTables(Map<String, Resource> value) {
/*  30 */     this.jsonTables = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonTableService() {
/*  35 */     if (instance == null) {
/*  36 */       instance = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean initJsonTable() {
/*  41 */     instance.jsonMap = new HashMap<>();
/*  42 */     instance.jsonKeyMap = new HashMap<>();
/*     */     
/*  44 */     instance.jsonTables.keySet().forEach(this::parseJsonTable);
/*  45 */     ParameterConstant.init();
/*  46 */     FightConstant.reload();
/*  47 */     return true;
/*     */   }
/*     */   
/*     */   private void parseJsonTable(String key) {
/*  51 */     Gson gson = new Gson();
/*  52 */     Class<?> onwClass = null;
/*     */     try {
/*  54 */       onwClass = Class.forName(key);
/*  55 */     } catch (Exception e) {
/*  56 */       LogUtils.errorLog(new Object[] { "此类有错误:" + key });
/*  57 */       System.out.println(key + "：此类有错误");
/*     */       return;
/*     */     } 
/*  60 */     Resource resource = instance.jsonTables.get(key);
/*     */     
/*  62 */     HashMap<Integer, Object> list = new HashMap<>();
/*  63 */     TreeSet<Integer> keySet = new TreeSet<>();
/*  64 */     try (Reader reader = new InputStreamReader(new FileInputStream(resource.getFile()))) {
/*  65 */       JsonObject jsonobject = (JsonObject)gson.fromJson(reader, JsonObject.class);
/*  66 */       Set var2 = jsonobject.entrySet();
/*  67 */       Object[] var3 = var2.toArray();
/*  68 */       for (Object aVar3 : var3) {
/*  69 */         Map.Entry tmp = (Map.Entry)aVar3;
/*  70 */         Object item = gson.fromJson(tmp.getValue().toString(), onwClass);
/*  71 */         list.put(Integer.valueOf(Integer.parseInt((String)tmp.getKey())), item);
/*  72 */         keySet.add(Integer.valueOf(Integer.parseInt((String)tmp.getKey())));
/*     */       } 
/*  74 */       instance.jsonKeyMap.put(key, keySet);
/*  75 */       instance.jsonMap.put(key, list);
/*  76 */     } catch (Exception e) {
/*  77 */       LogUtils.errorLog(new Object[] { "此类有错误:" + key });
/*  78 */       System.out.println(key + "：此类有错误");
/*  79 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void hotImportJsonTable(String key) {
/*  88 */     instance.parseJsonTable(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void hotImportAllJsonTable() {
/*  95 */     List<String> list = getTabNames();
/*  96 */     for (String table : list) {
/*  97 */       instance.parseJsonTable(table);
/*     */     }
/*  99 */     FightConstant.reload();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> T getJsonData(int id, Class<?> className) {
/* 110 */     HashMap<Integer, Object> list = instance.jsonMap.get(className.getName());
/*     */     
/* 112 */     T jsonObject = (T)list.get(Integer.valueOf(id));
/* 113 */     return jsonObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<Integer, Object> getJsonTable(Class<?> className) {
/* 122 */     HashMap<Integer, Object> map = instance.jsonMap.get(className.getName());
/* 123 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TreeSet<Integer> getJsonTableKey(Class<?> className) {
/* 132 */     TreeSet<Integer> set = instance.jsonKeyMap.get(className.getName());
/* 133 */     return set;
/*     */   }
/*     */   
/*     */   public String listJsons() {
/* 137 */     StringBuilder stringBuilder = new StringBuilder();
/* 138 */     this.jsonMap.keySet().forEach(s -> stringBuilder.append(s).append("<br>"));
/* 139 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public String showJson(String key) {
/* 143 */     StringBuilder stringBuilder = new StringBuilder();
/* 144 */     ((HashMap)this.jsonMap.get(key)).values().forEach(o -> stringBuilder.append(o.toString()).append("<br>"));
/* 145 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public static List<String> getTabNames() {
/* 149 */     List<String> list = new ArrayList<>();
/* 150 */     for (String key : instance.jsonMap.keySet()) {
/* 151 */       list.add(key);
/*     */     }
/* 153 */     return list;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\JsonTableService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */