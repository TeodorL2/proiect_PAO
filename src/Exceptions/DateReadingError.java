package Exceptions;

public class DateReadingError extends RuntimeException{
    public DateReadingError(String msg)
    {
        super(msg);
    }
}
