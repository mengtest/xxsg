/*      */ package com.linlongyx.sanguo.webgame.proto.protobuf;
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
/*      */ public final class ResponseBase
/*      */   extends GeneratedMessage.ExtendableMessage<Message.ResponseBase>
/*      */   implements Message.ResponseBaseOrBuilder
/*      */ {
/*      */   private static final ResponseBase defaultInstance;
/*      */   private final UnknownFieldSet unknownFields;
/*      */   
/*      */   private ResponseBase(GeneratedMessage.ExtendableBuilder<ResponseBase, ?> builder) {
/*  509 */     super(builder);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  650 */     this.memoizedIsInitialized = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  687 */     this.memoizedSerializedSize = -1; this.unknownFields = builder.getUnknownFields(); } private ResponseBase(boolean noInit) { this.memoizedIsInitialized = -1; this.memoizedSerializedSize = -1; this.unknownFields = UnknownFieldSet.getDefaultInstance(); } public static ResponseBase getDefaultInstance() { return defaultInstance; } public ResponseBase getDefaultInstanceForType() { return defaultInstance; } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private ResponseBase(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this.memoizedIsInitialized = -1; this.memoizedSerializedSize = -1; initFields(); int mutable_bitField0_ = 0; UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { int tag = input.readTag(); switch (tag) { case 0: done = true; break;case 8: this.bitField0_ |= 0x1; this.cmdId_ = input.readInt32(); break;case 16: this.bitField0_ |= 0x2; this.errorCode_ = input.readInt32(); break; }  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e.getMessage())).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  }
/*      */   public static final Descriptors.Descriptor getDescriptor() { return Message.access$900(); } protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() { return Message.access$1000().ensureFieldAccessorsInitialized(ResponseBase.class, Builder.class); } public static Parser<ResponseBase> PARSER = (Parser<ResponseBase>)new AbstractParser<ResponseBase>() {
/*      */       public Message.ResponseBase parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { return new Message.ResponseBase(input, extensionRegistry); }
/*  690 */     }; private int bitField0_; public int getSerializedSize() { int size = this.memoizedSerializedSize;
/*  691 */     if (size != -1) return size;
/*      */     
/*  693 */     size = 0;
/*  694 */     if ((this.bitField0_ & 0x1) == 1) {
/*  695 */       size += 
/*  696 */         CodedOutputStream.computeInt32Size(1, this.cmdId_);
/*      */     }
/*  698 */     if ((this.bitField0_ & 0x2) == 2) {
/*  699 */       size += 
/*  700 */         CodedOutputStream.computeInt32Size(2, this.errorCode_);
/*      */     }
/*  702 */     size += extensionsSerializedSize();
/*  703 */     size += getUnknownFields().getSerializedSize();
/*  704 */     this.memoizedSerializedSize = size;
/*  705 */     return size; }
/*      */    public static final int CMDID_FIELD_NUMBER = 1; private int cmdId_; public static final int ERRORCODE_FIELD_NUMBER = 2; private int errorCode_; private byte memoizedIsInitialized; private int memoizedSerializedSize; private static final long serialVersionUID = 0L; public Parser<ResponseBase> getParserForType() {
/*      */     return PARSER;
/*      */   } public boolean hasCmdId() {
/*      */     return ((this.bitField0_ & 0x1) == 1);
/*      */   } public int getCmdId() {
/*      */     return this.cmdId_;
/*      */   } protected Object writeReplace() throws ObjectStreamException {
/*  713 */     return super.writeReplace(); } public boolean hasErrorCode() { return ((this.bitField0_ & 0x2) == 2); }
/*      */   public int getErrorCode() { return this.errorCode_; }
/*      */   private void initFields() { this.cmdId_ = 0; this.errorCode_ = 0; }
/*      */   public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized; if (isInitialized != -1) return (isInitialized == 1);  if (!hasCmdId()) { this.memoizedIsInitialized = 0; return false; }  if (!hasErrorCode()) { this.memoizedIsInitialized = 0; return false; }  if (!extensionsAreInitialized()) { this.memoizedIsInitialized = 0; return false; }  this.memoizedIsInitialized = 1; return true; }
/*      */   public void writeTo(CodedOutputStream output) throws IOException { getSerializedSize(); GeneratedMessage.ExtendableMessage<ResponseBase>.ExtensionWriter extensionWriter = newExtensionWriter(); if ((this.bitField0_ & 0x1) == 1) output.writeInt32(1, this.cmdId_);  if ((this.bitField0_ & 0x2) == 2)
/*      */       output.writeInt32(2, this.errorCode_);  extensionWriter.writeUntil(536870912, output); getUnknownFields().writeTo(output); }
/*  719 */   public static ResponseBase parseFrom(ByteString data) throws InvalidProtocolBufferException { return (ResponseBase)PARSER.parseFrom(data); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ResponseBase parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  726 */     return (ResponseBase)PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static ResponseBase parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  731 */     return (ResponseBase)PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ResponseBase parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  738 */     return (ResponseBase)PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static ResponseBase parseFrom(InputStream input) throws IOException {
/*  743 */     return (ResponseBase)PARSER.parseFrom(input);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ResponseBase parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  750 */     return (ResponseBase)PARSER.parseFrom(input, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static ResponseBase parseDelimitedFrom(InputStream input) throws IOException {
/*  755 */     return (ResponseBase)PARSER.parseDelimitedFrom(input);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ResponseBase parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  762 */     return (ResponseBase)PARSER.parseDelimitedFrom(input, extensionRegistry);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static ResponseBase parseFrom(CodedInputStream input) throws IOException {
/*  768 */     return (ResponseBase)PARSER.parseFrom(input);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ResponseBase parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  775 */     return (ResponseBase)PARSER.parseFrom(input, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static Builder newBuilder() {
/*  779 */     return Builder.create();
/*      */   }
/*      */   
/*      */   public Builder newBuilderForType() {
/*  783 */     return newBuilder();
/*      */   }
/*      */   
/*      */   public static Builder newBuilder(ResponseBase prototype) {
/*  787 */     return newBuilder().mergeFrom(prototype);
/*      */   }
/*      */   
/*      */   public Builder toBuilder() {
/*  791 */     return newBuilder(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*  797 */     Builder builder = new Builder(parent);
/*  798 */     return builder;
/*      */   }
/*      */   
/*      */   public static final class Builder
/*      */     extends GeneratedMessage.ExtendableBuilder<ResponseBase, Builder>
/*      */     implements Message.ResponseBaseOrBuilder {
/*      */     private int bitField0_;
/*      */     private int cmdId_;
/*      */     private int errorCode_;
/*      */     
/*      */     public static final Descriptors.Descriptor getDescriptor() {
/*  809 */       return Message.access$900();
/*      */     }
/*      */ 
/*      */     
/*      */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*  814 */       return Message.access$1000()
/*  815 */         .ensureFieldAccessorsInitialized(Message.ResponseBase.class, Builder.class);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private Builder() {
/*  821 */       maybeForceBuilderInitialization();
/*      */     }
/*      */ 
/*      */     
/*      */     private Builder(GeneratedMessage.BuilderParent parent) {
/*  826 */       super(parent);
/*  827 */       maybeForceBuilderInitialization();
/*      */     }
/*      */     
/*      */     private void maybeForceBuilderInitialization() {
/*  831 */       if (Message.ResponseBase.alwaysUseFieldBuilders);
/*      */     }
/*      */ 
/*      */     
/*      */     private static Builder create() {
/*  836 */       return new Builder();
/*      */     }
/*      */     
/*      */     public Builder clear() {
/*  840 */       super.clear();
/*  841 */       this.cmdId_ = 0;
/*  842 */       this.bitField0_ &= 0xFFFFFFFE;
/*  843 */       this.errorCode_ = 0;
/*  844 */       this.bitField0_ &= 0xFFFFFFFD;
/*  845 */       return this;
/*      */     }
/*      */     
/*      */     public Builder clone() {
/*  849 */       return create().mergeFrom(buildPartial());
/*      */     }
/*      */ 
/*      */     
/*      */     public Descriptors.Descriptor getDescriptorForType() {
/*  854 */       return Message.access$900();
/*      */     }
/*      */     
/*      */     public Message.ResponseBase getDefaultInstanceForType() {
/*  858 */       return Message.ResponseBase.getDefaultInstance();
/*      */     }
/*      */     
/*      */     public Message.ResponseBase build() {
/*  862 */       Message.ResponseBase result = buildPartial();
/*  863 */       if (!result.isInitialized()) {
/*  864 */         throw newUninitializedMessageException(result);
/*      */       }
/*  866 */       return result;
/*      */     }
/*      */     
/*      */     public Message.ResponseBase buildPartial() {
/*  870 */       Message.ResponseBase result = new Message.ResponseBase(this);
/*  871 */       int from_bitField0_ = this.bitField0_;
/*  872 */       int to_bitField0_ = 0;
/*  873 */       if ((from_bitField0_ & 0x1) == 1) {
/*  874 */         to_bitField0_ |= 0x1;
/*      */       }
/*  876 */       result.cmdId_ = this.cmdId_;
/*  877 */       if ((from_bitField0_ & 0x2) == 2) {
/*  878 */         to_bitField0_ |= 0x2;
/*      */       }
/*  880 */       result.errorCode_ = this.errorCode_;
/*  881 */       result.bitField0_ = to_bitField0_;
/*  882 */       onBuilt();
/*  883 */       return result;
/*      */     }
/*      */     
/*      */     public Builder mergeFrom(Message other) {
/*  887 */       if (other instanceof Message.ResponseBase) {
/*  888 */         return mergeFrom((Message.ResponseBase)other);
/*      */       }
/*  890 */       super.mergeFrom(other);
/*  891 */       return this;
/*      */     }
/*      */ 
/*      */     
/*      */     public Builder mergeFrom(Message.ResponseBase other) {
/*  896 */       if (other == Message.ResponseBase.getDefaultInstance()) return this; 
/*  897 */       if (other.hasCmdId()) {
/*  898 */         setCmdId(other.getCmdId());
/*      */       }
/*  900 */       if (other.hasErrorCode()) {
/*  901 */         setErrorCode(other.getErrorCode());
/*      */       }
/*  903 */       mergeExtensionFields(other);
/*  904 */       mergeUnknownFields(other.getUnknownFields());
/*  905 */       return this;
/*      */     }
/*      */     
/*      */     public final boolean isInitialized() {
/*  909 */       if (!hasCmdId())
/*      */       {
/*  911 */         return false;
/*      */       }
/*  913 */       if (!hasErrorCode())
/*      */       {
/*  915 */         return false;
/*      */       }
/*  917 */       if (!extensionsAreInitialized())
/*      */       {
/*  919 */         return false;
/*      */       }
/*  921 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  928 */       Message.ResponseBase parsedMessage = null;
/*      */       try {
/*  930 */         parsedMessage = (Message.ResponseBase)Message.ResponseBase.PARSER.parsePartialFrom(input, extensionRegistry);
/*  931 */       } catch (InvalidProtocolBufferException e) {
/*  932 */         parsedMessage = (Message.ResponseBase)e.getUnfinishedMessage();
/*  933 */         throw e;
/*      */       } finally {
/*  935 */         if (parsedMessage != null) {
/*  936 */           mergeFrom(parsedMessage);
/*      */         }
/*      */       } 
/*  939 */       return this;
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
/*      */     public boolean hasCmdId() {
/*  951 */       return ((this.bitField0_ & 0x1) == 1);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getCmdId() {
/*  958 */       return this.cmdId_;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder setCmdId(int value) {
/*  965 */       this.bitField0_ |= 0x1;
/*  966 */       this.cmdId_ = value;
/*  967 */       onChanged();
/*  968 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder clearCmdId() {
/*  975 */       this.bitField0_ &= 0xFFFFFFFE;
/*  976 */       this.cmdId_ = 0;
/*  977 */       onChanged();
/*  978 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasErrorCode() {
/*  988 */       return ((this.bitField0_ & 0x2) == 2);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getErrorCode() {
/*  995 */       return this.errorCode_;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder setErrorCode(int value) {
/* 1002 */       this.bitField0_ |= 0x2;
/* 1003 */       this.errorCode_ = value;
/* 1004 */       onChanged();
/* 1005 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder clearErrorCode() {
/* 1012 */       this.bitField0_ &= 0xFFFFFFFD;
/* 1013 */       this.errorCode_ = 0;
/* 1014 */       onChanged();
/* 1015 */       return this;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/* 1022 */     defaultInstance = new ResponseBase(true);
/* 1023 */     defaultInstance.initFields();
/*      */   }
/*      */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\protobuf\Message$ResponseBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */