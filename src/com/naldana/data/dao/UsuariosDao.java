package com.naldana.data.dao;

import com.naldana.data.entidades.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuariosDao  extends BaseDAO<Usuario> {

    public UsuariosDao() {
        table = new TableData("usuarios",
                "_id",
                new String[]{"user", "password"});
    }

    public void login(Usuario usuario) {
        List<Usuario> loged = findBy(usuario,"login");
        if( loged.size() == 1){
            usuario.setId(loged.get(0).getId());
        }
    }


    @Override
    Usuario mapToObject(ResultSet resultSet) {
        Usuario usuario = new Usuario();
        try {
            usuario.setId(resultSet.getInt(table.PRIMARY_KEY));
            usuario.setUser(resultSet.getString(table.fields[0]));
            usuario.setPassword(resultSet.getString(table.fields[1]));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    PreparedStatement getSelectStatement(java.sql.Connection con, Usuario find, String by) {

        String query = "SELECT * FROM " + table.TABLE_NAME;

        /*query = "SELECT * FROM " + TABLE_NAME + " WHERE " + USER + " = ?" +
                " AND " + PASSWORD + " = ?";*/

        PreparedStatement preparedStatement = null;
        try {
            if(table.fields[0].equals(by)){
                query += " WHERE " + table.fields[0] + " = ?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1,find.getId());
            } else if( by.equals("login")){
                query += " WHERE " + table.fields[0] + " = ? AND "+ table.fields[1] +" = ?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1,find.getId());
                preparedStatement.setString(2,find.getPassword());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return preparedStatement;
    }

    @Override
    PreparedStatement getInsertStatement(java.sql.Connection con, Usuario _new) {
        return null;
    }

    @Override
    PreparedStatement getDeleteStatement(java.sql.Connection con, Usuario deleteObject) {
        return null;
    }
}
