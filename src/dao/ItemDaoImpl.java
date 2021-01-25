/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.ConexaoBd;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Funcionario;
import modelo.Item;
import modelo.Pessoa;

/**
 *
 * @author vitor
 */
public class ItemDaoImpl implements ItemDao {

    @Override
    public void inserir(Item item) {
        String insertTableSQL = "INSERT INTO item" + "(codigoItem, descricaoItem) VALUES" + "(?,?) ;";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = ConexaoBd.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, item.getCodigoItem());
            preparedStatement.setString(2, item.getDescricaoItem());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void inserirLocacao() {
//        String insertTableSQL = "INSERT INTO item" + "(codigoItem, descricaoItem) VALUES" + "(?,?) ;";
//        PreparedStatement preparedStatement;
//        Item item = new Item();
//        try {
//            preparedStatement = ConexaoBd.getConexao().prepareStatement(insertTableSQL);
//            preparedStatement.setInt(1, item.getCodigoItem());
//            preparedStatement.setString(2, item.getDescricaoItem());
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public Item procuraItemPeloCodigo(int codigo) {
        try {
            String sql = "select * from item where codigoItem = ? ;";
            PreparedStatement con = ConexaoBd.getConexao().prepareStatement(sql);

            con.setInt(1, codigo);
            ResultSet rs = con.executeQuery();
            Item item = new Item();
            if (rs.next()) {
                item.setDescricaoItem(rs.getString("descricaoItem"));
                item.setIdItem(rs.getInt("idItem"));
            }
            rs.close();
            con.close();
            return item;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void alterar(Item item) {

        String insertTableSQL = "UPDATE item SET descricaoItem = ?  WHERE idItem = ? ;";
        PreparedStatement preparedStatement;

        Item itemBuscaId = procuraItemPeloCodigo(item.getCodigoItem());
        try {
            preparedStatement = ConexaoBd.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setString(1, item.getDescricaoItem());
            preparedStatement.setInt(2, itemBuscaId.getIdItem());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(Item item) {
        String insertTableSQL = "DELETE FROM item WHERE codigoItem = ? ; ";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = ConexaoBd.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, item.getCodigoItem());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buscar(int idItem) {
        try {
            String sql = "select * from usuario where idItem = ? ;";
            PreparedStatement con = ConexaoBd.getConexao().prepareStatement(sql);

            con.setInt(1, idItem);
            ResultSet rs = con.executeQuery();
            Item item = new Item();

            if (rs.next()) {
                item.setIdItem(rs.getInt("idItem"));
                item.setCodigoItem(rs.getInt("codigoItem"));
                item.setDescricaoItem(rs.getString("descricaoItem"));

            }
            rs.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
