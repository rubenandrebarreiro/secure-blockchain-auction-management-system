package main.java.blockchain.structure.components;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class MerkleTreeHash {

	// Global Instance Variables:
	
    /**
     * The hash value as byte array.
     */
    private byte[] hashValue;

    
    // Constructors:
	
    /**
	 * Constructor #1:
	 * - TODO
	 */
    public MerkleTreeHash() {
    	// Empty/Obsolete Constructor
    }
    
    
    // Methods:
    
    /**
     * Creates/Returns a Merkle Tree Hash from
     * an array of bytes.
     *
     * @param buffer buffer/array of bytes
     * 
     * @return a Merkle Tree Hash from an array of bytes
     */
    public static MerkleTreeHash create(byte[] byteBuffer) {
        
    	MerkleTreeHash merkleTreeHash = new MerkleTreeHash();
        merkleTreeHash.computeHash(byteBuffer);
        
        return merkleTreeHash;
    }

    /**
     * Creates/Returns a Merkle Tree Hash from a string.
     * 
     * The string needs first to be transformed in
     * a UTF8 sequence of bytes (buffer/array of bytes).
     * 
     * NOTE:
     * - It's used for hashes on leaf nodes;
     *
     * @param someString a string, which will be used to
     *        create a Merkle Tree Hash
     * 
     * @return a Merkle Tree Hash from a string
     */
    public static MerkleTreeHash create(String someString) {
        return create(someString.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Creates/Returns a Merkle Tree Hash from two Merkle Tree Hashes
     * by concatenation of the byte buffers/arrays.
     * 
     * NOTE:
     * - It's used for internal nodes;
     *
     * @param leftMerkleTreeHash a Merkle SubTree Hash
     * 
     * @param rightMerkleTreeHash a Merkle SubTree Hash
     * 
     * @return a Merkle Tree Hash from two Merkle Tree Hashes
     *         by concatenation of the byte buffers/arrays
     */
    public static MerkleTreeHash create(MerkleTreeHash leftMerkleTreeHash,
    									MerkleTreeHash rightMerkleTreeHash) {
    	
        return create(concatenate(leftMerkleTreeHash.getHashValue(),
        						  rightMerkleTreeHash.getHashValue()));
    }

    /**
     * Returns the byte buffer/array of a Merkle Tree Hash.
     *
     * @return the byte buffer/array of a Merkle Tree Hash
     */
    public byte[] getHashValue() {
        return this.hashValue;
    }

    /**
     * Compares the Merkle Tree Hash with a given byte buffer/array.
     *
     * @param hash Merkle Tree Hash as byte buffer/array
     * 
     * @return true if the Merkle Tree Hash and a given byte buffer/array are equal
     *         and not, otherwise
     */
    public boolean equals(byte[] hashByteArray) {
    	
        return Arrays.equals(this.hashValue, hashByteArray);
    }

    /**
     * Compares the value of a Merkle Tree Hash with the value of another Merkle Tree Hash.
     *
     * @param merkleTreeHash a Merkle Tree Hash to be compared
     * 
     * @return true if the Merkle Tree Hash and the value of the other Merkle Tree Hash are equal
     *         and not, otherwise
     */
    public boolean equals(MerkleTreeHash merkleTreeHash) {
       
    	boolean validationResult = false;
        
        if (merkleTreeHash != null) {
        	validationResult = Arrays.equals(this.hashValue, merkleTreeHash.getHashValue());
        }
        
        return validationResult;
    }
    
    /**
     * Returns the Hash Code of the Merkle Tree Hash.
     * 
     * @return the Hash Code of the Merkle Tree Hash
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.hashValue);
    }

    /**
     * Encodes/Returns the value of the Merkle Tree Hash, in the Base64 (i.e., string format).
     *
     * @return the value of the Merkle Tree Hash, in the Base64 (i.e., string format)
     */
    @Override
    public String toString() {
        return Base64.getEncoder().encodeToString(this.hashValue);
    }

    /**
     * Computes/Returns the SHA256 Hash of a byte buffer/array.
     *
     * @param byteBuffer byte buffer/array
     */
    private void computeHash(byte[] byteBuffer) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
          
            this.hashValue = messageDigest.digest(byteBuffer);
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException) {
        	noSuchAlgorithmException.printStackTrace();
        }
    }

    /**
     * Concatenates two byte buffers/arrays and return the result.
     *
     * @param firstByteBuffer the 1st byte buffer/array
     * 
     * @param secondByteBuffer the 2nd byte buffer/array
     * 
     * @return the result of the two byte buffers/arrays concatenated
     */
    public static byte[] concatenate(byte[] firstByteBuffer, byte[] secondByteBuffer) {
    	
        byte[] concatenatedByteBuffer = new byte[firstByteBuffer.length + secondByteBuffer.length];
        
        System.arraycopy(firstByteBuffer, 0, concatenatedByteBuffer, 0, firstByteBuffer.length);
        System.arraycopy(secondByteBuffer, 0, concatenatedByteBuffer, firstByteBuffer.length, secondByteBuffer.length);
        
        return concatenatedByteBuffer;
    }
}