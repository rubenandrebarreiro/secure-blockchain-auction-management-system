package blockchain.structure.utils;

import blockchain.structure.components.MerkleTreeHash;

public class MerkleTreeProofHash {
	
	public enum Branch {
        LEFT,
        RIGHT,
        OLD_ROOT
    }

	
    public MerkleTreeHash merkleTreeHash;
    
    public Branch branchDirection;

    public MerkleTreeProofHash(MerkleTreeHash merkleTreeHash, Branch branchDirection) {
        this.merkleTreeHash = merkleTreeHash;
        this.branchDirection = branchDirection;
    }

    public MerkleTreeHash getHash() {
        return this.merkleTreeHash;
    }

    public Branch getDirection() {
        return this.branchDirection;
    }

    @Override
    public String toString() {
        String merkleTreeHashString = this.merkleTreeHash.toString();
        String branchDirectionString = this.branchDirection.toString();
        
        return String.format("%s is %s Child", merkleTreeHashString, branchDirectionString);
     }
}