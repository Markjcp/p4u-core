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

--
-- Dumping data for table `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Gastronomia', 'Gastronomia'),
(2, 'Eventos y Actividades', 'Eventos y Actividades'),
(3, 'Indumentaria Mujer', 'Indumentaria Mujer'),
(4, 'Salud y Fitness', 'Salud y Fitness'),
(5, 'Belleza', 'Belleza'),
(6, 'Servicios', 'Servicios'),
(7, 'Electronica', 'Electronica'),
(8, 'Indumentaria Hombre', 'Indumentaria Hombre'),
(9, 'Hogar y Jardin', 'Hogar y Jardin'),
(10, 'Productos', 'Productos'),
(11, 'Viajes', 'Viajes');

--
-- Dumping data for table `empresa`
--

INSERT INTO `empresa` (`id`, `codigo_compania`, `nombre`, `password`, `posicion`, `domicilio`) VALUES
(1, 1, 'McDonalds', 'password', NULL, '140'),
(2, 2, 'Cinemark', 'password', NULL, '130:130'),
(3, 3, 'Subway', 'Password', NULL, 'Coronel Diaz 2056'),
(4, 4, 'Burguer King', 'Password', NULL, 'Santa Fe 3460'),
(5, 5, 'Drivers', 'drivers', NULL, 'Nazca 123'),
(6, 6, 'Caro Cuore', 'caro', NULL, 'Arenales 1235'),
(7, 7, 'Clinica los Arcos', 'arcos', NULL, 'Juan B Justo 1924'),
(8, 8, 'Compumundo', 'compu', NULL, 'Cabildo 2391'),
(9, 9, 'Coto', 'Coto', NULL, 'Charcas 6574'),
(10, 10, 'Sala Crash', 'crash', NULL, 'Uriarte 1230'),
(11, 11, 'Despegar', 'despegar', NULL, 'Av Cordoba 222'),
(12, 12, 'Figurella', 'figurella', NULL, 'Lavalle 127'),
(13, 13, 'Eyelit', 'Eyelit', NULL, 'Av Corrientes 12253');

--
-- Dumping data for table `regalo`
--

INSERT INTO `regalo` (`id`, `id_empresa`, `nombre`, `marca`, `tipo`, `precio`, `imagen`, `cantidad_inicial`, `stock`, `gratis`) VALUES
(1, 1, 'Big Mac', '', '', 60, '', 100, 3000, 0),
(2, 2, 'Entrada gratis Peli x Funcion y', '', '', 120, '', 20, 40, 0),
(3, 3, 'Sub suprema de pollo', 'Subway', 'Sandwich', 65, '', 15000, 15000, 127),
(4, 4, 'Combo Wooper Extreme', 'Burguer King', 'Sandwich', 120, '', 5000, 5000, 2000),
(5, 5, 'Clase de manejo', 'Drivers', 'Servicio', 300, '', 500, 500, 5),
(6, 6, 'Culotte', 'Caro Cuore', 'Indumentaria mujer', 200, '', 2000, 2000, 10),
(7, 7, 'Consulta dermatologia', 'Los arcos', 'Salud', 500, '', 1000, 1000, 10),
(8, 8, 'Tablet Samsung 2014', 'Samsung', 'Electronica', 4000, '', 100, 100, 2),
(9, 9, 'Bordeadora OleoMac', 'OleoMac', 'Jardin', 5000, '', 300, 300, 10),
(10, 10, 'Entrada para Dancing Mood', 'La oreja negra', 'Entretenimiento', 160, '', 300, 300, 50),
(11, 11, 'Viaje a San Pablo', 'Austral Lineas Aereas', 'Viaje', 10000, '', 400, 400, 10),
(12, 12, 'Kit de maquillaje Puppa', 'Puppa', 'Maquillaje, Belleza', 3500, '', 60, 60, 1),
(13, 13, 'Boxer XL', 'Eyelit', 'Ropa intima', 120, '', 700, 700, 50),
(14, 1, 'Combo McNifica', 'Mc Donalds', 'Sandwich', 130, '', 12000, 12000, 1000);

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`id`, `id_regalo`, `codigo_producto`, `estado`, `codigo_regalo`) VALUES
(12, 1, 'XXCP15', 'Sin Canjear', 'MXCR1'),
(13, 8, 'XXCP151', 'Sin Canjear', 'MXCR132'),
(24, 2, NULL, 'Sin Canjear', 'MXCR1'),
(25, 13, NULL, 'Sin Canjear', 'MXCR1'),
(26, 1, NULL, 'Sin Canjear', 'MXCR1241'),
(27, 1, NULL, 'Sin Canjear', 'MXCR112'),
(28, 4, NULL, 'Canjeado', 'MXCR133');

--
-- Dumping data for table `preferencia`
--

INSERT INTO `preferencia` (`id`, `nombre`, `descripcion`) VALUES
(2, 'Computadoras y Tablets', ''),
(3, 'Instrumentos musicales', ''),
(4, 'Accesorios Celular', ''),
(5, 'Television y Home Theater', ''),
(6, 'Bebidas Alcohólicas', ''),
(7, 'Dulces y caramelos', ''),
(8, 'Comida', ''),
(9, 'Gaseosas', ''),
(10, 'Accesorios y cosméticos', ''),
(11, 'Audio Portatil', '');

--
-- Dumping data for table `servicio`
--

INSERT INTO `servicio` (`id`, `nombre`, `precio`) VALUES
(2, 'Terciarizar Regalos', 2000),
(3, 'Campaña empleados', 20000);

--
-- Dumping data for table `tarjeta`
--

INSERT INTO `tarjeta` (`id`, `banco`, `numero`, `codigo_seguridad`, `tipo_tarjeta`) VALUES
(1, 'Banco Ciudad', 123412891712982, 354, 'Crédito'),
(2, 'Santander Rio', 123412124151215, 345, 'Debito'),
(3, 'City Bank', 123412261659576, 281, 'Credito');

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `username`, `password`, `email`, `facebook`, `domicilio`) VALUES
(1, 'mnforlenza@gmail.com', 'password', 'mnforlenza@gmail.com', 'miface', '190:102'),
(2, 'l_sorrentino', 'password', 'sorrentino@fi.uba.ar', 'lucianoS', 'Calle falsa 123');

--
-- Dumping data for table `categoria_agrupa_preferencias`
--

INSERT INTO `categoria_agrupa_preferencias` (`id_preferencia`, `id_categoria`) VALUES
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 4),
(10, 5),
(2, 7),
(3, 7),
(4, 7),
(5, 7),
(11, 7);

--
-- Dumping data for table `empresa_paga_servicio`
--

INSERT INTO `empresa_paga_servicio` (`id_empresa`, `id_servicio`, `fecha_alta`, `monto`, `fecha`, `medio_pago`) VALUES
(1, 3, '2016-07-10 02:02:04', 2000, '0000-00-00 00:00:00', 'Depósito bancario'),
(5, 2, '2016-07-10 02:02:04', 20000, '0000-00-00 00:00:00', 'Transacción directa Banco Patagonia');

--
-- Dumping data for table `empresa_recibe_servicio`
--

INSERT INTO `empresa_recibe_servicio` (`id_empresa`, `id_servicio`, `fecha_baja`) VALUES
(1, 3, '2016-07-10 02:02:55'),
(5, 2, '2016-07-10 02:02:55');

--
-- Dumping data for table `item_comprado_usuario`
--

INSERT INTO `item_comprado_usuario` (`id_usuario`, `id_item`, `fecha`) VALUES
(1, 12, '2016-07-10 02:57:26'),
(2, 26, '2016-07-10 02:57:26');

--
-- Dumping data for table `item_notifiacion_usuario`
--

INSERT INTO `item_notifiacion_usuario` (`id_usuario`, `id_item`, `fecha_envio_email`, `email`, `remitente`, `destinatario`, `vencimiento`) VALUES
(1, 12, '2016-07-10 03:00:09', '', '', '', '0000-00-00 00:00:00'),
(1, 26, '2016-07-10 03:01:08', 'Te mando una Big Mac para que engordes', 'Lucho', 'Marcos', '2016-07-31 04:00:00'),
(2, 28, '2016-07-10 02:58:48', '', '', '', '0000-00-00 00:00:00');


--
-- Dumping data for table `regalo_pertenece_categoria`
--

INSERT INTO `regalo_pertenece_categoria` (`id_regalo`, `id_categoria`) VALUES
(1, 1),
(3, 1),
(4, 1),
(14, 1),
(2, 2),
(10, 2),
(6, 3),
(7, 4),
(12, 4),
(12, 5),
(5, 6),
(8, 7),
(13, 8),
(9, 9),
(11, 11);

--
-- Dumping data for table `usuario_registro_tarjeta`
--

INSERT INTO `usuario_registro_tarjeta` (`id_usuario`, `id_tarjeta`) VALUES
(2, 1),
(2, 2),
(1, 3);

--
-- Dumping data for table `usuario_tiene_preferencia`
--

INSERT INTO `usuario_tiene_preferencia` (`id_usuario`, `id_preferencia`) VALUES
(1, 2),
(1, 4),
(2, 4),
(2, 7),
(1, 11);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

UPDATE `regalo` SET `archivo_imagen`='p4u/image/1.jpg' WHERE `id`='1';
UPDATE `regalo` SET `archivo_imagen`='p4u/image/2.jpg' WHERE `id`='2';
UPDATE `regalo` SET `archivo_imagen`='p4u/image/3.jpg' WHERE `id`='3';
UPDATE `regalo` SET `archivo_imagen`='p4u/image/4.jpg' WHERE `id`='4';
UPDATE `regalo` SET `archivo_imagen`='p4u/image/5.png' WHERE `id`='5';
UPDATE `regalo` SET `archivo_imagen`='p4u/image/6.jpg' WHERE `id`='6';
UPDATE `regalo` SET `archivo_imagen`='p4u/image/7.jpg' WHERE `id`='7';
UPDATE `regalo` SET `archivo_imagen`='p4u/image/8.jpg' WHERE `id`='8';
UPDATE `regalo` SET `archivo_imagen`='p4u/image/9.jpg' WHERE `id`='9';
UPDATE `regalo` SET `archivo_imagen`='p4u/image/10.jpg' WHERE `id`='10';
UPDATE `regalo` SET `archivo_imagen`='p4u/image/11.png' WHERE `id`='11';
UPDATE `regalo` SET `archivo_imagen`='p4u/image/12.png' WHERE `id`='12';
UPDATE `regalo` SET `archivo_imagen`='p4u/image/13.jpg' WHERE `id`='13';
UPDATE `regalo` SET `archivo_imagen`='p4u/image/14.jpg' WHERE `id`='14';

UPDATE `usuario` SET `nombre`='Marcos', `apellido`='Forlenza' WHERE `id`='1';
UPDATE `usuario` SET `nombre`='Luciano', `apellido`='Sorrentino' WHERE `id`='2';
UPDATE `usuario` SET `nombre`='Marcos', `apellido`='Forlenza' WHERE `id`='3';
UPDATE `usuario` SET `nombre`='Marcos' WHERE `id`='4';


UPDATE `item_notifiacion_usuario` SET `email`='mail@mail.com', `remitente`='Jose', `destinatario`='Otro', `vencimiento`='2016-07-29 00:08:14' WHERE `id_usuario`='2' and`id_item`='28';
UPDATE `item_notifiacion_usuario` SET `email`='mail@mail.com', `remitente`='Juan', `destinatario`='Pedro', `vencimiento`='2016-07-29 00:08:14' WHERE `id_usuario`='1' and`id_item`='12';
UPDATE `item_notifiacion_usuario` SET `email`='mail@mail.com' WHERE `id_usuario`='1' and`id_item`='26';


