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
import modelo.Funcionario;
import modelo.Item;
import modelo.Locacao;

/**
 *
 * @author vitor
 */
public class LocacaoDaoImpl {

    public int procuraIdFuncPeloRE(int RE) {
        try {
            String sql = "select idFunc from funcionario where RE = ? ;";
            PreparedStatement con = ConexaoBd.getConexao().prepareStatement(sql);

            con.setInt(1, RE);
            ResultSet rs = con.executeQuery();
            Funcionario func = new Funcionario();

            if (rs.next()) {
                func.setIdFunc(rs.getInt("idFunc"));
            }
            int idFunc = func.getIdFunc();
            rs.close();
            con.close();
            return idFunc;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int procuraIdPeloCodigoItem(int codigo) {
        try {
            String sql = "select idItem from item where codigoItem = ? ;";
            PreparedStatement con = ConexaoBd.getConexao().prepareStatement(sql);

            con.setInt(1, codigo);
            ResultSet rs = con.executeQuery();
            Item item = new Item();

            if (rs.next()) {
                item.setIdItem(rs.getInt("idItem"));

            }
            int idItem = item.getIdItem();
            rs.close();
            con.close();
            return idItem;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void inserirLocacao(int idItem, int idFunc, Locacao loc) {
        String insertTableSQL = "INSERT INTO locacao" + "(fk_IDfunc, fk_IDItem,dataLocacao,dataDevolucao) VALUES" + "(?,?,?,?) ;";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = ConexaoBd.getConexao().prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, idItem);
            preparedStatement.setInt(2, idFunc);
            preparedStatement.setString(3, loc.getDataLocacao());
            preparedStatement.setString(4, loc.getDataDevolucao());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
