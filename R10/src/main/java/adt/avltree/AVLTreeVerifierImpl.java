package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {

		return isBST() && isAVLTree(this.avlTree.getRoot());

	}

	public boolean isAVLTree(BSTNode node) {
		boolean result = true;
		int height = avlTree.height((BSTNode) node.getLeft()) - avlTree.height((BSTNode) node.getRight());
		if(height > 1 || height < -1) {
			result = false;
		}
		return result;
	}
}
