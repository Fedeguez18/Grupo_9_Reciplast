CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    puntos INT DEFAULT 0
);

INSERT INTO Usuario (puntos) VALUES
(150),
(300),
(500);

-- ==============================
-- TABLA BENEFICIO
-- ==============================
CREATE TABLE Beneficio (
    idBeneficio INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_benef VARCHAR(100) NOT NULL,
    Categoria VARCHAR(50),
    Tipo VARCHAR(50),
    Ptos_req INT NOT NULL,
    Fecha_vigencia DATE NOT NULL,
    Activo BOOLEAN DEFAULT TRUE,
    Descripcion TEXT
);

INSERT INTO Beneficio (Nombre_benef, Categoria, Tipo, Ptos_req, Fecha_vigencia, Activo, Descripcion)
VALUES
('Descuento 10% en supermercado', 'Descuento', 'Cupón', 100, '2025-12-31', TRUE, 'Descuento válido en tiendas adheridas'),
('Bolsa ecológica reutilizable', 'Producto', 'Físico', 200, '2026-06-30', TRUE, 'Bolsa hecha 100% de plástico reciclado'),
('Entrada de cine', 'Entretenimiento', 'Cupón', 400, '2025-11-30', TRUE, 'Entrada válida de lunes a jueves');

-- ==============================
-- TABLA CANJE
-- ==============================
CREATE TABLE Canje (
    idCanje INT AUTO_INCREMENT PRIMARY KEY,
    idUser INT NOT NULL,
    idBeneficio INT NOT NULL,
    fechaCanje DATE NOT NULL,
    FOREIGN KEY (idUser) REFERENCES Usuario(id),
    FOREIGN KEY (idBeneficio) REFERENCES Beneficio(idBeneficio)
);
--MODIFICA LA TABLA CANJE PARA QUE UN USUARIO NO PUEDA CANJEAR EL MISMO BENEFICIO MÁS DE UNA VEZ
ALTER TABLE Canje
ADD CONSTRAINT unique_usuario_beneficio
UNIQUE (idUser, idBeneficio);

-- Ejemplo de un canje ya realizado
INSERT INTO Canje (idUser, idBeneficio, fechaCanje)
VALUES
(1, 1, CURDATE());

--gregar columna stock a la tabla BENEFICIO
ALTER TABLE Beneficio
ADD stock INT NOT NULL DEFAULT 0;

--Puedes asignar un valor inicial de stock para cada beneficio:
UPDATE Beneficio SET stock = 50 WHERE idBeneficio = 1;
UPDATE Beneficio SET stock = 100 WHERE idBeneficio = 2;

--Protección en BD: que stock nunca quede negativo
ALTER TABLE Beneficio
ADD CONSTRAINT check_stock_nonnegative
CHECK (stock >= 0);

--http://localhost:8081/canjear/1/3