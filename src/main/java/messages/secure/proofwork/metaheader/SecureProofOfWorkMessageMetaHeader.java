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
	
	
	public SecureProofOfWorkMessageMetaHeader() {
		
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

	public int getSizeOfSecureProofOfWorkMessageSolvedBlockSerialized() {
		return this.sizeOfSecureProofOfWorkMessageSolvedBlockSerialized;
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
