package main.java.messages.secure.challenge;

import main.java.messages.secure.challenge.block.ChallengeMessageBlock;
import main.java.messages.secure.challenge.metaheader.ChallengeMessageMetaHeader;
import main.java.messages.secure.common.header.SecureCommonHeader;

public class ChallengeMessage {
	
	private ChallengeMessageMetaHeader challengeMessageMetaHeader;
	
	private SecureCommonHeader secureCommonHeader;
	
	private ChallengeMessageBlock challengeMessageBlock;
	
	private byte[] challengeMessageSerialized;
			
	
	public ChallengeMessage(ChallengeMessageMetaHeader challengeMessageMetaHeader,
			                SecureCommonHeader secureCommonHeader,
			                ChallengeMessageBlock challengeMessageBlock) {
		
		this.challengeMessageMetaHeader = challengeMessageMetaHeader;
		this.secureCommonHeader = secureCommonHeader;
		this.challengeMessageBlock = challengeMessageBlock;
		
		this.challengeMessageSerialized = null;
		
	}
	
	public ChallengeMessage(byte[] challengeMessageSerialized) {
		
		this.challengeMessageSerialized = challengeMessageSerialized;

		this.challengeMessageMetaHeader = null;
		this.secureCommonHeader = null;
		this.challengeMessageBlock = null;
		
	}
	
	public ChallengeMessageMetaHeader getChallengeMessageMetaHeader() {
		return this.challengeMessageMetaHeader;
	}
	
	public void setChallengeMessageMetaHeader(ChallengeMessageMetaHeader challengeMessageMetaHeader) {
		this.challengeMessageMetaHeader = challengeMessageMetaHeader;
	}
	
	public SecureCommonHeader getSecureCommonHeader() {
		return this.secureCommonHeader;
	}
	
	public void setSecureCommonHeader(SecureCommonHeader secureCommonHeader) {
		this.secureCommonHeader = secureCommonHeader;
	}
	
	public ChallengeMessageBlock getChallengeMessageBlock() {
		return this.challengeMessageBlock;
	}
	
	public void setChallengeMessageBlock(ChallengeMessageBlock challengeMessageBlock) {
		this.challengeMessageBlock = challengeMessageBlock;
	}
	
	public byte[] getChallengeMessageSerialized() {
		return this.challengeMessageSerialized;
	}
	
	public void setChallengeMessageSerialized(byte[] challengeMessageSerialized) {
		this.challengeMessageSerialized = challengeMessageSerialized;
	}
	
	public void doChallengeMessageSerialization() {
		// TODO
	}
	
	public void undoChallengeMessageSerialization() {
		// TODO
	}
}