package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.users.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public Connection minhaConexao;

    public CustomerDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Customer customer) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO CUSTOMER (NOME, CPF, TELEFONE) VALUES (?,?,?)",
                new String[] { "ID_CUSTOMER" })) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getCpf());
            stmt.setString(3, customer.getPhone());
            stmt.executeUpdate();
            customer.setId(obterIdGerado(stmt));
        }
        return "Customer cadastrado com sucesso!";
    }

    public String atualizar(Customer customer) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE CUSTOMER SET NOME=?, CPF=?, TELEFONE=? WHERE ID_CUSTOMER=?")) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getCpf());
            stmt.setString(3, customer.getPhone());
            stmt.setInt(4, customer.getId());
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Customer atualizado com sucesso!"
                    : "Customer nao encontrado!";
        }
    }

    public String deletar(int id) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM CUSTOMER WHERE ID_CUSTOMER=?")) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Customer deletado com sucesso!"
                    : "Customer nao encontrado!";
        }
    }

    public Customer buscar(int id) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM CUSTOMER WHERE ID_CUSTOMER=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapCustomer(rs);
                }
            }
        }
        return null;
    }

    public List<Customer> selecionar() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM CUSTOMER ORDER BY ID_CUSTOMER");
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                customers.add(mapCustomer(rs));
            }
        }
        return customers;
    }

    private Customer mapCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("ID_CUSTOMER"),
                rs.getString("NOME"),
                rs.getString("CPF"),
                rs.getString("TELEFONE"));
    }

    private int obterIdGerado(PreparedStatement stmt) throws SQLException {
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        throw new SQLException("O banco nao retornou o ID gerado.");
    }
}
