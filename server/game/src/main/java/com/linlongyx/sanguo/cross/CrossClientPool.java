/*    */ package com.linlongyx.sanguo.cross;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossClientPool
/*    */ {
/*    */   private String host;
/*    */   private int port;
/*    */   private int initialCapacity;
/*    */   private CrossClient[] clients;
/*    */   
/*    */   public CrossClientPool(String host, int port) {
/* 15 */     this(host, port, 4);
/*    */   }
/*    */   
/*    */   private CrossClientPool(String host, int port, int initialCapacity) {
/* 19 */     this.initialCapacity = initialCapacity;
/* 20 */     this.host = host;
/* 21 */     this.port = port;
/* 22 */     this.clients = new CrossClient[initialCapacity];
/* 23 */     for (int i = 0; i < this.clients.length; i++) {
/* 24 */       this.clients[i] = new CrossClient(this.host, this.port);
/*    */     }
/*    */   }
/*    */   
/*    */   public CrossClient getClient(long playerId) {
/* 29 */     return this.clients[(int)(playerId % this.initialCapacity)];
/*    */   }
/*    */   
/*    */   public void start() {
/* 33 */     for (CrossClient client : this.clients) {
/* 34 */       client.connect();
/*    */     }
/*    */   }
/*    */   
/*    */   public void shutdown() {
/* 39 */     for (CrossClient client : this.clients) {
/* 40 */       client.close();
/*    */     }
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 45 */     CrossClientPool clientPool = new CrossClientPool("127.0.0.1", 19650);
/* 46 */     clientPool.start();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\CrossClientPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */