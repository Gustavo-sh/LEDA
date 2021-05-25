package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		int i = 0;
		do {
			if(i == this.table.length - 1) {
				throw new HashtableOverflowException();
			}
			int key = ((HashFunctionLinearProbing) this.hashFunction).hash(element, i);
			if(this.table[key] == null || this.table[key].equals(new DELETED())) {
				this.table[key] = element;
				this.elements += 1;
				return;
			} else {
				this.COLLISIONS += 1;
			}
			i++;
		} while(i < this.table.length);
	}

	@Override
	public void remove(T element) {
		if(isEmpty()) {
			return;
		}
		for (int i = 0; i < this.table.length; i++) {
			if(this.table[i] != null) {
				if(!this.table[i].equals(new DELETED())) {
					if(((HashtableElement) this.table[i]).equals(element)) {
						this.table[i] = new DELETED();
						this.elements -= 1;
						return;
					}
				}
			}
		}
	}

	@Override
	public T search(T element) {
		if(isEmpty()) {
			return null;
		}
		for (int i = 0; i < this.table.length; i++) {
			if(this.table[i] != null) {
				if(!this.table[i].equals(new DELETED())) {
					if(((HashtableElement) this.table[i]).equals(element)) {
						return (T) this.table[i];
					}
				}
			}
		}
		return null;
	}

	@Override
	public int indexOf(T element) {
		if(isEmpty()) {
			return -1;
		}
		for (int i = 0; i < this.table.length; i++) {
			if(this.table[i] != null) {
				if(!this.table[i].equals(new DELETED())) {
					if(((HashtableElement) this.table[i]).equals(element)) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	/**
	 * Um método que exibe, em forma de String, todos os elementos
	 * da tabela. Usado apenas para verificação de corretude da classe
	 * em conjunto com testes pessoais no JUnit. Não funciona sem o método 'getKey()'
	 * que deveria pertencer à classe HashtableElement. 'getKey()' foi criado temporáriamente
	 * na classe HashtableElement e portanto este método não deve mais ser usado.
	 * 
	 * @return Uma String que contém a representação da tabela.
	 */
	public String toS() {
		String f = "";
		for (int i = 0; i < this.table.length; i++) {
			if(this.table[i] != null && !this.table[i].equals(new DELETED())) {
				//f += ((HashtableElement) this.table[i]).getKey() + ", ";
			} else {
				if(this.table[i] == null) {
					f += "null" + ", ";
				} else {
					f += "DELETED" + ", ";
				}
			}
		}
		return f.substring(0, f.length()-2);
	}
}
