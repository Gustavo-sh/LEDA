package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = 0;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (head == tail && isFull()) {
			throw new QueueOverflowException();
		}
		if (tail != array.length - 1) {
	    	array[++tail] = element;
	    	elements += 1;
	    } else if (!isFull()) {
	    	tail = -1;
	    	array[++tail] = element;
	    	elements += 1;
	    } else {
	    	throw new QueueOverflowException();
	    }
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result;
		if (head == tail && isEmpty()) {
			throw new QueueUnderflowException();
		}
		if (head != array.length - 1) {
			result = array[head];
			head++;
			elements -= 1;
		} else if (!isEmpty()) {
			result = array[head];
			head = -1;
			head++;
			elements -= 1;
		} else {
			throw new QueueUnderflowException();
		}
		return result;
	}

	@Override
	public T head() {
		if (head > -1) {
			return array[head];
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == array.length;
	}
}
