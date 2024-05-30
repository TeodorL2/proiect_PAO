package Model;

import Exceptions.AdresaInvalida;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Magazin extends Cladire {

    public Magazin(){}
    public Magazin(String id, String id_adresa) {
        super(id, id_adresa);
    }

    public Magazin(String id)
    {
        this(id, null);
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
