INSERT INTO Cliente (dni, nombre, apellidos, direccion, telefono, cuenta_bancaria) VALUES
('11111111A', 'Juan', 'Pérez García', 'Calle Mayor 123', 911234567, 'ES12345678901234567890'),
('22222222B', 'María', 'López Martínez', 'Avenida del Sol 45', 922345678, 'ES98765432109876543210'),
('33333333C', 'Carlos', 'Ruiz Sánchez', 'Plaza de la Luna 6', 933456789, 'ES11223344556677889900'),
('44444444D', 'Ana', 'Gómez Fernández', 'Ronda del Castillo 78', 944567890, 'ES00998877665544332211'),
('55555555E', 'Pedro', 'Díaz Rodríguez', 'Travesía del Río 9', 955678901, 'ES10203040506070809000'),
('66666666F', 'Laura', 'Martínez Pérez', 'Camino de la Sierra 10', 966789012, 'ES90807060504030201000'),
('77777777G', 'Javier', 'Sánchez López', 'Paseo de las Flores 11', 977890123, 'ES12003400560078009000'),
('88888888H', 'Isabel', 'Fernández Ruiz', 'Carretera del Puerto 12', 988901234, 'ES98007600540032001000'),
('99999999I', 'Miguel', 'Rodríguez Gómez', 'Vía de la Estación 13', 999012345, 'ES13579246801357924680'),
('00000000J', 'Elena', 'Torres Díaz', 'Callejón de la Iglesia 14', 901234567, 'ES24680135792468013579');

INSERT INTO Vehiculo (matricula, modelo, marca, ano, dni_cliente) VALUES
('1234ABC', 'Focus', 'Ford', 2020, '11111111A'),
('5678DEF', 'Clio', 'Renault', 2019, '22222222B'),
('9012GHI', 'Ibiza', 'Seat', 2021, '33333333C'),
('3456JKL', '208', 'Peugeot', 2018, '44444444D'),
('7890MNO', 'Golf', 'Volkswagen', 2022, '55555555E'),
('1357PQR', 'A3', 'Audi', 2020, '66666666F'),
('2468STU', 'Serie 1', 'BMW', 2019, '77777777G'),
('1122VWX', 'Clase A', 'Mercedes', 2021, '88888888H'),
('3344YZA', 'Corolla', 'Toyota', 2018, '99999999I'),
('5566BCD', 'Civic', 'Honda', 2022, '00000000J');

INSERT INTO Empleado (dni, nombre, apellidos, direccion, telefono, salario, cargo, disponibilidad, cuenta_bancaria) VALUES
('10101010A', 'Antonio', 'Gómez Pérez', 'Calle del Sol 15', 912345678, 2500.00, 'Mecánico', TRUE, 'ES12345678901234567891'),
('20202020B', 'Carmen', 'Jiménez López', 'Avenida de la Luna 20', 923456789, 2800.00, 'Electricista', TRUE, 'ES98765432109876543211'),
('30303030C', 'David', 'Torres Ruiz', 'Plaza del Río 25', 934567890, 3000.00, 'Electrónico', TRUE, 'ES11223344556677889911'),
('40404040D', 'Sofía', 'Pérez Gómez', 'Ronda de la Montaña 30', 945678901, 2600.00, 'Mecánico', TRUE, 'ES00998877665544332212'),
('50505050E', 'Roberto', 'López Díaz', 'Travesía del Mar 35', 956789012, 2900.00, 'Frenos', TRUE, 'ES10203040506070809012'),
('60606060F', 'Patricia', 'Ruiz Martínez', 'Camino del Bosque 40', 967890123, 2700.00, 'Suspensión', TRUE, 'ES90807060504030201012'),
('70707070G', 'Alejandro', 'Martín Sánchez', 'Paseo del Prado 45', 978901234, 3100.00, 'Mantenimiento', TRUE, 'ES12003400560078009012'),
('80808080H', 'Mónica', 'García Fernández', 'Carretera de la Playa 50', 989012345, 2850.00, 'Diagnóstico', TRUE, 'ES98007600540032001012'),
('90909090I', 'Javier', 'Díaz Rodríguez', 'Vía del Tren 55', 990123456, 3200.00, 'Transmisión', TRUE, 'ES13579246801357924681'),
('01010101J', 'Lorena', 'Sánchez Torres', 'Callejón del Puente 60', 901234567, 2950.00, 'Alineación', TRUE, 'ES24680135792468013581');

INSERT INTO Proveedor (nombre, direccion, cuenta_bancaria) VALUES
('Proveedor A', 'Calle Principal 1', 'ES12345678901234567892'),
('Proveedor B', 'Avenida Secundaria 2', 'ES98765432109876543212'),
('Proveedor C', 'Plaza Central 3', 'ES11223344556677889912'),
('Proveedor D', 'Ronda Exterior 4', 'ES00998877665544332213'),
('Proveedor E', 'Travesía Interior 5', 'ES10203040506070809013'),
('Proveedor F', 'Camino Vecinal 6', 'ES90807060504030201013'),
('Proveedor G', 'Paseo Marítimo 7', 'ES12003400560078009013'),
('Proveedor H', 'Carretera Montaña 8', 'ES98007600540032001013'),
('Proveedor I', 'Vía Industrial 9', 'ES13579246801357924682'),
('Proveedor J', 'Callejón Comercial 10', 'ES24680135792468013582');

INSERT INTO Item (nombre, id_proveedor, cantidad, minimo, precio, restock) VALUES
('Filtro de aceite', 1, 50, 10, 15.00, FALSE),
('Pastillas de freno', 2, 30, 5, 25.00, FALSE),
('Bateria de coche', 3, 20, 3, 80.00, FALSE),
('Neumático', 4, 40, 8, 60.00, FALSE),
('Amortiguador', 5, 15, 2, 100.00, FALSE),
('Aceite de motor', 6, 60, 12, 12.00, FALSE),
('Bujía', 7, 100, 20, 5.00, FALSE),
('Correa de distribucion', 8, 25, 4, 30.00, FALSE),
('Liquido de frenos', 9, 35, 7, 8.00, FALSE),
('Refrigerante', 10, 45, 9, 10.00, FALSE);

INSERT INTO Servicio (nombre, descripcion, id_item, precio) VALUES
('Cambio de aceite', 'Cambio de aceite y filtro', 1, 50.00),
('Reparación de frenos', 'Cambio de pastillas y discos', 2, 120.00),
('Cambio de batería', 'Instalación de batería nueva', 3, 90.00),
('Cambio de neumáticos', 'Montaje y equilibrado', 4, 100.00),
('Cambio de amortiguadores', 'Instalación de amortiguadores nuevos', 5, 200.00),
('Revisión de motor', 'Diagnóstico y ajuste', 6, 80.00),
('Cambio de bujías', 'Instalación de bujías nuevas', 7, 40.00),
('Cambio de correa de distribución', 'Instalación de correa nueva', 8, 150.00),
('Cambio de líquido de frenos', 'Purgado y llenado', 9, 60.00),
('Cambio de refrigerante', 'Limpieza y llenado', 10, 70.00);

INSERT INTO Pedido (id_item, cantidad, precio, fecha_pedido, fecha_recibido, completado) VALUES
(1, 10, 150.00, '2023-10-26', '2023-10-28', TRUE),
(2, 5, 125.00, '2023-10-27', '2023-10-29', TRUE),
(3, 3, 240.00, '2023-10-28', NULL, FALSE),
(4, 8, 480.00, '2023-10-29', '2023-10-31', TRUE),
(5, 2, 200.00, '2023-10-30', NULL, FALSE),
(6, 12, 144.00, '2023-10-31', '2023-11-02', TRUE),
(7, 20, 100.00, '2023-11-01', NULL, FALSE),
(8, 4, 120.00, '2023-11-02', '2023-11-04', TRUE),
(9, 7, 56.00, '2023-11-03', NULL, FALSE),
(10, 9, 90.00, '2023-11-04', '2023-11-06', TRUE);

INSERT INTO Encargo (matricula_coche, precio_total, fecha_inicio, fecha_finalizado, completado) VALUES
('1234ABC', 170.00, '2023-10-26', '2023-10-28', TRUE),
('5678DEF', 120.00, '2023-10-27', '2023-10-29', TRUE),
('9012GHI', 90.00, '2023-10-28', NULL, FALSE),
('3456JKL', 100.00, '2023-10-29', '2023-10-31', TRUE),
('7890MNO', 200.00, '2023-10-30', NULL, FALSE),
('1357PQR', 80.00, '2023-10-31', '2023-11-02', TRUE),
('2468STU', 40.00, '2023-11-01', NULL, FALSE),
('1122VWX', 150.00, '2023-11-02', '2023-11-04', TRUE),
('3344YZA', 60.00, '2023-11-03', NULL, FALSE),
('5566BCD', 70.00, '2023-11-04', '2023-11-06', TRUE);

INSERT INTO Cita (fecha, hora, matricula_coche) VALUES
('2023-11-06', '10:00:00', '1234ABC'),
('2023-11-07', '11:00:00', '5678DEF'),
('2023-11-08', '12:00:00', '9012GHI'),
('2023-11-09', '13:00:00', '3456JKL'),
('2023-11-10', '14:00:00', '7890MNO'),
('2023-11-11', '15:00:00', '1357PQR'),
('2023-11-12', '16:00:00', '2468STU'),
('2023-11-13', '17:00:00', '1122VWX'),
('2023-11-14', '18:00:00', '3344YZA'),
('2023-11-15', '19:00:00', '5566BCD');

INSERT INTO Empleado_Encargo (id_empleado, id_encargo) VALUES
('10101010A', 1),
('20202020B', 2),
('30303030C', 3),
('40404040D', 4),
('50505050E', 5),
('60606060F', 6),
('70707070G', 7),
('80808080H', 8),
('90909090I', 9),
('01010101J', 10);

INSERT INTO Servicio_Encargo (id_encargo, id_servicio) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

INSERT INTO Item_Pedido (id_pedido, id_item, cantidad) VALUES
(1, 1, 10),
(2, 2, 5),
(3, 3, 3),
(4, 4, 8),
(5, 5, 2),
(6, 6, 12),
(7, 7, 20),
(8, 8, 4),
(9, 9, 7),
(10, 10, 9);

