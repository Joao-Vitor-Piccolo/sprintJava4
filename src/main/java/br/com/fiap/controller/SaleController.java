package br.com.fiap.controller;

import br.com.fiap.bo.SaleBo;
import br.com.fiap.entities.sale.Sale;
import br.com.fiap.excecoes.ExcecoesRegraNegocio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/sale")
public class SaleController {

    SaleBo saleBo = new SaleBo();

    @GetMapping
    public ArrayList<Sale> selecionarRs() throws SQLException, ClassNotFoundException {
        return saleBo.selecionarBo();
    }

    @GetMapping("/{id}")
    public Sale buscarRs(@PathVariable int id) throws SQLException, ClassNotFoundException {
        return saleBo.buscarBo(id);
    }

    @GetMapping("/salesman/{idSalesman}")
    public ArrayList<Sale> listarPorSalesmanRs(@PathVariable int idSalesman)
            throws SQLException, ClassNotFoundException {
        return saleBo.listarPorSalesmanBo(idSalesman);
    }

    @PostMapping
    public ResponseEntity<Void> inserirRs(@RequestBody Sale sale)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        saleBo.inserirBo(sale);
        URI location = URI.create("/sale/" + sale.getId_sale());
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarRs(@RequestBody Sale sale)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        saleBo.atualizarBo(sale);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRs(@PathVariable int id) throws SQLException, ClassNotFoundException {
        saleBo.deletarBo(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/iniciar/{idSalesman}")
    public ResponseEntity<Void> iniciarRs(@PathVariable int id, @PathVariable int idSalesman)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        saleBo.iniciarBo(id, idSalesman);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/finalizar/{idSalesman}")
    public ResponseEntity<Void> finalizarRs(@PathVariable int id, @PathVariable int idSalesman)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        saleBo.finalizarBo(id, idSalesman);
        return ResponseEntity.ok().build();
    }
}
