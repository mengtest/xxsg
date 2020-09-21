/*    */ package com.linlongyx.core.framework.server.netty;
/*    */ 
/*    */ import com.linlongyx.core.framework.server.Server;
/*    */ import io.netty.bootstrap.ServerBootstrap;
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.channel.ChannelHandler;
/*    */ import io.netty.channel.ChannelInitializer;
/*    */ import io.netty.channel.ChannelOption;
/*    */ import io.netty.channel.socket.nio.NioServerSocketChannel;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NettyTCPServer
/*    */   extends AbstractNettyServer
/*    */ {
/* 20 */   private static final Logger LOG = LoggerFactory.getLogger(NettyTCPServer.class);
/*    */   
/*    */   private ServerBootstrap serverBootstrap;
/*    */ 
/*    */   
/*    */   public NettyTCPServer(NettyConfig nettyConfig, ChannelInitializer<? extends Channel> channelInitializer) {
/* 26 */     super(nettyConfig, channelInitializer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void startServer() throws Exception {
/*    */     try {
/* 32 */       this.serverBootstrap = new ServerBootstrap();
/* 33 */       Map<ChannelOption<?>, Object> channelOptions = this.nettyConfig.getChannelOptions();
/* 34 */       if (null != channelOptions) {
/* 35 */         Set<ChannelOption<?>> keySet = channelOptions.keySet();
/* 36 */         for (ChannelOption<?> option : keySet) {
/* 37 */           this.serverBootstrap.option(option, channelOptions.get(option));
/*    */         }
/*    */       } 
/* 40 */       ((ServerBootstrap)this.serverBootstrap.group(getBossGroup(), getWorkerGroup())
/* 41 */         .channel(NioServerSocketChannel.class))
/* 42 */         .childHandler((ChannelHandler)getChannelInitializer());
/*    */       
/* 44 */       Channel serverChannel = this.serverBootstrap.bind(this.nettyConfig.getSocketAddress()).sync().channel();
/* 45 */       ALL_CHANNELS.add(serverChannel);
/* 46 */     } catch (Exception e) {
/* 47 */       LOG.error("TCP Server start error {}, going to shut down", e);
/* 48 */       stopServer();
/* 49 */       throw e;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Server.TransmissionProtocol getTransmissionProtocol() {
/* 55 */     return (Server.TransmissionProtocol)Server.TRANSMISSION_PROTOCOL.TCP;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setChannelInitializer(ChannelInitializer<? extends Channel> initializer) {
/* 60 */     this.channelInitializer = initializer;
/* 61 */     this.serverBootstrap.childHandler((ChannelHandler)initializer);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     return "NettyTCPServer [socketAddress=" + this.nettyConfig.getSocketAddress() + ", portNumber=" + this.nettyConfig
/* 67 */       .getPortNumber() + "]";
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\server\netty\NettyTCPServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */