package com.mztech.ms_cliente.controller;


import com.mztech.ms_cliente.model.Cliente;
import com.mztech.ms_cliente.repository.ClienteRepository;
import com.mztech.ms_cliente.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @Operation(summary="Cadastra um novo cliente.")
    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody @Valid Cliente cliente) {
        Cliente salvo = clienteService.criar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
    
    @Operation(summary = "Cadastra um bloco de clientes em lote.")
    @PostMapping("/lote")
    public ResponseEntity<List<Cliente>> criarEmLote(@RequestBody @Valid List<Cliente> clientes) {
        List<Cliente> salvos = clienteService.criarEmLote(clientes);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvos);
    }    

    @Operation(summary = "Lista todos os clientes cadastrados.")
    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listarTodos();
    }

    @Operation(summary = "Busca o cliente pelo ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }
    

    @Operation(summary="Atualiza os dados, por ID, de um cliente já existente.")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(clienteService.atualizar(id, cliente));
    }

    @Operation(summary = "Faz a deleção, por ID, de um cliente existente.")
    @DeleteMapping("/{id}")    
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}