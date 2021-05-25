package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
		head = 0;
	}

	@Override
	public T head() {
		if (tail == -1) {
			return null;
		} else {
			return array[head];
		}
	}

	@Override
	public boolean isEmpty() {
		return tail == -1;
	}

	@Override
	public boolean isFull() {
		return tail == array.length - 1;
	}

	private void shiftLeft() {
		for (int i = 0; i <= tail; i++) {
			array[i] = array[i + 1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
	    if (!isFull()) {
	    	array[++tail] = element;
	    } else {
	    	throw new QueueOverflowException();
	    }
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result;
		if (!isEmpty()) {
			result = array[0];
			shiftLeft();
			tail--;
		} else {
			throw new QueueUnderflowException();
		}
		return result;
	}
}
