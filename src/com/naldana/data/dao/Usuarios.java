package com.naldana.data.dao;

import com.naldana.data.entidades.Usuario;

import com.naldana.data.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Usuarios {
    private static final String TABLE_NAME = "usuario";
    private static final String ID = "_id";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private java.sql.Connection con = null;

    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            con = Connection.getInstance().getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getInt(ID));
                usuario.setUser(resultSet.getString(USER));
                usuario.setPassword(resultSet.getString(PASSWORD));

                usuarios.add(usuario);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConeccion();
        }
        return usuarios;
    }

    public void login(Usuario usuario) throws Exception {
        try {
            con = Connection.getInstance().getConnection();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + USER + " = ?" +
                    " AND " + PASSWORD + " = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, usuario.getUser());
            preparedStatement.setString(2, usuario.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario.setId(resultSet.getInt(ID));
            } else {
                throw new Exception("Usuario desconocido");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConeccion();
        }
    }

    private void cerrarConeccion() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
