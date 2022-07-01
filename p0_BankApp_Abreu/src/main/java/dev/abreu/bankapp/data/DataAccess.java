package dev.abreu.bankapp.data;

import dev.abreu.bankapp.ds.List;

public interface DataAccess<T> {
	
	public T create(T t);
	
	//public T findById(int id);
	
	public List<T> findAll();
	
	public void update(T t);
	
	/**
	 * Deletes the object in the data source with the matching identifier.
	 * 
	 * @param t the object to be deleted from the data source
	 */
	public void delete(T t);

}
