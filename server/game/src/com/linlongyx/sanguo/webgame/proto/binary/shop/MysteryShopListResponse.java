/*    */ package com.linlongyx.sanguo.webgame.proto.binary.shop;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GoodsData;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class MysteryShopListResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/* 21 */   public ArrayList<GoodsData> goods = new ArrayList<>();
/*    */   public int refreshTime;
/*    */   public int refreshNum;
/*    */   
/*    */   public MysteryShopListResponse() {
/* 26 */     this.eventResponseId = 24201;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public MysteryShopListResponse(short retCode) {
/* 31 */     this.eventResponseId = 24201;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeInt(out, this.type);
/*    */     
/* 40 */     int size_0 = this.goods.size();
/* 41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       GoodsData tmp_0 = this.goods.get(index_0);
/* 45 */       tmp_0.serial(out);
/*    */     } 
/* 47 */     ProtocolUtil.writeInt(out, this.refreshTime);
/* 48 */     ProtocolUtil.writeInt(out, this.refreshNum);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 53 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 55 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 56 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 58 */       GoodsData tmp_0 = new GoodsData();
/* 59 */       tmp_0.unserial(in);
/* 60 */       this.goods.add(tmp_0);
/*    */     } 
/* 62 */     this.refreshTime = ProtocolUtil.readUTFBinInt(in);
/* 63 */     this.refreshNum = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MysteryShopListResponse clone() throws CloneNotSupportedException {
/* 68 */     MysteryShopListResponse clone = (MysteryShopListResponse)super.clone();
/* 69 */     int size_0 = this.goods.size();
/* 70 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 72 */       GoodsData tmp_0 = this.goods.get(index_0);
/* 73 */       clone.goods.add(tmp_0.clone());
/*    */     } 
/* 75 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 80 */     this.type = 0;
/* 81 */     this.goods.clear();
/* 82 */     this.refreshTime = 0;
/* 83 */     this.refreshNum = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     String retVal = "{\"type\":" + this.type + ",\"goods\":" + this.goods.toString() + ",\"refreshTime\":" + this.refreshTime + ",\"refreshNum\":" + this.refreshNum + "}";
/* 89 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\shop\MysteryShopListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */