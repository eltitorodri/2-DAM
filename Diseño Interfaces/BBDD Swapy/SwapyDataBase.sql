use Swapy;

create or replace table  usuarios (

	id int auto_increment primary key,
	nombre_completo varchar(100),
	password_hash varchar(100) not null,
	nickname varchar(50) unique,
	email varchar(100) unique

);

create or replace table prendas (

	id int auto_increment primary key,
	titulo 
	

);