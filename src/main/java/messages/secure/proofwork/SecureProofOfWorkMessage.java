package main.java.messages.secure.proofwork;

import main.java.messages.secure.common.key.exchange.SecureCommonKeyExchange;
import main.java.messages.secure.proofwork.dos.mitigation.SecureProofOfWorkMessageDoSMitigation;
import main.java.messages.secure.proofwork.metaheader.SecureProofOfWorkMessageMetaHeader;
import main.java.messages.secure.proofwork.solvedblock.SecureProofOfWorkMessageSolvedBlock;

public class SecureProofOfWorkMessage {
	
	private SecureProofOfWorkMessageMetaHeader secureProofOfWorkMessageMetaHeader;
	
	private String userPeerID;
	
	private SecureCommonKeyExchange secureProofOfWorkMessageKeyExchange;
	
	private SecureProofOfWorkMessageSolvedBlock secureProofOfWorkMessageSolvedBlock;
	
	private SecureProofOfWorkMessageDoSMitigation secureProofOfWorkMessageDoSMitigation;
	
	private byte[] secureProofOfWorkMessageSerialized;
	
	private boolean isSecureProofOfWorkMessageSerialized;
	
	
	
}