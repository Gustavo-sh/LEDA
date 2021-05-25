package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Voce pode assumir que o maior inteiro armazenado não chega a 100.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex || rightIndex > array.length || leftIndex < 0) {
			return;
		}
		int[] C;
        int[] B = new int[array.length];
        int maior = array[leftIndex];
        for (int i = leftIndex + 1; i <= rightIndex; i++) {
            if (array[i] > maior) {
                maior = array[i];
            }
        }
        C = new int[maior];
        for (int i = leftIndex; i <= rightIndex; i++) {
        	if (array[i] == 0) {
        		C[0] += 1;
        	} else {
        		C[array[i] - 1] += 1;
        	}
        }
        for (int i = 1; i < C.length; i++) {
            C[i] = C[i] + C[i - 1];
        }
        for (int i = rightIndex; i >= leftIndex; i--) {
        	if (array[i] == 0) {
        		B[0] = array[i];
                C[0] -= 1;
        	} else {
        		B[C[array[i] - 1] - 1] = array[i];
                C[array[i] - 1] -= 1;
        	}
        }
        int pivot = 0;
        for (int i = leftIndex; i <= rightIndex; i++) {
        	array[i] = B[pivot];
        	pivot += 1;
        }
	}
}
