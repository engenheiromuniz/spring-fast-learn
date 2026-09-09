package com.mztech.ms_cliente.service;

import com.mztech.ms_cliente.event.ClienteCriadoEvent;
import com.mztech.ms_cliente.exception.ClienteNaoEncontradoException;
import com.mztech.ms_cliente.kafka.ClienteEventProducer;
import com.mztech.ms_cliente.model.Cliente;
import com.mztech.ms_cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteEventProducer clienteEventProducer;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }

    @Transactional
    public Cliente criar(Cliente cliente) {
        Cliente salvo = clienteRepository.save(cliente);

        ClienteCriadoEvent evento = new ClienteCriadoEvent(
                salvo.getId(), salvo.getNome(), salvo.getEmail()
        );
        clienteEventProducer.publicarClienteCriado(evento);

        return salvo;
    }

    @Transactional
    public List<Cliente> criarEmLote(List<Cliente> clientes) {
        // 1. Salva a lista completa eficientemente no banco de dados
        List<Cliente> salvos = clienteRepository.saveAll(clientes);

        // 2. Itera sobre os clientes salvos (já com IDs gerados) e dispara os eventos no Kafka
        salvos.forEach(cliente -> {
            ClienteCriadoEvent evento = new ClienteCriadoEvent(
                    cliente.getId(), cliente.getNome(), cliente.getEmail()
            );
            clienteEventProducer.publicarClienteCriado(evento);
        });

        return salvos;
    }

    @Transactional
    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = buscarPorId(id);
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        return clienteRepository.save(clienteExistente);
    }

    @Transactional
    public void deletar(Long id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }
}
