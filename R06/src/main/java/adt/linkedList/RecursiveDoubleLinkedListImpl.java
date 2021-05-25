package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insertFirst(T element) {
		if(isEmpty()) {
			data = element;
			next = new RecursiveDoubleLinkedListImpl<T>();
			previous = new RecursiveDoubleLinkedListImpl<T>();
		} else {
			RecursiveDoubleLinkedListImpl aux = new RecursiveDoubleLinkedListImpl<T>();
			aux.data = this.data;
			aux.next = this.next;
			this.data = element;
			this.next = aux;
			this.previous = new RecursiveDoubleLinkedListImpl();
			aux.previous = this;
		}
	}
	
	@Override
	public T[] toArray() {
		Integer[] result = new Integer[size()];
		toArrayy(result, 0);
		return (T[]) result;
	}
	
	public void toArrayy(Integer[] a, int in) {
		if(next.isEmpty()) {
			a[in] = (Integer) data;
		} else {
			a[in] = (Integer) data;
			next.toArrayy(a, in + 1);
		}
	}

	@Override
	public void removeFirst() {
		if(isEmpty()) {
			
		} else {
			data = next.data;
			next = next.next;
		}
	}

	@Override
	public void removeLast() {
		if(isEmpty()) {
			
		} else {
			if(next.isEmpty()) {
				data = null;
				next = new RecursiveDoubleLinkedListImpl<T>();
			} else {
				((DoubleLinkedList<T>) next).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
	
	@Override
	public void insert(T element) {
		if(isEmpty()) {
			data = element;
			next = new RecursiveDoubleLinkedListImpl<T>();
			if(previous == null) {
				previous = new RecursiveDoubleLinkedListImpl<T>();
			}
		} else {
			next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if(isEmpty()) {
			
		} else {
			if(data == element) {
				if(previous.isEmpty() && next.isEmpty()) {
					data = null;
					previous = null;
					next = null;
				} else {
					data = next.data;
					next = next.next;
					if(next != null) {
						((RecursiveDoubleLinkedListImpl<T>) next).previous = this;
					}
				}
			} else {
				next.remove(element);
			}
		}
	}
}
