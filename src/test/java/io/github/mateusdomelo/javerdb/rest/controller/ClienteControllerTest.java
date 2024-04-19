package io.github.mateusdomelo.javerdb.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mateusdomelo.javerdb.domain.entity.Cliente;
import io.github.mateusdomelo.javerdb.exception.ClienteNotFoundException;
import io.github.mateusdomelo.javerdb.service.ClienteService;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService serviceMock;

    @Test
    void givenCliente_whenObterPorId_thenStatus200() throws Exception {
        Long id = 1L;
        Cliente cliente = getClienteMock();
        when(serviceMock.obterPorId(id)).thenReturn(cliente);

        /* Actions */
        mockMvc.perform(
                        get("/ms/clientes/{id}", id)
                )
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.id").value(cliente.getId()),
                        jsonPath("$.nome").value(cliente.getNome()),
                        jsonPath("$.telefone").value(cliente.getTelefone()),
                        jsonPath("$.correntista").value(cliente.isCorrentista()),
                        jsonPath("$.saldo_cc").value(cliente.getSaldoCc())
                );
    }

    @Test
    void givenCliente_whenObterPorId_thenStatus404() throws Exception {
        Long id = 1L;
        when(serviceMock.obterPorId(id)).thenThrow(new ClienteNotFoundException());

        /* Actions */
        mockMvc.perform(get("/ms/clientes/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenCliente_whenSalvar_thenStatus201() throws Exception {
        Cliente cliente = getClienteMock();
        when(serviceMock.salvar(cliente)).thenReturn(cliente);

        /* Actions */
        String body = new ObjectMapper().writeValueAsString(cliente);
        mockMvc.perform(
                        post("/ms/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpect(status().isCreated())
                .andExpectAll(
                        jsonPath("$.id").hasJsonPath(),
                        jsonPath("$.nome").value(cliente.getNome()),
                        jsonPath("$.telefone").value(cliente.getTelefone()),
                        jsonPath("$.correntista").value(cliente.isCorrentista()),
                        jsonPath("$.saldo_cc").value(cliente.getSaldoCc())
                );
    }

    @Test
    void givenCliente_whenAtualizar_thenStatus204() throws Exception {
        Long id = 1L;
        Cliente cliente = getClienteMock();
        String body = new ObjectMapper().writeValueAsString(cliente);

        /* Actions */
        serviceMock.atualizar(id, cliente);
        verify(serviceMock, times(1)).atualizar(eq(id), eq(cliente));

        mockMvc.perform(
                        put("/ms/clientes/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                ).andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    void givenCliente_whenAtualizar_thenStatus404() throws Exception {
        Long id = 1L;
        Cliente cliente = getClienteMock();

        doThrow(new ClienteNotFoundException()).when(serviceMock).atualizar(eq(id), eq(cliente));

        /* Actions */
        String body = new ObjectMapper().writeValueAsString(cliente);
        mockMvc.perform(
                put("/ms/clientes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        ).andExpect(
                result -> assertInstanceOf(ClienteNotFoundException.class, result.getResolvedException())
        ).andExpect(status().isNotFound());
    }

    @Test
    void givenCliente_whenExcluir_thenStatus204() throws Exception {
        Long id = 1L;

        serviceMock.excluir(id);
        verify(serviceMock, times(1)).excluir(id);

        /* Actions */
        mockMvc.perform(
                        delete("/ms/clientes/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    void givenCliente_whenExcluir_thenStatus404() throws Exception {
        Long id = 1L;

        doThrow(new ClienteNotFoundException()).when(serviceMock).excluir(eq(id));

        /* Actions */
        mockMvc.perform(
                        delete("/ms/clientes/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                .andExpect(
                        result -> assertInstanceOf(ClienteNotFoundException.class, result.getResolvedException())
                );
    }

    private Cliente getClienteMock() {
        return Cliente.builder()
                .id(1L)
                .nome("Alguem")
                .telefone("01900040008")
                .correntista(true)
                .saldoCc(BigDecimal.valueOf(100))
                .build();
    }
}