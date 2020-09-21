/*    */ package com.linlongyx.sanguo.webgame.processors.sign;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.sign.SignComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sign.LevelPackageInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.sign.LevelPackageInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ public class LevelPackageInfoProcessor
/*    */   extends ProcessorBase<LevelPackageInfoRequest, LevelPackageInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 18 */     this.response = (ResponseBase)new LevelPackageInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, LevelPackageInfoRequest request) {
/* 23 */     SignComponent signComponent = (SignComponent)playerSession.getPlayer().createIfNotExist(SignComponent.class);
/* 24 */     for (Iterator<Integer> iterator = signComponent.getLevelMap().keySet().iterator(); iterator.hasNext(); ) { int level = ((Integer)iterator.next()).intValue();
/* 25 */       int time = ((Integer)signComponent.getLevelMap().get(Integer.valueOf(level))).intValue();
/* 26 */       KeyValue keyValue = new KeyValue();
/* 27 */       keyValue.key = level;
/* 28 */       keyValue.value = ((Integer)signComponent.getTimeMap().getOrDefault(Integer.valueOf(level), Integer.valueOf(0))).intValue();
/* 29 */       keyValue.valueStr = time + "";
/* 30 */       ((LevelPackageInfoResponse)this.response).levelBuyCount.add(keyValue); }
/*    */     
/* 32 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\sign\LevelPackageInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */