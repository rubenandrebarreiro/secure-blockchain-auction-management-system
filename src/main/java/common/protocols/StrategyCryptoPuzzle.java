package main.java.common.protocols;

public enum StrategyCryptoPuzzle {

	// Enumerations:
	/**
	 * The several Enumerations for the Strategies used for Crypto Puzzle
	 */
	STRATEGY_CRYPTO_PUZZLE_1((byte) 0x01), STRATEGY_CRYPTO_PUZZLE_2((byte) 0x02),
	STRATEGY_CRYPTO_PUZZLE_3((byte) 0x03);
	
	
	// Global Instance Variables:
	/**
	 * The Strategy used for Crypto Puzzle
	 */
	private byte strategyUsedForCryptoPuzzle;
	
	
	// Constructors:
	/**
	 * Constructor #1:
	 * - The Constructor for the Strategy used for Crypto Puzzle.
	 * 
	 * @param strategyUsedForCryptoPuzzle the Strategy used for Crypto Puzzle
	 */
	private StrategyCryptoPuzzle(byte strategyUsedForCryptoPuzzle) {
		this.strategyUsedForCryptoPuzzle = strategyUsedForCryptoPuzzle;
	}
	
	
	// Methods/Functions:
	/**
	 * Returns the Strategy used for Crypto Puzzle.
	 * 
	 * @return the Strategy used for Crypto Puzzle
	 */
	public byte getStrategyUsedForCryptoPuzzle() {
		return this.strategyUsedForCryptoPuzzle;
	}
	
}
