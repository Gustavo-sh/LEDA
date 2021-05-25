package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private DoubleLinkedList<Integer> lista1;
	private DoubleLinkedList<Integer> lista2;
	private DoubleLinkedList<Integer> lista3;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		// Lista com 1 elemento.
		lista3.insert(1);
	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new RecursiveDoubleLinkedListImpl();
		lista2 = new RecursiveDoubleLinkedListImpl();
		lista3 = new RecursiveDoubleLinkedListImpl();
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());
	}
	
	@Test
	public void myTests() {
		DoubleLinkedListImpl d = new DoubleLinkedListImpl();
		//d.insertFirst(2);
		d.insert(1);
		d.insert(2);
		//d.insertFirst(5);
		d.insert(4);
		d.insertFirst(0);
		//d.remove(1);
		d.insertFirst(5);
		d.removeFirst();
		d.insertFirst(8);
		d.insert(9);
		d.insertFirst(3);
		d.insertFirst(9);
		d.insert(6);
		d.removeLast();
		d.remove(1);
		//System.out.println(d.search(1));
		//d.remove(1);
		//d.remove(0);
		//d.remove(3);
		d.xxx();
		Object[] a = d.toArray();
		for(int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		//System.out.println(d.search(3));
		//Assert.assertArrayEquals(new Integer[] { 5, 2, 3, 2, 4 }, d.toArray());
	}
}