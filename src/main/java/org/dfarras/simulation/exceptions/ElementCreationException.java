package org.dfarras.simulation.exceptions;

public class ElementCreationException extends RuntimeException {
    public ElementCreationException(String message) {
        super(message);
    }

    public ElementCreationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
