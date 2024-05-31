package exceptions;

public class UserCredentialsDoesNotMatch extends RuntimeException {
	public UserCredentialsDoesNotMatch(String message) {
		super(message);
	}
}
