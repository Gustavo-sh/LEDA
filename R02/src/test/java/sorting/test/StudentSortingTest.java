package sorting.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.divideAndConquer.MergeSort;
import sorting.divideAndConquer.QuickSort;
import sorting.divideAndConquer.threeWayQuicksort.ThreeWayQuickSort;

public class StudentSortingTest {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;

	public AbstractSorting<Integer> implementation;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,
				31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49,
				11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
		populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });

		getImplementation();
	}

	// // MÉTODOS AUXILIARES DA INICIALIZAÇÃO
	/**
	 * Método que inicializa a implementação a ser testada com a implementação
	 * do aluno
	 */
	private void getImplementation() {
		
		this.implementation = new ThreeWayQuickSort();
		
	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorRepetido(Integer[] arrayPadrao) {
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao,
				arrayPadrao.length);
	}

	public void populaVetorIgual(Integer[] arrayPadrao) {
		this.vetorValoresIguais = Arrays
				.copyOf(arrayPadrao, arrayPadrao.length);
	}

	// FIM DOS METODOS AUXILIARES DA INICIALIZAÇÃO

	// MÉTODOS DE TESTE

	public void genericTest(Integer[] array) {
		Integer[] copy1 = {};
		if(array.length > 0){
			copy1 = Arrays.copyOf(array, array.length);			
		}
		implementation.sort(array);
		Arrays.sort(copy1);
		exibe(array);
		exibe(copy1);
		Assert.assertArrayEquals(copy1, array);
	}

	@Test
	public void testSort01() {
		genericTest(vetorTamPar);
	}

	@Test
	public void testSort02() {
		genericTest(vetorTamImpar);
	}

	@Test
	public void testSort03() {
		genericTest(vetorVazio);
	}

	@Test
	public void testSort04() {
		genericTest(vetorValoresIguais);
	}

	@Test
	public void testSort05() {
		genericTest(vetorValoresRepetidos);
	}

	// MÉTODOS QUE OS ALUNOS PODEM CRIAR
	/**
	 * O ALUNO PODE IMPLEMENTAR METODOS DE ORDENAÇÃO TESTANDO O SORT COM TRES
	 * ARGUMENTOS PARA TESTAR A ORDENACAO EM UM PEDAÇO DO ARRAY. DICA: PROCUREM
	 * SEGUIR A ESTRUTURA DOS MÉTODOS DE TESTE ACIMA DESCRITOS, ORDENANDO APENAS
	 * UMA PARTE DO ARRAY.
	 */
	@Test
	public void myTests() {
		Integer[] array1 = {8,6,4,1,3,7,2,9,0};
		Integer[] array2 = {8,1,4,6,3,7,2,9,0};
		Integer[] array3 = {8,3,2,7};
		Integer[] array4 = {8,2,3,7};
		Integer[] array5 = {2,3,7,8};
		Integer[] last = {8,6,4,1,3,0,2,7,9};
		
		MergeSort m = new MergeSort();
		QuickSort q = new QuickSort();
		ThreeWayQuickSort t = new ThreeWayQuickSort();
		
		//t.sort(array3, 0, 3);
		//Assert.assertArrayEquals(array3, array5);
		//m.sort(array3, 1, 2);
		//exibe(array3);
		//q.sort(array3, 1, 2);
		//exibe(array3);
		//t.sort(array3, 1, 2);
		//exibe(array3);
		//Assert.assertArrayEquals(array3, array4);
		
		//m.sort(array1, 5, 8);
		//Assert.assertArrayEquals(array1, last);
		//q.sort(array1, 5, 8);
		//exibe(array1);
		//t.sort(array1, 5, 8);
		//exibe(array1);
		//Assert.assertArrayEquals(array1, last);
	}
	
	public static void exibe(Integer[] a) {
		String s = "";
		for (Integer i : a) {
			s += i + " ";
		}
		System.out.println(s);
	}
}