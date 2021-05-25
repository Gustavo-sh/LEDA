package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			size += 1;
			aux = aux.next;
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			if (aux.data == element) {
				return aux.data;
			}
			aux = aux.next;
		}
		return null;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> aux = head;
		if (head.isNIL()) {
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>();
			newHead.setData(element);
			newHead.next = head;
			head = newHead;
		} else {
			while (!aux.isNIL()) {
				aux = aux.next;
			}
			aux.data = element;
			aux.next = new SingleLinkedListNode<T>();
		}
	}

	@Override
	public void remove(T element) {
		if (head.data == element) {
			head = head.next;
		} else {
			SingleLinkedListNode<T> aux = head;
			while (!aux.isNIL() && aux.data != element) {
				aux = aux.next;
			}
			if (!aux.isNIL()) {
				aux.data = aux.next.data;
				aux.next = aux.next.next;
			}
		}
	}

	@Override
	public T[] toArray() {
		if(isEmpty()) {
			Integer[] i = {};
			return (T[]) i;
		}
		SingleLinkedListNode<T> aux = head;
		int in = 0;
		Integer[] result = new Integer[size()];
		while (!aux.isNIL()) {
			result[in] = (Integer) aux.data;
			aux = aux.next;
			in += 1;
		}
		return (T[]) result;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
