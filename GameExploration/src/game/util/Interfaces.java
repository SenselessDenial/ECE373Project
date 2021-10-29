package game.util;

public class Interfaces {
	public interface Action {
		void function();
	}
	
	public interface Predicate<T> {
		boolean function(T item);
	}
}
