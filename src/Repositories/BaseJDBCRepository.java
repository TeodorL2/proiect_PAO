package Repositories;

import AuditService.AuditService;

import java.math.BigDecimal;
import java.sql.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BaseJDBCRepository<T> implements IBaseJDBCRepository<T>{
    private final String LogFileName = "Audit/AuditFile.csv";
    private AuditService auditService;
    public BaseJDBCRepository(){
        auditService = new AuditService(LogFileName);
    }

    @Override
    public Connection getDBConnection() throws SQLException
    {
        return DriverManager.getConnection(DBConnectionInfo.ConnectionUrl, DBConnectionInfo.User, DBConnectionInfo.Password);
    }

    @Override
    public T selectById(Class<T> type, String id) throws SQLException {
        String className = type.getSimpleName();
        String tableName = Mappings.getInstance().getTableName(className);
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (Connection connection = getDBConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                auditService.log("SELECT FROM " + tableName);
                return mapResultSetToEntity(type, resultSet);
            }
        }
        auditService.log("SELECT FROM " + tableName);
        return null;
    }

    @Override
    public Integer insert(T entitate) throws SQLException {
        String className = entitate.getClass().getSimpleName();
        String tableName = Mappings.getInstance().getTableName(className);
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        StringBuilder placeholders = new StringBuilder();
        for (Field field : getAllFields(entitate.getClass())) {
            field.setAccessible(true);
            columns.add(field.getName());
            try {
                values.add(field.get(entitate));
                placeholders.append("?,");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        placeholders.deleteCharAt(placeholders.length() - 1);
        String sql = "INSERT INTO " + tableName + " (" + String.join(",", columns) + ") VALUES (" + placeholders + ")";
        try (Connection connection = getDBConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < values.size(); i++) {
                statement.setObject(i + 1, values.get(i));
            }
            Integer nrRows = statement.executeUpdate();
            auditService.log("INSERT INTO " + tableName);
            return nrRows;

        }
    }

    @Override
    public Integer update(T entitate) throws SQLException {
        String className = entitate.getClass().getSimpleName();
        String tableName = Mappings.getInstance().getTableName(className);
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        for(Field field : getAllFields(entitate.getClass()))
        {
            field.setAccessible(true);
            columns.add(field.getName());
            try
            {
                values.add(field.get(entitate));
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }

        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
        for(String field: columns)
        {
            sql.append(field);
            sql.append(" = ?, ");
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append("WHERE id = ?");

        try(Connection connection = getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
            for(int i=0;i<values.size(); i++)
            {
                preparedStatement.setObject(i+1, values.get(i));
            }
            preparedStatement.setObject(values.size() + 1, findField(entitate.getClass(), "id").get(entitate));

            Integer nrRows = preparedStatement.executeUpdate();
            auditService.log("UPDATE " + tableName);
            return nrRows;
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Integer delete(T entitate) throws SQLException {
        String className = entitate.getClass().getSimpleName();
        String tableName = Mappings.getInstance().getTableName(className);

        Field idField = findField(entitate.getClass(), "id");
        idField.setAccessible(true);
        String id = null;

        try {
            id = (String) idField.get(entitate);
        }
        catch(IllegalAccessException e)
        {
            e.printStackTrace();
        }

        String sql = "DELETE FROM " + tableName + " WHERE id = ?";

        try(Connection connection = getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, id);

            Integer nrRows = preparedStatement.executeUpdate();
            auditService.log("DELETE FROM " + tableName);
            return nrRows;
        }


        // return 0;
    }

    private T mapResultSetToEntity(Class<T> type, ResultSet resultSet) throws SQLException {
        try {
            T entity = type.getDeclaredConstructor().newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Field field = findField(type, columnName);

                if (field != null) {
                    field.setAccessible(true);

                    field.setAccessible(true);
                    Object value = resultSet.getObject(i);

                    // Convert BigDecimal to Double if needed
                    if (value instanceof BigDecimal && field.getType().equals(Double.class)) {
                        value = ((BigDecimal) value).doubleValue();
                    }

                    field.set(entity, value);
                }
            }
            return entity;
        } catch (Exception e) {
            throw new SQLException("Failed to map ResultSet to entity", e);
        }
    }

    private List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        while (type != null) {
            for (Field field : type.getDeclaredFields()) {
                fields.add(field);
            }
            type = type.getSuperclass();
        }
        return fields;
    }

    private Field findField(Class<?> type, String fieldName) {
        while (type != null) {
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equalsIgnoreCase(fieldName)) {
                    return field;
                }
            }
            type = type.getSuperclass();
        }
        return null;
    }
}
