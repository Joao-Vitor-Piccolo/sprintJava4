package br.com.fiap.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
    private final static String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private final static String USUARIO = "RM565127";
    private final static String SENHA = "130606";

    public Connection conexao() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
