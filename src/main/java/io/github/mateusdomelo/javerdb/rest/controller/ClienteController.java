package io.github.mateusdomelo.javerdb.rest.controller;

import io.github.mateusdomelo.javerdb.domain.entity.Cliente;
import io.github.mateusdomelo.javerdb.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/ms/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(OK)
    public Cliente obterPorId(@PathVariable Long id) {
        return clienteService.obterPorId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente salvar(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    void atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        clienteService.atualizar(id, cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    void excluir(@PathVariable Long id) {
        clienteService.excluir(id);
    }
}
