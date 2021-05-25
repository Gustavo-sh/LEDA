package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		sort(array, 0, array.length - 1);
		return search(array, 0, array.length - 1, x, 0);
	}
	
	public Integer search(Integer[] array, int l, int r, int x, int n) {
		if (array.length == 0) {
			return null;
		}
		
		int min = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		
		if (x < min) {
			return null;
		}
		
		int middle = (l + r) / 2;
		if (l >= r) {
			return array[l];
		}
		if (array[middle] == x) {
			return x;
		}
		if (array[middle] < x) {
			if (array[middle + 1] > x) {
				return array[middle];
			}
			n = search(array, middle + 1, r, x, n);
		} else {
			n = search(array, l, middle - 1, x, n);
		}
		
		return n;
	}
	
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int i = particiona(array, leftIndex, rightIndex);
			sort(array, leftIndex, i - 1);
			sort(array, i + 1, rightIndex);
		}
	}
	
    public int particiona(Integer[] array, int l, int r) {
    	Integer pivot = (Integer) array[l];
    	Integer j = l;
    	for (int i = l + 1; i <= r; i++) {
    		if (array[i].compareTo(pivot) <= 0) {
    			j++;
    			Util.swap(array, i, j);
    		}
    	}
    	Util.swap(array, j, l);
		return j;
	}
}
