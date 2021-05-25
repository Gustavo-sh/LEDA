package orderStatistic;

import java.util.PriorityQueue;

import adt.heap.ComparatorMaxHeap;
import adt.heap.ComparatorMinHeap;

public class OrderStatisticsHeapImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Existem diversas formas de se calcular uma estatistica de ordem. 
	 * Voce deve fazer isso usando uma heap restricoes:
	 * - nenhuma copia ou estrutura array auxiliar serah permitida, exceto o uso de
	 *   uma PriorityQueue
	 * - caso a estatistica de ordem procurada nao exista no array o metodo deve retornar null 
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	
	@Override
	public T getOrderStatistics(T[] array, int k) {
		PriorityQueue<T> heap = new PriorityQueue<T>(new ComparatorMaxHeap());
		T result = null;
		if(k > array.length || k < 1) {
			return result;
		}
		for(int i = 0; i < array.length; i++) {
			heap.add(array[i]);
		}
		if(k == 1) {
			return heap.peek();
		}
		for(int i = 0; i < k; i++) {
			result = heap.remove();
		}
		return result;
	}
}
