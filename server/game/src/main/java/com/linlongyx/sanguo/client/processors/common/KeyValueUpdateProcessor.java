/*    */ package com.linlongyx.sanguo.client.processors.common;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.client.actor.Actor;
/*    */ import com.linlongyx.sanguo.client.processors.ProcessorBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.KeyValueUpdateRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.KeyValueUpdateResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KeyValueUpdateProcessor
/*    */   extends ProcessorBase<KeyValueUpdateRequest, KeyValueUpdateResponse>
/*    */ {
/*    */   protected void process(Actor actor, KeyValueUpdateResponse response) {
/* 17 */     for (KeyValue keyValue : response.keyValueList);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\processors\common\KeyValueUpdateProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */