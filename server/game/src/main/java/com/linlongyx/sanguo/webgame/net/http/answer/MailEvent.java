/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.google.gson.reflect.TypeToken;
/*    */ import com.linlongyx.core.utils.GsonUtil;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.mail.PubMailEntity;
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MailInfo;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MailEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 23 */     if (p.containsKey("mails")) {
/* 24 */       String str = ((List<String>)p.get("mails")).get(0);
/* 25 */       LogUtils.errorLog(new Object[] { "MailEvent", str });
/* 26 */       ArrayList<PubMailEntity> list = (ArrayList<PubMailEntity>)GsonUtil.fromJson(str, (new TypeToken<List<PubMailEntity>>() {  }).getType());
/* 27 */       for (PubMailEntity pubMailEntity : list) {
/* 28 */         MailInfo mailInfo = new MailInfo();
/* 29 */         mailInfo.type = pubMailEntity.getType();
/* 30 */         mailInfo.sendId = pubMailEntity.getSendId();
/* 31 */         mailInfo.sendName = pubMailEntity.getSendName();
/* 32 */         mailInfo.title = pubMailEntity.getTitle();
/* 33 */         mailInfo.context = pubMailEntity.getContext();
/* 34 */         mailInfo.rewards = pubMailEntity.getRewards();
/* 35 */         MailUtil.sendPubMail(mailInfo, pubMailEntity.getRules());
/*    */       } 
/* 37 */       return String.valueOf(10001);
/*    */     } 
/* 39 */     return String.valueOf(10002);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\MailEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */