/*    */ package com.linlongyx.sanguo.webgame.processors.offices;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.offices.MilitaryOfficeComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.MilitaryOfficeInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.MilitaryOfficeInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MilitaryOfficeInfoProcessor
/*    */   extends ProcessorBase<MilitaryOfficeInfoRequest, MilitaryOfficeInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new MilitaryOfficeInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MilitaryOfficeInfoRequest request) {
/* 27 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 80)) {
/* 28 */       return 10061;
/*    */     }
/* 30 */     MilitaryOfficeComponent militaryOfficeComponent = (MilitaryOfficeComponent)playerSession.getPlayer().createIfNotExist(MilitaryOfficeComponent.class);
/* 31 */     ((MilitaryOfficeInfoResponse)this.response).currJobId = militaryOfficeComponent.getCurrJobId();
/* 32 */     ((MilitaryOfficeInfoResponse)this.response).endTime = militaryOfficeComponent.getEndTime();
/* 33 */     ((MilitaryOfficeInfoResponse)this.response).officeHelpTimes = militaryOfficeComponent.getOfficeHelpTimes();
/* 34 */     ((MilitaryOfficeInfoResponse)this.response).status = militaryOfficeComponent.getStatus();
/* 35 */     if (request.type == 0) {
/* 36 */       for (Iterator<Integer> iterator = militaryOfficeComponent.getOfficeList().keySet().iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/* 37 */         KeyValue keyValue = new KeyValue();
/* 38 */         keyValue.key = key;
/* 39 */         keyValue.value = ((Integer)militaryOfficeComponent.getOfficeList().get(Integer.valueOf(key))).intValue();
/* 40 */         ((MilitaryOfficeInfoResponse)this.response).list.add(keyValue); }
/*    */     
/*    */     } else {
/* 43 */       Set<Integer> set = (Set<Integer>)militaryOfficeComponent.getTypeMap().get(Integer.valueOf(request.type));
/* 44 */       if (null != set) {
/* 45 */         for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/* 46 */           KeyValue keyValue = new KeyValue();
/* 47 */           keyValue.key = key;
/* 48 */           keyValue.value = ((Integer)militaryOfficeComponent.getOfficeList().get(Integer.valueOf(key))).intValue();
/* 49 */           ((MilitaryOfficeInfoResponse)this.response).list.add(keyValue); }
/*    */       
/*    */       }
/*    */     } 
/* 53 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\offices\MilitaryOfficeInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */