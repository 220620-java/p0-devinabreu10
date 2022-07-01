package dev.abreu.bankapp.ds;

import java.io.Serializable;
import java.util.Arrays;

// Generics allow you to restrict types and check them at compile time, while also still having
// the flexibility of using different types with your classes
// we use < > to specify Parameter type
@SuppressWarnings("serial")
public class ArrayList<T> implements List<T>, Serializable {
	private T[] array;
	private int size;
	
	@SuppressWarnings("unchecked")
	public ArrayList() { // array list constructor creates an arrList with intial capacity of 10
		size = 0;
		array = (T[]) new Object[10];
	}

	// varargs parameter (...) allows you to pass in either an array or
	// a comma-separated list of values
	@SafeVarargs
	public ArrayList(T... objects) {
		array = objects;
	}

	@Override
	public void add(T obj) {
		// grow array if it runs out of space
		if (size > array.length - 1) {
			array = Arrays.copyOf(array, size*2);
		}
		// adds new object
		this.array[size++] = obj;
		// this. is not necessary but is required
		// in Typescript so might be good to get used to
	}

	@Override
	public T get(int index) {
		if (index >= 0 && index < this.size) {
			return this.array[index];
		} else {
			throw new IndexOutOfBoundsException(index);
		}
	}

	// get size of the ArrayList
	public int size() {
		return this.size;
	}
	
	// boolean to check if list is empty
	public boolean isEmpty() {
		return size <= 0;
	}

	@Override
	public T delete(int index) { // removes the element at the specified index
		if(index>=0 && index < this.array.length) {
			T obj = this.array[index];
			// shift everything over
			for (int i = 0; i < this.array.length-1; i++) {
				this.array[i] = this.array[i+1];
			}
			// shift the last item over
			this.array[this.array.length-1]=null;
			this.size--;
			return obj;
		} else {
			throw new IndexOutOfBoundsException(index);
		}
	}

	@Override
	public int indexOf(T obj) {
		if (obj == null) {
			for (int i = 0; i < this.size; i++)
				if (this.array[i] == null)
					return i;
		} else {
			for (int i = 0; i < this.size; i++)
				if (obj.equals(this.array[i]))
					return i;

		}
		return -1;
	}

	@Override
	public String toString() {
		return Arrays.toString(array);
	}
}

