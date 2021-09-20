CREATE TABLE IF NOT EXISTS domicilios(
	id INT auto_increment PRIMARY KEY,
	calle VARCHAR(255),
	numero VARCHAR(255),
	localidad VARCHAR(255),
	provincia VARCHAR(255)
);
--------------------------------------------------------------
CREATE TABLE IF NOT EXISTS pacientes(
	id INT auto_increment PRIMARY KEY,
	nombre VARCHAR(255),
	apellido VARCHAR(255),
	dni VARCHAR(255),
	fecha_ingreso TIMESTAMP WITHOUT TIME ZONE,
	domicilio_id INT);
--------------------------------------------------------------
CREATE TABLE IF NOT EXISTS odontologos(
	id INT auto_increment PRIMARY KEY,
	nombre VARCHAR(255),
	apellido VARCHAR(255),
	matricula INT
);
