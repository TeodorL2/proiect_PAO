package Services;

import AuditService.AuditService;
import Exceptions.EroareCitireDoubleInvalid;
import Model.Client;
import Model.ProdusDisponibil;
import Repositories.BaseJDBCRepository;
import Repositories.IBaseJDBCRepository;
import Repositories.Mappings;
import Utilities.Sequences;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UpdateService implements IUpdateService{

    private static UpdateService instance;
    private AuditService auditService;
    private UpdateService(){
        auditService = new AuditService("Audit/AuditFile.csv");
    }

    public static UpdateService getInstance()
    {
        if(instance == null)
            instance = new UpdateService();
        return instance;
    }

    protected static Double readDouble(Scanner scanner)
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
    protected Integer UpdateProdusDisponibil(ProdusDisponibil p)
    {
        IBaseJDBCRepository<ProdusDisponibil> repo = new BaseJDBCRepository<ProdusDisponibil>();

        try {
            return repo.update(p);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer UpdateProdusDisponibilFromStdIn()
    {
        String id;
        String id_tip_produs;
        Double pret;
        String loc_depozitare;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Produs disponibil cu noile valori:");

        System.out.print("id: ");
        id = scanner.nextLine();

        System.out.print("id_tip_produs: ");
        id_tip_produs = scanner.nextLine();

        System.out.print("pret: ");
        pret = readDouble(scanner);

        System.out.print("loc_depozitare: ");
        loc_depozitare = scanner.nextLine();

        ProdusDisponibil produsDisponibil = new ProdusDisponibil(id, id_tip_produs, pret, loc_depozitare);

        return UpdateProdusDisponibil(produsDisponibil);
    }
    @Override
    public Integer UpdatePretProduseDisponibileDinCategorie(String id_categorie, Double scale)
    {
        IQueryService qs = QueryService.getInstance();
        List<ProdusDisponibil> lista = qs.ProduseDupaCategorie(id_categorie);

        Integer nrRows = 0;
        for(ProdusDisponibil elem : lista)
        {
            elem.setPret(elem.getPret() * scale);

            Integer nr = UpdateProdusDisponibil(elem);
            if(nr != null)
                nrRows += nr;
        }
        return nrRows;
    }


    protected Integer UpdateClient(Client client)
    {
        IBaseJDBCRepository<Client> repo = new BaseJDBCRepository<Client>();

        try {
            return repo.update(client);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Integer UpdateClientFromStdIn()
    {
        String id;
        String cnp;
        String nume;
        String prenume;
        String nr_tel;
        String mail;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Client cu noile valori:");

        System.out.print("id: ");
        id = scanner.nextLine();

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

        Client client = Client.getClient(id, cnp, nume, prenume, nr_tel, mail);


        return UpdateClient(client);
    }
}
