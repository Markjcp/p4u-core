-- phpMyAdmin SQL Dump
-- version 4.0.10.12
-- http://www.phpmyadmin.net
--
-- Host: 127.5.238.130:3306
-- Generation Time: Jul 10, 2016 at 03:02 AM
-- Server version: 5.5.45
-- PHP Version: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `p4u`
--

-- --------------------------------------------------------

--
-- Table structure for table `categoria`
--

CREATE TABLE IF NOT EXISTS `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(128) NOT NULL,
  `descripcion` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

-- --------------------------------------------------------

--
-- Table structure for table `categoria_agrupa_preferencias`
--

CREATE TABLE IF NOT EXISTS `categoria_agrupa_preferencias` (
  `id_preferencia` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  PRIMARY KEY (`id_preferencia`,`id_categoria`),
  KEY `id_categoria` (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `empresa`
--

CREATE TABLE IF NOT EXISTS `empresa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_compania` int(11) NOT NULL,
  `nombre` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `posicion` point DEFAULT NULL,
  `domicilio` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

-- --------------------------------------------------------

--
-- Table structure for table `empresa_paga_servicio`
--

CREATE TABLE IF NOT EXISTS `empresa_paga_servicio` (
  `id_empresa` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL,
  `fecha_alta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `monto` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `medio_pago` varchar(128) NOT NULL,
  PRIMARY KEY (`id_empresa`,`id_servicio`),
  KEY `id_servicio` (`id_servicio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `empresa_recibe_servicio`
--

CREATE TABLE IF NOT EXISTS `empresa_recibe_servicio` (
  `id_empresa` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL,
  `fecha_baja` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_empresa`,`id_servicio`),
  KEY `id_servicio` (`id_servicio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_regalo` int(11) NOT NULL,
  `codigo_producto` varchar(20) DEFAULT NULL,
  `estado` varchar(128) NOT NULL DEFAULT 'Sin Canjear',
  `codigo_regalo` varchar(11) NOT NULL DEFAULT 'MXCR1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_producto` (`codigo_producto`),
  KEY `id_regalo` (`id_regalo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

-- --------------------------------------------------------

--
-- Table structure for table `item_comprado_usuario`
--

CREATE TABLE IF NOT EXISTS `item_comprado_usuario` (
  `id_usuario` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_usuario`,`id_item`),
  KEY `id_item` (`id_item`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `item_notifiacion_usuario`
--

CREATE TABLE IF NOT EXISTS `item_notifiacion_usuario` (
  `id_usuario` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  `fecha_envio_email` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` text NOT NULL,
  `remitente` varchar(128) NOT NULL,
  `destinatario` varchar(128) NOT NULL,
  `vencimiento` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_usuario`,`id_item`),
  KEY `id_item` (`id_item`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `preferencia`
--

CREATE TABLE IF NOT EXISTS `preferencia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(128) NOT NULL,
  `descripcion` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

-- --------------------------------------------------------

--
-- Table structure for table `regalo`
--

CREATE TABLE IF NOT EXISTS `regalo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_empresa` int(11) NOT NULL,
  `nombre` varchar(128) NOT NULL,
  `marca` varchar(128) NOT NULL,
  `tipo` varchar(128) NOT NULL,
  `precio` float NOT NULL,
  `imagen` text NOT NULL,
  `cantidad_inicial` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `gratis` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_empresa` (`id_empresa`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

-- --------------------------------------------------------

--
-- Table structure for table `regalo_pertenece_categoria`
--

CREATE TABLE IF NOT EXISTS `regalo_pertenece_categoria` (
  `id_regalo` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  PRIMARY KEY (`id_regalo`,`id_categoria`),
  KEY `id_categoria` (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `servicio`
--

CREATE TABLE IF NOT EXISTS `servicio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(128) NOT NULL,
  `precio` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Table structure for table `tarjeta`
--

CREATE TABLE IF NOT EXISTS `tarjeta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `banco` varchar(128) NOT NULL,
  `numero` bigint(20) NOT NULL,
  `codigo_seguridad` smallint(6) NOT NULL,
  `tipo_tarjeta` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `facebook` varchar(128) NOT NULL,
  `domicilio` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Table structure for table `usuario_registro_tarjeta`
--

CREATE TABLE IF NOT EXISTS `usuario_registro_tarjeta` (
  `id_usuario` int(11) NOT NULL,
  `id_tarjeta` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_tarjeta`),
  KEY `id_tarjeta` (`id_tarjeta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `usuario_tiene_preferencia`
--

CREATE TABLE IF NOT EXISTS `usuario_tiene_preferencia` (
  `id_usuario` int(11) NOT NULL,
  `id_preferencia` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_preferencia`),
  KEY `id_preferencia` (`id_preferencia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `categoria_agrupa_preferencias`
--
ALTER TABLE `categoria_agrupa_preferencias`
  ADD CONSTRAINT `categoria_agrupa_preferencias_ibfk_1` FOREIGN KEY (`id_preferencia`) REFERENCES `preferencia` (`id`),
  ADD CONSTRAINT `categoria_agrupa_preferencias_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`);

--
-- Constraints for table `empresa_paga_servicio`
--
ALTER TABLE `empresa_paga_servicio`
  ADD CONSTRAINT `empresa_paga_servicio_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`),
  ADD CONSTRAINT `empresa_paga_servicio_ibfk_2` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id`);

--
-- Constraints for table `empresa_recibe_servicio`
--
ALTER TABLE `empresa_recibe_servicio`
  ADD CONSTRAINT `empresa_recibe_servicio_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`),
  ADD CONSTRAINT `empresa_recibe_servicio_ibfk_2` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id`);

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`id_regalo`) REFERENCES `regalo` (`id`);

--
-- Constraints for table `item_comprado_usuario`
--
ALTER TABLE `item_comprado_usuario`
  ADD CONSTRAINT `item_comprado_usuario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `item_comprado_usuario_ibfk_2` FOREIGN KEY (`id_item`) REFERENCES `item` (`id`);

--
-- Constraints for table `item_notifiacion_usuario`
--
ALTER TABLE `item_notifiacion_usuario`
  ADD CONSTRAINT `item_notifiacion_usuario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `item_notifiacion_usuario_ibfk_2` FOREIGN KEY (`id_item`) REFERENCES `item` (`id`);

--
-- Constraints for table `regalo`
--
ALTER TABLE `regalo`
  ADD CONSTRAINT `regalo_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`);

--
-- Constraints for table `regalo_pertenece_categoria`
--
ALTER TABLE `regalo_pertenece_categoria`
  ADD CONSTRAINT `regalo_pertenece_categoria_ibfk_1` FOREIGN KEY (`id_regalo`) REFERENCES `regalo` (`id`),
  ADD CONSTRAINT `regalo_pertenece_categoria_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`);

--
-- Constraints for table `usuario_registro_tarjeta`
--
ALTER TABLE `usuario_registro_tarjeta`
  ADD CONSTRAINT `usuario_registro_tarjeta_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `usuario_registro_tarjeta_ibfk_2` FOREIGN KEY (`id_tarjeta`) REFERENCES `tarjeta` (`id`);

--
-- Constraints for table `usuario_tiene_preferencia`
--
ALTER TABLE `usuario_tiene_preferencia`
  ADD CONSTRAINT `usuario_tiene_preferencia_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `usuario_tiene_preferencia_ibfk_2` FOREIGN KEY (`id_preferencia`) REFERENCES `preferencia` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--
-- Image name column added
--
ALTER TABLE `regalo` 
ADD COLUMN `archivo_imagen` VARCHAR(45) NULL AFTER `gratis`;

ALTER TABLE `regalo` 
CHANGE COLUMN `imagen` `imagen` BLOB NOT NULL ;

ALTER TABLE `usuario` 
CHANGE COLUMN `facebook` `facebook` VARCHAR(128) NULL ,
CHANGE COLUMN `domicilio` `domicilio` VARCHAR(256) NULL ,
ADD COLUMN `nombre` VARCHAR(100) NULL AFTER `domicilio`,
ADD COLUMN `apellido` VARCHAR(100) NULL AFTER `nombre`;

ALTER TABLE `usuario` 
ADD COLUMN `fecha_nacimiento` VARCHAR(45) NULL AFTER `apellido`;

ALTER TABLE `empresa` 
CHANGE COLUMN `posicion` `posicion` VARCHAR(128) NULL DEFAULT NULL ;


ALTER TABLE `regalo` 
ADD COLUMN `minutos_de_expiracion` INT(11) NULL DEFAULT NULL AFTER `archivo_imagen`;

ALTER TABLE `item_notifiacion_usuario` 
ADD COLUMN `mensaje` VARCHAR(500) NULL AFTER `fecha_envio_mail`;

ALTER TABLE `item` 
DROP INDEX `codigo_producto` ;

ALTER TABLE `p4u`.`item_notifiacion_usuario` 
CHANGE COLUMN `vencimiento` `vencimiento` TIMESTAMP NULL DEFAULT NULL ;

