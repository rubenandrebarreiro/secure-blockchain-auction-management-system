package main.java.messages.secure.proofwork.components.solvedblock;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import main.java.messages.secure.proofwork.components.solvedblock.confidential.SecureProofOfWorkMessageComponentsSolvedBlockConfidential;
import main.java.messages.secure.proofwork.components.solvedblock.signature.SecureProofOfWorkMessageComponentsSolvedBlockSignature;

public class SecureProofOfWorkMessageComponentsSolvedBlock {
	
	private SecureProofOfWorkMessageComponentsSolvedBlockConfidential 
			secureProofOfWorkMessageComponentsSolvedBlockConfidential;
	
	private SecureProofOfWorkMessageComponentsSolvedBlockSignature 
			secureProofOfWorkMessageComponentsSolvedBlockSignature;
	
	
	private byte[] secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
	

	private byte[] secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized;
	
	
	private byte[] secureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlock
	      (SecureProofOfWorkMessageComponentsSolvedBlockConfidential secureProofOfWorkMessageComponentsSolvedBlockConfidential,
		   SecureProofOfWorkMessageComponentsSolvedBlockSignature secureProofOfWorkMessageComponentsSolvedBlockSignature)
		   throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		
		this.secureProofOfWorkMessageComponentsSolvedBlockConfidential = secureProofOfWorkMessageComponentsSolvedBlockConfidential;
		this.secureProofOfWorkMessageComponentsSolvedBlockSignature = secureProofOfWorkMessageComponentsSolvedBlockSignature;

		this.secureProofOfWorkMessageComponentsSolvedBlockConfidential
			.buildSecureProofOfWorkMessageComponentsSolvedBlockConfidentialToSend();
		this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = 
				this.secureProofOfWorkMessageComponentsSolvedBlockConfidential
					.getBlockSerializedAndSolvedHashedCiphered();
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = 
				this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized.length;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = true;
		
		this.secureProofOfWorkMessageComponentsSolvedBlockSignature.signSecureProofOfWorkMessageComponentsSolvedBlock();
		this.secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = 
				this.secureProofOfWorkMessageComponentsSolvedBlockSignature
				    .getSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned();
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = 
				secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized.length;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = true;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockSerialized = null;
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 0;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized = false;
		
	}
	
	public SecureProofOfWorkMessageComponentsSolvedBlock
	      (byte[] secureProofOfWorkMessageComponentsSolvedBlockSerialized,
	       int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized,
	       int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized) {
		
		this.secureProofOfWorkMessageComponentsSolvedBlockSerialized = 
				secureProofOfWorkMessageComponentsSolvedBlockSerialized;
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
				secureProofOfWorkMessageComponentsSolvedBlockSerialized.length;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized = true;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = null;
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = 
				sizeOfSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = true;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = null;
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = 
				sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = true;

		
		this.secureProofOfWorkMessageComponentsSolvedBlockConfidential = null;
		this.secureProofOfWorkMessageComponentsSolvedBlockSignature = null;

		
	}
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlockConfidential 
		   getSecureProofOfWorkMessageComponentsSolvedBlockConfidential() {
		
		return this.secureProofOfWorkMessageComponentsSolvedBlockConfidential;
	
	}
	
	public SecureProofOfWorkMessageComponentsSolvedBlockSignature 
		   getSecureProofOfWorkMessageComponentsSolvedBlockSignature() {
		
		return this.secureProofOfWorkMessageComponentsSolvedBlockSignature;
	
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = 
				isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
	
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized
	      (boolean isSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = 
				isSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized;
	
	}
	

	public byte[] getSecureProofOfWorkMessageComponentsSolvedBlockSerialized() {
		return this.secureProofOfWorkMessageComponentsSolvedBlockSerialized;
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockSerialized() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockSerialized
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockSerialized) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
				isSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	}
	
	
	
	public void doSecureProofOfWorkMessageComponentsSolvedBlockSerialization() {
		
		if(!this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized) {
			
			this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
					( this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized + 
					  this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized );
			
			this.secureProofOfWorkMessageComponentsSolvedBlockSerialized = 
					new byte[ this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized ];
			
			
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
			System.arraycopy(this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized, 0,
							 this.secureProofOfWorkMessageComponentsSolvedBlockSerialized,
							 serializationOffset, this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized.length);
			serializationOffset += this.secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized, 0,
					         this.secureProofOfWorkMessageComponentsSolvedBlockSerialized,
					         serializationOffset, this.secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized.length);
			
			
			this.setIsSecureProofOfWorkMessageComponentsSolvedBlockSerialized(true);
			
		}	
	}
	
	
	public void undoSecureProofOfWorkMessageSolvedBlockSerialization() {
		
		if(this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized) {
			
			this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = 
					new byte[ this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized ];
			
			this.secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = 
					new byte[ this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized ];
			
			
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
			System.arraycopy(this.secureProofOfWorkMessageComponentsSolvedBlockSerialized, serializationOffset,
							 this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized,
							 0, this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized.length);
			serializationOffset += this.secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageComponentsSolvedBlockSerialized, serializationOffset,
							 this.secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized,
					         0, this.secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized.length);
			
			
			this.setIsSecureProofOfWorkMessageComponentsSolvedBlockSerialized(false);
			
		}
		
	}
		
}