package main.java.common.protocols;

public enum NumBytesChallengeType {

	// Enumerations:
	/**
	 * The several Enumerations for the Number of Bytes for the Challenge's Type
	 */
	NUM_BYTES_CHALLENGE_TYPE_1((byte) 0x01), NUM_BYTES_CHALLENGE_TYPE_2((byte) 0x02),
	NUM_BYTES_CHALLENGE_TYPE_3((byte) 0x03), NUM_BYTES_CHALLENGE_TYPE_4((byte) 0x04);
	
	
	// Global Instance Variables:
	/**
	 * The Number of Bytes for the Challenge's Type
	 */
	private byte numBytesChallengeType;
	
	
	// Constructors:
	/**
	 * Constructor #1:
	 * - The Constructor for the Number of Bytes for the Challenge's Type.
	 * 
	 * @param numBytesChallengeType the Number of Bytes for the Challenge's Type
	 */
	private NumBytesChallengeType(byte numBytesChallengeType) {
		this.numBytesChallengeType = numBytesChallengeType;
	}
	
	
	// Methods/Functions:
	/**
	 * Returns the Number of Bytes for the Challenge's Type.
	 * 
	 * @return the Number of Bytes for the Challenge's Type
	 */
	public byte getNumBytesChallengeType() {
		return this.numBytesChallengeType;
	}
	
}
