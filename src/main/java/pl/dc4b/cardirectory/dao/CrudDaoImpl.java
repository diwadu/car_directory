package pl.dc4b.cardirectory.dao;

import pl.dc4b.cardirectory.entities.BaseEntity;
import pl.dc4b.cardirectory.helpers.DbHelper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public T create(T entity) {
        try (Connection connection = DbHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(buildInsertQuery(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            setParametersForInsert(preparedStatement, entity);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

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

    public T read(int id) {
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

    public T update(T entity) {
        try (Connection connection = DbHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(buildUpdateQuery())) {

            setParametersForUpdate(preparedStatement, entity);
            preparedStatement.setInt(entity.getNumberOfFields() + 1, entity.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

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
                if (!field.getName().equals("id"))
                    fields.add(field);
            }
            currentClass = currentClass.getSuperclass();
        }

        return fields;
    }



    protected abstract void setParametersForInsert(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract void setParametersForUpdate(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;


    protected String buildInsertQuery() {
        String columnNames = "(";
        String values = "(";

        List<Field> fields = getAllFields();

        for (int i = 0; i < fields.size(); i++) {
            columnNames += fields.get(i).getName();
            values += "?";

            if (i < fields.size() - 1) {
                columnNames += ", ";
                values += ", ";
            }
        }

        columnNames += ")";
        values += ")";

        return String.format(INSERT_QUERY, getTableName(), columnNames, values);
    }

    private String buildUpdateQuery() {
        String setClause = "";

        List<Field> fields = getAllFields();

        for (int i = 0; i < fields.size(); i++) {
            setClause += fields.get(i).getName() + "=?";

            if (i < fields.size() - 1) {
                setClause += ", ";
            }
        }

        return String.format(UPDATE_QUERY, getTableName(), setClause);
    }


    private String buildDeleteQuery() {
        return String.format(DELETE_QUERY, getTableName());
    }

    private String buildSelectByIdQuery() {
        return String.format(SELECT_BY_ID_QUERY, getTableName());
    }

    private String buildSelectAllQuery() {
        return String.format(SELECT_ALL_QUERY, getTableName());
    }



}
