package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex || rightIndex > array.length || leftIndex < 0) {
			return;
		}
		int maior = leftIndex;
	    for(int i = rightIndex; i >= leftIndex; i--){
	      for(int j = leftIndex + 1; j <= i; j++){
	        if (array[j].compareTo(array[maior]) > 0){
	          maior = j;
	        }
	      }
	      if (maior != i){
	        Util.swap(array, maior, i);
	      }
	      maior = leftIndex;
	    }
	}
}
