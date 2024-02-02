package pl.dc4b.cardirectory.dao;

import pl.dc4b.cardirectory.entities.BaseEntity;
import pl.dc4b.cardirectory.helpers.DbHelper;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CrudDaoImpl<T extends BaseEntity> implements CrudDao<T> {
    private final Class<T> entityType;
    private static final String SELECT_ALL_QUERY = "SELECT * FROM %s";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM %s WHERE id=?";
    private static final String INSERT_QUERY = "INSERT INTO %s %s VALUES %s";
    private static final String UPDATE_QUERY = "UPDATE %s SET %s WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM %s WHERE id=?";
    public CrudDaoImpl(Class<T> entityType) {
        this.entityType = entityType;
    }

    public abstract String getTableName();


    @Override
    public void create(T entity) {
        try (Connection connection = DbHelper.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(buildInsertQuery())) {
                setParametersForInsert(preparedStatement, entity);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    try (Statement stmt = connection.createStatement()) {
                        try (ResultSet resultSet = stmt.executeQuery("SELECT LAST_INSERT_ROWID()")) {
                            if (resultSet.next()) {
                                entity.setId(resultSet.getInt(1));
                            } else {
                                throw new SQLException("Failed to retrieve the last inserted row ID.");
                            }
                        }
                    }
                } else {
                    throw new SQLException("Creating entity failed, no rows affected.");
                }

                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getAll() {
        List<T> entities = new ArrayList<>();

        try (Connection connection = DbHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(buildSelectAllQuery());
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                T entity = mapResultSetToEntity(resultSet);
                entities.add(entity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }

    @Override
    public T getById(int id) {
        T entity = null;

        try (Connection connection = DbHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(buildSelectByIdQuery())) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    entity = mapResultSetToEntity(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public void update(T entity) {
        try (Connection connection = DbHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(buildUpdateQuery())) {

            setParametersForUpdate(preparedStatement, entity);
            preparedStatement.setInt(entity.getNumberOfFields() + 1, entity.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try (Connection connection = DbHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(buildDeleteQuery())) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Field> getAllFields() {
        List<Field> fields = new ArrayList<>();

        Class<?> currentClass = entityType;
        while (currentClass != null) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (field.getName().equals("id") || field.isAnnotationPresent(IgnoreInSql.class))
                    continue;
                else fields.add(field);
            }
            currentClass = currentClass.getSuperclass();
        }

        return fields;
    }

    protected abstract void setParametersForInsert(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract void setParametersForUpdate(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    protected String buildInsertQuery() {
        StringBuilder columnNames = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");

        List<Field> fields = getAllFields();

        for (int i = 0; i < fields.size(); i++) {
            columnNames.append(fields.get(i).getName());
            values.append("?");

            if (i < fields.size() - 1) {
                columnNames.append(", ");
                values.append(", ");
            }
        }

        columnNames.append(")");
        values.append(")");

        return String.format(INSERT_QUERY, getTableName(), columnNames.toString(), values.toString());
    }

    protected String buildUpdateQuery() {
        StringBuilder setClause = new StringBuilder();

        List<Field> fields = getAllFields();

        for (int i = 0; i < fields.size(); i++) {
            setClause.append(fields.get(i).getName()).append("=?");

            if (i < fields.size() - 1) {
                setClause.append(", ");
            }
        }

        return String.format(UPDATE_QUERY, getTableName(), setClause.toString());
    }

    private String buildDeleteQuery() {
        return String.format(DELETE_QUERY, getTableName());
    }

    private String buildSelectByIdQuery() {
        return String.format(SELECT_BY_ID_QUERY, getTableName());
    }

    protected String buildSelectAllQuery() {
        return String.format(SELECT_ALL_QUERY, getTableName());
    }
}
