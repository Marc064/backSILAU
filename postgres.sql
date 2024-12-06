-- Crear el rol SILAU
CREATE ROLE SILAU WITH LOGIN PASSWORD 'SilauSAS';

-- Conectar a la base de datos recién creada
\c SilauSAS_DB;

-- Crear el esquema SilauSAS_DB
CREATE SCHEMA SilauSAS_DB AUTHORIZATION SILAU;

-- Crear secuencias
CREATE SEQUENCE SilauSAS_DB.administrador_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE SilauSAS_DB.cliente_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE SilauSAS_DB.detalle_pedido_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE SilauSAS_DB.empresa_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE SilauSAS_DB.linea_producto_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE SilauSAS_DB.pedido_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE SilauSAS_DB.producto_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

-- Crear tabla administrador
CREATE TABLE SilauSAS_DB.aministrador (
	id_administrador int8 NOT NULL,
	contraseña varchar(255) NOT NULL,
	nombre varchar(255) NOT NULL,
	usuario varchar(255) NOT NULL,
	CONSTRAINT aministrador_pkey PRIMARY KEY (id_administrador)
);


-- SilauSAS_DB.linea_producto definition

-- Drop table

-- DROP TABLE SilauSAS_DB.linea_producto;

CREATE TABLE SilauSAS_DB.linea_producto (
	id_linea_producto int8 NOT NULL,
	nombre_linea varchar(255) NULL,
	CONSTRAINT linea_producto_pkey PRIMARY KEY (id_linea_producto)
);


-- SilauSAS_DB.cliente definition

-- Drop table

-- DROP TABLE SilauSAS_DB.cliente;

CREATE TABLE SilauSAS_DB.cliente (
	activo bool NOT NULL,
	id_administrador int8 NOT NULL,
	id_cliente int8 NOT NULL,
	apellido varchar(255) NOT NULL,
	celular varchar(255) NOT NULL,
	correo varchar(255) NOT NULL,
	nombre varchar(255) NOT NULL,
	CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente),
	CONSTRAINT fkksdbiagtvdgdf288itjom6e5p FOREIGN KEY (id_administrador) REFERENCES SilauSAS_DB.aministrador(id_administrador)
);


-- SilauSAS_DB.empresa definition

-- Drop table

-- DROP TABLE SilauSAS_DB.empresa;

CREATE TABLE SilauSAS_DB.empresa (
	id_cliente int8 NOT NULL,
	id_empresa int8 NOT NULL,
	codigo varchar(255) NOT NULL,
	direccion varchar(255) NOT NULL,
	nombre varchar(255) NOT NULL,
	CONSTRAINT empresa_pkey PRIMARY KEY (id_empresa),
	CONSTRAINT fk94rvdehivjvy9xww0qtxyapme FOREIGN KEY (id_cliente) REFERENCES SilauSAS_DB.cliente(id_cliente)
);


-- SilauSAS_DB.pedido definition

-- Drop table

-- DROP TABLE SilauSAS_DB.pedido;

CREATE TABLE SilauSAS_DB.pedido (
	total float8 NOT NULL,
	fecha_entrega timestamp(6) NULL,
	fecha_ingreso timestamp(6) NOT NULL,
	id_administrador int8 NOT NULL,
	id_cliente int8 NOT NULL,
	id_pedido int8 NOT NULL,
	detalles varchar(255) NULL,
	estado varchar(255) NOT NULL,
	CONSTRAINT pedido_pkey PRIMARY KEY (id_pedido),
	CONSTRAINT fk1cecahmjb3wqsgbkfu93hnhw7 FOREIGN KEY (id_administrador) REFERENCES SilauSAS_DB.aministrador(id_administrador),
	CONSTRAINT fk9y4jnyp1hxqa386cnly0ay9uw FOREIGN KEY (id_cliente) REFERENCES SilauSAS_DB.cliente(id_cliente)
);


-- SilauSAS_DB.producto definition

-- Drop table

-- DROP TABLE SilauSAS_DB.producto;

CREATE TABLE SilauSAS_DB.producto (
	cantidad_existente int4 NOT NULL,
	precio float8 NOT NULL,
	id_administrador int8 NOT NULL,
	id_producto int8 NOT NULL,
	linea_producto int8 NOT NULL,
	descripcion varchar(255) NOT NULL,
	estado varchar(255) NOT NULL,
	imagen varchar(255) NOT NULL,
	nombre varchar(255) NOT NULL,
	tamaño varchar(255) NOT NULL,
	CONSTRAINT producto_pkey PRIMARY KEY (id_producto),
	CONSTRAINT fke88iyec91oq1ot33aiy3qv4x3 FOREIGN KEY (linea_producto) REFERENCES SilauSAS_DB.linea_producto(id_linea_producto),
	CONSTRAINT fklnq4wf1mt6onve9qtf0ta8e4y FOREIGN KEY (id_administrador) REFERENCES SilauSAS_DB.aministrador(id_administrador)
);


-- SilauSAS_DB.detalle_pedido definition

-- Drop table

-- DROP TABLE SilauSAS_DB.detalle_pedido;

CREATE TABLE SilauSAS_DB.detalle_pedido (
	cantidad int4 NOT NULL,
	id_detalle_pedido int8 NOT NULL,
	id_pedido int8 NOT NULL,
	id_producto int8 NOT NULL,
	CONSTRAINT detalle_pedido_pkey PRIMARY KEY (id_detalle_pedido),
	CONSTRAINT fk7n9hdifr08joboojejveby1vr FOREIGN KEY (id_pedido) REFERENCES SilauSAS_DB.pedido(id_pedido),
	CONSTRAINT fkjfm9pk0w2eag8tx8lu6pbego6 FOREIGN KEY (id_producto) REFERENCES SilauSAS_DB.producto(id_producto)
);  

-- Asignar permisos al rol SILAU
GRANT ALL ON SCHEMA SilauSAS_DB TO SILAU;
GRANT USAGE ON SCHEMA SilauSAS_DB TO SILAU;

-- Permisos sobre secuencias
GRANT ALL ON SEQUENCE SilauSAS_DB.administrador_seq TO SILAU;
GRANT ALL ON SEQUENCE SilauSAS_DB.cliente_seq TO SILAU;
GRANT ALL ON SEQUENCE SilauSAS_DB.detalle_pedido_seq TO SILAU;
GRANT ALL ON SEQUENCE SilauSAS_DB.empresa_seq TO SILAU;
GRANT ALL ON SEQUENCE SilauSAS_DB.linea_producto_seq TO SILAU;
GRANT ALL ON SEQUENCE SilauSAS_DB.pedido_seq TO SILAU;
GRANT ALL ON SEQUENCE SilauSAS_DB.producto_seq TO SILAU;

-- Permisos sobre tablas
GRANT ALL ON TABLE SilauSAS_DB.aministrador TO SILAU;
GRANT ALL ON TABLE SilauSAS_DB.cliente TO SILAU;
GRANT ALL ON TABLE SilauSAS_DB.empresa TO SILAU;
GRANT ALL ON TABLE SilauSAS_DB.pedido TO SILAU;
GRANT ALL ON TABLE SilauSAS_DB.producto TO SILAU;
GRANT ALL ON TABLE SilauSAS_DB.detalle_pedido TO SILAU;

-- Permisos sobre funciones
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA SilauSAS_DB TO SILAU;

-- Permisos sobre secuencias
GRANT UPDATE, SELECT, USAGE ON ALL SEQUENCES IN SCHEMA SilauSAS_DB TO SILAU;

-- Permisos sobre los objetos futuros en el esquema
ALTER DEFAULT PRIVILEGES IN SCHEMA SilauSAS_DB GRANT ALL ON TABLES TO SILAU;
ALTER DEFAULT PRIVILEGES IN SCHEMA SilauSAS_DB GRANT EXECUTE ON FUNCTIONS TO SILAU;
ALTER DEFAULT PRIVILEGES IN SCHEMA SilauSAS_DB GRANT UPDATE, SELECT, USAGE ON SEQUENCES TO SILAU;

INSERT INTO SilauSAS_DB.aministrador (id_administrador, contraseña, nombre, usuario)
VALUES (1, '$2y$10$Or2vr6ImhDby0EuPJKANVeS.Ya685z39Bh3uzNzrWvFrgDsUpZuwO', 'Admin', 'Admin');
