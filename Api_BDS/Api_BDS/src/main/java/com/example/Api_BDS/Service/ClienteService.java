package com.example.Api_BDS.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.example.Api_BDS.entity.Cliente;
import com.example.Api_BDS.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar con paginación y ordenación
    public List<Cliente> listarClientes(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return clienteRepository.findAll(pageable).getContent();
    }

    // Obtener un cliente por ID
    public Optional<Cliente> buscarPorId(int id) {
        return clienteRepository.findById(id);
    }

    // Buscar por nacionalidad
    public List<Cliente> buscarPorNacionalidad(String nacionalidad) {
        return clienteRepository.findByNacionalidadContaining(nacionalidad);
    }

    // Buscar por seguro
    public List<Cliente> buscarPorSeguro(String seguro) {
        return clienteRepository.findBySeguroContaining(seguro);
    }

    // Crear cliente
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Actualizar cliente
    public Optional<Cliente> actualizarCliente(int id, Cliente updatedCliente) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNombre(updatedCliente.getNombre());
            cliente.setApellidos(updatedCliente.getApellidos());
            cliente.setDni(updatedCliente.getDni());
            cliente.setCaducidadDni(updatedCliente.getCaducidadDni());
            cliente.setFechaNacimiento(updatedCliente.getFechaNacimiento());
            cliente.setTelefono(updatedCliente.getTelefono());
            cliente.setNacionalidad(updatedCliente.getNacionalidad());
            cliente.setSeguro(updatedCliente.getSeguro());
            cliente.setCorreo(updatedCliente.getCorreo());
            return clienteRepository.save(cliente);
        });
    }

    // Borrar cliente
    public boolean eliminarCliente(int id) {
        return clienteRepository.findById(id).map(cliente -> {
            clienteRepository.delete(cliente);
            return true;
        }).orElse(false);
    }
}

