package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		BSTNode aux = bst.getRoot();
		return isBST(false, aux);
	}
	
	public boolean isBST(boolean result, BSTNode node) {
		if(!node.isEmpty() && !node.isLeaf()) {
			isBST(result, (BSTNode) node.getLeft());
			if(node.getLeft().isEmpty()) {
				if(((Comparable<T>) node.getData()).compareTo((T) node.getRight().getData()) < 0) {
					result = true;
				} else {
					result = false;
				}
			} else if (node.getRight().isEmpty()){
				if(((Comparable<T>) node.getData()).compareTo((T) node.getLeft().getData()) > 0) {
					result = true;
				} else {
					result = false;
				}
			} else {
				if(((Comparable<T>) node.getData()).compareTo((T) node.getRight().getData()) < 0) {
					result = true;
				} else {
					result = false;
				}
				if(((Comparable<T>) node.getData()).compareTo((T) node.getLeft().getData()) > 0) {
					result = true;
				} else {
					result = false;
				}
			}
			isBST(result, (BSTNode) node.getRight());
		}
		return result;
	}
	
}
