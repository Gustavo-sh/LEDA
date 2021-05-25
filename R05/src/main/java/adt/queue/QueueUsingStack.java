package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (!stack1.isFull()) {
			try {
				stack1.push(element);
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		} else {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (stack1.isEmpty()) {
			throw new QueueUnderflowException();
		}
		while(!stack1.isEmpty()) {
			try {
				stack2.push(stack1.pop());
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		}
		T result = null;
		try {
			result = stack2.pop();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		while(!stack2.isEmpty()) {
			try {
				stack1.push(stack2.pop());
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public T head() {
		if (stack1.isEmpty()) {
			return null;
		}
		while(!stack1.isEmpty()) {
			try {
				stack2.push(stack1.pop());
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		}
		T result = stack2.top();
		while(!stack2.isEmpty()) {
			try {
				stack1.push(stack2.pop());
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}

}
