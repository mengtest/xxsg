/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.processors.mail.MailExtractProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.mail.MailListProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.mail.MailReadProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailExtractRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mail.MailReadRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MailGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 22 */     if (strings[2].equals("empty")) {
/* 23 */       MailUtil.sendSysMail(playerSession.getPlayer().getPlayerId(), new ArrayList(), "测试标题", "测试内容");
/* 24 */     } else if (strings[2].equals("add")) {
/* 25 */       Reward reward = new Reward();
/* 26 */       reward.type = 2;
/* 27 */       reward.id = Integer.valueOf(strings[3]).intValue();
/* 28 */       reward.num = Integer.valueOf(strings[4]).intValue();
/* 29 */       ArrayList<Reward> list = new ArrayList<>();
/* 30 */       list.add(reward);
/* 31 */       MailUtil.sendSysMail(playerSession.getPlayer().getPlayerId(), list, "测试标题", "测试内容");
/* 32 */     } else if (strings[2].equals("t1")) {
/* 33 */       MailListRequest request = new MailListRequest();
/* 34 */       request.type = Byte.valueOf(strings[3]).byteValue();
/* 35 */       request.start = Integer.valueOf(strings[4]).intValue();
/* 36 */       (new MailListProcessor()).handle(playerSession, (RequestBase)request);
/* 37 */     } else if (strings[2].equals("t2")) {
/* 38 */       MailReadRequest request = new MailReadRequest();
/* 39 */       request.id = Integer.valueOf(strings[3]).intValue();
/* 40 */       (new MailReadProcessor()).handle(playerSession, (RequestBase)request);
/* 41 */     } else if (strings[2].equals("t3")) {
/* 42 */       MailExtractRequest request = new MailExtractRequest();
/* 43 */       request.id = Integer.valueOf(strings[3]).intValue();
/* 44 */       (new MailExtractProcessor()).handle(playerSession, (RequestBase)request);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\MailGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */