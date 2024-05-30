package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipProdus extends Entitate {
    private String id_categorie;
    private String denumire;
    private String specificatii;


    public TipProdus(){}
    public TipProdus(String id, String id_categorie, String denumire, String specificatii) {
        super(id);
        this.id_categorie = id_categorie;
        this.denumire = denumire;
        this.specificatii = specificatii;
    }

    public TipProdus(String id_tip_produs, String denumire, String specificatii) {
        this(id_tip_produs, null, denumire, specificatii);
    }

    @Override
    public String toString()
    {
        return super.toString() + "   " + id_categorie + "   " + denumire + "    " + specificatii;
    }
}
