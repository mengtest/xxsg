/*     */ package com.linlongyx.sanguo.webgame.common.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NumberUtil
/*     */ {
/*   9 */   public static final int[] sizeTable = new int[] { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE };
/*     */   
/*     */   public static int sizeOfInt(int x) {
/*  12 */     for (int i = 0;; i++) {
/*  13 */       if (x <= sizeTable[i])
/*  14 */         return i + 1; 
/*     */     } 
/*     */   }
/*     */   public static int sizeOfInt(long x) {
/*  18 */     for (int i = 0;; i++) {
/*  19 */       if (x <= sizeTable[i]) {
/*  20 */         return i + 1;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int bigType(int itemId) {
/*  29 */     return itemId / 10000000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int secType(int itemId) {
/*  38 */     return itemId / 100000 % 100;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int blessThreeType(int itemId) {
/*  47 */     return itemId / 10000 % 10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int blessFourType(int itemId) {
/*  56 */     return itemId / 100 % 100;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int blessFiveType(int itemId) {
/*  65 */     return itemId % 100;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isTool(int itemId) {
/*  74 */     return (bigType(itemId) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEquip(int itemId) {
/*  83 */     return (bigType(itemId) == 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isAwakeMaterial(int itemId) {
/*  92 */     return (bigType(itemId) == 4);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/*  96 */     System.out.println(bigType(50720801));
/*  97 */     System.out.println(secType(50720801));
/*  98 */     System.out.println(blessThreeType(50720801));
/*  99 */     System.out.println(blessFourType(50720801));
/* 100 */     System.out.println(blessFiveType(50720801));
/* 101 */     System.out.println("-----------");
/* 102 */     System.out.println(5);
/* 103 */     System.out.println(1);
/* 104 */     System.out.println(1);
/* 105 */     System.out.println(1);
/* 106 */     System.out.println(1);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\commo\\util\NumberUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */