/*     */ package com.linlongyx.sanguo.webgame.common.util;
/*     */ 
/*     */ import io.netty.util.internal.ThreadLocalRandom;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class RandUtil
/*     */ {
/*     */   public static boolean isRandTrue(int rate) {
/*  21 */     int rand = ThreadLocalRandom.current().nextInt(10000);
/*  22 */     return (rand < rate);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isRandTrue() {
/*  31 */     int rand = ThreadLocalRandom.current().nextInt(2);
/*  32 */     return (rand == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int randNum() {
/*  43 */     return ThreadLocalRandom.current().nextInt(10000);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int randNum(int max) {
/*  53 */     return ThreadLocalRandom.current().nextInt(max + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int randNum(int min, int max) {
/*  64 */     return ThreadLocalRandom.current().nextInt(max - min + 1) + min;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int randomList(ArrayList<Integer> list) {
/*  74 */     return ((Integer)list.get(randNum(list.size() - 1))).intValue();
/*     */   }
/*     */   
/*     */   public static <T> T randomList(List<T> list, int index, int range) {
/*  78 */     if (list.size() == 1) {
/*  79 */       return list.get(0);
/*     */     }
/*  81 */     int min = index - range;
/*  82 */     if (min < 0) {
/*  83 */       min = 0;
/*     */     }
/*  85 */     int max = index + range;
/*  86 */     if (max > list.size() - 1) {
/*  87 */       max = list.size() - 1;
/*     */     }
/*  89 */     int nIndex = randNum(min, max);
/*  90 */     while (nIndex == index) {
/*  91 */       nIndex = randNum(min, max);
/*     */     }
/*  93 */     return list.get(nIndex);
/*     */   }
/*     */   
/*     */   public static int randomIndex(ArrayList<Integer> list) {
/*  97 */     int all = (int)list.stream().mapToInt(x -> x.intValue()).summaryStatistics().getSum();
/*  98 */     int rand = randNum(all);
/*     */     
/* 100 */     int index = 0;
/* 101 */     for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/* 102 */       if (rand <= i) {
/*     */         break;
/*     */       }
/* 105 */       rand -= i;
/* 106 */       index++; }
/*     */ 
/*     */     
/* 109 */     return index;
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
/*     */   public static int[] randomArray(int min, int max, int n) {
/* 121 */     int len = max - min + 1;
/* 122 */     if (max < min || n > len) {
/* 123 */       return null;
/*     */     }
/* 125 */     int[] source = new int[len];
/* 126 */     for (int i = min; i < min + len; i++) {
/* 127 */       source[i - min] = i;
/*     */     }
/* 129 */     int[] result = new int[n];
/*     */     
/* 131 */     for (int j = 0; j < result.length; j++) {
/* 132 */       int index = randNum() % len;
/* 133 */       len--;
/* 134 */       result[j] = source[index];
/* 135 */       source[index] = source[len];
/*     */     } 
/* 137 */     return result;
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
/*     */   public static void main(String[] args) {
/* 154 */     int[] a = randomArray(1, 6, 6);
/* 155 */     for (int i : a)
/* 156 */       System.out.println(i); 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\commo\\util\RandUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */