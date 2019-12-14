package main.java.messages.secure.proofwork.metaheader;

public class SecureProofOfWorkMessageMetaHeader {
	
	private int sizeOfUserPeerIDSerialized;
	
	private int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
	private int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	private int sizeOfSecureProofOfWorkMessageDoSMitigationSerialized;
	
	private int sizeOfSecureBidMessageSolvedBlockConfidentialSerialized;
	private int sizeOfSecureBidMessageSolvedBlockSignatureSerialized;
	
	private int sizeOfBlockAndBlockSolvedHashedSerialized;
	private int sizeOfBlockSerialized;
	private int sizeOfBlockSolvedHashedSerialized;
	
	
	private byte[] secureProofOfWorkMessageMetaHeaderSerialized;
	
	private boolean isSecureProofOfWorkMessageMetaHeaderSerialized;
	
	
	public SecureProofOfWorkMessageMetaHeader(int sizeOfUserPeerIDSerialized,
											  
											  int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered,
											  int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned,
											  
											  int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized,
											  int sizeOfSecureProofOfWorkMessageDoSMitigationSerialized,
											  
											  int sizeOfSecureBidMessageSolvedBlockConfidentialSerialized,
											  int sizeOfSecureBidMessageSolvedBlockSignatureSerialized,
											  
											  int sizeOfBlockAndBlockSolvedHashedSerialized,
											  int sizeOfBlockSerialized,
											  int sizeOfBlockSolvedHashedSerialized) {
		
		
		this.sizeOfUserPeerIDSerialized = sizeOfUserPeerIDSerialized;
		
		this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered = 
					sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
		this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = 
					sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
		
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
					sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
		this.sizeOfSecureProofOfWorkMessageDoSMitigationSerialized = 
					sizeOfSecureProofOfWorkMessageDoSMitigationSerialized;
		
		this.sizeOfSecureBidMessageSolvedBlockConfidentialSerialized = 
					sizeOfSecureBidMessageSolvedBlockConfidentialSerialized;
		this.sizeOfSecureBidMessageSolvedBlockSignatureSerialized = 
					sizeOfSecureBidMessageSolvedBlockSignatureSerialized;
		
		this.sizeOfBlockAndBlockSolvedHashedSerialized = sizeOfBlockAndBlockSolvedHashedSerialized;
		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
		this.sizeOfBlockSolvedHashedSerialized = sizeOfBlockSolvedHashedSerialized;
		
		
		this.secureProofOfWorkMessageMetaHeaderSerialized = null;
		
		this.isSecureProofOfWorkMessageMetaHeaderSerialized = false;
		
	}
	
	
	public int getSizeOfUserPeerIDSerialized() {
		return this.sizeOfUserPeerIDSerialized;
	}

	public int getSizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered() {
		return this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
	}
	
	public int getSizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned() {
		return this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	}

	public int getSizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized() {
		return this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	}
	
	public int getSizeOfSecureProofOfWorkMessageDoSMitigationSerialized() {
		return this.sizeOfSecureProofOfWorkMessageDoSMitigationSerialized;
	}
	  
	public int getSizeOfSecureBidMessageSolvedBlockConfidentialSerialized() {
		return this.sizeOfSecureBidMessageSolvedBlockConfidentialSerialized;
	}
	
	public int getSizeOfSecureBidMessageSolvedBlockSignatureSerialized() {
		return this.sizeOfSecureBidMessageSolvedBlockSignatureSerialized;
	}
	
	public int getSizeOfBlockAndBlockSolvedHashedSerialized() {
		return this.sizeOfBlockAndBlockSolvedHashedSerialized;
	}
	
	public int getSizeOfBlockSerialized() {
		return this.sizeOfBlockSerialized;
	}
	
	public int getSizeOfBlockSolvedHashedSerialized() {
		return this.sizeOfBlockSolvedHashedSerialized;
	}
	
	public byte[] getSecureProofOfWorkMessageMetaHeaderSerialized() {
		return this.secureProofOfWorkMessageMetaHeaderSerialized;
	}
	
	public boolean getIsSecureProofOfWorkMessageMetaHeaderSerialized() {
		return this.isSecureProofOfWorkMessageMetaHeaderSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageMetaHeaderSerialized
		  (boolean isSecureProofOfWorkMessageMetaHeaderSerialized) {
		
		this.isSecureProofOfWorkMessageMetaHeaderSerialized = isSecureProofOfWorkMessageMetaHeaderSerialized;
		
	}


	public void doSecureProofOfWorkMessageMetaHeaderSerialization() {
		// TODO Auto-generated method stub
		
	}
	
}
