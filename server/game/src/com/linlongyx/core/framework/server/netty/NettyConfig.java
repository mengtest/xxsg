/*     */ package com.linlongyx.core.framework.server.netty;
/*     */ 
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelInitializer;
/*     */ import io.netty.channel.ChannelOption;
/*     */ import io.netty.channel.nio.NioEventLoopGroup;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NettyConfig
/*     */ {
/*     */   private Map<ChannelOption<?>, Object> channelOptions;
/*     */   private NioEventLoopGroup bossGroup;
/*     */   private NioEventLoopGroup workerGroup;
/*     */   private int bossThreadCount;
/*     */   private int workerThreadCount;
/*     */   private InetSocketAddress socketAddress;
/*  21 */   private int portNumber = 18090;
/*     */   private boolean ssl = false;
/*     */   private static final int PORT_NUM = 1000;
/*     */   protected ChannelInitializer<? extends Channel> channelInitializer;
/*     */   
/*     */   public Map<ChannelOption<?>, Object> getChannelOptions() {
/*  27 */     return this.channelOptions;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChannelOptions(Map<ChannelOption<?>, Object> channelOptions) {
/*  32 */     this.channelOptions = channelOptions;
/*     */   }
/*     */   
/*     */   public synchronized NioEventLoopGroup getBossGroup() {
/*  36 */     if (null == this.bossGroup) {
/*  37 */       if (0 >= this.bossThreadCount) {
/*  38 */         this.bossGroup = new NioEventLoopGroup();
/*     */       } else {
/*  40 */         this.bossGroup = new NioEventLoopGroup(this.bossThreadCount);
/*     */       } 
/*     */     }
/*  43 */     return this.bossGroup;
/*     */   }
/*     */   
/*     */   public void setBossGroup(NioEventLoopGroup bossGroup) {
/*  47 */     this.bossGroup = bossGroup;
/*     */   }
/*     */   
/*     */   public synchronized NioEventLoopGroup getWorkerGroup() {
/*  51 */     if (null == this.workerGroup) {
/*  52 */       if (0 >= this.workerThreadCount) {
/*  53 */         this.workerGroup = new NioEventLoopGroup();
/*     */       } else {
/*  55 */         this.workerGroup = new NioEventLoopGroup(this.workerThreadCount);
/*     */       } 
/*     */     }
/*  58 */     return this.workerGroup;
/*     */   }
/*     */   
/*     */   public void setWorkerGroup(NioEventLoopGroup workerGroup) {
/*  62 */     this.workerGroup = workerGroup;
/*     */   }
/*     */   
/*     */   public int getBossThreadCount() {
/*  66 */     return this.bossThreadCount;
/*     */   }
/*     */   
/*     */   public void setBossThreadCount(int bossThreadCount) {
/*  70 */     this.bossThreadCount = bossThreadCount;
/*     */   }
/*     */   
/*     */   public int getWorkerThreadCount() {
/*  74 */     return this.workerThreadCount;
/*     */   }
/*     */   
/*     */   public void setWorkerThreadCount(int workerThreadCount) {
/*  78 */     this.workerThreadCount = workerThreadCount;
/*     */   }
/*     */   
/*     */   public synchronized InetSocketAddress getSocketAddress() {
/*  82 */     if (this.ssl)
/*  83 */       this.portNumber -= 1000; 
/*  84 */     if (null == this.socketAddress) {
/*  85 */       this.socketAddress = new InetSocketAddress(this.portNumber);
/*     */     }
/*  87 */     return this.socketAddress;
/*     */   }
/*     */   
/*     */   public void setSocketAddress(InetSocketAddress socketAddress) {
/*  91 */     this.socketAddress = socketAddress;
/*     */   }
/*     */   
/*     */   public int getPortNumber() {
/*  95 */     return this.portNumber;
/*     */   }
/*     */   
/*     */   public void setPortNumber(int portNumber) {
/*  99 */     this.portNumber = portNumber;
/*     */   }
/*     */   
/*     */   public boolean isSsl() {
/* 103 */     return this.ssl;
/*     */   }
/*     */   
/*     */   public void setSsl(boolean ssl) {
/* 107 */     this.ssl = ssl;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\server\netty\NettyConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */