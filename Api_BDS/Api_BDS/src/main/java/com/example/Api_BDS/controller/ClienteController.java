package com.example.Api_BDS.controller;

import com.example.Api_BDS.Service.ClienteService;
import com.example.Api_BDS.entity.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Cliente")
public class ClienteController {
    //private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Listar todos los Clientes
    @GetMapping
    public List<Cliente> getClientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return clienteService.listarClientes(page, size, sortBy, direction);
    }

    // Obtener un Cliente por ID TODO A TRAVES DE POSTMAN
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable int id) {
        Optional<Cliente> Cliente = clienteService.buscarPorId(id);
        return Cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Para añadir el nombre la url sera como  http://localhost:8080/api/Cliente/BNacionalidad?nacionalidad=Española
    @GetMapping("/BNacionalidad")
    public List<Cliente> buscarPorNacionalidad(@RequestParam String nacionalidad) {
        return clienteService.buscarPorNacionalidad(nacionalidad);
    }

    @GetMapping("/BSeguro") // PARA QUE NO SE REPITA EL BUSCAR SOLO HACE FALTA CAMBIAR EL NOMBRE DEL METODO
    public List<Cliente> buscarPorSeguro(@RequestParam String seguro) {
        return clienteService.buscarPorSeguro(seguro);
    }

    // Crear un nuevo Cliente CON EL POST EN POSTMAN
    @PostMapping
    public Cliente createCliente(@RequestBody Cliente Cliente) {
        return clienteService.crearCliente(Cliente);
    }


    // Actualizar un Cliente A TRAVES DE POSTMAN
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente updatedCliente) {
        Optional<Cliente> cliente = clienteService.actualizarCliente(id, updatedCliente);

        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Borrar un Cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) {
        boolean eliminado = clienteService.eliminarCliente(id);
        return eliminado ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}

