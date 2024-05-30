package Repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Mappings {
    private static Map<String, String> ClassNameToTableName = new HashMap<String, String>();

    private static Map<String, String[][]> ClassNameToConstructorOrderedFields = new HashMap<String, String[][]>();


    private static Mappings instance = null;

    private void init_ClassNameToTableName()
    {
        ClassNameToTableName.put("Adresa", "adrese");
        ClassNameToTableName.put("Categorie", "categorie");
        ClassNameToTableName.put("Client", "clienti");
        ClassNameToTableName.put("Comanda", "comenzi");
        ClassNameToTableName.put("Depozit", "depozite");
        ClassNameToTableName.put("IstoricComanda", "istoric_comenzi");
        ClassNameToTableName.put("IstoricLivrare", "istoric_livrari");
        ClassNameToTableName.put("Livrare", "livrari");
        ClassNameToTableName.put("Magazin", "magazine");
        ClassNameToTableName.put("ProdusDisponibil", "produse_disponibile");
        ClassNameToTableName.put("ProdusVandut", "produse_vandute");
        ClassNameToTableName.put("TipProdus", "tip_produse");
        ClassNameToTableName.put("Vanzare", "vanzari");

    }

    private void init_ClassNameToConstructorOrderedFields()
    {
        ClassNameToConstructorOrderedFields.put("Adresa", new String[][]{{"String", "id"}, {"String", "tara"}, {"String", "judet_sau_regiune"}, {"String", "oras"}, {"String", "sector"}, {"String", "strada"}, {"Integer", "nr_cladire"}, {"String", "bl"}, {"String", "sc"}, {"Integer", "etaj"}, {"Integer", "nr_ap"}});
        ClassNameToConstructorOrderedFields.put("Categorie", new String[][]{{"String", "id"}, {"String", "denumire"}});
        ClassNameToConstructorOrderedFields.put("Client", new String[][]{{"String", "id"}, {"String", "cnp"}, {"String", "nume"}, {"String", "prenume"}, {"String", "nr_tel"}, {"String", "mail"}});
        ClassNameToConstructorOrderedFields.put("Comanda", new String[][]{{"String", "id"}, {"LocalDateTime", "data"}, {"Double", "suma"}, {"String", "nr_card"}, {"String", "id_client"}, {"String", "id_adresa"}});
        ClassNameToConstructorOrderedFields.put("Depozit", new String[][]{{"String", "id"}, {"String", "id_adresa"}, {"Integer", "capacitate"}});
        ClassNameToConstructorOrderedFields.put("IstoricComanda", new String[][]{{"String", "id"}, {"LocalDateTime", "data"}, {"Double", "suma"}, {"String", "nr_card"}, {"String", "id_client"}, {"String", "id_adresa"}});
        ClassNameToConstructorOrderedFields.put("IstoricLivrare", new String[][]{{"String", "id"}, {"LocalDateTime", "data"}, {"Double", "distanta_parcursa_km"}});
        ClassNameToConstructorOrderedFields.put("Livrare", new String[][]{{"String", "id"}, {"LocalDateTime", "data"}, {"Double", "distanta_parcursa_km"}});
        ClassNameToConstructorOrderedFields.put("Magazin", new String[][]{{"String", "id"}, {"String", "id_adresa"}});
        ClassNameToConstructorOrderedFields.put("ProdusDisponibil", new String[][]{{"String", "id"}, {"String", "id_tip_produs"}, {"Double", "pret"}, {"String", "loc_depozitare"}});
        ClassNameToConstructorOrderedFields.put("ProdusVandut", new String[][]{{"String", "id"}, {"String", "id_tip_produs"}, {"Double", "pret"}, {"String", "id_tranzactie"}, {"String", "id_livrare"}});
        ClassNameToConstructorOrderedFields.put("TipProdus", new String[][]{{"String", "id"}, {"String", "id_categorie"}, {"String", "denumire"}, {"String", "specificatii"}});
        ClassNameToConstructorOrderedFields.put("Vanzare", new String[][]{{"String", "id"}, {"LocalDateTime", "data"}, {"Double", "suma"}, {"String", "nr_card"}});
    }

    private Mappings()
    {
        init_ClassNameToTableName();
        init_ClassNameToConstructorOrderedFields();
    }

    public static Mappings getInstance()
    {
        if(instance == null)
            instance = new Mappings();

        return instance;
    }

    public String getTableName(String className)
    {
        return ClassNameToTableName.get(className);
    }

    public String[][] getConstructorArgs(String className)
    {
        return ClassNameToConstructorOrderedFields.get(className);
    }
}
