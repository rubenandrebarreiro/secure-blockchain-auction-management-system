package main.java.blockchain.structure;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import main.java.blockchain.structure.utils.MerkleTreeProofHash;
import main.java.blockchain.structure.components.MerkleTreeHash;
import main.java.blockchain.structure.components.MerkleTreeNode;

public class MerkleTree {

	private MerkleTreeNode merkleTreeRoot;
    
	private List<MerkleTreeNode> merkleTreeInternalNodes;
    
	private List<MerkleTreeNode> merkleTreeLeafNodes;

	
    public MerkleTree() {
        this.merkleTreeInternalNodes = new ArrayList<>();
        this.merkleTreeLeafNodes = new ArrayList<>();
    }

    public List<MerkleTreeNode> getMerkleTreeInternalNodes() {
        return this.merkleTreeInternalNodes;
    }
    
    public List<MerkleTreeNode> getMerkleTreeLeafNodes() {
        return this.merkleTreeLeafNodes;
    }
    public MerkleTreeNode getMerkleTreeRoot() {
        return this.merkleTreeRoot;
    }

    public MerkleTreeNode appendMerkleTreeLeafNode(MerkleTreeNode merkleTreeNode) {
        this.merkleTreeInternalNodes.add(merkleTreeNode);
        this.merkleTreeLeafNodes.add(merkleTreeNode);
    
        return merkleTreeNode;
    }

    public void appendMerkleTreeLeafNodes(MerkleTreeNode[] merkleTreeNodes) {
        for (MerkleTreeNode merkleTreeNode : merkleTreeNodes) {
            this.appendMerkleTreeLeafNode(merkleTreeNode);
        }
    }

    public MerkleTreeNode appendMerkleTreeLeafNode(MerkleTreeHash merkleTreeHash) {
        return this.appendMerkleTreeLeafNode(new MerkleTreeNode(merkleTreeHash));
    }

    public List<MerkleTreeNode> appendMerkleTreeLeafNodes(MerkleTreeHash[] merkleTreeHashes) {
        List<MerkleTreeNode> merkleTreeNodes = new ArrayList<>();
       
        for (MerkleTreeHash merkleTreeHash : merkleTreeHashes) {
        	merkleTreeNodes.add(this.appendMerkleTreeLeafNode(merkleTreeHash));
        }
        
        return merkleTreeNodes;
    }

    public MerkleTreeHash addMerkleTree(MerkleTree merkleTree) {
        
    	if(this.merkleTreeLeafNodes.size() <= 0) {
        	throw new InvalidParameterException("Cannot add a Merkle Tree to another Merkle Tree with no leafs!!!");
        }
        
    	merkleTree.getMerkleTreeLeafNodes().forEach(this::appendMerkleTreeLeafNode);
        
        return this.buildMerkleTree();
    }

    public MerkleTreeHash buildMerkleTree() {
        
    	if (this.merkleTreeLeafNodes.size() <= 0) {
    		throw new InvalidParameterException("Cannot build a Merkle Tree from another Merkle Tree with no leafs!!!");
    	}
    	
    	this.buildMerkleTree(this.merkleTreeLeafNodes);
        
    	return this.merkleTreeRoot.getMerkleTreeHash();
    }

    public void buildMerkleTree(List<MerkleTreeNode> merkleTreeNodes) {
        if(merkleTreeNodes.size() <= 0) {
        	throw new InvalidParameterException("List of nodes in Merkle Tree not expected to be empty!!!");
        }

        if(merkleTreeNodes.size() == 1) {
            this.merkleTreeRoot = merkleTreeNodes.get(0);
        }
        else {
            List<MerkleTreeNode> merkleTreeNodeParents = new ArrayList<>();
        
            for (int i = 0; i < merkleTreeNodes.size(); i += 2) {
        
            	MerkleTreeNode merkleTreeNodeRight = (i + 1 < merkleTreeNodes.size()) ? 
                									  merkleTreeNodes.get(i + 1) : null;
                
                MerkleTreeNode merkleTreeNodeParent = new MerkleTreeNode(merkleTreeNodes.get(i), merkleTreeNodeRight);
                
                merkleTreeNodeParents.add(merkleTreeNodeParent);
            }
            
            this.buildMerkleTree(merkleTreeNodeParents);
        }
    }

    public List<MerkleTreeProofHash> auditTrailMerkleTreeProofHashes(MerkleTreeHash merkleTreeLeafHash) {
        
    	List<MerkleTreeProofHash> auditTrailMerkleTreeProofHashes = new ArrayList<>();

        MerkleTreeNode merkleTreeLeafNode = this.findMerkleTreeNodeLeaf(merkleTreeLeafHash);

        if(merkleTreeLeafNode != null) {
            
        	if(merkleTreeLeafNode.getParentMerkleTreeNode() == null) {
        		throw new InvalidParameterException("Leaf node in Merkle Tree expected to have a parent node!!!");
        	}
            
        	MerkleTreeNode parentMerkleTreeNode = merkleTreeLeafNode.getParentMerkleTreeNode();
        	
            this.buildAuditTrailProofHashes(auditTrailMerkleTreeProofHashes, parentMerkleTreeNode, merkleTreeLeafNode);
        }

        return auditTrailMerkleTreeProofHashes;
    }

    public static boolean verifyAuditTrailMerkleTreeProofHashes(MerkleTreeHash rootMerkleTreeHash,
    														    MerkleTreeHash leafMerkleTreeHash, 
    														    List<MerkleTreeProofHash> auditTrailMerkleTreeProofHashes) {
 
    	if(auditTrailMerkleTreeProofHashes.size() <= 0) {
    		throw new InvalidParameterException("The Audit Trail of Merkle Tree Proof Hashes cannot be empty!!!");
    	}

        MerkleTreeHash testMerkleTreeHash = leafMerkleTreeHash;

        
        for(MerkleTreeProofHash auditTrailMerkleTreeProofHash : auditTrailMerkleTreeProofHashes) {
        	
        	testMerkleTreeHash = ( auditTrailMerkleTreeProofHash.branchDirection == MerkleTreeProofHash.Branch.RIGHT ) ?
                      MerkleTreeHash.create(testMerkleTreeHash, auditTrailMerkleTreeProofHash.merkleTreeHash) :
                      MerkleTreeHash.create(auditTrailMerkleTreeProofHash.merkleTreeHash, testMerkleTreeHash);
        
        }

        
        return testMerkleTreeHash.equals(rootMerkleTreeHash);
    }

    private MerkleTreeNode findMerkleTreeNodeLeaf(MerkleTreeHash merkleTreeHash) {
        return this.getMerkleTreeLeafNodes().stream()
                .filter((merkleTreeNodeLeaf) -> merkleTreeNodeLeaf.getMerkleTreeHash() == merkleTreeHash)
                .findFirst()
                .orElse(null);
    }

    private void buildAuditTrailProofHashes(List<MerkleTreeProofHash> auditTrailProofHashes,
    										MerkleTreeNode parentMerkleTreeNode,
    										MerkleTreeNode childMerkleTreeNode) {
    	
    	if(parentMerkleTreeNode != null) {
            if (childMerkleTreeNode.getParentMerkleTreeNode() != parentMerkleTreeNode) {
                throw new InvalidParameterException
                		  ("Parent node of the child node in the Merkle Tree isn't the expected parent node in the Merkle Tree!!!");
            }

            MerkleTreeNode nextChild = parentMerkleTreeNode.getLeftMerkleTreeNode() == childMerkleTreeNode ? 
            		parentMerkleTreeNode.getRightMerkleTreeNode() : parentMerkleTreeNode.getLeftMerkleTreeNode();
        
            MerkleTreeProofHash.Branch merkleTreeProofHashBranchDirection =
            		( parentMerkleTreeNode.getLeftMerkleTreeNode() == childMerkleTreeNode )
		                    ? MerkleTreeProofHash.Branch.RIGHT
		                    : MerkleTreeProofHash.Branch.LEFT;

            if (nextChild != null) {
            	auditTrailProofHashes.add(new MerkleTreeProofHash
            							 (nextChild.getMerkleTreeHash(),
            							  merkleTreeProofHashBranchDirection));
            }

            this.buildAuditTrailProofHashes(auditTrailProofHashes,
			            		            parentMerkleTreeNode.getParentMerkleTreeNode(),
			            		            childMerkleTreeNode.getParentMerkleTreeNode());
        }
    }
}