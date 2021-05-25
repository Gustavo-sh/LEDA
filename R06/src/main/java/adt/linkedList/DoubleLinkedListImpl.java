package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.last = new DoubleLinkedListNode<T>();
	}
	
	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newH = new DoubleLinkedListNode<T>();
		newH.data = element;
		newH.next = head;
		newH.previous = new DoubleLinkedListNode<T>();
		((DoubleLinkedListNode<T>) head).previous = newH;
		if(head.isNIL()) {
			last = newH;
		}
		head = newH;
	}

	@Override
	public void removeFirst() {
		if(!head.isNIL()) {
			head = head.next;
			((DoubleLinkedListNode<T>) head).previous = new DoubleLinkedListNode<T>();
		}
	}

	@Override
	public void removeLast() {
		if(!last.isNIL()) {
			last = last.previous;
			last.next = new DoubleLinkedListNode<T>();
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	@Override
	public T search(T element) {
		DoubleLinkedListNode<T> auxL = last;
		SingleLinkedListNode<T> auxH = head;
		while (auxH != auxL && auxH.next != auxL && auxH.data != element && auxL.data != element) {
			auxH = auxH.next;
			auxL = auxL.previous;
		}
		if (auxH.data == element) {
			return auxH.data;
		}
		if (auxL.data == element) {
			return auxL.data;
		}
		return null;
	}
	
	@Override
	public void insert(T element) {
		DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode(), last);
		last.next = newLast;
		if (last.isNIL()) {
			head = newLast;
		}
		last = newLast;
	}
	
	@Override
	public void remove(T element) {
		if(head.data == element) {
			removeFirst();
		} else {
			DoubleLinkedListNode temp = (DoubleLinkedListNode) head;
			while(!temp.isNIL()) {
				if(temp.data == element) {
					temp.previous.next = temp.next;
					((DoubleLinkedListNode) temp.next).previous = temp.previous;
					break;
				}
				temp = (DoubleLinkedListNode) temp.next;
			}
		}
	}
	
	public void xxx() {
		DoubleLinkedListNode lastStd = (DoubleLinkedListNode) head;
		DoubleLinkedListNode nextNode = new DoubleLinkedListNode();
		DoubleLinkedListNode currentNode = null;
		T temp = null;
		
		if(!lastStd.isNIL()) {
			nextNode = (DoubleLinkedListNode) lastStd.next;
		}
		
		while(!nextNode.isNIL()) {
			
			currentNode = lastStd;
			temp = (T) nextNode.getData();
			
			while(!currentNode.isNIL() && ((Comparable) temp).compareTo((T) currentNode.getData()) < 0) {
				nextNode.data = currentNode.getData();
				currentNode = currentNode.previous;
				nextNode = (DoubleLinkedListNode) currentNode.next;
			}
			
			nextNode.data = temp;
			lastStd = (DoubleLinkedListNode) lastStd.next;
			nextNode = (DoubleLinkedListNode) lastStd.next;
		}
	}
}
