package in.tosc.doandroidlib.exceptions;

import java.io.IOException;

/**
 * Created by championswimmer on 15/07/17.
 */

public class ClientInitializationException extends Exception {

    public ClientInitializationException(String message) {
        super(message);
    }

    public ClientInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
