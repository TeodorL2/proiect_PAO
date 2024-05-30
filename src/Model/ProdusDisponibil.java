package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdusDisponibil extends Entitate {
    private String id_tip_produs;
    private Double pret;
    private String loc_depozitare;

    public ProdusDisponibil(){}

    public ProdusDisponibil(String id, String id_tip_produs, Double pret, String loc_depozitare) {
        super(id);
        this.id_tip_produs = id_tip_produs;
        this.pret = pret;
        this.loc_depozitare = loc_depozitare;
    }

    @Override
    public String toString()
    {
        return super.toString() + "   " + id_tip_produs + "   " + pret + "   " + loc_depozitare;
    }

    public Double getPret()
    {
        return pret;
    }
    public void setPret(Double pret)
    {
        this.pret = pret;
    }
}
