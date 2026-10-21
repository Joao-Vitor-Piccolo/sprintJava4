package br.com.fiap.controller;

import br.com.fiap.bo.SalesmanBo;
import br.com.fiap.entities.users.Salesman;
import br.com.fiap.excecoes.ExcecoesRegraNegocio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/salesman")
public class SalesmanController {

    SalesmanBo salesmanBo = new SalesmanBo();

    @GetMapping
    public ArrayList<Salesman> selecionarRs() throws SQLException, ClassNotFoundException {
        return salesmanBo.selecionarBo();
    }

    @GetMapping("/{id}")
    public Salesman buscarRs(@PathVariable int id) throws SQLException, ClassNotFoundException {
        return salesmanBo.buscarBo(id);
    }

    @GetMapping("/nome/{nome}")
    public ArrayList<Salesman> buscarPorNomeRs(@PathVariable String nome)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        return salesmanBo.buscarPorNomeBo(nome);
    }

    @PostMapping
    public ResponseEntity<Void> inserirRs(@RequestBody Salesman salesman)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        salesmanBo.inserirBo(salesman);
        URI location = URI.create("/salesman/" + salesman.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarRs(@RequestBody Salesman salesman)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        salesmanBo.atualizarBo(salesman);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRs(@PathVariable int id) throws SQLException, ClassNotFoundException {
        salesmanBo.deletarBo(id);
        return ResponseEntity.ok().build();
    }
}
