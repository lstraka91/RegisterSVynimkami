package register.exception;

public class DuplicationException extends Exception {
	public DuplicationException(String message) {
		super("Duplikovany zaznam v databaze. " + message);
	}
}
