package Model;

import Exceptions.AdresaInvalida;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Adresa extends Entitate{
    private String tara;
    private String judet_sau_regiune;
    private String oras;
    private String sector;
    private String strada;
    private Integer nr_cladire;
    private String bl;
    private String sc;
    private Integer etaj;
    private Integer nr_ap;

    public Adresa(){}
    private Adresa(String id, String tara, String judet_sau_regiune, String oras, String sector, String strada, Integer nr_cladire, String bl, String sc, Integer etaj, Integer nr_ap) {
        super(id);
        this.tara = tara;
        this.judet_sau_regiune = judet_sau_regiune;
        this.oras = oras;
        this.sector = sector;
        this.strada = strada;
        this.nr_cladire = nr_cladire;
        this.bl = bl;
        this.sc = sc;
        this.etaj = etaj;
        this.nr_ap = nr_ap;
    }

    public static Adresa getAdresa(String id, String tara, String judet_sau_regiune, String oras, String sector, String strada, Integer nr_cladire, String bl, String sc, Integer etaj, Integer nr_ap){
        if((tara == null || oras == null || strada == null || (nr_cladire == null && (bl == null || sc == null || nr_ap == null))) ||
                (tara == "" || oras == "" || strada == "" || (nr_cladire == 0 && (bl == "" || sc == "" || nr_ap == 0))))
        {
            throw new AdresaInvalida("Adresa nu este completa/corecta");
        }
        return new Adresa(id, tara, judet_sau_regiune, oras, sector, strada, nr_cladire, bl, sc, etaj, nr_ap);
    }

    @Override
    public String toString() {
        return "Adresa{" +
                "tara='" + tara + '\'' +
                ", judet_sau_regiune='" + judet_sau_regiune + '\'' +
                ", oras='" + oras + '\'' +
                ", sector='" + sector + '\'' +
                ", strada='" + strada + '\'' +
                ", nr_cladire=" + nr_cladire +
                ", bl='" + bl + '\'' +
                ", sc='" + sc + '\'' +
                ", etaj=" + etaj +
                ", nr_ap=" + nr_ap +
                '}';
    }
}
