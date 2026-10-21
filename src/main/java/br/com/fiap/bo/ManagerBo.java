package br.com.fiap.bo;

import br.com.fiap.dao.ManagerDAO;
import br.com.fiap.entities.users.Manager;
import br.com.fiap.excecoes.ExcecoesRegraNegocio;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerBo {

    ManagerDAO managerDAO;

    public ArrayList<Manager> selecionarBo() throws SQLException, ClassNotFoundException {
        managerDAO = new ManagerDAO();
        return (ArrayList<Manager>) managerDAO.selecionar();
    }

    public Manager buscarBo(int id) throws SQLException, ClassNotFoundException {
        ManagerDAO managerDao = new ManagerDAO();
        return managerDao.buscar(id);
    }

    public void inserirBo(Manager manager) throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        validarCampos(manager);
        ManagerDAO managerDao = new ManagerDAO();
        managerDao.inserir(manager);
    }

    public void atualizarBo(Manager manager) throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        validarCampos(manager);
        ManagerDAO managerDao = new ManagerDAO();
        managerDao.atualizar(manager);
    }

    public void deletarBo(int id) throws SQLException, ClassNotFoundException {
        ManagerDAO managerDao = new ManagerDAO();
        managerDao.deletar(id);
    }

    private void validarCampos(Manager manager) throws ExcecoesRegraNegocio {
        if (manager.getName() == null || manager.getName().isBlank()
                || manager.getCpf() == null || manager.getCpf().isBlank()
                || manager.getPhone() == null || manager.getPhone().isBlank()) {
            throw new ExcecoesRegraNegocio("O valor nao pode ficar vazio.");
        }
    }
}
