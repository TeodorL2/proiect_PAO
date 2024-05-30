package Model;
import java.sql.*;
public abstract class Entitate{
    protected String id;

    public Entitate(){}

    public Entitate(String id)
    {
        this.id = id;
    }

    public String getClassName()
    {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return id;
    }
}
