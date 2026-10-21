package br.com.fiap.bo;

import br.com.fiap.dao.SalesmanDAO;
import br.com.fiap.entities.users.Salesman;
import br.com.fiap.excecoes.ExcecoesRegraNegocio;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalesmanBo {

    SalesmanDAO salesmanDAO;

    public ArrayList<Salesman> selecionarBo() throws SQLException, ClassNotFoundException {
        salesmanDAO = new SalesmanDAO();
        return (ArrayList<Salesman>) salesmanDAO.selecionar();
    }

    public Salesman buscarBo(int id) throws SQLException, ClassNotFoundException {
        SalesmanDAO salesmanDao = new SalesmanDAO();
        return salesmanDao.buscar(id);
    }

    public ArrayList<Salesman> buscarPorNomeBo(String nome)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        if (nome == null || nome.isBlank()) {
            throw new ExcecoesRegraNegocio("Informe um nome.");
        }
        SalesmanDAO salesmanDao = new SalesmanDAO();
        return (ArrayList<Salesman>) salesmanDao.buscarPorNome(nome.trim());
    }

    public void inserirBo(Salesman salesman) throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        validarCampos(salesman);
        SalesmanDAO salesmanDao = new SalesmanDAO();
        salesmanDao.inserir(salesman);
    }

    public void atualizarBo(Salesman salesman) throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        validarCampos(salesman);
        SalesmanDAO salesmanDao = new SalesmanDAO();
        salesmanDao.atualizar(salesman);
    }

    public void deletarBo(int id) throws SQLException, ClassNotFoundException {
        SalesmanDAO salesmanDao = new SalesmanDAO();
        salesmanDao.deletar(id);
    }

    private void validarCampos(Salesman salesman) throws ExcecoesRegraNegocio {
        if (salesman.getName() == null || salesman.getName().isBlank()
                || salesman.getCpf() == null || salesman.getCpf().isBlank()
                || salesman.getPhone() == null || salesman.getPhone().isBlank()) {
            throw new ExcecoesRegraNegocio("O valor nao pode ficar vazio.");
        }
    }
}
