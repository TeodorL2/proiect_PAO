package Services;

import AuditService.AuditService;
import Model.*;
import Repositories.BaseJDBCRepository;
import Repositories.GetAConnection;
import Repositories.IBaseJDBCRepository;
import Repositories.Mappings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;

public class DeleteService implements IDeleteService{

    private static DeleteService instance;
    private AuditService auditService;
    private DeleteService(){
        auditService = new AuditService("Audit/AuditFile.csv");
    }

    public static DeleteService getInstance()
    {
        if(instance == null)
            instance = new DeleteService();
        return instance;
    }

    public Integer DeleteClientById(String id)
    {
        String sql = "DELETE FROM " +
                Mappings.getInstance().getTableName("Client") +
                " WHERE id = ?";

        try(Connection connection = GetAConnection.getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public Integer DeleteTipProdusById(String id)
    {
        IQueryService q = QueryService.getInstance();
        List<ProdusDisponibil> lista = q.ProduseDupaTip(id);

        IBaseJDBCRepository<ProdusDisponibil> repo = new BaseJDBCRepository<>();
        for(ProdusDisponibil elem : lista)
        {
            try{
                repo.delete(elem);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }


        String sql = "DELETE FROM " +
                Mappings.getInstance().getTableName("TipProdus") +
                " WHERE id = ?";

        try(Connection connection = GetAConnection.getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
