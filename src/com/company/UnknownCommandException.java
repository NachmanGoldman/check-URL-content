package com.company;

/**
 * UnknownCommandException that extends from Exception
 */
public class UnknownCommandException extends Exception {

    /**
     * Creates a new UnknownCommandException.
     * @param cause A phrase explaining the cause for the exception.
     */
    public UnknownCommandException(String cause) {
        super(cause);
    }
}
