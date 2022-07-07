use avaliacao_test;

DELETE FROM cliente;
DELETE FROM pedido;

INSERT IGNORE INTO cliente values 
(1, "Cliente A"),
(2, "Cliente B"),
(3, "Cliente C"),
(4, "Cliente D"),
(5, "Cliente F"),
(6, "Cliente G"),
(7, "Cliente H"),
(8, "Cliente I"),
(9, "Cliente J"),
(10, "Cliente K");