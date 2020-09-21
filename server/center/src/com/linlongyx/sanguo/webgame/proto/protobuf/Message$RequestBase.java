/*     */ package com.linlongyx.sanguo.webgame.proto.protobuf;
/*     */ 
/*     */ import com.google.protobuf.AbstractMessage;
/*     */ import com.google.protobuf.AbstractMessageLite;
/*     */ import com.google.protobuf.AbstractParser;
/*     */ import com.google.protobuf.ByteString;
/*     */ import com.google.protobuf.CodedInputStream;
/*     */ import com.google.protobuf.CodedOutputStream;
/*     */ import com.google.protobuf.Descriptors;
/*     */ import com.google.protobuf.ExtensionRegistryLite;
/*     */ import com.google.protobuf.GeneratedMessage;
/*     */ import com.google.protobuf.InvalidProtocolBufferException;
/*     */ import com.google.protobuf.Message;
/*     */ import com.google.protobuf.MessageLite;
/*     */ import com.google.protobuf.Parser;
/*     */ import com.google.protobuf.UnknownFieldSet;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectStreamException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class RequestBase
/*     */   extends GeneratedMessage.ExtendableMessage<Message.RequestBase>
/*     */   implements Message.RequestBaseOrBuilder
/*     */ {
/*     */   private static final RequestBase defaultInstance;
/*     */   private final UnknownFieldSet unknownFields;
/*     */   
/*     */   private RequestBase(GeneratedMessage.ExtendableBuilder<RequestBase, ?> builder) {
/*  37 */     super(builder);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     this.memoizedIsInitialized = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     this.memoizedSerializedSize = -1; this.unknownFields = builder.getUnknownFields(); } private RequestBase(boolean noInit) { this.memoizedIsInitialized = -1; this.memoizedSerializedSize = -1; this.unknownFields = UnknownFieldSet.getDefaultInstance(); } public static RequestBase getDefaultInstance() { return defaultInstance; } public RequestBase getDefaultInstanceForType() { return defaultInstance; } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } public static final Descriptors.Descriptor getDescriptor() { return Message.access$000(); } protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() { return Message.access$100().ensureFieldAccessorsInitialized(RequestBase.class, Builder.class); } public static Parser<RequestBase> PARSER = (Parser<RequestBase>)new AbstractParser<RequestBase>() { public Message.RequestBase parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { return new Message.RequestBase(input, extensionRegistry); } }; private int bitField0_; private RequestBase(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this.memoizedIsInitialized = -1; this.memoizedSerializedSize = -1; initFields(); int mutable_bitField0_ = 0; UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { int tag = input.readTag(); switch (tag) { case 0:
/*     */             done = true; break;
/*     */           case 8:
/* 187 */             this.bitField0_ |= 0x1; this.cmdId_ = input.readInt32(); break; }  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e.getMessage())).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public static final int CMDID_FIELD_NUMBER = 1; private int cmdId_; private byte memoizedIsInitialized; public int getSerializedSize() { int size = this.memoizedSerializedSize;
/* 188 */     if (size != -1) return size;
/*     */     
/* 190 */     size = 0;
/* 191 */     if ((this.bitField0_ & 0x1) == 1) {
/* 192 */       size += 
/* 193 */         CodedOutputStream.computeInt32Size(1, this.cmdId_);
/*     */     }
/* 195 */     size += extensionsSerializedSize();
/* 196 */     size += getUnknownFields().getSerializedSize();
/* 197 */     this.memoizedSerializedSize = size;
/* 198 */     return size; } private int memoizedSerializedSize; private static final long serialVersionUID = 0L; public Parser<RequestBase> getParserForType() { return PARSER; }
/*     */   public boolean hasCmdId() { return ((this.bitField0_ & 0x1) == 1); }
/*     */   public int getCmdId() { return this.cmdId_; }
/*     */   private void initFields() { this.cmdId_ = 0; }
/*     */   public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized; if (isInitialized != -1)
/*     */       return (isInitialized == 1);  if (!hasCmdId()) { this.memoizedIsInitialized = 0; return false; }  if (!extensionsAreInitialized()) { this.memoizedIsInitialized = 0; return false; }  this.memoizedIsInitialized = 1; return true; }
/*     */   public void writeTo(CodedOutputStream output) throws IOException { getSerializedSize(); GeneratedMessage.ExtendableMessage<RequestBase>.ExtensionWriter extensionWriter = newExtensionWriter(); if ((this.bitField0_ & 0x1) == 1)
/*     */       output.writeInt32(1, this.cmdId_);  extensionWriter.writeUntil(536870912, output); getUnknownFields().writeTo(output); }
/* 206 */   protected Object writeReplace() throws ObjectStreamException { return super.writeReplace(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 212 */     return (RequestBase)PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 219 */     return (RequestBase)PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 224 */     return (RequestBase)PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 231 */     return (RequestBase)PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(InputStream input) throws IOException {
/* 236 */     return (RequestBase)PARSER.parseFrom(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 243 */     return (RequestBase)PARSER.parseFrom(input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RequestBase parseDelimitedFrom(InputStream input) throws IOException {
/* 248 */     return (RequestBase)PARSER.parseDelimitedFrom(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 255 */     return (RequestBase)PARSER.parseDelimitedFrom(input, extensionRegistry);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(CodedInputStream input) throws IOException {
/* 261 */     return (RequestBase)PARSER.parseFrom(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 268 */     return (RequestBase)PARSER.parseFrom(input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Builder newBuilder() {
/* 272 */     return Builder.create();
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 276 */     return newBuilder();
/*     */   }
/*     */   
/*     */   public static Builder newBuilder(RequestBase prototype) {
/* 280 */     return newBuilder().mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 284 */     return newBuilder(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/* 290 */     Builder builder = new Builder(parent);
/* 291 */     return builder;
/*     */   }
/*     */   
/*     */   public static final class Builder
/*     */     extends GeneratedMessage.ExtendableBuilder<RequestBase, Builder>
/*     */     implements Message.RequestBaseOrBuilder
/*     */   {
/*     */     private int bitField0_;
/*     */     private int cmdId_;
/*     */     
/*     */     public static final Descriptors.Descriptor getDescriptor() {
/* 302 */       return Message.access$000();
/*     */     }
/*     */ 
/*     */     
/*     */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/* 307 */       return Message.access$100()
/* 308 */         .ensureFieldAccessorsInitialized(Message.RequestBase.class, Builder.class);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Builder() {
/* 314 */       maybeForceBuilderInitialization();
/*     */     }
/*     */ 
/*     */     
/*     */     private Builder(GeneratedMessage.BuilderParent parent) {
/* 319 */       super(parent);
/* 320 */       maybeForceBuilderInitialization();
/*     */     }
/*     */     
/*     */     private void maybeForceBuilderInitialization() {
/* 324 */       if (Message.RequestBase.alwaysUseFieldBuilders);
/*     */     }
/*     */ 
/*     */     
/*     */     private static Builder create() {
/* 329 */       return new Builder();
/*     */     }
/*     */     
/*     */     public Builder clear() {
/* 333 */       super.clear();
/* 334 */       this.cmdId_ = 0;
/* 335 */       this.bitField0_ &= 0xFFFFFFFE;
/* 336 */       return this;
/*     */     }
/*     */     
/*     */     public Builder clone() {
/* 340 */       return create().mergeFrom(buildPartial());
/*     */     }
/*     */ 
/*     */     
/*     */     public Descriptors.Descriptor getDescriptorForType() {
/* 345 */       return Message.access$000();
/*     */     }
/*     */     
/*     */     public Message.RequestBase getDefaultInstanceForType() {
/* 349 */       return Message.RequestBase.getDefaultInstance();
/*     */     }
/*     */     
/*     */     public Message.RequestBase build() {
/* 353 */       Message.RequestBase result = buildPartial();
/* 354 */       if (!result.isInitialized()) {
/* 355 */         throw newUninitializedMessageException(result);
/*     */       }
/* 357 */       return result;
/*     */     }
/*     */     
/*     */     public Message.RequestBase buildPartial() {
/* 361 */       Message.RequestBase result = new Message.RequestBase(this);
/* 362 */       int from_bitField0_ = this.bitField0_;
/* 363 */       int to_bitField0_ = 0;
/* 364 */       if ((from_bitField0_ & 0x1) == 1) {
/* 365 */         to_bitField0_ |= 0x1;
/*     */       }
/* 367 */       result.cmdId_ = this.cmdId_;
/* 368 */       result.bitField0_ = to_bitField0_;
/* 369 */       onBuilt();
/* 370 */       return result;
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(Message other) {
/* 374 */       if (other instanceof Message.RequestBase) {
/* 375 */         return mergeFrom((Message.RequestBase)other);
/*     */       }
/* 377 */       super.mergeFrom(other);
/* 378 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(Message.RequestBase other) {
/* 383 */       if (other == Message.RequestBase.getDefaultInstance()) return this; 
/* 384 */       if (other.hasCmdId()) {
/* 385 */         setCmdId(other.getCmdId());
/*     */       }
/* 387 */       mergeExtensionFields(other);
/* 388 */       mergeUnknownFields(other.getUnknownFields());
/* 389 */       return this;
/*     */     }
/*     */     
/*     */     public final boolean isInitialized() {
/* 393 */       if (!hasCmdId())
/*     */       {
/* 395 */         return false;
/*     */       }
/* 397 */       if (!extensionsAreInitialized())
/*     */       {
/* 399 */         return false;
/*     */       }
/* 401 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 408 */       Message.RequestBase parsedMessage = null;
/*     */       try {
/* 410 */         parsedMessage = (Message.RequestBase)Message.RequestBase.PARSER.parsePartialFrom(input, extensionRegistry);
/* 411 */       } catch (InvalidProtocolBufferException e) {
/* 412 */         parsedMessage = (Message.RequestBase)e.getUnfinishedMessage();
/* 413 */         throw e;
/*     */       } finally {
/* 415 */         if (parsedMessage != null) {
/* 416 */           mergeFrom(parsedMessage);
/*     */         }
/*     */       } 
/* 419 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasCmdId() {
/* 431 */       return ((this.bitField0_ & 0x1) == 1);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getCmdId() {
/* 438 */       return this.cmdId_;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setCmdId(int value) {
/* 445 */       this.bitField0_ |= 0x1;
/* 446 */       this.cmdId_ = value;
/* 447 */       onChanged();
/* 448 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder clearCmdId() {
/* 455 */       this.bitField0_ &= 0xFFFFFFFE;
/* 456 */       this.cmdId_ = 0;
/* 457 */       onChanged();
/* 458 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 465 */     defaultInstance = new RequestBase(true);
/* 466 */     defaultInstance.initFields();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\protobuf\Message$RequestBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */