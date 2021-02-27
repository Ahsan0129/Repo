import java.util.ArrayList;

public class NotationStack<T> implements StackInterface<T> {
	int max;
	ArrayList<T> stack;

/**
 * sets the max
 * @param max
 */
	public NotationStack(int max) {
		stack = new ArrayList<>();
		this.max = max;// setting line 4 max to line 8

	}
	/**
	 * default constructor
	 */
	public NotationStack() {
		stack = new ArrayList<>();
		this.max = 100;
	}
/**
 * loops through the array and shallow copies each index to the stack
 * @param array
 */
	public NotationStack(ArrayList<T> array) {
		stack = new ArrayList<>();
		this.max = 100;
		for (int i = 0; i < array.size(); i++) {
			T ogVersion = array.get(i);// gets the index of the array
			T copy = ogVersion;// shallow copies the index
			stack.add(copy);// adds the copy to stack

		}
	}

	public boolean isEmpty() {
		return stack.size() == 0;
	}

	/**
	 * checks if the size equals max if so then the stack is full
	 * @return stack.size() == max; 
	 */
	public boolean isFull() {
		return stack.size() == max;
	}

	/**
	 * Deletes and returns the element at the top(end) of the Stack
	 * 
	 * @return the element at the top of the Stack
	 */
	public T pop() throws StackUnderflowException {
		if (!isEmpty()) {
			T end = stack.get(stack.size() - 1);// storing the last value in the array into T end
			stack.remove(stack.size() - 1);// deleting the last value in the stack
			return end;// returning the stored value

		}
		throw new StackUnderflowException();// throwing exception if the stack is empty

	}

	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * 
	 * @return the element at the top of the Stack
	 */
	public T top() throws StackUnderflowException {
		return stack.get(stack.size()-1);

	}

	/**
	 * checks the size of the stack
	 */
	public int size() {
		return stack.size();
	}

	/**
	 * Adds an element to the top of the Stack
	 * 
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 */
	public boolean push(T e) throws StackOverflowException {
		if (!isFull()) {
			stack.add((T) e);
		} else {
			throw new StackOverflowException();
		}
		return true;
	}

	/**
	 * made an empty string and then adds b to the string onward
	 */
	public String toString() {
		String a = "";
		for (int i = 0; i < stack.size(); i++) {
			T item = stack.get(i);
			String b = item.toString();
			a = a + b;

		}
		return a;

	}
/**
 * @param String delimiter 
 * turns the stack into a string and puts delimiter in between each index
 */
	@Override
	public String toString(String delimiter) {
		String a = "";
		for (int i = 0; i < stack.size(); i++) {
			T item = stack.get(i);
			String b = item.toString();
			if (stack.size() - 1 == i) {
				a = a + b;
			} else {
				a = a + b + delimiter;
			}

		}
		return a;
	}

}
