DROP TABLE IF EXISTS Cliente;

CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(25),
    apellidos VARCHAR(25),
    dni VARCHAR(9),
    CaducidadDni DATE,
    FechaNacimiento DATE,
    Telefono INT,
    Nacionalidad VARCHAR(30),
    seguro CHAR(1),
    correo VARCHAR(20)
);

