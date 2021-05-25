package orderStatistic;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Voce pode modificar o array original
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 *   ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 *   Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		Integer[] a = {};
		if (k > array.length) {
			return (T[]) a;
		}
		Integer[] arr = new Integer[k];
		int index = 0;
		for (int i = array.length; i > array.length - k; i--) {
			arr[index] = (Integer) orderStatistics(array, i);
			index += 1;
		}
		return (T[]) arr;	
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		return particion(array, k, 0, array.length - 1, array[0]);
	}
	
	public T particion(T[] array, int k, int l, int r, T n) {
		if (l >= r) {
			return array[l];
		}
		Integer pivot = (Integer) array[l];
		Integer j = l;
		for (int i = l + 1; i <= r; i++) {
			if (array[i].compareTo((T) pivot) <= 0) {
				j++;
				Util.swap(array, i, j);
			}
		}
		Util.swap(array, j, l);
		if (j + 1 < k) {
			n = particion(array, k, j + 1, r, n);
		}
		if (j + 1 > k) {
			n = particion(array, k, l, j - 1, n);
		}
		if (j + 1 == k) {
			return array[j];
		}
	    return n;
	}
	
	public static void main(String[] args) {
		KLargestOrderStatisticsImpl<Integer> k = new KLargestOrderStatisticsImpl();
		QuickSelect q = new QuickSelect();
		Integer[] a = {3,2,5,4,7,40,37};
		Integer[] b = k.getKLargest(a, 3);
		System.out.println(q.quickSelect(a, 6));
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}
	}
}
