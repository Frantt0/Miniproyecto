package com.example.Api_BDS.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellidos;
    private String dni;
    private Date CaducidadDni;
    private Date FechaNacimiento;
    private int  telefono;
    private String nacionalidad;
    private char seguro;
    private String correo;

    public Cliente() {}

    public Cliente(String nombre, String apellidos, String dni, Date CaducidadDni, Date FechaNacimiento, int telefono, String nacionalidad, char seguro, String correo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.CaducidadDni = CaducidadDni;
        this.FechaNacimiento = FechaNacimiento;
        this.telefono = telefono;
        this.nacionalidad = nacionalidad;
        this.seguro = seguro;
        this.correo = correo;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }


    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellidos(){return apellidos; }
    public void setApellidos(String apellidos){this.apellidos = apellidos; }

    public String getDni(){return dni; }
    public void setDni(String dni){this.dni = dni; }

    public Date getCaducidadDni(){return CaducidadDni; }
    public void setCaducidadDni(Date CaducidadDni){this.CaducidadDni = CaducidadDni; }

    public Date getFechaNacimiento(){return FechaNacimiento; }
    public void setFechaNacimiento(Date FechaNacimiento){this.FechaNacimiento = FechaNacimiento; }

    public int getTelefono(){return telefono; }
    public void setTelefono(int telefono){this.telefono = telefono; }

    public String getNacionalidad(){return nacionalidad; }
    public void setNacionalidad(String nacionalidad){this.nacionalidad = nacionalidad; }

    public char getSeguro(){return seguro; }
    public void setSeguro(char seguro){this.seguro = seguro; }

    public String getCorreo(){return correo; }
    public void setCorreo(String correo){this.correo = correo; }


}
