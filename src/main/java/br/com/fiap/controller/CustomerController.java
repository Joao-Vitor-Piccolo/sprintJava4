package br.com.fiap.controller;

import br.com.fiap.bo.CustomerBo;
import br.com.fiap.entities.users.Customer;
import br.com.fiap.excecoes.ExcecoesRegraNegocio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    CustomerBo customerBo = new CustomerBo();

    @GetMapping
    public ArrayList<Customer> selecionarRs() throws SQLException, ClassNotFoundException {
        return customerBo.selecionarBo();
    }

    @GetMapping("/{id}")
    public Customer buscarRs(@PathVariable int id) throws SQLException, ClassNotFoundException {
        return customerBo.buscarBo(id);
    }

    @PostMapping
    public ResponseEntity<Void> inserirRs(@RequestBody Customer customer)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        customerBo.inserirBo(customer);
        URI location = URI.create("/customer/" + customer.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarRs(@RequestBody Customer customer)
            throws SQLException, ClassNotFoundException, ExcecoesRegraNegocio {
        customerBo.atualizarBo(customer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRs(@PathVariable int id) throws SQLException, ClassNotFoundException {
        customerBo.deletarBo(id);
        return ResponseEntity.ok().build();
    }
}
