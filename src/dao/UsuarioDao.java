/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.ConexaoBd;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;

/**
 *
 * @author vitor
 */
public class UsuarioDao {

    public void listar() {

    }

    public Usuario procuraUsuarioPelaMatricula(int matricula) {
        try {
            String sql = "select * from usuario where matricula = ? ;";
            PreparedStatement con = ConexaoBd.getConexao().prepareStatement(sql);

            con.setInt(1, matricula);
            ResultSet rs = con.executeQuery();
            Usuario user = new Usuario();
            if (rs.next()) {
                user.setIdUser(rs.getInt("idUser"));
                user.setMatricula(rs.getInt("matricula"));
                user.setSenha(rs.getString("senha"));

            }
            rs.close();
            con.close();
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
