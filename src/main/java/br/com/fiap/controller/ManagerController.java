package br.com.fiap.controller;

import br.com.fiap.bo.ManagerBo;
import br.com.fiap.entities.users.Manager;
import br.com.fiap.excecoes.ExcecoesRegraNegocio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    ManagerBo managerBo = new ManagerBo();

    @GetMapping
    public ArrayList<Manager> selecionarRs() throws SQLException, ClassNotFoundException {
        return managerBo.selecionarBo();
    }

    @GetMapping("/{id}")
    public Manager buscarRs(@PathVariable int id) throws SQLException, ClassNotFoundException {
        return managerBo.buscarBo(id);
    }

    @PostMapping
    public ResponseEntity<Void> inserirRs(@RequestBody Manager manager)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        managerBo.inserirBo(manager);
        URI location = URI.create("/manager/" + manager.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarRs(@RequestBody Manager manager)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        managerBo.atualizarBo(manager);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRs(@PathVariable int id) throws SQLException, ClassNotFoundException {
        managerBo.deletarBo(id);
        return ResponseEntity.ok().build();
    }
}
