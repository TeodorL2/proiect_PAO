package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetAConnection {
    public static Connection getDBConnection() throws SQLException
    {
        return DriverManager.getConnection(DBConnectionInfo.ConnectionUrl, DBConnectionInfo.User, DBConnectionInfo.Password);
    }
}
