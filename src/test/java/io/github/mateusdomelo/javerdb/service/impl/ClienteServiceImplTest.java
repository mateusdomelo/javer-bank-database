package io.github.mateusdomelo.javerdb.service.impl;

import io.github.mateusdomelo.javerdb.domain.entity.Cliente;
import io.github.mateusdomelo.javerdb.domain.repository.ClienteRepository;
import io.github.mateusdomelo.javerdb.exception.ClienteNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {ClienteServiceImpl.class})
class ClienteServiceImplTest {
    @MockBean
    private ClienteRepository repository;

    @Autowired
    private ClienteServiceImpl clienteService;

    @Test
    void givenCliente_whenSalvar_thenReturnCliente() {
        /* Setup */
        Cliente cliente = new Cliente();

        /* Behaviors & Actions */
        when(repository.save(cliente)).thenReturn(cliente);
        Cliente result = clienteService.salvar(cliente);

        /* Verifications  */
        assertEquals(cliente, result);
        assertInstanceOf(Cliente.class, result);
    }

    @Test
    void givenClienteId_whenObterPorId_thenReturnCliente() {
        /* Setup */
        Long id = 1L;
        Cliente cliente = new Cliente();

        /* Behaviors & Actions */
        when(repository.findById(id)).thenReturn(Optional.of(cliente));
        Cliente result = clienteService.obterPorId(id);

        /* Verifications */
        assertEquals(cliente, result);
        assertInstanceOf(Cliente.class, result);
    }

    @Test
    void givenInvalidClienteId_whenObterPorId_thenThrowClienteNotFoundException() {
        /* Setup */
        Long id = 1L;

        /* Behaviors & Actions */
        when(repository.findById(id)).thenThrow(ClienteNotFoundException.class);

        /* Verifications */
        assertThrows(ClienteNotFoundException.class, () -> clienteService.obterPorId(id));
    }

    @Test
    void givenCliente_whenAtualizar_thenUpdateCliente() {
        /* Setup */
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        Cliente newCliente = new Cliente();

        /* Behaviors & Actions */
        when(repository.findById(id)).thenReturn(Optional.of(cliente));

        clienteService.atualizar(id, newCliente);

        /* Verifications  */
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(cliente);
    }

    @Test
    void givenInvalidCliente_whenAtualizar_thenThrowClienteNotFoundException() {
        /* Setup */
        Long id = 1L;
        Cliente cliente = new Cliente();

        /* Actions/Behavior */
        when(repository.findById(id)).thenReturn(Optional.empty())
                .thenThrow(ClienteNotFoundException.class);

        /* Verifications  */
        assertThrows(ClienteNotFoundException.class, () -> clienteService.atualizar(id, cliente));
    }

    @Test
    void givenClienteId_whenExcluir_thenDeleteClienteByItsId() {
        /* Setup */
        Long id = 1L;
        Cliente cliente = new Cliente();

        /* Behaviors & Actions */
        when(repository.findById(id)).thenReturn(Optional.of(cliente));

        clienteService.excluir(id);

        /* Verifications */
        verify(repository, atLeastOnce()).findById(id);
        verify(repository, atMostOnce()).deleteById(id);
    }

    @Test
    void givenInvalidClienteId_whenExcluir_thenThrowClienteNotFoundException() {
        /* Setup */
        Long id = 1L;

        /* Actions/Behavior */
        when(repository.findById(id)).thenReturn(Optional.empty())
                .thenThrow(ClienteNotFoundException.class);

        /* Verifications */
        assertThrows(ClienteNotFoundException.class, () -> clienteService.excluir(id));
    }
}