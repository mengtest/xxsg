/*     */ package com.linlongyx.sanguo.client.actor;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.WalkSceneRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.WalkSyncRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SceneAvatar;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ public class Actor {
/*  22 */   protected final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
/*     */   protected ScheduledFuture<?> aoiSchedule;
/*     */   public static final short MAX_WIDTH = 10;
/*     */   public static final short MAX_HEIGHT = 20;
/*     */   public long playerId;
/*     */   public long userId;
/*     */   public short posx;
/*     */   public short posy;
/*     */   public short flexPosx;
/*     */   public short flexPosy;
/*     */   public long loginTime;
/*     */   public String userName;
/*  34 */   public HashMap<Long, SceneAvatar> othersMap = new HashMap<>();
/*  35 */   public Set<Long> otherSet = new HashSet<>(); public int taskId;
/*     */   public int taskSchedule;
/*     */   private NettyTCPMessageSender tcpSender;
/*     */   
/*     */   public void setTcpSender(NettyTCPMessageSender tcpSender) {
/*  40 */     this.loginTime = TimeUtil.currentTimeMillis();
/*  41 */     this.tcpSender = tcpSender;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMessage(RequestBase request) {
/*  47 */     ByteBuf byteBuf = Unpooled.buffer();
/*  48 */     ByteBuf out = Unpooled.buffer();
/*  49 */     request.serial(out);
/*  50 */     byteBuf.writeShort(request.eventRequestId);
/*  51 */     byteBuf.writeByte(request.codeType);
/*  52 */     byteBuf.writeShort(out.readableBytes());
/*  53 */     byteBuf.writeBytes(out);
/*  54 */     this.tcpSender.sendMessage(byteBuf);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/*  79 */     this.aoiSchedule = this.scheduler.scheduleAtFixedRate(this::randAction, 0L, 200L, TimeUnit.MILLISECONDS);
/*     */   }
/*     */ 
/*     */   
/*     */   private void randAction() {
/*  84 */     int cmd = RandUtil.randNum(9);
/*  85 */     if (cmd < 3) {
/*  86 */       robotMsg();
/*  87 */     } else if (cmd < 10) {
/*  88 */       attack();
/*     */     } else {
/*  90 */       testDB();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addOthers(long playerId, SceneAvatar avatar) {
/*  96 */     this.othersMap.put(Long.valueOf(playerId), avatar);
/*  97 */     this.otherSet.add(Long.valueOf(playerId));
/*     */   }
/*     */   
/*     */   public void removeOthers(long playerId) {
/* 101 */     this.othersMap.remove(Long.valueOf(playerId));
/* 102 */     this.otherSet.remove(Long.valueOf(playerId));
/*     */   }
/*     */   
/*     */   private void robotMsg() {
/* 106 */     short newposx = this.posx;
/* 107 */     short newposy = this.posy;
/* 108 */     if (this.flexPosx > newposx) {
/* 109 */       newposx = (short)(newposx + 1);
/* 110 */     } else if (this.flexPosx < newposx) {
/* 111 */       newposx = (short)(newposx - 1);
/*     */     } 
/* 113 */     if (this.flexPosy > newposy) {
/* 114 */       newposy = (short)(newposy + 1);
/* 115 */     } else if (this.flexPosy < newposy) {
/* 116 */       newposy = (short)(newposy - 1);
/*     */     } 
/* 118 */     if (this.flexPosx == this.posx && this.posy == this.flexPosy) {
/* 119 */       walkScene((short)random.nextInt(31), (short)random.nextInt(38));
/*     */     } else {
/* 121 */       walkSync(newposx, newposy, (byte)random.nextInt(7));
/*     */     } 
/*     */   }
/*     */   
/* 125 */   public static Random random = new Random();
/*     */   
/*     */   public Long random(List<Long> list) {
/* 128 */     return list.get(random.nextInt(list.size()));
/*     */   }
/*     */   
/*     */   public void attack() {
/* 132 */     if (random.nextInt(5) > 2) {
/*     */       
/* 134 */       if (this.otherSet.size() <= 0) {
/*     */         return;
/*     */       }
/* 137 */       int skillId = 100100;
/* 138 */       List<Long> list = new ArrayList<>();
/* 139 */       list.addAll(this.otherSet);
/*     */       
/* 141 */       long l = random(list).longValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void walkScene(short posx, short posy) {
/* 153 */     WalkSceneRequest walkSceneRequest = new WalkSceneRequest();
/* 154 */     walkSceneRequest.flexPosx = posx;
/* 155 */     walkSceneRequest.flexPosy = posy;
/* 156 */     this.flexPosx = posx;
/* 157 */     this.flexPosy = posy;
/* 158 */     sendMessage((RequestBase)walkSceneRequest);
/*     */   }
/*     */ 
/*     */   
/*     */   public void walkSync(short posx, short posy, byte direct) {
/* 163 */     WalkSyncRequest walkSyncRequest = new WalkSyncRequest();
/* 164 */     walkSyncRequest.direction = direct;
/* 165 */     this.posx = walkSyncRequest.posx = posx;
/* 166 */     this.posy = walkSyncRequest.posy = posy;
/* 167 */     sendMessage((RequestBase)walkSyncRequest);
/*     */   }
/*     */   
/*     */   public void testDB() {
/* 171 */     int cmd = RandUtil.randNum(10);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\actor\Actor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */