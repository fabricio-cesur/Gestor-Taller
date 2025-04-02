CREATE TABLE Cliente (
    dni VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(40),
    apellidos VARCHAR (100),
    direccion VARCHAR (100),
    telefono INT(20),
    cuenta_bancaria VARCHAR(50)
);

CREATE TABLE Vehiculo (
    matricula VARCHAR(20) PRIMARY KEY,
    modelo VARCHAR(40),
    marca VARCHAR (100),
    ano YEAR,
    dni_cliente VARCHAR(20),
    FOREIGN KEY (dni_cliente) REFERENCES Cliente(dni) 
);

CREATE TABLE Empleado (
    dni VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(40),
    apelldios VARCHAR (100),
    direccion VARCHAR (100),
    telefono INT(20),
    salario DECIMAL(8,2),
    cargo VARCHAR (40),
    disponibilidad BOOLEAN DEFAULT TRUE,
    cuenta_bancaria VARCHAR(50) 
);

CREATE TABLE Proveedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(40),
    direccion VARCHAR (100),
    cuenta_bancaria VARCHAR(50)
);

CREATE TABLE Item (
    codigo VARCHAR PRIMARY KEY,
    nombre VARCHAR(100),
    id_proveedor INT, 
    cantidad INT,
    minimo INT,
    precio INT,
    restock BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id)
);

CREATE TABLE Servicio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(40),
    descripcion VARCHAR(200),
    id_item INT,
    precio DECIMAL(8,2),
    FOREIGN KEY (id_item) REFERENCES Item(codigo) 
);

CREATE TABLE Pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_item VARCHAR,
    cantidad INT,
    precio DECIMAL(8,2),
    fecha_pedido DATE,
    fecha_recibido DATE DEFAULT NULL,
    completado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_item) REFERENCES Item(codigo)
);

CREATE TABLE Encargo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula_coche VARCHAR(20),
    precio_total DECIMAL(8,2),
    fecha_inicio DATE,
    fecha_finalizado DATE,
    completado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (matricula_coche) REFERENCES Vehiculo(matricula)  
);

CREATE TABLE Asignacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_empleado VARCHAR (20),
    id_encargo INT,
    FOREIGN KEY (id_empleado) REFERENCES Empleado(dni), 
    FOREIGN KEY (id_encargo) REFERENCES Encargo(id) 
);


CREATE TABLE Cita (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    hora TIME,
    matricula_coche VARCHAR(20)
);

CREATE TABLE Servicio_Encargo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_encargo VARCHAR,
    id_servicio VARCHAR,
    FOREIGN KEY (id_servicio) REFERENCES Servicio(id), 
    FOREIGN KEY (id_encargo) REFERENCES Encargo(id)
);

CREATE TABLE Item_Pedido(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT,
    id_item INT,
    cantidad INT,
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id),
    FOREIGN KEY (id_item) REFERENCES Item(codigo)
);
