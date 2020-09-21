/*    */ package com.linlongyx.sanguo.webgame.common.structure;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Map;
/*    */ 
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
/*    */   public Pair() {}
/*    */   
/*    */   public Pair(K key, V value) {
/* 21 */     this.key = key;
/* 22 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public K getKey() {
/* 27 */     return this.key;
/*    */   }
/*    */   
/*    */   public K setKey(K key) {
/* 31 */     return this.key = key;
/*    */   }
/*    */ 
/*    */   
/*    */   public V getValue() {
/* 36 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public V setValue(V value) {
/* 41 */     return this.value = value;
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
/* 59 */     return this.key.hashCode() * 13 + ((this.value == null) ? 0 : this.value.hashCode());
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
/* 80 */     if (this == o) return true; 
/* 81 */     if (o instanceof Pair) {
/* 82 */       Pair kv = (Pair)o;
/* 83 */       if ((this.key != null) ? !this.key.equals(kv.key) : (kv.key != null)) return false; 
/* 84 */       if ((this.value != null) ? !this.value.equals(kv.value) : (kv.value != null)) return false; 
/* 85 */       return true;
/*    */     } 
/* 87 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\structure\Pair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */