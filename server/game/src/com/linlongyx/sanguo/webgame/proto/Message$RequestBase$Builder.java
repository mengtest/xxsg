/*     */ package com.linlongyx.sanguo.webgame.proto;
/*     */ 
/*     */ import com.google.protobuf.AbstractMessage;
/*     */ import com.google.protobuf.AbstractMessageLite;
/*     */ import com.google.protobuf.CodedInputStream;
/*     */ import com.google.protobuf.Descriptors;
/*     */ import com.google.protobuf.ExtensionRegistryLite;
/*     */ import com.google.protobuf.GeneratedMessage;
/*     */ import com.google.protobuf.InvalidProtocolBufferException;
/*     */ import com.google.protobuf.Message;
/*     */ import com.google.protobuf.MessageLite;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Builder
/*     */   extends GeneratedMessage.ExtendableBuilder<Message.RequestBase, Message.RequestBase.Builder>
/*     */   implements Message.RequestBaseOrBuilder
/*     */ {
/*     */   private int bitField0_;
/*     */   private int cmdId_;
/*     */   
/*     */   public static final Descriptors.Descriptor getDescriptor() {
/* 305 */     return Message.access$000();
/*     */   }
/*     */ 
/*     */   
/*     */   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/* 310 */     return Message.access$100()
/* 311 */       .ensureFieldAccessorsInitialized(Message.RequestBase.class, Builder.class);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Builder() {
/* 317 */     maybeForceBuilderInitialization();
/*     */   }
/*     */ 
/*     */   
/*     */   private Builder(GeneratedMessage.BuilderParent parent) {
/* 322 */     super(parent);
/* 323 */     maybeForceBuilderInitialization();
/*     */   }
/*     */   
/*     */   private void maybeForceBuilderInitialization() {
/* 327 */     if (Message.RequestBase.access$500());
/*     */   }
/*     */ 
/*     */   
/*     */   private static Builder create() {
/* 332 */     return new Builder();
/*     */   }
/*     */   
/*     */   public Builder clear() {
/* 336 */     super.clear();
/* 337 */     this.cmdId_ = 0;
/* 338 */     this.bitField0_ &= 0xFFFFFFFE;
/* 339 */     return this;
/*     */   }
/*     */   
/*     */   public Builder clone() {
/* 343 */     return create().mergeFrom(buildPartial());
/*     */   }
/*     */ 
/*     */   
/*     */   public Descriptors.Descriptor getDescriptorForType() {
/* 348 */     return Message.access$000();
/*     */   }
/*     */   
/*     */   public Message.RequestBase getDefaultInstanceForType() {
/* 352 */     return Message.RequestBase.getDefaultInstance();
/*     */   }
/*     */   
/*     */   public Message.RequestBase build() {
/* 356 */     Message.RequestBase result = buildPartial();
/* 357 */     if (!result.isInitialized()) {
/* 358 */       throw newUninitializedMessageException(result);
/*     */     }
/* 360 */     return result;
/*     */   }
/*     */   
/*     */   public Message.RequestBase buildPartial() {
/* 364 */     Message.RequestBase result = new Message.RequestBase(this, null);
/* 365 */     int from_bitField0_ = this.bitField0_;
/* 366 */     int to_bitField0_ = 0;
/* 367 */     if ((from_bitField0_ & 0x1) == 1) {
/* 368 */       to_bitField0_ |= 0x1;
/*     */     }
/* 370 */     Message.RequestBase.access$702(result, this.cmdId_);
/* 371 */     Message.RequestBase.access$802(result, to_bitField0_);
/* 372 */     onBuilt();
/* 373 */     return result;
/*     */   }
/*     */   
/*     */   public Builder mergeFrom(Message other) {
/* 377 */     if (other instanceof Message.RequestBase) {
/* 378 */       return mergeFrom((Message.RequestBase)other);
/*     */     }
/* 380 */     super.mergeFrom(other);
/* 381 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Builder mergeFrom(Message.RequestBase other) {
/* 386 */     if (other == Message.RequestBase.getDefaultInstance()) return this; 
/* 387 */     if (other.hasCmdId()) {
/* 388 */       setCmdId(other.getCmdId());
/*     */     }
/* 390 */     mergeExtensionFields(other);
/* 391 */     mergeUnknownFields(other.getUnknownFields());
/* 392 */     return this;
/*     */   }
/*     */   
/*     */   public final boolean isInitialized() {
/* 396 */     if (!hasCmdId())
/*     */     {
/* 398 */       return false;
/*     */     }
/* 400 */     if (!extensionsAreInitialized())
/*     */     {
/* 402 */       return false;
/*     */     }
/* 404 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 411 */     Message.RequestBase parsedMessage = null;
/*     */     try {
/* 413 */       parsedMessage = (Message.RequestBase)Message.RequestBase.PARSER.parsePartialFrom(input, extensionRegistry);
/* 414 */     } catch (InvalidProtocolBufferException e) {
/* 415 */       parsedMessage = (Message.RequestBase)e.getUnfinishedMessage();
/* 416 */       throw e;
/*     */     } finally {
/* 418 */       if (parsedMessage != null) {
/* 419 */         mergeFrom(parsedMessage);
/*     */       }
/*     */     } 
/* 422 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCmdId() {
/* 434 */     return ((this.bitField0_ & 0x1) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCmdId() {
/* 441 */     return this.cmdId_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder setCmdId(int value) {
/* 448 */     this.bitField0_ |= 0x1;
/* 449 */     this.cmdId_ = value;
/* 450 */     onChanged();
/* 451 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder clearCmdId() {
/* 458 */     this.bitField0_ &= 0xFFFFFFFE;
/* 459 */     this.cmdId_ = 0;
/* 460 */     onChanged();
/* 461 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\Message$RequestBase$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */