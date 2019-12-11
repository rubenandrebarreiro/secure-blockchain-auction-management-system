package main.java.messages.secure.proofwork.metaheader;

public class SecureProofOfWorkMessageMetaHeader {
	
	private int sizeOfUserPeerIDSerialized;
	
	private int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
	private int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	
	private int sizeOfSecureProofOfWorkMessageSolvedBlockSerialized;
	private int sizeOfSecureProofOfWorkMessageDoSMitigationSerialized;
	  
	private int sizeOfSecureBidMessageSolvedBlockConfidentialSerialized;
	private int sizeOfSecureBidMessageSolvedBlockSignatureSerialized;
	
	private int sizeOfBlockAndBlockSolvedHashedSerialized;
	private int sizeOfBlockSerialized;
	private int sizeOfBlockSolvedHashedSerialized;
	
	
	private byte[] secureProofOfWorkMessageMetaHeaderSerialized;
	
	private boolean isSecureProofOfWorkMessageMetaHeaderSerialized;
	
}
