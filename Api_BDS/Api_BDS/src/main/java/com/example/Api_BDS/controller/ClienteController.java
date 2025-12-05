package com.example.Api_BDS.controller;

import com.example.Api_BDS.entity.Cliente;
import com.example.Api_BDS.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Cliente")
public class ClienteController {
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Listar todos los Clientes
    @GetMapping
    public List<Cliente> getClientes( // Permite obtener los valores desde ascendente y descendete
                                      @RequestParam(defaultValue = "0") int page, //paginas
                                      @RequestParam(defaultValue = "5") int size, // tamaño de la ordenación
                                      @RequestParam(defaultValue = "id") String sortBy, // lo hace desde la id
                                      @RequestParam(defaultValue = "asc") String direction // en orden ascendente
    ) {
        var sort = direction.equalsIgnoreCase("asc") ?
                org.springframework.data.domain.Sort.by(sortBy).ascending() :
                org.springframework.data.domain.Sort.by(sortBy).descending();

        var pageable = org.springframework.data.domain.PageRequest.of(page, size, sort);

        return clienteRepository.findAll(pageable).getContent();
    }
    // Para añadir el nombre la url sera como  http://localhost:8080/api/Cliente/buscar?nombre=Ana

    // Crear un nuevo Cliente CON EL POST EN POSTMAN
    @PostMapping
    public Cliente createCliente(@RequestBody Cliente Cliente) {
        return clienteRepository.save(Cliente);
    }

    // Obtener un Cliente por ID //TODO A TRAVES DE POSTMAN
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable int id) {
        Optional<Cliente> Cliente = clienteRepository.findById(id);
        return Cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un Cliente A TRAVES DE POSTMAN
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente updatedCliente) {
        return clienteRepository.findById(id)
                .map(Cliente -> {
                    Cliente.setNombre(updatedCliente.getNombre());
                    Cliente.setApellidos(updatedCliente.getApellidos());
                    Cliente.setDni(updatedCliente.getDni());
                    Cliente.setCaducidadDni(updatedCliente.getCaducidadDni());
                    Cliente.setFechaNacimiento(updatedCliente.getFechaNacimiento());
                    Cliente.setTelefono(updatedCliente.getTelefono());
                    Cliente.setNacionalidad(updatedCliente.getNacionalidad());
                    Cliente.setSeguro(updatedCliente.getSeguro());
                    Cliente.setCorreo(updatedCliente.getCorreo());
                    clienteRepository.save(Cliente);
                    return ResponseEntity.ok(Cliente);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Borrar un Cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) {
        Optional<Cliente> Cliente = clienteRepository.findById(id);
        if (Cliente.isPresent()) {
            clienteRepository.delete(Cliente.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    
}
}
