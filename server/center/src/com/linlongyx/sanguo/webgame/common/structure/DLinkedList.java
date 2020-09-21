/*     */ package com.linlongyx.sanguo.webgame.common.structure;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DLinkedList<E>
/*     */ {
/*  10 */   transient int size = 0;
/*     */   
/*  12 */   private transient int modCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   transient Node<E> first;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   transient Node<E> last;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Node<E> linkFirst(E e) {
/*  28 */     Node<E> f = this.first;
/*  29 */     Node<E> newNode = new Node<>(null, e, f);
/*  30 */     this.first = newNode;
/*  31 */     if (f == null) {
/*  32 */       this.last = newNode;
/*     */     } else {
/*  34 */       f.prev = newNode;
/*  35 */     }  this.size++;
/*  36 */     this.modCount++;
/*  37 */     return newNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Node<E> linkLast(E e) {
/*  44 */     Node<E> l = this.last;
/*  45 */     Node<E> newNode = new Node<>(l, e, null);
/*  46 */     this.last = newNode;
/*  47 */     if (l == null) {
/*  48 */       this.first = newNode;
/*     */     } else {
/*  50 */       l.next = newNode;
/*  51 */     }  this.size++;
/*  52 */     this.modCount++;
/*  53 */     return newNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node<E> linkBefore(E e, Node<E> succ) {
/*  61 */     Node<E> pred = succ.prev;
/*  62 */     Node<E> newNode = new Node<>(pred, e, succ);
/*  63 */     succ.prev = newNode;
/*  64 */     if (pred == null) {
/*  65 */       this.first = newNode;
/*     */     } else {
/*  67 */       pred.next = newNode;
/*  68 */     }  this.size++;
/*  69 */     this.modCount++;
/*  70 */     return newNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private E unlinkFirst(Node<E> f) {
/*  79 */     E element = f.item;
/*  80 */     Node<E> next = f.next;
/*  81 */     f.item = null;
/*  82 */     f.next = null;
/*  83 */     this.first = next;
/*  84 */     if (next == null) {
/*  85 */       this.last = null;
/*     */     } else {
/*  87 */       next.prev = null;
/*  88 */     }  this.size--;
/*  89 */     this.modCount++;
/*  90 */     return element;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private E unlinkLast(Node<E> l) {
/*  98 */     E element = l.item;
/*  99 */     Node<E> prev = l.prev;
/* 100 */     l.item = null;
/* 101 */     l.prev = null;
/* 102 */     this.last = prev;
/* 103 */     if (prev == null) {
/* 104 */       this.first = null;
/*     */     } else {
/* 106 */       prev.next = null;
/* 107 */     }  this.size--;
/* 108 */     this.modCount++;
/* 109 */     return element;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public E unlink(Node<E> x) {
/* 117 */     E element = x.item;
/* 118 */     Node<E> next = x.next;
/* 119 */     Node<E> prev = x.prev;
/*     */     
/* 121 */     if (prev == null) {
/* 122 */       this.first = next;
/*     */     } else {
/* 124 */       prev.next = next;
/* 125 */       x.prev = null;
/*     */     } 
/*     */     
/* 128 */     if (next == null) {
/* 129 */       this.last = prev;
/*     */     } else {
/* 131 */       next.prev = prev;
/* 132 */       x.next = null;
/*     */     } 
/*     */     
/* 135 */     x.item = null;
/* 136 */     this.size--;
/* 137 */     this.modCount++;
/* 138 */     return element;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public E getFirst() {
/* 148 */     Node<E> f = this.first;
/* 149 */     if (f == null)
/* 150 */       throw new NoSuchElementException(); 
/* 151 */     return f.item;
/*     */   }
/*     */   
/*     */   public Node<E> getFirstNode() {
/* 155 */     Node<E> f = this.first;
/* 156 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public E getLast() {
/* 166 */     Node<E> l = this.last;
/* 167 */     if (l == null)
/* 168 */       throw new NoSuchElementException(); 
/* 169 */     return l.item;
/*     */   }
/*     */   
/*     */   public Node<E> getLastNode() {
/* 173 */     Node<E> l = this.last;
/* 174 */     if (l == null)
/* 175 */       throw new NoSuchElementException(); 
/* 176 */     return l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public E removeFirst() {
/* 187 */     Node<E> f = this.first;
/* 188 */     if (f == null)
/* 189 */       throw new NoSuchElementException(); 
/* 190 */     return unlinkFirst(f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public E removeLast() {
/* 200 */     Node<E> l = this.last;
/* 201 */     if (l == null)
/* 202 */       throw new NoSuchElementException(); 
/* 203 */     return unlinkLast(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node<E> addFirst(E e) {
/* 212 */     return linkFirst(e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node<E> addLast(E e) {
/* 223 */     return linkLast(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object o) {
/* 228 */     return (indexOf(o) != -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 237 */     return this.size;
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
/*     */   public int indexOf(Object o) {
/* 253 */     int index = 0;
/* 254 */     if (o == null) {
/* 255 */       for (Node<E> x = this.first; x != null; x = x.next) {
/* 256 */         if (x.item == null)
/* 257 */           return index; 
/* 258 */         index++;
/*     */       } 
/*     */     } else {
/* 261 */       for (Node<E> x = this.first; x != null; x = x.next) {
/* 262 */         if (o.equals(x.item))
/* 263 */           return index; 
/* 264 */         index++;
/*     */       } 
/*     */     } 
/* 267 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean moveNext(Node<E> node) {
/* 278 */     Node<E> prev = node.prev;
/* 279 */     Node<E> next = node.next;
/* 280 */     if (null == next) {
/* 281 */       return false;
/*     */     }
/* 283 */     if (null == prev) {
/* 284 */       node.next = next.next;
/* 285 */       next.next.prev = node;
/* 286 */       next.next = node;
/* 287 */       next.prev = null;
/* 288 */       node.prev = next;
/* 289 */       this.first = next;
/* 290 */       if (this.last.equals(next)) {
/* 291 */         node.next = null;
/* 292 */         this.last = node;
/*     */       } 
/* 294 */       return true;
/*     */     } 
/*     */     
/* 297 */     prev.next = next;
/* 298 */     if (null != next.next) {
/* 299 */       next.next.prev = node;
/*     */     }
/* 301 */     node.next = next.next;
/* 302 */     node.prev = next;
/*     */     
/* 304 */     next.prev = prev;
/* 305 */     next.next = node;
/*     */     
/* 307 */     if (this.last.equals(next)) {
/* 308 */       node.next = null;
/* 309 */       this.last = node;
/*     */     } 
/* 311 */     return true;
/*     */   }
/*     */   
/*     */   public void moveToNext(Node<E> node, Node<E> newNext) {
/* 315 */     Node<E> prev = node.prev;
/* 316 */     Node<E> next = node.next;
/*     */     
/* 318 */     node.next = newNext;
/* 319 */     node.prev = newNext.prev;
/* 320 */     newNext.prev.next = node;
/* 321 */     newNext.prev = node;
/*     */     
/* 323 */     next.prev = prev;
/* 324 */     if (null == prev) {
/* 325 */       this.first = next;
/*     */     } else {
/* 327 */       prev.next = next;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void moveToLast(Node<E> node) {
/* 333 */     Node<E> prev = node.prev;
/* 334 */     Node<E> next = node.next;
/*     */     
/* 336 */     node.prev = this.last;
/* 337 */     this.last.next = node;
/* 338 */     node.next = null;
/*     */     
/* 340 */     next.prev = prev;
/* 341 */     if (prev != null) {
/* 342 */       prev.next = next;
/*     */     } else {
/* 344 */       this.first = next;
/*     */     } 
/* 346 */     this.last = node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean movePrev(Node<E> node) {
/* 356 */     Node<E> prev = node.prev;
/* 357 */     Node<E> next = node.next;
/* 358 */     if (null == prev) {
/* 359 */       return false;
/*     */     }
/* 361 */     if (null == next) {
/* 362 */       node.prev = prev.prev;
/* 363 */       node.prev.next = node;
/* 364 */       prev.prev = node;
/* 365 */       prev.next = null;
/* 366 */       node.next = prev;
/* 367 */       this.last = prev;
/* 368 */       if (this.first.equals(prev)) {
/* 369 */         node.prev = null;
/* 370 */         this.first = node;
/*     */       } 
/* 372 */       return true;
/*     */     } 
/*     */     
/* 375 */     next.prev = prev;
/* 376 */     if (null != prev.prev) {
/* 377 */       prev.prev.next = node;
/*     */     }
/* 379 */     node.next = prev;
/* 380 */     node.prev = prev.prev;
/*     */     
/* 382 */     prev.next = next;
/* 383 */     prev.prev = node;
/*     */     
/* 385 */     if (this.first.equals(prev)) {
/* 386 */       node.prev = null;
/* 387 */       this.first = node;
/*     */     } 
/* 389 */     return true;
/*     */   }
/*     */   
/*     */   public void moveToPrev(Node<E> node, Node<E> newPrev) {
/* 393 */     Node<E> prev = node.prev;
/* 394 */     Node<E> next = node.next;
/*     */     
/* 396 */     node.prev = newPrev;
/* 397 */     node.next = newPrev.next;
/* 398 */     newPrev.next.prev = node;
/* 399 */     newPrev.next = node;
/*     */     
/* 401 */     prev.next = next;
/* 402 */     if (null == next) {
/* 403 */       this.last = prev;
/*     */     } else {
/* 405 */       next.prev = prev;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void moveToFirst(Node<E> node) {
/* 410 */     Node<E> prev = node.prev;
/* 411 */     Node<E> next = node.next;
/*     */     
/* 413 */     node.next = this.first;
/* 414 */     this.first.prev = node;
/* 415 */     node.prev = null;
/*     */     
/* 417 */     prev.next = next;
/* 418 */     if (next != null) {
/* 419 */       next.prev = prev;
/*     */     } else {
/* 421 */       this.last = prev;
/*     */     } 
/* 423 */     this.first = node;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 428 */     String str = "DLinkedList: ";
/* 429 */     for (Node<E> node = this.first; null != node; node = node.next) {
/* 430 */       str = str + node.getItem().toString() + "; ";
/*     */     }
/* 432 */     return str;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\structure\DLinkedList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */