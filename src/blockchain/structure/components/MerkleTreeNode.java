package blockchain.structure.components;

import java.security.InvalidParameterException;
import java.util.Objects;

public class MerkleTreeNode {
    
	// Global Instance Variables:
	
	/**
	 * The Merkle Tree Hash of the Merkle Tree Node.
	 */
	private MerkleTreeHash merkleTreeHash;
    
	/**
	 * The left Merkle Tree Node of the Merkle Tree Node.
	 */
	private MerkleTreeNode leftMerkleTreeNode;
    
	/**
	 * The right Merkle Tree Node of the Merkle Tree Node.
	 */
	private MerkleTreeNode rightMerkleTreeNode;
    
	/**
	 * The parent Merkle Tree Node of the Merkle Tree Node.
	 */
	private MerkleTreeNode parentMerkleTreeNode;

	
	// Constructors:
	
    /**
	 * Constructor #1:
	 * - TODO
	 */
    public MerkleTreeNode() {
    	// Empty/Obsolete Constructor
    }

    /**
     * Constructor #2:
     * - TODO
     * 
     * @param merkleTreeHash the Merkle Tree Hash of the Merkle Tree Node
     */
    public MerkleTreeNode(MerkleTreeHash merkleTreeHash) {
        this.merkleTreeHash = merkleTreeHash;
    }

    public MerkleTreeNode(MerkleTreeNode leftMerkleTreeNode, MerkleTreeNode rightMerkleTreeNode) {
        this.leftMerkleTreeNode = leftMerkleTreeNode;
        this.rightMerkleTreeNode = rightMerkleTreeNode;
        
        this.leftMerkleTreeNode.parentMerkleTreeNode = this;
        
        if (this.rightMerkleTreeNode != null) {
        	this.rightMerkleTreeNode.parentMerkleTreeNode = this;
        }

        this.computeHash();
    }

    public boolean isLeafNode() {
        return ( (this.leftMerkleTreeNode == null) && 
        		 (this.rightMerkleTreeNode == null) );
    }

    @Override
    public String toString() {
        return this.merkleTreeHash.toString();
    }

    public void setLeftNode(MerkleTreeNode merkleTreeNode) {
    	
        if (merkleTreeNode.merkleTreeHash == null) {
            throw new InvalidParameterException("The hash value of the node of the Merkle Tree must be initialized!!!");
        }

        this.leftMerkleTreeNode = merkleTreeNode;
        this.leftMerkleTreeNode.parentMerkleTreeNode = this;

        this.computeHash();
    }

    public void setRightNode(MerkleTreeNode merkleTreeNode) {
       
    	if (merkleTreeNode.merkleTreeHash == null) {
            throw new InvalidParameterException("The hash value of the node of the Merkle Tree must be initialized!!!");
        }

        this.rightMerkleTreeNode = merkleTreeNode;
        this.rightMerkleTreeNode.parentMerkleTreeNode = this;

        if (this.leftMerkleTreeNode != null) {
           this.computeHash();
        }
    }

    public boolean canVerifyHash() {
        return ( (this.leftMerkleTreeNode != null && this.rightMerkleTreeNode != null) || 
        		 (this.leftMerkleTreeNode != null) );
    }

    public boolean verifyHash() {
        
    	if (this.leftMerkleTreeNode == null && this.rightMerkleTreeNode == null) {
        	return true;
        }
        
        if (this.rightMerkleTreeNode == null) {
        	return this.merkleTreeHash.equals(this.leftMerkleTreeNode.merkleTreeHash);
        }

        if (this.leftMerkleTreeNode == null) {
            throw new InvalidParameterException
            				("Left branch of the Merkle Tree must be a node if the right branch is a node!!!");
        }

        MerkleTreeHash leftAndRightMerkleTreeHash = MerkleTreeHash
        											.create(this.leftMerkleTreeNode.merkleTreeHash,
        													this.rightMerkleTreeNode.merkleTreeHash);
        
        return this.merkleTreeHash.equals(leftAndRightMerkleTreeHash);
    }

    public boolean equals(MerkleTreeNode otherMerkleTreeNode) {
        return this.merkleTreeHash.equals(otherMerkleTreeNode.merkleTreeHash);
    }

    public MerkleTreeHash getMerkleTreeHash() {
        return this.merkleTreeHash;
    }

    public MerkleTreeNode getParentMerkleTreeNode() {
        return this.parentMerkleTreeNode;
    }

    public MerkleTreeNode getLeftMerkleTreeNode() {
        return this.leftMerkleTreeNode;
    }

    public MerkleTreeNode getRightMerkleTreeNode() {
        return this.rightMerkleTreeNode;
    }

    public void computeHash() {
        if (this.rightMerkleTreeNode == null) {
            this.merkleTreeHash = this.leftMerkleTreeNode.merkleTreeHash;
        }
        else {
            this.merkleTreeHash = MerkleTreeHash
            					 .create(MerkleTreeHash
            							 .concatenate(this.leftMerkleTreeNode.merkleTreeHash.getHashValue(),
            									 	  this.rightMerkleTreeNode.merkleTreeHash.getHashValue()));
        }

        if (this.parentMerkleTreeNode != null) {
            this.parentMerkleTreeNode.computeHash();
        }
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.merkleTreeHash,
        					this.leftMerkleTreeNode, this.rightMerkleTreeNode,
        					this.parentMerkleTreeNode);
    }
}