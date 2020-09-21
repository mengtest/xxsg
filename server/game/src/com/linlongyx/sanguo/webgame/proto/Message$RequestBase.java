/*     */ package com.linlongyx.sanguo.webgame.proto;
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
/*  40 */     super(builder);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     this.memoizedIsInitialized = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     this.memoizedSerializedSize = -1; this.unknownFields = builder.getUnknownFields(); } private RequestBase(boolean noInit) { this.memoizedIsInitialized = -1; this.memoizedSerializedSize = -1; this.unknownFields = UnknownFieldSet.getDefaultInstance(); } public static RequestBase getDefaultInstance() { return defaultInstance; } public RequestBase getDefaultInstanceForType() { return defaultInstance; } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } public static final Descriptors.Descriptor getDescriptor() { return Message.access$000(); } protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() { return Message.access$100().ensureFieldAccessorsInitialized(RequestBase.class, Builder.class); } public static Parser<RequestBase> PARSER = (Parser<RequestBase>)new AbstractParser<RequestBase>() { public Message.RequestBase parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { return new Message.RequestBase(input, extensionRegistry); } }; private int bitField0_; private RequestBase(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this.memoizedIsInitialized = -1; this.memoizedSerializedSize = -1; initFields(); int mutable_bitField0_ = 0; UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { int tag = input.readTag(); switch (tag) { case 0:
/*     */             done = true; break;
/*     */           case 8:
/* 190 */             this.bitField0_ |= 0x1; this.cmdId_ = input.readInt32(); break; }  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e.getMessage())).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public static final int CMDID_FIELD_NUMBER = 1; private int cmdId_; private byte memoizedIsInitialized; public int getSerializedSize() { int size = this.memoizedSerializedSize;
/* 191 */     if (size != -1) return size;
/*     */     
/* 193 */     size = 0;
/* 194 */     if ((this.bitField0_ & 0x1) == 1) {
/* 195 */       size += 
/* 196 */         CodedOutputStream.computeInt32Size(1, this.cmdId_);
/*     */     }
/* 198 */     size += extensionsSerializedSize();
/* 199 */     size += getUnknownFields().getSerializedSize();
/* 200 */     this.memoizedSerializedSize = size;
/* 201 */     return size; } private int memoizedSerializedSize; private static final long serialVersionUID = 0L; public Parser<RequestBase> getParserForType() { return PARSER; }
/*     */   public boolean hasCmdId() { return ((this.bitField0_ & 0x1) == 1); }
/*     */   public int getCmdId() { return this.cmdId_; }
/*     */   private void initFields() { this.cmdId_ = 0; }
/*     */   public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized; if (isInitialized != -1)
/*     */       return (isInitialized == 1);  if (!hasCmdId()) { this.memoizedIsInitialized = 0; return false; }  if (!extensionsAreInitialized()) { this.memoizedIsInitialized = 0; return false; }  this.memoizedIsInitialized = 1; return true; }
/*     */   public void writeTo(CodedOutputStream output) throws IOException { getSerializedSize(); GeneratedMessage.ExtendableMessage<RequestBase>.ExtensionWriter extensionWriter = newExtensionWriter(); if ((this.bitField0_ & 0x1) == 1)
/*     */       output.writeInt32(1, this.cmdId_);  extensionWriter.writeUntil(536870912, output); getUnknownFields().writeTo(output); }
/* 209 */   protected Object writeReplace() throws ObjectStreamException { return super.writeReplace(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 215 */     return (RequestBase)PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 222 */     return (RequestBase)PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 227 */     return (RequestBase)PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 234 */     return (RequestBase)PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(InputStream input) throws IOException {
/* 239 */     return (RequestBase)PARSER.parseFrom(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 246 */     return (RequestBase)PARSER.parseFrom(input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RequestBase parseDelimitedFrom(InputStream input) throws IOException {
/* 251 */     return (RequestBase)PARSER.parseDelimitedFrom(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 258 */     return (RequestBase)PARSER.parseDelimitedFrom(input, extensionRegistry);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(CodedInputStream input) throws IOException {
/* 264 */     return (RequestBase)PARSER.parseFrom(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RequestBase parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 271 */     return (RequestBase)PARSER.parseFrom(input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Builder newBuilder() {
/* 275 */     return Builder.create();
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 279 */     return newBuilder();
/*     */   }
/*     */   
/*     */   public static Builder newBuilder(RequestBase prototype) {
/* 283 */     return newBuilder().mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 287 */     return newBuilder(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/* 293 */     Builder builder = new Builder(parent);
/* 294 */     return builder;
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
/* 305 */       return Message.access$000();
/*     */     }
/*     */ 
/*     */     
/*     */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/* 310 */       return Message.access$100()
/* 311 */         .ensureFieldAccessorsInitialized(Message.RequestBase.class, Builder.class);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Builder() {
/* 317 */       maybeForceBuilderInitialization();
/*     */     }
/*     */ 
/*     */     
/*     */     private Builder(GeneratedMessage.BuilderParent parent) {
/* 322 */       super(parent);
/* 323 */       maybeForceBuilderInitialization();
/*     */     }
/*     */     
/*     */     private void maybeForceBuilderInitialization() {
/* 327 */       if (Message.RequestBase.alwaysUseFieldBuilders);
/*     */     }
/*     */ 
/*     */     
/*     */     private static Builder create() {
/* 332 */       return new Builder();
/*     */     }
/*     */     
/*     */     public Builder clear() {
/* 336 */       super.clear();
/* 337 */       this.cmdId_ = 0;
/* 338 */       this.bitField0_ &= 0xFFFFFFFE;
/* 339 */       return this;
/*     */     }
/*     */     
/*     */     public Builder clone() {
/* 343 */       return create().mergeFrom(buildPartial());
/*     */     }
/*     */ 
/*     */     
/*     */     public Descriptors.Descriptor getDescriptorForType() {
/* 348 */       return Message.access$000();
/*     */     }
/*     */     
/*     */     public Message.RequestBase getDefaultInstanceForType() {
/* 352 */       return Message.RequestBase.getDefaultInstance();
/*     */     }
/*     */     
/*     */     public Message.RequestBase build() {
/* 356 */       Message.RequestBase result = buildPartial();
/* 357 */       if (!result.isInitialized()) {
/* 358 */         throw newUninitializedMessageException(result);
/*     */       }
/* 360 */       return result;
/*     */     }
/*     */     
/*     */     public Message.RequestBase buildPartial() {
/* 364 */       Message.RequestBase result = new Message.RequestBase(this);
/* 365 */       int from_bitField0_ = this.bitField0_;
/* 366 */       int to_bitField0_ = 0;
/* 367 */       if ((from_bitField0_ & 0x1) == 1) {
/* 368 */         to_bitField0_ |= 0x1;
/*     */       }
/* 370 */       result.cmdId_ = this.cmdId_;
/* 371 */       result.bitField0_ = to_bitField0_;
/* 372 */       onBuilt();
/* 373 */       return result;
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(Message other) {
/* 377 */       if (other instanceof Message.RequestBase) {
/* 378 */         return mergeFrom((Message.RequestBase)other);
/*     */       }
/* 380 */       super.mergeFrom(other);
/* 381 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(Message.RequestBase other) {
/* 386 */       if (other == Message.RequestBase.getDefaultInstance()) return this; 
/* 387 */       if (other.hasCmdId()) {
/* 388 */         setCmdId(other.getCmdId());
/*     */       }
/* 390 */       mergeExtensionFields(other);
/* 391 */       mergeUnknownFields(other.getUnknownFields());
/* 392 */       return this;
/*     */     }
/*     */     
/*     */     public final boolean isInitialized() {
/* 396 */       if (!hasCmdId())
/*     */       {
/* 398 */         return false;
/*     */       }
/* 400 */       if (!extensionsAreInitialized())
/*     */       {
/* 402 */         return false;
/*     */       }
/* 404 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 411 */       Message.RequestBase parsedMessage = null;
/*     */       try {
/* 413 */         parsedMessage = (Message.RequestBase)Message.RequestBase.PARSER.parsePartialFrom(input, extensionRegistry);
/* 414 */       } catch (InvalidProtocolBufferException e) {
/* 415 */         parsedMessage = (Message.RequestBase)e.getUnfinishedMessage();
/* 416 */         throw e;
/*     */       } finally {
/* 418 */         if (parsedMessage != null) {
/* 419 */           mergeFrom(parsedMessage);
/*     */         }
/*     */       } 
/* 422 */       return this;
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
/* 434 */       return ((this.bitField0_ & 0x1) == 1);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getCmdId() {
/* 441 */       return this.cmdId_;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setCmdId(int value) {
/* 448 */       this.bitField0_ |= 0x1;
/* 449 */       this.cmdId_ = value;
/* 450 */       onChanged();
/* 451 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder clearCmdId() {
/* 458 */       this.bitField0_ &= 0xFFFFFFFE;
/* 459 */       this.cmdId_ = 0;
/* 460 */       onChanged();
/* 461 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 468 */     defaultInstance = new RequestBase(true);
/* 469 */     defaultInstance.initFields();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\Message$RequestBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */