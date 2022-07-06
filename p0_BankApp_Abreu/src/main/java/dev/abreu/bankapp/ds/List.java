package dev.abreu.bankapp.ds;

public interface List<T> {
	
	/**
	 * Adds element to end of list
	 * 
	 * @param t the object being added to list
	 */
	public void add (T t);
	
	/**
	 * retrieves element from specified index
	 * 
	 * @param index of the desired element
	 * @return t the element at specified index
	 */
	public T get (int index);
	
	/**
	 * deletes object at specified index from the list
	 * 
	 * @param index of the object to be removed
	 * @return the object that was removed
	 */
	public T delete (int index);
	
	/**
	 * returns the index of the first instance of
	 * the specified object in the list
	 * 
	 * @param t the object being searched
	 * @return the index of the specified object in the list
	 */
	public int indexOf (T t);
	
	/**
	 * Determines whether the list is empty or contains
	 * at least one element
	 * 
	 * @return true if list is empty, false otherwise
	 */
	public boolean isEmpty();
	
	/**
	 * returns the current number of elements in the list
	 * 
	 * @return the number of elements in the list
	 */
	public int size();
	
}
