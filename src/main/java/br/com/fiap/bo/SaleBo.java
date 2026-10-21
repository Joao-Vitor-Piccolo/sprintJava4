package br.com.fiap.bo;

import br.com.fiap.dao.CustomerDAO;
import br.com.fiap.dao.SaleDAO;
import br.com.fiap.dao.SalesmanDAO;
import br.com.fiap.entities.sale.Sale;
import br.com.fiap.excecoes.ExcecoesRegraNegocio;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class SaleBo {

    SaleDAO saleDAO;

    public ArrayList<Sale> selecionarBo() throws SQLException, ClassNotFoundException {
        saleDAO = new SaleDAO();
        return (ArrayList<Sale>) saleDAO.selecionar();
    }

    public Sale buscarBo(int id) throws SQLException, ClassNotFoundException {
        SaleDAO saleDao = new SaleDAO();
        return saleDao.buscar(id);
    }

    public ArrayList<Sale> listarPorSalesmanBo(int idSalesman) throws SQLException, ClassNotFoundException {
        SaleDAO saleDao = new SaleDAO();
        return (ArrayList<Sale>) saleDao.listarSales(idSalesman);
    }

    public void inserirBo(Sale sale) throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        validarFks(sale);
        sale.setIs_started(0);
        sale.setIs_finished(0);
        sale.setCreated_at(new Date(System.currentTimeMillis()));
        SaleDAO saleDao = new SaleDAO();
        saleDao.inserir(sale);
    }

    public void atualizarBo(Sale sale) throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        SaleDAO saleDao = new SaleDAO();
        Sale atual = saleDao.buscar(sale.getId_sale());
        if (atual == null) {
            throw new ExcecoesRegraNegocio("Sale nao encontrada.");
        }
        validarFks(sale);
        Integer iniciada = sale.getIs_started_value();
        Integer finalizada = sale.getIs_finished_value();
        if (finalizada != null && finalizada == 1 && (iniciada == null || iniciada == 0)) {
            throw new ExcecoesRegraNegocio("Uma sale finalizada tambem deve estar iniciada.");
        }
        sale.setCreated_at(atual.getCreated_at());
        saleDao.atualizar(sale);
    }

    public void deletarBo(int id) throws SQLException, ClassNotFoundException {
        SaleDAO saleDao = new SaleDAO();
        saleDao.deletar(id);
    }

    public void iniciarBo(int idSale, int idSalesman)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        SaleDAO saleDao = new SaleDAO();
        Sale sale = saleDao.buscarSale(idSale, idSalesman);
        if (sale == null) {
            throw new ExcecoesRegraNegocio("Sale nao encontrada para este salesman.");
        }
        if (sale.getIs_finished()) {
            throw new ExcecoesRegraNegocio("A sale ja foi finalizada.");
        }
        if (sale.getIs_started()) {
            throw new ExcecoesRegraNegocio("Sale nao encontrada ou nao pode ser iniciada!");
        }
        String resultado = saleDao.iniciarSale(idSale, idSalesman);
        if (resultado.contains("nao")) {
            throw new ExcecoesRegraNegocio(resultado);
        }
    }

    public void finalizarBo(int idSale, int idSalesman)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        SaleDAO saleDao = new SaleDAO();
        Sale sale = saleDao.buscarSale(idSale, idSalesman);
        if (sale == null) {
            throw new ExcecoesRegraNegocio("Sale nao encontrada para este salesman.");
        }
        if (sale.getIs_finished()) {
            throw new ExcecoesRegraNegocio("A sale ja foi finalizada.");
        }
        if (!sale.getIs_started()) {
            throw new ExcecoesRegraNegocio("Sale nao encontrada ou nao pode ser finalizada!");
        }
        String resultado = saleDao.finalizarSale(idSale, idSalesman);
        if (resultado.contains("nao")) {
            throw new ExcecoesRegraNegocio(resultado);
        }
    }

    private void validarFks(Sale sale) throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        if (sale.getFk_salesman() == null || new SalesmanDAO().buscar(sale.getFk_salesman()) == null) {
            throw new ExcecoesRegraNegocio("Salesman nao encontrado.");
        }
        if (sale.getFk_customer() == null || new CustomerDAO().buscar(sale.getFk_customer()) == null) {
            throw new ExcecoesRegraNegocio("Customer nao encontrado.");
        }
    }
}
