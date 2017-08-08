package exception;

/**
 * Created by Zero on 2017/5/2.
 */
public class UserFrozenException extends CustomException {
    public UserFrozenException() {
    }

    public UserFrozenException(String message) {
        super(message);
    }
}
