package adt.avltree;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		int MID = array.length/2;
		int MID_RIGHT = MID+1;
		int MID_LEFT = MID-1;
		
		this.insert(array[MID]);
		while(MID_LEFT >= 0 && MID_RIGHT < array.length) {
			if(this.height((BSTNode) this.root.getLeft()) > this.height((BSTNode) this.root.getRight())) {
				this.insert(array[MID_RIGHT]);
				MID_RIGHT += 1;
			} else {
				this.insert(array[MID_LEFT]);
				MID_LEFT -= 1;
			}
		}
	}

}
