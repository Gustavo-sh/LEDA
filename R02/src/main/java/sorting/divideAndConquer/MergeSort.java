package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (rightIndex <= leftIndex) {
			return;
		}
		int middle = (leftIndex + rightIndex) / 2;
		sort(array, leftIndex, middle);
		sort(array, middle + 1, rightIndex);

		merge(array, leftIndex, middle, rightIndex);
	}

	public void merge(T[] array, int l, int middle, int r){
		T[] aux = (T[]) new Comparable[array.length];
		for (int i = l; i <= r; i++){
			aux[i] = array[i];
		}

		int i = l;
		int j = middle + 1;
		int k = l;

		while (i <= middle && j <= r){
			if (aux[i].compareTo(aux[j]) < 0){
				array[k] = aux[i];
				i++;
			} else {
				array[k] = aux[j];
				j++;
			}
			k++;
		}

		while (i <= middle){
			array[k] = aux[i];
			i++;
			k++;
		}

		while (j <= r){
			array[k] = aux[j];
			j++;
			k++;
		}
	}
}
