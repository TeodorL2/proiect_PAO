package Model;

import java.util.List;

public abstract class Cladire extends Entitate{
    protected String id_adresa;

    public Cladire(){}
    public Cladire(String id, String id_adresa)
    {
        super(id);
        this.id_adresa = id_adresa;
    }

    @Override
    public String toString() {
        return id + "   " + id_adresa;
    }
}
