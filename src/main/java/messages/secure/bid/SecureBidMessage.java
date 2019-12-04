package main.java.messages.secure.bid;

import main.java.messages.secure.bid.components.SecureBidMessageComponents;
import main.java.messages.secure.bid.dos.mitigation.SecureBidMessageDoSMitigation;
import main.java.messages.secure.bid.metaheader.SecureBidMessageMetaHeader;

public class SecureBidMessage {
	
	private SecureBidMessageMetaHeader secureBidMessageMetaHeader;
	
	private SecureBidMessageComponents secureBidMessageComponents;
	
	private SecureBidMessageDoSMitigation secureBidMessageDoSMitigation;
	
	private byte[] secureBidMessageSerialized;
	
	
	public SecureBidMessage(SecureBidMessageMetaHeader secureBidMessageMetaHeader,
							SecureBidMessageComponents secureBidMessageComponents,
							SecureBidMessageDoSMitigation secureBidMessageDoSMitigation) {
		
		this.secureBidMessageMetaHeader = secureBidMessageMetaHeader;
		this.secureBidMessageComponents = secureBidMessageComponents;
		this.secureBidMessageDoSMitigation = secureBidMessageDoSMitigation;
		
		this.secureBidMessageSerialized = null;
		
	}
	
	public SecureBidMessage(byte[] secureBidMessageSerialized) {
		
		this.secureBidMessageSerialized = secureBidMessageSerialized;
		
		this.secureBidMessageMetaHeader = null;
		this.secureBidMessageComponents = null;
		this.secureBidMessageDoSMitigation = null;
		
	}
	
	
	public SecureBidMessageMetaHeader getSecureBidMessageMetaHeader() {
		return this.secureBidMessageMetaHeader;
	}
	
	public SecureBidMessageComponents getSecureBidMessageComponents() {
		return this.secureBidMessageComponents;
	}
	
	public SecureBidMessageDoSMitigation getSecureBidMessageDoSMitigation() {
		return this.secureBidMessageDoSMitigation;
	}
	
	public byte[] getSecureBidMessageSerialized() {
		return this.secureBidMessageSerialized;
	}
	
	
	public void doSecureBidMessageSerialized() {
		
		this.secureBidMessageMetaHeader.doSecureBidMessageMetaHeaderSerialized();
		byte[] secureBidMessageMetaHeaderSerialized = 
				this.secureBidMessageMetaHeader.getSecureBidMessageMetaHeaderSerialized();
		
		 //TODO
		byte[] secureBidMessageComponentsSerialized = null;
		
		byte[] secureBidMessageDoSMitigationSerialized = null;
		
		int sizeOfSecureBidMessageSerialized = (secureBidMessageMetaHeaderSerialized.length +
												secureBidMessageComponentsSerialized.length +
												secureBidMessageDoSMitigationSerialized.length);

		this.secureBidMessageSerialized = new byte[sizeOfSecureBidMessageSerialized];
		
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
		System.arraycopy(secureBidMessageMetaHeaderSerialized, 0, this.secureBidMessageSerialized,
						 serializationOffset, secureBidMessageMetaHeaderSerialized.length);
		serializationOffset += secureBidMessageMetaHeaderSerialized.length;
	
		// Fills the byte array of the Block's Serialization with
		// the correspondent bytes from the current Bid serialized,
		// From the position corresponding to the length of the previous Bid's Serialization to
		// the position corresponding to the length of the current Bid's Serialization
		System.arraycopy(secureBidMessageComponentsSerialized, 0, this.secureBidMessageSerialized,
						 serializationOffset, secureBidMessageComponentsSerialized.length);
		serializationOffset += secureBidMessageComponentsSerialized.length;
	
		// Fills the byte array of the Block's Serialization with
		// the correspondent bytes from the current Bid serialized,
		// From the position corresponding to the length of the previous Bid's Serialization to
		// the position corresponding to the length of the current Bid's Serialization
		System.arraycopy(secureBidMessageDoSMitigationSerialized, 0, this.secureBidMessageSerialized,
						 serializationOffset, secureBidMessageDoSMitigationSerialized.length);
		
	}
	
}
