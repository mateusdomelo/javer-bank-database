package io.github.mateusdomelo.javerdb.service.impl;

import io.github.mateusdomelo.javerdb.domain.entity.Cliente;
import io.github.mateusdomelo.javerdb.domain.repository.ClienteRepository;
import io.github.mateusdomelo.javerdb.exception.ClienteNotFoundException;
import io.github.mateusdomelo.javerdb.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente obterPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
    }

    @Override
    public void atualizar(@PathVariable Long id, @RequestBody Cliente novoCliente) {
        clienteRepository.findById(id)
                .map(
                        client -> {
                            novoCliente.setId(client.getId());
                            clienteRepository.save(novoCliente);
                            return novoCliente;
                        }
                ).orElseThrow(ClienteNotFoundException::new);
    }

    @Override
    public void excluir(Long id) {
        clienteRepository.findById(id)
                .map(
                        client -> {
                            clienteRepository.deleteById(id);
                            return client;
                        }
                ).orElseThrow(ClienteNotFoundException::new);
    }
}
