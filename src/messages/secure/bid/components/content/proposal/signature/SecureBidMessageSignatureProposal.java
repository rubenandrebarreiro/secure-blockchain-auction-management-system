package messages.secure.bid.components.content.proposal.signature;

import resources.bid.Bid;

public class SecureBidMessageSignatureProposal {

	private Bid bid;

	private byte[] bidSerialized;
	
	private boolean isBidSerialized;
	
	private int sizeOfBidSerialized;
	
	private byte[] bidSerializedHashed;
	
	private boolean isBidSerializedHashed;
	
	private byte[] bidSerializedHashedCiphered;
	
	private boolean isBidSerializedHashedCiphered;
	
	private byte[] bidDigitalSigned;
	
	private boolean isBidDigitalSigned;
	
	
	public SecureBidMessageSignatureProposal(Bid bid) {
		
		this.bid = bid;
		
		this.bidSerialized = null;
		this.isBidSerialized = false;
		this.sizeOfBidSerialized = 0;
		
		this.bidSerializedHashed = null;
		this.isBidSerializedHashed = false;
		
		this.bidSerializedHashedCiphered = null;
		this.isBidSerializedHashedCiphered = false;
		
		this.bidDigitalSigned = null;
		this.isBidDigitalSigned = false;
		
	}
	
	public SecureBidMessageSignatureProposal(byte[] bidDigitalSigned, int sizeOfBidSerialized) {
		
		this.bidDigitalSigned = bidDigitalSigned;
		this.isBidDigitalSigned = true;
		
		this.bidSerializedHashedCiphered = null;
		this.isBidSerializedHashedCiphered = true;
		
		this.bidSerializedHashed = null;
		this.isBidSerializedHashed = true;
		
		this.bidSerialized = null;
		this.isBidSerialized = true;
		this.sizeOfBidSerialized = sizeOfBidSerialized;
		
		this.bid = null;
		
	}
	
	public Bid getBid() {
		return this.bid;
	}
	
	public void setBid(Bid bid) {
		this.bid = bid;
	}
	
	public byte[] getBidSerialized() {
		return this.bidSerialized;
	}
	
	public void setBidSerialized(byte[] bidSerialized) {
		this.bidSerialized = bidSerialized;
	}
	
	public byte[] getBidSerializedHashed() {
		return this.bidSerializedHashed;
	}
	
	
}
