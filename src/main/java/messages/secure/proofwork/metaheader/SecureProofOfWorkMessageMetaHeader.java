package main.java.messages.secure.proofwork.metaheader;

public class SecureProofOfWorkMessageMetaHeader {
	
	private int sizeOfUserPeerIDSerialized;
	
	private int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
	private int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	
	private int sizeOfSecureProofOfWorkMessageSolvedBlockSerialized;
	private int sizeOfSecureProofOfWorkMessageDoSMitigationSerialized;
	  
	private int sizeOfSecureBidMessageDataSignatureSerialized;
	private int sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed;
	  
	private int sizeOfProofOfWorkSerialized;
	private int sizeOfBidSerializedDigitalSigned;
	  
	private int sizeOfBidderUserClientIDSerialized;
	  
	private int sizeOfSecureBidMessageDataPersonalSerializedCiphered;
	private int sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed;
	private int sizeOfSecureBidMessageDataPersonalSerialized;
	  
	private int sizeOfUserEmailSerialized;
	private int sizeOfUserHomeAddressSerialized;
	private int sizeOfUserBankAccountNIBSerialized;
	
	
	private byte[] secureBidMessageMetaHeaderSerialized;
	
	private boolean isSecureBidMessageMetaHeaderSerialized;
	
}
