DROP database swapy;

CREATE database swapy;

use swapy;

create or replace table usuarios (
	id int auto_increment primary key,
	nombre_completo varchar(100),
	password_hash varchar(100) not null,
	nickname varchar(50) unique,
	email varchar(100) unique
);

create or replace table colores (
	id int auto_increment primary key,
	nombre_color varchar(100)
);

create or replace table marcas (
	id int auto_increment primary key,
	nombre varchar(100) unique
);

create or replace table categorias (
	id int auto_increment primary key,
	nombre varchar(100) unique
);

create or replace table prendas_tipos (
	id int auto_increment primary key,
	nombre varchar(100) unique
);

drop table prendas_colores;

CREATE TABLE prendas_colores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prendas_id INT NOT NULL,
    colores_id INT NOT NULL,
    CONSTRAINT fk_prendas_colores_prendas_id
        FOREIGN KEY (prendas_id) REFERENCES prendas(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_prendas_colores_colores_id
        FOREIGN KEY (colores_id) REFERENCES colores(id)
        ON DELETE CASCADE
);


create or replace table prendas (
	id int auto_increment primary key,
	titulo varchar(150),
	descripcion varchar(1000),
	fecha_agregado date,
	estado enum('Prestamo', 'Intercambio') not null,
	tipoGuardado enum('Pendiente', 'Guardado') not null,

	categorias_id int,
	constraint fk_prendas_categorias_id
		foreign key (categorias_id)
		references categorias (id),

	marcas_id int,
	constraint fk_prendas_marcas_id
		foreign key (marcas_id)
		references marcas (id),

	prendas_tipo_id int,
	constraint fk_prendas_prendas_tipo_id
		foreign key (prendas_tipo_id)
		references prendas_tipos (id),

	usuario_id int not null,
	constraint fk_prendas_usuario_id
		foreign key (usuario_id)
		references usuarios (id)

);

create or replace table imagenes (
	id int auto_increment primary key,
	orden int,
	url_imagen varchar(255),

	prendas_id int not null,
	constraint fk_imagenes_prendas_id
		foreign key (prendas_id)
		references prendas (id)
);

create or replace table transacciones (
	id int auto_increment primary key,
	tipo_transaccion enum('Prestamo', 'Intercambio') not null,
	estado enum('Pendiente', 'Aceptada', 'Rechazada', 'Finalizada') not null,
	fecha_fin_real date,
	fecha_inicio date,

	solicitante_id int not null,
	constraint fk_transacciones_solicitante_id
		foreign key (solicitante_id)
		references usuarios (id),

	propietario_id int not null,
	constraint fk_transacciones_propietario_id
		foreign key (propietario_id)
		references usuarios (id)

);

create or replace table calificacion (
	id int auto_increment primary key,
	rating decimal(2, 1),
	comentario varchar(1000),
	fecha_valoracion date,

	transaccion_id int unique not null,
	constraint fk_calificacion_transaccion_id
		foreign key (transaccion_id)
		references transacciones (id)
);

create or replace table chat (
	id int auto_increment primary key,

	prenda_id int not null,
	constraint fk_chat_prenda_id
		foreign key (prenda_id)
		references prendas (id),

	transaccion_id int unique,
	constraint fk_chat_transaccion_id
		foreign key (transaccion_id)
		references transacciones (id)
);

create or replace table mensaje (
	id int auto_increment primary key,
	fecha_envio datetime not null,
	contenido varchar(1000),
	es_leido boolean default false,

    usuario_id int not null,
    constraint fk_mensaje_usuario_id
		foreign key (usuario_id)
		references usuarios (id),

	chat_id int not null,
	constraint fk_mensaje_chat_id
		foreign key (chat_id)
		references chat (id)
);

create or replace table notificaciones (
	id int auto_increment primary key,
	fecha_creacion datetime not null,
	mensaje varchar(1000),
	es_leido boolean default false,

    usuario_id int not null,
    constraint fk_notificaciones_usuario_id
		foreign key (usuario_id)
		references usuarios (id),

	chat_id int,
	constraint fk_notificaciones_chat_id
		foreign key (chat_id)
		references chat (id)
);

alter table prendas_colores
    add constraint fk_prendas_colores_prendas_id
        foreign key (prendas_id)
        references prendas (id),
    add constraint fk_prendas_colores_colores_id
        foreign key (colores_id)
        references colores (id);