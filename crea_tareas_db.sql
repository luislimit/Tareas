DROP TABLE t_documento;
DROP TABLE t_imputacion;
DROP TABLE t_peticion;
DROP TABLE t_estado;
DROP TABLE t_tipodocumento;
DROP TABLE t_subcategoria;
DROP TABLE t_categoria;
DROP TABLE t_usuario;

CREATE TABLE t_categoria (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	codigo TEXT NOT NULL,
	nombre TEXT NOT NULL,
	fec_alta TEXT NOT NULL 
);

INSERT INTO t_categoria 
SELECT 
id,	codigo,	nombre,	strftime('%Y%m%d', fec_alta / 1000, 'unixepoch')
FROM tt_categoria;


CREATE TABLE t_subcategoria (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	id_categoria INTEGER NOT NULL,
	codigo TEXT NOT NULL,
	nombre TEXT NOT NULL,
	fec_alta TEXT NOT NULL, 
	CONSTRAINT t_subcategoria_t_categoria_FK FOREIGN KEY (id_categoria) REFERENCES t_categoria(id)
);

INSERT INTO t_subcategoria 
SELECT 
	id,	id_categoria, codigo, nombre,	strftime('%Y%m%d', fec_alta / 1000, 'unixepoch')
FROM tt_subcategoria;

CREATE TABLE t_estado (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	nombre TEXT NOT NULL,
	color TEXT
);

INSERT INTO t_estado SELECT * FROM tt_estado;

CREATE TABLE t_tipodocumento (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	nombre TEXT NOT NULL
);

INSERT INTO t_tipodocumento SELECT * FROM tt_tipodocumento;

CREATE TABLE t_usuario (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	codigo TEXT NOT NULL,
	email TEXT
);

INSERT INTO t_usuario SELECT * FROM tt_usuario;

CREATE TABLE t_peticion (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	codigo TEXT NOT NULL,
	asunto TEXT NOT NULL,
	id_usuario INTEGER NOT NULL, 
	fec_alta TEXT NOT NULL, 
	fec_prevista_inicio TEXT, 
	fec_prevista_fin TEXT, 
	fec_real_inicio TEXT, 
	fec_real_fin TEXT, 
	horas_prevista REAL, 
	horas_real REAL, 
	porcentaje REAL,
	descripcion TEXT,
    id_estado INTEGER,
	id_categoria INTEGER,
	id_subcategoria INTEGER,
	CONSTRAINT t_peticion_t_estado_FK FOREIGN KEY (id_estado) REFERENCES t_estado(id),
	CONSTRAINT t_peticion_t_usuario_FK FOREIGN KEY (id_usuario) REFERENCES t_usuario(id),
	CONSTRAINT t_peticion_t_categoria_FK FOREIGN KEY (id_categoria) REFERENCES t_categoria(id),
	CONSTRAINT t_peticion_t_subcategoria_FK FOREIGN KEY (id_subcategoria) REFERENCES t_subcategoria(id)
	);
	
CREATE UNIQUE INDEX t_peticion_codigo_IDX ON t_peticion (codigo);

INSERT INTO t_peticion  
SELECT 
	id,	codigo,	asunto,	id_usuario,
	strftime('%Y%m%d', fec_alta / 1000, 'unixepoch'), 
	strftime('%Y%m%d', fec_prevista_inicio / 1000, 'unixepoch'), 
	strftime('%Y%m%d', fec_prevista_fin / 1000, 'unixepoch'), 
	strftime('%Y%m%d', fec_real_inicio / 1000, 'unixepoch'), 
	strftime('%Y%m%d', fec_real_fin / 1000, 'unixepoch'), 
	horas_prevista,	horas_real,	porcentaje,	descripcion, id_estado,	id_categoria,id_subcategoria 
FROM tt_peticion ;


CREATE TABLE t_imputacion (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	id_peticion INTEGER NOT NULL,
	id_usuario INTEGER NOT NULL,
	id_estado INTEGER NOT NULL,
	id_estado_previo INTEGER NOT NULL,
	fec_alta TEXT NOT NULL, 
	fecha TEXT, 
	horas_real REAL,
	extra TEXT, 
	descripcion TEXT,
	CONSTRAINT t_imputacion_t_estado_FK FOREIGN KEY (id_estado) REFERENCES t_estado(id),
    CONSTRAINT t_imputacion_t_estado_previo_FK FOREIGN KEY (id_estado_previo) REFERENCES t_estado(id),
	CONSTRAINT t_imputacion_t_usuario_FK FOREIGN KEY (id_usuario) REFERENCES t_usuario(id),
	CONSTRAINT t_imputacion_t_peticion FOREIGN KEY (id_peticion) REFERENCES t_peticion(id)
);

INSERT INTO t_imputacion 
SELECT 
	id,	id_peticion,	id_usuario,	id_estado,	id_estado_previo,
	strftime('%Y%m%d', fec_alta / 1000, 'unixepoch'), 
	strftime('%Y%m%d', fecha / 1000, 'unixepoch'), 
	horas_real,	extra,descripcion
FROM tt_imputacion;	

CREATE TABLE t_documento (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	id_peticion INTEGER NOT NULL,
	id_tipodocumento INTEGER NOT NULL,
	fichero TEXT NOT NULL,
	id_usuario INTEGER NOT NULL, 
	fec_alta TEXT NOT NULL, 
	descripcion TEXT,
	CONSTRAINT t_imputacion_t_peticion FOREIGN KEY (id_peticion) REFERENCES t_peticion(id),
	CONSTRAINT t_documento_t_usuario_FK FOREIGN KEY (id_usuario) REFERENCES t_usuario(id),	
	CONSTRAINT t_imputacion_t_tipodocumento FOREIGN KEY (id_tipodocumento) REFERENCES t_tipodocumento(id)
);
