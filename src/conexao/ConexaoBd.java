/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBd {

    public Statement statement;
    public ResultSet resultset;

    public static Connection getConexao() {
        Connection con = null;
        try {
            String serverName = "localhost";
            String mydatabase = "controlePatrimonio";
            String username = "root";
            String password = "root";
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Conectado no Banco!!! ");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado " + e.toString());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar o Banco de Dados " + e.toString());
        }
        return con;
    }

    public void executeSQL(String sql) {
        try {
            statement = ConexaoBd.getConexao().createStatement();
            resultset = statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Falha em executar o SQL!");
            e.printStackTrace();
        }
    }
}
