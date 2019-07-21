package register_jdbc.exception;

public class UserExistsException extends Exception {
    public UserExistsException() {
        // TODO Auto-generated constructor stub
    }

    public UserExistsException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public UserExistsException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public UserExistsException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
}
