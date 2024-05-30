package Exceptions;

public class ClientInvalid extends RuntimeException{
    public ClientInvalid(String msg)
    {
        super(msg);
    }
}
