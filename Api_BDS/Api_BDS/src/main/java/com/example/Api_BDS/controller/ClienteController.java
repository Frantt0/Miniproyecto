package com.example.Api_BDS.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Cliente")
public class ClienteController {

    private final ClienteController ClienteService;

    public ClienteController(ClienteService ClienteService) {
        this.ClienteService = ClienteService;
    }
    
    private final ClienteRepository ClienteRepository;

    public ClienteController(ClienteRepository ClienteRepository) {
        this.ClienteRepository = ClienteRepository;
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

        return ClienteRepository.findAll(pageable).getContent();
    }
    // Para añadir el nombre la url sera como  http://localhost:8080/api/Cliente/buscar?nombre=Ana
    @GetMapping("/buscar")
    public List<Cliente> buscar(@RequestParam String nombre) {
        return ClienteRepository.findByNombreContaining(nombre);
    }
    //Buscar por mayores de edad CON EL PATH VARIABLE ES ENTRE CORCHETES http://localhost:8080/api/Cliente/mayores/20
    @GetMapping("/mayores/{edad}")
    public List<Cliente> obtenerMayores(@PathVariable int edad) {
        return ClienteRepository.buscarMayores(edad);
    }
    // Crear un nuevo Cliente
    @PostMapping
    public Cliente createCliente(@RequestBody Cliente Cliente) {
        return ClienteRepository.save(Cliente);
    }

    // Obtener un Cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable int id) {
        Optional<Cliente> Cliente = ClienteRepository.findById(id);
        return Cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un Cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente updatedCliente) {
        return ClienteRepository.findById(id)
                .map(Cliente -> {
                    Cliente.setNombre(updatedCliente.getNombre());
                    Cliente.setEdad(updatedCliente.getEdad());
                    ClienteRepository.save(Cliente);
                    return ResponseEntity.ok(Cliente);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Borrar un Cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) {
        Optional<Cliente> Cliente = ClienteRepository.findById(id);
        if (Cliente.isPresent()) {
            ClienteRepository.delete(Cliente.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    
}

