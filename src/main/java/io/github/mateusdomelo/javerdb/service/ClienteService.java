package io.github.mateusdomelo.javerdb.service;

import io.github.mateusdomelo.javerdb.domain.entity.Cliente;

public interface ClienteService {
    Cliente obterPorId(Long id);
    Cliente salvar(Cliente cliente);
    void atualizar(Long id, Cliente cliente);
    void excluir(Long id);
}
