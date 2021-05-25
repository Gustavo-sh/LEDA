package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer result = Integer.MIN_VALUE;
		for(int i = 0; i < array.length; i++) {
			insert(array[i]);
		}
		for(int i = 0; i < array.length; i++) {
			if(rootElement() <= numero && rootElement() > result) {
				result = rootElement();
			}
			extractRootElement();
		}
		if(result == Integer.MIN_VALUE) {
			return null;
		}
		return result;
	}
	
	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer result = Integer.MAX_VALUE;
		for(int i = 0; i < array.length; i++) {
			insert(array[i]);
		}
		for(int i = 0; i < array.length; i++) {
			if(rootElement() >= numero && rootElement() < result) {
				result = rootElement();
			}
			extractRootElement();
		}
		if(result == Integer.MAX_VALUE) {
			return null;
		}
		return result;
	}

}
