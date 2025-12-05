package com.example.Api_BDS.repository;

import com.example.Api_BDS.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    //List<com.example.Api_BDS.entity.Cliente> findByNombre(String nombre);

    //List<com.example.Api_BDS.entity.Cliente> findByEdadGreaterThan(int edad);

    //List<com.example.Api_BDS.entity.Cliente> findByNombreContaining(String texto);

    // es la consulta para mayores de edad, la llama realiza la constulta y proseguimos
    //@Query("SELECT a FROM Cliente a WHERE a. > :edad")
    //List<com.example.Api_BDS.entity.Cliente> buscarMayores(@Param("edad") int edad);
}
