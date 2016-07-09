insert into usuario values (1,'mnforlenza@gmail.com','password','mnforlenza@gmail.com','miface','190:102');

insert into categoria values (1,'Comida','Comida');
insert into categoria values (2,'Entretenimiento','Entretenimiento');
insert into categoria values (3,'Vestimenta','Vestimenta');

INSERT INTO `empresa` (`id`, `codigo_compania`, `nombre`, `password`, `posicion`, `domicilio`) VALUES ('1', '1', 'McDonalds', 'password', NULL, '140');
INSERT INTO `empresa` (`id`, `codigo_compania`, `nombre`, `password`, `domicilio`) VALUES ('2', '2', 'Cinemark', 'password', '130:130');

INSERT INTO `regalo` (`id`, `id_empresa`, `nombre`, `precio`, `cantidad_inicial`, `stock`, `gratis`) VALUES ('1', '1', 'Big Mac', '60', '100', '3000', 'true');
INSERT INTO `regalo` (`id`, `id_empresa`, `nombre`, `precio`, `cantidad_inicial`, `stock`, `gratis`) VALUES ('2', '2', 'Entrada gratis Peli x Funcion y', '120', '20', '40', 'true');

INSERT INTO `regalo_pertenece_categoria` (`id_regalo`, `id_categoria`) VALUES ('1', '1');
INSERT INTO `regalo_pertenece_categoria` (`id_regalo`, `id_categoria`) VALUES ('2', '2');




