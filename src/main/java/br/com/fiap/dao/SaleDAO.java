package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.sale.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

    public Connection minhaConexao;

    public SaleDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Sale sale) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO SALE (FK_SALESMAN, FK_CUSTOMER, IS_STARTED, IS_FINISHED, CREATED_AT) VALUES (?,?,?,?,?)",
                new String[] { "ID_SALE" })) {
            stmt.setInt(1, sale.getFk_salesman());
            stmt.setInt(2, sale.getFk_customer());
            stmt.setInt(3, sale.getIs_started_value());
            stmt.setInt(4, sale.getIs_finished_value());
            stmt.setDate(5, sale.getCreated_at());
            stmt.executeUpdate();
            sale.setId_sale(obterIdGerado(stmt));
        }
        return "Sale cadastrada com sucesso!";
    }

    public String atualizar(Sale sale) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE SALE SET FK_SALESMAN=?, FK_CUSTOMER=?, IS_STARTED=?, IS_FINISHED=?, CREATED_AT=? WHERE ID_SALE=?")) {
            stmt.setInt(1, sale.getFk_salesman());
            stmt.setInt(2, sale.getFk_customer());
            stmt.setInt(3, sale.getIs_started_value());
            stmt.setInt(4, sale.getIs_finished_value());
            stmt.setDate(5, sale.getCreated_at());
            stmt.setInt(6, sale.getId_sale());
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Sale atualizada com sucesso!"
                    : "Sale nao encontrada!";
        }
    }

    public String deletar(int id) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM SALE WHERE ID_SALE=?")) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Sale deletada com sucesso!"
                    : "Sale nao encontrada!";
        }
    }

    public Sale buscar(int id) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM SALE WHERE ID_SALE=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapSale(rs);
                }
            }
        }
        return null;
    }

    public List<Sale> selecionar() throws SQLException {
        List<Sale> sales = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM SALE ORDER BY ID_SALE");
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                sales.add(mapSale(rs));
            }
        }
        return sales;
    }

    public List<Sale> listarSales(int idSalesman) throws SQLException {
        List<Sale> sales = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM SALE WHERE FK_SALESMAN = ?")) {
            stmt.setInt(1, idSalesman);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    sales.add(mapSale(rs));
                }
            }
        }
        return sales;
    }

    public Sale buscarSale(int idSale, int idSalesman) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM SALE WHERE ID_SALE = ? AND FK_SALESMAN = ?")) {
            stmt.setInt(1, idSale);
            stmt.setInt(2, idSalesman);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapSale(rs);
                }
            }
        }
        return null;
    }

    public String iniciarSale(int idSale, int idSalesman) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE SALE SET IS_STARTED = 1 "
                        + "WHERE ID_SALE = ? AND FK_SALESMAN = ? "
                        + "AND IS_STARTED = 0 AND IS_FINISHED = 0")) {
            stmt.setInt(1, idSale);
            stmt.setInt(2, idSalesman);
            int updated = stmt.executeUpdate();
            if (updated == 0) {
                return "Sale nao encontrada ou nao pode ser iniciada!";
            }
            return "Sale iniciada com sucesso!";
        }
    }

    public String finalizarSale(int idSale, int idSalesman) throws SQLException {
        try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE SALE SET IS_FINISHED = 1 "
                        + "WHERE ID_SALE = ? AND FK_SALESMAN = ? "
                        + "AND IS_STARTED = 1 AND IS_FINISHED = 0")) {
            stmt.setInt(1, idSale);
            stmt.setInt(2, idSalesman);
            int updated = stmt.executeUpdate();
            if (updated == 0) {
                return "Sale nao encontrada ou nao pode ser finalizada!";
            }
            return "Sale finalizada com sucesso!";
        }
    }

    private Sale mapSale(ResultSet rs) throws SQLException {
        return new Sale(
                rs.getInt("ID_SALE"),
                rs.getInt("FK_SALESMAN"),
                rs.getInt("FK_CUSTOMER"),
                rs.getInt("IS_STARTED"),
                rs.getInt("IS_FINISHED"),
                rs.getDate("CREATED_AT"));
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
