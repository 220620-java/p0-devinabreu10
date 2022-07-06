package dev.abreu.bankapp.data;


public interface DataAccessObject<T> {
	
	/**
	 * Adds specified object to database and returns it if successful
	 * 
	 * @param t the object being added to database
	 * @return the object that was added or null if no object added
	 */
	public T create(T t);
	
	//public T findById(int id);
	
	/**
	 * updates object in database by matching the identifier and changing
	 * any different values
	 * 
	 * @param t the updated object to be saved in database
	 */
	public void update(T t);
	
	/**
	 * Deletes the object in the database with the matching identifier.
	 * 
	 * @param t the object to be deleted from the database
	 */
	public void delete(T t);

}
