package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Categorie extends Entitate{
    private String denumire;

    public Categorie(){}
    public Categorie(String  id, String denumire) {
        super(id);
        this.denumire = denumire;
    }

    @Override
    public String toString() {
        return super.toString() + "   " + denumire;
    }
}
