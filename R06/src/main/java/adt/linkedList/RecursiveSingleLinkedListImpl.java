package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return data == null;
	}

	@Override
	public int size() {
		if(isEmpty()) {
			return 0;
		} else {
			return 1 + next.size();
		}
	}

	@Override
	public T search(T element) {
		if(isEmpty()) {
			return null;
		} else {
			if(data == element) {
				return data;
			} else {
				return next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if(isEmpty()) {
			data = element;
			next = new RecursiveSingleLinkedListImpl<T>(); 
		} else {
			next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if(isEmpty()) {
			
		} else {
			if(data == element) {
				data = next.data;
				next = next.next;
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		if(isEmpty()) {
			Integer[] i = {};
			return (T[]) i;
		}
		Integer[] result = new Integer[size()];
		toArrayy(result, 0);
		return (T[]) result;
	}
	
	public void toArrayy(Integer[] a, int in) {
		RecursiveSingleLinkedListImpl<T> aux = next;
		if(next.isEmpty()) {
			a[in] = (Integer) data;
		} else {
			a[in] = (Integer) data;
			next.toArrayy(a, in + 1);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
