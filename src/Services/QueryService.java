package Services;

import Model.*;
import Repositories.GetAConnection;
import Repositories.IBaseJDBCRepository;
import Repositories.BaseJDBCRepository;
import Repositories.Mappings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import AuditService.AuditService;

public class QueryService implements IQueryService{
    private static QueryService instance;
    private AuditService auditService;
    private QueryService(){
        auditService = new AuditService("Audit/AuditFile.csv");
    }

    public static QueryService getInstance()
    {
        if(instance == null)
            instance = new QueryService();
        return instance;
    }

    @Override
    public List<ProdusDisponibil> ProduseDupaCategorie(String idCategorie)
    {
        List<ProdusDisponibil> res = new ArrayList<>();

        String sql = "SELECT p.id, p.id_tip_produs, p.pret, p.loc_depozitare FROM " +
                Mappings.getInstance().getTableName("ProdusDisponibil") +
                " p JOIN " + Mappings.getInstance().getTableName("TipProdus") +
                " tp ON(p.id_tip_produs = tp.id) WHERE tp.id_categorie = ?";

        try(Connection connection = GetAConnection.getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, idCategorie);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                ProdusDisponibil prod = new ProdusDisponibil(rs.getString("id"),
                        rs.getString("id_tip_produs"),
                        rs.getDouble("pret"),
                        rs.getString("loc_depozitare"));
                res.add(prod);
            }

            auditService.log("SELECT FROM " + Mappings.getInstance().getTableName("ProdusDisponibil") + " by id_categorie");
            return res;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public List<ProdusDisponibil> ProduseDupaDenumireCategorie(String denumire)
    {
        List<ProdusDisponibil> res = new ArrayList<>();

        String sql = "SELECT p.id, p.id_tip_produs, p.pret, p.loc_depozitare FROM " +
                Mappings.getInstance().getTableName("ProdusDisponibil") +
                " p JOIN " + Mappings.getInstance().getTableName("TipProdus") +
                " tp ON(p.id_tip_produs = tp.id) JOIN " +
                Mappings.getInstance().getTableName("Categorie") +
                " c ON(tp.id_categorie = c.id) WHERE lower(c.denumire) = lower(?)";

        try(Connection connection = GetAConnection.getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, denumire);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                ProdusDisponibil prod = new ProdusDisponibil(rs.getString("id"),
                        rs.getString("id_tip_produs"),
                        rs.getDouble("pret"),
                        rs.getString("loc_depozitare"));
                res.add(prod);
            }

            auditService.log("SELECT FROM " + Mappings.getInstance().getTableName("ProdusDisponibil") + " by denumire categorie");
            return res;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return res;
    }
    @Override
    public List<ProdusDisponibil> ProduseDupaTip(String idTipProdus)
    {
        List<ProdusDisponibil> res = new ArrayList<>();

        String sql = "SELECT p.id, p.id_tip_produs, p.pret, p.loc_depozitare FROM " +
                Mappings.getInstance().getTableName("ProdusDisponibil") +
                " p JOIN " + Mappings.getInstance().getTableName("TipProdus") +
                " tp ON(p.id_tip_produs = tp.id) WHERE tp.id = ?";

        try(Connection connection = GetAConnection.getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, idTipProdus);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                ProdusDisponibil prod = new ProdusDisponibil(rs.getString("id"),
                        rs.getString("id_tip_produs"),
                        rs.getDouble("pret"),
                        rs.getString("loc_depozitare"));

                res.add(prod);
            }

            auditService.log("SELECT FROM " + Mappings.getInstance().getTableName("ProdusDisponibil") + " by id_tip_produs");
            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return res;
    }
    @Override
    public List<Categorie> Categorii()
    {
        List<Categorie> res = new ArrayList<>();

        String sql = "SELECT * FROM " + Mappings.getInstance().getTableName("Categorie");

        try(Connection connection = GetAConnection.getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                Categorie categorie = new Categorie(rs.getString("id"),
                        rs.getString("denumire"));

                res.add(categorie);
            }

            auditService.log("SELECT ALL FROM " + Mappings.getInstance().getTableName("Categorie"));
            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<TipProdus> TipuriProduse()
    {
        List<TipProdus> res = new ArrayList<>();

        String sql = "SELECT * FROM " + Mappings.getInstance().getTableName("TipProdus");

        try(Connection connection = GetAConnection.getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                TipProdus tipProdus = new TipProdus(rs.getString("id"),
                        rs.getString("id_categorie"),
                        rs.getString("denumire"),
                        rs.getString("specificatii"));

                res.add(tipProdus);
            }

            auditService.log("SELECT ALL FROM " + Mappings.getInstance().getTableName("TipProdus"));
            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return res;
    }
    @Override
    public List<ProdusDisponibil> ProduseDisponibile()
    {
        List<ProdusDisponibil> res = new ArrayList<>();

        String sql = "SELECT * FROM " + Mappings.getInstance().getTableName("ProdusDisponibil");

        try(Connection connection = GetAConnection.getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                ProdusDisponibil produsDisponibil = new ProdusDisponibil(
                        rs.getString("id"),
                        rs.getString("id_tip_produs"),
                        rs.getDouble("pret"),
                        rs.getString("loc_depozitare"));

                res.add(produsDisponibil);
            }

            auditService.log("SELECT ALL FROM " + Mappings.getInstance().getTableName("ProdusDisponibil"));
            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return res;
    }
    @Override
    public List<Client> Clienti()
    {
        List<Client> res = new ArrayList<>();

        String sql = "SELECT * FROM " + Mappings.getInstance().getTableName("Client");

        try(Connection connection = GetAConnection.getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                Client client =  Client.getClient(
                        rs.getString("id"),
                        rs.getString("cnp"),
                        rs.getString("nume"),
                        rs.getString("prenume"),
                        rs.getString("nr_tel"),
                        rs.getString("mail"));

                res.add(client);
            }

            auditService.log("SELECT ALL FROM " + Mappings.getInstance().getTableName("Client"));
            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return res;
    }
    @Override
    public List<Magazin> Magazine()
    {
        List<Magazin> res = new ArrayList<>();

        String sql = "SELECT * FROM " + Mappings.getInstance().getTableName("Magazin");

        try(Connection connection = GetAConnection.getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                Magazin magazin = new Magazin(
                        rs.getString("id"),
                        rs.getString("id_adresa"));

                res.add(magazin);
            }

            auditService.log("SELECT ALL FROM " + Mappings.getInstance().getTableName("Magazin"));
            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ProdusDisponibil ProdusDisponibilDupaId(String id)
    {
        IBaseJDBCRepository<ProdusDisponibil> repo = new BaseJDBCRepository<ProdusDisponibil>();

        try {
            return repo.selectById(ProdusDisponibil.class, id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Magazin> MagazineUndeSeGasesteUnProdus(String idProdus)
    {
        List<Magazin> res = new ArrayList<>();

        String sql = "SELECT m.id, m.id_adresa FROM " +
                Mappings.getInstance().getTableName("Magazin") +
                " m JOIN " + Mappings.getInstance().getTableName("ProdusDisponibil") +
                " pd ON(pd.loc_depozitare = m.id) WHERE pd.id = ?";

        try(Connection connection = GetAConnection.getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, idProdus);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                Magazin magazin = new Magazin(rs.getString("id"),
                        rs.getString("id_adresa"));

                res.add(magazin);
            }

            auditService.log("SELECT FROM " + Mappings.getInstance().getTableName("Magazin") + " by produs");
            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return res;
    }
}
