import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class NotationQueue<T> implements QueueInterface {
	ArrayList<T> queue;
	private int max;

	public NotationQueue(int max) {// an overloaded contructor(same function name with different params)
		queue = new ArrayList<>();
		this.max = max;

	}
	/**
	 * the default constructors
	 */
	public NotationQueue() {
		queue = new ArrayList<>();
		this.max = 100;

	}

	/**
	 * creates a deep copy of the arraylist to enure no security breaches
	 * 
	 * @param array
	 */
	public NotationQueue(ArrayList<T> array) {
		queue = new ArrayList<>();
		this.max = 100;
		for (int i = 0; i < array.size(); i++) {
			T ogVersion = array.get(i);// gets the index of the array
			T copy = ogVersion;// shallow copies the index
			queue.add(copy);// adds the deep copy to queue

		}
	}

	/**
	 * adds to the back(top) of the queue
	 */
	public boolean enqueue(Object e) throws QueueOverflowException {
		if (!isFull()) {
			queue.add((T) e);
		} else {
			throw new QueueOverflowException();
		}
		return true;
	}

	/**
	 * takes from the front of the queue
	 */
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		return queue.remove(0);
	}

	/**
	 * checks if the queue is empty by returning if count = 0
	 */
	public boolean isEmpty() {
		return queue.size() == 0;
	}

	/**
	 * checks if the queue is full by returning the count if it equals the user
	 * inputted size of the bag
	 */
	public boolean isFull() {
		return queue.size() == max;
	}
/**
 * size of the queue
 */
	public int size() {
		return queue.size();
	}
/**
 * turns the item into a string
 */
	public String toString() {
		String a = "";
		for (int i = 0; i < queue.size(); i++) {
			T item = queue.get(i);
			String b = item.toString();
			a = a + b;

		}
		return a;
	}
/**
 * write the toString but with something in between each index(the delimiter)
 */
	public String toString(String delimiter) {
		String a = "";
		for (int i = 0; i < queue.size(); i++) {
			T item = queue.get(i);
			String b = item.toString();
			if (queue.size() - 1 == i) {
				a = a + b;
			} else {
				a = a + b + delimiter; //adding the delimiter if we arent at the last index of arrays
			}

		}
		return a;
	}

}
