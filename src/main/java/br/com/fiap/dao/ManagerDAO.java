package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.users.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {

    public Connection minhaConexao;

    public ManagerDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Manager managerUser) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO MANAGER (NOME, CPF, TELEFONE) VALUES (?,?,?)",
                new String[] { "ID_MANAGER" })) {
            stmt.setString(1, managerUser.getName());
            stmt.setString(2, managerUser.getCpf());
            stmt.setString(3, managerUser.getPhone());
            stmt.executeUpdate();
            managerUser.setId(obterIdGerado(stmt));
        }
        return "Manager cadastrado com sucesso!";
    }

    public String atualizar(Manager managerUser) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE MANAGER SET NOME=?, CPF=?, TELEFONE=? WHERE ID_MANAGER=?")) {
            stmt.setString(1, managerUser.getName());
            stmt.setString(2, managerUser.getCpf());
            stmt.setString(3, managerUser.getPhone());
            stmt.setInt(4, managerUser.getId());
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Manager atualizado com sucesso!"
                    : "Manager nao encontrado!";
        }
    }

    public String deletar(int id) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM MANAGER WHERE ID_MANAGER=?")) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Manager deletado com sucesso!"
                    : "Manager nao encontrado!";
        }
    }

    public Manager buscar(int id) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM MANAGER WHERE ID_MANAGER=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Manager(
                            rs.getInt("ID_MANAGER"),
                            rs.getString("NOME"),
                            rs.getString("CPF"),
                            rs.getString("TELEFONE"));
                }
            }
        }
        return null;
    }

    public List<Manager> selecionar() throws SQLException {
        List<Manager> managers = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM MANAGER ORDER BY ID_MANAGER");
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                managers.add(new Manager(
                        rs.getInt("ID_MANAGER"),
                        rs.getString("NOME"),
                        rs.getString("CPF"),
                        rs.getString("TELEFONE")));
            }
        }
        return managers;
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
