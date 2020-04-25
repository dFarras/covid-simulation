package org.dfarras.simulation.exceptions;

public class DailyScheduleException extends RuntimeException {
    public DailyScheduleException(String message) {
        super(message);
    }

    public DailyScheduleException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
