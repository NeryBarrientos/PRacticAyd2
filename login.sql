CREATE SCHEMA `aydpractica` ;
use aydpractica;

CREATE TABLE `aydpractica`.`rol` (
  `id_rol` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_rol`));
INSERT INTO `aydpractica`.`rol` (`nombre`, `descripcion`) VALUES ('ADMIN', 'Acceso total');
INSERT INTO `aydpractica`.`rol` (`nombre`, `descripcion`) VALUES ('USER', 'Acceso estandar');

select * from rol;

CREATE TABLE `aydpractica`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `correo` VARCHAR(255) NOT NULL,
  `contrasenia` VARCHAR(255) NOT NULL,
  `nombre_completo` VARCHAR(150) NOT NULL,
  `activo` VARCHAR(45) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `fecha_modificacion` DATETIME NULL,
  PRIMARY KEY (`id_usuario`));

select * from usuario;
INSERT INTO aydpractica.usuario (correo, contrasenia, nombre_completo, activo, fecha_creacion)
VALUES ('n3roeya@gmail.com', '123456', 'Nery José Barrientos Posadas', 1, CURRENT_TIMESTAMP);

INSERT INTO aydpractica.usuario (correo, contrasenia, nombre_completo,activo, fecha_creacion)
VALUES ('neryjosebarrientos@gmail.com', '123456', 'Nery José Barrientos Posadas', 1,CURRENT_TIMESTAMP);

CREATE TABLE `aydpractica`.`usuario_rol` (
  `id_usuario_rol` INT NOT NULL AUTO_INCREMENT,
  `usuario` INT NOT NULL,
  `rol` INT NOT NULL,
  PRIMARY KEY (`id_usuario_rol`),
  INDEX `fk_ur_usuario_idx` (`usuario` ASC) VISIBLE,
  INDEX `fk_ur_rol_idx` (`rol` ASC) VISIBLE,
  CONSTRAINT `fk_ur_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `aydpractica`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ur_rol`
    FOREIGN KEY (`rol`)
    REFERENCES `aydpractica`.`rol` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

select * from usuario_rol;
INSERT INTO `aydpractica`.`usuario_rol` (`usuario`, `rol`) VALUES ('1', '1');
INSERT INTO `aydpractica`.`usuario_rol` (`usuario`, `rol`) VALUES ('2', '2');