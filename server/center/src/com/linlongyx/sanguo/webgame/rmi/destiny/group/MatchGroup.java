/*    */ package com.linlongyx.sanguo.webgame.rmi.destiny.group;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*    */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.PkData;
/*    */ 
/*    */ @JsonIgnoreProperties(ignoreUnknown = true)
/*    */ public class MatchGroup
/*    */   extends AbstractGroup
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private PkData finalPkData;
/*    */   
/*    */   public PkData getFinalPkData() {
/* 14 */     return this.finalPkData;
/*    */   }
/*    */   
/*    */   public void setFinalPkData(PkData finalPkData) {
/* 18 */     this.finalPkData = finalPkData;
/*    */   }
/*    */ 
/*    */   
/*    */   public PkData[] getAllPkData() {
/* 23 */     return new PkData[0];
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\group\MatchGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */