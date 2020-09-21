/*    */ package com.linlongyx.sanguo.webgame.processors.skill;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.skill.SkillNewRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.skill.SkillNewResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillNewProcessor
/*    */   extends ProcessorBase<SkillNewRequest, SkillNewResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new SkillNewResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SkillNewRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\skill\SkillNewProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */