package main.java.messages.secure.proofwork.solvedblock;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import main.java.messages.secure.proofwork.solvedblock.confidential.SecureProofOfWorkMessageSolvedBlockConfidential;
import main.java.messages.secure.proofwork.solvedblock.signature.SecureProofOfWorkMessageSolvedBlockSignature;

public class SecureProofOfWorkMessageSolvedBlock {
	
	private SecureProofOfWorkMessageSolvedBlockConfidential secureProofOfWorkMessageSolvedBlockConfidential;
	
	private SecureProofOfWorkMessageSolvedBlockSignature secureProofOfWorkMessageSolvedBlockSignature;
	
	
	private byte[] secureProofOfWorkMessageSolvedBlockConfidentialSerialized;
	
	private int sizeOfSecureProofOfWorkMessageSolvedBlockConfidentialSerialized;
	
	private boolean isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized;
	

	private byte[] secureProofOfWorkMessageSolvedBlockSignatureSerialized;
	
	private int sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized;
	
	private boolean isSecureProofOfWorkMessageSolvedBlockSignatureSerialized;
	
	
	private byte[] secureProofOfWorkMessageSolvedBlockSerialized;
	
	private int sizeOfSecureProofOfWorkMessageSolvedBlockSerialized;
	
	private boolean isSecureProofOfWorkMessageSolvedBlockSerialized;
	
	
	
	public SecureProofOfWorkMessageSolvedBlock
	      (SecureProofOfWorkMessageSolvedBlockConfidential secureProofOfWorkMessageSolvedBlockConfidential,
		   SecureProofOfWorkMessageSolvedBlockSignature secureProofOfWorkMessageSolvedBlockSignature)
		   throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		
		this.secureProofOfWorkMessageSolvedBlockConfidential = secureProofOfWorkMessageSolvedBlockConfidential;
		this.secureProofOfWorkMessageSolvedBlockSignature = secureProofOfWorkMessageSolvedBlockSignature;

		this.secureProofOfWorkMessageSolvedBlockConfidential
			.buildSecureProofOfWorkMessageSolvedBlockConfidentialToSend();
		this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized = 
				this.secureProofOfWorkMessageSolvedBlockConfidential.getBlockSerializedAndSolvedHashedCiphered();
		this.sizeOfSecureProofOfWorkMessageSolvedBlockConfidentialSerialized = 
				this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized.length;
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized = true;
		
		this.secureProofOfWorkMessageSolvedBlockSignature.signSecureProofOfWorkMessageSolvedBlock();
		this.secureProofOfWorkMessageSolvedBlockSignatureSerialized = 
				this.secureProofOfWorkMessageSolvedBlockSignature
				    .getSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned();
		this.sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized = 
				secureProofOfWorkMessageSolvedBlockSignatureSerialized.length;
		this.isSecureProofOfWorkMessageSolvedBlockSignatureSerialized = true;
		
		
		this.secureProofOfWorkMessageSolvedBlockSerialized = null;
		this.sizeOfSecureProofOfWorkMessageSolvedBlockSerialized = 0;
		this.isSecureProofOfWorkMessageSolvedBlockSerialized = false;
		
	}
	
	public SecureProofOfWorkMessageSolvedBlock
	      (byte[] secureProofOfWorkMessageSolvedBlockSerialized,
	       int sizeOfSecureProofOfWorkMessageSolvedBlockConfidentialSerialized,
	       int sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized) {
		
		this.secureProofOfWorkMessageSolvedBlockSerialized = secureProofOfWorkMessageSolvedBlockSerialized;
		this.sizeOfSecureProofOfWorkMessageSolvedBlockSerialized = 
				secureProofOfWorkMessageSolvedBlockSerialized.length;
		this.isSecureProofOfWorkMessageSolvedBlockSerialized = true;
		
		
		this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized = null;
		this.sizeOfSecureProofOfWorkMessageSolvedBlockConfidentialSerialized = 
				sizeOfSecureProofOfWorkMessageSolvedBlockConfidentialSerialized;
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized = true;
		
		
		this.secureProofOfWorkMessageSolvedBlockSignatureSerialized = null;
		this.sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized = 
				sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized;
		this.isSecureProofOfWorkMessageSolvedBlockSignatureSerialized = true;

		
		this.secureProofOfWorkMessageSolvedBlockConfidential = null;
		this.secureProofOfWorkMessageSolvedBlockSignature = null;

		
	}
	
	
	public SecureProofOfWorkMessageSolvedBlockConfidential getSecureProofOfWorkMessageSolvedBlockConfidential() {
		return this.secureProofOfWorkMessageSolvedBlockConfidential;
	}
	
	public SecureProofOfWorkMessageSolvedBlockSignature getSecureProofOfWorkMessageSolvedBlockSignature() {
		return this.secureProofOfWorkMessageSolvedBlockSignature;
	}
	
	public boolean getIsSecureProofOfWorkMessageSolvedBlockConfidentialSerialized() {
		return this.isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageSolvedBlockConfidentialSerialized
		  (boolean isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized) {
		
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized = 
				isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized;
	
	}
	
	public boolean getIsSecureProofOfWorkMessageSolvedBlockSignatureSerialized() {
		return this.isSecureProofOfWorkMessageSolvedBlockSignatureSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageSolvedBlockSignatureSerialized
	      (boolean isSecureProofOfWorkMessageSolvedBlockSignatureSerialized) {
		
		this.isSecureProofOfWorkMessageSolvedBlockSignatureSerialized = 
				isSecureProofOfWorkMessageSolvedBlockSignatureSerialized;
	
	}
	

	public byte[] secureProofOfWorkMessageSolvedBlockSerialized() {
		return this.secureProofOfWorkMessageSolvedBlockSerialized;
	}
	
	public boolean getIsSecureProofOfWorkMessageSolvedBlockSerialized() {
		return this.isSecureProofOfWorkMessageSolvedBlockSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageSolvedBlockSerialized
		  (boolean isSecureProofOfWorkMessageSolvedBlockSerialized) {
		
		this.isSecureProofOfWorkMessageSolvedBlockSerialized = 
				isSecureProofOfWorkMessageSolvedBlockSerialized;
	
	}
	
	
	
	public void buildSecureProofOfWorkMessageSolvedBlockToSend() {
		
		if(!this.isSecureProofOfWorkMessageSolvedBlockSerialized) {
			
			this.sizeOfSecureProofOfWorkMessageSolvedBlockSerialized = 
					( this.sizeOfSecureProofOfWorkMessageSolvedBlockConfidentialSerialized + 
					  this.sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized );
			
			this.secureProofOfWorkMessageSolvedBlockSerialized = 
					new byte[ this.sizeOfSecureProofOfWorkMessageSolvedBlockSerialized ];
			
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new SolvedBlock will be placed
			// 5) length - The length of the SolvedBlock to be copied from the source array to the destination array
	
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized, 0,
							 this.secureProofOfWorkMessageSolvedBlockSerialized,
							 serializationOffset, this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized.length);
			serializationOffset += this.secureProofOfWorkMessageSolvedBlockSignatureSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageSolvedBlockSignatureSerialized, 0,
					         this.secureProofOfWorkMessageSolvedBlockSerialized,
					         serializationOffset, this.secureProofOfWorkMessageSolvedBlockSignatureSerialized.length);
			
			
			this.setIsSecureProofOfWorkMessageSolvedBlockSerialized(true);
			
		}
		
	}
	
	public void buildSecureProofOfWorkMessageSolvedBlockReceived() {
		
		if(this.isSecureProofOfWorkMessageSolvedBlockSerialized) {
			
			this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized = 
					new byte[ this.sizeOfSecureProofOfWorkMessageSolvedBlockConfidentialSerialized ];
			
			this.secureProofOfWorkMessageSolvedBlockSignatureSerialized = 
					new byte[ this.sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized ];
			
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new SolvedBlock will be placed
			// 5) length - The length of the SolvedBlock to be copied from the source array to the destination array
	
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageSolvedBlockSerialized, serializationOffset,
							 this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized,
							 0, this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized.length);
			serializationOffset += this.secureProofOfWorkMessageSolvedBlockSignatureSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageSolvedBlockSerialized, serializationOffset,
							 this.secureProofOfWorkMessageSolvedBlockSignatureSerialized,
					         0, this.secureProofOfWorkMessageSolvedBlockSignatureSerialized.length);
			
			
			this.setIsSecureProofOfWorkMessageSolvedBlockSerialized(false);
			
		}
		
	}
	
	
}
