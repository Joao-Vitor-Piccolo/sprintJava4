package br.com.fiap.bo;

import br.com.fiap.dao.CustomerDAO;
import br.com.fiap.entities.users.Customer;
import br.com.fiap.excecoes.ExcecoesRegraNegocio;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBo {

    CustomerDAO customerDAO;

    public ArrayList<Customer> selecionarBo() throws SQLException, ClassNotFoundException {
        customerDAO = new CustomerDAO();
        return (ArrayList<Customer>) customerDAO.selecionar();
    }

    public Customer buscarBo(int id) throws SQLException, ClassNotFoundException {
        CustomerDAO customerDao = new CustomerDAO();
        return customerDao.buscar(id);
    }

    public void inserirBo(Customer customer) throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        validarCampos(customer);
        CustomerDAO customerDao = new CustomerDAO();
        customerDao.inserir(customer);
    }

    public void atualizarBo(Customer customer) throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        validarCampos(customer);
        CustomerDAO customerDao = new CustomerDAO();
        customerDao.atualizar(customer);
    }

    public void deletarBo(int id) throws SQLException, ClassNotFoundException {
        CustomerDAO customerDao = new CustomerDAO();
        customerDao.deletar(id);
    }

    private void validarCampos(Customer customer) throws ExcecoesRegraNegocio {
        if (customer.getName() == null || customer.getName().isBlank()
                || customer.getCpf() == null || customer.getCpf().isBlank()
                || customer.getPhone() == null || customer.getPhone().isBlank()) {
            throw new ExcecoesRegraNegocio("O valor nao pode ficar vazio.");
        }
    }
}
