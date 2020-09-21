/*    */ package com.linlongyx.sanguo.webgame.rmi.destiny;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import java.io.File;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyFileUtil
/*    */ {
/* 13 */   private static final DestinyFileData fileData = new DestinyFileData(System.getProperty("user.dir") + File.separator + "crossdata", "YUI");
/*    */   
/*    */   public static void readFromFile() {
/* 16 */     byte[] bytes = fileData.readFile();
/* 17 */     if (bytes != null) {
/* 18 */       DestinyCache.fromBytes(bytes);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void writeToFile() {
/* 23 */     byte[] bytes = DestinyCache.toBytes();
/* 24 */     if (bytes != null) {
/* 25 */       fileData.writeFile(bytes);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void saveRecord() {
/* 30 */     byte[] bytes = DestinyCache.toBytes();
/* 31 */     if (bytes != null) {
/* 32 */       fileData.saveRecord(bytes, "player_data_" + TimeUtil.currentTime() + ".dat");
/*    */     }
/*    */   }
/*    */   
/*    */   public static DestinyMap loadRecord(String fileName) {
/* 37 */     byte[] bytes = fileData.loadRecord(fileName);
/* 38 */     if (bytes != null) {
/* 39 */       return DestinyCache.loadRecord(bytes);
/*    */     }
/* 41 */     return new DestinyMap();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\DestinyFileUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */