package dev.abreu.bankapp.ds;

import java.util.Arrays;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
	private T[] array;
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayList() { // array list constructor creates an arrList with intial capacity of 10
		this.size = 0;
		this.array = (T[]) new Object[10];
	}

	// varargs parameter (...) allows you to pass in either an array or
	// a comma-separated list of values
	@SafeVarargs
	public ArrayList(T... objects) {
		array = objects;
	}

	@Override
	public void add(T obj) {
		if (size > array.length - 1) {
			array = Arrays.copyOf(array, size * 2); // grow array if it runs out of space
		}

		this.array[size++] = obj; // adds new object
	}

	@Override
	public T get(int index) {
		if (index >= 0 && index < this.size) {
			return this.array[index];
		} else {
			throw new IndexOutOfBoundsException(index);
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size <= 0;
	}

	@Override
	public T delete(int index) { // removes the element at the specified index
		if (index >= 0 && index < this.array.length) {
			T obj = this.array[index];

			for (int i = 0; i < this.array.length - 1; i++) {
				this.array[i] = this.array[i + 1];
			}
			// shift the last item over
			this.array[this.array.length - 1] = null;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(array);
		result = prime * result + Objects.hash(size);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		ArrayList<T> other = (ArrayList<T>) obj;
		return Arrays.deepEquals(array, other.array) && size == other.size;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("");
		for (int i = 0; i < this.size; i++) {
			result.append(this.array[i] + "\n");
		}
		return result.toString();
	}

}
