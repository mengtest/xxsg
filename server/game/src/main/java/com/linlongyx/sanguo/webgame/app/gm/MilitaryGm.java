/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.offices.MilitaryOfficeComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MilitaryBean;
/*    */ import com.linlongyx.sanguo.webgame.processors.offices.HelpOthersProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.offices.ResearchEndProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.HelpInfoListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.HelpOthersRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.MilitaryAskForHelpRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.MilitaryOfficeInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.ResearchEndRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.StartResearchRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.UseSpeedUpItemRequest;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class MilitaryGm implements IGm {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 21 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/* 22 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().getComponent(PartnerComponent.class);
/* 23 */     if (playerComponent == null)
/* 24 */       return;  if (strings[2].equals("askhelp")) {
/*    */       
/* 26 */       MilitaryAskForHelpRequest request = new MilitaryAskForHelpRequest();
/* 27 */       int id = Integer.parseInt(strings[3]);
/* 28 */       request.officeId = id;
/* 29 */       (new MilitaryAskForHelpProcessor()).handle(playerSession, (RequestBase)request);
/* 30 */     } else if (strings[2].equals("end")) {
/* 31 */       ResearchEndRequest request = new ResearchEndRequest();
/* 32 */       int id = Integer.parseInt(strings[3]);
/* 33 */       request.officeId = id;
/* 34 */       (new ResearchEndProcessor()).handle(playerSession, (RequestBase)request);
/* 35 */     } else if (strings[2].equals("start")) {
/* 36 */       StartResearchRequest request = new StartResearchRequest();
/* 37 */       int id = Integer.parseInt(strings[3]);
/* 38 */       request.officeId = id;
/* 39 */       (new StartResearchProcessor()).handle(playerSession, (RequestBase)request);
/* 40 */     } else if (strings[2].equals("use")) {
/* 41 */       UseSpeedUpItemRequest request = new UseSpeedUpItemRequest();
/* 42 */       int id = Integer.parseInt(strings[3]);
/* 43 */       int num = Integer.parseInt(strings[4]);
/* 44 */       request.itemId = id;
/* 45 */       request.itemNum = num;
/* 46 */       (new UseSpeedUpItemProcessor()).handle(playerSession, (RequestBase)request);
/* 47 */     } else if (strings[2].equals("helpOther")) {
/* 48 */       HelpOthersRequest request = new HelpOthersRequest();
/* 49 */       long playerId = Long.parseLong(strings[3]);
/* 50 */       request.list.add(Long.valueOf(playerId));
/* 51 */       (new HelpOthersProcessor()).handle(playerSession, (RequestBase)request);
/* 52 */     } else if (strings[2].equals("info")) {
/* 53 */       HelpInfoListRequest request = new HelpInfoListRequest();
/* 54 */       (new HelpInfoListProcessor()).handle(playerSession, (RequestBase)request);
/* 55 */     } else if (strings[2].equals("miInfo")) {
/* 56 */       MilitaryOfficeInfoRequest request = new MilitaryOfficeInfoRequest();
/* 57 */       (new MilitaryOfficeInfoProcessor()).handle(playerSession, (RequestBase)request);
/* 58 */     } else if (strings[2].equals("add")) {
/* 59 */       int id = Integer.parseInt(strings[3]);
/* 60 */       MilitaryOfficeComponent militaryOfficeComponent = (MilitaryOfficeComponent)playerSession.getPlayer().createIfNotExist(MilitaryOfficeComponent.class);
/* 61 */       if (!militaryOfficeComponent.getOfficeList().containsKey(Integer.valueOf(id))) {
/* 62 */         MilitaryBean militaryBean = (MilitaryBean)JsonTableService.getJsonData(id, MilitaryBean.class);
/* 63 */         if (null == militaryBean) {
/*    */           return;
/*    */         }
/* 66 */         militaryOfficeComponent.getOfficeList().put(Integer.valueOf(id), Integer.valueOf(1));
/* 67 */         militaryOfficeComponent.setOfficeList(militaryOfficeComponent.getOfficeList());
/* 68 */         int type = ((MilitaryBean.LevelBean)militaryBean.getLevel().get(Integer.valueOf(1))).getTypeS();
/* 69 */         if (militaryOfficeComponent.getTypeMap().containsKey(Integer.valueOf(type)) && !((Set)militaryOfficeComponent.getTypeMap().get(Integer.valueOf(type))).contains(Integer.valueOf(id))) {
/* 70 */           ((Set<Integer>)militaryOfficeComponent.getTypeMap().get(Integer.valueOf(type))).add(Integer.valueOf(id));
/* 71 */         } else if (!militaryOfficeComponent.getTypeMap().containsKey(Integer.valueOf(type))) {
/* 72 */           Set<Integer> set = new HashSet<>();
/* 73 */           set.add(Integer.valueOf(id));
/* 74 */           militaryOfficeComponent.getTypeMap().put(Integer.valueOf(type), set);
/* 75 */           militaryOfficeComponent.setTypeMap(militaryOfficeComponent.getTypeMap());
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\MilitaryGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */