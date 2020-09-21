/*    */ package com.linlongyx.core.framework.server.netty;
/*    */ 
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.channel.ChannelInitializer;
/*    */ import io.netty.channel.EventLoopGroup;
/*    */ import io.netty.channel.group.ChannelGroup;
/*    */ import io.netty.channel.group.ChannelGroupFuture;
/*    */ import io.netty.channel.group.DefaultChannelGroup;
/*    */ import io.netty.util.concurrent.EventExecutor;
/*    */ import io.netty.util.concurrent.GlobalEventExecutor;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public abstract class AbstractNettyServer
/*    */   implements NettyServer
/*    */ {
/* 17 */   private static final Logger LOG = LoggerFactory.getLogger(AbstractNettyServer.class);
/*    */   
/* 19 */   public static final ChannelGroup ALL_CHANNELS = (ChannelGroup)new DefaultChannelGroup("DROGONBALL-CHANNELS", (EventExecutor)GlobalEventExecutor.INSTANCE);
/*    */   
/*    */   protected final NettyConfig nettyConfig;
/*    */   
/*    */   protected ChannelInitializer<? extends Channel> channelInitializer;
/*    */   
/*    */   public AbstractNettyServer(NettyConfig nettyConfig, ChannelInitializer<? extends Channel> channelInitializer) {
/* 26 */     this.nettyConfig = nettyConfig;
/* 27 */     this.channelInitializer = channelInitializer;
/*    */   }
/*    */ 
/*    */   
/*    */   public ChannelInitializer<? extends Channel> getChannelInitializer() {
/* 32 */     return this.channelInitializer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setChannelInitializer(ChannelInitializer<? extends Channel> initializer) {
/* 37 */     this.channelInitializer = initializer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopServer() throws Exception {
/* 42 */     LOG.debug("In stopServer method of class: {}", getClass()
/* 43 */         .getName());
/* 44 */     ChannelGroupFuture future = ALL_CHANNELS.close();
/*    */     try {
/* 46 */       future.await();
/* 47 */     } catch (InterruptedException e) {
/* 48 */       LOG.error("Execption occurred while waiting for channels to close: {}", e);
/*    */     
/*    */     }
/*    */     finally {
/*    */       
/* 53 */       if (null != this.nettyConfig.getBossGroup()) {
/* 54 */         this.nettyConfig.getBossGroup().shutdownGracefully();
/*    */       }
/* 56 */       if (null != this.nettyConfig.getWorkerGroup()) {
/* 57 */         this.nettyConfig.getWorkerGroup().shutdownGracefully();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public NettyConfig getNettyConfig() {
/* 64 */     return this.nettyConfig;
/*    */   }
/*    */   
/*    */   protected EventLoopGroup getBossGroup() {
/* 68 */     return (EventLoopGroup)this.nettyConfig.getBossGroup();
/*    */   }
/*    */   
/*    */   protected EventLoopGroup getWorkerGroup() {
/* 72 */     return (EventLoopGroup)this.nettyConfig.getWorkerGroup();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     return "NettyServer [socketAddress=" + this.nettyConfig.getSocketAddress() + ", portNumber=" + this.nettyConfig
/* 81 */       .getPortNumber() + "]";
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\server\netty\AbstractNettyServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */