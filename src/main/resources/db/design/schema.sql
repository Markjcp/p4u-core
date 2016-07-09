
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--

-- Base de datos: `taller-proyectos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE IF NOT EXISTS `categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(128) NOT NULL,
  `descripcion` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_agrupa_preferencias`
--

CREATE TABLE IF NOT EXISTS `categoria_agrupa_preferencias` (
  `id_preferencia` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  PRIMARY KEY (`id_preferencia`,`id_categoria`),
  KEY `id_categoria` (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE IF NOT EXISTS `empresa` (
  `id` int(11) NOT NULL,
  `codigo_compania` int(11) NOT NULL,
  `nombre` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `posicion` point NOT NULL,
  `domicilio` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa_paga_servicio`
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
-- Estructura de tabla para la tabla `empresa_recibe_servicio`
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
-- Estructura de tabla para la tabla `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `id` int(11) NOT NULL,
  `id_regalo` int(11) NOT NULL,
  `codigo_producto` int(11) NOT NULL,
  `estado` varchar(128) NOT NULL,
  `codigo_regalo` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_regalo` (`id_regalo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `item_comprado_usuario`
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
-- Estructura de tabla para la tabla `item_notifiacion_usuario`
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
-- Estructura de tabla para la tabla `preferencia`
--

CREATE TABLE IF NOT EXISTS `preferencia` (
  `id` int(11) NOT NULL,
  `nombre` varchar(128) NOT NULL,
  `descripcion` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `regalo`
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
  `gratis` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_empresa` (`id_empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `regalo_pertenece_categoria`
--

CREATE TABLE IF NOT EXISTS `regalo_pertenece_categoria` (
  `id_regalo` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  PRIMARY KEY (`id_regalo`,`id_categoria`),
  KEY `id_categoria` (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE IF NOT EXISTS `servicio` (
  `id` int(11) NOT NULL,
  `nombre` varchar(128) NOT NULL,
  `precio` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta`
--

CREATE TABLE IF NOT EXISTS `tarjeta` (
  `id` int(11) NOT NULL,
  `banco` varchar(128) NOT NULL,
  `numero` bigint(20) NOT NULL,
  `codigo_seguridad` smallint(6) NOT NULL,
  `tipo_tarjeta` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `facebook` varchar(128) NOT NULL,
  `domicilio` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_registro_tarjeta`
--

CREATE TABLE IF NOT EXISTS `usuario_registro_tarjeta` (
  `id_usuario` int(11) NOT NULL,
  `id_tarjeta` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_tarjeta`),
  KEY `id_tarjeta` (`id_tarjeta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_tiene_preferencia`
--

CREATE TABLE IF NOT EXISTS `usuario_tiene_preferencia` (
  `id_usuario` int(11) NOT NULL,
  `id_preferencia` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_preferencia`),
  KEY `id_preferencia` (`id_preferencia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `categoria_agrupa_preferencias`
--
ALTER TABLE `categoria_agrupa_preferencias`
  ADD CONSTRAINT `categoria_agrupa_preferencias_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `categoria_agrupa_preferencias_ibfk_1` FOREIGN KEY (`id_preferencia`) REFERENCES `preferencia` (`id`);

--
-- Filtros para la tabla `empresa_paga_servicio`
--
ALTER TABLE `empresa_paga_servicio`
  ADD CONSTRAINT `empresa_paga_servicio_ibfk_2` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id`),
  ADD CONSTRAINT `empresa_paga_servicio_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`);

--
-- Filtros para la tabla `empresa_recibe_servicio`
--
ALTER TABLE `empresa_recibe_servicio`
  ADD CONSTRAINT `empresa_recibe_servicio_ibfk_2` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id`),
  ADD CONSTRAINT `empresa_recibe_servicio_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`);

--
-- Filtros para la tabla `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`id_regalo`) REFERENCES `regalo` (`id`);

--
-- Filtros para la tabla `item_comprado_usuario`
--
ALTER TABLE `item_comprado_usuario`
  ADD CONSTRAINT `item_comprado_usuario_ibfk_2` FOREIGN KEY (`id_item`) REFERENCES `item` (`id`),
  ADD CONSTRAINT `item_comprado_usuario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);
search-by-email/mnforlenza@gmail.com
--
-- Filtros para la tabla `item_notifiacion_usuario`
--
ALTER TABLE `item_notifiacion_usuario`
  ADD CONSTRAINT `item_notifiacion_usuario_ibfk_2` FOREIGN KEY (`id_item`) REFERENCES `item` (`id`),
  ADD CONSTRAINT `item_notifiacion_usuario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `regalo`
--
ALTER TABLE `regalo`
  ADD CONSTRAINT `regalo_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`);

--
-- Filtros para la tabla `regalo_pertenece_categoria`
--
ALTER TABLE `regalo_pertenece_categoria`
  ADD CONSTRAINT `regalo_pertenece_categoria_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `regalo_pertenece_categoria_ibfk_1` FOREIGN KEY (`id_regalo`) REFERENCES `regalo` (`id`);

--
-- Filtros para la tabla `usuario_registro_tarjeta`
--
ALTER TABLE `usuario_registro_tarjeta`
  ADD CONSTRAINT `usuario_registro_tarjeta_ibfk_2` FOREIGN KEY (`id_tarjeta`) REFERENCES `tarjeta` (`id`),
  ADD CONSTRAINT `usuario_registro_tarjeta_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `usuario_tiene_preferencia`
--
ALTER TABLE `usuario_tiene_preferencia`
  ADD CONSTRAINT `usuario_tiene_preferencia_ibfk_2` FOREIGN KEY (`id_preferencia`) REFERENCES `preferencia` (`id`),
  ADD CONSTRAINT `usuario_tiene_preferencia_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


ALTER TABLE `empresa` 
CHANGE COLUMN `posicion` `posicion` POINT NULL ;
