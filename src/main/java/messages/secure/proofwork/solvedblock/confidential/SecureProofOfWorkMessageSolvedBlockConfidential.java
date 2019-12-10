package main.java.messages.secure.proofwork.solvedblock.confidential;

import main.java.resources.block.Block;

public class SecureProofOfWorkMessageSolvedBlockConfidential {

	private Block block;
	
	
	private byte[] blockSerialized;
	
	private int sizeOfBlockSerialized;
	
	private boolean isBlockSerialized;
	
	
	private byte[] blockSolvedHashed;
	
	private int sizeOfBlockSolvedHashed;
	
	private boolean isBlockSolvedHashed;
	
	
	private byte[] blockSerializedAndSolvedHashed;
	
	private int sizeOfBlockSerializedAndSolvedHashed;
	
	private boolean isBlockSerializedAndSolvedHashed;
	
	
	private byte[] blockSerializedAndSolvedHashedCiphered;
	
	private int sizeOfBlockSerializedAndSolvedHashedCiphered;
	
	private boolean isBlockSerializedAndSolvedHashedCiphered;
	
	
	
	public SecureProofOfWorkMessageSolvedBlockConfidential(Block block, byte[] blockSolvedHashed) {
		
		this.block = block;
		
		
		this.block.doBlockSerialization();
		
		this.blockSerialized = this.block.getBlockSerialized();
		this.sizeOfBlockSerialized = this.blockSerialized.length;
		this.isBlockSerialized = true;
		
		
		this.blockSolvedHashed = blockSolvedHashed;
		this.sizeOfBlockSolvedHashed = this.blockSolvedHashed.length;
		this.isBlockSolvedHashed = true;
		
		
		this.blockSerializedAndSolvedHashed = null;
		this.sizeOfBlockSerializedAndSolvedHashed = 0;
		this.isBlockSerializedAndSolvedHashed = false;
		
		
		this.blockSerializedAndSolvedHashedCiphered = null;
		this.sizeOfBlockSerializedAndSolvedHashedCiphered = 0;
		this.isBlockSerializedAndSolvedHashedCiphered = false;
		
	}
	
	public SecureProofOfWorkMessageSolvedBlockConfidential(byte[] blockSerializedAndSolvedHashedCiphered,
														   int sizeOfBlockSerializedAndSolvedHashed,
														   int sizeOfBlockSolvedHashed,
														   int sizeOfBlockSerialized) {
		
		this.blockSerializedAndSolvedHashedCiphered = blockSerializedAndSolvedHashedCiphered;
		this.sizeOfBlockSerializedAndSolvedHashedCiphered = blockSerializedAndSolvedHashedCiphered.length;
		this.isBlockSerializedAndSolvedHashedCiphered = true;
		
		
		this.blockSerializedAndSolvedHashed = null;
		this.sizeOfBlockSerializedAndSolvedHashed = sizeOfBlockSerializedAndSolvedHashed;
		this.isBlockSerializedAndSolvedHashed = true;
		
		
		this.blockSolvedHashed = null;
		this.sizeOfBlockSolvedHashed = sizeOfBlockSolvedHashed;
		this.isBlockSolvedHashed = true;
		
		this.blockSerialized = null;
		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
		this.isBlockSerialized = true;
		
		this.block = null;
		
	}
	
	
	
	
	public Block getBlock() {
		return this.block;
	}
	
	
	public byte[] getBlockSerialized() {
		return this.blockSerialized;
	}
	
	public int getSizeOfBlockSerialized() {
		return this.sizeOfBlockSerialized;
	}
	
	public boolean getIsBlockSerialized() {
		return this.isBlockSerialized;
	}
	
	public void setIsBlockSerialized(boolean isBlockSerialized) {
		this.isBlockSerialized = isBlockSerialized;
	}
	
	public byte[] getBlockSolvedHashed() {
		return this.blockSolvedHashed;
	}
	
	public int getSizeOfBlockSolvedHashed() {
		return this.sizeOfBlockSolvedHashed;
	}
	
	public boolean getIsBlockSolvedHashed() {
		return this.isBlockSolvedHashed;
	}
	
	public void setIsBlockSolvedHashed(boolean isBlockSolvedHashed) {
		this.isBlockSolvedHashed = isBlockSolvedHashed;
	}
	
	
	public byte[] getBlockSerializedAndSolvedHashed() {
		return this.blockSerializedAndSolvedHashed;
	}
	
	public int getSizeOfBlockSerializedAndSolvedHashed() {
		return this.sizeOfBlockSerializedAndSolvedHashed;
	}
	
	public boolean getIsBlockSerializedAndSolvedHashed() {
		return this.isBlockSerializedAndSolvedHashed;
	}
	
	public void setIsBlockSerializedAndSolvedHashed(boolean isBlockSerializedAndSolvedHashed) {
		this.isBlockSerializedAndSolvedHashed = isBlockSerializedAndSolvedHashed;
	}
	
	public byte[] getBlockSerializedAndSolvedHashedCiphered() {
		return this.blockSerializedAndSolvedHashedCiphered;
	}
	
	public int getSizeOfBlockSerializedAndSolvedHashedCiphered() {
		return this.sizeOfBlockSerializedAndSolvedHashedCiphered;
	}
	
	public boolean getIsBlockSerializedAndSolvedHashedCiphered() {
		return this.isBlockSerializedAndSolvedHashedCiphered;
	}
	
	public void setIsBlockSerializedAndSolvedHashedCiphered(boolean isBlockSerializedAndSolvedHashedCiphered) {
		this.isBlockSerializedAndSolvedHashedCiphered = isBlockSerializedAndSolvedHashedCiphered;
	}
	
	
	
	
	
	public void buildSecureProofOfWorkMessageSolvedBlockConfidentialToSend() {
		
		boolean isPossibleToBuildSecureProofOfWorkMessageSolvedBlockConfidentialToSend = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			    !this.isBlockSerializedAndSolvedHashed && 
			    !this.isBlockSerializedAndSolvedHashedCiphered);
		
		
		if(isPossibleToBuildSecureProofOfWorkMessageSolvedBlockConfidentialToSend) {
			
			this.doBlockSerializedAndSolvedHashed();
			
			this.encryptBlockSerializedAndSolvedHashed();
			
		}
		
	}
	
	public void buildSecureProofOfWorkMessageSolvedBlockConfidentialReceived() {
		
		boolean isPossibleToBuildSecureProofOfWorkMessageSolvedBlockConfidentialReceived = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			     this.isBlockSerializedAndSolvedHashed && 
			     this.isBlockSerializedAndSolvedHashedCiphered);
	
		
		if(isPossibleToBuildSecureProofOfWorkMessageSolvedBlockConfidentialReceived) {
			
			this.decryptBlockSerializedAndSolvedHashed();

			this.undoBlockSerializedAndSolvedHashed();
			
		}
		
	}
	
	
	
	
	public void doBlockSerializedAndSolvedHashed() {
		
		boolean isPossibleToDoBlockSerializedAndSolvedHashed = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			    !this.isBlockSerializedAndSolvedHashed && 
			    !this.isBlockSerializedAndSolvedHashedCiphered);
		
		
		if(isPossibleToDoBlockSerializedAndSolvedHashed) {
			
			this.sizeOfBlockSerializedAndSolvedHashed = ( this.sizeOfBlockSerialized + this.sizeOfBlockSolvedHashed );
			
			this.blockSerializedAndSolvedHashed = 
					new byte[ this.sizeOfBlockSerializedAndSolvedHashed ];
			
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array
	
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.blockSerialized, 0,
							 this.blockSerializedAndSolvedHashed,
							 serializationOffset, this.blockSerialized.length);
			serializationOffset += this.blockSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.blockSolvedHashed, 0,
					         this.blockSerializedAndSolvedHashed,
					         serializationOffset, this.blockSerialized.length);
			
			
			this.setIsBlockSerializedAndSolvedHashed(true);
			
		}
		
		
	}
	
	public void undoBlockSerializedAndSolvedHashed() {
		
		boolean isPossibleToUndoBlockSerializedAndSolvedHashed = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			     this.isBlockSerializedAndSolvedHashed && 
			    !this.isBlockSerializedAndSolvedHashedCiphered);
			
		
		if(isPossibleToUndoBlockSerializedAndSolvedHashed) {
			
			this.blockSerialized = new byte[ this.sizeOfBlockSerialized ];
			
			this.blockSolvedHashed = new byte[ this.sizeOfBlockSolvedHashed ];
			
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array
	
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.blockSerializedAndSolvedHashed, serializationOffset,
							 this.blockSerialized, 0, this.blockSerialized.length);
			serializationOffset += this.blockSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.blockSerializedAndSolvedHashed, serializationOffset,
					         this.blockSolvedHashed, 0, this.blockSerialized.length);
			
			
			this.setIsBlockSerializedAndSolvedHashed(false);
			
		}
		
	}
	

	
	
	public void encryptBlockSerializedAndSolvedHashed() {
		
		boolean isPossibleToEncryptSerializedAndSolvedHashed = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			     this.isBlockSerializedAndSolvedHashed && 
			    !this.isBlockSerializedAndSolvedHashedCiphered);
			
		
		if(isPossibleToEncryptSerializedAndSolvedHashed) {
			
			
			
			
			this.setIsBlockSerializedAndSolvedHashedCiphered(true);
			
		}
		
	}
	
	public void decryptBlockSerializedAndSolvedHashed() {
		
		boolean isPossibleToDecryptSerializedAndSolvedHashed = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			     this.isBlockSerializedAndSolvedHashed && 
			     this.isBlockSerializedAndSolvedHashedCiphered);
				
		
		if(isPossibleToDecryptSerializedAndSolvedHashed) {
			
			
			
			
			this.setIsBlockSerializedAndSolvedHashedCiphered(false);
			
		}
		
	}
}
