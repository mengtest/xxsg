/*     */ package com.linlongyx.sanguo.webgame.service;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.springframework.core.io.Resource;
/*     */ 
/*     */ public class JsonTableService {
/*     */   private static JsonTableService instance;
/*     */   private Map<String, Resource> jsonTables;
/*     */   private HashMap<String, HashMap<Integer, Object>> jsonMap;
/*     */   private HashMap<String, TreeSet<Integer>> jsonKeyMap;
/*     */   
/*     */   public void setJsonTables(Map<String, Resource> value) {
/*  26 */     this.jsonTables = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonTableService() {
/*  31 */     if (instance == null) {
/*  32 */       instance = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean initJsonTable() {
/*  37 */     instance.jsonMap = new HashMap<>();
/*  38 */     instance.jsonKeyMap = new HashMap<>();
/*     */     
/*  40 */     instance.jsonTables.keySet().forEach(this::parseJsonTable);
/*  41 */     ParameterConstant.init();
/*  42 */     FightConstant.reload();
/*  43 */     return true;
/*     */   }
/*     */   
/*     */   private void parseJsonTable(String key) {
/*  47 */     Gson gson = new Gson();
/*  48 */     Class<?> onwClass = null;
/*     */     try {
/*  50 */       onwClass = Class.forName(key);
/*  51 */     } catch (Exception e) {
/*  52 */       System.out.println(key + "：此类有错误");
/*  53 */       LogUtil.errorLog(new Object[] { key + "：此类有错误" });
/*     */       return;
/*     */     } 
/*  56 */     Resource resource = instance.jsonTables.get(key);
/*     */     
/*  58 */     HashMap<Integer, Object> list = new HashMap<>();
/*  59 */     TreeSet<Integer> keySet = new TreeSet<>();
/*  60 */     try (Reader reader = new InputStreamReader(new FileInputStream(resource.getFile()))) {
/*  61 */       JsonObject jsonobject = (JsonObject)gson.fromJson(reader, JsonObject.class);
/*  62 */       Set var2 = jsonobject.entrySet();
/*  63 */       Object[] var3 = var2.toArray();
/*  64 */       for (Object aVar3 : var3) {
/*  65 */         Map.Entry tmp = (Map.Entry)aVar3;
/*  66 */         Object item = gson.fromJson(tmp.getValue().toString(), onwClass);
/*  67 */         list.put(Integer.valueOf(Integer.parseInt((String)tmp.getKey())), item);
/*  68 */         keySet.add(Integer.valueOf(Integer.parseInt((String)tmp.getKey())));
/*     */       } 
/*  70 */       instance.jsonKeyMap.put(key, keySet);
/*  71 */       instance.jsonMap.put(key, list);
/*  72 */     } catch (Exception e) {
/*  73 */       System.out.println(key + "：此类有错误");
/*  74 */       LogUtil.errorLog(new Object[] { key + "：此类有错误" });
/*  75 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void hotImportJsonTable(String key) {
/*  84 */     instance.parseJsonTable(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void hotImportAllJsonTable() {
/*  91 */     List<String> list = getTabNames();
/*  92 */     for (String table : list) {
/*  93 */       instance.parseJsonTable(table);
/*     */     }
/*  95 */     FightConstant.reload();
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
/* 106 */     HashMap<Integer, Object> list = instance.jsonMap.get(className.getName());
/* 107 */     if (list == null) {
/* 108 */       System.out.println(className);
/*     */     }
/*     */     
/* 111 */     T jsonObject = (T)list.get(Integer.valueOf(id));
/* 112 */     return jsonObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<Integer, Object> getJsonTable(Class<?> className) {
/* 121 */     HashMap<Integer, Object> map = instance.jsonMap.get(className.getName());
/* 122 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TreeSet<Integer> getJsonTableKey(Class<?> className) {
/* 131 */     TreeSet<Integer> set = instance.jsonKeyMap.get(className.getName());
/* 132 */     return set;
/*     */   }
/*     */   
/*     */   public String listJsons() {
/* 136 */     StringBuilder stringBuilder = new StringBuilder();
/* 137 */     this.jsonMap.keySet().forEach(s -> stringBuilder.append(s).append("<br>"));
/* 138 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public String showJson(String key) {
/* 142 */     StringBuilder stringBuilder = new StringBuilder();
/* 143 */     ((HashMap)this.jsonMap.get(key)).values().forEach(o -> stringBuilder.append(o.toString()).append("<br>"));
/* 144 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public static List<String> getTabNames() {
/* 148 */     List<String> list = new ArrayList<>();
/* 149 */     for (String key : instance.jsonMap.keySet()) {
/* 150 */       list.add(key);
/*     */     }
/* 152 */     return list;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\service\JsonTableService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */