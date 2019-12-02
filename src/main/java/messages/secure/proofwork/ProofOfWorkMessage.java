package main.java.messages.secure.proofwork;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.NoSuchPaddingException;

import main.java.messages.secure.common.header.SecureCommonHeader;
import main.java.messages.secure.proofwork.metaheader.ProofOfWorkMessageMetaHeader;
import main.java.messages.secure.proofwork.solvedblock.ProofOfWorkMessageSolvedBlockSignature;

public class ProofOfWorkMessage {
	
	private ProofOfWorkMessageMetaHeader proofOfWorkMessageMetaHeader;
	
	private int sizeOfProofOfWorkMessageMetaHeader;
	
	
	private SecureCommonHeader secureCommonHeader;
	
	private int sizeOfSecureCommonHeader;
	
	
	private ProofOfWorkMessageSolvedBlockSignature proofOfWorkMessageSolvedBlockSignature;
	
	private int sizeOfProofOfWorkMessageSolvedBlockSignature;
	
	
	private byte[] proofOfWorkMessageSerialized;
	
	
	
	public ProofOfWorkMessage(ProofOfWorkMessageMetaHeader proofOfWorkMessageMetaHeader,
			                  SecureCommonHeader secureCommonHeader,
			                  ProofOfWorkMessageSolvedBlockSignature proofOfWorkMessageSolvedBlockSignature)
			                  throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
			                         NoSuchPaddingException, InvalidAlgorithmParameterException {
		
		this.proofOfWorkMessageMetaHeader = proofOfWorkMessageMetaHeader;
		this.proofOfWorkMessageMetaHeader.doProofOfWorkMessageMetaHeaderSerialized();
		this.sizeOfProofOfWorkMessageMetaHeader = this.proofOfWorkMessageMetaHeader
													  .getProofOfWorkMessageMetaHeaderSerialized().length;
		
		this.secureCommonHeader = secureCommonHeader;
		this.sizeOfSecureCommonHeader = this.secureCommonHeader.getSecureCommonHeaderSerialized().length;
		
		this.proofOfWorkMessageSolvedBlockSignature = proofOfWorkMessageSolvedBlockSignature;
		this.proofOfWorkMessageSolvedBlockSignature.buildProofOfWorkMessageSolvedBlockSignatureToSend();
		this.sizeOfProofOfWorkMessageSolvedBlockSignature = this.getProofOfWorkMessageSolvedBlockSignature()
											   					.getProofOfWorkDigitalSigned().length;
		
		this.proofOfWorkMessageSerialized = null;
		
	}
	
	public ProofOfWorkMessage(byte[] proofOfWorkMessageSerialized,
							  int sizeOfProofOfWorkMessageMetaHeader,
							  int sizeOfSecureCommonHeader,
							  int sizeOfProofOfWorkMessageSolvedBlockSignature) {
		
		this.proofOfWorkMessageSerialized = proofOfWorkMessageSerialized;

		this.proofOfWorkMessageMetaHeader = null;
		this.sizeOfProofOfWorkMessageMetaHeader = sizeOfProofOfWorkMessageMetaHeader;
		
		this.secureCommonHeader = null;
		this.sizeOfSecureCommonHeader = sizeOfSecureCommonHeader;
		
		this.proofOfWorkMessageSolvedBlockSignature = null;
		this.sizeOfProofOfWorkMessageSolvedBlockSignature = sizeOfProofOfWorkMessageSolvedBlockSignature;
		
	}
	
	
	
	public ProofOfWorkMessageMetaHeader getProofOfWorkMessageMetaHeader() {
		return this.proofOfWorkMessageMetaHeader;
	}
	
	public void setProofOfWorkMessageMetaHeader(ProofOfWorkMessageMetaHeader proofOfWorkMessageMetaHeader) {
		this.proofOfWorkMessageMetaHeader = proofOfWorkMessageMetaHeader;
	}
	
	public int getSizeOfProofOfWorkMessageMetaHeader() {
		return this.sizeOfProofOfWorkMessageMetaHeader;
	}

	public void setSizeOfProofOfWorkMessageMetaHeader(int sizeOfProofOfWorkMessageMetaHeader) {
		this.sizeOfProofOfWorkMessageMetaHeader = sizeOfProofOfWorkMessageMetaHeader;
	}
	
	public SecureCommonHeader getSecureCommonHeader() {
		return this.secureCommonHeader;
	}
	
	public void setSecureCommonHeader(SecureCommonHeader secureCommonHeader) {
		this.secureCommonHeader = secureCommonHeader;
	}
	
	public int getSizeOfSecureCommonHeader() {
		return sizeOfSecureCommonHeader;
	}

	public void setSizeOfSecureCommonHeader(int sizeOfSecureCommonHeader) {
		this.sizeOfSecureCommonHeader = sizeOfSecureCommonHeader;
	}
	
	public ProofOfWorkMessageSolvedBlockSignature getProofOfWorkMessageSolvedBlockSignature() {
		return this.proofOfWorkMessageSolvedBlockSignature;
	}
	
	public void setProofOfWorkMessageSolvedBlockSignature
		  	   (ProofOfWorkMessageSolvedBlockSignature proofOfWorkMessageSolvedBlockSignature) {
		
		this.proofOfWorkMessageSolvedBlockSignature = proofOfWorkMessageSolvedBlockSignature;
		
	}
	
	public int getSizeOfProofOfWorkMessageSolvedBlockSignature() {
		return this.sizeOfProofOfWorkMessageSolvedBlockSignature;
	}

	public void setSizeOfProofOfWorkMessageSolvedBlockSignature(int sizeOfProofOfWorkMessageSolvedBlockSignature) {
		this.sizeOfProofOfWorkMessageSolvedBlockSignature = sizeOfProofOfWorkMessageSolvedBlockSignature;
	}
	
	public byte[] getProofOfWorkMessageSerialized() {
		return this.proofOfWorkMessageSerialized;
	}
	
	public void setProofOfWorkMessageSerialized(byte[] proofOfWorkMessageSerialized) {
		this.proofOfWorkMessageSerialized = proofOfWorkMessageSerialized;
	}
	
	public void doProofOfWorkMessageSerialization() {
		
		byte[] proofOfWorkMessageMetaHeaderSerialized = this.proofOfWorkMessageMetaHeader
														    .getProofOfWorkMessageMetaHeaderSerialized();
		
		byte[] secureCommonHeaderSerialized = this.secureCommonHeader.getSecureCommonHeaderSerialized();
		
		byte[] proofOfWorkMessageSolvedBlockSignatureSerialized = this.proofOfWorkMessageSolvedBlockSignature.getProofOfWorkDigitalSigned();
		
		
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
		System.arraycopy(proofOfWorkMessageMetaHeaderSerialized, 0, this.proofOfWorkMessageSerialized, serializationOffset, proofOfWorkMessageMetaHeaderSerialized.length);
		serializationOffset += proofOfWorkMessageMetaHeaderSerialized.length;
		
		// Fills the byte array of the Block's Serialization with
		// the correspondent bytes from the current Bid serialized,
		// From the position corresponding to the length of the previous Bid's Serialization to
		// the position corresponding to the length of the current Bid's Serialization
		System.arraycopy(secureCommonHeaderSerialized, 0, this.proofOfWorkMessageSerialized, serializationOffset, secureCommonHeaderSerialized.length);
		serializationOffset += secureCommonHeaderSerialized.length;
		
		// Fills the byte array of the Block's Serialization with
		// the correspondent bytes from the current Bid serialized,
		// From the position corresponding to the length of the previous Bid's Serialization to
		// the position corresponding to the length of the current Bid's Serialization
		System.arraycopy(proofOfWorkMessageSolvedBlockSignatureSerialized, 0, this.proofOfWorkMessageSerialized, serializationOffset, proofOfWorkMessageSolvedBlockSignatureSerialized.length);
		
	}
	
	public void undoProofOfWorkMessageSerialization() {
		
		byte[] proofOfWorkMessageMetaHeaderSerialized = new byte[this.sizeOfProofOfWorkMessageMetaHeader];

		byte[] secureCommonHeaderSerialized = new byte[this.sizeOfSecureCommonHeader];
		
		byte[] proofOfWorkMessageSolvedBlockSignatureSerialized = new byte[this.sizeOfProofOfWorkMessageSolvedBlockSignature];
		
		
		int sizeOfProofOfWorkMessageSerialized = ( this.sizeOfProofOfWorkMessageMetaHeader + 
												 this.sizeOfSecureCommonHeader + 
												 this.sizeOfProofOfWorkMessageSolvedBlockSignature );
		
		this.proofOfWorkMessageSerialized = new byte[ sizeOfProofOfWorkMessageSerialized ];
		
		
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
		System.arraycopy(this.proofOfWorkMessageSerialized, serializationOffset, proofOfWorkMessageMetaHeaderSerialized, 0, proofOfWorkMessageMetaHeaderSerialized.length);
		serializationOffset += proofOfWorkMessageMetaHeaderSerialized.length;
		
		// Fills the byte array of the Block's Serialization with
		// the correspondent bytes from the current Bid serialized,
		// From the position corresponding to the length of the previous Bid's Serialization to
		// the position corresponding to the length of the current Bid's Serialization
		System.arraycopy(this.proofOfWorkMessageSerialized, serializationOffset, secureCommonHeaderSerialized, 0, secureCommonHeaderSerialized.length);
		serializationOffset += secureCommonHeaderSerialized.length;
		
		// Fills the byte array of the Block's Serialization with
		// the correspondent bytes from the current Bid serialized,
		// From the position corresponding to the length of the previous Bid's Serialization to
		// the position corresponding to the length of the current Bid's Serialization
		System.arraycopy(this.proofOfWorkMessageSerialized, serializationOffset, proofOfWorkMessageSolvedBlockSignatureSerialized, 0, proofOfWorkMessageSolvedBlockSignatureSerialized.length);
		
	}
}