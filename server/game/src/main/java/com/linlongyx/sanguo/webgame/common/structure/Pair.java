/*    */ package com.linlongyx.sanguo.webgame.common.structure;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Pair<K, V>
/*    */   implements Map.Entry<K, V>, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private K key;
/*    */   private V value;
/*    */   
/*    */   public Pair(K key, V value) {
/* 18 */     this.key = key;
/* 19 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public K getKey() {
/* 24 */     return this.key;
/*    */   }
/*    */   
/*    */   public K setKey(K key) {
/* 28 */     return this.key = key;
/*    */   }
/*    */ 
/*    */   
/*    */   public V getValue() {
/* 33 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public V setValue(V value) {
/* 38 */     return this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 56 */     return this.key.hashCode() * 13 + ((this.value == null) ? 0 : this.value.hashCode());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 77 */     if (this == o) return true; 
/* 78 */     if (o instanceof Pair) {
/* 79 */       Pair kv = (Pair)o;
/* 80 */       if ((this.key != null) ? !this.key.equals(kv.key) : (kv.key != null)) return false; 
/* 81 */       if ((this.value != null) ? !this.value.equals(kv.value) : (kv.value != null)) return false; 
/* 82 */       return true;
/*    */     } 
/* 84 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\structure\Pair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */