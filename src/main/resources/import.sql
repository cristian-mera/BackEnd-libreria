/* Populate tabla libros */


INSERT INTO libros (titulo, isbn, num_paginas, estado, create_at) VALUES('Harry Potter and the sorcerer stone', '1112354821562', '245', true,'2018-01-01');
INSERT INTO libros (titulo, isbn, num_Paginas, estado, create_at) VALUES('Harry Potter and the chamber of secrets', '22216482', '302', true, '2018-01-02');
INSERT INTO libros (titulo, isbn, num_Paginas, estado, create_at) VALUES('Harry Potter and the Prisioner of Azkaban', '33342454896', '342', true, '2018-01-03');
INSERT INTO libros (titulo, isbn, num_Paginas, estado, create_at) VALUES('Harry Potter and the Goblet of Fire', '444875622', '292', true, '2018-01-04');
INSERT INTO libros (titulo, isbn, num_Paginas, estado, create_at) VALUES('Harry Potter and the Order of the Phoenix', '555214789', '385', true, '2018-01-05');
INSERT INTO libros (titulo, isbn, num_Paginas, estado, create_at) VALUES('Harry Potter and the Half-Blood Prince', '665824562', '295', true, '2018-01-06');
INSERT INTO libros (titulo, isbn, num_Paginas, estado, create_at) VALUES('Harry Potter and the Deathly Hallows', '774152896669', '268', true, '2018-01-07');

INSERT INTO usuarios (username, password, enabled, nombre, email) VALUES ('andres','$2a$10$KkhA25FuY48cvVo7Cd/gWeAMXiJQVGV7scAqLbXkHtt1fFHSL6JrK',true, 'Andres','profesor@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, email) VALUES ('admin','$2a$10$vhlg9fXuFn75IRQowmNe9uaiAhqn3NJQJtKfiDIAnoFquNUf/FNQa',true, 'John','jhon.doe@gmail.com');


INSERT INTO roles (id,nombre) VALUES (1,'ROLE_USER');
INSERT INTO roles (id,nombre) VALUES (2,'ROLE_ADMIN');


INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);