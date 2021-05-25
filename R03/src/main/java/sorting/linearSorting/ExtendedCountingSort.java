package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex || rightIndex > array.length || leftIndex < 0) {
			return;
		}
		int min = array[leftIndex];
        int max = array[leftIndex];
        for (int i = leftIndex + 1; i <= rightIndex; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int[] B = new int[array.length];
        int[] C = new int[max - min + 1];
        for (int i = leftIndex; i <= rightIndex; i++) {
            C[array[i] - min] += 1;
        }
        for (int i = 1; i < C.length; i++) {
            C[i] = C[i] + C[i - 1];
        }
        for (int i = rightIndex; i >= leftIndex; i--) {
            B[C[array[i] - min] - 1] = array[i];
            C[array[i] - min] -= 1;
        }
        int pivot = 0;
        for (int i = leftIndex; i <= rightIndex; i++) {
        	array[i] = B[pivot];
        	pivot += 1;        	
        }
	}
}
