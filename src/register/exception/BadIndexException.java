package register.exception;

public class BadIndexException extends Exception {
	public BadIndexException() {
		super("Zadal si zly index");
	}
}
