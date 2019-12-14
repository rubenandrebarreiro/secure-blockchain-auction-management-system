package main.java.messages.secure.proofwork.components;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.common.header.SecureCommonHeader;
import main.java.messages.secure.proofwork.components.solvedblock.SecureProofOfWorkMessageComponentsSolvedBlock;

public class SecureProofOfWorkMessageComponents {
	
	
	private SecureCommonHeader secureCommonHeader;
	
	private byte[] secureCommonHeaderSerialized;
	
	private boolean isSecureCommonHeaderSerialized;
	
	
	
	private SecureProofOfWorkMessageComponentsSolvedBlock
										secureProofOfWorkMessageComponentsSolvedBlock;
	
	private byte[] secureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	
	private byte[] secureProofOfWorkMessageComponentsSerialized;
	
	private boolean isSecureProofOfWorkMessageComponentsSerialized;
	
	
	
	public SecureProofOfWorkMessageComponents(SecureCommonHeader secureCommonHeader,
											  SecureProofOfWorkMessageComponentsSolvedBlock 
											  				secureProofOfWorkMessageComponentsSolvedBlock) {
		
		this.secureCommonHeader = secureCommonHeader;
		
		this.secureCommonHeader
			.doSecureCommonHeaderSerialization();
		this.secureCommonHeaderSerialized = this.secureCommonHeader.getSecureCommonHeaderSerialized();
		
		this.isSecureCommonHeaderSerialized = true;
		
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlock = secureProofOfWorkMessageComponentsSolvedBlock;
		
		this.secureProofOfWorkMessageComponentsSolvedBlock
			.doSecureProofOfWorkMessageComponentsSolvedBlockSerialization();
		this.secureProofOfWorkMessageComponentsSolvedBlockSerialized = 
					this.secureProofOfWorkMessageComponentsSolvedBlock
						.getSecureProofOfWorkMessageComponentsSolvedBlockSerialized();
		
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
				this.secureProofOfWorkMessageComponentsSolvedBlockSerialized.length;
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized = true;
		
		
		this.secureProofOfWorkMessageComponentsSerialized = null;

		this.isSecureProofOfWorkMessageComponentsSerialized = false;
			
	}
	
	
	
	
	public SecureCommonHeader getSecureCommonHeader() {
		return this.secureCommonHeader;
	}
	
	public byte[] getSecureCommonHeaderSerialized() {
		return this.secureCommonHeaderSerialized;
	}
	
	public boolean getIsSecureCommonHeaderSerialized() {
		return this.isSecureCommonHeaderSerialized;
	}
	
	public void setIsSecureCommonHeaderSerialized(boolean isSecureCommonHeaderSerialized) {
		this.isSecureCommonHeaderSerialized = isSecureCommonHeaderSerialized;
	}
	
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlock
								getSecureProofOfWorkMessageComponentsSolvedBlock() {
		
		return this.secureProofOfWorkMessageComponentsSolvedBlock;
		
	}
	
	public byte[] getSecureProofOfWorkMessageComponentsSolvedBlockSerialized() {
		return this.secureProofOfWorkMessageComponentsSolvedBlockSerialized;
	}
	
	public int getSizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized() {
		return this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	}
		
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockSerialized() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockSerialized
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockSerialized) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
				isSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	}
	
	
	
	public byte[] getSecureProofOfWorkMessageComponentsSerialized() {
		return this.secureProofOfWorkMessageComponentsSolvedBlockSerialized;
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSerialized() {
		return this.isSecureProofOfWorkMessageComponentsSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSerialized
		  (boolean isSecureProofOfWorkMessageComponentsSerialized) {
		
		this.isSecureProofOfWorkMessageComponentsSerialized = 
				isSecureProofOfWorkMessageComponentsSerialized;
	}
	
	
	
	
	
	public void doSecureProofOfWorkMessageComponentsSerialization() {
		
		boolean isPossibleToBuildSecureProofOfWorkMessageComponents = 
				( this.isSecureCommonHeaderSerialized && 
				  this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized );
	
		
		if(isPossibleToBuildSecureProofOfWorkMessageComponents) {
		
			int sizeOfSecureProofOfWorkMessageComponentsSerialized =
					( CommonUtils.COMMON_HEADER_LENGTH + 
					  this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized );
			
			this.secureProofOfWorkMessageComponentsSerialized =
					new byte[ sizeOfSecureProofOfWorkMessageComponentsSerialized ];
			
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
			System.arraycopy(this.secureCommonHeaderSerialized, 0,
							 this.secureProofOfWorkMessageComponentsSerialized,
							 serializationOffset, this.secureCommonHeaderSerialized.length);
			serializationOffset += this.secureCommonHeaderSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageComponentsSolvedBlockSerialized, 0,
					         this.secureProofOfWorkMessageComponentsSerialized,
					         serializationOffset, this.secureProofOfWorkMessageComponentsSolvedBlockSerialized.length);
			
			
			this.setIsSecureProofOfWorkMessageComponentsSerialized(true);
		
		}
				
	}
	
	
}
