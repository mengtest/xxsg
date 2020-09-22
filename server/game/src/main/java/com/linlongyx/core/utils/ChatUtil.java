/*     */ package com.linlongyx.core.utils;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.springframework.core.io.Resource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChatUtil
/*     */ {
/*     */   private static ChatUtil instance;
/*     */   private static Resource sensitiveWord;
/*     */   private static Resource name;
/*     */   private static HashMap sensitiveWordMap;
/*     */   private static HashMap nameMap;
/*     */   
/*     */   public ChatUtil() {
/*  32 */     instance = this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initSensitiveWord() {
/*  51 */     Set<String> keyWordSet = readSensitiveWordFile();
/*  52 */     addSensitiveWordToHashMap(keyWordSet);
/*  53 */     keyWordSet = readNameFile();
/*  54 */     addNameToHashMap(keyWordSet);
/*     */   }
/*     */   
/*     */   public static void reloadSensitiveWord() {
/*  58 */     Set<String> keyWordSet = readSensitiveWordFile();
/*  59 */     addSensitiveWordToHashMap(keyWordSet);
/*  60 */     keyWordSet = readNameFile();
/*  61 */     addNameToHashMap(keyWordSet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isLegalName(String name) {
/*  69 */     for (int i = 0; i < name.length(); i++) {
/*  70 */       int length = CheckSensitiveWord(name, i, sensitiveWordMap);
/*  71 */       if (length > 0) {
/*  72 */         return false;
/*     */       }
/*  74 */       length = CheckSensitiveWord(name, i, nameMap);
/*  75 */       if (length > 0) {
/*  76 */         return false;
/*     */       }
/*     */     } 
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String replaceSensitiveWord(String txt, String replaceChar) {
/*  89 */     txt = txt.replaceAll("\\s*", "");
/*  90 */     String resultTxt = txt;
/*  91 */     Set<String> set = getSensitiveWord(txt);
/*  92 */     Iterator<String> iterator = set.iterator();
/*  93 */     String word = null;
/*  94 */     String replaceString = null;
/*  95 */     while (iterator.hasNext()) {
/*  96 */       word = iterator.next();
/*  97 */       replaceString = getReplaceChars(replaceChar, word.length());
/*  98 */       resultTxt = resultTxt.replace(word, replaceString);
/*     */     } 
/* 100 */     return resultTxt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getReplaceChars(String replaceChar, int length) {
/* 110 */     String resultReplace = replaceChar;
/* 111 */     for (int i = 1; i < length; i++) {
/* 112 */       resultReplace = resultReplace + replaceChar;
/*     */     }
/* 114 */     return resultReplace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Set<String> getSensitiveWord(String txt) {
/* 124 */     Set<String> sensitiveWordList = new HashSet<>();
/* 125 */     String str = "";
/* 126 */     for (int i = 0; i < txt.length(); i++) {
/* 127 */       int length = CheckSensitiveWord(txt, i, sensitiveWordMap);
/* 128 */       if (length > 0) {
/* 129 */         sensitiveWordList.add(txt.substring(i, i + length));
/* 130 */         i = i + length - 1;
/*     */       } 
/* 132 */       boolean isNum = false;
/* 133 */       if (txt.charAt(i) >= '0' && txt.charAt(i) <= '9') {
/* 134 */         str = str + txt.charAt(i);
/* 135 */         isNum = true;
/*     */       } 
/* 137 */       if (!isNum || i == txt.length() - 1) {
/* 138 */         if (str.length() >= 4) {
/* 139 */           sensitiveWordList.add(str);
/*     */         }
/* 141 */         str = "";
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 146 */     return sensitiveWordList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFirstSensitiveWord(String txt) {
/* 157 */     for (int i = 0; i < txt.length(); i++) {
/* 158 */       int length = CheckSensitiveWord(txt, i, sensitiveWordMap);
/* 159 */       if (length > 0) {
/* 160 */         return txt.substring(i, i + length);
/*     */       }
/*     */     } 
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int CheckSensitiveWord(String txt, int beginIndex, HashMap map) {
/* 173 */     boolean flag = false;
/* 174 */     int matchFlag = 0;
/* 175 */     int resultFlag = 0;
/* 176 */     char word = Character.MIN_VALUE;
/* 177 */     Map nowMap = map;
/* 178 */     for (int i = beginIndex; i < txt.length(); i++) {
/* 179 */       word = txt.charAt(i);
/* 180 */       if (word == ' ') {
/* 181 */         matchFlag++;
/*     */       } else {
/*     */         
/* 184 */         nowMap = (Map)nowMap.get(Character.valueOf(word));
/* 185 */         if (nowMap != null) {
/* 186 */           matchFlag++;
/* 187 */           if ("1".equals(nowMap.get("isEnd"))) {
/* 188 */             flag = true;
/* 189 */             resultFlag = matchFlag;
/*     */           } 
/*     */         } else {
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 199 */     return resultFlag;
/*     */   }
/*     */   
/*     */   private static Set<String> readSensitiveWordFile() {
/* 203 */     String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "dragonball" + File.separator + "sensitiveWord.txt";
/*     */     
/*     */     try {
/* 206 */       filePath = sensitiveWord.getFile().getPath();
/* 207 */     } catch (Exception e) {
/* 208 */       e.printStackTrace();
/*     */     } 
/* 210 */     return readFileToSet(filePath);
/*     */   }
/*     */   
/*     */   private static Set<String> readNameFile() {
/* 214 */     String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "dragonball" + File.separator + "nameWord.txt";
/*     */     
/*     */     try {
/* 217 */       filePath = name.getFile().getPath();
/* 218 */     } catch (Exception e) {
/* 219 */       e.printStackTrace();
/*     */     } 
/* 221 */     return readFileToSet(filePath);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Set<String> readFileToSet(String filePath) {
/* 230 */     File file = new File(filePath);
/* 231 */     Set<String> set = null;
/* 232 */     if (file.isFile() && file.exists()) {
/* 233 */       try (InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8")) {
/* 234 */         set = new HashSet<>();
/* 235 */         BufferedReader bufferedReader = new BufferedReader(read);
/* 236 */         String content = null;
/* 237 */         while ((content = bufferedReader.readLine()) != null) {
/* 238 */           set.add(content);
/*     */         }
/* 240 */       } catch (Exception e) {
/* 241 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/* 244 */       System.err.println("readSensitiveWordFile file is not found!");
/*     */     } 
/* 246 */     return set;
/*     */   }
/*     */   
/*     */   private static void addSensitiveWordToHashMap(Set<String> keyWordSet) {
/* 250 */     sensitiveWordMap = new HashMap<>(keyWordSet.size());
/* 251 */     String key = null;
/* 252 */     Map<Character, Map<String, String>> nowMap = null;
/* 253 */     Map<String, String> newWorMap = null;
/*     */     
/* 255 */     Iterator<String> iterator = keyWordSet.iterator();
/* 256 */     while (iterator.hasNext()) {
/* 257 */       key = iterator.next();
/* 258 */       nowMap = sensitiveWordMap;
/* 259 */       for (int i = 0; i < key.length(); i++) {
/* 260 */         Map<String, String> map = new HashMap<>(); char keyChar = key.charAt(i);
/* 261 */         Object wordMap = nowMap.get(Character.valueOf(keyChar));
/*     */         
/* 263 */         if (wordMap != null) {
/* 264 */           nowMap = (Map)wordMap;
/*     */         }
/* 266 */         else if (keyChar != ' ') {
/* 267 */           newWorMap = new HashMap<>();
/* 268 */           newWorMap.put("isEnd", "0");
/* 269 */           nowMap.put(Character.valueOf(keyChar), newWorMap);
/* 270 */           map = newWorMap;
/*     */         } 
/*     */         
/* 273 */         if (i == key.length() - 1) {
/* 274 */           map.put("isEnd", "1");
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void addNameToHashMap(Set<String> keyWordSet) {
/* 281 */     nameMap = new HashMap<>(keyWordSet.size());
/* 282 */     String key = null;
/* 283 */     Map<Character, Map<String, String>> nowMap = null;
/* 284 */     Map<String, String> newWorMap = null;
/*     */     
/* 286 */     Iterator<String> iterator = keyWordSet.iterator();
/* 287 */     while (iterator.hasNext()) {
/* 288 */       key = iterator.next();
/* 289 */       nowMap = nameMap;
/* 290 */       for (int i = 0; i < key.length(); i++) {
/* 291 */         Map<String, String> map = new HashMap<>(); char keyChar = key.charAt(i);
/* 292 */         Object wordMap = nowMap.get(Character.valueOf(keyChar));
/*     */         
/* 294 */         if (wordMap != null) {
/* 295 */           nowMap = (Map)wordMap;
/*     */         } else {
/* 297 */           newWorMap = new HashMap<>();
/* 298 */           newWorMap.put("isEnd", "0");
/* 299 */           nowMap.put(Character.valueOf(keyChar), newWorMap);
/* 300 */           map = newWorMap;
/*     */         } 
/* 302 */         if (i == key.length() - 1) {
/* 303 */           map.put("isEnd", "1");
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Resource getSensitiveWord() {
/* 314 */     return sensitiveWord;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSensitiveWord(Resource sensitiveWord) {
/* 321 */     ChatUtil.sensitiveWord = sensitiveWord;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Resource getName() {
/* 328 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(Resource name) {
/* 335 */     ChatUtil.name = name;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\cor\\utils\ChatUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */