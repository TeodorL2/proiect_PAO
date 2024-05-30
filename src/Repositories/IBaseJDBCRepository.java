package Repositories;

import Model.Entitate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IBaseJDBCRepository<T> {
    Connection getDBConnection() throws SQLException;
    T selectById(Class<T> type, String id) throws SQLException;
    Integer insert(T entitate) throws SQLException;
    Integer update(T entitate) throws SQLException;
    Integer delete(T entitate) throws SQLException;
//    Integer delete(String id) throws SQLException;
}
