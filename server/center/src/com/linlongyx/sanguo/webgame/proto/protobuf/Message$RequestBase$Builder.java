/*     */ package com.linlongyx.sanguo.webgame.proto.protobuf;
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
/*     */ public final class Builder
/*     */   extends GeneratedMessage.ExtendableBuilder<Message.RequestBase, Message.RequestBase.Builder>
/*     */   implements Message.RequestBaseOrBuilder
/*     */ {
/*     */   private int bitField0_;
/*     */   private int cmdId_;
/*     */   
/*     */   public static final Descriptors.Descriptor getDescriptor() {
/* 302 */     return Message.access$000();
/*     */   }
/*     */ 
/*     */   
/*     */   protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/* 307 */     return Message.access$100()
/* 308 */       .ensureFieldAccessorsInitialized(Message.RequestBase.class, Builder.class);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Builder() {
/* 314 */     maybeForceBuilderInitialization();
/*     */   }
/*     */ 
/*     */   
/*     */   private Builder(GeneratedMessage.BuilderParent parent) {
/* 319 */     super(parent);
/* 320 */     maybeForceBuilderInitialization();
/*     */   }
/*     */   
/*     */   private void maybeForceBuilderInitialization() {
/* 324 */     if (Message.RequestBase.access$500());
/*     */   }
/*     */ 
/*     */   
/*     */   private static Builder create() {
/* 329 */     return new Builder();
/*     */   }
/*     */   
/*     */   public Builder clear() {
/* 333 */     super.clear();
/* 334 */     this.cmdId_ = 0;
/* 335 */     this.bitField0_ &= 0xFFFFFFFE;
/* 336 */     return this;
/*     */   }
/*     */   
/*     */   public Builder clone() {
/* 340 */     return create().mergeFrom(buildPartial());
/*     */   }
/*     */ 
/*     */   
/*     */   public Descriptors.Descriptor getDescriptorForType() {
/* 345 */     return Message.access$000();
/*     */   }
/*     */   
/*     */   public Message.RequestBase getDefaultInstanceForType() {
/* 349 */     return Message.RequestBase.getDefaultInstance();
/*     */   }
/*     */   
/*     */   public Message.RequestBase build() {
/* 353 */     Message.RequestBase result = buildPartial();
/* 354 */     if (!result.isInitialized()) {
/* 355 */       throw newUninitializedMessageException(result);
/*     */     }
/* 357 */     return result;
/*     */   }
/*     */   
/*     */   public Message.RequestBase buildPartial() {
/* 361 */     Message.RequestBase result = new Message.RequestBase(this, null);
/* 362 */     int from_bitField0_ = this.bitField0_;
/* 363 */     int to_bitField0_ = 0;
/* 364 */     if ((from_bitField0_ & 0x1) == 1) {
/* 365 */       to_bitField0_ |= 0x1;
/*     */     }
/* 367 */     Message.RequestBase.access$702(result, this.cmdId_);
/* 368 */     Message.RequestBase.access$802(result, to_bitField0_);
/* 369 */     onBuilt();
/* 370 */     return result;
/*     */   }
/*     */   
/*     */   public Builder mergeFrom(Message other) {
/* 374 */     if (other instanceof Message.RequestBase) {
/* 375 */       return mergeFrom((Message.RequestBase)other);
/*     */     }
/* 377 */     super.mergeFrom(other);
/* 378 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Builder mergeFrom(Message.RequestBase other) {
/* 383 */     if (other == Message.RequestBase.getDefaultInstance()) return this; 
/* 384 */     if (other.hasCmdId()) {
/* 385 */       setCmdId(other.getCmdId());
/*     */     }
/* 387 */     mergeExtensionFields(other);
/* 388 */     mergeUnknownFields(other.getUnknownFields());
/* 389 */     return this;
/*     */   }
/*     */   
/*     */   public final boolean isInitialized() {
/* 393 */     if (!hasCmdId())
/*     */     {
/* 395 */       return false;
/*     */     }
/* 397 */     if (!extensionsAreInitialized())
/*     */     {
/* 399 */       return false;
/*     */     }
/* 401 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 408 */     Message.RequestBase parsedMessage = null;
/*     */     try {
/* 410 */       parsedMessage = (Message.RequestBase)Message.RequestBase.PARSER.parsePartialFrom(input, extensionRegistry);
/* 411 */     } catch (InvalidProtocolBufferException e) {
/* 412 */       parsedMessage = (Message.RequestBase)e.getUnfinishedMessage();
/* 413 */       throw e;
/*     */     } finally {
/* 415 */       if (parsedMessage != null) {
/* 416 */         mergeFrom(parsedMessage);
/*     */       }
/*     */     } 
/* 419 */     return this;
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
/* 431 */     return ((this.bitField0_ & 0x1) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCmdId() {
/* 438 */     return this.cmdId_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder setCmdId(int value) {
/* 445 */     this.bitField0_ |= 0x1;
/* 446 */     this.cmdId_ = value;
/* 447 */     onChanged();
/* 448 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Builder clearCmdId() {
/* 455 */     this.bitField0_ &= 0xFFFFFFFE;
/* 456 */     this.cmdId_ = 0;
/* 457 */     onChanged();
/* 458 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\protobuf\Message$RequestBase$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */