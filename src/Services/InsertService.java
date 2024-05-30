package Services;

import Exceptions.*;
import Exceptions.DateReadingError;
import Exceptions.EroareCitireDoubleInvalid;
import Model.*;
import Repositories.BaseJDBCRepository;
import Repositories.IBaseJDBCRepository;
import Utilities.Sequences;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InsertService implements IInsertService{
    private static InsertService instance;
    private InsertService(){}
    public static InsertService getInstance()
    {
        if(instance == null)
            instance = new InsertService();
        return instance;
    }

    public static Integer readInteger(Scanner scanner)
    {
        String line = scanner.nextLine();
        if(line.isBlank())
            return null;

        try {
            return Integer.valueOf(line);
        }
        catch(NumberFormatException e)
        {
            throw new EroareCitireIntegerInvalid("numarul introdus nu este valid");
        }
    }
    public static Double readDouble(Scanner scanner)
    {
        String line = scanner.nextLine();
        if(line.isBlank())
            return null;
        try {
            return Double.valueOf(line);
        }
        catch(NumberFormatException e)
        {
            throw new EroareCitireDoubleInvalid("numarul introdus nu este valid");
        }
    }
    @Override
    public Integer InsertAdresaFromStdIn()
    {
        String tara;
        String judet_sau_regiune;
        String oras;
        String sector;
        String strada;
        Integer nr_cladire;
        String bl;
        String sc;
        Integer etaj;
        Integer nr_ap;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Adresa:");

        System.out.print("tara: ");
        tara = scanner.nextLine();

        System.out.print("judet_sau_regiune: ");
        judet_sau_regiune = scanner.nextLine();

        System.out.print("oras: ");
        oras = scanner.nextLine();

        System.out.print("sector: ");
        sector = scanner.nextLine();

        System.out.print("strada: ");
        strada = scanner.nextLine();

        System.out.print("nr_cladire: ");
        nr_cladire = readInteger(scanner);

        System.out.print("bl: ");
        bl = scanner.nextLine();

        System.out.print("sc: ");
        sc = scanner.nextLine();

        System.out.print("etaj: ");
        etaj = readInteger(scanner);

        System.out.print("nr_ap: ");
        nr_ap = readInteger(scanner);


        Adresa adresa = Adresa.getAdresa(Sequences.nextVal("Adresa"), tara, judet_sau_regiune, oras, sector, strada, nr_cladire, bl, sc, etaj, nr_ap);

        IBaseJDBCRepository<Adresa> repo = new BaseJDBCRepository<>();

        try {
            return repo.insert(adresa);
        }
        catch (SQLException e)
        {
            //e.printStackTrace();
            return 0;
        }

    }
    @Override
    public Integer InsertCategorieFromStdIn()
    {
        String denumire;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Categorie:");

        System.out.print("denumire: ");
        denumire = scanner.nextLine();

        Categorie categorie = new Categorie(Sequences.nextVal("Categorie"), denumire);

        IBaseJDBCRepository<Categorie> repo = new BaseJDBCRepository<>();

        try {
            return repo.insert(categorie);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }

    }
    @Override
    public Integer InsertClientFromStdIn()
    {
        String cnp;
        String nume;
        String prenume;
        String nr_tel;
        String mail;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Client:");

        System.out.print("cnp: ");
        cnp = scanner.nextLine();

        System.out.print("nume: ");
        nume = scanner.nextLine();

        System.out.print("prenume: ");
        prenume = scanner.nextLine();

        System.out.print("nr_tel: ");
        nr_tel = scanner.nextLine();

        System.out.print("mail: ");
        mail = scanner.nextLine();

        Client client = Client.getClient(Sequences.nextVal("Client"), cnp, nume, prenume, nr_tel, mail);

        IBaseJDBCRepository<Client> repo = new BaseJDBCRepository<>();

        try {
            return repo.insert(client);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }

    }
    @Override
    public Integer InsertComandaFromStdIn()
    {
        LocalDateTime data = LocalDateTime.now();
        Double suma;
        String nr_card;
        String id_client;
        String id_adresa;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Comanda:");

        System.out.print("suma: ");
        suma = readDouble(scanner);

        System.out.print("nr_card: ");
        nr_card = scanner.nextLine();

        System.out.print("id_client: ");
        id_client = scanner.nextLine();

        System.out.print("id_adresa: ");
        id_adresa = scanner.nextLine();

        Comanda comanda = new Comanda(Sequences.nextVal("Comanda"), data, suma, nr_card, id_client, id_adresa);

        IBaseJDBCRepository<Comanda> repo = new BaseJDBCRepository<>();

        try {
            return repo.insert(comanda);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public Integer InsertDepozitFromStdIn()
    {
        String id_adresa;
        Integer capacitate;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Depozit:");

        System.out.print("id_adresa: ");
        id_adresa = scanner.nextLine();

        System.out.print("capacitate: ");
        capacitate = readInteger(scanner);

        Depozit depozit = new Depozit(Sequences.nextVal("Depozit"), id_adresa, capacitate);

        IBaseJDBCRepository<Depozit> repo = new BaseJDBCRepository<>();

        try {
            return repo.insert(depozit);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
//    public Integer InsertIstoricComandaFromStdIn()
//    {
//
//    }
//    public Integer InsertIstoricLivrareFromStdIn()
//    {
//
//    }
    @Override
    public Integer InsertLivrareFromStdIn()
    {
        LocalDateTime data;
        Double distanta_parcursa_km;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Livrare:");

        try {
            System.out.print("data (dd.mm.yyyy): ");
            String input = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = LocalDate.parse(input, formatter);
            data = date.atStartOfDay();
        }catch(DateTimeParseException e)
        {
            throw new DateReadingError("format nerespectat");
        }

        System.out.print("distanta_parcursa_km: ");
        distanta_parcursa_km = readDouble(scanner);

        Livrare livrare = new Livrare(Sequences.nextVal("Livrare"), data, distanta_parcursa_km);

        IBaseJDBCRepository<Livrare> repo = new BaseJDBCRepository<>();

        try {
            return repo.insert(livrare);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public Integer InsertMagazinFromStdIn()
    {
        String id_adresa;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Magazin:");

        System.out.print("id_adresa: ");
        id_adresa = scanner.nextLine();

        Magazin magazin = new Magazin(Sequences.nextVal("Magazin"), id_adresa);

        IBaseJDBCRepository<Magazin> repo = new BaseJDBCRepository<>();

        try {
            return repo.insert(magazin);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public Integer InsertProdusDisponibilFromStdIn()
    {
        String id_tip_produs;
        Double pret;
        String loc_depozitare;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Produs disponibil:");

        System.out.print("id_tip_produs: ");
        id_tip_produs = scanner.nextLine();

        System.out.print("pret: ");
        pret = readDouble(scanner);

        System.out.print("loc_depozitare: ");
        loc_depozitare = scanner.nextLine();

        ProdusDisponibil produsDisponibil = new ProdusDisponibil(Sequences.nextVal("ProdusDisponibil"), id_tip_produs, pret, loc_depozitare);

        IBaseJDBCRepository<ProdusDisponibil> repo = new BaseJDBCRepository<>();

        try {
            return repo.insert(produsDisponibil);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

//    public Integer InsertProdusVandutFromStdIn()
//    {
//
//    }
    @Override
    public Integer InsertTipProdusFromStdIn()
    {
        String id_categorie;
        String denumire;
        String specificatii;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Tip produs:");

        System.out.print("id_categorie: ");
        id_categorie = scanner.nextLine();

        System.out.print("denumire: ");
        denumire = scanner.nextLine();

        System.out.print("specificatii: ");
        specificatii = scanner.nextLine();

        TipProdus tipProdus = new TipProdus(Sequences.nextVal("TipProdus"), id_categorie, denumire, specificatii);

        IBaseJDBCRepository<TipProdus> repo = new BaseJDBCRepository<>();

        try {
            return repo.insert(tipProdus);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public Integer InsertVanzareFromStdIn()
    {
        LocalDateTime data = LocalDateTime.now();
        Double suma;
        String nr_card;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Vanzare:");

        System.out.print("suma: ");
        suma = readDouble(scanner);

        System.out.print("nr_card: ");
        nr_card = scanner.nextLine();

        Vanzare vanzare = new Vanzare(Sequences.nextVal("Vanzare"), data, suma, nr_card);

        IBaseJDBCRepository<Vanzare> repo = new BaseJDBCRepository<>();

        try {
            return repo.insert(vanzare);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
