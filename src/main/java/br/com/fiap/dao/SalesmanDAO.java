package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.users.Salesman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesmanDAO {

    public Connection minhaConexao;

    public SalesmanDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Salesman salesman) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO SALESMAN (NOME, CPF, TELEFONE) VALUES (?,?,?)",
                new String[] { "ID_SALESMAN" })) {
            stmt.setString(1, salesman.getName());
            stmt.setString(2, salesman.getCpf());
            stmt.setString(3, salesman.getPhone());
            stmt.executeUpdate();
            salesman.setId(obterIdGerado(stmt));
        }
        return "Salesman cadastrado com sucesso!";
    }

    public String atualizar(Salesman salesman) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE SALESMAN SET NOME=?, CPF=?, TELEFONE=? WHERE ID_SALESMAN=?")) {
            stmt.setString(1, salesman.getName());
            stmt.setString(2, salesman.getCpf());
            stmt.setString(3, salesman.getPhone());
            stmt.setInt(4, salesman.getId());
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Salesman atualizado com sucesso!"
                    : "Salesman nao encontrado!";
        }
    }

    public String deletar(int id) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM SALESMAN WHERE ID_SALESMAN=?")) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Salesman deletado com sucesso!"
                    : "Salesman nao encontrado!";
        }
    }

    public Salesman buscar(int id) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM SALESMAN WHERE ID_SALESMAN=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapSalesman(rs);
                }
            }
        }
        return null;
    }

    public List<Salesman> selecionar() throws SQLException {
        List<Salesman> salesmen = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM SALESMAN ORDER BY ID_SALESMAN");
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                salesmen.add(mapSalesman(rs));
            }
        }
        return salesmen;
    }

    public List<Salesman> buscarPorNome(String nome) throws SQLException {
        List<Salesman> salesmen = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM SALESMAN WHERE UPPER(NOME) = UPPER(?) ORDER BY ID_SALESMAN")) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    salesmen.add(mapSalesman(rs));
                }
            }
        }
        return salesmen;
    }

    private Salesman mapSalesman(ResultSet rs) throws SQLException {
        return new Salesman(
                rs.getInt("ID_SALESMAN"),
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
