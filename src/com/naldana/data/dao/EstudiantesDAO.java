package com.naldana.data.dao;

import entidades.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EstudiantesDAO extends BaseDAO<Estudiante> {

    public EstudiantesDAO() {
        table = new TableData(
                "estudiantes",
                "_id",
                new String[]{"nombre", "apellido", "fecha_nacimiento"});
    }

    @Override
    Estudiante mapToObject(ResultSet resultSet) {
        Estudiante e = new Estudiante();
        try {
            e.setId(resultSet.getInt(table.PRIMARY_KEY));
            e.setNombre(resultSet.getString(table.fields[0]));
            e.setApellido(resultSet.getString(table.fields[1]));
            e.setFechaNacimiento(resultSet.getDate(table.fields[2]));
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return e;
    }

    @Override
    PreparedStatement getSelectStatement(Connection con, Estudiante find, String by) {

        String query = "SELECT * FROM " + table.TABLE_NAME + " WHERE " + by + " = ? ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(query);
            if (by.equals(table.PRIMARY_KEY)) {
                preparedStatement.setInt(1, find.getId());
            } else if (by.equals(table.fields[0])) {
                preparedStatement.setString(1, "%" + find.getNombre() + "%");
            } else if (by.equals(table.fields[1])) {
                preparedStatement.setString(1, "%" + find.getApellido() + "%");
            } else if (by.equals(table.fields[2])) {
                preparedStatement.setDate(1, getDate(find.getFechaNacimiento()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    PreparedStatement getInsertStatement(Connection con, Estudiante _new) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(
                    "INSERT INTO " + table.TABLE_NAME +
                            "(" + table.fields[0] + "," + table.fields[1] + "," + table.fields[2] + ")"
                            + " VALUES(?,?,?)");

            preparedStatement.setString(1, _new.getNombre());
            preparedStatement.setString(2, _new.getApellido());
            preparedStatement.setDate(3, getDate(_new.getFechaNacimiento()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    @Override
    protected PreparedStatement getDeleteStatement(Connection con, Estudiante deleteObject) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement("DELETE FROM " + table.TABLE_NAME +" WHERE " + table.PRIMARY_KEY + " = ?" );
            preparedStatement.setInt(1,deleteObject.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public List<Estudiante> findById(Estudiante estudiante) {
        return findBy(estudiante, table.PRIMARY_KEY);
    }

    public List<Estudiante> findByNombre(Estudiante estudiante) {
        return findBy(estudiante, table.fields[0]);
    }

    public List<Estudiante> findByApellido(Estudiante estudiante) {
        return findBy(estudiante, table.fields[1]);
    }

    public List<Estudiante> findByFechaNacimiento(Estudiante estudiante) {
        return findBy(estudiante, table.fields[2]);
    }
}
