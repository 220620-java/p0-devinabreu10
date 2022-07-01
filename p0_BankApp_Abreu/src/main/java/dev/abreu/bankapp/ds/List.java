package dev.abreu.bankapp.ds;

//Interfaces and Abstract classes make up Abstraction in Java OOP
//interfaces are for setting up a group of behaviors that something should have without
//specifying how those behaviors will actually work 

public interface List<T> {
	// In interfaces, all methods are abstract implicitly because they're designed for behaviors, 
	// fields are implicitly public, static, and final
	// interfaces cannot be instantiated
	// can't do this: List myList = new List();
	public void add (T obj);
	public T get (int index);
	public T delete (int index);
	public int indexOf (T obj);

	
	// if it's static you don't need the "default" keyword to make it concrete
	@SuppressWarnings("rawtypes")
	public static List emptyList() {
		//TODO // reminder to come back and add an implementation
		return null;
	}
}
