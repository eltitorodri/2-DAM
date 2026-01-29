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

-- TABLA DE DETALLE DE INTERCAMBIOS
-- Establece una relación 1:1 con 'transacciones' (gracias a 'transaccion_id UNIQUE')
-- Esta tabla modela qué prendas están involucradas en la transacción (solicitada y ofrecida).

create or replace table intercambio_detalle (
    id int auto_increment primary key,
    
    -- Relación 1:1 con Transacciones.
    -- El 'unique not null' asegura que cada transacción tenga un solo set de detalles.
    transaccion_id int unique not null,
    constraint fk_intercambio_detalle_transaccion_id
        foreign key (transaccion_id)
        references transacciones (id),
    
    -- Prenda que el SOLICITANTE desea obtener (pertenece al propietario).
    prenda_solicitada_id int not null,
    constraint fk_intercambio_detalle_solicitada_id
        foreign key (prenda_solicitada_id)
        references prendas (id),
    
    -- Prenda que el SOLICITANTE OFRECE a cambio. 
    -- Es NULL si es un Préstamo (tipo_transaccion='Prestamo').
    prenda_ofrecida_id int,
    constraint fk_intercambio_detalle_ofrecida_id
        foreign key (prenda_ofrecida_id)
        references prendas (id)
);

-- 1. Eliminar la clave foránea (para poder modificar la columna).
-- Nombre de la clave foránea en tu esquema: fk_chat_transaccion_id
ALTER TABLE chat DROP FOREIGN KEY fk_chat_transaccion_id;

-- 2. Eliminar el índice que impide la modificación (el que usa la base de datos).
-- Usamos el nombre que causaba el conflicto: transaccion_id_3
ALTER TABLE chat DROP INDEX transaccion_id_2;

-- 3. Modificar la columna: la hacemos ÚNICA y OBLIGATORIA (NOT NULL).
-- Si esta línea da error, significa que tienes filas con transaccion_id nulo.
ALTER TABLE chat MODIFY COLUMN transaccion_id INT UNIQUE NOT NULL;

-- 4. Volver a crear la clave foránea.
ALTER TABLE chat ADD CONSTRAINT fk_chat_transaccion_id
    FOREIGN KEY (transaccion_id)
    REFERENCES transacciones (id);

DESCRIBE chat;
-- o, dependiendo de tu base de datos:
SHOW COLUMNS FROM chat;

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

ALTER TABLE prendas
RENAME COLUMN tipoGuardado TO tipo_guardado;


SELECT
    p.titulo AS nombrePrenda,
    COUNT(c.id) AS numeroIntercambios
FROM
    prendas p
JOIN
    chat c ON p.id = c.prenda_id
GROUP BY
    p.id, p.titulo
ORDER BY
    numeroIntercambios DESC
LIMIT 5;


select u.nombre_completo as nombreCompleto, count(t.id) as numeroIntercambios
from usuarios u join transacciones t 
on (u.id = t.solicitante_id or u.id = t.propietario_id)
where t.estado = 'Aceptada'
group by u.id, u.nickname 
order by numerointercambios desc
limit 1;

-- Primero elimina la FK que está en imágenes
ALTER TABLE imagenes
DROP FOREIGN KEY fk_imagenes_prendas_id,
DROP COLUMN prendas_id;

ALTER TABLE prendas
DROP COLUMN imagen_id;


ALTER TABLE prendas DROP COLUMN imagen_id;

SHOW CREATE TABLE prendas;

ALTER TABLE prendas DROP FOREIGN KEY fk_prendas_imagen_id;

ALTER TABLE prendas DROP INDEX imagen_id;

ALTER TABLE prendas 
ADD CONSTRAINT fk_prendas_imagen_id FOREIGN KEY (imagen_id) REFERENCES imagenes(id);


-- Luego añade la FK en prendas
ALTER TABLE prendas
ADD imagen_id INT NULL DEFAULT NULL UNIQUE,  -- UNIQUE asegura que una imagen solo esté en una prenda
ADD CONSTRAINT fk_prendas_imagen_id
    FOREIGN KEY (imagen_id)
    REFERENCES imagenes(id);

ALTER TABLE prendas_colores
ADD CONSTRAINT fk_prenda
FOREIGN KEY (prendas_id) REFERENCES prendas(id)
ON DELETE CASCADE;

ALTER TABLE chat
DROP FOREIGN KEY fk_chat_prenda_id;

ALTER TABLE chat
ADD CONSTRAINT fk_chat_prenda_id
FOREIGN KEY (prenda_id) REFERENCES prendas(id)
ON DELETE CASCADE;

ALTER TABLE mensaje
DROP FOREIGN KEY fk_mensaje_chat_id;

ALTER TABLE mensaje
ADD CONSTRAINT fk_mensaje_chat_id
FOREIGN KEY (chat_id) REFERENCES chat(id)
ON DELETE CASCADE;

ALTER TABLE notificaciones
DROP FOREIGN KEY fk_notificaciones_chat_id;

ALTER TAB LE notificaciones
ADD CONSTRAINT fk_notificaciones_chat_id
FOREIGN KEY (chat_id) REFERENCES chat(id)
ON DELETE CASCADE;

ALTER TABLE prendas
ADD CONSTRAINT fk_prendas_imagen
FOREIGN KEY (imagen_id)
REFERENCES imagenes(id);

