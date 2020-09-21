/*      */ package com.linlongyx.sanguo.webgame.proto;
/*      */ 
/*      */ import com.google.protobuf.AbstractMessage;
/*      */ import com.google.protobuf.AbstractMessageLite;
/*      */ import com.google.protobuf.AbstractParser;
/*      */ import com.google.protobuf.ByteString;
/*      */ import com.google.protobuf.CodedInputStream;
/*      */ import com.google.protobuf.CodedOutputStream;
/*      */ import com.google.protobuf.Descriptors;
/*      */ import com.google.protobuf.ExtensionRegistryLite;
/*      */ import com.google.protobuf.GeneratedMessage;
/*      */ import com.google.protobuf.InvalidProtocolBufferException;
/*      */ import com.google.protobuf.Message;
/*      */ import com.google.protobuf.MessageLite;
/*      */ import com.google.protobuf.Parser;
/*      */ import com.google.protobuf.UnknownFieldSet;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.ObjectStreamException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class Login
/*      */   extends GeneratedMessage
/*      */   implements Message.LoginOrBuilder
/*      */ {
/*      */   private static final Login defaultInstance;
/*      */   private final UnknownFieldSet unknownFields;
/*      */   
/*      */   private Login(GeneratedMessage.Builder<?> builder) {
/*  523 */     super(builder);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  720 */     this.memoizedIsInitialized = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  750 */     this.memoizedSerializedSize = -1; this.unknownFields = builder.getUnknownFields(); } private Login(boolean noInit) { this.memoizedIsInitialized = -1; this.memoizedSerializedSize = -1; this.unknownFields = UnknownFieldSet.getDefaultInstance(); } public static Login getDefaultInstance() { return defaultInstance; } public Login getDefaultInstanceForType() { return defaultInstance; } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private Login(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this.memoizedIsInitialized = -1; this.memoizedSerializedSize = -1; initFields(); int mutable_bitField0_ = 0; UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { int tag = input.readTag(); switch (tag) { case 0: done = true; break;case 10: this.bitField0_ |= 0x1; this.user_ = input.readBytes(); break;case 18: this.bitField0_ |= 0x2; this.pwd_ = input.readBytes(); break; }  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e.getMessage())).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public static final Descriptors.Descriptor getDescriptor() { return Message.access$900(); } protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() { return Message.access$1000().ensureFieldAccessorsInitialized(Login.class, Builder.class); } public static Parser<Login> PARSER = (Parser<Login>)new AbstractParser<Login>() { public Message.Login parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { return new Message.Login(input, extensionRegistry); } }
/*      */   ;
/*      */   private int bitField0_; public static final int USER_FIELD_NUMBER = 1; private Object user_; public static final int PWD_FIELD_NUMBER = 2; private Object pwd_; private byte memoizedIsInitialized; private int memoizedSerializedSize;
/*  753 */   public int getSerializedSize() { int size = this.memoizedSerializedSize;
/*  754 */     if (size != -1) return size;
/*      */     
/*  756 */     size = 0;
/*  757 */     if ((this.bitField0_ & 0x1) == 1) {
/*  758 */       size += 
/*  759 */         CodedOutputStream.computeBytesSize(1, getUserBytes());
/*      */     }
/*  761 */     if ((this.bitField0_ & 0x2) == 2) {
/*  762 */       size += 
/*  763 */         CodedOutputStream.computeBytesSize(2, getPwdBytes());
/*      */     }
/*  765 */     size += getUnknownFields().getSerializedSize();
/*  766 */     this.memoizedSerializedSize = size;
/*  767 */     return size; } private static final long serialVersionUID = 0L; public Parser<Login> getParserForType() { return PARSER; }
/*      */   public boolean hasUser() { return ((this.bitField0_ & 0x1) == 1); }
/*      */   public String getUser() { Object ref = this.user_; if (ref instanceof String)
/*      */       return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (bs.isValidUtf8())
/*      */       this.user_ = s;  return s; }
/*      */   public ByteString getUserBytes() { Object ref = this.user_; if (ref instanceof String) {
/*      */       ByteString b = ByteString.copyFromUtf8((String)ref); this.user_ = b; return b;
/*      */     }  return (ByteString)ref; }
/*  775 */   protected Object writeReplace() throws ObjectStreamException { return super.writeReplace(); } public boolean hasPwd() { return ((this.bitField0_ & 0x2) == 2); }
/*      */   public String getPwd() { Object ref = this.pwd_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (bs.isValidUtf8()) this.pwd_ = s;  return s; }
/*      */   public ByteString getPwdBytes() { Object ref = this.pwd_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.pwd_ = b; return b; }  return (ByteString)ref; }
/*      */   private void initFields() { this.user_ = ""; this.pwd_ = ""; }
/*      */   public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized; if (isInitialized != -1) return (isInitialized == 1);  if (!hasUser()) { this.memoizedIsInitialized = 0; return false; }  if (!hasPwd()) { this.memoizedIsInitialized = 0; return false; }  this.memoizedIsInitialized = 1; return true; }
/*      */   public void writeTo(CodedOutputStream output) throws IOException { getSerializedSize(); if ((this.bitField0_ & 0x1) == 1) output.writeBytes(1, getUserBytes());  if ((this.bitField0_ & 0x2) == 2) output.writeBytes(2, getPwdBytes());  getUnknownFields().writeTo(output); }
/*  781 */   public static Login parseFrom(ByteString data) throws InvalidProtocolBufferException { return (Login)PARSER.parseFrom(data); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Login parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  788 */     return (Login)PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Login parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  793 */     return (Login)PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Login parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  800 */     return (Login)PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Login parseFrom(InputStream input) throws IOException {
/*  805 */     return (Login)PARSER.parseFrom(input);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Login parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  812 */     return (Login)PARSER.parseFrom(input, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Login parseDelimitedFrom(InputStream input) throws IOException {
/*  817 */     return (Login)PARSER.parseDelimitedFrom(input);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Login parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  824 */     return (Login)PARSER.parseDelimitedFrom(input, extensionRegistry);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Login parseFrom(CodedInputStream input) throws IOException {
/*  830 */     return (Login)PARSER.parseFrom(input);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Login parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  837 */     return (Login)PARSER.parseFrom(input, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static Builder newBuilder() {
/*  841 */     return Builder.create();
/*      */   }
/*      */   
/*      */   public Builder newBuilderForType() {
/*  845 */     return newBuilder();
/*      */   }
/*      */   
/*      */   public static Builder newBuilder(Login prototype) {
/*  849 */     return newBuilder().mergeFrom(prototype);
/*      */   }
/*      */   
/*      */   public Builder toBuilder() {
/*  853 */     return newBuilder(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*  859 */     Builder builder = new Builder(parent);
/*  860 */     return builder;
/*      */   }
/*      */   
/*      */   public static final class Builder
/*      */     extends GeneratedMessage.Builder<Builder>
/*      */     implements Message.LoginOrBuilder {
/*      */     private int bitField0_;
/*      */     private Object user_;
/*      */     private Object pwd_;
/*      */     
/*      */     public static final Descriptors.Descriptor getDescriptor() {
/*  871 */       return Message.access$900();
/*      */     }
/*      */ 
/*      */     
/*      */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*  876 */       return Message.access$1000()
/*  877 */         .ensureFieldAccessorsInitialized(Message.Login.class, Builder.class);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Builder()
/*      */     {
/* 1006 */       this.user_ = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1086 */       this.pwd_ = ""; maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (Message.Login.alwaysUseFieldBuilders); } private static Builder create() { return new Builder(); } public Builder clear() { super.clear(); this.user_ = ""; this.bitField0_ &= 0xFFFFFFFE; this.pwd_ = ""; this.bitField0_ &= 0xFFFFFFFD; return this; } public Builder clone() { return create().mergeFrom(buildPartial()); } public Descriptors.Descriptor getDescriptorForType() { return Message.access$900(); } private Builder(GeneratedMessage.BuilderParent parent) { super(parent); this.user_ = ""; this.pwd_ = ""; maybeForceBuilderInitialization(); }
/*      */     public Message.Login getDefaultInstanceForType() { return Message.Login.getDefaultInstance(); }
/*      */     public Message.Login build() { Message.Login result = buildPartial(); if (!result.isInitialized()) throw newUninitializedMessageException(result);  return result; }
/*      */     public Message.Login buildPartial() { Message.Login result = new Message.Login(this); int from_bitField0_ = this.bitField0_; int to_bitField0_ = 0; if ((from_bitField0_ & 0x1) == 1) to_bitField0_ |= 0x1;  result.user_ = this.user_; if ((from_bitField0_ & 0x2) == 2)
/*      */         to_bitField0_ |= 0x2;  result.pwd_ = this.pwd_; result.bitField0_ = to_bitField0_; onBuilt(); return result; }
/*      */     public Builder mergeFrom(Message other) { if (other instanceof Message.Login)
/* 1092 */         return mergeFrom((Message.Login)other);  super.mergeFrom(other); return this; } public boolean hasPwd() { return ((this.bitField0_ & 0x2) == 2); } public Builder mergeFrom(Message.Login other) { if (other == Message.Login.getDefaultInstance()) return this;  if (other.hasUser()) { this.bitField0_ |= 0x1; this.user_ = other.user_; onChanged(); }  if (other.hasPwd()) { this.bitField0_ |= 0x2; this.pwd_ = other.pwd_; onChanged(); }  mergeUnknownFields(other.getUnknownFields()); return this; }
/*      */     public final boolean isInitialized() { if (!hasUser())
/*      */         return false;  if (!hasPwd())
/*      */         return false;  return true; }
/*      */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { Message.Login parsedMessage = null; try { parsedMessage = (Message.Login)Message.Login.PARSER.parsePartialFrom(input, extensionRegistry); } catch (InvalidProtocolBufferException e) { parsedMessage = (Message.Login)e.getUnfinishedMessage(); throw e; } finally { if (parsedMessage != null)
/*      */           mergeFrom(parsedMessage);  }  return this; }
/*      */     public boolean hasUser() { return ((this.bitField0_ & 0x1) == 1); }
/* 1099 */     public String getPwd() { Object ref = this.pwd_;
/* 1100 */       if (!(ref instanceof String)) {
/*      */         
/* 1102 */         String s = ((ByteString)ref).toStringUtf8();
/* 1103 */         this.pwd_ = s;
/* 1104 */         return s;
/*      */       } 
/* 1106 */       return (String)ref; } public String getUser() { Object ref = this.user_; if (!(ref instanceof String)) { String s = ((ByteString)ref).toStringUtf8(); this.user_ = s; return s; }
/*      */        return (String)ref; }
/*      */     public ByteString getUserBytes() { Object ref = this.user_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.user_ = b; return b; }
/*      */        return (ByteString)ref; }
/*      */     public Builder setUser(String value) { if (value == null)
/*      */         throw new NullPointerException();  this.bitField0_ |= 0x1; this.user_ = value; onChanged(); return this; }
/*      */     public Builder clearUser() { this.bitField0_ &= 0xFFFFFFFE; this.user_ = Message.Login.getDefaultInstance().getUser(); onChanged(); return this; }
/*      */     public Builder setUserBytes(ByteString value) { if (value == null)
/*      */         throw new NullPointerException();  this.bitField0_ |= 0x1; this.user_ = value; onChanged(); return this; }
/* 1115 */     public ByteString getPwdBytes() { Object ref = this.pwd_;
/* 1116 */       if (ref instanceof String) {
/*      */         
/* 1118 */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*      */         
/* 1120 */         this.pwd_ = b;
/* 1121 */         return b;
/*      */       } 
/* 1123 */       return (ByteString)ref; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder setPwd(String value) {
/* 1132 */       if (value == null) {
/* 1133 */         throw new NullPointerException();
/*      */       }
/* 1135 */       this.bitField0_ |= 0x2;
/* 1136 */       this.pwd_ = value;
/* 1137 */       onChanged();
/* 1138 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder clearPwd() {
/* 1145 */       this.bitField0_ &= 0xFFFFFFFD;
/* 1146 */       this.pwd_ = Message.Login.getDefaultInstance().getPwd();
/* 1147 */       onChanged();
/* 1148 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder setPwdBytes(ByteString value) {
/* 1156 */       if (value == null) {
/* 1157 */         throw new NullPointerException();
/*      */       }
/* 1159 */       this.bitField0_ |= 0x2;
/* 1160 */       this.pwd_ = value;
/* 1161 */       onChanged();
/* 1162 */       return this;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/* 1169 */     defaultInstance = new Login(true);
/* 1170 */     defaultInstance.initFields();
/*      */   }
/*      */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\Message$Login.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */