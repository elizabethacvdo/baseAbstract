package com.naldana.data.dao;


import com.naldana.data.Connection;
import com.naldana.data.entidades.Estudiante;

import java.sql.*;
import java.util.ArrayList;

public class Estudiantes {
    private final String TABLE_NAME = "estudiantes";
    private final String ID = "_id";
    private final String NOMBRE = "nombre";
    private final String APELLIDO = "apellido";
    private final String FECHA_NACIMIENTO = "fecha_nacimiento";

    /**
     * Obtiene todos los estudiantes
     *
     * @return lista de estudiantes
     */
    public ArrayList<Estudiante> getEstudiantes() {
        java.sql.Connection con = null;
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        try {
            con = Connection.getInstance().getConnection();
            Statement statement = con.createStatement();

            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM " + TABLE_NAME);

            while (resultSet.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(resultSet.getInt(ID));
                estudiante.setNombre(resultSet.getString(NOMBRE));
                estudiante.setApellido(resultSet.getString(APELLIDO));
                estudiante.setFechaNacimiento(resultSet.getDate(FECHA_NACIMIENTO));
                estudiantes.add(estudiante);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return estudiantes;
    }

    public ArrayList<Estudiante> getByNombre(String nombre) {
        java.sql.Connection con = null;
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        try {
            con = Connection.getInstance().getConnection();

            String query = "SELECT * FROM " + TABLE_NAME
                    + " WHERE " + NOMBRE + " LIKE ?";

            PreparedStatement statement = con.prepareStatement(query);

            statement.setString(1, nombre);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Estudiante estudiante = new Estudiante();

                estudiante.setId(resultSet.getInt(ID));
                estudiante.setId(resultSet.getInt(ID));
                estudiante.setNombre(resultSet.getString(NOMBRE));
                estudiante.setApellido(resultSet.getString(APELLIDO));
                estudiante.setFechaNacimiento(resultSet.getDate(FECHA_NACIMIENTO));
                estudiantes.add(estudiante);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return estudiantes;
    }

    public void insert(Estudiante estudiante) {
        java.sql.Connection con = null;
        try {
            con = Connection.getInstance().getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO " + TABLE_NAME +
                            "(" + NOMBRE + "," + APELLIDO + "," + FECHA_NACIMIENTO + ")"
                            + " VALUES(?,?,?)");
            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setString(2, estudiante.getApellido());
            preparedStatement.setDate(3, getDate(estudiante.getFechaNacimiento()));

            preparedStatement.execute();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Date getDate(java.util.Date date) {
        return new Date(date.getTime());
    }
}
