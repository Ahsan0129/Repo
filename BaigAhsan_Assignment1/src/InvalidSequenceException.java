
@SuppressWarnings("serial")
public class InvalidSequenceException extends Exception {
	
	public InvalidSequenceException() {
		super("Your password can't contain more than 2 letters in a sequence.");
	}

}
