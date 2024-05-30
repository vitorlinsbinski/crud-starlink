package exceptions;

public class UserAlreadyExistsExepction extends RuntimeException {
	public UserAlreadyExistsExepction(String message) {
		super(message);
	}
}
