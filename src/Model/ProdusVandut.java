package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProdusVandut extends Entitate {
    private String id_tip_produs;
    private Double pret;
    private String id_tranzactie;
    private String id_livrare;

    public ProdusVandut(){}

    public ProdusVandut(String id, String id_tip_produs, Double pret, String id_tranzactie, String id_livrare) {
        super(id);
        this.id_tip_produs = id_tip_produs;
        this.pret = pret;
        this.id_tranzactie = id_tranzactie;
        this.id_livrare = id_livrare;
    }

    @Override
    public String toString()
    {
        return super.toString() + "   " + id_tip_produs + "   " + pret +
                "   " + id_tranzactie + "   " + id_livrare;
    }
}
