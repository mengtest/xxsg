/*    */ package com.linlongyx.sanguo.webgame.common.structure;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Node<E>
/*    */ {
/*    */   E item;
/*    */   public Node<E> next;
/*    */   public Node<E> prev;
/*    */   
/*    */   public Node(Node<E> prev, E element, Node<E> next) {
/* 14 */     this.item = element;
/* 15 */     this.next = next;
/* 16 */     this.prev = prev;
/*    */   }
/*    */   
/*    */   public E getItem() {
/* 20 */     return this.item;
/*    */   }
/*    */   
/*    */   public void setItem(E item) {
/* 24 */     this.item = item;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 28 */     return this.item.hashCode();
/*    */   }
/*    */   
/*    */   public boolean equals(Node<E> node) {
/* 32 */     if (null == node)
/* 33 */       return false; 
/* 34 */     return this.item.equals(node.getItem());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\structure\Node.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */