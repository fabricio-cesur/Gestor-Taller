CREATE TABLE Cliente (
    dni VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(40),
    apelldios VARCHAR (100),
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
    FOREIGN KEY (dni_cliente) REFERENCES cliente(dni) 
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
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    proveedor_id INT, 
    cantidad INT,
    minimo INT,
    restock BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (proveedor_id) REFERENCES proveedor(id)
);

CREATE TABLE Servicio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(40),
    descripcion VARCHAR(200),
    id_item INT,
    precio DECIMAL(8,2),
    FOREIGN KEY (id_item) REFERENCES item(codigo) 
);

CREATE TABLE Pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_proveedor INT, 
    fecha_pedido DATE,
    fecha_entrega_aproximada DATE,
    fecha_recibido DATE,
    completado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id)
);

CREATE TABLE Pedido_item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT,
    id_item INT,
    cantidad INT,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id),
    FOREIGN KEY (id_item) REFERENCES item(codigo)
);

CREATE TABLE Encargo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula_coche VARCHAR(20),
    id_servicio INT,
    precio_total DECIMAL(8,2),
    fecha_inicio DATE,
    fecha_finalizado DATE,
    completado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (matricula_coche) REFERENCES vehiculo(matricula),  
    FOREIGN KEY (id_servicio) REFERENCES servicio(id) 
);

CREATE TABLE Asignacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_empleado VARCHAR (20),
    id_encargo INT,
    FOREIGN KEY (id_empleado) REFERENCES empleado(dni), 
    FOREIGN KEY (id_encargo) REFERENCES encargo(id) 
);

CREATE TABLE Citas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    hora TIME,
    matricula_coche VARCHAR(20),
    id_encargo INT, 
    FOREIGN KEY (id_encargo) REFERENCES encargo(id)
);

