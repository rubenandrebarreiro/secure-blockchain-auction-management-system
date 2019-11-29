package main.java.messages.secure.challenge;

import main.java.messages.secure.challenge.block.ChallengeMessageBlock;
import main.java.messages.secure.challenge.metaheader.ChallengeMessageMetaHeader;

public class ChallengeMessage {
	
	private ChallengeMessageMetaHeader challengeMessageMetaHeader;
	
	private ChallengeMessageBlock challengeMessageBlock;
	
	
	public ChallengeMessage(ChallengeMessageMetaHeader challengeMessageMetaHeader, ChallengeMessageBlock challengeMessageBlock) {
		
		this.challengeMessageMetaHeader = challengeMessageMetaHeader;
		this.challengeMessageBlock = challengeMessageBlock;
		
	}
	
	public ChallengeMessageMetaHeader getChallengeMessageMetaHeader() {
		return this.challengeMessageMetaHeader;
	}
	
	public void setChallengeMessageMetaHeader(ChallengeMessageMetaHeader challengeMessageMetaHeader) {
		this.challengeMessageMetaHeader = challengeMessageMetaHeader;
	}
	
	public ChallengeMessageBlock getChallengeMessageBlock() {
		return this.challengeMessageBlock;
	}
	
	public void setChallengeMessageBlock(ChallengeMessageBlock challengeMessageBlock) {
		this.challengeMessageBlock = challengeMessageBlock;
	}
	
}
