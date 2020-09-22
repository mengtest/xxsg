/*    */ package com.linlongyx.sanguo.webgame.rmi.data;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.LinkedList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AxisData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 35 */   private LinkedList<CoordinateData.NodeData> linkedList = new LinkedList<>();
/*    */   
/*    */   public LinkedList<CoordinateData.NodeData> getLinkedList() {
/* 38 */     return this.linkedList;
/*    */   }
/*    */   
/*    */   public void setLinkedList(LinkedList<CoordinateData.NodeData> linkedList) {
/* 42 */     this.linkedList = linkedList;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\CoordinateData$AxisData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */