package main.java.messages.secure.proofwork.components.solvedblock;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import main.java.messages.secure.proofwork.components.solvedblock.info.SecureProofOfWorkMessageComponentsSolvedBlockInfo;
import main.java.messages.secure.proofwork.components.solvedblock.signature.SecureProofOfWorkMessageComponentsSolvedBlockSignature;

public class SecureProofOfWorkMessageComponentsSolvedBlock {
	
	private SecureProofOfWorkMessageComponentsSolvedBlockInfo 
			secureProofOfWorkMessageComponentsSolvedBlockInfo;
	
	private SecureProofOfWorkMessageComponentsSolvedBlockSignature 
			secureProofOfWorkMessageComponentsSolvedBlockSignature;
	
	
	private byte[] secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
	
	
	private int sizeOfBlockSerialized;
	
	private int sizeOfBlockSolvedHashedSerialized;
	
	
	private byte[] secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized;
	
	
	private byte[] secureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	
	private String userPeerID;
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlock
	      (SecureProofOfWorkMessageComponentsSolvedBlockInfo secureProofOfWorkMessageComponentsSolvedBlockInfo,
		   SecureProofOfWorkMessageComponentsSolvedBlockSignature secureProofOfWorkMessageComponentsSolvedBlockSignature)
		   throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		
		this.sizeOfBlockSerialized = 0;
		this.sizeOfBlockSolvedHashedSerialized = 0;
		
		this.secureProofOfWorkMessageComponentsSolvedBlockInfo = secureProofOfWorkMessageComponentsSolvedBlockInfo;
		this.secureProofOfWorkMessageComponentsSolvedBlockSignature = secureProofOfWorkMessageComponentsSolvedBlockSignature;

		this.secureProofOfWorkMessageComponentsSolvedBlockInfo
			.doBlockSerializedAndSolvedHashed();
		this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = 
				this.secureProofOfWorkMessageComponentsSolvedBlockInfo
					.getBlockSerializedAndSolvedHashed();
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = 
				this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized.length;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = true;
		
		this.secureProofOfWorkMessageComponentsSolvedBlockSignature.signSecureProofOfWorkMessageComponentsSolvedBlock();
		this.secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = 
				this.secureProofOfWorkMessageComponentsSolvedBlockSignature
				    .getSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned();
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = 
				secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized.length;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = true;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockSerialized = null;
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 0;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized = false;
		
	}
	
	public SecureProofOfWorkMessageComponentsSolvedBlock
	      (byte[] secureProofOfWorkMessageComponentsSolvedBlockSerialized,
	       int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized,
	       int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized,
	       int sizeOfBlockSerialized,
		   int sizeOfBlockSolvedHashedSerialized,
		   String userPeerID) {
		
		this.secureProofOfWorkMessageComponentsSolvedBlockSerialized = 
				secureProofOfWorkMessageComponentsSolvedBlockSerialized;
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
				secureProofOfWorkMessageComponentsSolvedBlockSerialized.length;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized = true;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = null;
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = 
				sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = true;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = null;
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = 
				sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = true;

		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
		this.sizeOfBlockSolvedHashedSerialized = sizeOfBlockSolvedHashedSerialized;
		
		this.secureProofOfWorkMessageComponentsSolvedBlockInfo = null;
		this.secureProofOfWorkMessageComponentsSolvedBlockSignature = null;

		this.userPeerID = userPeerID;
		
	}
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlockInfo 
		   getSecureProofOfWorkMessageComponentsSolvedBlockInfo() {
		
		return this.secureProofOfWorkMessageComponentsSolvedBlockInfo;
	
	}
	
	public SecureProofOfWorkMessageComponentsSolvedBlockSignature 
		   getSecureProofOfWorkMessageComponentsSolvedBlockSignature() {
		
		return this.secureProofOfWorkMessageComponentsSolvedBlockSignature;
	
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = 
				isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
	
	}
	
	
	public int getSizeOfBlockSerialized() {
		return this.sizeOfBlockSerialized;
	}

	public int getSizeOfBlockSolvedHashedSerialized() {
		return this.sizeOfBlockSolvedHashedSerialized;
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
					( this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized + 
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
			System.arraycopy(this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized, 0,
							 this.secureProofOfWorkMessageComponentsSolvedBlockSerialized,
							 serializationOffset, this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized.length);
			serializationOffset += this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized.length;
			
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
			
			this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = 
					new byte[ this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized ];
			
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
							 this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized,
							 0, this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized.length);
			serializationOffset += this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageComponentsSolvedBlockSerialized, serializationOffset,
							 this.secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized,
					         0, this.secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized.length);
			
			
			this.secureProofOfWorkMessageComponentsSolvedBlockInfo = 
					new SecureProofOfWorkMessageComponentsSolvedBlockInfo
						(secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized,
						 this.sizeOfBlockSerialized, this.sizeOfBlockSolvedHashedSerialized);
			
			this.secureProofOfWorkMessageComponentsSolvedBlockSignature =
					new SecureProofOfWorkMessageComponentsSolvedBlockSignature
					    (secureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized,
  			    		 secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized, this.userPeerID);
			
			this.setIsSecureProofOfWorkMessageComponentsSolvedBlockSerialized(false);
			
		}
		
	}
		
}